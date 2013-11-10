<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String referer = request.getHeader("Referer");
%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>frontend/">
    
    <link href="<%=basePath %>comm_script/bootstrap/css/bootstrap_custom_1.css" rel="stylesheet">
    <script src="<%=basePath %>comm_script/jquery-1.9.1.min.js" type="application/javascript"></script>
    <title>login page</title>
    <style type="text/css">
   		body {TEXT-ALIGN: center;}
    	#loginbox{
    		MARGIN-RIGHT: auto; MARGIN-LEFT: auto;
    		width:460px;
    		margin-top:20px;
    		padding:10px;
    	}
    	ol li{
    		list-style: none;
    	}
    </style>
  </head>
  
  <body>
  	<jsp:include page="header.jsp"></jsp:include>
  	<div id="loginbox">
  		<!-- form itself -->
		<form action="../login" method="post">
			<input type="hidden" name="referer" value="<%=referer %>"/>
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
  	<jsp:include page="footer.jsp"></jsp:include>
  </body>
</html>
