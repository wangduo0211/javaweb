package com.wangsoft.ph;

import com.wangsoft.ph.dao.PetDao;
import com.wangsoft.ph.dao.UserDao;
import com.wangsoft.ph.dao.VetDao;
import com.wangsoft.ph.entity.Pet;
import com.wangsoft.ph.entity.User;
import com.wangsoft.ph.entity.Vet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
         System.out.println("Hello Maven");
        //testEntity();
        testDao();
    }

    //测试实体类
 public  static void testEntity(){
//创建User实例
     User user=new User();


     user.setId(1);
     user.setRole("admin");
     user.setName("zhangsan");
     user.setPwd("123456");
     user.setTel("123456789");
     user.setAddress("中国湖北武汉");

     for (int i = 1; i <= 2; i++) {
         Pet pet = new Pet();
         pet.setId(i);
         pet.setOwnerId(1);
         pet.setName("cat" + 1);
         pet.setBrithdate("2022");
         pet.setPhoto("cat.jpg");


         user.getPets().add(pet);
     }
     System.out.println(user.toString());

 }
public static void testDao()  {
UserDao userDao=new UserDao();
    PetDao petDao=new PetDao();
try {
    User user=userDao.getById(5);
    List<Pet> pets=petDao.getPetsByOwnerId(3);


    System.out.println(user);
    for (Pet p:pets){
        System.out.println(p);

    }



    }catch (Exception e){
        e.printStackTrace();
    }

}

    }
