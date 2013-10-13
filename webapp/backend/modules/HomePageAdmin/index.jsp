<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>backend/">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<link rel="stylesheet" href="<%=basePath %>comm_style/base.css" type="text/css"/>
	<link rel="stylesheet" href="<%=basePath %>comm_script/jqmodal/jqModal.css" type="text/css"/>
	
	<link href="<%=basePath %>comm_script/fileuploader/css/custom_uploader.css" rel="stylesheet">
	
	<script type="text/javascript"
			src="<%=basePath%>comm_script/jquery-1.7.2.min.js">
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
  </head>
  
  <body>
  	<div id="wrapper">
  		<form id="fmt" action="homepager_modify.action" method="post" target="main" enctype="multipart/form-data" onkeydown="if(event.keyCode==13){return false;}">
			<div  class="test">
				<table class="tableClass">
					<caption><h3>Featured Desinger Info</h3></caption>
					<tr>
						<th></th>
						<th>English Name</th>
						<th>Chinese Name</th>
						<th>Option</th>
					</tr>
					<tr id="featuredDesigner">
						<td><input type="hidden" name="featuredDesignerId" value="<s:property value='homevo.featuredDesigner.designerId'/>"/></td>
						<td><s:property value="homevo.featuredDesigner.ename"/></td>
						<td><s:property value="homevo.featuredDesigner.cname"/></td>
						<td><a href="javascript:openDialog('Select Featured Designer','modules/DesignerAdmin/featuredDesigner.jsp',800,500);">[select]</a></td>
					</tr>
				</table>
			</div>
			<div id="uploadbox">
				<div id="title">
		        	<p>Editorial Images:</p>
		            <hr />
		        </div>
		        <div id="container">
		        <div id="imagecontainer">
		        	<ul>
		                <s:iterator value="homevo.imageThumbnailUrls" var="thumbnail">
		                	<li class="list_common">
			                	<div class="imgLiquidFill imgLuid pic">
			                    	<img src="<%=basePath %><s:property value='#thumbnail'/>"  width="100" height="100"/>
			                    </div>
			                </li>
		                </s:iterator>
		        	</ul>
		        </div>
		        </div>
		        <div id="uploader">
					<input type="file" name="imageFiles" multiple="multiple" />
		        </div>
		     </div>
			<div style="margin-left:30px;margin-right:20px;">
				<label style="float:left;">
					Home Video:
				</label>
				<table>
					<tr>
						<td><h4><s:property value="homevo.videoName"/></h4></td>
						<td>
							<input type="file" name="videoFile" />
						</td>
					</tr>
				</table>
			</div>
			<div>
				<table class="tableClass" id="designers">
					<caption><h3>Desinger Info</h3></caption>
					<tr>
						<th></th>
						<th>English Name</th>
						<th>Chinese Name</th>
						<th><a href="javascript:openDialog('Select Designer','modules/DesignerAdmin/selectDesigner.jsp',800,500);">[select]</a></th>
					</tr>
					<s:iterator value="homevo.designers" var="designer">
						<tr>
							<td><input name="designerlist.designerId" type="hidden" value="<s:property value='#designer.designerId'/>"/></td>
							<td><s:property value="#designer.ename"/></td>
							<td colspan="2"><s:property value="#designer.cname"/></td>
						</tr>
					</s:iterator>
				</table>
			</div>
			<div>
				<table class="tableClass" id="lookbooks">
					<caption><h3>Lookbook Info</h3></caption>
					<tr>
						<th></th>
						<th>English Name</th>
						<th>Chinese Name</th>
						<th><a href="javascript:openDialog('Select Lookbook','modules/LookbookAdmin/selectLookbook.jsp',800,500);">[select]</a></th>
					</tr>
					<s:iterator value="homevo.lookbooks" var="lookbook">
						<tr>
							<td><input name="lookbooklist.lookbookid" type="hidden" value="<s:property value="#lookbook.lookbookid"/>"/></td>
							<td><s:property value="#lookbook.lookbookEname"/></td>
							<td colspan="2"><s:property value="#designer.lookbookCname"/></td>
						</tr>
					</s:iterator>
				</table>
			</div>
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
       		var msg = "${msg}";
			if(msg != ""){
				feedbackInfo(msg);			
			}
   		});
   	</script>
  </body>
</html>
