package com.kaishengit.controller;

import com.google.common.collect.Maps;
import com.kaishengit.dto.AjaxResult;
import com.kaishengit.dto.DataTableResult;
import com.kaishengit.pojo.DeviceRent;
import com.kaishengit.pojo.Finance;
import com.kaishengit.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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

    @PostMapping("/confirm")
    @ResponseBody
    public String confirm(Integer id) {
        //更新state
        Finance finance = financeService.findFinanceById(id);
        if (finance == null) {
            return "error";
        } else {
            finance.setState(Finance.STATE_COMPLETE);
            financeService.updateState(finance);
            return "success";
        }
    }




}
