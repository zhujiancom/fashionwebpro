<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>backend/">

		<title>My JSP 'styleAdd.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	    <link href="<%=basePath %>comm_style/base.css" rel="stylesheet" type="text/css" />
   	 	<script type="text/javascript" 
    		src="<%=basePath %>comm_script/jquery-1.6.2.min.js">
    	</script>
    	<script type="text/javascript" 
    		src="<%=basePath%>comm_script/dialog/lhgdialog.min.js">
	    </script>
	    <script type="text/javascript" 
	    		src="<%=basePath%>comm_script/jquery-plugin/jquery-ui-1.8.16.custom.min.js">
	    </script>
	    <script type="text/javascript" 
	    		src="<%=basePath %>comm_script/base.js">
	    </script>

    

	</head>

	<body>
		<div id="wrapper">
			<form id="fm" action="style_save.action" method="post" target="main" enctype="multipart/form-data">
				<div class="rowElem">
					<label>
						Style English Name:
					</label>
					<input type="text" name="style.styleEname" />
				</div>
				<div class="rowElem">
					<label>Style Chinese Name:</label>
					<input type="text" name="style.styleCname" />
				</div>
				<div class="rowElem">
					<label>Style Image Upload:</label>
					<input type="file" name="imageFile" />
				</div>
				<br />
			<br />
			<div class="rowElem"
				style="float: right; position: relative; right: 20px;">
				<input type="submit" class="big_submitButton_class" value="保存/更新" />
			</div>
			</form>
		</div>
	</body>
</html>
