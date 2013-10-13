<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	String basePath = serverPath + path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML>
<html>
	<head>
		<base href="<%=basePath%>backend/">

		<title>interview modify page</title>

	    <link href="<%=basePath %>comm_style/base.css" rel="stylesheet" type="text/css" />
   	 	<link href="<%=basePath %>comm_script/bootstrap/css/bootstrap.css" rel="stylesheet">
   	 	<link href="<%=basePath %>comm_script/bootstrap/css/bootstrap-formhelpers.css" rel="stylesheet">
   	 	<link href="<%=basePath %>comm_script/fileuploader/css/custom_uploader.css" rel="stylesheet">
   	 	
   	 	<script type="text/javascript" 
    		src="<%=basePath %>comm_script/jquery-1.7.2.min.js">
    	</script>
    	<script src="<%=basePath %>comm_script/bootstrap/js/bootstrap-formhelpers-datepicker.en_US.js"></script>
	    <script src="<%=basePath %>comm_script/bootstrap/js/bootstrap-formhelpers-datepicker.js"></script>
	    <script src="<%=basePath %>comm_script/bootstrap/js/bootstrap-formhelpers-selectbox.js"></script>
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
			<form id="fmt" action="interview_update.action" method="post" target="main" enctype="multipart/form-data">
				<input type="hidden" name="interview.interviewid" value="<s:property value='interviewvo.id'/>" />
				<input type="hidden" name="interview.interviewurl" value="<s:property value='interviewvo.interview.interviewurl'/>"/>
				<input type="hidden" name="interview.poster" value="<s:property value='interviewvo.interview.poster'/>"/>
				<div class="rowElem">
					<label>
						<h4>Interview English Name:</h4>
					</label>
					<input type="text" name="interview.interviewEname" value="<s:property value='interviewvo.interview.interviewEname' />" />
				</div>
				<div class="rowElem">
					<label><h4>Interview Chinese Name:</h4></label>
					<input type="text" name="interview.interviewCname" value="<s:property value='interviewvo.interview.interviewCname' />" />
				</div>
				<div class="rowElem">
					<label><h4>Interivew Type:</h4></label>
					<div class="bfh-selectbox">
					  <input type="hidden" name="interview.interviewtype" value="<s:property value='interviewvo.type'/>">
					  <a class="bfh-selectbox-toggle" role="button" data-toggle="bfh-selectbox" href="#">
					    <span class="bfh-selectbox-option input-medium"><s:property value="interviewvo.type"/></span>
					    <b class="caret"></b>
					  </a>
					  <div class="bfh-selectbox-options">
					    <div role="listbox">
						    <ul role="option">
						      <li><a tabindex="-1" href="#" data-option="video">Video</a></li>
						      <li><a tabindex="-1" href="#" data-option="audio">Audio</a></li>
						    </ul>
						  </div>
					  </div>
					</div>
				</div>
				<div class="rowElem">
					<label><h4>Interview Media:</h4></label>
					<input type="file" value="upload" name="videoFile" />
					<p><s:property value="interviewvo.videoName"/></p>
				</div>
				<div id="uploadbox">
					<div id="title">
			        	<p>Interview Poster:</p>
			            <hr />
			        </div>
			        <div id="container">
			        <div id="imagecontainer">
			        	<ul>
		                	<li class="list_common">
			                	<div class="imgLiquidFill imgLuid pic">
			                    	<img src="<s:property value='interviewvo.posterthumnail'/>"  width="80" height="80"/>
			                    </div>
			                </li>
			        	</ul>
			        </div>
			        </div>
			        <div id="uploader">
						<input type="file" value="upload" name="posterFile" />
			        </div>
			     </div>
				<br/>
				<div class="rowElem">
					<label><h4>Interview Date:</h4></label>
						<div class="bfh-datepicker" data-format="y-m-d" data-date="<s:date name='interviewvo.interview.interviewdate' format='yyyy-MM-dd'/>">
		              <div class="input-prepend bfh-datepicker-toggle" data-toggle="bfh-datepicker">
		                <span class="add-on"><i class="icon-calendar"></i></span>
		                <input type="text" class="input-medium" name="interview.interviewdate" readonly>
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
				<div class="rowElem">
					<label><h4>Interview Introduction(EN):</h4></label>
					<textarea name="interview.interviewEintro" ><s:property value='interviewvo.interview.interviewEintro' /></textarea>
				</div>
				<div class="rowElem">
					<label><h4>Interview Introduction(ZH):</h4></label>
					<textarea name="interview.interviewCintro" ><s:property value='interviewvo.interview.interviewCintro' /></textarea>
				</div>
				<div class="ui-widget">
					<label for="designerName">Designer Name(EN): </label>
					<input id="designerName" type="text" name="designer.ename" value="<s:property value='interviewvo.interview.designer.ename'/>" />
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
