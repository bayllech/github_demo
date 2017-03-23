package cn.bayllech.conttoller;

import cn.bayllech.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class HelloController {

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "tom");
        List<String> nameList = Arrays.asList("aa", "bb", "cc");
        model.addAttribute("nameList", nameList);

        return "index";
    }

    @GetMapping("/main")
    public String main(Model model) {
       /* model.addAttribute("userList", userService.findAll());*/
        model.addAttribute("userList", userService.find());
        return "main";
    }

}
