<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE HTML>
<html>
	<head>
		<base href="<%=basePath%>backend/">

		<title>runwayshow add page</title>

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
			<form id="fmt" action="runwayshow_save.action" method="post" target="main" enctype="multipart/form-data">
				<div class="rowElem">
					<label>
						<h4>Runway Show Name(EN):</h4>
					</label>
					<input type="text" name="runwayshow.runwayshowEname" />
				</div>
				<div class="rowElem">
					<label>
						<h4>Runway Show Name(ZH):</h4>
					</label>
					<input type="text" name="runwayshow.runwayshowCname" />
				</div>
				<div class="rowElem">
					<label>
						<h4>Runway Show Video:</h4>
					</label>
					<input type="file" name="videoFile" />
				</div>
				<div class="rowElem">
					<label><h4>Runway Show Poster:</h4></label>
					<input type="file" name="posterFile" />
				</div>
				<div class="rowElem">
					<label><h4>Runway Show Date:</h4></label>
						<div class="bfh-datepicker" data-format="y-m-d">
		              <div class="input-prepend bfh-datepicker-toggle" data-toggle="bfh-datepicker">
		                <span class="add-on"><i class="icon-calendar"></i></span>
		                <input type="text" class="input-medium" name="runwayshow.runwayshowdate" readonly>
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
					<label>
						<h4>Runway Show Introduction(EN):</h4>
					</label>
					<textarea  name="runwayshow.runwayshowEintro" ></textarea>
				</div>
				<div class="rowElem">
					<label>
						<h4>Runway Show Introduction(ZH):</h4>
					</label>
					<textarea name="runwayshow.runwayshowCintro" ></textarea>
				</div>
				<div class="rowElem">
					<div class="ui-widget">
						<label for="brandName"><h4>Brand Name(EN): </h4></label>
						<input id="brandName" type="text" name="brand.brandEname" />
					</div>
				</div>
				<br />
				<br />
				<div class="rowElem"
					style="float: right; position: relative; right: 20px;">
					<input type="submit" class="big_submitButton_class" value="Submit" />
				</div>
			</form>
		</div>
		<script type="text/javascript">
    		$(function(){
    			$( "#brandName" ).autocomplete({
					source: "brand_fuzzySearch.action"
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
    		});
    	</script>
	</body>
</html>
