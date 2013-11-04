<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>backend/">
    
	<link href="<%=basePath %>comm_style/base.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" 
    		src="<%=basePath %>comm_script/jquery-1.7.2.min.js">
   	</script>
   	<script type="text/javascript" 
	    		src="<%=basePath %>comm_script/base.js">
    </script>
   	<script type="text/javascript" src="<%=basePath %>comm_script/dialog/lhgdialog.min.js"></script>
   	<script type="text/javascript"
				src="<%=basePath %>ckeditor/ckeditor.js">
	</script>
	<script type="text/javascript"
			src="<%=basePath %>/ckfinder/ckfinder.js">
	</script>
  </head>
  
  <body>
  	<form action="footer_save.action" method="post">
  		<div >
  			<label>
				<h4>About us (EN):</h4>
			</label>
			<textarea name="footer.aboutus_EN"></textarea>
  		</div>
  		<div >
  			<label>
				<h4>About us (CH):</h4>
			</label>
			<textarea name="footer.aboutus_CH"></textarea>
  		</div>
  		<div >
  			<label>
				<h4>LegalStatement (EN):</h4>
			</label>
			<textarea name="footer.legalstmt_EN"></textarea>
  		</div>
  		<div >
  			<label>
				<h4>LegalStatement (CH):</h4>
			</label>
			<textarea name="footer.legalstmt_CH"></textarea>
  		</div>
  		<div >
  			<label>
				<h4>Links:</h4>
			</label>
			<textarea name="footer.links"></textarea>
  		</div>
  		<div class="rowElem"
			style="float: right; position: relative; right: 20px;">
			<input type="submit" class="big_submitButton_class" value="Submit" />
		</div>
  	</form>
    <script type="text/javascript" >
				$(function(){
					var editor = null;
		    		$(document).ready(function(){
		    			editor = CKEDITOR.replaceAll(function(textarea,config){
		    				config.customConfig="<%=basePath%>/ckeditor/backend_config.js";
		    			});
		    			CKFinder.setupCKEditor(editor, "<%=basePath%>ckfinder/");
		    		});
				});
		</script>
  </body>
</html>
