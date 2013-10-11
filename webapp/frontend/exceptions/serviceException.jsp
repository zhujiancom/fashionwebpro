<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Error page</title>
	<script src="<%=basePath %>frontend/javascript/jquery-1.7.2.min.js" type="application/javascript"></script>
  	<style type="text/css">
  		#messageContent{
  			padding:10px;
  			position:relative;
  			top:100px;
  			left:35%;
  			width:895px;
  			height:300px;
  		}
  	</style>
  </head>
  
  <body>
  		<jsp:include page="/frontend/header.jsp"></jsp:include>
  		<div id="messageContent">
  			<img src="<%=basePath %>comm_images/error.jpg"/>
  		</div>
    	<s:property value="exception.message"/>
    	<jsp:include page="/frontend/footer.jsp"></jsp:include>
  </body>
</html>
