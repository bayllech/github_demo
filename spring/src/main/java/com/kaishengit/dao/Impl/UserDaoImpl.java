package com.kaishengit.dao.Impl;

import com.kaishengit.dao.UserDao;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    public void save() {
        System.out.println("spring save...");
    }

    public void update() {
        System.out.println("spring update...");
    }
}
