package com.wangsoft.ph.servlet;


import com.wangsoft.ph.dao.VetDao;
import com.wangsoft.ph.entity.Vet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "/VisitServlet", value = "/VisitServlet")
public class VisitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String m=req.getParameter("m");
        if ("toAdd".equals(m)){
            showHistory(req,resp);

        }else if ("toAdd".equals(m)){

            toAdd(req,resp);
        }
    }

    private void toAdd(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            VetDao vetDao=new VetDao();
            List<Vet> vets=VetDao.getAll();
            req.setAttribute("vets",vets);
            req.getRequestDispatcher("/visitadd.jsp").forward(req,resp);
        } catch (Exception e) {
            req.setAttribute("msg",e.getMessage());
            showHistory(req,resp);
        }
    }

    private void showHistory(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }
}
