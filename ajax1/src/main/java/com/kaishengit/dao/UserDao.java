package com.kaishengit.dao;

import com.kaishengit.entity.User;
import com.kaishengit.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

/**
 * Created by 帅比 on 2016/12/14.
 */
public class UserDao {
    public User findById(Integer id) {
        String sql = "select * from t_user where id = ?";
        System.out.println(sql);
        return DbHelp.query(sql, new BeanHandler<User>(User.class),id);
    }

    public List<User> findAll() {
        String sql = "select * from t_user";
        System.out.println(sql);
        return DbHelp.query(sql,new BeanListHandler<User>(User.class));
    }
}
