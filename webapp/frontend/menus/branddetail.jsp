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
	
	<script type="text/javascript">
	$(document).ready(function() {
		$(".imgLiquidFill").imgLiquid();
	});
</script>
  </head>
  
  <body  oncontextmenu="return false" onselectstart="return false">
  	<div id="wrapper">
  		<div id="contentwrapper">
		    <div id="portratewrapper">
		    	<div class="portrate imgLiquidFill imgLiquid">
		    		<img src="<%=basePath %><s:property value='brandVO.brand.brandimg'/>"  />
		    	</div>
		    </div>
		    <div id="contwrapper">
		    	<table class="tablecss">
		            <tr style="width:100%">
		                <td class="label"colspan="2"><strong><s:text name="brandname" />:&nbsp;&nbsp;</strong><s:property value="brandVO.name"/></td>
		            </tr>
		             <tr style="width:100%">
		                <td class="label"colspan="2"><strong><s:text name="establishyear"/>:&nbsp;&nbsp;</strong><s:property value="brandVO.brand.establishyear"/></td>
		            </tr>
		        	<tr style="width:100%">
		                <td class="label"colspan="2"><strong><s:text name="storenum"/>:&nbsp;&nbsp;</strong><s:property value="brandVO.brand.storenum"/></td>
		           </tr>
		           <tr style="width:100%">
		           	<td  class="label" colspan="2"><strong><s:text name="pricerange"/>:&nbsp;&nbsp;</strong><s:property value="brandVO.pricerange"/></td>
		           </tr>
		           <tr style="width:100%">
		                <td class="label" colspan="2"><strong><s:text name="latest" />:&nbsp;&nbsp;</strong><s:property value="brandVO.lastest"/></td>
		           </tr>
		           <tr  style="width:100%">
		                <td class="label" colspan="2"><strong><s:text name="estimate" />:&nbsp;&nbsp;</strong><s:property value="brandVO.estimate"/></td>
		           </tr>
		            <tr style="width:100%">
		                <td class="label" colspan="2"><strong><s:text name="target"/>:&nbsp;&nbsp;</strong><s:property value="brandVO.targetcustomer"/></td>
		            </tr>
		            <tr style="width:100%">
		                <td class="label" colspan="2"><strong><s:text name="mission" />:&nbsp;&nbsp;</strong><s:property value="brandVO.brandmission"/></td>
		            </tr>
		          
		            <tr style="width:100%">
		                <td class="label" colspan="2"><strong><s:text name="sellpoint"/>:&nbsp;&nbsp;</strong><br /> <s:property value="brandVO.sellingpoint"/> </td>
		            </tr>
		              <tr style="width:100%">
		                <td class="label" colspan="2"><strong><s:text name="model" />:&nbsp;&nbsp;</strong><br /><s:property value="brandVO.operationmodel"/> </td>
		            </tr>
		            <tr style="width:100%">
		                <td class="label" colspan="2"><strong><s:text name="website" />:&nbsp;&nbsp;</strong><a href="<s:property value='brandVO.brand.officialwebsite' />"><s:property value="brandVO.brand.officialwebsite"/></a></td>
		            </tr>
		        </table>
		    </div>
  		</div>
  	</div>
  </body>
</html>
