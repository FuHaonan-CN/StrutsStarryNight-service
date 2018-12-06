<%--
  Created by IntelliJ IDEA.
  User: FHN
  Date: 2017/12/5
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%--处理字符编码--%>
<%@ page language="java" import="entity.Admin" pageEncoding="utf-8"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%--s标签--%>
<%--<s:if test="#session.admin==null">
    <script language=javascript>alert('您还没有登录,请先登陆!!!');
window.location = 'adminLogin.jsp'</script>
</s:if>--%>
<%
    /* 乱码处理 */
    request.setCharacterEncoding("utf-8");
    /*获取session*/
    Admin admin = (Admin) session.getAttribute("admin");
    if(admin==null)
    {
%>
<div style="text-align: center">
    <h1>未登录!!!</h1>
    2秒后跳转到登录页面
    <p>如果没有跳转，请点<a href="/admin/adminLogin.jsp">这里</a></p>
</div>
<%
        response.setHeader("refresh","2;URL=/admin/adminLogin.jsp");
        return;
    }
%>