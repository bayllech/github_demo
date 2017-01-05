package com.kaishengit.mapper;

import com.kaishengit.pojo.User;

import java.util.List;

/**
 * Created by bayllech on 2017/1/5.
 */
public interface UserMapper {
    User findUserById(Integer id);
    void saveUser(User user);
    List<User> findAll();
}
