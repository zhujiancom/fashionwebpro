<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>frontend/">
    <link rel="stylesheet" href="css/contact.css" />
    <title>My JSP 'contactus.jsp' starting page</title>

  </head>
  
  <body  oncontextmenu="return false" onselectstart="return false">
  	<div id="wrapper">
	  	<jsp:include page="/frontend/header.jsp"></jsp:include>
		<div id="mainwrapper">
	    	<table class="tablestyle">
	           <tr>
	            	<td class="labelstyle">Contact Us:&nbsp;</td>
	           </tr>
	           <s:iterator value="contactlist" var="contact" >
	           	<tr>
	           		<td class="contentstyle">
	           			<s:property value="#contact.contactName" /><br />
	           			<s:property value="#contact.Tel" /><br />
	           			<s:property value="#contact.email" /><br />
	           			<s:property value="#contact.address" /><br />
	           		</td>
	           	</tr>
	           </s:iterator>
			</table>
	    </div>
	    <jsp:include page="/frontend/footer.jsp"></jsp:include>
    </div>
  </body>
</html>
