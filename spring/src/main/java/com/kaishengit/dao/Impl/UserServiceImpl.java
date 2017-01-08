package com.kaishengit.dao.Impl;

import com.kaishengit.dao.UserDao;
import com.kaishengit.dao.UserService;

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
    public void update() {
        userDao.update();
    }
}
