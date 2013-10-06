<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>frontend/">
    
    <title>My JSP 'leftmenu.jsp' starting page</title>
	<link rel="stylesheet" href="<%=basePath %>frontend/css/leftmenu.css" type="text/css"></link>
	<link rel="stylesheet" href="<%=basePath %>comm_script/navgoco/jquery.navgoco.css" type="text/css"></link>
	
	<script type="text/javascript" src="<%=basePath %>comm_script/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>comm_script/navgoco/jquery.navgoco.min.js" type="text/css"></script>
	
	<script type="text/javascript">
		$(function(){
			var msg = "${msg}";
			if(msg != ""){
				var parentWin = window.parent.frames['mainPanel'];
		//		parentWin.$.dialog.tips(msg);			
			}
		});
		$(document).ready(function() {
			$("#menu").navgoco({
				caret: '<span class="caret"></span>',
				accordion: false,
				openClass: 'open',
				save: true,
				cookie: {
					name: 'navgoco',
					expires: false,
					path: '/'
				},
				slide: {
					duration: 400,
					easing: 'swing'
				}
	    });

	    $("#collapseAll").click(function(e) {
	        e.preventDefault();
	        $("#menu").navgoco('toggle', false);
	    });

	    $("#expandAll").click(function(e) {
	        e.preventDefault();
	        $("#menu").navgoco('toggle', true);
	    });
	});
	</script>
	

  </head>
  
  <body  oncontextmenu="return false" onselectstart="return false">
        <div id="menus">
            <div id="nav">
                <ul id="menu" class="nav">
                    <li class="active"><a href="#">Home</a></li>
					<s:iterator value="brandvo.menus" var="menu">
						<li>
							<a <s:property value="#menu.attributes"/>><s:property value="#menu.name"/></a>
							<s:if test="!#menu.items.isEmpty() || !#menu.subMenus.isEmpty()">
								<ul>
									<s:iterator value="#menu.items" var="menuItem">
										<li><a <s:property value="#menuItem.attributes"/>><s:property value="#menuItem.name"/></a></li>
									</s:iterator>
									<s:iterator value="#menu.subMenus" var="subMenu">
										<li>
											<a href="#"><s:property value="#subMenu.name"/></a>
											<ul>
												<s:iterator value="#subMenu.items" var="subItem">
													<li><a <s:property value="#subItem.attributes"/>><s:property value="#subItem.name"/></a></li>
												</s:iterator>
											</ul>
										</li>
									</s:iterator>		
								</ul>
							</s:if>
						</li>
					</s:iterator>
                </ul>
                <p class="external">
                    <a href="#" id="collapseAll">Collapse All</a> | <a href="#" id="expandAll">Expand All</a>
                </p>
            </div>
        </div>
  </body>
</html>
