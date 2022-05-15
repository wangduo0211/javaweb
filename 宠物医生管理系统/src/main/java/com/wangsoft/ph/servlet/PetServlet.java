package com.wangsoft.ph.servlet;


import com.wangsoft.ph.dao.PetDao;
import com.wangsoft.ph.dao.UserDao;
import com.wangsoft.ph.entity.Pet;
import com.wangsoft.ph.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@MultipartConfig
@WebServlet(name = "PetServlet",value="/PetServlet")
public class PetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String m = request.getParameter("m");
        if ("toAdd".equals(m)){
            toAdd(request,response);
        }else if ("delete".equals(m)){
            delete(request,response);
        }
    }
    private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid=request.getParameter("cid");
        try{
            int petId = Integer.parseInt(request.getParameter("petId"));
            PetDao petDao = new PetDao();
            request.setAttribute("msg","删除宠物成功");
            request.getRequestDispatcher("/CustomerServlet?m=showDeteil&cid" +cid).forward(request,response);
        }catch (Exception ex){
            ex.printStackTrace();
            request.setAttribute("msg","操作失败，请稍后重试");
            request.getRequestDispatcher("/CustomerServlet?m=showDeteil&cid" +cid).forward(request,response);
        }
    }
    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("petadd.jsp").forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String m = request.getParameter("m");
        if ("add".equals(m)){
            add(request,response);
        }
    }
    private void add (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Part part = request.getPart("photo");
            String filename = getFileName(part);
            System.out.println(filename);
            String photo;
            if (filename!=null){
                long currentTimeMills = System.currentTimeMillis();
                photo = "photo/" + currentTimeMills + filename.substring(filename.lastIndexOf("."));
                System.out.println(photo);
                filename =getServletContext().getRealPath("/")+"/"+photo;
                part.write(filename);
            }else {
                photo = "";
            }
            PetDao petDao = new PetDao();
            Pet pet = new Pet();
            pet.setName(request.getParameter("name"));
            pet.setOwnerId(Integer.parseInt(request.getParameter("cid")));
            //规定用户的输入格式
            SimpleDateFormat ft1 = new SimpleDateFormat("yyyy-MM-dd");
            Date bd = ft1.parse(request.getParameter("birthdate"));
            //规定数据库的格式
            SimpleDateFormat ft2 =new SimpleDateFormat("yyyyMMdd");
            pet.setBrithdate(ft2.format(bd));
            pet.setPhoto(photo);
            petDao.save(pet);
            request.setAttribute("msg","添加宠物成功");
            response.sendRedirect("CustomerServlet?m=showDetail&cid=" + pet.getOwnerId());
        }catch (Exception ex){
            ex.printStackTrace();
            request.setAttribute("msg","操作失败，请稍后重试");
            request.getRequestDispatcher("/petadd.jsp").forward(request,response);
        }
    }
    private String getFileName(Part part){
        String fileName = null;
        String contentDesc = part.getHeader("Content-Disposition");
        Pattern pattern = Pattern.compile("filename=\".+\"");
        Matcher matcher =pattern.matcher(contentDesc);
        if(matcher.find()){
            fileName = matcher.group();
            fileName = fileName.substring(10,fileName.length()-1);
            System.out.println(fileName);
        }
        return fileName;
    }
}
