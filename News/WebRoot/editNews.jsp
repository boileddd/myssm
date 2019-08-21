<%@page import="com.etc.news.entity.Type"%>
<%@page import="com.etc.news.entity.News"%>
<%@page import="com.etc.news.entity.User"%>
<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- 导入c标签   -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>新闻中国</title>
<link href="CSS/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/uedit/ueditor.config.js"></script>
<script type="text/javascript" src="js/uedit/ueditor.all.js"></script>
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

	function show() {
		var fileTag = document.getElementById('file');
		fileTag.onchange = function() {
			var file = fileTag.files[0];
			var fileReader = new FileReader();
			fileReader.onloadend = function() {
				if (fileReader.readyState == fileReader.DONE) {
					document.getElementById('img').setAttribute('src', fileReader.result);
				}
			};
			fileReader.readAsDataURL(file);
		};
	}
	;
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
	<div id="container" style="height:auto;">
		<div class="sidebar">
			<ul>
				<li><a href="addNews.jsp">添加新闻</a></li>
				<br>
				<li><a href="DoQueryNewsPage">编辑新闻</a></li>
				<br>
				<li><a href="addType.jsp">添加主题</a></li>
				<br>
				<li><a>编辑主题</a></li>
				<br>
			</ul>
		</div>
		<div class="main">
			<ul type="circle">
			<form action="DoSaveNews" method="post" enctype="multipart/form-data">
				新闻id<input type="text" name="newsid" readonly="readonly"
					value="${requestScope.news.news_id}"><br> 
				新闻标题<input type="text" name="newstitle" value="${requestScope.news.news_title }"><br> 
				新闻内容<div style="width: 500px;">
				<textarea id="myEditor"   name="newscontent" >${requestScope.news.news_content }</textarea>
				</div>
				
				<script type="text/javascript">
				  //构造一个富文本编辑器对象
				  var editor = new UE.ui.Editor(); 
				  //再将该对象放入id为myEditor的textarea中
				  editor.render("myEditor"); 
				</script>
				 新闻作者 <select name="newsauthor">
					<c:forEach items="${requestScope.userlist}" var="myuser">
					<c:if test="${requestScope.news.news_author.user_id==myuser.user_id}">
						<option selected="selected" value="${myuser.user_id }">
							${myuser.user_name }
						</option>
					</c:if>
					<c:if test="${requestScope.news.news_author.user_id!=myuser.user_id}">
						<option value="${myuser.user_id }">
							${myuser.user_name }
						</option>
					</c:if>
					</c:forEach>
				</select> <br> 
				新闻类型 <select name="newstype">
					<!-- items 要遍历的集合   var 每次遍历的对象名称-->
					<c:forEach items="${requestScope.typelist}" var="mytype">
					<c:if test="${mytype.type_id==requestScope.news.news_type.type_id}">
						<option value="${mytype.type_id}" selected="selected">
							${mytype.type_name}
						</option>
					</c:if>
					<!-- test表示返回一个boolean类型的值,如果为真执行c if里面的代码 -->
					<c:if
						test="${mytype.type_id!=requestScope.news.news_type.type_id}">
						<option value="${mytype.type_id}">${mytype.type_name}</option>
					</c:if>
					</c:forEach>
				</select> <br> 
				新闻图片<br> 
					<c:if test="${requestScope.news.news_image == null}">
						<img alt="" id="img" src="Images/timg.jpg" width="300" height="300">
					</c:if>
					<c:if test="${requestScope.news.news_image != null}">
						<img alt="" id="img" src="upload/${requestScope.news.news_image}" width="300" height="300">
					</c:if><br>
					<input type="file"
					id="file" name="newsimage" onclick="show()" value="${requestScope.news.news_image}"><br> 
					<input type="submit" value="保存">
			</form>
			</ul>
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