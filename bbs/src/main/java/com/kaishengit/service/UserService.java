package com.kaishengit.service;

import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.User;
import com.kaishengit.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;

/**
 * Created by bayllech on 2016/12/15.
 */
public class UserService {
    UserDao userDao = new UserDao();
    public boolean validateUsername(String username) {
        String sql = "select * from t_user where username = ?";
        User user = DbHelp.query(sql, new BeanHandler<>(User.class), username);
        if (user == null) {
            return true;
        } else {
            return false;
        }
    }
}
