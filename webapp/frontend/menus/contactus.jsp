<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>frontend/">
    <link rel="icon" type="image/png" href="<%=basePath %>favicon.ico" />
    <link rel="stylesheet" href="<%=basePath %>frontend/css/contact.css" />
    <link rel="stylesheet" href="<%=basePath %>comm_style/inputStyle.css" />
    <link href="<%=basePath %>comm_script/bootstrap/css/bootstrap_custom_1.css" rel="stylesheet">
    
    <script src="<%=basePath %>comm_script/jquery-1.7.2.min.js" type="application/javascript"></script>
    <script type="text/javascript" 
			src="<%=basePath%>comm_script/base.js">
	</script>
    <script type="text/javascript"
			src="<%=basePath%>comm_script/dialog/lhgdialog.min.js">
	</script>
  	
    <title>Contact US</title>

  </head>
  
  <body>
  	<div id="wrapper">
	  	<jsp:include page="/frontend/header.jsp"></jsp:include>
		<div id="form_wrap">
			<form action="contact_submitMsg.action" method="post">
				<h1><s:text name="contact.title"/></h1>
				<p> </p>
				<p><s:text name="contact.tips"/></p>
				<p></p>
				<div class="main_wrapper">
					<p class="requirednote">
						<span class="required">*</span>
						<s:text name="contact.required"/>
					</p>
					<ul>
						<li>
							<label><s:text name="contact.name"/><span class="required">*</span></label>
							<div class="desc"><s:text name="contact.tips.typename"/></div>
							<input type="text" class="input_type_text" name="contactus.contactName"/>
						</li>
						<li>
							<label><s:text name="contact.email"/><span class="required">*</span></label>
							<div class="desc"><s:text name="contact.tips.typeemail"/></div>
							<input type="text" class="input_type_text" name="contactus.email"/>
						</li>
						<li>
							<label><s:text name="contact.message"/></label>
							<div class="desc"></div>
							<textarea rows="5" cols="20" name="contactus.message"></textarea>
						</li>
					</ul>
					<div class="user_submit">
						<input type="submit" class="btn btn-large" value="<s:text name='submit'/>"/>
					</div>
				</div>
			</form>
	    </div>
	   <jsp:include page="/frontend/footer.jsp"></jsp:include>
    </div>
    <script type="text/javascript">
    	$(function(){
    		var msg = "${msg}";
			if(msg != ""){
				feedbackInfo(msg,"TIPS");
			}
    	});
    </script>
  </body>
</html>
