package com.kaishengit.dao;

import com.kaishengit.entity.User;
import com.kaishengit.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * Created by bayllech on 2016/12/15.
 */
public class UserDao {
    public User findUserByName(String username) {
        String sql = "select * from t_user where username = ?";
        return DbHelp.query(sql, new BeanHandler<>(User.class), username);
    }

    public void saveUser(User user) {
        String sql = "insert into t_user(username,password,email,phone,state,avatar) values (?,?,?,?,?,?)";
        DbHelp.update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getPhone(), user.getState(), user.getAvatar());
    }

    public User findUserByEmail(String email) {
        System.out.println(email);
        String sql = "select * from t_user where email = ?";
        return DbHelp.query(sql, new BeanHandler<>(User.class), email);
    }

    public void update(User user) {
        
    }
}
