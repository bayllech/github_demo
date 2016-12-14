package com.kaishengit.service;

import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.User;
import com.kaishengit.util.EhcacheUtil;

import java.util.List;

/**
 * Created by 帅比 on 2016/12/14.
 */
public class UserService {
    private static final String USER_CACHE_NAME = "user";

    private UserDao userDao = new UserDao();
    private EhcacheUtil ehcacheUtil = new EhcacheUtil();

    public User findById(Integer id) {
        User user = (User) ehcacheUtil.get(USER_CACHE_NAME,"user:"+id);
        if (user == null ) {
            user = userDao.findById(id);
            ehcacheUtil.set(USER_CACHE_NAME,"user:"+id,user);
        }
        return user;
    }

    public List<User> findAll() {
        List<User> userList = (List<User>) ehcacheUtil.get(USER_CACHE_NAME, "userList");
        if (userList == null) {
            userList = userDao.findAll();
            ehcacheUtil.set(USER_CACHE_NAME,"userList",userList);
        }
        return userList;
    }

}
