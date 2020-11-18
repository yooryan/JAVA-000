package com.github.yooryan.spring.jdbc.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * 数据库连接工具类
 *
 * @author linyunrui
 */
public class ConnectionUtil {



    private static Connection con;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
            if (con == null){
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");
            }else {
                return con;
            }
        return con;
    }

    public static Statement getStatement()  {
        try {
            Connection connection = getConnection();
            return connection.createStatement();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}
