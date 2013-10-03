<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>frontend/">
    
    <title>My JSP 'displayLookbookImage.jsp' starting page</title>
    <script src="javascript/jquery-1.7.2.min.js" type="application/javascript"></script>
	<link rel="stylesheet" href="javascript/jquery-plugin/magnific-popup/magnific-popup.css"/> 
	<script src="javascript/jquery-plugin/magnific-popup/jquery.magnific-popup.js" type="text/javascript"></script> 
	
    <style type="text/css">
    	.white-popup-block {
		    background: none repeat scroll 0 0 #FFFFFF;
		    margin: 40px auto;
		    max-width: 650px;
		    padding: 20px 30px;
		    position: relative;
		    text-align: left;
		}
    </style>
    
  </head>
  
  <body>
     <div class="white-popup-block">
     	<form>
     		<input type="text"/>
     	</form>
     </div>
  </body>
</html>
