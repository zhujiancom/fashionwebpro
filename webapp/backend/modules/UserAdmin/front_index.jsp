<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>backend/">
    
    <title>Front user Admin</title>
    
     <link rel="stylesheet" href="<%=basePath %>comm_script/jquery-plugin/flexigrid/css/flexigrid-style.css" type="text/css"/>
    <link rel="stylesheet" href="<%=basePath %>comm_script/jquery-plugin/flexigrid/css/flexigrid.pack.css" type="text/css"/>
    
    <script type="text/javascript"
			src="<%=basePath%>comm_script/jquery-1.6.2.min.js">
	</script>
	<script type="text/javascript"
			src="<%=basePath%>comm_script/jquery.cookie.js">
	</script>
	<script type="text/javascript" 
			src="<%=basePath%>comm_script/base.js">
	</script>
	<script type="text/javascript"
			src="<%=basePath%>comm_script/dialog/lhgdialog.min.js">
	</script>
	<script type="text/javascript"
			src="<%=basePath%>comm_script/jquery-plugin/flexigrid/js/flexigrid.js">
	</script>
	<script type="text/javascript"
			src="<%=basePath%>backend/modules/UserAdmin/data/front_userlist.js">
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
  	<table id="accountGrid" style="display: none;"></table>
  </body>
</html>
