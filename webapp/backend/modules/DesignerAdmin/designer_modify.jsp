<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	String basePath = serverPath + path + "/";
%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<%@ taglib uri="http://ckeditor.com"  prefix="ckeditor"%>
<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>backend/">

		<title>My JSP 'designerAdd.jsp' starting page</title>

		<link href="<%=basePath %>comm_script/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="<%=basePath %>comm_style/base.css" rel="stylesheet" type="text/css" />
	    <link href="<%=basePath %>comm_script/fileuploader/css/custom_uploader.css" rel="stylesheet">
	    <script type="text/javascript" 
	    		src="<%=basePath %>comm_script/jquery-1.6.2.min.js">
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
	</head>

	<body>
		<div id="wrapper">
			<form id="fmt" action="designer_update.action" method="post" target="main" enctype="multipart/form-data">
				<input type="hidden" name="designer.designerId" value="<s:property value="designervo.designer.designerId"/>" />
				<input type="hidden" name="designer.imgURL" value="<s:property value="designervo.designer.imgURL"/>"/>
				<div class="rowElem">
					<label>
						<h4>Designer Name(EN):</h4>
					</label>
					<input type="text" name="designer.ename" value="<s:property value='designervo.designer.ename' />" />
				</div>
				<div class="rowElem">
					<label>
						<h4>Designer Name(ZH):</h4>
					</label>
					<input type="text" name="designer.cname" value="<s:property value='designervo.designer.cname' />"/>
				</div>
				<div id="uploadbox">
					<div id="title">
			        	<p>Designer Head Image:</p>
			            <hr />
			        </div>
			        <div id="container">
			        <div id="imagecontainer">
			        	<ul>
			            	<li class="list_common">
			                	<div class="imgLiquidFill imgLuid pic">
			                    	<img src="<%=serverPath %>/<s:property value='designervo.thumbnailUrl'/>"  />
			                    </div>
			                </li>
			        	</ul>
			        </div>
			        </div>
			        <div id="uploader">
			        	<input type="file" name="imageFile" multiple="multiple"/>
			        </div>
			     </div>
				<div>
					<label><h4>Detail Content(CH):</h4></label>
					<textarea name="designer.detailContentCH"><s:property value="designervo.designer.detailContentCH"/></textarea>
				</div>
				<br/>
				<div>
					<label><h4>Detail Content(EN):</h4></label>
					<textarea name="designer.detailContentEN"><s:property value="designervo.designer.detailContentEN"/></textarea>
				</div>
				<br />
			<br />
			<div class="rowElem"
				style="float: right; position: relative; right: 20px;">
				<input type="submit" class="big_submitButton_class" value="SUBMIT" />
			</div>
			</form>
		</div>
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
	</body>
</html>
