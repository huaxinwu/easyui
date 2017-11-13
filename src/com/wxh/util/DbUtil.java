/**
 * wxh Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.wxh.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库连接工具类
 * @author wxh
 * @version $Id: DbUtil.java, v 0.1 2017年11月8日 上午10:54:04 wxh Exp $
 */
public class DbUtil {

    private static final String DRIVE    = "com.mysql.jdbc.Driver";
    private static final String URL      = "jdbc:mysql://127.0.0.1:3306/dbtest?useUnicode=true&amp;characterEncoding=UTF-8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "cqabj208";

    /**
     * 防止被实例化
     */
    private DbUtil() {

    }

    /**
     * 打开一个会话
     * @return Connection
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVE);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("类没有找到异常");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接异常");
        }
        return connection;
    }

    /**
     * 关闭一个会话，释放资源
     * @param connection
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("数据库连接异常");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(getConnection());
    }

}
