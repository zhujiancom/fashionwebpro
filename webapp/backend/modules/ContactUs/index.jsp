<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>backend/">
    
	<link rel="stylesheet" href="<%=basePath %>comm_script/jquery-plugin/flexigrid/css/flexigrid-style.css" type="text/css"/>
    <link rel="stylesheet" href="<%=basePath %>comm_script/jquery-plugin/flexigrid/css/flexigrid.pack.css" type="text/css"/>
    <script type="text/javascript"
			src="<%=basePath%>comm_script/jquery-1.7.2.min.js">
	</script>
	<script type="text/javascript" 
			src="<%=basePath%>comm_script/base.js">
	</script>
	<script type="text/javascript"
			src="<%=basePath%>comm_script/dialog/lhgdialog.min.js">
	</script>
	<script type="text/javascript"
			src="<%=basePath%>backend/modules/ContactUs/data/contacter.js">
	</script>
    
    <script type="text/javascript">
		$(function(){
			var msg = "${msg}";
			if(msg != ""){
				$.dialog.tips(msg);			
			}
		});
	</script>
  </head>
  
  <body>
    <table id="ContactGrid" style="display: none;"></table>
  </body>
</html>
