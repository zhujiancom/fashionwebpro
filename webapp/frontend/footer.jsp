<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s"%>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="<%=basePath%>frontend/css/footer.css" type="text/css" rel="stylesheet"/>

  	<div id="footerWrapper">
    	<hr />
        <ul class="list_commons">
        	<li><a href="#"><s:text name="footer.aboutus"/></a></li>
        	<li><a href="#"><s:text name="footer.sitemap"/></a></li>
            <li><a href="#"><s:text name="footer.legalStatement"/></a></li>
        	<li style="text-align:center"><a href="#"><s:text name="footer.links"/></a></li>
            <li>
            	<ul class="list_common_sub">
                	<li><a href="#"><img src="<%=basePath %>frontend/images/icons/facebook.png" /></a></li>
                    <li><a href="#"><img src="<%=basePath %>frontend/images/icons/twitter.png" /></a></li>
                    <li><a href="#"><img src="<%=basePath %>frontend/images/icons/academia.png" /></a></li>
                    <li><a href="#"><img src="<%=basePath %>frontend/images/icons/weibo.png" /></a></li>
                    <li><a href="#"><img src="<%=basePath %>frontend/images/icons/weixin.png" /></a></li>
                </ul>
            </li>
        </ul>
        <div style="clear:both;"></div>
        <br />
        <p class="copyright"><s:text name="footer.copyright1"/></p>
        <br />
        <p class="copyright">©  <s:text name="footer.copyright2"/></p>
    </div>
