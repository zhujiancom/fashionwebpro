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

		<title>Comtemporary Fashion Archive Backend System</title>

		<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	padding:0;
	background-color: #016aa9;
	overflow:hidden;
}

.STYLE1 {
	color: #000000;
	font-size: 12px;
}
-->
</style>
	<link rel="stylesheet" href="../comm_script/dialog/common.css" type="text/css"></link>
	<script type="text/javascript" src="../comm_script/jquery-1.6.2.min.js"></script>
	<script type="text/javascript" src="../comm_script/dialog/lhgdialog.min.js"></script>
	<script type="text/javascript">
		$(function(){
			var s = "${errorMsg}";
			$("#loginid").focus();
			if(s){
				$.dialog.alert(s,function(){
					$("#loginid").empty().focus();
				});
			}
			var faragoMessage = "<s:property value='faragoMessage'/>";
			if(faragoMessage){
				$.dialog.alert(faragoMessage,function(){
					$("#loginid").empty().focus();
				});
			}
		});
	</script>
	
	</head>

	<body>
		<form action="sysuser_login.action" method="post" name="loginform">
			<input type="hidden" name="request_locale" value="en_US"/>
			<table width="100%" height="100%" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td>
						<table width="962" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td height="235" background="images/login_03.gif">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td height="53">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="394" height="53" background="images/login_05.gif">
												&nbsp;
											</td>
											<td width="206" background="images/login_06.gif">
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td width="16%" height="25">
															<div align="right">
																<span class="STYLE1">Username</span>
															</div>
														</td>
														<td width="57%" height="25">
															<div align="center">
																<input type="text" id="loginid" name="user.loginId"
																	style="width: 105px; height: 17px; background-color: #292929; border: solid 1px #7dbad7; font-size: 12px; color: #6cd0ff">
															</div>
														</td>
														<td width="27%" height="25">
															&nbsp;
														</td>
													</tr>
													<tr>
														<td height="25">
															<div align="right">
																<span class="STYLE1">Password</span>
															</div>
														</td>
														<td height="25">
															<div align="center">
																<input type="password" id="pswd" name="user.pswd"
																	style="width: 105px; height: 17px; background-color: #292929; border: solid 1px #7dbad7; font-size: 12px; color: #6cd0ff">
															</div>
														</td>
														<td height="25">
															<div align="left">
																<input type="image" src="images/dl.gif" width="49"
																	height="18" border="0" />
															</div>
														</td>
													</tr>
												</table>
											</td>
											<td width="362" background="images/login_07.gif">
												&nbsp;
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td height="212" background="images/login_08.gif">
									&nbsp;
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
