<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>backend/">

		<title>My JSP 'designerAdd.jsp' starting page</title>

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
		<script type="text/javascript" >
				$(function(){
					var _fileWrapper = $("#fileWrapper");
					$("#addFile").click(function(event){
						_fileWrapper.append("<tr><td><input type='file' name='imageFiles' /><span class='imgt'><img src='../comm_images/X.gif'/></td></tr>");
					});
					$("span",_fileWrapper).live("click",function(event){
						event.stopPropagation();
						$(this).parent().parent().remove();
					});
				});
		</script>
    

	</head>

	<body>
		<div id="wrapper">
			<form id="fm" action="editorial_update.action" method="post" target="main" enctype="multipart/form-data">
				<input type="hidden" name="editorial.editorialid" value="<s:property value="editorial.editorialid"/>" />
				<input type="hidden" name="editorial.imgs" value="<s:property value='editorial.imgs'/>">
				<div class="rowElem">
					<label>
						Editorial Name(EN):
					</label>
					<input type="text" name="editorial.editorialEname" value="<s:property value='editorial.editorialEname' />" />
				</div>
				<div class="rowElem">
					<label>
						Editorial Name(ZH):
					</label>
					<input type="text" name="editorial.editorialCname" value="<s:property value='editorial.editorialCname' />" />
				</div>
					<div class="rowElem">
					<label>
						Editorial Image:
					</label>
					<input type="button" name="addFile" id="addFile" value="addFile"/>
					<table id="fileWrapper">
						<tr>
							<td>
								<input type="file" name="imageFiles" /><span><img src="../comm_images/X.gif"/></span>
							</td>
						</tr>
					</table>
				</div>
					<div class="rowElem">
					<label>
						Editorial Date:
					</label>
					<input type="text" name="editorial.editdate"  class="datepicker" value="<s:date name='editorial.editdate' format='yyyy-MM-dd'/>" />
				</div>
					<div class="rowElem">
					<label>
						Brand:
					</label>
					<input type="text" name="brand.brandEname"  value="<s:property value='editorial.brand.brandEname' />" />
				</div>
				<br />
			<br />
			<div class="rowElem"
				style="float: right; position: relative; right: 20px;">
				<input type="submit" class="big_submitButton_class" value="Submit" />
			</div>
			</form>
		</div>
	</body>
</html>
