<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
String basePath = serverPath + path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>frontend/">
    
    <title>Interviews Browser</title>
    <link rel="icon" type="image/png" href="<%=basePath %>favicon.ico" />
    <link rel="stylesheet" href="<%=basePath %>frontend/css/press.css" type="text/css" media="screen" />
    <link href="<%=basePath %>frontend/javascript/jquery-plugin/jpages/jPages.css" rel="stylesheet"/>
	
    <script type="text/javascript" 
    		src="<%=basePath %>comm_script/jquery-1.7.2.min.js">
    </script>
    <script src="<%=basePath %>frontend/javascript/jquery-plugin/imgLiquid/imgLiquid.js" type="application/javascript"></script>
  	<script src="javascript/jquery-plugin/jpages/jPages.js" type="application/javascript"></script>
	
  	<style type="text/css">
		#itemContainer{
			list-style: none;
		}
  	</style>
  </head>
  
  <body>
    <div id="main">
    	<jsp:include page="/frontend/header.jsp"></jsp:include>
    	<div id="contents">
            <div class="ulwrapper" class="defaults">
	            <ul id="itemContainer">
	                <s:iterator value="reportvolist" var="reportvo">
	                	<li>
	                		<div class="item">
				            	<s:if test="#reportvo.thumbnail != null && #reportvo.thumbnail != '' ">
	                				<div class="imgLiquidFill imgLuid pic">
					                	<img src="<%=basePath %><s:property value='#reportvo.thumbnail'/>" alt="<s:property value='#reportvo.thumbnail'/>"/>
					                </div>
	                			</s:if>
				            	<div class="preview">
				                	<p class="pretitle"><a href="<%=basePath %>report/<s:property value='#reportvo.id'/>.html" style="color:#000;"><s:property value='#reportvo.title'/></a></p>
				                	<p class="precontent">
				                		<s:set name="preview" value="#reportvo.preview"/>
				                		<s:if test="%{#preview.length()>180}">
				                			<s:property value="#preview.substring(0,180)+'...'"/>
				                		</s:if>
				                		<s:else>
				                			<s:property value="#preview"/>
				                		</s:else>
				                	</p>
				                </div>
				            </div>
	                	</li>
	                </s:iterator>
	            </ul>
	            <div style="clear:both;"></div>
	            <!-- navigation holder -->
      			<div class="holder"></div>
	        </div>
            
            <!-- End here -->
    	</div>
    	<jsp:include page="/frontend/footer.jsp"></jsp:include>
    </div>
    <script type="application/javascript">
		$(document).ready(function(){
			$(".imgLiquidFill").imgLiquid({
				fill:true,
				horizontalAlign:"center",
				verticalAlign:"top"  
					  
			});

			/* initiate plugin */
    		$("div.holder").jPages({
      			containerID : "itemContainer",
				perPage:8
    		});

    		/* when button is clicked */
    		$("button").click(function(){
     			 /* get given page */
     			 var page = parseInt( $("input").val() );

	     		 /* jump to that page */
	     		 $("div.holder").jPages( page );

   	 		});
		});
	</script>
  </body>
</html>
