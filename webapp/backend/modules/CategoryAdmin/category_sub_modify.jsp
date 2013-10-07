<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>

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
   	 	<form id="fm" action="category_updateItem.action" method="post" target="category_main">
   	 		<input type="hidden" name="category.categoryId" value="<s:property value='categoryItem.categoryId'/>"/>
   	 		<div class="rowElem">
	   			<label>Chinese Name：</label>
	   			<input name="category.cname" id="cname" type="text" value="<s:property value='categoryItem.cname'/>" />
	    	</div>
	    	<div class="rowElem">
	   			<label>English Name：</label>
	   			<input name="category.ename" id="ename" type="text" value='<s:property value="categoryItem.ename"/>'  />
	    	</div>
	    	<div class="rowElem">
	   			<label>Category Code：</label>
	   			<input name="category.categoryCd" id="code" type="text" readonly="readonly" value='<s:property value="categoryItem.categoryCd"/>' />
	    	</div>
	    	<div class="rowElem">
	   			<label>Category Group Name：</label>
	   			<input name="categoryGroup" type="text" readonly="readonly" value='<s:property value="categoryItem.parent.ename"/>' />
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
