package com.kaishengit.mapper;

import com.kaishengit.pojo.User;

import java.util.List;

public interface UserMapper {
    void save(User user);
    User findById(Integer id);

    List<User> findAll();

    void delUser(Integer id);

    void editUser(User user);

}
