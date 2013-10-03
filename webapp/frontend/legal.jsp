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
    
	
	<link href="css/contact.css" rel="stylesheet" />

  </head>
  
  <body>
  	<div id="wrapper">
  		<jsp:include page="/frontend/header.jsp"></jsp:include>
  		<div id="mainwrapper">
    	<table class="tablestyle">
           <tr>
            	<td class="labelstyle">LEGAL STATEMENT:&nbsp;</td>
           </tr>
           <tr>
            <td class="contentstyle"><p>Your right to privacy, and the security of your personal information is very important to the British Fashion Council (BFC). The BFC may collect and use your personal data in the following ways:

    Anonymously, to track the pattern of your usage in a single session, or to keep track of your potential purchases online;
    Individually, to maintain your 'logged in' status to a particular section of the site;
    Individually, to send you information about our products and services when you have both provided us with your personal data and consented to our use in this way;
    Individually, to send you information about our partners or sponsors’ products and services when you have both provided us with your personal data and consented to our use in this way.
    Individually, to conduct transactions online using your personal and credit/bank card details.
    The BFC uses cookies and session trackers in URLs to track individual sessions with the website for the purpose of enhancing your user experience. This information is not used to develop a personal profile of you and the log files are regularly purged.
    BFC will never use your personal data in a way that you have not consented to, and will treat all data in compliance with the Data Protection Act.
</p>

            </td>
           
		</table>
    </div>
  	</div>
  </body>
</html>
