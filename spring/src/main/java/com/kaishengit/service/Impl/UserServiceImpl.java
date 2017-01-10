package com.kaishengit.service.Impl;

import com.kaishengit.dao.UserDao;
import com.kaishengit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /*@Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }*/

    @Override
    public void save() {
        userDao.save();
    }

    @Override
    public int getNum() throws RuntimeException {
        System.out.println("get num...");
//        throw new RuntimeException("故意异常");
        return 100;
    }
}
