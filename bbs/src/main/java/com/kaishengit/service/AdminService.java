package com.kaishengit.service;

import com.kaishengit.dao.AdminDao;
import com.kaishengit.entity.Admin;
import com.kaishengit.entity.Topic;
import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Config;
import com.kaishengit.util.Page;
import com.kaishengit.vto.HomeView;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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

    /**
     * 查找首页显示内容
     * @return
     */
    public Page<HomeView> homeView(Integer pageNo) {
        int count = adminDao.countDay();
        Page<HomeView> homeViewPage = new Page<>(count, pageNo);
        List<HomeView> homeViewList = adminDao.homeView(homeViewPage.getStart(),homeViewPage.getPageSize());
        homeViewPage.setItems(homeViewList);
        return homeViewPage;
    }

}

