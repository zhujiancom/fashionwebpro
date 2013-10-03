<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'footer.jsp' starting page</title>
    
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="<%=basePath%>frontend/css/footer.css" type="text/css" rel="stylesheet"/>

  </head>
  	<div style="width:895px;height:50px;margin:0 auto;">
  		<hr/>
        <div id="information">
        	<ul>
                <li>Contact us</li>
                <li>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
                <li>Site Map</li>
                <li>&nbsp;&nbsp;|</li>
                <li style="font-size:16px">Legal&nbsp;&nbsp;Statement</li>
                <li>|&nbsp;&nbsp;</li>
                <li>Links</li>
            </ul>
        </div>
        <br />
        <div id="copyright">
        	<span>©&nbsp;&nbsp;2013&nbsp;&nbsp;University&nbsp;&nbsp;of&nbsp;&nbsp;Hong Kong.&nbsp;&nbsp;All&nbsp;&nbsp;rights&nbsp;&nbsp;reserved</span><span>©&nbsp;&nbsp;2013&nbsp;&nbsp;University&nbsp;&nbsp;of&nbsp;&nbsp;Hong&nbsp;&nbsp;Kong</span>
        </div>
  	</div>
