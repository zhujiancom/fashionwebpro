<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>菜单页面</title>
    
	<link href="<%=basePath %>backend/css/admin.css" type="text/css" rel="stylesheet"/>
	<link href="<%=basePath %>comm_script/jquery-plugin/asynmenu/asynmenu.css" type="text/css" rel="stylesheet"/>
	
	<script type="text/javascript"
			src="<%=basePath%>comm_script/jquery-1.6.2.min.js">
	</script>
	<script type="text/javascript"
			src="<%=basePath%>comm_script/jquery-plugin/asynmenu/asynmenu.js">
	</script>
	<script>
		$(function(){
			$("#asynmenu").menulist({
				url:'sysmodule_moduleList.action',
				dataType:'json',
				userId:'${login_user_session.userId}',
				initParentId:0,
				basepath:'<%=basePath%>'
			});
		});
	</script>
	
  </head>
  
  <body>
	<div id="asynmenu"></div>
  </body>
</html>
