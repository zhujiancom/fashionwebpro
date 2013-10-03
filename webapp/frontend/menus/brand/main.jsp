<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>frontend/">
    
    <title>My JSP 'main.jsp' starting page</title>
    
	<script src="javascript/jquery-1.7.2.min.js" type="application/javascript"></script>
    <style type="text/css">
    	#menu{
    		position:relative;
    		float:left;
    		height:600px;
    		width:22%;
    		border-right:#C0C0C0 solid 2px;
    	}
    	#main{
    		position:relative;
    		float:right;
    		width:77%;
    	}
    </style>
  </head>
  
  <body  oncontextmenu="return false" onselectstart="return false">
    <div id="wrapper">
  		<jsp:include page="/frontend/header.jsp"></jsp:include>
  		<div id="contentwrapper">
		    <div id="menu">
		    	<iframe name="menuPanel" src="menus/brand/leftmenu.jsp?brandId=<%=request.getParameter("brandId") %>" frameborder="0" width="180px" height="600px" scrolling="no"></iframe>
		    </div>
		    <div id="main">
		    	<iframe name="mainPanel" src="brand_showBrandInfo.action?brand.brandid=<%=request.getParameter("brandId") %>" frameborder="0" width="800px" height="1000px""></iframe>
		    </div>
		</div>
		<jsp:include page="/frontend/footer.jsp"></jsp:include>
	</div>
  </body>
</html>
