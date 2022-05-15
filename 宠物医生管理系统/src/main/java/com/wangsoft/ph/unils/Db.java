package com.wangsoft.ph.unils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    public static Connection getConnection() {
        Connection conn = null ;

        // 指定数据库驱动类
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //连接数据库
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_ph?useUnicode=true&characterEncoding=UTF-8",
                        "root", "123456");
                System.out.println("VetDao连接数据库成功");
            } catch (SQLException e) {
                System.out.println("数据库字符串异常");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动异常");
        }


        return conn;
    }
}
