package com.wangsoft.ph.dao;

import com.wangsoft.ph.entity.Speciality;
import com.wangsoft.ph.unils.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SpecialityDao {
    public List<Speciality> getAll()throws Exception{
        ArrayList<Speciality> specialities = new ArrayList<>();
        
        Connection conn = null;
        // 数据库查询类
        PreparedStatement ps = null;
        // 数据库查询结果集
        ResultSet rs = null;
        try {
            conn = Db.getConnection();
            //准备数据库的查询
            ps = conn.prepareStatement("select * from t_speciality");
            //执行查询获取结果
            rs =ps.executeQuery();
            //逐行获取查询结果
            while (rs.next()){
                Speciality s=new Speciality();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                specialities.add(s);
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

        return specialities ;
    }
}