package com.kaishengit.mapper;

import com.kaishengit.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by bayllech on 2017/1/5.
 */
public interface UserMapper {
    User findUserById(Integer id);
    void saveUser(User user);
    List<User> findAll();
    User findByNameAndPassword(@Param("username") String name, @Param("pwd") String password);
    User findByNameAndPassword(Map<String, Object> param);
    User findByParam(Map<String,Object> param);
}
