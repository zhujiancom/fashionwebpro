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
    
    <title>My JSP 'profile.jsp' starting page</title>
    
	
	<link href="<%=basePath%>frontend/css/lookbook.css" rel="stylesheet" />
	<link href="<%=basePath%>frontend/javascript/jquery-plugin/jpages/jPages.css" rel="stylesheet"/>
	<link href="<%=basePath%>frontend/javascript/jquery-plugin/magnific-popup/magnific-popup.css" rel="stylesheet" />
	<script src="<%=basePath%>comm_script/jquery-1.7.2.min.js" type="application/javascript"></script>
	<script src="<%=basePath%>frontend/javascript/jquery-plugin/magnific-popup/jquery.magnific-popup.js" type="text/javascript"></script> 
	<script src="<%=basePath%>frontend/javascript/jquery-plugin/jpages/jPages.js" type="application/javascript"></script>
	
  </head>
  
  <body  oncontextmenu="return false" onselectstart="return false">
  	<div id="wrapper">
  		<div id="mainwrapper">
		    <div id="content">
		    	<s:iterator value="lookbooklist" var="lookbookvo" >
		        	<table class="tablestyle popup-gallery">
		        		<tr>
		            		<td colspan="4" class="titlestyle"><s:property value="#lookbookvo.name"/></td>
			            </tr>
			            <tr>
			            	<td colspan="4" style="height:10px"><hr /></td>
			            </tr>
                   		<s:iterator value="#lookbookvo.images" var="image" status="serial">
                   			<s:if test="#serial.count > 4">
                   			<td class="lookbookstyle"><a style="display:none;" class="simple-ajax-popup" href="<%=basePath %><s:property value='#image'/>"><img class="imgstyle" src="<%=basePath %><s:property value='#image'/>" /></a></td>
                   			</s:if>
                   			<s:else>
                   				<td class="lookbookstyle"><a class="simple-ajax-popup" href="<%=basePath %><s:property value='#image'/>"><img class="imgstyle" src="<%=basePath %><s:property value='#image'/>" /></a></td>
                   			</s:else>
                        </s:iterator>
		        	</table>
		        </s:iterator>
			</div>
			<div class="holder"></div>
		</div>
  	</div>
  	<script type="text/javascript">
		$(document).ready(function(){
			$('.popup-gallery').each(function(){
				$(this).magnificPopup({
			          delegate: 'a',
			          type: 'image',
			          tLoading: 'Loading image #%curr%...',
			          mainClass: 'mfp-img-mobile',
			          gallery: {
			            enabled: true,
			            navigateByImgClick: true,
			            preload: [0,1] // Will preload 0 - before current, and 1 after the current image
			          },
			          image: {
			            tError: '<a href="%url%">The image #%curr%</a> could not be loaded.',
			            titleSrc: function(item) {
			              return item.el.attr('title');
			            }
			          }
			     });
			});
			/* initiate plugin */
    		$("div.holder").jPages({
      			containerID : "content",
				perPage:2
    		});

    		/* when button is clicked */
    		$("button").click(function(){
     			 /* get given page */
     			 var page = parseInt( $("input").val() );

	     		 /* jump to that page */
	     		 $("div.holder").jPages( page );

   	 		});
		});
	</script>
  </body>
</html>
