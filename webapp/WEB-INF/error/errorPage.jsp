<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>errorPage</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		#warpper{
			position: relative;
			left:0;
			top:0;
			width:1000px;
			margin:0 auto;
			padding:5px 0 0 0;
			clear:both;
		}
	</style>
  </head>
  
  <body>
    	<div id="warpper">
			<img src="<%=basePath %>comm_images/404.jpg"/>    	
	    </div>
  </body>
</html>
