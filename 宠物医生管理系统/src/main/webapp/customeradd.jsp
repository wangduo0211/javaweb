<%--
  Created by IntelliJ IDEA.
  User: wangduo
  Date: 2022/4/15
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
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
    <title>添加用户</title>
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
        <form action="CustomerServlet?m=add" method="post">
            <table>
                <tr>
                    <td>客户姓名</td>
                    <td><input type="text" name="name" /></td>
                </tr>
                <tr>
                    <td>联系电话</td>
                    <td><input type="text" name="tel"/></td>
                </tr>
                <tr>
                    <td>家庭地址</td>
                    <td><input type="text" name="address"/></td>
                </tr>


                <tr class="cols2">
                    <td colspan="2"><input type="submit" value="添加" /><input
                            type="reset" value="重置 " /></td>
                </tr>
                <tr class="cols2">
                    <td colspan="2" class="info"><%=request.getAttribute("msg")==null?"":request.getAttribute("msg") %></td>
                </tr>
            </table>
        </form>
    </div>
    <div id="footer"></div>
</div>
</body>
</html>