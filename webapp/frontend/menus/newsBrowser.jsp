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
    
	
	<link href="css/newsbrowser.css" rel="stylesheet" />
	<link href="javascript/jquery-plugin/jpages/jPages.css" rel="stylesheet"/>
	<script src="javascript/jquery-1.7.2.min.js" type="application/javascript"></script>
	<script src="javascript/jquery-plugin/imgLiquid/imgLiquid.js" type="application/javascript"></script>
	<script src="javascript/jquery-plugin/jpages/jPages.js" type="application/javascript"></script>
	<script type="text/javascript"
			src="<%=basePath%>comm_script/dialog/lhgdialog.min.js">
	</script>
	
	
	<script type="text/javascript">
		$(document).ready(function() {
			$(".imgLiquidFill").imgLiquid();

			/* initiate plugin */
    		$("div.holder").jPages({
      			containerID : "itemContainer",
				perPage:5
    		});

    		/* when button is clicked */
    		$("button").click(function(){
     			 /* get given page */
     			 var page = parseInt( $("input").val() );

	     		 /* jump to that page */
	     		 $("div.holder").jPages( page );

   	 		});
			
			var msg = "${msg}";
			if(msg != ""){
				$.dialog.tips(msg);			
			}
		});

		
	</script>
	
	<style type="text/css">
		.holder {
		  margin: 15px 270px 0;
		}
		  
  	</style>
  </head>
  
  <body  oncontextmenu="return false" onselectstart="return false">
  	<div id="wrapper">
  		<jsp:include page="/frontend/header.jsp"></jsp:include>
  		 <div id="main_wrapper">
			<div id="main_lower" class="defaults">
			    <ul id="itemContainer">
			    	<s:iterator value="newslist" var="news">
			    		 <li>
	                    	<table class="tablestyle">
	                        	<tr>
	                            	<td class="newsheadImg commonstyle imgLiquidFill imgLiquid"><img src="<%=basePath %><s:property value='#news.headImg'/>" /></td>
	                                <td class="commonstyle"><a href="news_showDetails.action?news.newsId=<s:property value='#news.news.newsId'/>"><s:property value="#news.title"/></a></td>
	                        	</tr>
	                        </table>
		                  </li>
			    	</s:iterator>
               </ul>      
		      <!-- navigation holder -->
     			<div class="holder"></div>
			</div>
	    </div>
	    <jsp:include page="/frontend/footer.jsp"></jsp:include>
  	</div>
  </body>
</html>
