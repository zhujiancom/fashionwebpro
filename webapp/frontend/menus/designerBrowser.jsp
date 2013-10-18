<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
String basePath = serverPath + path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>frontend/">
    
    <title>Designers Browser</title>
    
	
	<link href="css/designerbrowser.css" rel="stylesheet" />
	<link href="javascript/jquery-plugin/jpages/jPages.css" rel="stylesheet"/>
	<script src="javascript/jquery-1.7.2.min.js" type="application/javascript"></script>
	<script src="javascript/jquery-plugin/imgLiquid/imgLiquid.js" type="application/javascript"></script>
	<script src="javascript/jquery-plugin/jpages/jPages.js" type="application/javascript"></script>
	<script src="javascript/jquery-plugin/listnav/jquery.listnav-2.1.js" type="application/javascript"></script>
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
			$("#itemContainer").listnav();
			
			var msg = "${msg}";
			if(msg != ""){
				$.dialog.tips(msg);			
			}
		});

		
	</script>
	
	<style type="text/css">
		.holder {
		  margin: 15px 0;
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
		  
		.listNav { margin:0 0 10px; }
		.ln-letters { overflow:hidden; }
		.ln-letters a { font-size:0.9em; display:block; float:left; padding:2px 6px; border:1px solid silver; border-right:none; text-decoration:none; }
		.ln-letters a.ln-last { border-right:1px solid silver; }
		.ln-letters a:hover,
		.ln-letters a.ln-selected { background-color:#eaeaea; }
		.ln-letters a.ln-disabled { color:#ccc; }
		.ln-letter-count { text-align:center; font-size:0.8em; line-height:1; margin-bottom:3px; color:#336699; }

  	</style>
  </head>
  
  <body  oncontextmenu="return false" onselectstart="return false">
  	<div id="wrapper">
  		<jsp:include page="/frontend/header.jsp"></jsp:include>
  		 <div id="main_wrapper">
			<div id="ul_wrapper" class="defaults">
				<div id="itemContainer-nav" class="listNav"></div>
				<!-- item container -->
			      <ul id="itemContainer">
	                <s:iterator value="designers" var="designervo">
	                	<li>
		                    <table class="tablestyle">
		                        <tr>
		                            <td rowspan="3" class="portraite imgLiquidFill imgLiquid" ><img src="<%=basePath %><s:property value='#designervo.designer.imgURL'/>" /></td>
		                            <td colspan="2" class="columnstyle titlestyle"><a href="menus/designer/main.jsp?designerId=<s:property value='#designervo.id'/>"><s:property value="#designervo.name" /></a></td>
		                        </tr>
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
