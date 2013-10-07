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
    
    <title>My JSP 'result.jsp' starting page</title>
    
	
	<link href="css/newsbrowser.css" rel="stylesheet" />
	<link href="javascript/jquery-plugin/jpages/jPages.css" rel="stylesheet"/>
	<script src="javascript/jquery-1.7.2.min.js" type="application/javascript"></script>
	<script src="javascript/jquery-plugin/jpages/jPages.js" type="application/javascript"></script>
	<script type="text/javascript"
			src="<%=basePath%>comm_script/dialog/lhgdialog.min.js">
	</script>
	
	
	<script type="text/javascript">
		$(document).ready(function() {

			/* initiate plugin */
    		$("#designerholder").jPages({
      			containerID : "designerGrid",
      			previous : "←",
      	        next : "→",
				perPage:5,
				delay : 20
    		});
			
    		$("#brandholder").jPages({
      			containerID : "brandGrid",
      			previous : "←",
      	        next : "→",
				perPage:5,
				delay : 20
    		});

    		$("#styleholder").jPages({
      			containerID : "styleGrid",
      			previous : "←",
      	        next : "→",
				perPage:5,
				delay : 20
    		});
    		
    		/* when button is clicked */
    		$("button").click(function(){
     			 /* get given page */
     			 var page = parseInt( $("input").val() );

	     		 /* jump to that page */
	     		 $("div.holder").jPages( page );

   	 		});
			
			var designerMsg = "${designerMsg}";
			var brandMsg = "${brandMsg}";
			var styleMsg = "${styleMsg}";
			
			if(designerMsg != ""){
				$.dialog.tips(designerMsg);			
			}
			if(brandMsg != ""){
				$.dialog.tips(brandMsg);			
			}
			if(styleMsg != ""){
				$.dialog.tips(styleMsg);			
			}
		});

		
	</script>
	
	<style type="text/css">
		.holder {
		  margin: 15px 270px 0;
		}
		table{ width: 100%; margin-top: 30px; }
	    td, th{ text-align: left; height:25px; }
	    th { background: #f5f5f5; }
	    th:nth-child(1){ width:10%; }
	    th:nth-child(2){ width:10%; }
	    th:nth-child(3){ width:60%; }
	    th:nth-child(4){ width:20%;}
		
  </style>
  </head>
  
  <body  oncontextmenu="return false" onselectstart="return false">
  	<div id="wrapper">
  		<jsp:include page="/frontend/header.jsp"></jsp:include>
  		 <div id="main_wrapper">
			<div id="main_lower" class="defaults">
				<h4><font color="red">Designer Resultset</font></h4>
			    <table>
			        <thead><tr><th>Serial</th><th>Rank</th><th>Name</th><th>OfficialWebsite</th></tr></thead>
			        <tbody id="designerGrid">
			        	<s:iterator value="designers" var="designer" status="serial">
			        		<tr>
			        			<td><s:property value="#serial.count"/></td>
			        			<td><s:property value="#designer.rank"/></td>
			        			<td><s:property value="#designer.ename"/></td>
			        			<td><a href="<s:property value='#designer.officialwebsite'/>"><s:property value="#designer.officialwebsite"/></a></td>
			        		</tr>
			        	</s:iterator>
			        </tbody>
		        </table>  
		      <!-- navigation holder -->
     			<div class="holder" id="designerholder"></div>
     			
     			<h4><font color="red">Brand Resultset</font></h4>
			    <table>
			        <thead><tr><th>Serial</th><th>Name</th><th>PriceRange</th><th>OfficialWebsite</th></tr></thead>
			        <tbody id="brandGrid">
			        	<s:iterator value="brands" var="brand" status="serial">
			        		<tr>
				        		<td><s:property value="#serial.count"/></td>
				        		<td><s:property value="#brand.brandEname"/></td>
			        			<td><s:property value="#brand.pricerange"/></td>
			        			<td><a href="<s:property value='#brand.officialwebsite'/>"><s:property value="#brand.officialwebsite"/></a></td>
				        	</tr>
			        	</s:iterator>
			        </tbody>
		        </table>  
		      <!-- navigation holder -->
     			<div class="holder" id="brandholder"></div>
     			
     			<h4><font color="red">Style Resultset</font></h4>
			    <table>
			        <thead><tr><th>Serial</th><th>Name</th></tr></thead>
			        <tbody id="styleGrid">
			        	<s:iterator value="styles" var="style" status="serial">
			        		<tr>
				        		<td><s:property value="#serial.count"/></td>
				        		<td><s:property value="#style.styleEname"/></td>
				        	</tr>
			        	</s:iterator>
			        </tbody>
		        </table>  
		      <!-- navigation holder -->
     			<div class="holder" id="styleholder"></div>
			</div>
	    </div>
	    <jsp:include page="/frontend/footer.jsp"></jsp:include>
  	</div>
  </body>
</html>
