<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>frontend/">
    
    <title>Add User Information</title>
	<link href="<%=basePath %>comm_script/jqtransform/jqtransform.css" rel="stylesheet" type="text/css" />
	<link href="<%=basePath %>comm_style/inputStyle.css" rel="stylesheet" type="text/css" />
    <script src="javascript/jquery-1.7.2.min.js" type="application/javascript"></script>
    <script type="text/javascript" 
    		src="<%=basePath %>comm_script/base.js">
    </script>
    
    <script type="text/javascript">
		$(function(){
			var projectName = getProjectName();
			$("#confirmpswd").blur(function(){
				var confirmvalue = $("#confirmpswd").val();
				var newvalue = $("#newpswd").val();
				var _this = $(this);
				if(confirmvalue != "" && confirmvalue != newvalue){
					var $wrapper = _this.parent().parent().parent();
					$wrapper.after("<span style='color:red'><img src=/"+projectName+"/comm_images/error.png width=16px height=16px />&nbsp;&nbsp;Please reconfirm password!</span>");
					_this.attr("result",false);
				}
			});
		});
	</script>
  </head>
  
  <body>
    <div id="wrapper">
    	<jsp:include page="/frontend/header.jsp"></jsp:include>
		<div id="mainwrapper">
	   	 	<form id="fm" action="account_registerAccount.action" method="post" enctype="multipart/form-data">
	   	 		<div class="rowElem">
		   			<label>Accountname:&nbsp;</label>
		   			<input name="account.accountname" id="accountname"  class="unique require" action="account_uniqueAccountCheck.action" type="text" />
		    	</div>
		    	<div class="rowElem">
		   			<label>Password：&nbsp;</label>
	   				<input name="account.pswd" id="newpswd" rex="\w{6,}" type="password" /><strong><font color="blue">&nbsp;&nbsp;*提示格式：6位及以上数字或字母</font></strong>
		   		</div>
		   		<div class="rowElem">
	    			<label>Retype Password：&nbsp;</label>
	    			<input name="confirm" id="confirmpswd" rex="\w{6,}" type="password"/>
	    		</div>
		    	<div class="rowElem">
		   			<label>Gender:&nbsp; </label>
		   			<select name="account.gender" referenceKey="SEX"
							style="width: 128px;">
					</select>
		    	</div>
		    	<div class="rowElem">
		   			<label>E-Mail：&nbsp;</label>
		   			<input name="account.email"  type="text" rex="^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$|^(\s{0})$"/>
		    	</div>
		    	<div class="rowElem">
		   			<label>Career：&nbsp;</label>
		   			<select name="account.career" referenceKey="CAREER"
							style="width: 128px;">
					</select>
		    	</div>
		    	<div class="rowElem">
		   			<label>Know Approach：&nbsp;</label>
		   			<input name="account.knowapproach"  type="text" />
		    	</div>
		    	<div class="rowElem">
		   			<label>Header Image：&nbsp;</label>
		   			<input name="imageFile"  type="file" />
		    	</div>
		    	<br />
				<br />
				<div class="rowElem"
					style="float: right; position: relative; right: 20px;">
					<input type="submit" class="big_submitButton_class" value="SUBMIT" />
				</div>
	    	</form>
	    </div>
	    <jsp:include page="/frontend/footer.jsp"></jsp:include>
    </div>	
  </body>
</html>
