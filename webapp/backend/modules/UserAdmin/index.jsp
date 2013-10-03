<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>用户管理</title>
		<link rel="stylesheet" href="<%=basePath %>comm_script/jquery-plugin/flexigrid/css/flexigrid-style.css" type="text/css"></link>
			<link rel="stylesheet" href="<%=basePath %>comm_script/jquery-plugin/flexigrid/css/flexigrid.pack.css" type="text/css"></link>
		<script type="text/javascript"
			src="<%=basePath%>comm_script/jquery-1.6.2.min.js">
</script>
<script type="text/javascript"
			src="<%=basePath%>comm_script/jquery.cookie.js">
</script>
		<script type="text/javascript"
			src="<%=basePath%>comm_script/dialog/lhgdialog.min.js">
</script>
		<script type="text/javascript"
			src="<%=basePath%>comm_script/jquery-plugin/flexigrid/js/flexigrid.js">
</script>
		<script type="text/javascript" src="<%=basePath%>comm_script/base.js">
</script>
<script type="text/javascript"
			src="<%=basePath%>comm_script/jquery-plugin/flexigrid/js/user_grid/userlist.js">
</script>
	<script type="text/javascript">
	$(function(){
		var msg = "${msg}";
		if(msg != ""){
			feedbackInfo(msg);
		}
	});
	</script>

	</head>

	<body>
		<table id="flex1" style="display: none;"></table>
	</body>
</html>
