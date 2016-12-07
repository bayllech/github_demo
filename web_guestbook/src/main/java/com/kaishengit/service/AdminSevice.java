package com.kaishengit.service;

import com.kaishengit.dao.AdminDao;
import com.kaishengit.entity.Admin;
import com.kaishengit.exception.ServiceException;

/**
 * Created by 帅比 on 2016/11/23.
 */
public class AdminSevice {

    /**
     * 注册查找用户名
     * @param username
     */
    public void findByName(String username) {
        AdminDao adminDao = new AdminDao();
        Admin admin = adminDao.findByName(username);

        if (admin != null) {
            throw new ServiceException("账号已被使用");
        }
    }

    /**
     * 登录验证账号密码
     * @param name
     * @param password
     * @return
     */
    public Admin findByName(String name,String password) {
        AdminDao admindao = new AdminDao();
        Admin admin = admindao.findByName(name);

        if (admin != null && password.equals(admin.getPassword())) {
            return admin;
        } else {
            throw new ServiceException("账号或密码错误，请重新登录");
        }

    }

    /**
     * 注册保存用户
     * @param username
     * @param password
     */
    public void addAdmin(String username, String password) {
        AdminDao adminDao = new AdminDao();
        adminDao.addAdmin(username, password);
    }
}
