package com.kaishengit.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Config;

import com.kaishengit.util.EmailUtil;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by bayllech on 2016/12/15.
 */
public class UserService {
    /**
     * 发送激活邮件缓存设置
     */
    private static Cache<String ,String> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.HOURS)
            .build();

    /**
     * 找回密码邮件缓存设置
     */
    private static Cache<String ,String> passwordCache = CacheBuilder.newBuilder()
            .expireAfterWrite(30, TimeUnit.SECONDS)
            .build();


    UserDao userDao = new UserDao();

    public boolean findUserByName(String username) {
        User user = userDao.findUserByName(username);
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
                String html ="<h3>Dear "+username+":</h3></br>  请点击<a href='"+url+"'><button>此处</button></a>去激活你的账号. <br> 备有网";
                EmailUtil.sendHtmlEmail(email, "备有网用户激活", html);
            }
        });
        thread.start();

    }

    public User findUserByEmail(String email) {
        return  userDao.findUserByEmail(email);
    }

    /**
     * 根据token激活用户
     * @param token 用户token
     */
    public void activeUser(String token) {
        String cacheUsername = cache.getIfPresent(token);
        if (cacheUsername == null) {
            throw new ServiceException("验证信息已过期或无效");
        } else  {
            UserDao userDao = new UserDao();
            User user = userDao.findUserByName(cacheUsername);
            if (user == null) {
                throw new ServiceException("用户信息不存在，请查证");
            } else {
                user.setState(User.USERSTATE_ACTIVE);
                userDao.update(user);

                //删除缓存中的token
                cache.invalidate(token);
            }
        }

    }
}
