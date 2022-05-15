package com.wangsoft.ph.servlet;

import com.sun.xml.internal.bind.v2.TODO;
import com.wangsoft.ph.dao.SpecialityDao;
import com.wangsoft.ph.dao.VetDao;
import com.wangsoft.ph.entity.Speciality;
import com.wangsoft.ph.entity.Vet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "VetServlet ", value = "/VetServlet")
public class VetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //对比请求参数
        String m = request.getParameter("m");
        if ("toAdd".equals(m)) {
            toAdd(request, response);
        }

    }

    private void toAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            SpecialityDao specialityDao = new SpecialityDao();
            //获取专业数据和设置一起操作
            request.setAttribute("specs", specialityDao.getAll());
            request.getRequestDispatcher("/vetadd.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "添加失败,请稍后重试");
            request.getRequestDispatcher("/vetadd.jsp").forward(request, response);
        }
    }


    private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入VetDao.search()方法");
        String vname = request.getParameter("vetName");
        String sname = request.getParameter("specName");
        System.out.println(vname);
        System.out.println(sname);
        try {
            VetDao vetDao = new VetDao();
            List<Vet> vets = vetDao.search(vname, sname);

            if (vets.size() == 0) {
                //没有查到结果
                request.setAttribute("msg", "没有符合条件的医生");
                request.getRequestDispatcher("/vetsearch.jsp").forward(request, response);
            } else {
                request.setAttribute("vets", vets);
                request.getRequestDispatcher("/vetsearch_result.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("msg", "操作失败，请稍后重试");
            request.getRequestDispatcher("/vetsearch.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //对比请求参数
        String m = request.getParameter("m");
        if ("search".equals(m)) {
            search(request, response);
        } else if ("add".equals(m)) {
            add(request, response);
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //获取用户提交的数据保存到新的vet实例中
            String vname = request.getParameter("name");
            if (vname == null || "".equals(vname)) {
                throw  new Exception("医生姓名不能为空");
            }
            String[] specIds = request.getParameterValues("specId");
            if (specIds == null || specIds.length == 0) {
                throw  new Exception("请至少选择一项专业特长");
            }

            Vet vet = new Vet();
            vet.setName(vname);

            for (String s : specIds) {

                Speciality speciality = new Speciality();
                speciality.setId(Integer.valueOf(s));
                vet.getSpecialities().add(speciality);
            }
            System.out.println(vet);
            VetDao vetDao = new VetDao();
            vetDao.save(vet);

            request.setAttribute("msg","添加医生信息成功");
            request.getRequestDispatcher("/vetsearch.jsp").forward(request, response);
        }catch (Exception ex) {
            //一定要把异常输出到控制台
            ex.printStackTrace();
            request.setAttribute("msg", ex.getMessage());
            request.getRequestDispatcher("/vetsearch.jsp").forward(request, response);
        }

    }
}
