<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>frontend/">
    
    <title>HOMEPAGE</title>
    
    <link rel="stylesheet" href="javascript/jquery-plugin/flexslider/flexslider.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="css/homepage.css" type="text/css" media="screen" />
	
	<script src="javascript/jquery-1.7.2.min.js" type="application/javascript"></script>
  	<script src="javascript/jquery-plugin/imgLiquid/imgLiquid.js" type="application/javascript"></script>
	<script src="javascript/jquery-plugin/flexslider/jquery.flexslider-min.js" type="application/javascript"></script>
	<script type="text/javascript"
				src="<%=basePath %>ckeditor/ckeditor.js">
		</script>
	<script type="application/javascript">
			var editor = null;
			$(window).load(function() {
				$('.flexslider').flexslider({
				  animation: "slide"
				});
			  });
			  
			  $(document).ready(function() {
				  $(".imgLiquidFill").imgLiquid({
					fill:true,
					horizontalAlign:"center",
					verticalAlign:"top"  
				  });
				  
					//ckeditor
				editor = CKEDITOR.replace("designerProfile",{
					customConfig:"<%=basePath%>/ckeditor/homepage_config.js"
				});
				var data = CKEDITOR.ajax.load( 'designer_fetchInfo.action', function( data ) {
    				alert( data );
				} );
			  });
		</script>
  </head>
  
  <body>
			<jsp:include page="header.jsp"></jsp:include>
			<div id="main">
			<div id="main_upper">
          	<div id="images">
              <div class="flexslider">
                <ul class="slides">
                  <s:iterator value="imagesrcs" var="imagesrc">
						<li><img src="<%=basePath %><s:property value="#imagesrc"/>"/></li>
					</s:iterator>
                </ul>
              </div>
              </div>
              <div id="featureddesigner">
              	<table cellpadding="0" cellspacing="0" rules="rows">
                	<tr>
                		<th colspan="2"><a href="#">FEATURED DESIGNERS</a></th>
                    </tr>
                    <tr align="center" valign="middle">
                    	<td>
                        	<div class="imgLiquidFill imgLuid">
                            	<a href="#"><img src="testsource/poster.png"  /></a>
                             </div>
                         </td>
                        <td><a href="#" >Zhang Da</a></td>
                    </tr>
                   <tr align="center" valign="middle">
                    	<td>
                        	<div class="imgLiquidFill imgLuid">
                            	<a href="#"><img src="testsource/poster.png"  /></a>
                             </div>
                         </td>
                        <td><a href="#" >Name</a></td>
                    </tr>
                    <tr align="center" valign="middle">
                    	<td>
                        	<div class="imgLiquidFill imgLuid">
                            	<a href="#"><img src="testsource/poster.png"  /></a>
                             </div>
                         </td>
                        <td><a href="#" >Name</a></td>
                    </tr>
                    <tr align="center" valign="middle">
                    	<td>
                        	<div class="imgLiquidFill imgLuid">
                            	<a href="#"><img src="testsource/poster.png"  /></a>
                             </div>
                         </td>
                        <td><a href="#" >Name</a></td>
                    </tr>
                    <tr align="center" valign="middle">
                    	<td>
                        	<div class="imgLiquidFill imgLuid">
                            	<a href="#"><img src="testsource/poster.png"  /></a>
                             </div>
                         </td>
                        <td><a href="#" >Name</a></td>
                    </tr>
                </table>
              </div>
          </div>
          <div id="main_center" style="border:1px solid blue;">
          	<div id="videodiv">
            	<h3><a href="#">VIDEO</a></h3>
                <hr />
            	<video controls="controls" autoplay="autoplay" height="340px" width="500px">
                	<source src="testsource/sintel-1024-stereo.mp4" >
                    Your browser dose not support the video tag.
                </video>
            </div>
            <div id="profilediv">
            	<h3><a href="#">PROFILE</a></h3>
                <hr />
                <textarea name="designerProfile"></textarea>
            </div>
          </div>
          <div id="main_bottom" style="margin-top:2px;">
          	<h3><a href="#">COLLECTION</a></h3>
            <hr />
            <h4><a href="#">Title</a></h4>
            <table width="895px">
            <tr align="center" valign="middle">
            	<td><div  class="imgLiquidFill imgLuid" style="width:146px; height:220px; padding:5px;"><img src="testsource/01.png"</div></td>
                <td><div  class="imgLiquidFill imgLuid" style="width:146px; height:220px; padding:5px;"><img src="testsource/01.png"</div></td>
                <td><div  class="imgLiquidFill imgLuid" style="width:146px; height:220px; padding:5px;"><img src="testsource/01.png"</div></td>
                <td><div  class="imgLiquidFill imgLuid" style="width:146px; height:220px; padding:5px;"><img src="testsource/01.png"</div></td>
                <td><div  class="imgLiquidFill imgLuid" style="width:146px; height:220px; padding:5px;"><img src="testsource/01.png"</div></td>
            </tr>
            </table>
          </div>
        </div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
  </body>
</html>
