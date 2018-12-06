<%--
  Created by IntelliJ IDEA.
  User: FHN
  Date: 2017/12/5
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%--处理字符编码--%>
<%@ page language="java" import="entity.Admin" pageEncoding="utf-8"%>
<script language='javascript'>
    function logout() {
        if(!confirm('事都干完了吗，就想着离开！')){ window.event.returnValue = false;}
    }
</script>
<div class="topbar-wrap white">
    <div class="topbar-inner clearfix">
        <div class="topbar-logo-wrap clearfix">
            <h1 class="topbar-logo none"><a href="../admin/admin.jsp" class="navbar-brand">后台管理</a></h1>
            <ul class="navbar-list clearfix">
                <li><a href="admin/admin.jsp" class="on">后台首页</a></li>
                <li><a href="/homepage.action" target="_blank">网站首页</a></li>
            </ul>
        </div>
        <div class="top-info-wrap">
            <ul class="top-info-list clearfix">
                <li>
                    <a class="on1"><img src="../upload/<s:property value="#session.admin.pic"/>" width="30" height="30"/>
                        <s:property value="#session.admin.adminname"/>管理员
                    </a>
                </li>
                <li><a href="/Admin_updateInput_admin?id=<s:property value="#session.admin.id"/>">修改密码</a></li>
                <li><a href="/Admin_logout_admin" onclick="logout()">退出</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="container clearfix">
    <div class="sidebar-wrap">
        <div class="sidebar-title">
            <h1>菜单</h1>
        </div>
        <div class="sidebar-content">
            <ul class="sidebar-list">
                <li>
                    <a style="font-size: 20px">&nbsp;&nbsp;<i class="icon-font">&#xe003;</i> 常用操作</a>
                    <ul class="sub-menu">
                        <li><a href="/Admin_selectAll_admin"><i class="icon-font">&#xe008;</i>admin账号管理</a></li>
                        <li><a href="/News_selectAll_news"><i class="icon-font">&#xe005;</i>宇宙新闻管理</a></li>
                        <li><a href="/User_selectAll_user"><i class="icon-font">&#xe006;</i>用户账号管理</a></li>
                        <li><a href="admin/friendlyLink.jsp"><i class="icon-font">&#xe052;</i>友情链接</a></li>
                        <li><a href="admin/advertisement.jsp"><i class="icon-font">&#xe033;</i>广告管理</a></li>
                    </ul>
                </li>
                <li>
                    <a style="font-size: 20px">&nbsp;&nbsp;<i class="icon-font">&#xe018;</i> 系统管理</a>
                    <ul class="sub-menu">
                        <li><a href="admin/system.jsp"><i class="icon-font">&#xe017;</i>系统设置</a></li>
                        <li><a href="admin/system.jsp"><i class="icon-font">&#xe037;</i>清理缓存</a></li>
                        <li><a href="admin/system.jsp"><i class="icon-font">&#xe046;</i>数据备份</a></li>
                        <li><a href="admin/system.jsp"><i class="icon-font">&#xe045;</i>数据还原</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
