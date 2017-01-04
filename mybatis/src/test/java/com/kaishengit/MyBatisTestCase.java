package com.kaishengit;

import com.kaishengit.pojo.User;
import com.kaishengit.util.SqlSessionFactoryUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

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
        user.setUsername("lusi");
        user.setPassword("123123");
        sqlSession.insert("com.kaishengit.mapper.UserMapper.saveUser",user);

        sqlSession.commit();
        sqlSession.close();
    }



}
