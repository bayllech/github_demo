package com.kaishengit.controller;

import com.kaishengit.dto.AjaxResult;
import com.kaishengit.dto.DeviceRentDto;
import com.kaishengit.exception.NotFoundException;
import com.kaishengit.pojo.*;
import com.kaishengit.service.DeviceService;
import com.kaishengit.service.WorkTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/workOut/rent")
public class WorkOutController {

    @Autowired
    private WorkTypeService workTypeService;

    @Autowired
    private DeviceService deviceService;

    @GetMapping
    public String list(Model model) {
        List<DeviceRent> rentList = deviceService.findAllRent();
        model.addAttribute("rentList", rentList);
        return "/workout/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
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

    /**
     * 保存合同
     * @param deviceRentDto 设备与合同信息
     * @return
     */
    @PostMapping("/new")
    @ResponseBody
    public AjaxResult saveRent(@RequestBody DeviceRentDto deviceRentDto) {
        String serialNumber = workTypeService.saveRent(deviceRentDto);
        return new AjaxResult(AjaxResult.SUCCESS, serialNumber);

    }

    /**
     * 根据流水号查找合同
     * @param serialNumber
     * @param model
     * @return
     */
    @GetMapping("/{serialNumber:\\d+}")
    public String showRent(@PathVariable String serialNumber,Model model) {
        DeviceRent deviceRent = deviceService.findRentBySerialNum(serialNumber);
        if (deviceRent == null) {
            throw new NotFoundException();
        } else {
            List<WorkTypeDetail> rentDetailList = workTypeService.findRentDetailByRentId(deviceRent.getId());
            List<DeviceRentDoc> docList = deviceService.findDocByRentId(deviceRent.getId());

            model.addAttribute("rent", deviceRent);
            model.addAttribute("detailList", rentDetailList);
            model.addAttribute("docList", docList);

            return "/workout/show";
        }

    }

}
