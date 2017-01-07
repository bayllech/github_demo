package com.kaishengit.dao;

import com.kaishengit.dao.Impl.UserDaoImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserDaoTestCase {
    @Test
    public void load() {
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = (UserDaoImpl) applicationContext.getBean("UserDaoImpl");
        userDao.save();
        userDao.update();

    }
}
