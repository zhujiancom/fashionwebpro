<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>frontend/">
    
    <title><s:property value="reportvo.title"/></title>
   	<link href="<%=basePath %>comm_style/base.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath %>comm_script/jquery-1.7.2.min.js" type="application/javascript"></script>
	<script type="text/javascript"
			src="<%=basePath %>ckeditor/ckeditor.js">
	</script>

	<style type="text/css">
		*{
			margin:0;
			padding:0;
		}
		html{
			margin:0;
			padding:0;
		}
		#contentwrapper{
			position:relative;
			margin:30px 107px;
			height:700px;
		}
		#contenttitle{
			height:100px;
			width:100%;
		}
		
		.title{
			font-family: Arial,"Microsoft YaHei","微软雅黑","Verdana",sans-serif,STXihei,"华文细黑";
			text-align:center;
			padding:10px;
		}
		.otherinfor{
			height:auto;
			text-align:center;
			font-size:16px;
			font-family: Arial,"Microsoft YaHei","微软雅黑","Verdana",sans-serif,STXihei,"华文细黑";
			padding-left:35%;
		}
		.otherinfor ul li{
			list-style:none;
			float:left;
			display:block;
			color:#808080;
		}
		.otherinfor ul li a:link{
			text-decoration:none;
			color:#808080;
		}
		.otherinfor ul li a:hover{
			text-decoration:underline;
			color:#ffa631;
		}
		.otherinfor ul li a:visited{
			text-decoration:none;
			color:#424c50;
		}
		
		
		.tpsep{
			border:none;
			border-top:3px dotted #CCC;
		}
		
		.fotorama{
			margin-left:15%;
		}
		
		.sepsep{
			margin-top:1%;
			margin-bottom:1%;
		}
		
		#content{
			height:300px;
			width:68%;
			margin:0;
			padding:5px;
			word-wrap:break-word;
			word-break:break-strict;
			font-size:14px;
			font-family: Arial,"Microsoft YaHei","微软雅黑","Verdana",sans-serif,STXihei,"华文细黑";
		}
		
	</style>
  </head>
  
  <body>
    <div id="wrapper">
    	<jsp:include page="/frontend/header.jsp"></jsp:include>
  		<div id="contentwrapper">
  			<div id="contenttitle">
		    	<div class="title"><h1><s:property value="reportvo.title"/></h1></div>
		    	<div class="otherinfor">
		        	<ul>
		            	<li><s:date name="reportvo.report.reportdate" format="yyyy-MM-dd"/></li>
		                <li>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
		            	<li><a href="<%=basePath %>report"><s:text name="back"/></a></li>
		            	<li>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
		            	<li><a href="<%=basePath %>report/pre_<s:property value='reportvo.id'/>.html"><s:text name="pre"/></a></li>
		            	<li>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
		                <li><a href="<%=basePath %>report/next_<s:property value='reportvo.id'/>.html"><s:text name="next"/></a></li>
		            </ul>
		        </div>
		    </div>
		    
		    <hr class="tpsep sepsep"   />
		    <div id="content">
		       <textarea name="reportDetail"><s:property value="reportvo.content"/></textarea>
		    </div>
	  	</div>
	  	<jsp:include page="/frontend/footer.jsp"></jsp:include>
  	</div>
  	<script type="text/javascript">
		var editor = null;
	  $(document).ready(function() {
			//ckeditor
		editor = CKEDITOR.replace("reportDetail",{
			customConfig:"<%=basePath%>/ckeditor/profile_config.js"
		});
	  });
	</script>
  </body>
</html>
