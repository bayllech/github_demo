package com.kaishengit.service.Impl;

import com.kaishengit.dao.UserDao;
import com.kaishengit.mapper.UserMapper;
import com.kaishengit.pojo.User;
import com.kaishengit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(isolation = Isolation.SERIALIZABLE)
public class UserServiceImpl implements UserService {

/*    @Autowired
    private UserDao userDao;*/

    @Autowired
    private UserMapper userMapper;

    /*@Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }*/

    @Override
    public void save(User user) {
//        userDao.save(user);
        userMapper.save(user);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll() ;
    }

    @Override
    public void delById(Integer id) {
        userMapper.delUser(id);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public void editUser(User user) {
        userMapper.editUser(user);
    }

    @Override
    public int getNum() throws RuntimeException {
        System.out.println("get num...");
//        throw new RuntimeException("故意异常");
        return 100;
    }
}
