<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML>
<html>
  <head>
	<link href="<%=basePath%>frontend/css/video.css" rel="stylesheet" />
	<link href="<%=basePath%>frontend/javascript/jquery-plugin/osmplayer/osmplayer-2.x/jquery-ui/ui-darkness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
	<link href="<%=basePath%>frontend/javascript/jquery-plugin/osmplayer/osmplayer-2.x/templates/default/css/osmplayer_default.css" rel="stylesheet"/>
	
	<script src="<%=basePath%>comm_script/jquery-1.7.2.min.js" type="application/javascript"></script>
	<script type="text/javascript" 
    		src="<%=basePath%>comm_script/jquery-plugin/jquery-ui-1.8.16.custom.min.js">
    </script>
	<script src="<%=basePath%>frontend/javascript/jquery-plugin/osmplayer/osmplayer-2.x/bin/osmplayer.compressed.js" type="application/javascript"></script>
	<script src="<%=basePath%>frontend/javascript/jquery-plugin/osmplayer/osmplayer-2.x/templates/default/osmplayer.default.js" type="application/javascript"></script>
	<script src="<%=basePath%>frontend/javascript/jquery-plugin/osmplayer/osmplayer-2.x/minplayer/src/minplayer.players.flash.js" type="application/javascript"></script>
	<script src="<%=basePath%>frontend/javascript/jquery-plugin/imgLiquid/imgLiquid.js" type="application/javascript"></script>
  </head>
  
  <body oncontextmenu="return false" onselectstart="return false">
  	<div >
  		 <div id="mainwrapper">
    		<div id="videowrapper">
	        	<div id="osmplayer"></div>
        		<video src="<s:property value='interviewvo.videoUrl'/>" poster="<s:property value='interviewvo.posterthumnail'/>"></video>
    		</div>
		</div>
  	</div>
  	<script type="text/javascript">
	  $(function() {
	    $("video").osmplayer({
		  playlist:'<%=basePath%>frontend/menus/designer/playlist.xml',
	      width: '80%',
	      height: '500px'
	    });
	  });
	</script>
  </body>
</html>
