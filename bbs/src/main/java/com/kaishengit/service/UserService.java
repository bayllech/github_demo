package com.kaishengit.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.User;
import com.kaishengit.util.Config;

import com.kaishengit.util.EmailUtil;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by bayllech on 2016/12/15.
 */
public class UserService {
    private static Cache<String ,String> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.HOURS)
            .build();

    UserDao userDao = new UserDao();

    public boolean validateUsername(String username) {
        User user = userDao.validateUsername(username);
        if (user == null) {
            return true;
        } else {
            return false;
        }
    }

    public void saveUser(String username, String password, String email, String phone) {
        User user = new User();
        user.setAvatar(User.DEFAULT_AVATAR_NAME);
        user.setEmail(email);
        user.setPassword(DigestUtils.md5Hex(Config.get("user.password.salt")+password));
        user.setPhone(phone);
        user.setState(User.USERSTATE_UNACTIVE);
        user.setUsername(username);

        userDao.saveUser(user);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String uuid = UUID.randomUUID().toString();
                String url = "http://bbs.bayllech.com/user/active?_="+uuid;

                cache.put(uuid,username);
                String html ="<h3>Dear "+username+":</h3></br>请点击<a href='"+url+"'>该链接</a>去激活你的账号. <br> 备有网";
                EmailUtil.sendHtmlEmail(email, "备有网用户激活", html);
            }
        });
        thread.start();

    }

    public User validateEmail(String email) {
        return  userDao.validateEmail(email);
    }
}
