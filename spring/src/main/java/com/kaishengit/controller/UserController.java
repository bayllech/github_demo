package com.kaishengit.controller;

import com.kaishengit.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add() {
        return "user/add";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String save (User user) {
        System.out.println(user.getUsername() + "" + user.getPassword());
        return "redirect:/home";
    }
/*

    @RequestMapping
    public String showUser(int id, Model model) {
        System.out.println(id);
        model.addAttribute("id", id);
        return "user/show";
    }
*/

    @RequestMapping(value = "/{id:\\d{3,}}/{type}",method = RequestMethod.GET)
    public String showUser(@PathVariable Integer id,
                           @PathVariable String type,
                           Model model) {
        model.addAttribute("id", id);
        model.addAttribute("type",type);
        return "user/show";
    }
}
