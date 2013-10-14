<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://ckeditor.com"  prefix="ckeditor"%>
<!DOCTYPE HTML>
<html>
	<head>
		<base href="<%=basePath%>backend/">

		<title>My JSP 'styleAdd.jsp' starting page</title>

	    <link href="<%=basePath %>comm_style/base.css" rel="stylesheet" type="text/css" />
   	 	<link href="<%=basePath %>comm_script/bootstrap/css/bootstrap.css" rel="stylesheet">
   	 	<link href="<%=basePath %>comm_script/bootstrap/css/bootstrap-formhelpers.css" rel="stylesheet">
   	 	<script type="text/javascript" 
    		src="<%=basePath %>comm_script/jquery-1.7.2.min.js">
    	</script>
    	<script src="<%=basePath %>comm_script/bootstrap/js/bootstrap-formhelpers-datepicker.en_US.js"></script>
	    <script src="<%=basePath %>comm_script/bootstrap/js/bootstrap-formhelpers-datepicker.js"></script>
	    <script type="text/javascript" 
	    		src="<%=basePath%>comm_script/jquery-plugin/jquery-ui-1.8.16.custom.min.js">
	    </script>
	    <script type="text/javascript" src="<%=basePath %>comm_script/dialog/lhgdialog.min.js"></script>
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
			<form id="fmt" action="report_save.action" method="post" target="main" enctype="multipart/form-data">
				<div class="rowElem">
					<label>
						<h4>Report English Name:</h4>
					</label>
					<input type="text" name="report.reportEname" />
				</div>
				<div class="rowElem">
					<label><h4>Report Chinese Name:</h4></label>
					<input type="text" name="report.reportCname" />
				</div>
			
				<div class="rowElem">
					<label>
						<h4><h4>Report Thumbnail Image:</h4></h4>
					</label>
					<input type="file" value="upload" name="imageFile"/>
				</div>
				<br/>
				<div class="rowElem">
					<label><h4>Report Date:</h4></label>
						<div class="bfh-datepicker" data-format="y-m-d">
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
					<textarea name="report.previewEN" class="preview"></textarea>
				</div>
				<br/>
				<div class="rowElem">
					<label><h4>Report Preview(CH):</h4></label>
					<textarea name="report.previewCH" class="preview"></textarea>
				</div>
				<br/>
				<div class="rowElem">
					<label><h4>Report Detail Content(EN):</h4></label>
					<textarea name="report.detailContentEN" cols="80" rows="5"></textarea>
				</div>
				<br/>
				<div class="rowElem">
					<label><h4>Report Detail Content(ZH):</h4></label>
					<textarea name="report.detailContentCH" cols="80" rows="5"></textarea>
				</div>
				<br/>
				<div class="ui-widget">
					<label for="designerName">Designer Name(EN): </label>
					<input id="designerName" type="text" name="designer.ename" />
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
