<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>frontend/">
    
    <title><s:text name="account.tab.title"/></title>
    <link href="<%=basePath %>comm_style/base.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="<%=basePath %>comm_script/jqtransform/jqtransform.css" type="text/css"></link>
	<link href="<%=basePath %>comm_style/inputStyle.css" rel="stylesheet" type="text/css" />
    <script src="<%=basePath %>comm_script/jquery-1.7.2.min.js" type="application/javascript"></script>
    <script src="<%=basePath %>comm_script/base.js" type="application/javascript"></script>
    <script type="text/javascript"
			src="<%=basePath%>comm_script/dialog/lhgdialog.min.js">
	</script>
    
    <style type="text/css">
    	#mainwrapper{
    		position:relative;
    		top:20px;
    		left:0;
    		height:600px;
    		width:880px;
    		margin-bottom:50px;
    		padding:10px;
    		font-family: Arial,"Microsoft YaHei","微软雅黑","Verdana",sans-serif,STXihei,"华文细黑";
    	}
    	.rowElem{
    		margin:5px;
    		postion:relative;
    		top:5px;
    		float:left;
    		width:700px;
    	}
    	
    	.rowElem input{
    	}
    	
    	#formPanel{
    		float:left;
    		width:720px;
    		margin:0;
    		padding:10px;
    		overflow:auto;
    	}
    	#formPanel h1{
    		margin-bottom:20px;
    		font-family:"ModernNAP-Text",Georgia,"Times New Roman", Times,serif;
    	}
    	
    	#iamgePanel{
    		float:right;
    	}
    	
    	#imagePanel img{
    		width:267px;
    		height:417px;
    	}
    	
    	#tip{
    		font-family: Arial,"Microsoft YaHei","微软雅黑","Verdana",sans-serif,STXihei,"华文细黑";
    		padding:10px;
    		line-height:20px;
    		font-size:1.2em;
    		font-weight: bold;
    		font-style:italic;
    		color:#ff0000;	
    	}
    </style>
    
  </head>
  
  <body>
    <div id="wrapper">
    	<jsp:include page="/frontend/header.jsp"></jsp:include>
		<div id="mainwrapper">
			<div id="formPanel">
				<h1><s:text name="account.registerTitle"/></h1>
				<p style="margin-bottom:15px;"><s:text name="account.adv"/></p>
				<span id="tip">* <s:text name="account.tip"/></span>
				<form id="fm" action="account_registerAccount.action" method="post">
					<div class="rowElem">
			   			<label><s:text name="account.email"/></label>
			   			<input name="account.email"  type="text" class="require unique" rex="^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$|^(\s{0})$" action="account_uniqueAccountCheck.action"/>
			    	</div>
		   	 		<div class="rowElem">
			   			<label><s:text name="account.name"/></label>
			   			<input name="account.accountname" id="accountname"  class="unique require" action="account_uniqueAccountCheck.action" type="text" />
			    	</div>
			    	<div class="rowElem">
			   			<label><s:text name="account.password"/></label>
		   				<input name="account.pswd" id="newpswd" placeholder="<s:text name='account.password.placeholder'/>" rex="\w{6,}" type="password" />
			   		</div>
			   		<div class="rowElem">
		    			<label><s:text name="account.retype.password"/></label>
		    			<input name="confirm" id="confirmpswd" rex="\w{6,}" type="password"/>
		    		</div>
<%--			    	<div class="rowElem">--%>
<%--			   			<label><s:text name="account.career"/></label>--%>
<%--			   			<select name="account.career" referenceKey="CAREER"--%>
<%--								style="width: 116px;">--%>
<%--						</select>--%>
<%--			    	</div>--%>
<%--			    	<div class="rowElem">--%>
<%--			   			<label><s:text name="account.knowapproach"/></label>--%>
<%--			   			<input name="account.knowapproach"  type="text" />--%>
<%--			    	</div>--%>
			    	<div class="rowElem">
						<label><s:text name="account.newsletter"/></label>
						<input type="radio" name="account.isReceiveNewsletter" value="1" checked="checked" />
						<label style="width:50px;line-height:20px;"><s:text name='account.newsletter.yes'/></label>&nbsp;&nbsp;
						<input type="radio" name="account.isReceiveNewsletter" value="0" />
						<label style="width:50px;line-height:20px;"><s:text name='account.newsletter.no'/></label>
					</div>
					<br />
					<div class="rowElem"
						style="float: right; position: relative; right: 20px;">
						<input type="submit" class="big_submitButton_class" value="<s:text name='account.btn.singup'/>" />
					</div>
		    	</form>
			</div>
	   	 	<div id="iamgePanel">
<%--	   	 		<img src="images/registerbg.jpg"/>--%>
	   	 	</div>
	    </div>
	    <jsp:include page="/frontend/footer.jsp"></jsp:include>
    </div>	
    <script type="text/javascript">
		$(function(){
			var projectName = getProjectName();
			$("#confirmpswd").blur(function(){
				var confirmvalue = $("#confirmpswd").val();
				var newvalue = $("#newpswd").val();
				var _this = $(this);
				if(confirmvalue != "" && confirmvalue != newvalue){
					var $wrapper = _this.parent().parent().parent();
					$wrapper.after("<span style='color:red'><img src=/"+projectName+"/comm_images/error.png width=16px height=16px />&nbsp;&nbsp;This password does not match the one you input previously!</span>");
					_this.attr("result",false);
				}
			});
			
			var errorMsg = "${msg}";
			if(errorMsg != ""){
				feedbackInfo(errorMsg,"ERROR");
			}
		});
	</script>
  </body>
</html>
