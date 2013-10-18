<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
	String basePath = serverPath + path + "/";
%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE HTML>
<html>
	<head>
		<base href="<%=basePath%>backend/">

		<title>editorial modify page</title>

		<link href="<%=basePath %>comm_style/base.css" rel="stylesheet" type="text/css" />
		<link href="<%=basePath %>comm_script/fileuploader/css/custom_uploader.css" rel="stylesheet">
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
	</head>

	<body>
		<div id="wrapper">
			<form id="fm" action="editorial_update.action" method="post" target="main" enctype="multipart/form-data">
				<input type="hidden" name="editorial.editorialid" value="<s:property value="editorialvo.editorial.editorialid"/>" />
				<input type="hidden" name="editorial.imgs" value="<s:property value='editorialvo.editorial.imgs'/>">
				<div class="rowElem">
					<label>
						<h4>Editorial Name(EN):</h4>
					</label>
					<input type="text" name="editorial.editorialEname" value="<s:property value='editorialvo.editorial.editorialEname' />" />
				</div>
				<div class="rowElem">
					<label>
						<h4>Editorial Name(ZH):</h4>
					</label>
					<input type="text" name="editorial.editorialCname" value="<s:property value='editorialvo.editorial.editorialCname' />" />
				</div>
				<div id="uploadbox">
					<div id="title">
			        	<p>Editorial Images:</p>
			            <hr />
			        </div>
			        <div id="container">
			        <div id="imagecontainer">
			        	<ul>
			                <s:iterator value="editorialvo.thumbnailUrls" var="thumbnail">
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
					<label><h4>Editorial Date:</h4></label>
						<div class="bfh-datepicker" data-format="y-m-d" data-date="<s:date name='editorialvo.editorial.editdate' format='yyyy-MM-dd'/>">
		              <div class="input-prepend bfh-datepicker-toggle" data-toggle="bfh-datepicker">
		                <span class="add-on"><i class="icon-calendar"></i></span>
		                <input type="text" class="input-medium" name="editorial.editdate" readonly>
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
					<label for="brandName">
						<h4>Brand:</h4>
					</label>
					<input type="text"id="brandName" name="brand.brandEname"  value="<s:property value='editorialvo.editorial.brand.brandEname' />" />
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
					$( "#brandName" ).autocomplete({
						source: "brand_fuzzySearch.action"
					});
				});
		</script>
	</body>
</html>
