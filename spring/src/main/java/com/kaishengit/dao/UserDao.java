package com.kaishengit.dao;

import com.kaishengit.pojo.User;

import java.util.List;

public interface UserDao {
    public void save(User user);
    public void update();
    List<User> findAll();
}
