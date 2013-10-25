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
    <link href="<%=basePath %>comm_script/bootstrap/css/bootstrap.css" rel="stylesheet">
    <title>Contact US</title>

  </head>
  
  <body>
  	<div id="wrapper">
	  	<jsp:include page="/frontend/header.jsp"></jsp:include>
		<div id="form_wrap">
			<form>
				<h1>Contact form</h1>
				<p> </p>
				<p>Submit your query via the form below and the relevant person will be in touch at the earliest opportunity.</p>
				<p></p>
				<div class="main_wrapper">
					<p class="requirednote">
						<span class="required">*</span>
						required field(s)
					</p>
					<ul>
						<li>
							<label>Name<span class="required">*</span></label>
							<div class="desc">Please enter your name</div>
							<input type="text" class="input_type_text" name="contact.contactName"/>
						</li>
						<li>
							<label>Email<span class="required">*</span></label>
							<div class="desc">Enter the email address you would like us to contact you on.</div>
							<input type="text" class="input_type_text" name="contact.email"/>
						</li>
						<li>
							<label>Your message</label>
							<div class="desc"></div>
							<textarea rows="5" cols="20" name="contact.message"></textarea>
						</li>
					</ul>
					<div class="user_submit">
						<input type="submit" value="Submit"/>
					</div>
				</div>
			</form>
	    </div>
	   <jsp:include page="/frontend/footer.jsp"></jsp:include>
    </div>
  </body>
</html>
