<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>backend/">
    
    <title>Category Admin</title>
	<script type="text/javascript" src="<%=basePath %>comm_script/jquery-1.6.2.min.js"></script>
    <script type="text/javascript"
			src="<%=basePath%>comm_script/dialog/lhgdialog.min.js">
	</script>
    <script type="text/javascript" 
    		src="<%=basePath %>comm_script/base.js">
    </script>
  </head>
  
  	<FRAMESET cols="170,10,*" id="framesets">
	<FRAME name=category_menu src="modules/CategoryAdmin/category_menu.jsp" frameBorder=0 noResize>
	<FRAME src="middlenarrow.jsp" name="middlenarrow" scrolling="No" noresize="noresize"
			id="middlenarrow" frameBorder=0 />
	<FRAME name="category_main" src="modules/CategoryAdmin/category_main.jsp" frameBorder=0 noResize="noresize" scrolling=auto>
	</FRAMESET>
	<noframes>
	</noframes>
</html>
