<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>backend/">
    
    <title>Category Main page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="<%=basePath %>comm_style/base.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="<%=basePath %>comm_script/jquery-plugin/flexigrid/css/flexigrid-style.css" type="text/css"/>
    <link rel="stylesheet" href="<%=basePath %>comm_script/jquery-plugin/flexigrid/css/flexigrid.pack.css" type="text/css"/>
    <script type="text/javascript" src="<%=basePath%>comm_script/jquery-1.6.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>comm_script/dialog/lhgdialog.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>comm_script/base.js"></script>
	<script type="text/javascript" src="<%=basePath%>backend/modules/CategoryAdmin/data/categorydata.js"></script>
	<script type="text/javascript">
		$(function(){
			parent.frames['category_menu'].location.reload();
			var msg = "${msg}";
			if(msg != ""){
				//$.dialog.tips(msg);
				feedbackInfo(msg);
			}
		});
	</script>
  </head>
  
  <body>
	 <table id="categoryGroupGrid" style="display: none;"></table>
  </body>
</html>
