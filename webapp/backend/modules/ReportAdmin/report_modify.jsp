<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	String basePath = serverPath + path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://ckeditor.com"  prefix="ckeditor"%>
<!DOCTYPE HTML>
<html>
	<head>
		<base href="<%=basePath%>backend/">

		<title>My JSP 'styleAdd.jsp' starting page</title>

	    <link href="<%=basePath %>comm_style/base.css" rel="stylesheet" type="text/css" />
   	 	<link href="<%=basePath %>comm_script/bootstrap/css/bootstrap.css" rel="stylesheet">
   	 	<link href="<%=basePath %>comm_script/bootstrap/css/bootstrap-formhelpers.css" rel="stylesheet">
   	 	<link href="<%=basePath %>comm_script/fileuploader/css/custom_uploader.css" rel="stylesheet">
   	 	<script type="text/javascript" 
    		src="<%=basePath %>comm_script/jquery-1.7.2.min.js">
    	</script>
    	<script src="<%=basePath %>comm_script/bootstrap/js/bootstrap-formhelpers-datepicker.en_US.js"></script>
	    <script src="<%=basePath %>comm_script/bootstrap/js/bootstrap-formhelpers-datepicker.js"></script>
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
		<style type="text/css">
			.preview{
				width:500px;
				height:50px;
			}
		</style>
	</head>

	<body>
		<div id="wrapper">
			<form id="fmt" action="report_update.action" method="post" target="main"  enctype="multipart/form-data">
				<input type="hidden" name="report.reportid" value="<s:property value='reportvo.id'/>" />
				<input type="hidden" name="report.reportimg" value="<s:property value='reportvo.report.reportimg'/>">
				<div class="rowElem">
					<label>
						<h4>Report English Name:</h4>
					</label>
					<input type="text" name="report.reportEname" value="<s:property value='reportvo.report.reportEname'/>"  />
				</div>
				<div class="rowElem">
					<label><h4>Report Chinese Name:</h4></label>
					<input type="text" name="report.reportCname" value="<s:property value='reportvo.report.reportCname'/>"  />
				</div>
			
				<div id="uploadbox">
					<div id="title">
			        	<p>Report Thumbnail Image:</p>
			            <hr />
			        </div>
			        <div id="container">
			        <div id="imagecontainer">
			        	<ul>
		                	<li class="list_common">
			                	<div class="imgLiquidFill imgLuid pic">
			                    	<img src="<%=basePath %><s:property value='reportvo.thumbnail'/>"  width="80" height="80"/>
			                    </div>
			                </li>
			        	</ul>
			        </div>
			        </div>
			        <div id="uploader">
						<input type="file" value="upload" name="imageFile" />
			        </div>
			     </div>
				<br/>
				<div class="rowElem">
					<label><h4>Report Date:</h4></label>
						<div class="bfh-datepicker" data-format="y-m-d" data-date="<s:date name='reportvo.report.reportdate' format='yyyy-MM-dd'/>">
		              <div class="input-prepend bfh-datepicker-toggle" data-toggle="bfh-datepicker">
		                <span class="add-on"><i class="icon-calendar"></i></span>
		                <input type="text" class="input-medium" name="report.reportdate" readonly>
		              </div>
		              <div class="bfh-datepicker-calendar">
		                <table class="calendar table table-bordered">
		                  <thead>
		                    <tr class="months-header">
		                      <th class="month" colspan="4">
		                        <a class="previous" href="#"><i class="icon-chevron-left"></i></a>
		                        <span></span>
		                        <a class="next" href="#"><i class="icon-chevron-right"></i></a>
		                      </th>
		                      <th class="year" colspan="3">
		                        <a class="previous" href="#"><i class="icon-chevron-left"></i></a>
		                        <span></span>
		                        <a class="next" href="#"><i class="icon-chevron-right"></i></a>
		                      </th>
		                    </tr>
		                    <tr class="days-header">
		                    </tr>
		                  </thead>
		                  <tbody>
		                  </tbody>
		                </table>
		              </div>
		            </div>
				</div>
				<br/>
				<div class="rowElem">
					<label><h4>Report Preview(EN):</h4></label>
					<textarea name="report.previewEN" cols="200" rows="2" class="preview"><s:property value="reportvo.report.previewEN"/></textarea>
				</div>
				<br/>
				<div class="rowElem">
					<label><h4>Report Preview(CH):</h4></label>
					<textarea name="report.previewCH" cols="200" rows="2" class="preview"><s:property value="reportvo.report.previewCH"/></textarea>
				</div>
				<br/>
				<div class="rowElem">
					<label><h4>Report Introduction(EN):</h4></label>
					<textarea name="report.detailContentEN" cols="80" rows="5" ><s:property value='reportvo.report.detailContentEN'/></textarea>
				</div>
				<br/>
				<div class="rowElem">
					<label><h4>Report Introduction(ZH):</h4></label>
					<textarea name="report.detailContentCH" cols="80" rows="5" ><s:property value='reportvo.report.detailContentCH'/></textarea>
				</div>
				<br/>
				<div class="ui-widget">
					<label for="designerName"><h4>Designer Name(EN): </h4></label>
					<input id="designerName" type="text" name="designer.ename" value="<s:property value='designer.ename'/>"/>
				</div>
				<br />
			<br />
			<div class="rowElem"
				style="float: right; position: relative; right: 20px;">
				<input type="submit" class="big_submitButton_class" value="Submit" />
			</div>
			</form>
		</div>
		<script type="text/javascript" >
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
		    				if(textarea.className == "preview"){
		    					return false;
		    				}
		    			});
		    			CKFinder.setupCKEditor(editor, "<%=basePath%>ckfinder/");
		    		});
		    		
		    		$(".preview").each(function(index){
		    			$(this).blur(function(){
		    				var _this = this;
		    				var len = $(_this).val().length;
		    				if(len > 200){
		    					feedbackInfo("the max input characters amount is 200","WARNING");
		    					$("form").submit(function(){
		    						return false;
		    					});
		    				}else{
		    					$("form").submit(function(){
		    						return true;
		    					});
		    				}
		    			});
		    		});
				});
		</script>
	</body>
</html>
