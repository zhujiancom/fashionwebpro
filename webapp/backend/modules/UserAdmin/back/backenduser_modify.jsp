<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@page import="com.zj.bigdefine.GlobalParam"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>backend/">
    
    <title>modify user</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link href="<%=basePath %>comm_style/base.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" 
    		src="<%=basePath %>comm_script/jquery-1.6.2.min.js">
    </script>
    <script type="text/javascript" 
    		src="<%=basePath%>comm_script/dialog/lhgdialog.min.js">
    </script>
    <script type="text/javascript" 
    		src="<%=basePath%>comm_script/jquery-plugin/jquery-ui-1.8.16.custom.min.js">
    </script>
    <script type="text/javascript" 
    		src="<%=basePath %>comm_script/base.js">
    </script>
  </head>
  
  <body>
    <div id="wrapper">
   	 	<form id="fm" action="sysuser_updateUser.action" method="post" target="main" enctype="multipart/form-data">
   	 		<input type="hidden" name="user.userId" value="<s:property value="user.userId"/>"/>
   	 		<input type="hidden" name="user.imgUrl" value="<s:property value="user.imgUrl"/>"/>
   	 		<div class="rowElem">
	   			<label>Chinese Name：</label>
	   			<input name="user.cname" id="cname" type="text" value="<s:property value='user.cname'/>" />
	    	</div>
	    	<div class="rowElem">
	   			<label>English Name：</label>
	   			<input name="user.ename" id="ename" type="text" value="<s:property value='user.ename'/>" />
	    	</div>
	    	<div class="rowElem">
	   			<label>Nick Name：</label>
	   			<input name="user.nickname" id="nickname" type="text" value="<s:property value='user.nickname'/>" />
	    	</div>
	    	<div class="rowElem">
	   			<label>Birthday：</label>
	   			<input type="text" name="user.birthday" class="datepicker" value="<s:date name='user.birthday' format='yyyy-MM-dd' />" />
	    	</div>
	    	<div class="rowElem">
	   			<label>Login ID：</label>
	   			<input name="user.loginId"  type="text" class="unique require" action="sysuser_uniqueLoginIdCheck.action" value="<s:property value='user.loginId'/>"/>
	    	</div>
	    	<div class="rowElem">
	   			<label>Status：</label>
	   			<s:set var="isenable" value="user.isEnable"/>
	   			<s:if test="#isenable == 1">
	   				<input type="radio" name="user.isEnable" checked value="<s:property value='@com.zj.bigdefine.GlobalParam@YES'/>"/><label>启用</label>&nbsp;&nbsp;
	   				<input type="radio" name="user.isEnable" value="<s:property value='@com.zj.bigdefine.GlobalParam@NO'/>"/><label>不启用</label>
	   			</s:if>
	   			<s:else>
	   				<input type="radio" name="user.isEnable" value="<s:property value='@com.zj.bigdefine.GlobalParam@YES'/>"/><label>启用</label>&nbsp;&nbsp;
	   				<input type="radio" name="user.isEnable" checked  value="<s:property value='@com.zj.bigdefine.GlobalParam@NO'/>"/><label>不启用</label>
	   			</s:else>
	   		</div>
	   		<div class="rowElem" id="nationality">
	   			<label>Nationality: </label>
	   			<select name="user.nationality" referenceKey="NATIONALITY"
						style="width: 128px;">
						<option value="">
							---Plseas Select---
						</option>
				</select>
	    	</div>
	    	<div class="rowElem">
	   			<label>NativePlace：</label>
   				<input name="user.nativeplace"  type="text" value="<s:property value='user.nativeplace'/>"/>
	   		</div>
	   		<div class="rowElem">
	   			<label>Province：</label>
   				<input name="user.province"  type="text" value="<s:property value='user.province'/>" />
	   		</div>
	   		<div class="rowElem">
	   			<label>City：</label>
   				<input name="user.city"  type="text" value="<s:property value='user.city'/>"/>
	   		</div>
	    	<div class="rowElem" id="sex">
	   			<label>Gender: </label>
	   			<select name="user.sex" referenceKey="SEX"
						style="width: 128px;">
						<option value="">
							---Plseas Select---
						</option>
				</select>
	    	</div>
	    	<div class="rowElem">
	   			<label>E-Mail：</label>
	   			<input name="user.email"  type="text" rex="^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$|^(\s{0})$" value="<s:property value='user.email'/>"/>
	    	</div>
	    	<div class="rowElem">
	   			<label>MobilePhone：</label>
	   			<input name="user.moblie" rex="^\d{11}$|^\s{0}$" type="text" value="<s:property value='user.moblie'/>"/>
	    	</div>
	    	<div class="rowElem">
	   			<label>QQ：</label>
	   			<input name="user.qqnum" rex="[1-9][0-9]{4,}|^\s{0}$"  type="text" value="<s:property value='user.qqnum'/>"/>
	    	</div>
	    	<div class="rowElem">
	   			<label>Self-sign：</label>
				<textarea rows="2" cols="50" name="user.selfsign"><s:property value="user.selfsign"/></textarea>
	    	</div>
	    	<div class="rowElem">
	   			<label>Head Portrait：</label>
	   			<input type="file" name="imageFile"/>
	    	</div>
	    	<br />
			<br />
			<div class="rowElem"
				style="float: right; position: relative; right: 20px;">
				<input type="submit" class="big_submitButton_class" value="SUBMIT" />
			</div>
    	</form>
    </div>	
  </body>
</html>
