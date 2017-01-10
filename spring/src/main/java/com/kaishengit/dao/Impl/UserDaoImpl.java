package com.kaishengit.dao.Impl;

import com.kaishengit.dao.UserDao;
import com.kaishengit.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(User user) throws RuntimeException {
        String sql = "insert into user (username,password) values (?,?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword());
        /*if (1 == 1) {
            throw new RuntimeException("故意异常。。。");
        }*/
    }

    public void update() {
        System.out.println("spring update...");
    }
}
