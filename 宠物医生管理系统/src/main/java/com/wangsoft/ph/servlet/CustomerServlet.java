package com.wangsoft.ph.servlet;

import com.wangsoft.ph.dao.PetDao;
import com.wangsoft.ph.dao.UserDao;
import com.wangsoft.ph.entity.Pet;
import com.wangsoft.ph.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerServlet", value = "/CustomerServlet")
public class CustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String m = request.getParameter("m");
        if("showDetail".equals(m)){
            showDetail(request,response);
        }else  if ("toAdd".equals(m)){
            toAdd(request,response);
        }

    }

    private void toAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        request.getRequestDispatcher("/customeradd.jsp").forward(request, response);

    }

    private void showDetail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String cid=request.getParameter("cid");
        try {
//            数据类型转换
            int customerId = Integer.parseInt(cid);
//            查用户
            UserDao userDao = new UserDao();
//            做查询结果
            User user = userDao.getById(customerId);
            PetDao petDao = new PetDao();
            List<Pet> pets = petDao.getPetsByOwnerId(customerId);
            user.setPets(pets);
//                查到了就将结果返回页面 让页面解析呈现结果
            request.setAttribute("user", user);
            request.getRequestDispatcher("/customerdetail.jsp").forward(request, response);

        }catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("msg", "操作失败，请稍后重试");
            request.getRequestDispatcher("/customersearch.jsp").forward(request, response);
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
String m = request.getParameter("m");
    if("search".equals(m)){
        search(request,response);
    }else  if ("add".equals(m)){
        add(request,response);
    }
    }

    private void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            UserDao userDao = new UserDao();
          User user=new User();
            user.setRole("customer");
            user.setName(request.getParameter("name"));
            user.setPwd("123456");
            user.setTel(request.getParameter("tel"));
            user.setAddress(request.getParameter("address"));
            userDao.save(user);
            request.setAttribute("msg", "添加用户成功");
            request.getRequestDispatcher("/customersearch.jsp").forward(request, response);
        }catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("msg", "操作失败，请稍后重试");
            request.getRequestDispatcher("/customersearch.jsp").forward(request, response);
        }
    }

    private void search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("customerName");
        try {

            UserDao userDao = new UserDao();
            List<User> users = userDao.searchCustomer(name);

            if (users==null || users.size()==0) {
                request.setAttribute("msg", "没有找到相关客户信息");
                request.getRequestDispatcher("/customersearch.jsp").forward(request, response);
            }else
            {
                request.setAttribute("users", users);
                request.getRequestDispatcher("/customersearch-result.jsp").forward(request, response);
            }
        }catch (Exception ex) {
          ex.printStackTrace();
               request.setAttribute("msg", "操作失败，请稍后重试");
            request.getRequestDispatcher("/customersearch.jsp").forward(request, response);
        }
    }
}
