<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 
    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html>
    <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name=”renderer” content=”webkit” /> 
    <title>执行结果</title>
    <script type="text/javascript" src="jquery-3.2.1.js"></script>
<script type="text/javascript">
		function show() {
        var fileTag = document.getElementById('file');
        fileTag.onchange = function () {
            var file = fileTag.files[0];
            var fileReader = new FileReader();
            fileReader.onloadend = function () {
                if (fileReader.readyState == fileReader.DONE) {
                    document.getElementById('img').setAttribute('src', fileReader.result);
                }
            };
            fileReader.readAsDataURL(file);
        };
    };
</script>

</head>
<body>
<div>
     <img src="Images/timg.jpg" id="img" style="width:300px;height:300px;"/>
    </div>
    <input type="file" id="file" onclick="show()"/>
</body>

</html>
