package com.kaishengit.controller;

import com.kaishengit.dto.AjaxResult;
import com.kaishengit.pojo.Device;
import com.kaishengit.pojo.WorkType;
import com.kaishengit.service.WorkTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/workOut/rent")
public class WorkOutController {

    @Autowired
    private WorkTypeService workTypeService;

    @GetMapping
    public String list(Model model) {
        List<WorkType> workTypeList = workTypeService.findAllWorkType();
        model.addAttribute("deviceList", workTypeList);
        return "/workout/add";
    }

    /**
     * 根据id查找工种
     * @param id
     * @return
     */
    @GetMapping("/workType.json")
    @ResponseBody
    public AjaxResult deviceJson(Integer id) {
        WorkType workType = workTypeService.findDeviceById(id);
        if(workType == null) {
            return new AjaxResult(AjaxResult.ERROR,"工种暂不存在");
        } else {
            return new AjaxResult(workType);
        }
    }

}
