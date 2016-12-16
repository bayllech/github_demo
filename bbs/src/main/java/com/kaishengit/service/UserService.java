package com.kaishengit.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.User;
import com.kaishengit.util.Config;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.UUID;

/**
 * Created by bayllech on 2016/12/15.
 */
public class UserService {
    

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


            }
        });

    }

    public User validateEmail(String email) {
        return  userDao.validateEmail(email);
    }
}
