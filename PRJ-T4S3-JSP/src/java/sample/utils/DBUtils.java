/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ANPHUOC
 */
public class DBUtils {
    private static final String DB_NAME=${db_name};
    private static final String USER_NAME=${user_name};
    private static final String PASSWORD=${password};
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn= null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url= ${db_url}+ DB_NAME;
        conn= DriverManager.getConnection(url, USER_NAME, PASSWORD);
        return conn;
    }
}
