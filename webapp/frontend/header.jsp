<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>frontend/">
    
    <title>My JSP 'header.jsp' starting page</title>
    
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="css/header.css" type="text/css" rel="stylesheet"/>
	<link rel="stylesheet" href="javascript/jquery-plugin/magnific-popup/magnific-popup.css"/> 
	<script src="javascript/jquery-plugin/magnific-popup/jquery.magnific-popup.js" type="text/javascript"></script> 
	
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
	    <div id="header">
	        <div id="header_upper">
	            <div id="login">
	            	<ul>
	            		<s:set var="account" value="#session.login_account_session"/>
	            		<s:if test="#account.accountname != null">
	            			<li class="login"><s:text name="welcome"/>&nbsp;<font color="red"><s:property value="#account.accountname"/></font></li>
	            			 <li class="login">&nbsp;&nbsp;|&nbsp;&nbsp;</li>
		                    <li class="login"><a href="account_logout.action"><s:text name="logout"/></a></li>
	            		</s:if>
	            		<s:else>
	            			<li class="login"><a class="popup-with-form" href="#fms"><s:text name="signin"/></a></li>
		                    <li class="login">&nbsp;&nbsp;|&nbsp;&nbsp;</li>
		                    <li class="login"><a href="account_registerForward.action"><s:text name="joinus"/></a></li>
	            		</s:else>
	                </ul>
	            </div>
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
	            
	            <div id="language">
	            	<ul>
	                	<li class="language"><a href="homepager_loadData.action?request_locale=zh_CN">简</a></li>
	                    <li class="language">&nbsp;&nbsp;|&nbsp;&nbsp;</li>
	                    <li class="language"><a href="homepager_loadData.action?request_locale=zh_TW">繁</a></li>
	                    <li class="language">&nbsp;&nbsp;|&nbsp;&nbsp;</li>
	                    <li class="language"><a href="homepager_loadData.action?request_locale=en_US">EN</a></li>
	                </ul>
	            </div>
	        </div>
	        <div id="header_title">
	        	<img src="images/HOME-PAGE-5.png" id="webtitle" alt="Comtemporary Fashion Designer's Archive" />
	            <div id="search_area">
	            	<form method="get" action="search_headSearch.action">
	                	<input type="text" id="searchkeywords" name="keywords" />
	                    <input type="submit" id="searchbutton"  value="Submit" />
	                    <br/>
	                    <div id="keywords">
	                        <input type="radio" name="searchType" checked="checked" value="all" />
	                        <label  class="keywords"><s:text name="all"/>&nbsp;&nbsp;</label>
	                        <input type="radio" name="searchType" value="designer" /><label class="keywords"><s:text name="designer"/>&nbsp;&nbsp;</label>
	                        <input type="radio" name="searchType" value="brand" /><label class="keywords"><s:text name="brand"/>&nbsp;&nbsp;</label>
	                        <input type="radio" name="searchType" value="style" /><label class="keywords"><s:text name="style"/></label>
	                    </div>
	                </form>
	            </div>
	        </div>
	        <div id="header_footer">
	        	<ul>
	            	<li class="menu"><a href="homepager_loadData.action"><s:text name="home"/></a></li>
	                <li class="menu"><a href="news_loadAll.action"><s:text name="news"/></a></li>
	                <li class="menu"><a href="designer_loadAll.action"><s:text name="designer"/></a></li>
	                <li class="menu"><a href="brand_loadAll.action"><s:text name="brand"/></a></li>
	                <li class="menu"><a href="style_loadAll.action"><s:text name="style"/></a></li>
	                <li class="menu"><a href="menus/search/search.jsp"><s:text name="search"/></a></li>
	                <li class="menu"><a href="contact_loadInfo.action"><s:text name="aboutus"/></a></li>
	            </ul>
	        </div>
	    </div>
  </body>
</html>
