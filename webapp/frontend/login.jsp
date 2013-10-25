<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>frontend/">
    
    <link href="<%=basePath %>comm_script/bootstrap/css/bootstrap_custom_1.css" rel="stylesheet">
    <title>login page</title>
    
  </head>
  
  <body>
  	<div id="loginbox">
  		<!-- form itself -->
		<form action="account_login.action" method="post">
			<h1>Login</h1>
			<fieldset style="border:0;">
				<ol>
					<li>
						<label for="name">Name</label>
						<input id="name" name="account.accountname" placeholder="Name" required="true" type="text">
					</li>
					<li>
						<label for="passwd">Password</label>
						<input id="passwd" name="account.pswd" placeholder="Password" pattern="\w{6,}" required="true" type="password">
					</li>
					<li>
						<input type="submit" class="btn btn-small" value="<s:text name='login'/>"/>
						<input type="reset" class="btn btn-small" value="<s:text name='reset'/>"/>
					</li>
				</ol>
			</fieldset>
		</form>	
  	</div>
  </body>
</html>
