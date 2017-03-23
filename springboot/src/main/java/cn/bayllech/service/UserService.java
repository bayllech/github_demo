package cn.bayllech.service;

import cn.bayllech.mapper.UserMapper;
import cn.bayllech.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bayllech on 2017/3/23.
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

   /* public List<User> findAll() {
        return userMapper.findAll();
    }*/

    public List<User> find() {
        return userMapper.find();
    }
}
