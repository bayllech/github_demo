package com.kaishengit.controller;

import com.kaishengit.pojo.Device;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/device/add")
public class DeviceAddController {

    @GetMapping("/new")
    public String add() {
        return "/device/add/new";
    }

    @PostMapping("/new")
    public String add(Device device) {
        return "";
    }
}
