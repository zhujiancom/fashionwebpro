<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    
<%--    <meta http-equiv="refresh" content="0;url=<%=basePath %>frontend/homepager_loadData.action">--%>
	<meta http-equiv="refresh" content="0;url=index.html">
    <link rel="icon" type="image/png" href="<%=basePath %>favicon.ico" />
    <title>Directing...</title>
  </head>
  
  <body>  
  </body>
</html>
