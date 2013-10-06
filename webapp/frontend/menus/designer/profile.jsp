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
    
	<link href="css/profile.css" rel="stylesheet" />
	<script src="javascript/jquery-1.7.2.min.js" type="application/javascript"></script>
	<script src="javascript/jquery-plugin/imgLiquid/imgLiquid.js" type="application/javascript"></script>
	<script type="text/javascript"
			src="<%=basePath %>ckeditor/ckeditor.js">
	</script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$(".imgLiquidFill").imgLiquid();
		});
		
		var editor = null;
	  $(document).ready(function() {
			//ckeditor
		editor = CKEDITOR.replace("designerProfile",{
			customConfig:"<%=basePath%>/ckeditor/homepage_config.js"
		});
	  });
	</script>
  </head>
  
  <body  oncontextmenu="return false" onselectstart="return false">
  	<div id="wrapper">
  		<div id="contentwrapper">
  			<textarea name="designerProfile"><s:property value="designervo.profile"/></textarea>
<%--		    <div id="portratewrapper">--%>
<%--		    	<div class="portrate imgLiquidFill imgLiquid">--%>
<%--		    		<img src="<%=basePath %><s:property value='designer.po.imgURL'/>"  />--%>
<%--		    	</div>--%>
<%--		    </div>--%>
<%--		    <div id="contwrapper">--%>
<%--		    	<table class="tablecss">--%>
<%--		            <tr style="width:100%">--%>
<%--		                <td class="label"><strong><s:text name="dname" />:&nbsp;&nbsp;</strong><s:property value="designer.name"/></td>--%>
<%--		                <td class="label"><strong><s:text name="gender" />:&nbsp;&nbsp;</strong><s:property value="designer.gender"/></td>--%>
<%--		            </tr>--%>
<%--		        	<tr style="width:100%">--%>
<%--		                <td class="label"><strong><s:text name="nationality" />:&nbsp;&nbsp;</strong><s:property value="designer.nationality"/></td>--%>
<%--		                <td class="label"><strong><s:text name="bornyear" />:&nbsp;&nbsp;</strong><s:property value="designer.po.bornyear"/></td>--%>
<%--		            </tr>--%>
<%--		        	<tr style="width:100%">--%>
<%--		                <td class="label"><strong><s:text name="borncountry" />:&nbsp;&nbsp;</strong><s:property value="designer.borncountry"/></td>--%>
<%--		                <td class="label"><strong><s:text name="borncity" />:&nbsp;&nbsp;</strong><s:property value="designer.borncity"/></td>--%>
<%--		            </tr>--%>
<%--		            <tr style="width:100%">--%>
<%--		                <td class="label"><strong><s:text name="livecountry" />:&nbsp;&nbsp;</strong><s:property value="designer.livingcountry"/></td>--%>
<%--		                <td class="label"><strong><s:text name="livecity" />:&nbsp;&nbsp;</strong><s:property value="designer.livingcity"/></td>--%>
<%--		            </tr>--%>
<%--		            <tr style="width:100%">--%>
<%--		                <td class="label" colspan="2"><strong>SIGNATURES:&nbsp;&nbsp;</strong><s:property value="designer.po.signature"/></td>--%>
<%--		            </tr>--%>
<%--		            <tr style="width:100%">--%>
<%--		                <td class="label" colspan="2"><strong>IDEAL CLIENT:&nbsp;&nbsp;</strong><s:property value="designer.idealclient"/></td>--%>
<%--		            </tr>--%>
<%--		            <tr style="width:100%">--%>
<%--		                <td class="label" colspan="2"><strong>TRADEMARK PIECE:&nbsp;&nbsp;</strong><s:property value="designer.trademarkpiece"/></td>--%>
<%--		            </tr>--%>
<%--		            <tr style="width:100%">--%>
<%--		                <td class="label" colspan="2"><strong>EDUCATION:&nbsp;&nbsp;</strong><br /><s:property value="designer.educationbg"/> </td>--%>
<%--		            </tr>--%>
<%--		              <tr style="width:100%">--%>
<%--		                <td class="label" colspan="2"><strong>PROFESSIONAL CAREER EXPERIENCE:&nbsp;&nbsp;</strong><br /> <s:property value="designer.personalcareerexpr"/></td>--%>
<%--		            </tr>--%>
<%--		            <tr style="width:100%">--%>
<%--		                <td class="label" colspan="2"><strong>CONTACT LIST:&nbsp;&nbsp;</strong><br /><s:property value="designer.contactlist"/> </td>--%>
<%--		            </tr>--%>
<%--		            <tr style="width:100%">--%>
<%--		                <td class="label" colspan="2"><strong><s:text name="website" />:&nbsp;&nbsp;</strong><a href="<s:property value='designer.officialwebsite'/>"><s:property value="designer.officialwebsite"/></a></td>--%>
<%--		            </tr>--%>
<%--		        </table>--%>
<%--		    </div>--%>
	  	</div>
  	</div>
  </body>
</html>
