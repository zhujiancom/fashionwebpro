<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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

  </head>
  
  <body  oncontextmenu="return false" onselectstart="return false">
  	<div id="wrapper">
  		 <div id="mainwrapper">
    		<div id="videowrapper">
	        	<div id="osmplayer"></div>
	        	<video src="<s:property value='runwayshowvo.videoUrl'/>" poster="<s:property value='runwayshowvo.posterthumnail'/>"></video>
		 	</div>
		</div>
	</div>
	
  	<script type="text/javascript">
	  $(function() {
	    $("video").osmplayer({
		  playlist:'<%=basePath%>frontend/menus/brand/playlist.xml',
	      width: '80%',
	      height: '500px'
	    });
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
  </body>
</html>
