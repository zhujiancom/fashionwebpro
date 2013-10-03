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
    
	
	<link href="css/pressreport.css" rel="stylesheet" />
	<link href="javascript/jquery-plugin/fotorama/fotorama.css" rel="stylesheet" />
	<script src="javascript/jquery-1.9.1.min.js" type="application/javascript"></script>
	<script src="javascript/jquery-plugin/fotorama/fotorama.js" type="application/javascript"></script>
	<script src="javascript/jquery-plugin/imgLiquid/imgLiquid.js" type="application/javascript"></script>
	<script type="text/javascript"
			src="<%=basePath%>comm_script/dialog/lhgdialog.min.js">
	</script>
	
	<!-- This is for text scroll -->
<script type="text/javascript">
    $(function(){
	    var $comment = $('#comment');//获取评论框
	    $('.up').click(function(){ //向上按钮绑定单击事件
		   if(!$comment.is(":animated")){//判定是否处于<a href='http://www.21edu8.com/pcnet/3d/' target='_blank'><u>动画</u></a>
				$comment.animate({ scrollTop  : "-=50" } , 400);
			}
		});
		$('.down').click(function(){//向下按钮绑定单击事件
		   if(!$comment.is(":animated")){
				$comment.animate({ scrollTop  : "+=50" } , 400);
			}
		});
	});
    </script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$(".imgLiquidFill").imgLiquid();
		});
	</script>
  </head>
  
  <body  oncontextmenu="return false" onselectstart="return false">
  	<div id="wrapper">
  		 <div id="contentwrapper">
			<div id="contenttitle">
		    	<div class="title"><h1><s:property value="specreport.title"/></h1></div>
		    	<div class="otherinfor">
		        	<ul>
		            	<li><s:date name="specreport.report.reportdate" format="yyyy-MM-dd"/></li>
		                <li>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
		            	<li><a href="javascript:history.go(-1);"><s:text name="back" /></a></li>
		            	<li>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
		            	<li><a href="report_showPreItem.action?report.reportid=<s:property value='specnews.report.reportid'/>"><s:text name="pre"/></a></li>
		            	<li>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
		                <li><a href="report_showNextItem.action?report.reportid=<s:property value='specnews.report.reportid'/>"><s:text name="next"/></a></li>
		            </ul>
		        </div>
		        <br />       
		        <!--<hr class="tpsep"  />-->
		    </div>
		    <div class="fotorama" data-width="80%" data-ratio="1.5" data-hash="true" data-max-height="100%" data-nav="thumbs" data-fit="cover" data-allow-full-screen="native" data-thumb-width="72" data-thumb-height="48">
			      <s:iterator value="specreport.images" var="image">
			      	<img src="<%=basePath %><s:property value='#image'/>">
			      </s:iterator>
		    </div>
		    
		    <hr class="tpsep sepsep"/>
		    <div id="content">
		        <div class="msg_caption">
		            <span class="up imgLiquidFill imgLiquid" ><img src="images/up-arrow-circle-hi.png" /></span>
		        </div>
		        <textarea id="comment"><s:property value="specnews.news.contentEn"/></textarea>
		        <div class="msg_caption">
		            <span class="down imgLiquidFill imgLiquid" ><img src="images/down-arrow-circle-hi.png" /></span>
		        </div>
		    </div>
    	</div>
	</div>
  </body>
</html>
