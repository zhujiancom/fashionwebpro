<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>头页面</title>

		<link href="<%=basePath %>backend/css/admin.css" type="text/css" rel="stylesheet">
	    <script type="text/javascript" src="<%=basePath %>comm_script/jquery-1.6.2.min.js"></script>
	    <script type="text/javascript" src="<%=basePath %>comm_script/base.js"></script>
	    <script type="text/javascript">
    		function modify(){
	    		var win = window.parent.frames['main'];
	    		if(win.frames[2]){
	    			var innerWin = win.frames[2];
	    			openDialog('Change Password','<%=basePath %>backend/modify_password.jsp',650,380,innerWin); 
	    		}else{
					openDialog('Change Password','<%=basePath %>backend/modify_password.jsp',650,380,win);    			
	    		}
	    	}
	    </script>
	</head>

	<body>
		<TABLE cellSpacing=0 cellPadding=0 width="100%"
			background="images/header_bg.jpg" border=0>
			<TR height=56>
				<TD width=260>
					<a href="mains.jsp" target="main"><IMG height=56 src="images/header_left.jpg" width=260 style="border: none;"></a>
				</TD>
				<TD style="FONT-WEIGHT: bold; COLOR: #fff; PADDING-TOP: 20px"
					align="center">
					Current User:<span style="color:#F0E68C"><s:property value="#session.login_user_session.loginId"/></span> &nbsp;&nbsp;
					<A style="COLOR: #fff" href="#" onclick="modify()">Change</A> &nbsp;&nbsp;
					<A style="COLOR: #fff"
						onclick="if (confirm('Sure logout?')) return true; else return false;"
						href="<%=basePath %>backend/user_logout.action" target=_top>Logout</A>
				</TD>
				<TD align=right width=268>
					<IMG height=56 src="images/header_right.jpg" width=268>
				</TD>
			</TR>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
			<TR bgColor=#1c5db6 height=4>
				<TD></TD>
			</TR>
		</TABLE>
	</body>
	<s:debug></s:debug>
</html>
