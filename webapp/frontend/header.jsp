<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Header</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<%--	<link href="<%=basePath %>comm_script/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>--%>
	<link href="<%=basePath %>frontend/css/header.css" type="text/css" rel="stylesheet"/>
	<link rel="stylesheet" href="<%=basePath %>frontend/javascript/jquery-plugin/magnific-popup/magnific-popup.css"/> 
	<script src="<%=basePath %>frontend/javascript/jquery-plugin/magnific-popup/jquery.magnific-popup.min.js" type="application/javascript"></script>
	<style type="text/css">
    	
		.white-popup-block {
		    background: none repeat scroll 0 0 #FFFFFF;
		    margin: 40px auto;
		    max-width: 650px;
		    padding: 20px 30px;
		    position: relative;
		    text-align: left;
		}
		
    </style>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$('.popup-with-form').magnificPopup({
				type: 'inline',
				preloader: false,
				focus: '#name',
	
				// When elemened is focused, some mobile browsers in some cases zoom in
				// It looks not nice, so we disable it:
				callbacks: {
					beforeOpen: function() {
						if($(window).width() < 700) {
							this.st.focus = false;
						} else {
							this.st.focus = '#name';
						}
					}
				}
			});
		});
	</script>
	
  </head>
  
  <body>
	    <form id="fms" class="white-popup-block mfp-hide" method="post" action="account_login.action">
					<h1>Sign In Form</h1>
					<fieldset style="border:0;">
						<div>
				   			<label>Accountname:&nbsp;</label>
				   			<input name="account.accountname" type="text" />
				    	</div>
				    	<div>
				   			<label>Password：&nbsp;</label>
			   				<input name="account.pswd"type="password" />
				   		</div>
				    	<br />
						<br />
						<div style="float: right; position: relative; right: 20px;">
							<input type="submit" style="background-image: url(<%=basePath%>comm_images/big_submit_btn.gif); width:93px;height:30px;border:none;" value="SUBMIT" />
						</div>
					</fieldset>
				</form>
	    <div id="headerbg">
			<div id="headerupper">
				<ul id="joinus">
					<li class="list_common"><a class="popup-with-form" href="#fms"><s:text name="signin"/></a></li>
					<li class="list_common">&nbsp;&nbsp;|&nbsp;&nbsp;</li>
					<li class="list_common"><a href="account_registerForward.action"><s:text name="joinus"/></a></li>
				</ul>
				<ul id="languages">
					<li class="list_common"><a href="homepager_loadData.action?request_locale=en_US">En</a></li>
					<li class="list_common">&nbsp;&nbsp;|&nbsp;&nbsp;</li>
					<li class="list_common"><a href="homepager_loadData.action?request_locale=zh_CN">简</a></li>
					<li class="list_common">&nbsp;&nbsp;|&nbsp;&nbsp;</li>
					<li class="list_common"><a href="homepager_loadData.action?request_locale=zh_TW">繁</a></li>
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
					<li class="list_common menus"><a href="#"><s:text name="interview"/></a></li>
					<li class="list_common menus"><a href="menus/search/search.jsp"><s:text name="search"/></a></li>
					<li class="list_common menus"><a href="contact_loadInfo.action"><s:text name="aboutus"/></a></li>
				</ul>
			</div>
		</div>
  </body>
</html>
