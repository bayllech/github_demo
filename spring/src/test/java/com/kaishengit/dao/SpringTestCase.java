package com.kaishengit.dao;

import com.kaishengit.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by bayllech on 2017/1/9.
 */
public class SpringTestCase {
    @Test
    public void load () {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.save();
        userService.getNum();
    }
}
