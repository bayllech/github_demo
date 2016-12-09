package com.kaishengit.util;

import com.kaishengit.exception.DataAccessException;
import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

public class ConnectionManager {

    private static  String URL;
    private static  String DRIVER;
    private static  String USERNAME;
    private static  String PASSWORD;
    private static BasicDataSource dataSource = new BasicDataSource();

    //加载config文件
    static {
        Properties properties = new Properties();
        try {
            properties.load(ConnectionManager.class.getClassLoader().getResourceAsStream("config.properties"));
            DRIVER = properties.getProperty("jdbc.driver");
            URL = properties.getProperty("jdbc.url");
            USERNAME = properties.getProperty("jdbc.username");
            PASSWORD = properties.getProperty("jdbc.password");

        } catch (IOException e) {
            throw new DataAccessException("读取config文件异常", e);
        }

        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        dataSource.setInitialSize(5);
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(20);
        dataSource.setMaxWaitMillis(5000);

    }

    /**
     * 获取数据库连接池
     * @return
     */
    public static DataSource getDataSource() {
        return dataSource;
    }

}
