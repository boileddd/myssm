<%@page import="com.etc.news.entity.News"%>
<%@page import="com.etc.news.entity.User"%>
<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/MyPage.tld" prefix="pt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>新闻中国</title>
<link href="CSS/main.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>

<style type="text/css">
.logoimage {
	margin: 0px auto;
	width: 947px;
	background-image: url("Images/admin_bar.gif");
}
</style>
<script type="text/javascript">
	function mydelete(newsid) {
		if (confirm("你确定要删除该条数据吗?")) {
			location.href = "doDeleteNewsById.jsp?newsid=" + newsid;
		}
	}
</script>
</head>
<body>
	<div id="header">
		<div id="top_login">
			<label> 欢迎使用新闻管理系统 </label> <label id="error"> </label> <img
				src="Images/friend_logo.gif" alt="Google" id="friend_logo" />
		</div>
		<div id="nav">
			<div id="logo">
				<img src="Images/logo.jpg" alt="新闻中国" />
			</div>
			<div id="a_b01">
				<img src="Images/a_b01.gif" alt="" />
			</div>
			<!--mainnav end-->
		</div>

	</div>
	<div class="logoimage">
		管理员:${sessionScope.user.user_name } 登录 &nbsp; &nbsp; &nbsp; &nbsp;
		&nbsp; &nbsp;<a>loginout</a>
	</div>
	<div id="container">
		<div class="sidebar">
			<ul>
				<li><a href="addNews.jsp">添加新闻</a></li>
				<br>
				<li><a href="DoQueryNewsPage">编辑新闻</a></li>
				<br>
				<li><a href="addType.jsp">添加主题</a></li>
				<br>
				<li><a href="editType.jsp">编辑主题</a></li>
				<br>
			</ul>
		</div>
		<div class="main">
			<ul type="circle">
			<c:forEach items="${requestScope.list}" var="list">
				<li><label>
					<a href="DoQueryNewsById?newsid=${list.news_id }">
					${list.news_title }
					</a>
					</label>
					<label style="margin-left:400px">
					作者:${list.news_author.user_name}
					</label>
					<label>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="DoQueryNewsById2?newsid=${list.news_id}">
						修改</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a href="javascript:mydelete('${list.news_id}')">
						删除</a>
					</label>
				</li>
			</c:forEach>
			</ul>


			<div class="container" style="width:500px;height:50px;margin:0 auto;">                 
			  <ul class="pagination">
			    <li class="page-item"><a class="page-link" href="DoQueryNewsPage?page=${requestScope.page-1}">上一页</a></li>
			    <c:forEach items="${requestScope.pageCountList}" var="pageindex">
			    <c:if test="${pageindex==requestScope.page }">
				    <li class="page-item active"><a class="page-link">${pageindex}</a></li>
			    </c:if>
			    <c:if test="${pageindex!=requestScope.page }">
				    <li class="page-item"><a class="page-link" href="DoQueryNewsPage?page=${pageindex }">${pageindex}</a></li>
			    </c:if>
			    </c:forEach>
			    <li class="page-item"><a class="page-link" href="DoQueryNewsPage?page=${requestScope.page+1}">下一页</a></li>
			  </ul>
			</div>
			
			<div style="width:500px;height:50px;margin:0 auto;">                 
				<pt:page pageIndex="${requestScope.page}" url="DoQueryNewsPage?" pageMax="${requestScope.pageCount}"/>
			</div>
			
		</div>

	</div>
	<div id="friend"></div>
	<div id="footer">
		<p class="">
			24小时客户服务热线：010-68988888 &#160;&#160;&#160;&#160; <a href="#">常见问题解答</a>
			&#160;&#160;&#160;&#160; 新闻热线：010-627488888 <br />
			文明办网文明上网举报电话：010-627488888 &#160;&#160;&#160;&#160; 举报邮箱： <a href="#">jubao@jb-aptech.com.cn</a>
		</p>
		<p class="copyright">
			Copyright &copy; 1999-2009 News China gov, All Right Reserver <br />
			新闻中国 版权所有
		</p>
	</div>
</body>
</html>