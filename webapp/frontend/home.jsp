<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
  <head>
    
    <title>HOMEPAGE</title>
    
    <link rel="stylesheet" href="<%=basePath %>frontend/javascript/jquery-plugin/flexslider/flexslider.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="<%=basePath %>frontend/css/homepage.css" type="text/css" media="screen" />
	
	<script src="<%=basePath %>frontend/javascript/jquery-1.7.2.min.js" type="application/javascript"></script>
  	<script src="<%=basePath %>frontend/javascript/jquery-plugin/imgLiquid/imgLiquid.js" type="application/javascript"></script>
	<script src="<%=basePath %>frontend/javascript/jquery-plugin/flexslider/jquery.flexslider-min.js" type="application/javascript"></script>
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
                  <s:iterator value="homevo.imageUrls" var="imagesrc">
						<li><img src="<%=basePath %><s:property value="#imagesrc"/>"/></li>
					</s:iterator>
                </ul>
              </div>
              </div>
              <div id="featureddesigner">
              	<table cellpadding="0" cellspacing="0" rules="rows">
                	<tr>
                		<th colspan="2"><a href="#"><s:text name="featuredDesigner"/></a></th>
                    </tr>
                    <tr align="center" valign="middle">
                    	<td>
                        	<div class="imgLiquidFill imgLuid">
                            	<a href="menus/designer/main.jsp?designerId=<s:property value='homevo.featuredDesignerVO.designer.designerId'/>"><img src="<%=basePath %><s:property value='homevo.featuredDesignerVO.thumbnailUrl'/>"  /></a>
                             </div>
                         </td>
                        <td><a href="menus/designer/main.jsp?designerId=<s:property value='homevo.featuredDesignerVO.designer.designerId'/>" ><s:property value="homevo.featuredDesignerVO.name"/></a></td>
                    </tr>
                    <s:iterator value="homevo.designerVOs" var="designervo">
                    	 <tr align="center" valign="middle">
	                    	<td>
	                        	<div class="imgLiquidFill imgLuid">
	                            	<a href="menus/designer/main.jsp?designerId=<s:property value='#designervo.designer.designerId'/>"><img src="<%=basePath %><s:property value='#designervo.thumbnailUrl'/>"  /></a>
	                             </div>
	                         </td>
	                        <td><a href="menus/designer/main.jsp?designerId=<s:property value='#designervo.designer.designerId'/>" ><s:property value="#designervo.name"/></a></td>
	                    </tr>
                    </s:iterator>
                </table>
              </div>
          </div>
          <div id="main_center">
          	<div id="videodiv">
            	<h3><a href="#"><s:text name="video"/></a></h3>
                <hr />
            	<video controls="controls" autoplay="autoplay"  height="340px" width="500px">
                	<source src="<%=basePath %><s:property value='homevo.videoUrl'/>" >
                    Your browser dose not support the video tag.
                </video>
            </div>
            <div id="profilediv">
            	<h3><a href="#"><s:text name="profile"/></a></h3>
                <hr />
                <textarea name="designerProfile"><s:property value="homevo.featuredDesignerVO.profile"/></textarea>
            </div>
          </div>
          <div id="main_bottom" style="margin-top:2px;">
          	<h3><a href="#"><s:text name="collection"/></a></h3>
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
            <s:iterator value="homevo.lookbookVOs" var="lookbookvo">
            	<tr align="center" valign="middle">
	            	<s:iterator value="#lookbookvo.images" var="image">
	            		<td><div  class="imgLiquidFill imgLuid" style="width:146px; height:220px; padding:5px;"><img src="<%=basePath %><s:property value='#image'/>"</div></td>
	            	</s:iterator>
	            </tr>
            </s:iterator>
            </table>
          </div>
        </div>
		</div>
		<jsp:include page="footer.jsp"></jsp:include>
  </body>
</html>
