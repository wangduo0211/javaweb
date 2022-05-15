<%--
  Created by IntelliJ IDEA.
  User: wangduo
  Date: 2022/4/13
  Time: 22:57
  To change this template use File | Settings | File Templates.
--%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.wangsoft.ph.entity.Pet"%>
<%@page import="com.wangsoft.ph.entity.User"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <base href="<%=basePath%>">
    <link rel="stylesheet" href="style.css">
    <title>客户详细信息</title>
</head>
<body>
<div id="container">
    <div id="header">
        <a id="quit" href="QuitServlet">退出</a>
        <h1>社区宠物诊所</h1>
        <ul id="menu">
            <li><a href="vetsearch.jsp">医生管理</a></li>
            <li><a href="customersearch.jsp">客户管理</a></li>
        </ul>
    </div>
    <div id="content">

        <%
            User user = (User) request.getAttribute("user");
        %>

        <table>
            <tr>
                <td>客户姓名</td>
                <td><input type="text" name="name" disabled="disabled"
                           value="<%=user.getName()%>" /></td>
            </tr>
            <tr>
                <td>联系电话</td>
                <td><input type="text" name="tel" disabled="disabled"
                           value="<%=user.getTel()%>" /></td>
            </tr>
            <tr>
                <td>家庭地址</td>
                <td><input type="text" name="address" disabled="disabled"
                           value="<%=user.getAddress()%>" /></td>
            </tr>
            <tr class="cols2">
                <td colspan="2" class="info"><a
                        href="PetServlet?m=toAdd&cid=<%=user.getId()%>&cname=<%=URLEncoder.encode(user.getName(),"UTF-8")%>">添加新宠物</a></td>
            </tr>
            <tr class="cols2">
                <td colspan="2" class="info">
                    <%=request.getAttribute("msg") == null ? "" : request.getAttribute("msg")%>
                </td>
            </tr>
        </table>
        <hr>
        <table class="wide">
            <thead>
            <tr>
                <td colspan="2">宠物信息</td>
                <td>操作</td>
            </tr>
            </thead>

            <%
                for (Pet p:user.getPets()){
                    //解析时间日期格式

                    SimpleDateFormat ft1 = new SimpleDateFormat("yyyyMMdd");
                    Date birthDate = ft1.parse(p.getBrithdate());
                    SimpleDateFormat ft2 = new SimpleDateFormat("yyyy年MM月dd日");
            %>
            <tr>
                <td>
                    <img src="<%=p.getPhoto()%>" alt="Pet photo" width="64" height="64">
                </td>
                <td>
                    姓名:<%=p.getName()%>,生日:<%=p.getBrithdate()%>
                </td>
                <td>
                    <a href="#">历史病例</a>|
                    <a href="VetServlet?m=toAdd&petId=<%=p.getId()  %>&cid=<%p.getOwnerId()%> &petName=<% URLEncoder.encode(p.getName(),"utf8")%>">添加病例</a>
                    |<a class="dBtn" href="PetServlet?m=delete&cid=<%=user.getId()%>&petId=<%=p.getId()%>">删除病例</a>
                </td>
            </tr>

            <%
                }
            %>
        </table>
    </div>
    <div id="footer"></div>
    <script>
        let deleteBtn = document.getElementsByClassName("dBtn");
        console.log(deleteBtn);
        for (let i=0;i<deleteBtn.length;i++){
            deleteBtn[i].onclick = function (){
                if (window.confirm("确定删除该宠物信息吗?")){
                    return true;
                }else {
                    return  false;
                }
            }
        }
    </script>
</div>
</body>
</html>