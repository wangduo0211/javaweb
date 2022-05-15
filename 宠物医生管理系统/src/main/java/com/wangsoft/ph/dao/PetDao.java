package com.wangsoft.ph.dao;

import com.wangsoft.ph.entity.Pet;
import com.wangsoft.ph.entity.User;
import com.wangsoft.ph.unils.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PetDao {
  public   List<Pet>  getPetsByOwnerId(int id) throws Exception{
    List<Pet> pets = new ArrayList<Pet>();
    // 数据库连接变量
    Connection conn = null;
    // 数据库查询类
    PreparedStatement ps = null;
    // 数据库查询结果集
    ResultSet rs = null;
    try {
      conn = Db.getConnection();
      //准备数据库的查询
      ps = conn.prepareStatement("SELECT * FROM t_pet WHERE ownerId=?");
      // 设置查询参数
      ps.setInt(1, id);
      //执行查询获取结果
      rs = ps.executeQuery();
      // 逐行获取查询结果
      while (rs.next()) {
         Pet pet = new Pet();
pet.setPhoto(rs.getString("photo"));
pet.setName(rs.getString("name"));
pet.setOwnerId(rs.getInt("ownerID"));
pet.setBrithdate(rs.getString("brithdate" ));
pet.setId(rs.getInt("id"));
        //添加结果到users列表实例
        pets.add(pet);
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
    return pets;
  }

    public  void save(Pet pet) throws Exception{
        // 数据库连接变量
        Connection conn = null;
        // 数据库查询类
        PreparedStatement ps = null;
        try {
            conn = Db.getConnection();

//            通过连接准备数据库查询的SQL语句 name=?是留一个参数？随意填写
            ps = conn.prepareStatement("insert into t_pet value  (null,?,?,?,?)");
//            设置查询参数
            ps.setString(1, pet.getName());
            ps.setString(2, pet.getBrithdate());
            ps.setString(3, pet.getPhoto());
            ps.setInt(4, pet.getOwnerId());
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


    public  void delete(int petId) throws Exception {
        // 数据库连接变量
        Connection conn = null;
        // 数据库查询类
        PreparedStatement ps = null;
        try {
            conn = Db.getConnection();

//            通过连接准备数据库查询的SQL语句 name=?是留一个参数？随意填写
            ps = conn.prepareStatement("delete from t_pet where id=?");
//            设置查询参数;
            ps.setInt(1, petId);
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


