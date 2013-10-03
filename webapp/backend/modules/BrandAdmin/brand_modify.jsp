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
				src="<%=basePath%>backend/modules/BrandAdmin/data/brandlist.js">
		</script>
		<script type="text/javascript"
				src="<%=basePath %>ckeditor/ckeditor.js">
		</script>
		<script type="text/javascript"
				src="<%=basePath %>/ckfinder/ckfinder.js">
		</script>
	    <script type="text/javascript">
		    $(function(){
	     		$( "#designerName" ).autocomplete({
	     			source: "designer_fuzzySearch.action"
	     		});
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
	    		});
	    		CKFinder.setupCKEditor(editor, "<%=basePath%>ckfinder/");
			});
	    </script>

	</head>

	<body>
		<div id="wrapper">
			<form id="fmt" action="brand_update.action" method="post" target="main" enctype="multipart/form-data">
				<input type="hidden" name="brand.brandid" value="<s:property value='brand.brandid'/>" />
				<input type="hidden" name="brand.brandimg" value="<s:property value='brand.brandimg'/>"/>
				<div class="rowElem">
					<label>
						Brand Name(EN):
					</label>
					<input type="text" name="brand.brandEname" value="<s:property value='brand.brandEname' />" />
				</div>
				<div class="rowElem">
					<label>
						Brand Name(ZH):
					</label>
					<input type="text" name="brand.brandCname" value="<s:property value='brand.brandCname' />" />
				</div>
					<div class="rowElem">
					<label>
						Brand Image:
					</label>
					<input type="file" name="imageFile" />
				</div>
				<div>
					<label>Detail Content(CH):</label>
					<textarea name="brand.detailContentCH"><s:property value="brand.detailContentCH"/></textarea>
				</div>
				<br/>
				<div>
					<label>Detail Content(EN):</label>
					<textarea name="brand.detailContentEN"><s:property value="brand.detailContentEN"/></textarea>
				</div>
				<br />
				<div class="ui-widget">
					<label for="designerName">Designer Name(EN): </label>
					<input id="designerName" name="designer.ename" value="<s:property value='brand.designer.ename'/>"/>
				</div>
<%--				<div class="rowElem">--%>
<%--					<label>Brand Style:</label>--%>
<%--					<table id="styleGrid" style="display: none;"></table>--%>
<%--				</div>--%>
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
