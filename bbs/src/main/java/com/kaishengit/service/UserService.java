package com.kaishengit.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import com.kaishengit.dao.LoginLogDao;
import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.LoginLog;
import com.kaishengit.entity.Topic;
import com.kaishengit.entity.User;
import com.kaishengit.exception.ServiceException;
import com.kaishengit.util.Config;

import com.kaishengit.util.EmailUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by bayllech on 2016/12/15.
 */
public class UserService {

    UserDao userDao = new UserDao();
    private Logger logger = LoggerFactory.getLogger(UserService.class);

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
            .expireAfterWrite(30, TimeUnit.MINUTES)
            .build();

    /**
     * 限制操作频率缓存设置
     */
    private static Cache<String ,String> activeCache = CacheBuilder.newBuilder()
            .expireAfterWrite(60, TimeUnit.SECONDS)
            .build();

    public boolean findUserByName(String username) {
        //保留账号
        String names = Config.get("no.singup.names");
        List<String> nameList = Arrays.asList(names.split(","));
        if (nameList.contains(username)) {
            return false;
        }
        return userDao.findUserByName(username) == null;
       /* User user = userDao.findUserByName(username);
        if (user == null) {
            return true;
        } else {
            return false;
        }*/
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

        sendEmail(username,email);

    }

    public void sendEmail(String username, String email) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String uuid = UUID.randomUUID().toString();
                String url = "http://bbs.bayllech.cn/user/active?_="+uuid;

                cache.put(uuid,username);
                String html ="<h3>Dear "+username+":</h3>请点击<a href='"+url+"'><button>此处</button></a>去激活你的账号. <br> 备有网";
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

    /**
     * 用户忘记密码找回
     * @param sessionId 用户客户端的，用于限制操作频率
     * @param type 邮箱或手机找回类型
     * @param value 邮箱或手机号
     */
    public void forgetPassword(String sessionId, String type, String value) {
        if (activeCache.getIfPresent(sessionId) == null) {
            if ("email".equals(type)) {
                User user = userDao.findUserByEmail(value);
                if (user != null) {
//                    System.out.println("查到了："+value);
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String uuid = UUID.randomUUID().toString();
                            String url = "http://bbs.bayllech.cn/forgetPassword/newPassword?token="+uuid;
                            passwordCache.put(uuid,user.getUsername());

                            String html = "<h3>"+user.getUsername()+":"+"</h3>请点击<a href='"+url+"'><button>此处</button></a>进行找回密码操作，30分钟内有效";
                            EmailUtil.sendHtmlEmail(value,"密码找回邮件，勿回复",html);
                        }
                    });
                    thread.start();
                } else {
                    throw new ServiceException("该邮箱账号未注册");
                }
            } else {
                //TODO根据手机号验证
            }
            activeCache.put(sessionId,"xxxooo");
        } else {
            throw new ServiceException("操作频率过快，请稍后再试");
        }
    }

    /**
     * 根据token查找用户，用于找回密码
     * @param token
     * @return
     */
    public User foundPasswordByToken(String token) {
        String cacheUser = passwordCache.getIfPresent(token);
//        System.out.println("cacheUser:"+cacheUser);
        if (cacheUser == null) {
            throw new ServiceException("验证信息已过期或无效");
        } else {
            User user = userDao.findUserByName(cacheUser);
            if (user == null) {
                throw new ServiceException("账户不存在");
            } else {
                return user;
            }
        }
    }

    /**
     * 根据token设置密码，用于找回密码
     * @param token
     * @param name
     * @param password
     */
    public void resetpassword(String token, String name, String password) {
        if (passwordCache.getIfPresent(token) == null) {
            throw new ServiceException("验证信息已过期或无效");
        } else {
            System.out.println(name);
            User user = userDao.findUserByName(name);
            if (user == null) {
                throw new ServiceException("账户不存在");
            } else {
                user.setPassword(DigestUtils.md5Hex(Config.get("user.password.salt") + password));
                userDao.update(user);
                logger.info("{}重置了密码",user.getUsername());
            }
        }
    }

    /**
     * 用户登录
     * @param name
     * @param password
     * @param ip
     * @return
     */
    public User login(String name, String password, String ip) {
        //判断账号密码
        User user = userDao.findUserByName(name);
        if (user == null || !user.getPassword().equals(DigestUtils.md5Hex(Config.get("user.password.salt") + password))) {
            throw new ServiceException("账号或密码错误");
        } else {
            LoginLogService loginLogService = new LoginLogService();
            loginLogService.saveIp(user.getId(),ip);
            logger.info("{}在ip:{}处登录",user.getUsername(),ip);
            return user;
        }
    }

    /**
     * 修改当前用户信息
     * @param user
     */
    public void updateEmail(User user,String email) {
        user.setEmail(email);
        user.setState(User.USERSTATE_UNACTIVE);
        userDao.update(user);
    }

    /**
     * 修改密码
     * @param oldpassword
     * @param newpassword
     * @param user
     */
    public void updatePassword(String oldpassword,String newpassword, User user) {
        if (DigestUtils.md5Hex(Config.get("user.password.salt") + oldpassword).equals(user.getPassword())) {
            newpassword = DigestUtils.md5Hex(Config.get("user.password.salt") + newpassword);
            user.setPassword(newpassword);
            userDao.update(user);
            logger.info("{}修改了密码",user.getUsername());
        } else {
            throw new ServiceException("原始密码错误");
        }

    }

    /**
     * 修改用户头像
     * @param user
     * @param fileKey
     */
    public void updateAvatar(User user, String fileKey) {
        user.setAvatar(fileKey);
        userDao.update(user);
    }

}
