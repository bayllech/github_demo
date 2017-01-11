package com.kaishengit.dao;

import com.kaishengit.pojo.User;
import com.kaishengit.service.Impl.UserServiceImpl;
import com.kaishengit.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceTestCase {
    @Autowired
    private UserService userService;
    @Test
    public void readSet() {
        /*
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = (UserService) applicationContext.getBean("userServiceImpl");
        */
       /* User user = new User("meng","123123");
        userService.save(user);
        userService.getNum();
        */
       List<User> userList = userService.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
