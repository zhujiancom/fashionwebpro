<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="/struts-tags"  prefix="s"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>backend/">

		<title>My JSP 'designerAdd.jsp' starting page</title>

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

    	<script type="text/javascript">
    		$(function(){
    			$( "#brandName" ).autocomplete({
					source: "brand_fuzzySearch.action"
				});
    		});
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
				});
		</script>

	</head>

	<body>
		<div id="wrapper">
			<form id="fmt" action="lookbook_update.action" method="post" target="main"  enctype="multipart/form-data">
				<input type="hidden" name="lookbook.lookbookid" value="<s:property value='lookbook.lookbookid'/>" />
				<input type="hidden" name="lookbook.imgs" value="<s:property value='lookbook.imgs'/>">
				<div class="rowElem">
					<label>
						Lookbook Name(EN):
					</label>
					<input type="text" name="lookbook.lookbookEname" value="<s:property value='lookbookvo.lookbook.lookbookEname'/>"  />
				</div>
				<div class="rowElem">
					<label>
						Lookbook Name(ZH):
					</label>
					<input type="text" name="lookbook.lookbookCname" value="<s:property value='lookbookvo.lookbook.lookbookCname'/>" />
				</div>
					<div id="uploadbox">
				<div id="title">
		        	<p>Lookbook Images:</p>
		            <hr />
		        </div>
		        <div id="container">
		        <div id="imagecontainer">
		        	<ul>
		                <s:iterator value="lookbookvo.imageThumbnailUrls" var="thumbnail">
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
					<div class="rowElem">
					<label>
						Lookbook Date:
					</label>
					<input type="text" name="lookbook.lookbookdate"  class="datepicker" value="<s:date name='lookbook.lookbookdate' format='yyyy-MM-dd'/>" />
				</div>
				<div class="rowElem">
					<div class="ui-widget">
						<label for="brandName">Brand Name(EN): </label>
						<input id="brandName" name="brand.brandEname" value="<s:property value='lookbook.brand.brandEname'/>"/>
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
	</body>
</html>
