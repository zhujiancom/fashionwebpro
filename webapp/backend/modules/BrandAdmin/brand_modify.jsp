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

		<title>My JSP 'designerAdd.jsp' starting page</title>
		
		<link href="<%=basePath %>comm_script/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<link href="<%=basePath %>comm_style/base.css" rel="stylesheet" type="text/css" />
	    <link href="<%=basePath %>comm_script/fileuploader/css/custom_uploader.css" rel="stylesheet">
	    <link href="<%=basePath %>comm_script/fileuploader/css/jquery.fileupload-ui.css" rel="stylesheet">
	    <link href="<%=basePath %>comm_script/fileuploader/css/jquery.fileupload-ui-noscript.css" rel="stylesheet">
	    <script type="text/javascript" 
	    		src="<%=basePath %>comm_script/jquery-1.9.1.min.js">
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
		<script type="text/javascript" src="<%=basePath %>frontend/javascript/jquery-plugin/imgLiquid/imgLiquid-min.js"></script>
	    <script type="text/javascript" src="<%=basePath %>comm_script/fileuploader/js/vendor/jquery.ui.widget.js"></script>
	    <script type="text/javascript" src="<%=basePath %>comm_script/fileuploader/js/jquery.iframe-transport.js"></script>
	    <script type="text/javascript" src="<%=basePath %>comm_script/fileuploader/js/jquery.fileupload.js"></script>
	    <script type="text/javascript" src="<%=basePath %>comm_script/fileuploader/js/jquery.fileupload-image.js"></script>
	    <script type="text/javascript" src="<%=basePath %>comm_script/fileuploader/js/jquery.fileupload-audio.js"></script>
	    <script type="text/javascript" src="<%=basePath %>comm_script/fileuploader/js/jquery.fileupload-video.js"></script>
	    <script type="text/javascript" src="<%=basePath %>comm_script/fileuploader/js/jquery.fileupload-validate.js"></script>
	    <script type="text/javascript" src="<%=basePath %>comm_script/fileuploader/js/jquery.fileupload-ui.js"></script>
	    <script type="text/javascript" src="<%=basePath %>comm_script/fileuploader/js/main.js"></script>
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
	    			$(".imgLiquidFill").imgLiquid({
						fill:true,
						horizontalAlign:"center",
						verticalAlign:"top"  
						  
					  });
	    		});
	    		CKFinder.setupCKEditor(editor, "<%=basePath%>ckfinder/");
			});
	    </script>
	</head>

	<body>
		<div id="wrapper">
			<form id="fmt" action="brand_update.action" method="post" target="main" enctype="multipart/form-data">
				<input type="hidden" name="brand.brandid" value="<s:property value='brandvo.id'/>" />
				<input type="hidden" name="brand.brandimg" value="<s:property value='brandvo.brand.brandimg'/>"/>
				<div class="rowElem">
					<label>
						Brand Name(EN):
					</label>
					<input type="text" name="brand.brandEname" value="<s:property value='brandvo.brand.brandEname' />" />
				</div>
				<div class="rowElem">
					<label>
						Brand Name(ZH):
					</label>
					<input type="text" name="brand.brandCname" value="<s:property value='brandvo.brand.brandCname' />" />
				</div>
				<div id="uploadbox">
					<div id="title">
			        	<p>Brand Thumbnail Image:</p>
			            <hr />
			        </div>
			        <div id="container">
			        <div id="imagecontainer">
			        	<ul>
		                	<li class="list_common">
			                	<div class="imgLiquidFill imgLuid pic">
			                    	<img src="<%=basePath %><s:property value='brandvo.thumbnailUrl'/>"  width="80" height="80"/>
			                    </div>
			                </li>
			        	</ul>
			        </div>
			        </div>
			        <div id="uploader">
						<input type="file" name="imageFile" />
			        </div>
			     </div>
<%--				<div id="uploadbox">--%>
<%--					<div id="title">--%>
<%--			        	<p>Brand Image:</p>--%>
<%--			            <hr />--%>
<%--			        </div>--%>
<%--			        <div id="container">--%>
<%--			        <div id="imagecontainer">--%>
<%--			        	<ul>--%>
<%--			            	<li class="list_common">--%>
<%--			                	<div class="imgLiquidFill imgLuid pic">--%>
<%--			                    	<img src="<%=basePath %><s:property value='brandvo.thumbnailUrl'/>"  width="100" height="100"/>--%>
<%--			                    </div>--%>
<%--			                </li>--%>
<%--			        	</ul>--%>
<%--			        </div>--%>
<%--			        </div>--%>
<%--					<div id="uploader">--%>
<%--						 <form id="fileupload" action="" method="POST" enctype="multipart/form-data">--%>
<%--						 	<noscript><input type="hidden" name="redirect" value="http://blueimp.github.io/jQuery-File-Upload/"></noscript>--%>
<%--						 	<div class="row fileupload-buttonbar">--%>
<%--					            <div class="col-lg-7">--%>
<%--					                <!-- The fileinput-button span is used to style the file input field as button -->--%>
<%--					                <span class="btn btn-success fileinput-button">--%>
<%--					                    <i class="glyphicon glyphicon-plus"></i>--%>
<%--					                    <span>Add files...</span>--%>
<%--					                    <input type="file" name="files[]" multiple>--%>
<%--					                </span>--%>
<%--					                <button type="submit" class="btn btn-primary start">--%>
<%--					                    <i class="glyphicon glyphicon-upload"></i>--%>
<%--					                    <span>Start upload</span>--%>
<%--					                </button>--%>
<%--					                <button type="reset" class="btn btn-warning cancel">--%>
<%--					                    <i class="glyphicon glyphicon-ban-circle"></i>--%>
<%--					                    <span>Cancel upload</span>--%>
<%--					                </button>--%>
<%--					                <button type="button" class="btn btn-danger delete">--%>
<%--					                    <i class="glyphicon glyphicon-trash"></i>--%>
<%--					                    <span>Delete</span>--%>
<%--					                </button>--%>
<%--					                <input type="checkbox" class="toggle">--%>
<%--					                <!-- The loading indicator is shown during file processing -->--%>
<%--					                <span class="fileupload-loading"></span>--%>
<%--					            </div>--%>
<%--					            <!-- The global progress information -->--%>
<%--					            <div class="col-lg-5 fileupload-progress fade">--%>
<%--					                <!-- The global progress bar -->--%>
<%--					                <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">--%>
<%--					                    <div class="progress-bar progress-bar-success" style="width:0%;"></div>--%>
<%--					                </div>--%>
<%--					                <!-- The extended global progress information -->--%>
<%--					                <div class="progress-extended">&nbsp;</div>--%>
<%--					            </div>--%>
<%--					        </div>--%>
<%--					        <!-- The table listing the files available for upload/download -->--%>
<%--        					<table role="presentation" class="table table-striped"><tbody class="files"></tbody></table>--%>
<%--						 </form>--%>
<%--					</div>--%>
<%--				</div>--%>
				<div>
					<label>Detail Content(CH):</label>
					<textarea name="brand.detailContentCH"><s:property value="brandvo.brand.detailContentCH"/></textarea>
				</div>
				<br/>
				<div>
					<label>Detail Content(EN):</label>
					<textarea name="brand.detailContentEN"><s:property value="brandvo.brand.detailContentEN"/></textarea>
				</div>
				<br />
				<div class="ui-widget">
					<label for="designerName">Designer Name(EN): </label>
					<input id="designerName" type="text" name="designer.ename" value="<s:property value='brandvo.brand.designer.ename'/>"/>
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
