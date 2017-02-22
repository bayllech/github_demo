package com.kaishengit.service;

import com.kaishengit.pojo.Role;
import com.kaishengit.pojo.User;

import java.util.List;

/**
 * Created by bayllech on 2017/1/8.
 */
public interface UserService {
    void save(User user, Integer[] roleIds);

    List<User> findAll();

    void delById(Integer id);

    User findById(Integer id);

    void editUser(User user, Integer[] roleIds);

    List<Role> findAllRole();
}
