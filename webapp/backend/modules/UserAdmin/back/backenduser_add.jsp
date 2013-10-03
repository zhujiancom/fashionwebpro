<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>backend/">
    
    <title>Add User Information</title>
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
   	 	<form id="fm" action="sysuser_createUser.action" method="post" target="main" enctype="multipart/form-data">
   	 		<div class="rowElem">
	   			<label>Chinese Name：</label>
	   			<input name="user.cname" id="cname" type="text" />
	    	</div>
	    	<div class="rowElem">
	   			<label>English Name：</label>
	   			<input name="user.ename" id="ename" type="text" />
	    	</div>
	    	<div class="rowElem">
	   			<label>Nick Name：</label>
	   			<input name="user.nickname" id="nickname" type="text" />
	    	</div>
	    	<div class="rowElem">
	   			<label>Birthday：</label>
	   			<input type="text" name="user.birthday" class="datepicker" />
	    	</div>
	    	<div class="rowElem">
	   			<label>Login ID：</label>
	   			<input name="user.loginId"  type="text" class="unique require" action="sysuser_uniqueLoginIdCheck.action"/>
	    	</div>
	    	<div class="rowElem">
	   			<label>Status：</label>
   				<input type="radio" name="user.isEnable" id="isEnable" checked value="1" /><label>Enabled</label>&nbsp;&nbsp;
   				<input type="radio" name="user.isEnable" id="isEnable" value="0" /><label>Disabled</label>
	   		</div>
	   		<div class="rowElem" id="nationality">
	   			<label>Nationality: </label>
	   			<select name="user.nationality" referenceKey="NATIONALITY"
						style="width: 128px;">
				</select>
	    	</div>
	    	<div class="rowElem">
	   			<label>NativePlace：</label>
   				<input name="user.nativeplace"  type="text" />
	   		</div>
	   		<div class="rowElem">
	   			<label>Province：</label>
   				<input name="user.province"  type="text" />
	   		</div>
	   		<div class="rowElem">
	   			<label>City：</label>
   				<input name="user.city"  type="text" />
	   		</div>
	    	<div class="rowElem" id="sex">
	   			<label>Gender: </label>
	   			<select name="user.sex" referenceKey="SEX"
						style="width: 128px;">
				</select>
	    	</div>
	    	<div class="rowElem">
	   			<label>E-Mail：</label>
	   			<input name="user.email"  type="text" rex="^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$|^(\s{0})$"/>
	    	</div>
	    	<div class="rowElem">
	   			<label>MobilePhone：</label>
	   			<input name="user.moblie" rex="^\d{11}$|^\s{0}$" type="text" />
	    	</div>
	    	<div class="rowElem">
	   			<label>QQ：</label>
	   			<input name="user.qqnum" rex="[1-9][0-9]{4,}|^\s{0}$"  type="text" />
	    	</div>
	    	<div class="rowElem">
	   			<label>Self-sign：</label>
				<textarea rows="2" cols="50" name="user.selfsign"></textarea>
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
