<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>frontend/">
    
    <title><s:text name="search"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
	<style type="text/css">
		html{
			margin:0;
			padding:0;
		}
		*{
			margin:0;
			padding:0;
		}
		body{
			margin:0;
			padding:0;
		}
		#search{
			width:500px;
			height:400px;
			padding:5px;
			margin:0 auto;
		}
		
		.searchfield{
			padding:10px;
			border-color:#666;
			font-family:Arial,"Microsoft YaHei","微软雅黑","Verdana",sans-serif,STXihei,"华文细黑";
		}
	</style>
	<link href="<%=basePath %>comm_script/bootstrap/css/bootstrap_custom_1.css" rel="stylesheet" type="text/css" media="screen">
<%--    <link href="<%=basePath %>frontend/css/header.css" type="text/css" rel="stylesheet"/>--%>
<%--    <link href="<%=basePath%>frontend/css/footer.css" type="text/css" rel="stylesheet"/>--%>
	<script src="<%=basePath %>comm_script/jquery-1.7.2.min.js" type="application/javascript"></script>
  </head>
  
  <body>
  	<div id="wrapper">
  		<jsp:include page="/frontend/header.jsp"></jsp:include>
		 <div id="search">
		 		<fieldset class="searchfield">
			      <legend><s:text name="search"/></legend>
			      <form id="searchForm" action="search_headSearch.action" method="get">
				       <input type="text" name="keywords" placeholder="KEY WORDS" />
				       <input type="submit" class="btn btn-large" value="<s:text name='search'/>"/>
				       <br />
				       <br />
				       <input type="radio" name="searchType" checked="checked" value="designer" /><label><s:text name="designer"/></label>
				       <input type="radio" name="searchType" value="brand"/><label><s:text name="brand"/></label>
			  		</form>
			  	</fieldset>
		  </div>
    	<jsp:include page="/frontend/footer.jsp"></jsp:include>
  	</div>
  </body>
</html>
