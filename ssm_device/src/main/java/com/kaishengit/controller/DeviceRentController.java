package com.kaishengit.controller;

import com.kaishengit.dto.AjaxResult;
import com.kaishengit.dto.DeviceRentDto;
import com.kaishengit.exception.NotFoundException;
import com.kaishengit.pojo.Device;
import com.kaishengit.pojo.DeviceRent;
import com.kaishengit.pojo.DeviceRentDetail;
import com.kaishengit.pojo.DeviceRentDoc;
import com.kaishengit.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/device/rent")
public class DeviceRentController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping
    public String list() {
        return "/device/rent/list";
    }

    /**
     * 新增租赁信息
     * @return 新增租赁页面
     */
    @GetMapping("/new")
    public String newRent(Model model) {
        List<Device> deviceList = deviceService.findAllDevice();
        model.addAttribute("deviceList",deviceList);
        return "/device/rent/new";
    }

    /**
     * 保存合同
     * @param deviceRentDto 设备与合同信息
     * @return
     */
    @PostMapping("/new")
    @ResponseBody
    public AjaxResult saveRent(@RequestBody DeviceRentDto deviceRentDto) {
        String serialNumber = deviceService.saveRent(deviceRentDto);
        return new AjaxResult(AjaxResult.SUCCESS, serialNumber);

    }

    /**
     * 根据设备ID查找设备信息
     * @param id
     * @return 设备信息
     */
    @GetMapping("/device.json")
    @ResponseBody
    public AjaxResult deviceJson(Integer id) {
        Device device = deviceService.findDeviceById(id);
        if(device == null) {
            return new AjaxResult(AjaxResult.ERROR,"设备不存在");
        } else {
            return new AjaxResult(device);
        }
    }

    @GetMapping("/{serialNumber:\\d+}")
    public String showRent(@PathVariable String serialNumber,Model model) {
        DeviceRent deviceRent = deviceService.findRentBySerialNum(serialNumber);
        if (deviceRent == null) {
            throw new NotFoundException();
        } else {
            List<DeviceRentDetail> rentDetailList = deviceService.findRentDetailByRentId(deviceRent.getId());
            List<DeviceRentDoc> docList = deviceService.findDocByRentId(deviceRent.getId());

            model.addAttribute("rent", deviceRent);
            model.addAttribute("detailList", rentDetailList);
            model.addAttribute("docList", docList);

            return "/device/rent/show";
        }

    }

}
