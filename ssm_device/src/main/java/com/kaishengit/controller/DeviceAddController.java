package com.kaishengit.controller;

import com.kaishengit.pojo.Device;
import com.kaishengit.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/device/add")
public class DeviceAddController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/new")
    public String add() {
        return "/device/add/new";
    }

    @PostMapping("/new")
    public String add(Device device, RedirectAttributes redirectAttributes) {
        device.setCurrentNum(device.getTotalNum());
        deviceService.saveDevice(device);
        redirectAttributes.addFlashAttribute("message", "添加成功");
        return "/device/add/new";
    }
}
