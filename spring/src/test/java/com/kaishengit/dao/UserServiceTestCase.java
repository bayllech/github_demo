package com.kaishengit.dao;

import com.kaishengit.service.Impl.UserServiceImpl;
import com.kaishengit.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by bayllech on 2017/1/8.
 */
public class UserServiceTestCase {
    @Test
    public void readSet() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) applicationContext.getBean("userServiceImpl");
        userService.save();
        userService.getNum();

    }
}
