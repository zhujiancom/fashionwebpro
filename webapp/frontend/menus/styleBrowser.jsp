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
    
	
	<link href="css/stylebrowser.css" rel="stylesheet" />
	<link href="javascript/jquery-plugin/jpages/jPages.css" rel="stylesheet"/>
	<script src="javascript/jquery-1.7.2.min.js" type="application/javascript"></script>
	<script src="javascript/jquery-plugin/jpages/jPages.js" type="application/javascript"></script>
	
	<script type="text/javascript">
		$(document).ready(function() {
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
	<style type="text/css">
		.holder {
		  margin: 15px 100px;
		  position:absolute;
		  top:300px;
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
	</style>
  </head>
  
  <body  oncontextmenu="return false" onselectstart="return false">
  	<div id="wrapper">
  		<jsp:include page="/frontend/header.jsp"></jsp:include>
		<div id="mainwrapper">
	    	<div id="ulwrapper" class="ulwrapper defaults">
	            <ul id="itemContainer">
	                <s:iterator value="stylelist" var="style">
	                	<li class="contentstyle">
		                	<table class="tablestyle">
		                    	<tr class="imgstyle"><td><img src="<%=basePath %><s:property value='#style.style.styleimg'/>" /></td></tr>
		                        <tr class="namestyle"><td><a href="brand_loadBrandByStyle.action?styleobj.styleid=<s:property value='#style.style.styleid'/>"><s:property value="#style.title"/></a></td></tr>
		                    </table>
		                </li>
	                </s:iterator>
	            </ul>
	            <div class="holder"></div>
	        </div>
    	</div>
    	<jsp:include page="/frontend/footer.jsp"></jsp:include>
  	</div>
  </body>
</html>
