package com.kaishengit;

import com.kaishengit.mapper.UserMapper;
import com.kaishengit.pojo.User;
import com.kaishengit.util.SqlSessionFactoryUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bayllech on 2017/1/4.
 */
public class MyBatisTestCase {
    @Test
    public void readxml() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession sqlSession = sessionFactory.openSession();

            User user = sqlSession.selectOne("com.kaishengit.mapper.UserMapper.findUserById", 1004);
            System.out.println(user);

            sqlSession.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findById() {
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();

        User user = sqlSession.selectOne("com.kaishengit.mapper.UserMapper.findUserById", 1004);
        System.out.println(user);

        sqlSession.close();
    }

    @Test
    public void save() {
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();

        User user = new User();
        user.setUsername("liu");
        user.setPassword("123123");
        Integer id= sqlSession.insert("com.kaishengit.mapper.UserMapper.saveUser",user);
        System.out.println(id);
        sqlSession.commit();
        sqlSession.close();
        user.setId(id);
        System.out.println(user);

    }

    @Test
    public void findAll() {
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<User> userList = userMapper.findAll();
        for (User user :userList) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    @Test
    public void findByNameAndPassword() {
        SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//        User user = userMapper.findByNameAndPassword("rose","123");
        Map<String, Object> map = new HashMap<>();
        map.put("username","rose");
        map.put("password", "123");
        User user = userMapper.findByNameAndPassword(map);
    }



}
