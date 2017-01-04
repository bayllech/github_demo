package com.kaishengit.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;


/**
 * Created by bayllech on 2017/1/4.
 */
public class SqlSessionFactoryUtil {
    private static SqlSessionFactory sessionFactory = buildSqlSessionFactory();

    private static SqlSessionFactory buildSqlSessionFactory() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis.xml");
            return new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new RuntimeException("读取mybatis.xml文件异常");
        }
    }

    public static SqlSessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public static SqlSession getSqlSession() {
        return getSessionFactory().openSession();
    }
    public static SqlSession getSqlSession(boolean isAutoCommit) {
        return getSessionFactory().openSession(isAutoCommit);
    }
}
