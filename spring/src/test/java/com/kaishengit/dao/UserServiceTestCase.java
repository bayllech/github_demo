package com.kaishengit.dao;

import com.kaishengit.dao.Impl.UserServiceImpl;
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
        UserService userService = (UserServiceImpl) applicationContext.getBean("userService");
        userService.save();

    }
}
