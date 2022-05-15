package com.wangsoft.ph.servlet;
import com.wangsoft.ph.dao.UserDao;
import com.wangsoft.ph.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet ", value = "/LoginServlet")
public class LoginServlet  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        //显示登录验证信息
        String msg;
        String url = "/index.jsp" ;
        System.out.println("LoginServlet启动");

        //指定解析参数使用的编码
        req.setCharacterEncoding("utf-8");
//获取传递的参数
        String name = req.getParameter("name");
        String pwd = req.getParameter("pwd");
        String checkCode = req.getParameter("checkcode");
        System.out.println(name);
        System.out.println(pwd);
        System.out.println(checkCode);

        //获取正确的验证码
        String realcode = req.getSession(true).getAttribute("realcode").toString();
        System.out.println(realcode);

        // 判断用户的验证码是否正确
        if (realcode.equalsIgnoreCase(checkCode)) {
            System.out.println("验证码正确");
            msg = "验证码正确";
            // 查询数据库比对用户
            UserDao userDao = new UserDao();
            try {
                User user = userDao.getByName(req.getParameter("name"));
                // 1.用户名不存在
                if (user == null) {
                    url = "/index.jsp";
                    msg = "用户名不存在";
                } else if (!user.getPwd().equals(pwd)) {
                    msg = "用户密码错误";
                } else {
                    req.getSession(true).setAttribute("user", user);
                    if(user.getRole().equals("admin")){
                        url = "/vetsearch.jsp";
                    }
                    msg = "登录成功";
                    //保存登录用户的信息
                }
            } catch (Exception e) {
                url = "/index.jsp";
                msg = e.toString();
            }
        } else {
            url = "/index.jsp";
            msg = "验证码错误";
        }
        req.setAttribute("msg", msg);
        // 请求转发，不是重定向
        req.getRequestDispatcher(url).forward(req, resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }
}
