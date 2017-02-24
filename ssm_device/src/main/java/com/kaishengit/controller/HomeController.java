package com.kaishengit.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    @GetMapping("/")
    public String login() {
        return "/login";
    }

    @PostMapping("/")
    public String home(String userName, String password, RedirectAttributes redirectAttributes) {

//        shiro登录
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(userName, password));
            return "redirect:/device/rent";
        } catch (AuthenticationException ex) {
            ex.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "账号或密码错误");
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(RedirectAttributes redirectAttributes) {
        //安全退出
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message","你已安全退出");
        return "redirect:/";
    }

    @RequestMapping("/403")
    public String error403() {
        return "/403";
    }
}
