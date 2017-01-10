package com.kaishengit.dao;

import com.kaishengit.mapper.UserMapper;
import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 * Created by bayllech on 2017/1/9.
 */
public class SpringTestCase {
    @Test
    public void load () {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        UserMapper userMapper = (UserMapper) applicationContext.getBean("userMapper");
        /*User user = new User("lili","123123");
        userMapper.save(user);*/
        User user = userMapper.findById(1009);
        System.out.println(user);




       /* UserService userService = (UserService) applicationContext.getBean("userService");
        userService.save();
        userService.getNum();*/
    }
}
