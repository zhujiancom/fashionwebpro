<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
String basePath = serverPath + path + "/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
  <head>
    
    <title>HOMEPAGE</title>
    <link rel="icon" type="image/png" href="<%=basePath %>favicon.ico" />
    <link rel="stylesheet" href="<%=basePath%>comm_script/jquery-ui/1.9/theme/jquery.ui.all.css"/>
    <link rel="stylesheet" href="<%=basePath %>frontend/javascript/jquery-plugin/flexslider/flexslider.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="<%=basePath %>frontend/css/homepage.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="<%=basePath%>frontend/javascript/jquery-plugin/magnific-popup/magnific-popup.css"/>
	
	<script src="<%=basePath %>comm_script/jquery-1.9.1.min.js" type="application/javascript"></script>
	<script src="<%=basePath %>comm_script/jquery-ui/1.9/ui/jquery.ui.core.js" type="application/javascript"></script>
	<script src="<%=basePath %>comm_script/jquery-ui/1.9/ui/jquery.ui.widget.js" type="application/javascript"></script>
	<script src="<%=basePath %>comm_script/jquery-ui/1.9/ui/jquery.ui.position.js" type="application/javascript"></script>
	<script src="<%=basePath %>comm_script/jquery-ui/1.9/ui/jquery.ui.tooltip.js" type="application/javascript"></script>
	<script type="text/javascript" 
			src="<%=basePath%>comm_script/base.js">
	</script>
  	<script src="<%=basePath %>frontend/javascript/jquery-plugin/imgLiquid/imgLiquid.js" type="application/javascript"></script>
	<script src="<%=basePath %>frontend/javascript/jquery-plugin/flexslider/jquery.flexslider-min.js" type="application/javascript"></script>
	<script type="text/javascript"
			src="<%=basePath %>ckeditor/ckeditor.js">
	</script>
	<script src="<%=basePath%>frontend/javascript/jquery-plugin/magnific-popup/jquery.magnific-popup.js" type="text/javascript"></script>
	
	<style type="text/css">
		#lookbookImgsContainer{
			width:895px;
			position:relative;
			padding:5px;
		}
		
		#lookbookImgsContainer ul{
			width:895px;
		}
		#lookbookImgsContainer ul li{
			line-height:228px;
			list-style: none;
			float:left;
			padding-right:15px;
		}
		
	</style>
	
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
                		<th colspan="2"><s:text name="featuredDesigner"/></th>
                    </tr>
                    <tr align="center" valign="middle">
                    	<td>
                        	<div class="imgLiquidFill imgLuid">
                            	<a href="<%=basePath %>designer/main/<s:property value="homevo.featuredDesignerVO.designer.designerId"/>.html"><img src="<%=basePath %><s:property value='homevo.featuredDesignerVO.thumbnailUrl'/>"  /></a>
                             </div>
                         </td>
                        <td><a href="<%=basePath %>designer/main/<s:property value="homevo.featuredDesignerVO.designer.designerId"/>.html"><s:property value="homevo.featuredDesignerVO.name"/></a></td>
                    </tr>
                    <s:iterator value="homevo.designerVOs" var="designervo">
                    	 <tr align="center" valign="middle">
	                    	<td>
	                        	<div class="imgLiquidFill imgLuid">
	                            	<a href="<%=basePath %>designer/main/<s:property value='#designervo.designer.designerId'/>.html"><img src="<%=basePath %><s:property value='#designervo.thumbnailUrl'/>"  /></a>
	                             </div>
	                         </td>
	                        <td><a href="<%=basePath %>designer/main/<s:property value='#designervo.designer.designerId'/>.html" ><s:property value="#designervo.name"/></a></td>
	                    </tr>
                    </s:iterator>
                </table>
              </div>
          </div>
          <div id="main_center">
          	<div id="videodiv">
            	<h3><s:text name="video"/></h3>
                <hr />
            	<video src="<%=basePath %><s:property value='homevo.videoUrl'/>" controls="controls" autoplay="autoplay"  height="340px" width="500px" title="<s:text name='signintip.videosetting'/>">
                    Your browser dose not support the video tag.
                </video>
            </div>
            <div id="profilediv">
            	<h3><s:text name="profile"/></h3>
                <hr />
                <textarea name="designerProfile"><s:property value="homevo.featuredDesignerVO.profile"/></textarea>
            </div>
          </div>
          <div id="main_bottom" style="margin-top:2px;height:600px;">
          	<h3><s:text name="collection"/></h3>
            <hr />
			<s:iterator value="homevo.lookbookVOs" var="lookbookvo">
				<h4><p style="font-size:14px;"><s:property value="#lookbookvo.name"/></p></h4>
				<div>
					<div id="lookbookImgsContainer" class="popup-gallery">
						<ul>
							<s:iterator value="#lookbookvo.images" var="image" status="serial">
								<s:if test="#serial.count > 5">
									<li style="display:none;"><div class="imgLiquidFill imgLuid" style="width:146px; height:220px; padding:5px;"><a href="<%=basePath %><s:property value='#image'/>"><img src="<%=basePath %><s:property value='#image'/>"/></a></div></li>
								</s:if>
								<s:else>
									<li><div class="imgLiquidFill imgLuid" style="width:146px; height:220px; padding:5px;"><a href="<%=basePath %><s:property value='#image'/>"><img src="<%=basePath %><s:property value='#image'/>"/></a></div></li>
								</s:else>
							</s:iterator>
						</ul>
						<div style="clear:both"></div>
					</div>
				</div>
			</s:iterator>
          </div>
        </div>
        <div>
        <jsp:include page="footer.jsp"></jsp:include>
        </div>
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
				 
				var msg = "${msg}";
				if(msg != ""){
					feedbackInfo(msg,"TIPS");
				}

				var loginuser = "${session.login_account_session.accountId}";
					$('.popup-gallery').each(function(){
						if(loginuser != 0){
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
						}else{
							$("a",this).click(function(event){
								feedbackInfo("<s:text name='signintip.menushow'/>","TIPS");
								event.preventDefault();
							});
						}
					});
			  });
			
			$( document ).tooltip();
		</script>
  </body>
</html>
