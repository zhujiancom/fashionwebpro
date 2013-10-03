<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>修改口令</title>
     <link href="<%=basePath %>comm_style/base.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=basePath%>comm_script/jquery-1.6.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>comm_script/dialog/lhgdialog.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>comm_script/base.js"></script>
	<script type="text/javascript">
		$(function(){
			var sessionvalue = "<s:property value='#session.login_user_session.pswd'/>";
			var projectName = getProjectName();
			$("#oldpswd").blur(function(){
				var oldpswd = $("#oldpswd").val();
				var _this = $(this);
				if(oldpswd != "" && sessionvalue != oldpswd){
					var $wrapper = _this.parent().parent().parent();
					$wrapper.after("<span style='color:red'><img src=/"+projectName+"/comm_images/error.png width=16px height=16px />&nbsp;&nbsp;密码不正确!</span>");
					_this.attr("result",false);
				}
			});
			$("#confirmpswd").blur(function(){
				var confirmvalue = $("#confirmpswd").val();
				var newvalue = $("#newpswd").val();
				var _this = $(this);
				if(confirmvalue != "" && confirmvalue != newvalue){
					var $wrapper = _this.parent().parent().parent();
					$wrapper.after("<span style='color:red'><img src=/"+projectName+"/comm_images/error.png width=16px height=16px />&nbsp;&nbsp;请重新确认密码!</span>");
					_this.attr("result",false);
				}
			});
		});
	</script>
	
  </head>
  
  <body>
  	<div style="width:80%;margin:0 auto;padding-top:100px;">
    	<form id="fm" action="<%=basePath %>backend/sysuser_updateUser.action" method="post" target="main">
    		<input type="hidden" name="user.userId" value="<s:property value='#session.login_user_session.userId'/>"/>
    		<div class="rowElem">
	    			<label>旧口令：</label>
	    			<input name="pswd" id="oldpswd" class="require" type="password"/>
	    	</div>
	    	<div class="rowElem">
    			<label>新口令：</label>
    			<input name="user.pswd" id="newpswd" class="require" rex="\w{6,}" type="password"/><strong><font color="blue">&nbsp;&nbsp;*提示格式：6位及以上数字或字母</font></strong>
    		</div>
    		<div class="rowElem">
    			<label>确认新口令：</label>
    			<input name="confirm" id="confirmpswd" class="require" type="password"/>
    		</div>
    		<div class="rowElem">
    			<input type="submit"  value="修改"  />
    		</div>
    	</form>
	</div>
  </body>
</html>
