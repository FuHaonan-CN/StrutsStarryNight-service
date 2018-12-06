<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: FHN
  Date: 2018/3/14
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>系统当前时间</title>
</head>
<body>
<%--<s:param name="oneCountdownNews" value="news"/>;--%>
<%
    Date date = new Date();
    request.setAttribute("date", date);
%>
<s:set name="nowdate" value="#request.date"></s:set>
<s:date name="nowdate" format="yyyy-MM-dd hh:mm:ss E"/>
</body>
</html>