package com.kaishengit.controller;

import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
/*
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
 */
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add() {
        return "user/add";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String save (User user, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "添加成功");
        return "redirect:/user/list";
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

    @GetMapping("/list")
    public String userList(Model model) {
        User user = new User("aa", "123123");
        User user1 = new User("bb", "111111");
        List<User> userList = Arrays.asList(user, user1);
        model.addAttribute("userList", userList);
        return "user/list";
    }

    @GetMapping(value = "/check/{name}",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String newUser(@PathVariable String name) {
        if ("tom".equals(name)) {
            return "可用";
        } else {
            return "不可用";
        }
    }

    @GetMapping("/see/user")
    @ResponseBody
    public User seeUser() {
        User user = new User("aa", "123123");
        return user;
    }

    @GetMapping("/upload")
    public String uploadFile() {
        return "user/upload";
    }

    @PostMapping("/upload")
    public String saveFile(String name, MultipartFile multipartFile) {
        if (multipartFile == null) {
            System.out.println("null");
        }
        /*System.out.println(multipartFile.getName());
        System.out.println(multipartFile.getOriginalFilename());
        System.out.println(multipartFile.getContentType());*/
        return "redirect:/home";
    }


}
