<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>

	<link href="<%=basePath%>frontend/css/footer.css" type="text/css" rel="stylesheet"/>
	<style type="text/css">
		img{
			border:none;
		}
	</style>
	
  	<div id="footerWrapper">
    	<hr />
        <ul class="list_commons">
        	<li><a href="javascript:void(0);" id="aboutus"><s:text name="footer.aboutus"/></a></li>
        	<li><a href="javascript:void(0);"><s:text name="footer.sitemap"/></a></li>
            <li><a href="javascript:void(0);" id="legalstmt"><s:text name="footer.legalStatement"/></a></li>
        	<li style="text-align:center"><a href="javascript:void(0);"><s:text name="footer.links"/></a></li>
            <li>
            	<ul class="list_common_sub">
                    <li><a href="javascript:void(0);"><img src="<%=basePath %>frontend/images/icons/16x16/facebook.png" /></a></li>
                    <li><a href="javascript:void(0);"><img src="<%=basePath %>frontend/images/icons/16x16/twitter.png" /></a></li>
                    <li><a href="javascript:void(0);"><img src="<%=basePath %>frontend/images/icons/16x16/academia.png" /></a></li>
                    <li><a href="javascript:void(0);"><img src="<%=basePath %>frontend/images/icons/16x16/weibo.png" /></a></li>
                    <li><a href="javascript:void(0);"><img src="<%=basePath %>frontend/images/icons/16x16/weixin.png" /></a></li>
                </ul>
            </li>
        </ul>
        <div style="clear:both;"></div>
        <br />
        <p class="copyright"><s:text name="footer.copyright1"/></p>
        <br />
        <p class="copyright">©  <s:text name="footer.copyright2"/></p>
        <script type="text/javascript"
			src="<%=basePath%>comm_script/dialog/lhgdialog.min.js?self=true&skin=chrome">
		</script>
        <script type="text/javascript">
        $(document).ready(function(){
        	$("#legalstmt").click(function(){
        		$.dialog({
        			title : "<s:text name='footer.legalStatement'/>",
        			content : "url:<%=basePath%>frontend/footer_getFooterInfo.action?type=legalstatement",
        			width : 780,
        			height : 540,
        			lock : true,
        			fixed : true,
        			ok:function(){
        				return true;
        			}
        		});
        	});
        	$("#aboutus").click(function(){
        		$.dialog({
        			title : "<s:text name='footer.aboutus'/>",
        			content : "url:<%=basePath%>frontend/footer_getFooterInfo.action?type=aboutus",
        			width : 780,
        			height : 540,
        			lock : true,
        			fixed : true,
        			ok:function(){
        				return true;
        			}
        		});
        	});
        });
        </script>
    </div>
