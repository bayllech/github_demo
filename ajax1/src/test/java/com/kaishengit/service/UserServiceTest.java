package com.kaishengit.service;

import com.kaishengit.entity.User;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 帅比 on 2016/12/14.
 */
public class UserServiceTest {
    private UserService userService = new UserService();
    private UserServiceWithGuava userServiceWithGuava = new UserServiceWithGuava();
    @Test
    public void findById() throws Exception {
        User user = userServiceWithGuava.findById(1);

        System.out.println(user);
    }

    @Test
    public void findAll() throws Exception {
        List<User> userList = userService.findAll();
        System.out.println(userList);
    }

    @Test
    public void testSystem() {
        String str = System.getProperty("java.io.tmpdir");
        System.out.println(str);

        System.out.println(System.getProperty("java.version"));
        System.out.println(System.getProperty("java.home"));
        System.out.println(System.getProperty("os.name"));
    }

}