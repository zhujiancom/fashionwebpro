<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>backend/">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<link rel="stylesheet" href="<%=basePath %>comm_style/base.css" type="text/css"/>
	<link rel="stylesheet" href="<%=basePath %>comm_script/jquery-plugin/flexigrid/css/flexigrid-style.css" type="text/css"/>
    <link rel="stylesheet" href="<%=basePath %>comm_script/jquery-plugin/flexigrid/css/flexigrid.pack.css" type="text/css"/>
	<link rel="stylesheet" href="<%=basePath %>comm_script/jqmodal/jqModal.css" type="text/css"/>
	
	<script type="text/javascript"
			src="<%=basePath%>comm_script/jquery-1.6.2.min.js">
	</script>
	<script type="text/javascript" 
			src="<%=basePath%>comm_script/base.js">
	</script>
	<script type="text/javascript"
			src="<%=basePath%>comm_script/dialog/lhgdialog.min.js">
	</script>
	<script type="text/javascript"
			src="<%=basePath%>backend/modules/HomePageAdmin/data/homepage.js">
	</script>
	<script type="text/javascript" 
    		src="<%=basePath%>comm_script/jquery-plugin/jquery-ui-1.8.16.custom.min.js">
    </script>
    <script type="text/javascript" 
    		src="<%=basePath%>comm_script/jqmodal/jqModal.js">
    </script>
	<script type="text/javascript">
   		$(function(){
       		$( "#designerName" ).autocomplete({
       			source: "designer_fuzzySearch.action"
       		});
       		var msg = "${msg}";
			if(msg != ""){
				$.dialog.alert(msg);			
			}
			var _fileWrapper = $("#fileWrapper");
			$("#addFile").click(function(event){
				_fileWrapper.append("<tr><td><input type='file' name='imageFiles' /><span class='imgt'><img src='../comm_images/X.gif'/></td></tr>");
			});
			$("span",_fileWrapper).live("click",function(event){
				event.stopPropagation();
				$(this).parent().parent().remove();
			});
   		});
   	</script>
  </head>
  
  <body>
  	<div id="wrapper">
  		<form id="fm" action="homepager_modify.action" method="post" target="main" enctype="multipart/form-data" onkeydown="if(event.keyCode==13){return false;}">
 			<div class="ui-widget">
				<label for="designerName">Designer Name(EN): </label>
				<input id="designerName" name="featuredDesigner.ename" />
			</div>
			<div class="rowElem">
				<label>
					Home Image:
				</label>
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
				<label>
					Home Video Poster:
				</label>
				<table >
					<tr>
						<td>
							<input type="file" name="poster" />
						</td>
					</tr>
				</table>
			</div>
			<div class="rowElem">
				<label>
					Home Video:
				</label>
				<table>
					<tr>
						<td>
							<input type="file" name="videoFile" />
						</td>
					</tr>
				</table>
			</div>
			<div class="blankRow"></div>
	  		<table id="designerGrid" style="display: none;"></table>
	  		<div class="blankRow"></div>
			<table id="newsGrid" style="display: none;"></table>
			<div class="blankRow"></div>
			<table id="lookbookGrid" style="display: none;"></table>
			<div class="rowElem"
				style="float: right; position: relative; right: 20px;">
				<input type="submit" class="big_submitButton_class" value="Submit" />
			</div>
	  	</form>
  	</div>
  </body>
</html>
