package com.wangsoft.ph.dao;


import com.wangsoft.ph.entity.User;
import com.wangsoft.ph.unils.Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public User getByName(String name) throws Exception {
        User user = null;
        // 数据库连接变量
        Connection conn = null;
        // 数据库查询类
        PreparedStatement ps = null;
        // 数据库查询结果集
        ResultSet rs = null;
        try {
            conn = Db.getConnection();
            //准备数据库的查询
            ps = conn.prepareStatement("SELECT * FROM t_user WHERE name=?");
            // 设置查询参数
            ps.setString(1, name);
            //执行查询获取结果
            rs = ps.executeQuery();
            // 逐行获取查询结果
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setRole(rs.getString("role"));
                user.setName(rs.getString("name"));
                user.setPwd(rs.getString("pwd"));
                user.setTel(rs.getString("tel"));
                user.setAddress(rs.getString("address"));
            }
        } catch (Exception exception) {
            System.out.println("数据库查询异常");
            exception.printStackTrace();
        } finally {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }
        return user;
    }
    public List<User> searchCustomer(String name)  throws Exception {
        List<User> users = new ArrayList<User>();
        // 数据库连接变量
        Connection conn = null;
        // 数据库查询类
        PreparedStatement ps = null;
        // 数据库查询结果集
        ResultSet rs = null;
        try {
            conn = Db.getConnection();
            //准备数据库的查询
            ps = conn.prepareStatement("SELECT * FROM t_user WHERE name like ?and role='customer'");
            // 设置查询参数
            ps.setString(1, "%" + name + "%");
            //执行查询获取结果
            rs = ps.executeQuery();
            // 逐行获取查询结果
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setRole(rs.getString("role"));
                user.setName(rs.getString("name"));
                user.setPwd(rs.getString("pwd"));
                user.setTel(rs.getString("tel"));
                user.setAddress(rs.getString("address"));
                //添加结果到users列表实例
                users.add(user);
            }
        } catch (Exception exception) {
            System.out.println("数据库查询异常");
            exception.printStackTrace();
        } finally {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }
        return users;
    }
 public User getById( int id) throws Exception{
    User user=null;

     // 数据库连接变量
     Connection conn = null;
     // 数据库查询类
     PreparedStatement ps = null;
     // 数据库查询结果集
     ResultSet rs = null;
     try {
         conn = Db.getConnection();
         //准备数据库的查询
         ps = conn.prepareStatement("SELECT * FROM t_user WHERE id=? ");
         // 设置查询参数
         ps.setInt(1,id);
         //执行查询获取结果
         rs = ps.executeQuery();
         // 逐行获取查询结果
         if (rs.next()) {
           user = new User();
             user.setId(rs.getInt("id"));
             user.setRole(rs.getString("role"));
             user.setName(rs.getString("name"));
             user.setPwd(rs.getString("pwd"));
             user.setTel(rs.getString("tel"));
             user.setAddress(rs.getString("address"));

         }
     } catch (Exception exception) {
         System.out.println("数据库查询异常");
         exception.printStackTrace();
     } finally {
         if (rs != null)
             rs.close();
         if (ps != null)
             ps.close();
         if (conn != null)
             conn.close();
     }
     return user;
 }

    public  void save(User user) throws  Exception{

        // 数据库连接变量
        Connection conn = null;
        // 数据库查询类
        PreparedStatement ps = null;
        try {
            conn = Db.getConnection();

//            通过连接准备数据库查询的SQL语句 name=?是留一个参数？随意填写
            ps = conn.prepareStatement("insert into t_user value  (null ,?,?,?,?,?)");
//            设置查询参数
            ps.setString(1, user.getRole());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPwd());
            ps.setString(4, user.getTel());
            ps.setString(5, user.getAddress());
//            执行查询获取结果  因为是更新不返回结果
            ps.executeUpdate();

        } catch (Exception exception) {
            System.out.println("数据库查询异常");
            exception.printStackTrace();
        } finally {

            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }


    }
}
