package com.kaishengit.service;

import com.kaishengit.pojo.User;

public class UserService {

    public void saveUser(User user) {
        System.out.println(user.getUsername()+" 登录");
    }
}
