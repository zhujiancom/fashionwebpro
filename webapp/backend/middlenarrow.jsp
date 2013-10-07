<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title></title>
    <link rel="stylesheet" href="css/admin.css" type="text/css"></link>
<script type="text/javascript" src="../comm_script/jquery-1.6.2.min.js"></script>
  <script type="text/javascript" src="../comm_script/base.js"></script>

	<script type="text/javascript">
	$(function(){
		$("#hideButton").click(function(evt){
			var evt = evt || window.event; 
			//alert(evt);
			var targetElement = evt.target || evt.srcElement;
			var frame = parent.document.getElementById("framesets");
			if(frame == null) 
			{
				return;
			}
			
			if(frame.cols == "0,10,*")
		    {
				frame.cols="170,10,*";
				targetElement.src="../backend/images/bar_buttonleft.gif";
				targetElement.alt="点击隐藏";
		    }
			else
		    {
				frame.cols="0,10,*";
				targetElement.src="../backend/images/bar_buttonright.gif";
				targetElement.alt="点击展开";

		    }
		});
	});
	</script>

  </head>
  
  <body>
    <table width="10" height="100%" border="0" cellpadding="0" cellspacing="0" background="images/page_system_14.gif">
	    <tr>
	        <td width="10" height="100%" valign="middle" ID="bar_arrow">
	        <img id="hideButton" src="images/bar_buttonleft.gif" width="10" height="57" style="cursor: hand;background-position: center; background-repeat: no-repeat;">
	        </td>
	    </tr>
	</table>
  </body>
</html>
