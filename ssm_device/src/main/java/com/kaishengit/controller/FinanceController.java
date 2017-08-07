package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.dto.AjaxResult;
import com.kaishengit.dto.DataTableResult;
import com.kaishengit.pojo.DeviceRent;
import com.kaishengit.pojo.Finance;
import com.kaishengit.service.FinanceService;
import com.kaishengit.shiro.ShiroUtil;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/finance")
public class FinanceController {

    @Autowired
    private FinanceService financeService;

    @GetMapping("/day")
    public String manage() {
        return "/finance/day";
    }

    /**
     * 显示日统计表
     * @param request
     * @return
     */
    @GetMapping("/day/load")
    @ResponseBody
    public DataTableResult dayLoad(HttpServletRequest request) {
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        String date = request.getParameter("date");

        Map<String,Object> queryParam = Maps.newHashMap();
        queryParam.put("start", start);
        queryParam.put("length", length);
        queryParam.put("date", date);

        List<Finance> financeList = financeService.findFinanceByParam(queryParam);
        Long count = financeService.count();
        Long filterCount = financeService.filterCount(queryParam);

        return new DataTableResult(draw,count,filterCount,financeList);
    }

    /**
     * 确认收付款
     * @param id
     * @return
     */
    @PostMapping("/confirm")
    @ResponseBody
    public String confirm(Integer id) {
        //更新state
        Finance finance = financeService.findFinanceById(id);
        if (finance == null) {
            return "error";
        } else {
            finance.setState(Finance.STATE_COMPLETE);
            finance.setConfirmDate(DateTime.now().toString("yyyy-MM-dd"));
            finance.setConfirmUser(ShiroUtil.getCurrentUserName());
            financeService.updateState(finance);
            return "success";
        }
    }

    @GetMapping("/day/{type}/{today}")
    @ResponseBody
    public AjaxResult day(@PathVariable String type,@PathVariable String today) {
        type = "in".equals(type) ? "收入" : "支出";
        List<Map<String,Object>> dayData = financeService.getDayData(type,today);
        return new AjaxResult(dayData);
    }




}
