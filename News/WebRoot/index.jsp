<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script src="js/jquery.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	$.getJSON("DoQueryAllType",function(data){
		var str="<li id='class_month'>";
		for(var i=0;i<data.length;i++){
			str=str+"<a href='#'><b> "+data[i].type_name+"</b></a> ";
			if((i+1)%10==0){
				str=str+"</li> <li id='class_month'>";
			}
		}
		str=str+"</li>";
		$("#newstype").html(str);
	});

});


$(document).ready(function(){
	$.getJSON("DoQueryNewsByType",{typename:"国内"},function(data){
		var str="<ul>";
		for(var i=0;i<data.length;i++){
			str = str+"<li> <a href='DoQueryNewsById?newsid="+data[i].news_id+" '><b>"+ data[i].news_title +"</b></a> </li>"
		}
		str=str+"</ul>";
		$("#news1").html(str);
	
	});

});

$(document).ready(function(){
	$.getJSON("DoQueryNewsByType",{typename:"国际"},function(data){
		var str="<ul>";
		for(var i=0;i<data.length;i++){
			str = str+"<li> <a href='DoQueryNewsById?newsid="+data[i].news_id+" '><b>"+ data[i].news_title +"</b></a> </li>"
		}
		str=str+"</ul>";
		$("#news2").html(str);
	
	});

});

$(document).ready(function(){
	$.getJSON("DoQueryNewsByType",{typename:"娱乐"},function(data){
		var str="<ul>";
		for(var i=0;i<data.length;i++){
			str = str+"<li> <a href='DoQueryNewsById?newsid="+data[i].news_id+" '><b>"+ data[i].news_title +"</b></a> </li>"
		}
		str=str+"</ul>";
		$("#news3").html(str);
	
	});

});
//图片新闻
$(document).ready(function(){
	$.getJSON("DoQueryNewsByImage",function(data){
		var str="<ul>";
		for(var i=0;i<data.length;i++){
			if(data[i].news_image !=null)
			str = str+"<li> <a href='DoQueryNewsById?newsid="+data[i].news_id+"'><img src=upload/"
				+ data[i].news_image+" width='249' alt='' /> </a><a href='DoQueryNewsById?newsid="+data[i].news_id+"'>"
				+ data[i].news_title +"</a> </li>";
		}
		str=str+"</ul>";
		$("#imagenews").html(str);
	
	});

});

//使用ajax进行分页
$(document).ready(function(){
	$.getJSON("DoQueryNewsPageByAjax",{page:"1"},function(data){
		var str="";
		for(var i = 0;i<data.list.length;i++){
			str=str+"<li><a href='DoQueryNewsById?newsid="+data.list[i].news_id+"'>"+data.list[i].news_title+"</a><span>"+data.list[i].news_time+"</span></li>";
			if((i+1)%5==0){
				str=str+"<li class='space'></li>";
			}
		}
		$("#allnews").html(str);
		
		var str2="<div class='container' style='width:500px;height:50px;margin:0 auto;'><ul class='pagination'>"
				+"<li class='page-item'><a class='page-link' href='javascript:mypage(1)'>首页</a></li>"
				+"<li class='page-item'><a class='page-link' href='javascript:mypage("+(data.page-1)+")'>上一页</a></li>";
		for(var i = 1;i<=data.pageCount;i++){
			if(data.page==i){
				str2=str2+"<li class='page-item active'><a class='page-link'>"+i+"</a></li>"
			}
			else{
				str2 = str2+"<li class='page-item'><a class='page-link' href='javascript:mypage("+i+")'>"+i+"</a></li>"
			}
		}
		str2=str2+"<li class='page-item'><a class='page-link' href='javascript:mypage("+(data.page+1)+")'>下一页</a></li>"
				+"<li class='page-item'><a class='page-link' href='javascript:mypage("+data.pageCount+")'>末页</a></li>"
				+"</ul></div>";
		$("#mypage").html(str2);
	});
});

function mypage(page){
	$.getJSON("DoQueryNewsPageByAjax",{page:page},function(data){
		var str="";
		for(var i = 0;i<data.list.length;i++){
			str=str+"<li><a href='DoQueryNewsById?newsid="+data.list[i].news_id+"'>"+data.list[i].news_title+"</a><span>"+data.list[i].news_time+"</span></li>";
			if((i+1)%5==0){
				str=str+"<li class='space'></li>";
			}
		}
		$("#allnews").html(str);
		var str2="<div class='container' style='width:500px;height:50px;margin:0 auto;'><ul class='pagination'>"
				+"<li class='page-item'><a class='page-link' href='javascript:mypage(1)'>首页</a></li>"
				+"<li class='page-item'><a class='page-link' href='javascript:mypage("+(data.page-1)+")'>上一页</a></li>";
		for(var i = 1;i<=data.pageCount;i++){
			if(data.page==i){
				str2=str2+"<li class='page-item active'><a class='page-link'>"+i+"</a></li>"
			}
			else{
				str2 = str2+"<li class='page-item'><a class='page-link' href='javascript:mypage("+i+")'>"+i+"</a></li>"
			}
		}
		str2=str2+"<li class='page-item'><a class='page-link' href='javascript:mypage("+(data.page+1)+")'>下一页</a></li>"
				+"<li class='page-item'><a class='page-link' href='javascript:mypage("+data.pageCount+")'>末页</a></li>"
				+"</ul></div>";
		$("#mypage").html(str2);
	});
}
</script>
</head>
<body>
<div id="header">
  <div id="top_login">
    
    <form action="DoMyLogin" method="get">
    <label>登录名 </label>
    <input type="text" name="uname" id="uname" value="" class="login_input" />
    <label> 密&#160;&#160;码 </label>
    <input type="password" name="upwd" id="upwd" value="" class="login_input" />
    <input type="submit" class="login_sub" value="登录" />
    </form>
    <label id="error"> </label>
    <img src="Images/friend_logo.gif" alt="Google" id="friend_logo" /> </div>
  <div id="nav">
    <div id="logo"> <img src="Images/logo.jpg" alt="新闻中国" /> </div>
    <div id="a_b01"> <img src="Images/a_b01.gif" alt="" /> </div>
    <!--mainnav end-->
  </div>
</div>
<div id="container">
  <div class="sidebar">
    <h1> <img src="Images/title_1.gif" alt="国内新闻" /> </h1>
    <div class="side_list" id="news1">
      <img src="Images/ajax-loader.gif">正在加载中...
    </div>
    <h1> <img src="Images/title_2.gif" alt="国际新闻" /> </h1>
    <div class="side_list" id="news2">
      <img src="Images/ajax-loader.gif">正在加载中...
    </div>
    <h1> <img src="Images/title_3.gif" alt="娱乐新闻" /> </h1>
    <div class="side_list" id="news3">
      <img src="Images/ajax-loader.gif">正在加载中...
    </div>
  </div>
  <div class="main">
    <div class="class_type"> <img src="Images/class_type.gif" alt="新闻中心" /> </div>
    <div class="content">
      <ul class="class_date" id="newstype">
        <img src="Images/ajax-loader.gif">正在加载中...
      </ul>
      <ul class="classlist" id="allnews">
        <img src="Images/ajax-loader.gif">正在加载中...
      </ul>
        <div id="mypage">
		    
	    </div>
    </div>
    <div class="picnews" id="imagenews">
      
    </div>
  </div>
</div>
<div id="friend">
  <h1 class="friend_t"> <img src="Images/friend_ico.gif" alt="合作伙伴" /> </h1>
  <div class="friend_list">
    <ul>
      <li> <a href="#">中国政府网</a> </li>
      <li> <a href="#">中国政府网</a> </li>
      <li> <a href="#">中国政府网</a> </li>
      <li> <a href="#">中国政府网</a> </li>
      <li> <a href="#">中国政府网</a> </li>
      <li> <a href="#">中国政府网</a> </li>
      <li> <a href="#">中国政府网</a> </li>
    </ul>
  </div>
</div>
<div id="footer">
  <p class=""> 24小时客户服务热线：010-68988888 &#160;&#160;&#160;&#160; <a href="#">常见问题解答</a> &#160;&#160;&#160;&#160; 新闻热线：010-627488888 <br />
    文明办网文明上网举报电话：010-627488888 &#160;&#160;&#160;&#160; 举报邮箱： <a href="#">jubao@jb-aptech.com.cn</a> </p>
  <p class="copyright"> Copyright &copy; 1999-2009 News China gov, All Right Reserver <br />
    新闻中国 版权所有 </p>
</div>
</body>
</html>