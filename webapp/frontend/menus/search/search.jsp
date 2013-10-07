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
    
    <title>My JSP 'profile.jsp' starting page</title>
    
	
	<link href="css/login.css" rel="stylesheet" />
	<script src="javascript/jquery-1.7.2.min.js" type="application/javascript"></script>

  </head>
  
  <body oncontextmenu="return false" onselectstart="return false">
  	<div id="wrapper">
  		<jsp:include page="/frontend/header.jsp"></jsp:include>
		<div id="mainwrapper">
    		<form method="post" action="">
	        	<table class="tablestyle">
	                <tr>
	                	<td><label>Designer:&nbsp;</label></td>
	                    <td><input type="text" name="designer" class="inputstyle" /></td>
	                </tr>
	                <tr>
	                	<td><label>Brand:&nbsp;</label></td>
	                    <td><input type="text" name="brand" class="inputstyle"  /><br /></td>
	                </tr>
	                <tr>
	                	<td><label>Year:&nbsp;</label></td>
	                    <td><input type="text" name="year" class="inputstyle"  /></td>
	                </tr>
	                <tr>
	                	<td class="buttonstyle"><input type="reset" value="RESET" /></td>
	                	<td class="buttonstyle"><input type="submit" value="SUBMIT" /></td>
	                </tr>
	            </table>
	        </form>	
		</div>
    	<jsp:include page="/frontend/footer.jsp"></jsp:include>
  	</div>
  </body>
</html>
