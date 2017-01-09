package com.kaishengit.service.Impl;

import com.kaishengit.dao.UserDao;
import com.kaishengit.service.UserService;

/**
 * Created by bayllech on 2017/1/8.
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

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
