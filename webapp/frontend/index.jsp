<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <meta http-equiv="refresh" content="0;url=<%=basePath %>frontend/homepager_loadData.action?request_locale=en_US">
    
    <title>My JSP 'frontend/index.jsp' starting page</title>
  </head>
  
  <body>  
  </body>
</html>
