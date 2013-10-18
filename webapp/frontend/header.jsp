<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="<%=basePath %>frontend/css/header.css" type="text/css" rel="stylesheet"/>
    <div id="headerbg">
		<div id="headerupper">
			<ul id="joinus">
				<li class="list_common"><a class="popup-with-form" href="#fms"><s:text name="signin"/></a></li>
				<li class="list_common">&nbsp;&nbsp;|&nbsp;&nbsp;</li>
				<li class="list_common"><a href="account_registerForward.action"><s:text name="joinus"/></a></li>
			</ul>
			<ul id="languages">
				<li class="list_common"><a href="switch.action?request_locale=en_US">En</a></li>
				<li class="list_common">&nbsp;&nbsp;|&nbsp;&nbsp;</li>
				<li class="list_common"><a href="switch.action?request_locale=zh_CN">简</a></li>
				<li class="list_common">&nbsp;&nbsp;|&nbsp;&nbsp;</li>
				<li class="list_common"><a href="switch.action?request_locale=zh_TW">繁</a></li>
			</ul>
		</div>
		<div id="headercenter">
			<img src="images/HOME-PAGE-5.png" id="title" name="Contemporary Fashion Designers' Archive" alt="Contemporary Fashion Designers' Archive">
		</div>
		<div id="headerbottom">
			<ul id="menus">
				<li class="list_common menus"><a href="homepager_loadData.action"><s:text name="home"/></li>
				<li class="list_common menus"><a href="designer_loadAll.action"><s:text name="designer"/></a></li>
				<li class="list_common menus"><a href="brand_loadAll.action"><s:text name="brand"/></a></li>
				<li class="list_common menus"><a href="report_loadReportsByOrder.action"><s:text name="interview"/></a></li>
				<li class="list_common menus"><a href="menus/search/search.jsp"><s:text name="search"/></a></li>
				<li class="list_common menus"><a href="contact_loadInfo.action"><s:text name="aboutus"/></a></li>
			</ul>
		</div>
	</div>
