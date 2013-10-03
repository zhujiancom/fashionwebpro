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
    		border-right:#C0C0C0 solid 2px;
    		height:600px;
    		width:22%;
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
		    	<iframe name="menuPanel" src="menus/designer/leftmenu.jsp?designerId=<%=request.getParameter("designerId") %>" frameborder="0" width="160px" height="540px" scrolling="no"></iframe>
		    </div>
		    <div id="main">
		    	<iframe name="mainPanel" src="designer_showProfile.action?designer.designerId=<%=request.getParameter("designerId") %>" frameborder="0" width="835px" height="1000px" style="margin:0"></iframe>
		    </div>
		</div>
		<jsp:include page="/frontend/footer.jsp"></jsp:include>
	</div>
  </body>
</html>
