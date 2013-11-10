<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML >
<html>
  <head>
    <base href="<%=basePath%>frontend/">
    
	<script src="<%=basePath %>comm_script/jquery-1.7.2.min.js" type="application/javascript"></script>
	<script type="text/javascript"
			src="<%=basePath %>ckeditor/ckeditor.js">
	</script>
  </head>
  
  <body>
    <textarea name="legalstatement"><s:property value="footervo.legalstmt"/></textarea>
    <script type="text/javascript">
   		$(document).ready(function() {
   			//ckeditor
			 editor = CKEDITOR.replace("legalstatement",{
				customConfig:"<%=basePath%>/ckeditor/homepage_config.js",
				width:750,
				height:480
			 });
   		});
    </script>
  </body>
</html>
