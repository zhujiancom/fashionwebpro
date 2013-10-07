<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
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

    	<script type="text/javascript">
    		$(function(){
    			$( "#designerName" ).autocomplete({
         			source: "designer_fuzzySearch.action"
         		});
    		});
    	</script>

	</head>

	<body>
		<div id="wrapper">
			<form id="fm" action="interview_update.action" method="post" target="main" enctype="multipart/form-data">
				<input type="hidden" name="interview.interviewid" value="<s:property value='interview.interviewid'/>" />
				<input type="hidden" name="interview.interviewurl" value="<s:property value='interview.interviewurl'/>"/>
				<input type="hidden" name="interview.poster" value="<s:property value='interview.poster'/>"/>
				<div class="rowElem">
					<label>
						Interview English Name:
					</label>
					<input type="text" name="interview.interviewEname" value="<s:property value='interview.interviewEname' />" />
				</div>
				<div class="rowElem">
					<label>Interview Chinese Name:</label>
					<input type="text" name="interview.interviewCname" value="<s:property value='interview.interviewCname' />" />
				</div>
					<div class="rowElem">
					<label>Interview Type:</label>
					<select name="interview.interviewtype"
						style="width: 128px;">
						<option value="video"> Video</option>
						<option value="audio"> Audio</option>
				</select>
				</div>
				<div class="rowElem">
					<label>Interview Media Upload:</label>
					<input type="file" name="videoFile" />
				</div>
				<div class="rowElem">
					<label>Interview Poster Upload:</label>
					<input type="file" name="posterFile" />
				</div>
				<div class="rowElem">
					<label>Interviewer:</label>
					<input type="text" name="interview.interviewer" value="<s:property value='interview.interviewer' />" />
				</div>
				<div class="rowElem">
					<label>Interview Date:</label>
					<input type="text" name="interview.interviewdate"  class="datepicker" value="<s:date name='interview.interviewdate' format='yyyy-MM-dd'/>" />
				</div>
				<div class="rowElem">
					<label>Interview Introduction(EN):</label>
					<textarea name="interview.interviewEintro" ><s:property value='interview.interviewEintro' /></textarea>
				</div>
				<div class="rowElem">
					<label>Interview Introduction(ZH):</label>
					<textarea name="interview.interviewCintro" ><s:property value='interview.interviewCintro' /></textarea>
				</div>
				<div class="ui-widget">
					<label for="designerName">Designer Name(EN): </label>
					<input id="designerName" name="designer.ename" value="<s:property value='interview.designer.ename'/>" />
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
