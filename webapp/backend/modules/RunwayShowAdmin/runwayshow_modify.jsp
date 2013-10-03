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

	</head>

	<body>
		<div id="wrapper">
			<form id="fm" action="runwayshow_update.action" method="post" target="main"  enctype="multipart/form-data">
				<input type="hidden" name="runwayshow.runwayshowid" value="<s:property value='runwayshow.runwayshowid'/>" />
				<input type="hidden" name="runwayshow.runwayshowUrl" value="<s:property value='runwayshow.runwayshowUrl'/>"/>
				<input type="hidden" name="runwayshow.poster" value="<s:property value='runwayshow.poster'/>" />
				<div class="rowElem">
					<label>
						Runway Show Name(EN):
					</label>
					<input type="text" name="runwayshow.runwayshowEname" value="<s:property value='runwayshow.runwayshowEname'/>"  />
				</div>
				<div class="rowElem">
					<label>
						Runway Show Name(ZH):
					</label>
					<input type="text" name="runwayshow.runwayshowCname" value="<s:property value='runwayshow.runwayshowCname'/>"  />
				</div>
				<div class="rowElem">
					<label>
						Runway Show Video Upload:
					</label>
					<input type="file" name="videoFile" />
				</div>
				<div class="rowElem">
					<label>Runway Show Poster Upload:</label>
					<input type="file" name="posterFile" />
				</div>
				<div class="rowElem">
					<label>
						Runway Show Date:
					</label>
					<input type="text" name="runwayshow.runwayshowdate"  class="datepicker"  value="<s:date name='runwayshow.runwayshowdate' format='yyyy-MM-dd'/>" />
				</div>
				<div class="rowElem">
					<div class="ui-widget">
						<label for="brandName">Brand Name(EN): </label>
						<input id="brandName" name="brand.brandEname" value="<s:property value='runwayshow.brand.brandEname'/>" />
					</div>
				</div>
				<div class="rowElem">
					<label>
						Runway Show Introduction(EN):
					</label>
					<textarea  name="runwayshow.runwayshowEintro" ><s:property value='runwayshow.runwayshowEintro'/></textarea>
				</div>
				<div class="rowElem">
					<label>
						Runway Show Introduction(ZH):
					</label>
					<textarea name="runwayshow.runwayshowCintro" ><s:property value='runwayshow.runwayshowCintro'/></textarea>
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
