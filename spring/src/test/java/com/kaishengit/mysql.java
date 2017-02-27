package com.kaishengit;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class mysql {

    @Test
    public void testNoParam() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///kaishengit_db", "root", "root");
        String sql = "";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql);

        
    }
}
