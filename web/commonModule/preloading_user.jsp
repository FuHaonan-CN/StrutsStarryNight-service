<%--
  Created by IntelliJ IDEA.
  User: FHN
  Date: 2017/12/5
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%--处理字符编码--%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ page import="entity.UserBasicInfo" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/user/";
%>
<%
    /* 乱码处理 */
    request.setCharacterEncoding("utf-8");
    /*获取session*/
    UserBasicInfo user = (UserBasicInfo) session.getAttribute("user");
    if(user==null)
    {
%>
<div style="text-align: center">
    <h1>未登录!!!</h1>
    2秒后跳转到登录页面
    <p>如果没有跳转，请点<a href="/user/userLogin.jsp">这里</a></p>
</div>
<%
        response.setHeader("refresh","2;URL=/user/userLogin.jsp");
        return;
    }
%>