﻿<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="entity.UserBasicInfo" %>
<%@ page language="java" pageEncoding="utf-8" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<!DOCTYPE html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/homepage/";
%>
<%
    /* 乱码处理 */
    request.setCharacterEncoding("utf-8");
%>
<%
    UserBasicInfo user = (UserBasicInfo) session.getAttribute("user");
%>
<html lang="en">
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Starnight - A Sexy As Hell Free HTML5/CSS3 Template</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    <%--<link href="css/font-awesome.min.css" rel="stylesheet">--%>
    <%--首页图标不显示--%>
    <link rel="stylesheet" href="css/flexslider.css" type="text/css">
    <link href="css/styles.css?v=1.6" rel="stylesheet">
    <link href="css/queries.css?v=1.6" rel="stylesheet">
    <link href="css/jquery.fancybox.css" rel="stylesheet">
    <link href="css/mystyle.css">
    <!-- Fonts -->
    <%--<link href='http://fonts.useso.com/css?family=Lato:100,300,400,700,900,100italic,300italic,400italic,700italic,900italic' rel='stylesheet' type='text/css'>--%>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script language='javascript'>
        function logout() {
            if (!confirm('您真的忍心注销登录吗？')) {
                window.event.returnValue = false;
            }
        }
    </script>

    <style>
        .mynav {
            background-color: #0000003f;
        }
    </style>

    <%--导入Dojo的CSS库和JavaScript库--%>
    <sx:head/>
</head>
<body>
<%--导航条--%>
<nav class="navbar navbar-fixed-top navbar-inverse">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Brand</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
                <li><a href="#">Link</a></li>

            </ul>
            <form class="navbar-form navbar-left">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default">搜索</button>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <br>
                    <s:url id="time" value="/time.action"/>
                    <sx:div id="div" cssStyle="border:1px solid #ffffff;width:200px;color:#ffffff;"
                            updateFreq="1000"
                            href="%{time}">
                        当前时间：
                    </sx:div>
                </li>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <li>
                    <%
                        if (user == null) {
                    %>
                    <a href="../user/userLogin.jsp">
                        <span class="glyphicon glyphicon-user" aria-hidden="true"></span> 登录
                    </a>
                    <%
                    } else {
                    %>
                    <p class="navbar-text on2">
                        <img src="../upload/<%=user.getPic()%>" width="30" height="30"/> 欢迎您-</p>
                    <%
                        }
                    %>
                </li>
                <%
                    if (user != null) {
                %>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                       aria-expanded="false">
                        <%=user.getUsername() %>
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu" style="text-align: center">
                        <li><a href="/User_updateInput_user?id=<s:property value="#session.user.id"/>"><span
                                class="glyphicon glyphicon-text-height" aria-hidden="true"></span> 信息修改</a></li>
                        <li><a href="/Post_insertInput_post"><span class="glyphicon glyphicon-edit"
                                                                   aria-hidden="true"></span> 发布帖子</a></li>
                        <li><a href="/Post_selectAll_post?rightname=<s:property value="#session.user.username"/>"><span
                                class="glyphicon glyphicon-check" aria-hidden="true"></span> 我的帖子</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="/user/my.jsp" target="_blank"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span> 我的主页</a>
                        </li>
                        <li>
                            <a href="/User_logout_user" class="btn btn-warning" onclick="logout()">
                                <span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span> 注销登录
                            </a>
                        </li>
                    </ul>
                </li>
                <% } %>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
<%--目录--%>
<div class="col-md-2 col-md-offset-10" role="complementary">
    <nav class="bs-docs-sidebar hidden-print hidden-xs hidden-sm affix mynav">
        <ul class="nav bs-docs-sidenav">
            <li>
                <a href="#"><span class="glyphicon glyphicon-list" aria-hidden="true"></span> 目录</a>
                <ul class="nav">
                    <li><a href="/homepage.action#top">新闻倒计时</a></li>
                    <li><a href="/homepage.action#media">用户反馈</a></li>
                    <li><a href="/homepage.action#features">新闻</a></li>
                    <li><a href="/homepage.action#design">百科知识</a></li>
                    <li><a href="/homepage.action#getstarted">了解手机APP</a></li>
                    <li><a href="/homepage.action#footer">关于我们</a></li>
                    <li><a href="/homepage.action#top"><span class="glyphicon glyphicon-arrow-up" aria-hidden="true"></span> 返回顶部</a>
                    </li>
                </ul>
            </li>
        </ul>
    </nav>
</div>
<!-- 倒计时事件 -->
<section class="hero" id="hero">
    <p class="container">
        <div class="row">
            <div class="col-md-8 col-md-offset-2 text-center">
                <h1 class="animated bounceInDown">Into The Starry Night</h1>
                <br>
    <p class="animated fadeInUpDelay" style="font-size:20px">安静笼罩著夏夜巨大的星空，覆盖著整个地球，温柔地无声无息。</p>
    <p class="animated fadeInUpDelay" style="font-size:20px">我对着广阔无垠的浩瀚星海，对着满天的银光，静静地站着，心里就有了一片完整的 </p>
    <p class="animated fadeInUpDelay" style="font-size:35px">星空</p>
    </div>
    </div>
    <%--倒计时--%>
    <div class="col-md-8 col-md-offset-2 text-center">
        距离<input type="text" id="date2" value="<s:date format="yyyy-MM-dd HH:mm:ss" name="countdownNews.newstime"/>">
    </div>
    <div class="col-md-8 col-md-offset-2 text-center">
        <p style="font-size:45px" id="timer" onload="leftTimer()"></p>
    </div>
    <div class="row">
        <div class="col-md-2 col-md-offset-5 text-center">
            <a
                    href="&amp;byline=0&amp;portrait=0&amp;color=1883ad&amp;autoplay=1"
                    class="hero-play-btn various fancybox.iframe">Play Button</a>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6 col-md-offset-3 text-center">
            <a href="" class="get-started-btn">短信提醒</a>
        </div>
    </div>
    </div>
</section>
<%--用户体验--%>
<section class="testimonials" id="media">
    <div class="container">
        <div class="row">
            <div class="col-md-12 text-center">
                <div id="firstSlider">
                    <ul class="slides">
                        <li>
                            <div class="avatar">
                                <img src="img/av-blaz.png" alt="Blaz Robar">
                            </div>
                            <%--<h1>I couldnt possibly use my own eyes to look at the stars, thansk Starnught App.</h1>--%>
                            <h1>用户感受：</h1>
                        </li>
                        <li>
                            <div class="avatar">
                                <img src="img/av-pete.png" alt="Pete Finlan">
                            </div>
                            <%--<h1>Staring at the stars has given me feels like never before.</h1>--%>
                            <h1>盯着星星给了我前所未有的感觉。</h1>
                        </li>
                        <li>
                            <div class="avatar">
                                <img src="img/av-doge.png" alt="Doge Finbar">
                            </div>
                            <h1>我不再用自己的眼睛去看星星，感谢IntoTheStarryNight APP。</h1>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- 图片加新闻 -->
<section class="features" id="features">
    <div class="container">
        <div class="row">
            <div class="col-md-6 features-leftcol wp2">
                <s:iterator value="sixNews" begin="0" end="1" var="news">
                    <a href="/News_queryById_news?id=<s:property value="#news.newsid"/>" target="_blank">
                        <img src="../upload/<s:property value="#news.pic"/>" width="250" height="100" border="0" alt="<s:property value="#news.title"/>［阅读］"></a>
                    <h1 align="center"><a href="/News_queryById_news?id=<s:property value="#news.newsid"/>"><s:property value="#news.title"/></a></h1>
                    <p class="margin-bottom">一句话概括新闻
                        <a href="/News_queryById_news?id=<s:property value="#news.newsid"/>">［阅读］</a>
                    </p>
                </s:iterator>
            </div>
            <div class="col-md-6 features-rightcol wp3">
                <s:iterator value="#request.sixNews" begin="2" end="3" var="news">
                    <a href="/News_queryById_news?id=<s:property value="#news.newsid"/>" target="_blank">
                        <img src="../upload/<s:property value="#news.pic"/>" width="250" height="100" border="0" alt="<s:property value="#news.title"/>［阅读］"></a>
                    <h1 align="center"><a href="/News_queryById_news?id=<s:property value="#news.newsid"/>"><s:property value="#news.title"/></a></h1>
                    <p class="margin-bottom">一句话概括新闻
                        <a href="#">［阅读］</a>
                    </p>
                </s:iterator>
            </div>
            <div class="row">
                <div class="col-md-12 text-center">
                    <a href="#" class="try-btn">了解更多</a>
                </div>
            </div>
        </div>
    </div>
</section>
<!--星球介绍 -->
<section class="design" id="design">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div id="secondSlider">
                    <ul class="slides">
                        <li>
                            <div class="col-md-4">
                                <div class="flat-box">
                                    <div class="colourway colour1">
                                        <img src="img/starry/1.jpg" width="350px" height="350px" alt="">
                                    </div>
                                    <p class="title">星座图</p>
                                    <p class="feature-text">查看详情</p>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="flat-box">
                                    <div class="colourway colour2">
                                        <img src="img/starry/2.jpg" width="350px" height="350px" alt="">
                                    </div>
                                    <p class="title">黑洞</p>
                                    <p class="feature-text">查看详情</p>
                                </div>
                            </div>
                            <div class="col-md-4 design-content">
                                <h1>新闻3</h1>
                                <p>查看详情</p>
                            </div>
                        </li>
                        <li>
                            <div class="col-md-4">
                                <div class="flat-box">
                                    <div class="colourway colour3">
                                        <img src="img/starry/3.jpg" width="350px" height="350px" alt="">
                                    </div>
                                    <p class="title">太阳系</p>
                                    <p class="feature-text">查看详情</p>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="flat-box">
                                    <div class="colourway colour4">
                                        <img src="img/starry/4.jpg" width="350px" height="350px" alt="">
                                    </div>
                                    <p class="title">银河系</p>
                                    <p class="feature-text">查看详情</p>
                                </div>
                            </div>
                            <div class="col-md-4 design-content">
                                <h1>新闻6</h1>
                                <p>查看详情</p>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="col-md-1 col-md-offset-11 text-right controls">
                    <a href="prev" class="prev">
                        <i class="fa fa-angle-left fa-3x"></i>
                    </a>
                    <a href="next" class="next">
                        <i class="fa fa-angle-right fa-3x"></i>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 text-center">
            <a href="#" class="try-btn">了解更多</a>
        </div>
    </div>
</section>
<!-- 手机端APP -->
<section class="download-now" id="getstarted">
    <div class="container">
        <div class="row">
            <div class="col-md-8 wp1">
                <%--<h1>See the sky even better than your own eyes can</h1>--%>
                <h1>看更美到天空</h1>
                <h2>泰戈尔说：“当你为错过太阳而哭泣的时候，你也要再错过群星了。”</h2>
                <h2>幸运的是我没有哭</h2>
                <h2>所以今天我可以幸福的仰望天空</h2>
                <h2>细数满天的繁星</h2>
                <h2>我看到东南边角落的那颗星星</h2>
                <h2>它很亮，很亮...</h2>
                <h2>一直发着光，像你一样。</h2>
                <p style="font-size: large">没有城市的灯光和高楼的阴影，夜空无比纯净，不是纯黑色的，而是有些发蓝，像一块柔软的天鹅绒。那些星辰就是无数散落在天鹅绒上的钻石，闪烁着微光。</p>
                <a href="../download/Star.zip" class="download-btn">马上下载</a>
                <%--<a href='<s:property value="uploadFileName"/> ' class="download-btn">马上下载</a>--%>
            </div>
        </div>
    </div>
</section>

<footer id="footer">
    <div class="container" style="height: 150px">
        <div class="row">
            <div class="col-md-4">
                <p>这夜，这星空，忽然之间就是一次偶遇，而这种偶遇总是会季节性地出现。
                    秋的萧瑟，秋的浅殇，总是无意间给生命增添了一种催化剂。
                    我想：我等待在青春，却错过了彼此，但我永远记得，那年……最灿烂，最寂寞的星空。</p>
            </div>
            <div class="col-md-3">
                <h1>也只有在最深沉的夜，</h1>
                <h3>我们才能拥有最美丽的星空。</h3>
            </div>
            <div class="col-md-2 col-md-offset-3">
                <ul class="footer-nav">
                    <li>
                        <a href="#">入门教程</a>
                    </li>
                    <li>
                        <a href="#">简介视频</a>
                    </li>
                    <li>
                        <a href="#">功能列表</a>
                    </li>
                    <li>
                        <a href="#">下载试用</a>
                    </li>
                    <li>
                        <a href="#">保持联系</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 text-center">
                <a href="#" class="badge-btn">Badge Button</a>
            </div>
        </div>
    </div>
</footer>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<%--<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>--%>
<script src="./js/jquery-1.12.4.min.js"></script>
<script src="./js/jquery.fancybox.pack.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="./js/bootstrap.min.js"></script>
<script src="./js/scripts.js?v=1.7"></script>
<script src="./js/jquery.flexslider.js"></script>
<script src="./js/jquery.smooth-scroll.js"></script>
<script src="./js/modernizr.js"></script>
<script src="./js/waypoints.min.js"></script>
//倒计时脚本
<script type="text/javascript">
    // 倒计时
    var _ordertimer = null;
    go(document.getElementById('date2').value);

    function leftTimer(enddate) {
        var leftTime = (new Date(enddate)) - new Date(); //计算剩余的毫秒数
        var days = parseInt(leftTime / 1000 / 60 / 60 / 24, 10); //计算剩余的天数
        var hours = parseInt(leftTime / 1000 / 60 / 60 % 24, 10); //计算剩余的小时
        var minutes = parseInt(leftTime / 1000 / 60 % 60, 10);//计算剩余的分钟
        var seconds = parseInt(leftTime / 1000 % 60, 10);//计算剩余的秒数
        days = checkTime(days);
        hours = checkTime(hours);
        minutes = checkTime(minutes);
        seconds = checkTime(seconds);
        if (days >= 0 || hours >= 0 || minutes >= 0 || seconds >= 0) document.getElementById("timer").innerHTML = "倒计时：" + days + "天" + hours + "小时" + minutes + "分" + seconds + "秒";
        if (days <= 0 && hours <= 0 && minutes <= 0 && seconds <= 0) {
            window.clearInterval(_ordertimer);
            _ordertimer = null;
        }
    }

    function checkTime(i) { //将0-9的数字前面加上0，例1变为01
        if (i < 10) {
            i = "0" + i;
        }
        return i;
    }

    function go(v) {
        var date1 = new Date(), data2 = new Date(v);
        if (data2 < date1) return;//设置的时间小于现在时间退出
        _ordertimer = setInterval(function () {
            leftTimer(data2)
        }, 1000);
    }
</script>
</body>
</html>