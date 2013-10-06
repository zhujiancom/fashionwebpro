<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>frontend/">
    
    <title>My JSP 'profile.jsp' starting page</title>
    
	<script src="<%=basePath %>comm_script/jquery-1.9.1.min.js" type="application/javascript"></script>
	<script type="text/javascript"
			src="<%=basePath %>ckeditor/ckeditor.js">
	</script>
	
	<script type="text/javascript">
		var editor = null;
	  $(document).ready(function() {
			//ckeditor
		editor = CKEDITOR.replace("designerProfile",{
			customConfig:"<%=basePath%>/ckeditor/profile_config.js"
		});
	  });
	</script>
  </head>
  
  <body >
  	<div id="wrapper">
  		<div id="contentwrapper">
  			<textarea name="designerProfile"><s:property value="designervo.profile"/></textarea>
	  	</div>
  	</div>
  </body>
</html>
