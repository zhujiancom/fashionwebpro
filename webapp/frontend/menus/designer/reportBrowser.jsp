<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
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
	<link href="<%=basePath %>frontend/css/press.css" rel="stylesheet" />
	<link href="<%=basePath %>frontend/javascript/jquery-plugin/jpages/jPages.css" rel="stylesheet"/>
	<script src="<%=basePath %>comm_script/jquery-1.7.2.min.js" type="application/javascript"></script>
	<script src="<%=basePath %>frontend/javascript/jquery-plugin/imgLiquid/imgLiquid.js" type="application/javascript"></script>
	<script src="<%=basePath %>frontend/javascript/jquery-plugin/jpages/jPages.js" type="application/javascript"></script>
	<style type="text/css">
		.holder {
		  margin: 15px 200px 0;
		}
		
		.holder a {
		  font-size: 12px;
		  cursor: pointer;
		  margin: 0 5px;
		  color: #333;
		}
		
		.holder a:hover {
		  background-color: #222;
		  color: #fff;
		}
		
		.holder a.jp-previous { margin-right: 15px; }
		.holder a.jp-next { margin-left: 15px; }
		
		.holder a.jp-current, a.jp-current:hover {
		  color: #FF4242;
		  font-weight: bold;
		}
		
		.holder a.jp-disabled, a.jp-disabled:hover {
		  color: #bbb;
		}
		
		.holder a.jp-current, a.jp-current:hover,
		.holder a.jp-disabled, a.jp-disabled:hover {
		  cursor: default;
		  background: none;
		}
		
		.holder span { margin: 0 5px; }
		
		#main{
			margin:0;
			padding:0;
		}
		
		 #itemContainer{
			list-style: none;
		}
		
		#contents{
			margin:0;
			padding:0;
		}
  </style>
  </head>
  
  <body  oncontextmenu="return false" onselectstart="return false">
  	<div id="main">
  		 <div id="contents">
            <div class="ulwrapper" class="defaults">
			    <ul id="itemContainer">
			    	<s:iterator value="reportvolist" var="reportvo">
	                	<li>
	                		<div class="item">
	                			<s:if test="#reportvo.thumbnail != null">
	                				<div class="imgLiquidFill imgLuid pic">
					                	<img src="<%=basePath %><s:property value='#reportvo.thumbnail'/>" alt="<s:property value='#reportvo.thumbnail'/>"/>
					                </div>
	                			</s:if>
				            	<div class="preview">
				                	<p class="pretitle"><a href="report_showDetail.action?report.reportid=<s:property value='#reportvo.id'/>"><s:property value='#reportvo.title'/></a></p>
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
		      <!-- navigation holder -->
     			<div class="holder"></div>
			</div>
	    </div>
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
