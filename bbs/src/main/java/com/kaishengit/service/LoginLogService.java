package com.kaishengit.service;

import com.kaishengit.dao.LoginLogDao;

/**
 * Created by bayllech on 2016/12/15.
 */
public class LoginLogService {
    public void saveIp(Integer id, String ip) {
        LoginLogDao loginLogDao = new LoginLogDao();
        loginLogDao.saveIp(id, ip);
    }
}
