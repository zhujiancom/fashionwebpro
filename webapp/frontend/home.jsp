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
    
    <title>Contemporary Fashion Archive</title>
    
	<link rel="stylesheet" href="javascript/jquery-plugin/flexslider/flexslider.css" type="text/css" media="screen" />
  	<link rel="stylesheet" href="css/homepage.css" type="text/css" media="screen" />
  	<link href="javascript/jquery-plugin/osmplayer/osmplayer-2.x/jquery-ui/ui-darkness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
	<link href="javascript/jquery-plugin/osmplayer/osmplayer-2.x/templates/default/css/osmplayer_default.css" rel="stylesheet"/>
	
  	<script src="javascript/jquery-1.7.2.min.js" type="application/javascript"></script>
  	<script src="javascript/base_frontend.js" type="application/javascript"></script>
  	<script type="text/javascript" 
    		src="<%=basePath%>comm_script/jquery-plugin/jquery-ui-1.8.16.custom.min.js">
    </script>
    <script type="text/javascript" src="<%=basePath%>comm_script/dialog/lhgdialog.min.js"></script>
	<script src="javascript/jquery-plugin/osmplayer/osmplayer-2.x/bin/osmplayer.compressed.js" type="application/javascript"></script>
	<script src="javascript/jquery-plugin/osmplayer/osmplayer-2.x/templates/default/osmplayer.default.js" type="application/javascript"></script>
	<script src="javascript/jquery-plugin/osmplayer/osmplayer-2.x/minplayer/src/minplayer.players.flash.js" type="application/javascript"></script>
	
  	<script type="text/javascript">
		$(window).load(function() {
  			$(".flexslider").flexslider({
    			animation: "slide"
  			});
		});
		$(document).ready(function(){
    		$("#scrollDiv").Scroll({line:5,speed:500,up:"btn1",down:"btn2"});
    	});

		var loginMsg = "${loginMsg}";
		if(loginMsg){
			$.dialog.alert(loginMsg);		
		}
	</script>
	<script type="text/javascript">
	  $(function() {
	    $("video").osmplayer({
	      width: '100%',
	      height:'310px'
	    });
	  });
	</script>
  </head>
  
  <body oncontextmenu="return false" onselectstart="return false">
  	<div id="wrapper">
  		<jsp:include page="header.jsp"></jsp:include>
	    <div id="main_wrapper">
    	<div id="main_upper">
        	<div id="imagerotate">
                <div class="flexslider" style="margin-bottom:0;">
                  <ul class="slides">
					<s:iterator value="imagesrcs" var="imagesrc">
						<li><img src="<%=basePath %><s:property value="#imagesrc"/>"/></li>
					</s:iterator>
                  </ul>
                </div>
            </div>
            <div id="featureddesigner">
            	<h3 class="featuredlabel"><s:text name="featuredDesigner"/></h3>
                <hr class="designersep" />
                <div class="designerfield">
                 	<h4 class="fr"><a href="menus/designer/main.jsp?designerId=<s:property value='featuredDesigner.po.designerId'/>"><s:property value="featuredDesigner.name"/></a></h4>
                    <img src="<%=basePath %><s:property value='featuredDesigner.po.imgURL'/>" alt="designer" name="designer" class="designerportrate" />
                </div>
                <hr class="designersep" />
				<s:iterator value="designerlist" var="designervo" status="serial">
				 	<div class="designerfield">
				 		<s:if test="#serial.even">
				 			<h4 class="fr"><a href="menus/designer/main.jsp?designerId=<s:property value='#designervo.po.designerId'/>"><s:property value="#designervo.name"/></a></h4>
				 			<img src="<%=basePath %><s:property value='#designervo.po.imgURL'/>" alt="designer" name="designer" class="designerportrateLeft" />
				 		</s:if>
				 		<s:else>
				 			<h4 class="fl"><a href="menus/designer/main.jsp?designerId=<s:property value='#designervo.po.designerId'/>"><s:property value="#designervo.name"/></a></h4>
				 			<img src="<%=basePath %><s:property value='#designervo.po.imgURL'/>" alt="designer" name="designer" class="designerportrateRight" />
				 		</s:else>
                	</div>
                	<hr class="designersep" />
				 </s:iterator>
                 <span id="designermore"><a href="designer_loadAll.action">More &gt;&gt;</a></span>
            </div>
        </div>
        <div id="main_lower">
        	<div id="lower_left">
            	<div id="lower_left_upper">
                	<div id="lower_video">
                    	<h1><a href="menus/designer/main.jsp?designerId=<s:property value='featuredDesigner.po.designerId'/>"><s:text name="video"/></a></h1>
                        <hr />
                        <video src="<%=basePath %><s:property value='videourl'/>" poster="<%=basePath %><s:property value='poster'/>"></video>
                    </div>
                    <div id="lower_profile">
                    	<h1><a href="menus/designer/main.jsp?designerId=<s:property value='featuredDesigner.po.designerId'/>"><s:text name="profile" /></a></h1>
                        <hr />
                        <div id="profile">
                            <span id="btn1"><img src="images/up-arrow-circle-hi.png" height="25px" width="25px;" /></span>
                        	<div id="scrollDiv">
                                <ul>
                                <br /><br />
                                <li><strong><s:text name="dname" />:</strong><s:property value="featuredDesigner.name"/></li><br/>
                                <li><strong><s:text name="drank" />:</strong><s:property value="featuredDesigner.designer.rank"/></li>
                                <li><strong><s:text name="dbrand" />:</strong><s:property value="featuredDesigner.brands"/></li>
                                <li><strong><s:text name="gender" />:</strong><s:property value="featuredDesigner.gender"/></li>
                                <li><strong><s:text name="nationality" />:</strong><s:property value="featuredDesigner.nationality"/></li>
                                <li><strong><s:text name="livecountry" />:</strong><s:property value="featuredDesigner.livingcountry"/></li>
                                <li><strong><s:text name="livecity" />:</strong><s:property value="featuredDesigner.livingcity"/></li>
                                <li><strong><s:text name="borncountry" />:</strong><s:property value="featuredDesigner.borncountry"/></li>
                                <li><strong><s:text name="borncity" />:</strong><s:property value="featuredDesigner.borncity"/></li>
                                <li><strong><s:text name="bornyear" />:</strong><s:property value="featuredDesigner.designer.bornyear"/></li>
                                <li><strong><s:text name="website" />:</strong><s:property value="featuredDesigner.designer.officialwebsite"/></li>
                                </ul>
                            </div>
                             <span id="btn2"><img src="images/down-arrow-circle-hi.png" height="25px" width="25px;" /></span>
                        </div>
                    </div> 
                </div>
            	<div id="lower_left_lower">
                	<div id="lower_Collections">
                    	<h1><a href="menus/designer/main.jsp?designerId=<s:property value='featuredDesigner.po.designerId'/>"><s:text name="collection"/></a></h1>
                        <hr />
                        <s:set name="lookbookLen" value="lookbooklist.size()"/>
                        <s:if test="#lookbookLen > 2">
                        	<s:set name="lookbookLen" value="1"/>
                        </s:if>
                        <s:iterator value="lookbooklist" var="lookbookvo" begin="0" end="#lookbookLen-1">
                        	<div class="collectionItem">
                        		<span class="collection_title"><s:property value="#lookbookvo.title"/></span>
                        		<div class="collection_imgs">
	                        		<s:set name="imageslength" value="#lookbookvo.images.size()"/>
	                        		<s:if test="#imageslength > 4">
	                        			<s:set name="imageslength" value="4"/>
	                        		</s:if>
		                            <s:iterator value="#lookbookvo.images" var="image" begin="0" end="#imageslength-1">
		                            	<img src="<%=basePath %><s:property value='#image'/>"/>
		                            </s:iterator>
		                        </div>
                        	</div>
                        </s:iterator>
                    </div>
                </div>
            </div>
            <div id="lower_right">
            	<div id="lower_news">
                	<div class="news_title">
                    	<img src="images/what'snew.png" />
                    </div>
                	<div class="news_outline">
                		<s:set name="newslength" value="newslist.size()"/>
                		<s:if test="#newslength > 6">
                			<s:set name="newslength" value="5"/>
                		</s:if>
             	    	<s:iterator value="newslist" var="newsvo" begin="0" end="#newslength-1">
							<div class="news_content">
								<img src="<%=basePath %><s:property value='#newsvo.headImg' />" />
								<p><a href="news_showDetails.action?news.newsId=<s:property value='#newsvo.news.newsId'/>"><s:property value='#newsvo.title' /></a></p>
							</div>
						</s:iterator>
                    </div>
                    
                </div>
            </div>
        </div>
    </div>
  	<jsp:include page="footer.jsp"></jsp:include>
  	</div>
  </body>
</html>
