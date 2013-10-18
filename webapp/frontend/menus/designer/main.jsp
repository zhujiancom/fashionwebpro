<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>frontend/">
    
    <title>Designer Browser</title>
    <script src="<%=basePath %>comm_script/jquery-1.9.1.min.js" type="text/javascript"></script> 
    <script src="<%=basePath %>frontend/javascript/jquery-plugin/magnific-popup/jquery.magnific-popup.js" type="text/javascript"></script> 
    <style type="text/css">
    	#menu{
    		float:left;
    		width:22%;
    		clear:left;
    	}
    	#main{
    		position:relative;
    		float:right;
    		width:77%;
    	}
    	#contentwrapper{
    		width:895px;
    		position:relative;
    		top:5px;
    	}
    </style>
  </head>
  
  <body  oncontextmenu="return false" onselectstart="return false">
    <div id="wrapper">
  		<jsp:include page="../../header.jsp"></jsp:include>
  		<div id="contentwrapper">
		    <div id="menu">
					<iframe name="menuPanel" id="menuPanel" src="designer_loadMenu.action?designer.designerId=<%=request.getParameter("designerId") %>" frameborder="0" width="180px" style="padding:0;margin:0" scrolling="no"></iframe>
		    </div>
		    <div id="main">
		    	<iframe name="mainPanel" id="mainPanel" src="designer_showProfile.action?designer.designerId=<%=request.getParameter("designerId") %>" frameborder="0" width="690px" style="padding:0;margin:0;" scrolling="no"></iframe>
		    	<script type="text/javascript">
					function reinitIframe(){
					var menuFrame = document.getElementById("menuPanel");
					var iframe = document.getElementById("mainPanel");
					
					try{
						iframe.height = iframe.contentWindow.document.documentElement.scrollHeight;
						menuFrame.height = iframe.contentWindow.document.documentElement.scrollHeight;
					}catch (ex){}
					}
					window.setInterval("reinitIframe()", 200);
		    	</script>
		    </div>
		</div>
		<jsp:include page="../../footer.jsp"></jsp:include>
	</div>
  </body>
</html>
