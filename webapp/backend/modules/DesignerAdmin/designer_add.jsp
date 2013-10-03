<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<%@ taglib uri="http://ckeditor.com"  prefix="ckeditor"%>
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
		<script type="text/javascript"
				src="<%=basePath %>ckeditor/ckeditor.js">
		</script>
		<script type="text/javascript"
				src="<%=basePath %>/ckfinder/ckfinder.js">
		</script>
    	<script type="text/javascript">
    	var editor = null;
		$(document).ready(function(){
			editor = CKEDITOR.replaceAll(function(textarea,config){
				config.customConfig="<%=basePath%>/ckeditor/backend_config.js";
				config.on={
						instanceReady:function(ev){
    						this.dataProcessor.writer.setRules('p', {
    	                        indent: false,
    	                        breakBeforeOpen: false,   //<p>之前不加换行
    	                        breakAfterOpen: false,    //<p>之后不加换行
    	                        breakBeforeClose: false,  //</p>之前不加换行
    	                        breakAfterClose: false    //</p>之后不加换行7
    	                    });
    					}
				};
			});
			CKFinder.setupCKEditor(editor, "<%=basePath%>ckfinder/");
		});
    	</script>

	</head>

	<body>
		<div id="wrapper">
			<form id="fmt" action="designer_save.action" method="post" target="main" enctype="multipart/form-data">
				<div class="rowElem">
					<label>
						Designer Name(EN):
					</label>
					<input type="text" name="designer.ename" />
				</div>
				<div class="rowElem">
					<label>
						Designer Name(CH):
					</label>
					<input type="text" name="designer.cname" />
				</div>
				<div class="rowElem">
					<label>
						Designer Image:
					</label>
					<input type="file" name="imageFile">
				</div>
				<br/>
				<div>
					<label>Detail Content(CH):</label>
					<textarea name="designer.detailContentCH" rows="5" cols="80"></textarea>
				</div>
				<br/>
				<div>
					<label>Detail Content(EN):</label>
					<textarea name="designer.detailContentEN"></textarea>
				</div>
				<br />
			<br />
			<div class="rowElem"
				style="float: right; position: relative; right: 20px;">
				<input type="submit" class="big_submitButton_class" value="SUBMIT" />
			</div>
			</form>
		</div>
	</body>
</html>
