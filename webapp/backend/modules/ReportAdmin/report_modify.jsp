<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://ckeditor.com"  prefix="ckeditor"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>backend/">

		<title>My JSP 'styleAdd.jsp' starting page</title>

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
		<script type="text/javascript" >
				$(function(){
					var _fileWrapper = $("#fileWrapper");
					$("#addFile").click(function(event){
						_fileWrapper.append("<tr><td><input type='file' name='imageFiles' /><span class='imgt'><img src='../comm_images/X.gif'/></td></tr>");
					});
					$("span",_fileWrapper).live("click",function(event){
						event.stopPropagation();
						$(this).parent().parent().remove();
					});
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
				});
		</script>
    

	</head>

	<body>
		<div id="wrapper">
			<form id="fmt" action="report_update.action" method="post" target="main"  enctype="multipart/form-data">
				<input type="hidden" name="report.reportid" value="<s:property value='report.reportid'/>" />
				<input type="hidden" name="report.reportimg" value="<s:property value='report.reportimg'/>">
				<div class="rowElem">
					<label>
						Report English Name:
					</label>
					<input type="text" name="report.reportEname" value="<s:property value='report.reportEname'/>"  />
				</div>
				<div class="rowElem">
					<label>Report Chinese Name:</label>
					<input type="text" name="report.reportCname" value="<s:property value='report.reportCname'/>"  />
				</div>
			
				<div class="rowElem">
					<label>Report Images:</label>
					<input type="button" name="addFile" id="addFile" value="addFile"/>
					<table id="fileWrapper">
						<tr>
							<td>
								<input type="file" name="imageFiles" /><span><img src="../comm_images/X.gif"/></span>
							</td>
						</tr>
					</table>
				</div>
				<div class="rowElem">
					<label>Interviewer:</label>
					<input type="text" name="report.interviewer" value="<s:property value='report.interviewer'/>"  />
				</div>
				<br/>
				<div class="rowElem">
					<label>Report Introduction(EN):</label>
					<textarea name="report.detailContentCH" cols="80" rows="5" ><s:property value='report.detailContentCH'/></textarea>
				</div>
				<br/>
				<div class="rowElem">
					<label>Report Introduction(ZH):</label>
					<textarea name="report.detailContentEN" cols="80" rows="5" ><s:property value='report.detailContentEN'/></textarea>
				</div>
				<br/>
				<div class="ui-widget">
					<label for="designerName">Designer Name(EN): </label>
					<input id="designerName" name="designer.ename" value="<s:property value='report.designer.ename'/>"/>
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
