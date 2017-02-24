package com.kaishengit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/wx")
public class WxController {

    /**
     * 微信初始化
     * @param msg_signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @GetMapping("/init")
    @ResponseBody
    public String init(String msg_signature,String timestamp,String nonce,String echostr) {
        return "";
    }
}
