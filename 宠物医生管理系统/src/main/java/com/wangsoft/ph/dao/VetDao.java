package com.wangsoft.ph.dao;

import com.wangsoft.ph.entity.Pet;
import com.wangsoft.ph.entity.Speciality;
import com.wangsoft.ph.entity.Vet;
import com.wangsoft.ph.unils.Db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VetDao {
    public List<Vet> search(String vname, String sname) throws Exception {
        List<Vet> vetList = new ArrayList<Vet>();
        //数据库链接变量
        Connection conn = null;
        //数据库查询类
        PreparedStatement ps = null;
        //数据库查询结果集
        ResultSet rs = null;
        try {
            // 获取数据库连接
            conn = Db.getConnection();

            //准备数据库查询
            String sql = "select distinct tv.* " +
                    "from " +
                    " t_vet_speciality " +
                    " inner join " +
                    " t_speciality ts on (t_vet_speciality.specId = ts.id) " +
                    "inner join " +
                    " t_vet tv on (t_vet_speciality.vetId =tv.id) " +
                    "where tv.name like ? and ts.name like ?";
            System.out.println(sql);
            ps = conn.prepareStatement(sql);
            //设置查询参数
            ps.setString(1, "%" + vname + "%");
            ps.setString(2, "%" + sname + "%");
            //执行查询获取结果
            rs = ps.executeQuery();


            while (rs.next()) {
                Vet vet = new Vet();
                vet.setId(rs.getInt("id"));
                vet.setName(rs.getString("name"));
                // 将结果存入list
                vetList.add(vet);
            }

            for (Vet v : vetList) {
                //准备第二次查询的sql语句
                sql = "select Distinct ts.*" +
                        "FROM t_vet_speciality Inner join t_speciality ts on (t_vet_speciality.specId =ts.id)" +
                        "INNER  join t_vet tv on (t_vet_speciality.vetId = tv.id)" +
                        "where tv.id = ?";
                System.out.println(sql);
                ps = conn.prepareStatement(sql);
                //设置查询参数
                ps.setInt(1, v.getId());
                rs = ps.executeQuery();

                while (rs.next()) {
                    Speciality speciality = new Speciality();
                    speciality.setId(rs.getInt("id"));
                    speciality.setName(rs.getString("name"));
                    //取出医生的专业列表在添加专业对象
                    v.getSpecialities().add(speciality);
                }
            }

            //控制台显示 输出结果
            for (Vet v : vetList) {
                System.out.println(v);
            }
        } catch (Exception ex) {
            System.out.println("数据库查询异常！");
            ex.printStackTrace();

        } finally {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();
        }

        return vetList;
    }


    public void save(Vet vet) throws Exception {
        //数据库链接变量
        Connection conn = null;
        //数据库查询类
        PreparedStatement ps = null;
        //数据库查询结果集
        ResultSet rs = null;
        try {
            // 获取数据库连接
            conn = Db.getConnection();

//一组操作，两个或者多个更新，需要开启事务处理
            conn.setAutoCommit(false);

            //准备数据库查询,需要返回生成的id
            ps = conn.prepareStatement("insert into t_vet values(null,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            //设置查询参数
            ps.setString(1, vet.getName());
//执行查询
            ps.executeUpdate();
            //取回生成的主键
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                vet.setId(rs.getInt(1));
            }

            //第二阶段，添加医生的专业信息
            //构造sql语句，并执行
            String sql = "insert into t_vet_speciality values";
            boolean isFirst = true;
            //根据专业数量来循环
            for (Speciality s : vet.getSpecialities()) {
                if (isFirst) {
                    sql +="(" + vet.getId() + "," + s.getId() + ")";
                    isFirst = false;
                }else {
                    sql +=",(" + vet.getId() + "," + s.getId() + ")";
                }
            }
            //输出sql语句
            System.out.println(sql);
         //执行查询
            ps.executeUpdate(sql);

            //全部运行完成后，提交事务
            conn.commit();


        } catch (Exception ex) {
            System.out.println("数据库查询异常！");
            ex.printStackTrace();

        } finally {
            if (rs != null)
                rs.close();
            if (ps != null)
                ps.close();
            if (conn != null)
                conn.close();

        }
    }


    public static List<Vet> getAll() throws Exception{
        List<Vet> vets  = new ArrayList<Vet>();
        // 数据库连接变量
        Connection conn = null;
        // 数据库查询类
        PreparedStatement ps = null;
        // 数据库查询结果集
        ResultSet rs = null;
        try {
            conn = Db.getConnection();
            //准备数据库的查询
            ps = conn.prepareStatement("SELECT * FROM t_vet");
            //执行查询获取结果
            rs = ps.executeQuery();
            // 逐行获取查询结果
            while (rs.next()) {
                Vet vet=new Vet();
                vet.setId(rs.getInt("id"));
                vet.setName(rs.getString("name"));
                //添加结果到users列表实例
                vets.add(vet);
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
        return vets;
    }
    }


