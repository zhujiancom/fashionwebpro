<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE HTML >
<html>
	<head>
		<base href="<%=basePath%>backend/">

		<title>lookbook add page</title>

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
	</head>

	<body>
		<div id="wrapper">
			<form action="lookbook_save.action" method="post" target="main" enctype="multipart/form-data">
				<div class="rowElem">
					<label>
						<h4>Lookbook Name(EN):</h4>
					</label>
					<input type="text" name="lookbook.lookbookEname" />
				</div>
				<div class="rowElem">
					<label>
						<h4>Lookbook Name(ZH):</h4>
					</label>
					<input type="text" name="lookbook.lookbookCname" />
				</div>
					<div class="rowElem">
					<label>
						<h4>Lookbook Image:</h4>
					</label>
					<input type="file" name="imageFiles" multiple="multiple" />
				</div>
				<div class="rowElem">
					<label><h4>Lookbook Date:</h4></label>
						<div class="bfh-datepicker" data-format="y-m-d">
		              <div class="input-prepend bfh-datepicker-toggle" data-toggle="bfh-datepicker">
		                <span class="add-on"><i class="icon-calendar"></i></span>
		                <input type="text" class="input-medium" name="lookbook.lookbookdate" readonly>
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
					<div class="ui-widget">
						<label for="brandName">Brand Name(EN): </label>
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
		<script type="text/javascript" >
			$(function(){
				$( "#brandName" ).autocomplete({
					source: "brand_fuzzySearch.action"
				});
			});
		</script>
	</body>
</html>
