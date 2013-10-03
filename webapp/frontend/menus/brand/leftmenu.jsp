<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>frontend/">
    
    <title>My JSP 'leftmenu.jsp' starting page</title>
    <link rel="stylesheet" href="<%=basePath %>comm_script/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"></link>
	<script type="text/javascript" src="<%=basePath %>comm_script/jquery-1.6.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>comm_script/ztree/jquery.ztree.all-3.2.min.js"></script>
	
	<script type="text/javascript">
		$(function(){
			var msg = "${msg}";
			if(msg != ""){
				$.dialog.tips(msg);			
			}
		});
	</script>
	
    <script type="text/javascript">
		var ZtreeSetting = {
	            data: {
	                simpleData: {
	                    enable: true,
	                    idKey: "treeid",
	                    pIdKey: "pid",
	                    rootPId: null
	                }
	            },
	            key:{
	            	name:"name"
	            },
	            async: {//异步加载
	                enable: true, //开启异步加载
	                url:"brand_menuTree.action",
	                dataFilter: ajaxDataFilter,
	                type:"post",
	                autoParam: ["treeid","nodetype"],
	                otherParam:{
            			"brand.brandid":"<%=request.getParameter("brandId") %>"
            			}
	            	},
	            view: {
	            	dblClickExpand:dblClickExpand,
	                showLine: false,
	                selectedMulti: false,
	                expandSpeed: ($.browser.msie && parseInt($.browser.version) <= 6) ? "" : "fast",
	                showIcon: false
	            },
	            callback: {
	                beforeClick: function(treeId, treeNode) {
	                	var zTree = $.fn.zTree.getZTreeObj("MenuTree");
	                	if(treeNode.isParent){
	                		treeNode.url = "";
	                		zTree.updateNode(treeNode);
						}
	                },
	                beforeExpand:zTreeBeforeExpand,
	                onClick: treeNodeClick
	            }
	
	        };
		
		function zTreeBeforeExpand(treeId,treeNode){
			return treeNode.isParent;
		}
		
		function dblClickExpand(treeId,treeNode){
			return treeNode.level >0 ;
		}
		
		function treeNodeClick(event, treeid, treeNode) {
			var parentWin = window.parent.frames['mainPanel'];
		};
		
		function RefreshTreeNode(nodeID) {
	        var zTree = $.fn.zTree.getZTreeObj("MenuTree"),
			node = zTree.getNodeByParam("treeid", nodeID, null);
	        alert(node);
	        if (!node) {
	            return;
	        }
	        zTree.reAsyncChildNodes(node.getParentNode(), "refresh", false);
	    };
	    
	    function ajaxDataFilter(treeid, parentNode, childNodes) {
	    	if (!childNodes){
	    		return null;
	    	}
	        for (var i=0, l=childNodes.length; i<l; i++) {
					if(childNodes[i].treeid == 0 ){
						childNodes[i].icon = "<%=basePath%>comm_script/ztree/css/zTreeStyle/img/display.png";
					}
					if(typeof childNodes[i].url === "string" && childNodes[i].url != ""){
						childNodes[i].url = "<%=basePath%>"+childNodes[i].url;
					}
			}
	        return childNodes;
	    }
	    $(function(){
	    	var ztreeNode = $.fn.zTree.init($("#MenuTree"),ZtreeSetting,null);
	    });
	</script>
	<style type="text/css">
		.ztree li span.button.switch.level0 {visibility:hidden; width:1px;}
		.ztree li ul.level0 {padding:0; background:none;}
	</style>

  </head>
  
  <body  oncontextmenu="return false" onselectstart="return false">
   	<ul id="MenuTree" class="ztree" style="width: 90%;">
    </ul>
  </body>
</html>
