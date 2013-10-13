<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<%@ taglib uri="http://ckeditor.com"  prefix="ckeditor"%>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>backend/">

		<title>brand add</title>

		<link href="<%=basePath %>comm_style/base.css" rel="stylesheet" type="text/css" />
    	<link href="<%=basePath %>comm_script/bootstrap/css/bootstrap.css" rel="stylesheet">
    
	    <script type="text/javascript" 
	    		src="<%=basePath %>comm_script/jquery-1.7.2.min.js">
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
	</head>

	<body>
		<div id="wrapper">
			<form id="fmt" action="brand_save.action" method="post" target="main" enctype="multipart/form-data">
				<div class="rowElem">
					<label>
						<h4>Brand Name(EN):</h4>
					</label>
					<input type="text" name="brand.brandEname" />
				</div>
				<div class="rowElem">
					<label>
						<h4>Brand Name(ZH):</h4>
					</label>
					<input type="text" name="brand.brandCname" />
<%--				</div>--%>
<%--					<div class="rowElem">--%>
<%--					<label>--%>
<%--						Brand Image:--%>
<%--					</label>--%>
<%--					<input type="file" name="imageFile" />--%>
<%--				</div>--%>
				<br/>
				<div class="rowElem">
					<label><h4>Detail Content(ZH):</h4></label>
					<textarea name="brand.detailContentCH"></textarea>
				</div>
				<br/>
				<div class="rowElem">
					<label><h4>Detail Content(EN):</h4></label>
					<textarea name="brand.detailContentEN"></textarea>
				</div>
				<br />
			<br />
			<div class="ui-widget">
				<label for="designerName"><h4>Designer Name(EN):</h4></label>
				<input id="designerName" type="text" name="designer.ename"/>
			</div>
			<br/>
			<div class="rowElem"
				style="float: right; position: relative; right: 20px;">
				<input type="submit" class="big_submitButton_class" value="保存/更新" />
			</div>
			</form>
		</div>
		<script type="text/javascript">
    		$(function(){
         		$( "#designerName" ).autocomplete({
         			source: "designer_fuzzySearch.action"
         		});
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
    			CKFinder.setupCKEditor(editor, "<%=basePath%>ckfinder/");
    		});
    	</script>
	</body>
</html>
