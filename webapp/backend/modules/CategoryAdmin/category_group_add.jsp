<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>backend/">
    
    <title>category_group_add page</title>
    
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
    		src="<%=basePath %>comm_script/base.js">
    </script>

  </head>
  
  <body>
    <div id="wrapper">
   	 	<form id="fm" action="category_createGroup.action" method="post" target="category_main">
   	 		<div class="rowElem">
	   			<label>Chinese Name：</label>
	   			<input name="group.cname" id="cname" type="text" />
	    	</div>
	    	<div class="rowElem">
	   			<label>English Name：</label>
	   			<input name="group.ename" id="ename" type="text" />
	    	</div>
	    	<div class="rowElem">
	   			<label>Category Group Code：</label>
	   			<input name="group.categoryGroupCd" id="code" type="text" class="unique require" action="category_uniqueGroupKeyCheck.action"/>
	    	</div>
	    	<div class="rowElem">
	   			<label>Status：</label>
   				<input type="radio" name="group.isEnable" id="isEnable" checked value="1" /><label>Enabled</label>&nbsp;&nbsp;
   				<input type="radio" name="group.isEnable" id="isEnable" value="0" /><label>Disabled</label>
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
