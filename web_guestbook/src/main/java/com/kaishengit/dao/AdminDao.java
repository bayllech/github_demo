package com.kaishengit.dao;

import com.kaishengit.entity.Admin;
import com.kaishengit.util.DbHelp;
import org.apache.commons.dbutils.handlers.BeanHandler;

public class AdminDao {

    public Admin findByName(String name) {
        String sql = "select * from t_admin where name=?";
        Admin admin = DbHelp.query(sql, new BeanHandler<>(Admin.class), name);
        return admin;
    }

    /**
     * 添加用户
     * @param username
     * @param password
     */
    public void addAdmin(String username, String password) {
        String sql = "insert into t_admin(username,password) values(?,?)";
        DbHelp.update(sql,username,password);
    }
}
