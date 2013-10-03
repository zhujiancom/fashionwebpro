<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>frontend/">
    
    <title>leftmenu</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=basePath %>frontend/javascript/jquery-plugin/navgoco/jquery.navgoco.css"/>
	
	<script type="text/javascript" src="<%=basePath %>frontend/javascript/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>frontend/javascript/jquery-plugin/navgoco/jquery.navgoco.js"></script>
  
  	<script type="text/javascript" id="demo3-javascript">
		$(document).ready(function() {
			$("#demo3").navgoco({
				accordion: true,
				onClickBefore: function(e, submenu) {
					alert('Clicked on '+ (submenu === false ? 'leaf' : 'branch') + ' `'+$(this).text()+'`');
				},
				onClickAfter: function(e, submenu) {
					e.preventDefault();
					$('#demo3').find('li').removeClass('active');
						var li =  $(this).parent();
						var lis = li.parents('li');
						li.addClass('active');
						lis.addClass('active');
				},
				onToggleBefore: function(submenu, opening) {
					var idx = submenu.attr('data-index');
					var message = opening ? 'opening' : 'closing';
					console.log('I am ' + message + ' menu ' + idx + ' just after this.');
				},
				onToggleAfter: function(submenu, opened) {
					var idx = submenu.attr('data-index');
					var message = opened ? 'opened' : 'closed';
					console.log('I ' + message + ' menu ' + idx + ' just before this.');
				}
			});
		});
	</script>
  </head>
  
  <body>
    	<div class="panes">
					<div id="demo3-html">
						<ul id="demo3" class="nav"></ul>
					</div>
					<pre><code class="javascript" data-source="demo3"></code></pre>
					<pre><code class="html" data-source="demo3"></code></pre>
				</div>
  </body>
</html>
