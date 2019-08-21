<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="no-js" lang="zxx">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>房间详情</title>
    <!-- Google Fonts Includes -->
    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet">
    <!-- Favi icon -->
    <link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico">
    <!-- bootstrap v3.3.6 css -->
    <link rel="stylesheet" href="css/assets/bootstrap.min.css">
    <!-- animate css -->
    <link rel="stylesheet" href="css/assets/animate.css">
    <!-- Button Hover animate css -->
    <link rel="stylesheet" href="css/assets/hover-min.css">
    <!-- jquery-ui.min css -->
    <link rel="stylesheet" href="css/assets/jquery-ui.min.css">
    <!-- meanmenu css -->
    <link rel="stylesheet" href="css/assets/meanmenu.min.css">
    <!-- owl.carousel css -->
    <link rel="stylesheet" href="css/assets/owl.carousel.min.css">
    <!-- slick css -->
    <link rel="stylesheet" href="css/assets/slick.css">
    <!-- chosen.min-->
    <link rel="stylesheet" href="css/assets/jquery-customselect.css">
    <!-- font-awesome css -->
    <link rel="stylesheet" href="css/assets/font-awesome.min.css">
    <!-- magnific Css -->
    <link rel="stylesheet" href="css/assets/magnific-popup.css">
    <!-- Preloader css -->
    <link rel="stylesheet" href="css/assets/preloader.css"> 
    <!-- custome css -->
    <link rel="stylesheet" href="css/style.css">
    <!-- responsive css -->
    <link rel="stylesheet" href="css/responsive.css">
    <link rel="stylesheet" href="css/master.css">
    <!-- modernizr css -->
    <script src="js/vendor/modernizr-2.8.3.min.js"></script>
    <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
	<link href="css/light-carousel.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
	
	<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/css/bootstrap.min.css">
  	<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
  	<script src="https://cdn.staticfile.org/popper.js/1.12.5/umd/popper.min.js"></script>
  	<script src="https://cdn.staticfile.org/twitter-bootstrap/4.1.0/js/bootstrap.min.js"></script>
	
	<script src="js/jquery.js"></script>
	<script> var jq1 = jQuery.noConflict(true); </script>
	<script type="text/javascript">
	
	//使用ajax分页查询所有的房源评价
	$(document).ready(function(){
		$.getJSON("DoQueryHouseComtById",{houseId:${requestScope.pojo.house.houseId},page:"1"},function(data){
			var str="";
			for(var i = 0;i<data.list.length;i++){
				str=str+"<div class='comment-list-wrapper'>"
					+"<div class='comment-list'>"
					+"<div class='commnet_img'>"
					+"<img src='"+data.list[i].user.userPic+"' alt='暂无头像' class='img-fluid'>"
					+"</div>"
					+"<div class='comment-text'>"
					+"<div class='author_info'>"
					+"<div class='author_name'>"
					+"<a href='' class=''>"+data.list[i].user.userName+"</a>"
					+"<span>"+data.list[i].hComtTime+"</span>"
					+"</div>"
					+"</div>"
					+"<ul class='review_rating d-flex'>";
					for(var j = 0; j < data.list[i].hComtScore;j++){
						str=str+"<li><i class='fa fa-star'></i></li>";
					}
					str=str+"</ul>"
					+"<p>"+data.list[i].hComtText+"</p>"
					+"</div>"
					+"</div>"
					+"</div>";
				
			}
			$("#allComt").html(str);
			
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
				if(i==3){
					break;
				}
			}
			str2=str2+"<li class='page-item'><a class='page-link' href='javascript:mypage("+(data.page+1)+")'>下一页</a></li>"
					+"<li class='page-item'><a class='page-link' href='javascript:mypage("+data.pageCount+")'>末页</a></li>"
					+"</ul></div>"
					+"<div class='container' style='width:500px;height:50px;margin:0 auto;'><h4>当前第"+data.page+"页/共"+data.pageCount+"页</h4><div>";
			$("#mypage").html(str2);
		});
	});

	function mypage(page){
		$.getJSON("DoQueryHouseComtById",{houseId:${requestScope.pojo.house.houseId},page:page},function(data){
			var str="";
			for(var i = 0;i<data.list.length;i++){
				str=str+"<div class='comment-list-wrapper'>"
					+"<div class='comment-list'>"
					+"<div class='commnet_img'>"
					+"<img src='"+data.list[i].user.userPic+"' alt='暂无头像' class='img-fluid'>"
					+"</div>"
					+"<div class='comment-text'>"
					+"<div class='author_info'>"
					+"<div class='author_name'>"
					+"<a href='' class=''>"+data.list[i].user.userName+"</a>"
					+"<span>"+data.list[i].hComtTime+"</span>"
					+"</div>"
					+"</div>"
					+"<ul class='review_rating d-flex'>";
					for(var j = 0; j < data.list[i].hComtScore;j++){
						str=str+"<li><i class='fa fa-star'></i></li>";
					}
					str=str+"</ul>"
					+"<p>"+data.list[i].hComtText+"</p>"
					+"</div>"
					+"</div>"
					+"</div>";
				
			}
			$("#allComt").html(str);
			
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
					+"</ul></div>"
					+"<div class='container' style='width:500px;height:50px;margin:0 auto;'><h4>当前第"+data.page+"页/共"+data.pageCount+"页</h4><div>";
			$("#mypage").html(str2);
		});
	}
	
	
	//点击联系方式按钮显示房东的联系方式
	function myTel(){
	$("#userTel").html(${requestScope.pojo.house.user.userTel});
	}
	
	//验证输入的举报信息
	function checkReport(){
		var reason = document.getElementById("reason").value;
		var mytel = document.getElementById("r_contact").value;
		if(reason.length <= 0){
			var str="<div class='alert alert-danger'><strong>提示!</strong>请输入举报理由</div>";
			$("#tips").html(str);
		}
		else if(mytel.length <=0 ){
			var str="<div class='alert alert-danger'><strong>提示!</strong>请输入您的联系方式方便我们与您反馈举报结果</div>";
			$("#tips").html(str);
		}else{
			$("#tips").html("");
		}
		
	}
    
	</script>
	
	
</head>
<body>
<div id="loader-wrapper">
    <div id="loader"></div>
    <div class="loader-section section-left"></div>
    <div class="loader-section section-right"></div>
</div>
<!-- header area start here -->
<header>
    <div class="header_top_area">
        <div class="container">
            <div class="row">
                <div class="col-12 col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="contact_wrapper_top">
                        <ul class="header_top_contact">
                            <li><i class="fa fa-phone" aria-hidden="true"></i>996</li>
                            <li><i class="fa fa-envelope-o" aria-hidden="true"></i>@fjut</li>
                        </ul>
                        <div class="book-btn">
                            <a href="#">立即预订</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div> <!-- header top end -->

    <div class="main_nav">
		<div class="container">
			<div class="row">
				<div class="col-md-2 col-sm-2 col-xs-12 tap-v-responsive">
					<div class="logo-area">
						<a href="index.jsp"><img src="images/logo.png" alt="">
						</a>
					</div>
				</div>
				<div class="col-md-10">
					<nav>
						<ul class="main-menu text-right">
							<li class="active"><a href="index.jsp">首页</a>
								
							</li>
							<li><a href="HotelList.jsp">房间列表</a>
								<ul class="dropdown">
									<li><a href="HotelListOne.jsp">热门房源</a></li>
                                    <!-- <li><a href="HotelListTwo.jsp">Package Two</a></li> -->
                                    <!-- <li><a href="HotelListDetails.jsp">Package Details</a></li> -->
								</ul>
							</li>
							<li><a href="JoinUsPage.jsp">管理房源</a>
								<ul class="dropdown">
									<li><a href="JoinUsPageOne.jsp">添加房源</a></li>
                                   <!--  <li><a href="JoinUsPageTwo.jsp"></a></li> -->
                                    <li><a href="JoinUsPageDetails.jsp">修改房源信息</a></li>
                                    <li><a href="JoinUsPageDetails.jsp">撤销房源</a></li>
								</ul>
							</li>
							<li><a href="Story.jsp">故事</a></li>
							<li><a href="#">订单中心</a></li>
							<li><a href="#">心愿单</a></li>
							<li><a href="#">个人中心</a></li>
							<li><a href="HelpPage.jsp">帮助</a>
								<ul class="dropdown">
									<li><a href="HelpPageOne.jsp">订房流程</a></li>
                                    <li><a href="HelpPageTwo.jsp">了解房源信息</a></li>
                                    <li><a href="HelpPageSingle.jsp">安全与信任</a></li>
								</ul>
							</li>
							<li><a href="Register.jsp">注册</a></li>
						</ul>
					</nav>
				</div> <!-- main menu end here -->
			</div>
		</div>
	</div> <!-- header-bottom area end here -->
</header> <!-- header area end here -->

<!--
	blog breadcrumb version one start here
-->
<section class="breadcrumb-blog-version-one">
    <div class="single-bredcurms" style="background-image:url('images/bercums/Hotels-Version-02.jpg');">
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <div class="bredcrums-content">
                        <h2>房间</h2>
                        <ul>
                            <li><a href="index.html">首页</a>
                            </li>
                            <li class="active"><a href="single-package.html">房间详情</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- blog breadcrumb version one end here -->

<section class="section-paddings single-package-area" id="hotel_details">
    <div class="container">
        <div class="row">
            <!-- single package tab with details -->
            <div class="col-md-8 col-sm-12">
                <div class="single-package-details">
                    <div class="single-package-title">
                    	<!-- 房源标题 -->
                        <h2>${requestScope.pojo.house.houseTitle}</h2>
                    </div>
                    <ul class="package-content d-flex">
                        <li class="price_hotel"><span>￥  ${requestScope.pojo.house.housePrice} /</span>晚</li>
                        <li>
                            <span>
                            	<c:set var="num" value="${requestScope.pojo.house.houseScore}"></c:set>
                            	<c:forEach begin="1" end="${num}" var="v" >
                            		<i class="fa fa-star"></i>
                            	</c:forEach>
                           </span>
                        </li>
                        <li>${requestScope.pojo.house.houseType.houseTypeName}</li>
                    </ul>
                </div><!-- tab menu strat -->
                
                
                <div class="package_banner">
                    <!--<img src="images/hotel/hotel01.jpg" alt="" class="img-fluid">-->
                    <div class="col-md-8 single-left">
						<div class="sample1" style="width: 700px;">
							<div class="carousel">
								<ul id="housePic1">
								<c:set var="pics" value="${requestScope.pojo.house.housePic}"></c:set>
								<c:set var="pic" value="${fn:split(pics, ';')}"></c:set>
								<c:forEach items="${pic}" var="str">
									<li> <img src="${str}" alt=" "> </li>
								</c:forEach>
									<!-- <li> <img src="images/13.jpg" alt=" "> </li>
									<li> <img src="images/14.jpg" alt=" "> </li>
									<li> <img src="images/15.jpg" alt=" "> </li>
									<li> <img src="images/16.jpg" alt=" "> </li> -->
								</ul>
								<div class="controls">
									<div class="prev"></div>
									<div class="next"></div>
								</div>
								
							</div>
							<div class="thumbnails">
								<ul id="housePic2">
									<c:set var="pics" value="${requestScope.pojo.house.housePic}"></c:set>
									<c:set var="pic" value="${fn:split(pics, ';')}"></c:set>
									<c:forEach items="${pic}" var="str">
										<li> <img src="${str}" alt=" "> </li>
									</c:forEach>
									<!-- <li> <img src="images/13.jpg" alt=" "> </li>
									<li> <img src="images/14.jpg" alt=" "> </li>
									<li> <img src="images/15.jpg" alt=" "> </li>
									<li> <img src="images/16.jpg" alt=" "> </li> -->
								</ul>
							</div>
						</div>
						<script src="js/jquery.light-carousel.js"></script> 
						<script>
							$('.sample1').lightCarousel();
						</script> 
					</div>
                </div>
                <div class="package-tab-menu">
                    <ul class="package-tab-menu nav nav-tabs" id="myTab2" role="tablist">
                        <li role="presentation"><a href="#description" class="active" aria-controls="description" role="tab" data-toggle="tab">简介</a></li>
                        <li role="presentation"><a href="#itinerary" aria-controls="itinerary" role="tab" data-toggle="tab">设施</a></li>
                        <li role="presentation"><a href="#map" aria-controls="map" role="tab" data-toggle="tab">地理位置</a>
                        <li role="presentation"><a href="#video" aria-controls="video" role="tab" data-toggle="tab">房东</a></li>
                        <li role="presentation"><a href="#reviews" aria-controls="reviews" role="tab" data-toggle="tab">评论</a></li>
                    </ul>
                </div><!-- tab menu end -->

                <!-- tab content start -->
                <div class="row">
                    <!-- tabs content -->
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane fade show active in" id="description">
                            <div class="row">
                                <!-- left content -->
                                <div class="col-md-12 col-sm-12">
                                    <div class="tour-description">
                                        <p>${requestScope.pojo.house.houseImfo}</p>
                                    </div>
                                    
                                </div><!-- left-content -->

                            </div>
                        </div>

                        <div role="tabpanel" class="tab-pane fade" id="itinerary">
                            <div class="row">
                                <div class="col-md-12 col-sm-12">
                                    <div class="tour-description">
                                        <h4>该房东提供以下设施</h4>
                                        <div class="main-timeline">
                                        
                                            <!-- single timeline -->
                                            <div class="timeline">
                                                <div class="timeline-content left">
                                                    <span class="timeline-icon">1</span>
                                                    <h4>基础设施</h4>
                                                    <p>
                                                    	<c:forEach items="${requestScope.pojo.list}" var="list">
                                                    	<c:if test="${list.facTypeId == 1}">
                                                    	<div class="packaging_contents_wrapper">
					                                        <div class="packaging-contents">
					                                            <ul class="list-unstyled">
					                                                <li><i class="fa fa-angle-double-right"></i>${list.fac.facName}*${list.facAmount}</li>
					                                            </ul>
					                                        </div>
					                                    </div>
                                        				</c:if>
                                        				</c:forEach>
                                                    </p>
                                                </div>
                                            </div>
                                            <!-- single timeline -->
                                            
                                            
                                            <!-- single timeline -->
                                            <div class="timeline">
                                                <div class="timeline-content left">
                                                    <span class="timeline-icon">2</span>
                                                    <h4>便利设施</h4>
                                                    <p>
                                                    	<c:forEach items="${requestScope.pojo.list}" var="list">
                                                    	<c:if test="${list.facTypeId == 2}">
                                        				<div class="packaging_contents_wrapper">
					                                        <div class="packaging-contents">
					                                            <ul class="list-unstyled">
					                                                <li><i class="fa fa-angle-double-right"></i>${list.fac.facName}*${list.facAmount}</li>
					                                            </ul>
					                                        </div>
					                                    </div>
                                        				</c:if>
                                        				</c:forEach>
                                                    </p>
                                                </div>
                                            </div>
                                            <!-- single timeline -->
                                            
                                            <!-- single timeline -->
                                            <div class="timeline">
                                                <div class="timeline-content left">
                                                    <span class="timeline-icon">3</span>
                                                    <h4>安全设施</h4>
                                                    <p>
                                                    	<c:forEach items="${requestScope.pojo.list}" var="list">
                                                    	<c:if test="${list.facTypeId == 3}">
                                        				<div class="packaging_contents_wrapper">
					                                        <div class="packaging-contents">
					                                            <ul class="list-unstyled">
					                                                <li><i class="fa fa-angle-double-right"></i>${list.fac.facName}*${list.facAmount}</li>
					                                            </ul>
					                                        </div>
					                                    </div>
                                        				</c:if>
                                        				</c:forEach>
                                                    </p>
                                                </div>
                                            </div>
                                            <!-- single timeline -->
                                            
                                            
                                            <!-- single timeline -->
                                            <div class="timeline">
                                                <div class="timeline-content left">
                                                    <span class="timeline-icon">4</span>
                                                    <h4>公共设施</h4>
                                                    <p>
                                                    	<c:forEach items="${requestScope.pojo.list}" var="list">
                                                    	<c:if test="${list.facTypeId == 4}">
                                        				<div class="packaging_contents_wrapper">
					                                        <div class="packaging-contents">
					                                            <ul class="list-unstyled">
					                                                <li><i class="fa fa-angle-double-right"></i>${list.fac.facName}*${list.facAmount}</li>
					                                            </ul>
					                                        </div>
					                                    </div>
                                        				</c:if>
                                        				</c:forEach>
                                                    </p>
                                                </div>
                                            </div>
                                            <!-- single timeline -->
                                            
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div role="tabpanel" class="tab-pane fade" id="map">
                            <div class="row">
                                <div class="col-md-12 col-sm-12">
                                    <div class="tour-description">
                                        <h4>Map Tour</h4>
                                        <!-- map -->
                                        <div class="mapp" id="overlay">
                                            <iframe id="maping" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3350.701132954845!2d-96.89708368545975!3d32.879626086054884!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x864e9d666937c1f7%3A0xae26e896218f405!2sUSA+Bowl!5e0!3m2!1sbn!2sbd!4v1493296407400" allowfullscreen></iframe>
                                        </div><!-- map -->
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        
                        <!-- 房东信息 start -->
                        <div role="tabpanel" class="tab-pane fade" id="video">
                            <div class="row">
                                <div class="col-12 col-md-12 col-sm-12">
                                    <div class="tour-description">
                                        <h4>房东信息</h4>
                                        <h4><a href="" class="">${requestScope.pojo.house.user.userName}</a>
                                        <span>${requestScope.pojo.house.user.userAdress}</span>
                                        </h4>
                                        <div class="commnet_img">
                                             <img src="${requestScope.pojo.house.user.userPic}" alt="暂无头像" class="img-fluid">
                                        </div><br><br>
                                        <button type="button" class="btn btn-primary" onclick="myTel()">联系方式</button>
                                        <p id="userTel"></p>
                                        <h4>个人简介</h4>
                                        	<p>${requestScope.pojo.house.user.userImfo}</p>
                                        
                                        <!-- 房东信息 -->
                               
                                	</div>
                            	</div>
                            </div>
                        </div><!-- 房东信息 content end -->

                        <!-- 评价内容 start -->
                        <div role="tabpanel" class="tab-pane fade" id="reviews">
                            <div class="row">
                                <div class="col-md-12 col-sm-12">
                                    <div class="tour-description">
                                    <!-- 评价列表  -->
                                        <div class="comment-list-items" id="allComt">
                                         
                                            
                                        </div> 
                                        <!-- 评价列表  -->
                                        <div id="mypage">
                                        
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div><!-- 评价内容 end -->
                        
                    </div><!-- tabs content-->
                </div><!-- tab content end -->
            </div><!-- single package tab with details -->

            <!-- booking form start here -->
            <div class="col-md-4 col-sm-12">
                <aside >
                    <div class="booking-form">
                        <div class="booking-title">
                            <h2>${requestScope.pojo.house.houseTitle}</h2>
                            <p><span>￥  ${requestScope.pojo.house.housePrice} /</span>晚</p>
                            <p><span>服务费： ${requestScope.pojo.house.housePrice*0.1}</span></p>
                        </div>
                        <!-- <form action="#" method="post" style="display:none">
                            <div class="form-group">
                                <input type="text" class="form-control" id="name" placeholder="姓名">
                            </div>
                            <div class="form-group">
                                <input type="email" class="form-control" id="confirm_email" placeholder="邮箱">
                            </div>
                            <div class="form-group">
                                <input type="tel" class="form-control" id="number" placeholder="电话号码">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" id="booking-date" placeholder="dd-mm-yy *">
                            </div>
                            <div class="form-group">
                                <input type="text" class="form-control" id="ticket" placeholder="人数 *">
                            </div>
                            <div class="form-group">
                                <textarea name="messgae" id="message" cols="30" rows="7" class="form-control" placeholder="留言"></textarea>
                            </div>
                            <div class="form-group form_btn">
                                <button type="submit" class="booking-confirm hvr-shutter-out-horizontal">立即预订</button>
                            </div>
                        </form> -->
                        <div class="form-group form_btn">
                                <button type="submit" class="booking-confirm hvr-shutter-out-horizontal">立即预订</button>
                        </div>
                    </div>
                    
                    
                </aside><!-- adverestment start here-->
                
                <div style="text-align:center;margin:0 auto;">
                <a href="javascript:void(0)" data-toggle="modal" data-target="#myModal">举报此房源</a>
                </div>
				  <!-- 模态框 -->
				  <div class="modal fade" id="myModal">
				    <div class="modal-dialog modal-sm">
				      <div class="modal-content">
				   
				        <!-- 模态框头部 -->
				        <div class="modal-header">
				          <h4 class="modal-title">举报此房源</h4>
				          <button type="button" class="close" data-dismiss="modal">&times;</button>
				        </div>
				   
				        <!-- 模态框主体 -->
				        <div class="modal-body">
				        <form action="DoReport" method="post">
		                    <div class="form-group">
		                        <input type="text" class="form-control" id="reason" placeholder="请填写举报理由" onblur="checkReport()">
		                    </div>
		                    <div class="form-group">
		                        <input type="text" class="form-control" id="r_contact" placeholder="请填写您的联系方式" onblur="checkReport()">
		                    </div>
		                    <div class="form-group form_btn" style="float:right">
		                        <button type="submit" class="btn btn-primary btn-sm">提交</button>
		                    </div>
		                </form>
		                <div id="tips">
		                
		                </div>
				        </div>
				        <!-- 模态框底部 -->
				        <div class="modal-footer">
				          <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
				        </div>
				   
				      </div>
				    </div>
				  </div>
                
    			
                <div class="adding-form">
                    <div class="addfor-bg">
                        <div class="add-content">
                            <h3>有疑问?</h3>
                            <p>写点什么问题</p>
                            <ul class="contact-for-add">
                                <li><img src="images/icon/phone.png" alt="">+123-456-7890</li>
                                <li><img src="images/icon/gmail.png" alt="">info@yourcompany.com</li>
                            </ul>
                        </div>
                    </div>
                </div><!-- adverestment start here-->
            </div><!-- booking form end here -->
        </div>
    </div>
</section>

<footer class="footer-area">
    <div class="container">
        <div class="row">
            <!-- footer left -->
            <div class="col-md-3 col-sm-6">
                <div class="single-footer">
                    <div class="footer-title">
                        <h3><a href="#"><img src="images/logo.png" alt=""></a></h3>
                    </div>
                    <div class="footer-left">
                        <div class="footer-logo">
                            <p>可以写点什么</p>
                        </div>
                        <ul class="footer-contact">
                            <li><img class="map" src="images/icon/map.png" alt="">观日路2号</li>
                            <li><img class="map" src="images/icon/phone.png" alt="">996</li>
                            <li><img class="map" src="images/icon/gmail.png" alt="">@fjut</li>
                        </ul>
                    </div>
                </div>
            </div> <!-- footer left -->

            <!-- footer destination -->
            <div class="col-md-2 col-sm-6">
                <div class="single-footer">
                    <div class="footer-title">
                        <h3>链接</h3>
                    </div>
                    <ul class="list-unstyled">
                        <li><a href="" title="">1@@@</a></li>
                        <li><a href="" title="">2@@@</a></li>
                        <li><a href="" title="">3@@@</a></li>
                        <li><a href="" title="">4@@@</a></li>
                        <li><a href="" title="">5@@@</a></li>
                        <li><a href="" title="">6@@@</a></li>
                        <li><a href="" title="">7@@@</a></li>
                    </ul>
                </div>
            </div>  <!-- footer destination -->

            <div class="col-md-4 col-sm-6">
                <div class="single-footer">
                    <div class="single-recent-post">
                        <div class="footer-title">
                            <h3>为您推荐</h3>
                        </div>
                        <ul class="recent-post">
                            <li>
                                <a href="#">
                                    <div class="post-thum">
                                        <img src="images/blog/f4.jpg" alt="" class="img-rounded">
                                    </div>
                                    <div class="post-content">
                                        <p>A Clean Website Gives More Experience To The Visitors.
                                        </p>
                                        <span>12 July, 2019</span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <div class="post-thum">
                                        <img src="images/blog/f5.jpg" alt="" class="img-rounded">
                                    </div>
                                    <div class="post-content">
                                        <p>A Clean Website Gives More Experience To The Visitors.
                                        </p>
                                        <span>12 July, 2019</span>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <div class="post-thum">
                                        <img src="images/blog/f6.jpg" alt="" class="img-rounded">
                                    </div>
                                    <div class="post-content">
                                        <p>A Clean Website Gives More Experience To The Visitors.
                                        </p>
                                        <span>12 July, 2019</span>
                                    </div>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>  <!-- footer latest news -->

            <!-- footer contact -->
            <div class="col-md-3 col-sm-6 f-phone-responsive">
                <div class="single-footer">
                    <div class="footer-title">
                        <h3>留言给我们</h3>
                    </div>
                    <div class="footer-contact-form">
                        <form action="#">
                            <ul class="footer-form-element">
                                <li>
                                    <input type="text" name="email" id="email" placeholder="" value="邮件地址" onblur="if(this.value==''){this.value='Email Address'}" onfocus="if(this.value=='Email Address'){this.value=''}">
                                </li>
                                <li class="text_area">
                                    <textarea name="message" id="message" cols="30" rows="10" placeholder="说点什么..."></textarea>
                                    <button type="submit">提交</button>
                                </li>
                                <li>

                                </li>
                            </ul>
                        </form>
                    </div>
                    <div class="footer-social-media">
                        <div class="social-footer-title">
                            <h3>订阅我们</h3>
                        </div>
                        <ul class="footer-social-link">
                            <li class="facebook"><a href="#"><i class="fa fa-facebook"></i></a></li>
                            <li class="twitter"><a href="#"><i class="fa fa-twitter"></i></a></li>
                            <li class="linkedin"><a href="#"><i class="fa fa-linkedin"></i></a></li>
                            <li class="gplus"><a href="#"><i class="fa fa-google-plus"></i></a></li>
                            <li class="youtube"><a href="#"><i class="fa fa-youtube-play"></i></a></li>
                        </ul>
                    </div>
                </div>
            </div>  <!-- footer contact -->
        </div>
    </div>
    <div class="footer_bottom_wrapper">
        <div class="container">
            <div class="row">
                <div class="col-12 col-sm-12 col-md-12">
                    <div class="copy_right_wrapper">
                        <div class="copyright">
                            <p>Copyright &copy; 2019.Company name All rights reserved.<a target="_blank" href="#"></a></p>
                        </div>
                        <ul class="payicon pull-right">
                            <li>我们支持 : </li>
                            <li><a href="" title=""><img src="images/payicon01.png" alt=""></a></li>
                            <li><a href="" title=""><img src="images/payicon02.png" alt=""></a></li>
                            <li><a href="" title=""><img src="images/payicon03.png" alt=""></a></li>
                            <li><a href="" title=""><img src="images/payicon04.png" alt=""></a></li>
                            <li><a href="" title=""><img src="images/payicon05.png" alt=""></a></li>
                            <li><a href="" title=""><img src="images/payicon06.png" alt=""></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer> <!-- end footer -->

<div class="to-top pos-rtive">
    <a href="#"><i class = "fa fa-angle-up"></i></a>
</div><!-- Scroll to top-->
    <!-- ============================
            JavaScript Files
    ============================= -->
    
    <!-- jquery latest version -->
    <script src="js/vendor/jquery-3.2.0.min.js"></script>
    <script src="js/vendor/modernizr-2.8.3.min.js"></script>  
    <!-- bootstrap js -->
    <script src="js/bootstrap.min.js"></script>
    <!-- owl.carousel js -->
    <script src="js/owl.carousel.min.js"></script>
    <!-- slick js -->
    <script src="js/slick.min.js"></script>
    <!-- meanmenu js -->
    <script src="js/jquery.meanmenu.min.js"></script>
    <!-- jquery-ui js -->
    <script src="js/jquery-ui.min.js"></script>
    <!-- wow js -->
    <script src="js/wow.min.js"></script>
    <!-- counter js -->
    <script src="js/jquery.counterup.min.js"></script>
    <!-- Countdown js -->
    <script src="js/jquery.countdown.min.js"></script>
    <!-- waypoints js -->
    <script src="js/jquery.waypoints.min.js"></script>
    <!-- Isotope js -->
    <script src="js/isotope.pkgd.min.js"></script>
    <!-- magnific js -->
    <script src="js/jquery.magnific-popup.min.js"></script>
    <!-- Image loaded js -->
    <script src="js/imagesloaded.pkgd.min.js"></script>
    <!-- chossen js -->
    <script src="js/chosen.jquery.min.js"></script>  
    <!-- Jquery plugin -->
    <script src="js/plugins.js"></script>
    <!-- select2 js plugin -->
    <script src="js/select2.min.js"></script>    
    <script src="js/colors.js"></script>
    <!-- Jquery plugin -->
    <script src="js/jquery-customselect.js"></script>
    <!-- google map api -->
    <script src="http://ditu.google.cn/maps/api/js?key=AIzaSyCIW0B_E3g-Yg533xy3yF0WHThi-mFvSNQ "></script>
    <!-- map js -->
    <script src="js/google-map.js"></script>
    <!-- main js -->    
    <script src="js/custom.js"></script>
</body>
</html>

