package com.kaishengit.service;

import com.kaishengit.pojo.User;

/**
 * Created by bayllech on 2017/1/8.
 */
public interface UserService {
    void save(User user);
    int getNum() throws RuntimeException;
}
