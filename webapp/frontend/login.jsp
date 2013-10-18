<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>frontend/">
    
    <link href="<%=basePath %>comm_script/bootstrap/css/bootstrap.css" rel="stylesheet">
    <title>login page</title>
    
    <script type="text/javascript" 
    		src="<%=basePath %>comm_script/jquery-1.7.2.min.js">
    </script>
    
  </head>
  
  <body>
    <!-- form itself -->
	<form id="test-form" class="white-popup-block mfp-hide">
		<h1>Form</h1>
		<fieldset style="border:0;">
			<p>Lightbox has an option to automatically focus on the first input. It's strongly recommended to use <code>inline</code> popup type for lightboxes with form instead of <code>ajax</code> (to keep entered data if the user accidentally refreshed the page).</p>
			<ol>
				<li>
					<label for="name">Name</label>
					<input id="name" name="name" placeholder="Name" required="" type="text">
				</li>
				<li>
					<label for="email">Email</label>
					<input id="email" name="email" placeholder="example@domain.com" required="" type="email">
				</li>
				<li>
					<label for="phone">Phone</label>
					<input id="phone" name="phone" placeholder="Eg. +447500000000" required="" type="tel">
				</li>
				<li>
					<label for="textarea">Textarea</label><br>
					<textarea id="textarea">Try to resize me to see how popup CSS-based resizing works.</textarea>
				</li>
			</ol>
		</fieldset>
	</form>	
  </body>
</html>
