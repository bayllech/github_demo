package com.kaishengit.service;

import com.kaishengit.dao.AdminDao;
import com.kaishengit.entity.Admin;
import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Config;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bayllech on 2016/12/29.
 */
public class AdminService {
    AdminDao adminDao = new AdminDao();
    Logger logger = LoggerFactory.getLogger(AdminService.class);

    /**
     * 管理员登录
     * @param adminName
     * @param password
     * @param ip
     * @return
     */
    public Admin adminLogin(String adminName, String password, String ip) {
            Admin admin = adminDao.findAdminByName(adminName);
            if (admin == null || !admin.getPassword().equals(DigestUtils.md5Hex(Config.get("user.password.salt") + password))) {
                throw new ServiceException("账号或密码错误");
            } else {
                logger.info("管理员{}在ip:{}处登录",admin.getAdminname(),ip);
                return admin;
            }
        }
    }

