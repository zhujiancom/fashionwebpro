<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTMLN">
<html>
  <head>
    <base href="<%=basePath%>frontend/">
    
    <title>Brands Browser</title>
    
	<link rel="icon" type="image/png" href="<%=basePath %>favicon.ico" />
	<link href="css/brandbrowser.css" rel="stylesheet" />
	<link href="javascript/jquery-plugin/jpages/jPages.css" rel="stylesheet"/>
	<script src="javascript/jquery-1.7.2.min.js" type="application/javascript"></script>
	<script src="javascript/jquery-plugin/imgLiquid/imgLiquid.js" type="application/javascript"></script>
	<script src="javascript/jquery-plugin/jpages/jPages.js" type="application/javascript"></script>
	<script src="javascript/jquery-plugin/listnav/jquery.listnav-2.1.js" type="application/javascript"></script>
	
	<style type="text/css">
		 .holder {
			margin: 15px 0;
			text-align: center;
		}
		.listNav { margin:0 0 10px;position:relative;left:200px; }
		.ln-letters { overflow:hidden; }
		.ln-letters a { font-size:0.9em; display:block; float:left; padding:2px 6px; border:1px solid silver; border-right:none; text-decoration:none; }
		.ln-letters a.ln-last { border-right:1px solid silver; }
		.ln-letters a:hover,
		.ln-letters a.ln-selected { background-color:#eaeaea; }
		.ln-letters a.ln-disabled { color:#ccc; }
		.ln-letter-count { text-align:center; font-size:0.8em; line-height:1; margin-bottom:3px; color:#336699; }

  	</style>
  </head>
  
  <body oncontextmenu="return false" onselectstart="return false">
  	<div id="wrapper">
  		<jsp:include page="/frontend/header.jsp"></jsp:include>
		<div id="mainwrapper">
	    	<div class="ulwrapper" class="defaults">
	    		<div id="itemContainer-nav" class="listNav"></div>
	            <ul id="itemContainer">
	                <s:iterator value="brandlist" var="brandvo" status="serial">
	                	<li class="contentstyle"><p><a href="../brand/main/<s:property value='#brandvo.id'/>.html"><s:property value="#brandvo.name"/></a></p></li>
	                </s:iterator>
	            </ul>
	            <div style="clear:both;"></div>
	            <!-- navigation holder -->
      			<div class="holder"></div>
	        </div>
	    </div>
	    <div style="clear:both;"></div>
	    <jsp:include page="/frontend/footer.jsp"></jsp:include>
  	</div>
  	<script type="text/javascript">
		$(document).ready(function() {
			$(".imgLiquidFill").imgLiquid();

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
			$("#itemContainer").listnav();
			
			var msg = "${msg}";
			if(msg != ""){
				$.dialog.tips(msg);			
			}
		});
	</script>
  </body>
</html>
