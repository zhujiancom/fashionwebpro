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
		<base href="<%=basePath%>backend/">

		<title>主面板</title>

		<link href="css/admin.css" type="text/css" rel="stylesheet" />
		
    <script type="text/javascript" src="<%=basePath %>comm_script/jquery-1.6.2.min.js"></script>
    <script type="text/javascript"
			src="<%=basePath%>comm_script/dialog/lhgdialog.min.js">
</script>
		<script type="text/javascript" src="<%=basePath %>comm_script/base.js"></script>
		<script type="text/javascript">
			$(function(){
				var msg = "${msg}";
				if(msg != ""){
					$.dialog.tips(msg);
				}
			});
		</script>
		<script type="text/javascript">
function startTime() {
	var today = new Date();
	var h = today.getHours();
	var m = today.getMinutes();
	var s = today.getSeconds();
	m = checkTime(m);
	s = checkTime(s);
	document.getElementById('txt').innerHTML = h + ":" + m + ":" + s;
	t = setTimeout('startTime()', 500);
}

function checkTime(i) {
	if (i < 10) {
		i = "0" + i;
	}
	return i;
}
</script>

<style type="text/css">
	<!--
	.ulClass{
	padding:0 10 0 10;
	margin:0;
}
.ulClass li{
	float:left;
	list-style:none;
	padding:0 10 0 10;
	margin-right:10px;
}
	-->
</style>
	</head>

	<body onload="startTime()">
		<!--<jsp:include page="/WEB-INF/sessionTimeout.jsp"></jsp:include>-->
		<TABLE cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
			<TR>
				<TD bgColor=#b1ceef height=1></TD>
			</TR>
			<TR height=20>
				<TD background=images/shadow_bg.jpg></TD>
			</TR>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="90%" align=center border=0>
			<TR height=100>
				<TD align="center" width=100>
					<IMG height=100 src="images/admin_p.gif" width=90>
				</TD>
				<TD width=60>
					&nbsp;
				</TD>
				<TD>
					<TABLE height=100 cellSpacing=0 cellPadding=0 width="100%" border=0>

						<TR>
							<TD>
								Current Time：
								<div id="txt"></div>
							</TD>
						</TR>
						<TR>
							<TD style="FONT-WEIGHT: bold; FONT-SIZE: 16px">
								<s:property value="#session.user.loginid" />
							</TD>
						</TR>
						<TR>
							<TD>
								Welcome Access FashionArchive Backend Management System!
							</TD>
						</TR>
					</TABLE>
				</TD>
			</TR>
			<TR>
				<TD colSpan=3 height=10></TD>
			</TR>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="95%" align=center border=0>
			<TR height=20>
				<TD></TD>
			</TR>
			<TR height=22>
				<TD style="PADDING-LEFT: 20px; FONT-WEIGHT: bold; COLOR: #ffffff"
					align="center" background=images/title_bg2.jpg>
					Your relevant Information
				</TD>
			</TR>
			<TR bgColor=#ecf4fc height=12>
				<TD></TD>
			</TR>
			<TR height=20>
				<TD></TD>
			</TR>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=2 width="95%" align=center border=0>
			<TR>
				<TD align=right width=100>
					Login ID:
				</TD>
				<TD style="COLOR: #880000">
					<s:property value="#session.login_user_session.loginId" />
				</TD>
			</TR>
			<TR>
				<TD align=right width=100>
					NickName:
				</TD>
				<TD style="COLOR: #880000">
					<s:property value="#session.login_user_session.nickname" />
				</TD>
			</TR>
			<TR>
				<TD align=right width=100>
					Nationality:
				</TD>
				<TD style="COLOR: #880000">
					<s:property value="#session.login_user_session.nationality" />
				</TD>
			</TR>
			<TR>
				<TD align=right width=100>
					RegisterTime:
				</TD>
				<TD style="COLOR: #880000">
					<s:date name="#session.login_user_session.registetime" format="yyyy-MM-dd"/>
				</TD>
			</TR>
			<TR>
				<TD align=right width=100>
					LoginCount:
				</TD>
				<TD style="COLOR: #880000">
					<s:property value="#session.login_user_session.loginCount" /> times
				</TD>
			</TR>
			<TR>
				<TD align=right>
					Login Time:
				</TD>
				<TD style="COLOR: #880000"><s:date name="#session.login_user_session.logintime" format="yyyy-MM-dd hh:mm:ss"/>
			</TR>
			<TR>
			<TR>
				<TD align=right>
					IP Address:
				</TD>
				<TD style="COLOR: #880000"><%=request.getServerName()%></TD>
			</TR>
			<TR>
				<TD align=right>
					Session Timeout:
				</TD>
				<TD style="COLOR: #880000">
					10 mins
				</TD>
			</TR>
			<TR>
				<TD align=right>
					Signature:
				</TD>
				<TD style="COLOR: #880000">
					<s:property value="#session.login_user_session.selfsign"/>
				</TD>
			</TR>
		</TABLE>
	</body>
</html>
