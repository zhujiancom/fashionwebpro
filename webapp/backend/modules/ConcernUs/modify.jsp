<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>backend/">

		<title>My JSP 'designerAdd.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="<%=basePath %>comm_style/base.css" rel="stylesheet" type="text/css" />
	    <script type="text/javascript" 
	    		src="<%=basePath %>comm_script/jquery-1.6.2.min.js">
	    </script>
	    <script type="text/javascript" 
	    		src="<%=basePath%>comm_script/dialog/lhgdialog.min.js">
	    </script>
	    <script type="text/javascript" 
	    		src="<%=basePath%>comm_script/jquery-plugin/jquery-ui-1.8.16.custom.min.js">
	    </script>
	    <script type="text/javascript" 
	    		src="<%=basePath %>comm_script/base.js">
	    </script>

    

	</head>

	<body>
		<div id="wrapper">
			<form id="fm" action="contact_update.action" method="post" target="main">
				<input type="hidden" name="contactus.contactId" value="<s:property value="contactus.contactId"/>" />
				<div class="rowElem">
					<label>
						Contacter Name:
					</label>
					<input type="text" name="contactus.contactName" value="<s:property value='contactus.contactName' />" />
				</div>
				<div class="rowElem">
					<label>
						Tel:
					</label>
					<input type="text" name="contactus.Tel" value="<s:property value='contactus.Tel' />" />
				</div>
				
				<div class="rowElem">
					<label>Email:</label>
					<input type="text" name="contactus.email" value="<s:property value='contactus.email' />" />
				</div>
				
				<div class="rowElem">
					<label>Address:</label>
					<input type="text" name="contactus.address" value="<s:property value='contactus.address' />" />
				</div>
				<br />
			<br />
			<div class="rowElem"
				style="float: right; position: relative; right: 20px;">
				<input type="submit" class="big_submitButton_class" value="SUBMIT" />
			</div>
			</form>
		</div>
	</body>
</html>
