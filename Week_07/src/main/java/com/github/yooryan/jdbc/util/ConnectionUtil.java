package com.github.yooryan.jdbc.util;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import javax.naming.Name;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * 数据库连接工具类
 *
 * @author linyunrui
 */
public class ConnectionUtil {



    private static Connection con;

    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/miao-mall";
    private static final String DEFAULT_USER = "root";
    private static final String DEFAULT_PASSWORD = "mysql";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(String url,String user,String password) throws Exception {
            if (con == null){
                con = DriverManager.getConnection(url, user, password);
            }else {
                return con;
            }
        return con;
    }

    public static Connection getConnection() throws Exception {
      return getConnection(DEFAULT_URL,DEFAULT_USER,DEFAULT_PASSWORD);
    }

    public static Statement getStatement(String url,String user,String password)  {
        try{
            Connection connection = getConnection(url,user, password);
            return connection.createStatement();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static Statement getStatement()  {
        try {
            return getConnection(DEFAULT_URL,DEFAULT_USER,DEFAULT_PASSWORD).createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
