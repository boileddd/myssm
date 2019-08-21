<%@page import="com.etc.news.entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>新闻中国</title>
<link href="CSS/main.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.logoimage{
margin:0px auto;
width:947px;
background-image: url("Images/admin_bar.gif");
}

</style>

</head>
<body>
<div id="header">
  <div id="top_login">
    <label> 欢迎使用新闻管理系统 </label>
  
   
   
    <label id="error"> </label>
    <img src="Images/friend_logo.gif" alt="Google" id="friend_logo" /> </div>
  <div id="nav">
    <div id="logo"> <img src="Images/logo.jpg" alt="新闻中国" /> </div>
    <div id="a_b01"> <img src="Images/a_b01.gif" alt="" /> </div>
    <!--mainnav end-->
  </div>
</div>
  <div  class="logoimage">管理员:${sessionScope.user.user_name }  登录 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<a>loginout</a></div>
<div id="container">
  <div class="sidebar">
    <ul>
    <li><a href="">添加新闻</a></li><br>
     <li><a href="DoQueryNewsPage">编辑新闻</a></li><br>
       <li href="addType.jsp"><a>添加主题</a></li><br>
        <li><a>编辑主题</a></li><br>
    </ul>
  </div>
  <div class="main">
  <form action="DoAddType" method="get">
<ul type="circle">
  <li>输入要添加的主题:<input name="typename"></li>
    <li><input type="submit" value="提交"></li>
  </ul>
</form>
  </div>
</div>
<div id="friend">

</div>
<div id="footer">
  <p class=""> 24小时客户服务热线：010-68988888 &#160;&#160;&#160;&#160; <a href="#">常见问题解答</a> &#160;&#160;&#160;&#160; 新闻热线：010-627488888 <br />
    文明办网文明上网举报电话：010-627488888 &#160;&#160;&#160;&#160; 举报邮箱： <a href="#">jubao@jb-aptech.com.cn</a> </p>
  <p class="copyright"> Copyright &copy; 1999-2009 News China gov, All Right Reserver <br />
    新闻中国 版权所有 </p>
</div>
</body>
</html>