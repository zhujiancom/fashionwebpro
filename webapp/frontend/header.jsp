<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<base href="<%=basePath%>frontend/">
	<link href="<%=basePath %>frontend/css/header.css" type="text/css" rel="stylesheet"/>
	<script src="<%=basePath %>comm_script/jquerytools/js/toolbox/toolbox.expose.js" type="application/javascript"></script>
	<script src="<%=basePath %>comm_script/jquerytools/js/overlay/overlay.js" type="application/javascript"></script>
	<style type="text/css">
		div.overlay {
			width:600px;
			display:none;
			color:#fff;
			padding:30px 10px 10px 10px;
		}
		
		div.overlay a.close {
			background-image:url(images/close.png);
			position:absolute;
			right:5px;
			top:7px;
			cursor:pointer;
			height:27px;
			width:27px;
		}
		
		#overlay {
			height:450px;		
			background-color:#666;
			-moz-border-radius:15px;		
			border-radius:15px;
		}
		
		#img {
			position:absolute;		
		}
		
		.info {
			position:absolute;
			bottom:10px;
			background-color:#333;
			opacity:0.7;
			padding:4px 15px;
		}
		
		.info strong {
			display:block;	
		}
		
		.disabled {
			visibility:hidden;		
		}
		
		div.contentWrap{
			height:450px;
			overflow-y:auto;
		}
	</style>
	
	
    <div id="headerbg">
		<div id="headerupper">
			<ul id="joinus">
				<s:set var="account" value="#session.login_account_session"/>
				<s:if test="#account.accountname != null">
					<li class="list_common"><s:text name="welcome"/>&nbsp;<font color="red"><s:property value="#account.accountname"/></font></li>
					<li class="list_common">&nbsp;&nbsp;|&nbsp;&nbsp;</li>
					<li class="list_common"><a href="../logout"><s:text name="logout"/></a></li>
				</s:if>
				<s:else>
					<li class="list_common"><a rel="#overlay" href="login.jsp"><s:text name="signin"/></a></li>
					<li class="list_common">&nbsp;&nbsp;|&nbsp;&nbsp;</li>
					<li class="list_common"><a href="../register"><s:text name="joinus"/></a></li>
				</s:else>
			</ul>
			<ul id="languages">
				<li class="list_common"><a href="../switchLang/en_US">En</a></li>
				<li class="list_common">&nbsp;&nbsp;|&nbsp;&nbsp;</li>
				<li class="list_common"><a href="../switchLang/zh_CN">简</a></li>
				<li class="list_common">&nbsp;&nbsp;|&nbsp;&nbsp;</li>
				<li class="list_common"><a href="../switchLang/zh_TW">繁</a></li>
			</ul>
		</div>
		<div id="headercenter">
			<img src="<%=basePath %>frontend/images/HOME-PAGE-5.png" id="title" name="Contemporary Fashion Designers' Archive" alt="Contemporary Fashion Designers' Archive">
		</div>
		<div id="headerbottom">
			<ul id="menus">
				<li class="list_common menus"><a href="../"><s:text name="home"/></li>
				<li class="list_common menus"><a href="../designer"><s:text name="designer"/></a></li>
				<li class="list_common menus"><a href="../brand"><s:text name="brand"/></a></li>
				<li class="list_common menus"><a href="../report"><s:text name="interview"/></a></li>
				<li class="list_common menus"><a href="../search"><s:text name="search"/></a></li>
				<li class="list_common menus"><a href="../contactus"><s:text name="contactus"/></a></li>
			</ul>
		</div>
	</div>
	<div class="overlay" id="overlay">
	  <!-- the external content is loaded inside this tag -->
	  	<div class="contentWrap"></div>
	</div>
	<script>
		$("a[rel]").overlay({
			
			mask: { 
				color: '#ccc',
				closeSpeed: 200
			},
			
			onBeforeLoad: function() {
		
				// grab wrapper element inside content
				var wrap = this.getOverlay().find(".contentWrap");
		
				// load the page specified in the trigger
				wrap.load(this.getTrigger().attr("href"));
			}
			
		});
		</script>
