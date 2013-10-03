<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@page import="com.zj.bigdefine.GlobalParam"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>Category Menu</title>
    <base href="<%=basePath %>backend/"/>
    
	<link rel="stylesheet" href="<%=basePath %>comm_script/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"></link>
	<link href="<%=basePath %>backend/css/admin.css" type="text/css" rel="stylesheet"></link>
	<script type="text/javascript" src="<%=basePath %>comm_script/jquery-1.6.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>comm_script/ztree/jquery.ztree.all-3.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>comm_script/dialog/lhgdialog.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>comm_script/base.js"></script>
	<script type="text/javascript">
		var ZtreeSetting = {
	            data: {
	                simpleData: {
	                    enable: true,
	                    idKey: "id",
	                    pIdKey: "pid",
	                    rootPId: null
	                }
	            },
	            key:{
	            	name:"name"
	            },
	            async: {//异步加载
	                enable: true, //开启异步加载
	                url:"category_treeList.action",
	                dataFilter: ajaxDataFilter,
	                type:"post",
	                autoParam: ["id"]
	            },
	            view: {
	            	dblClickExpand:dblClickExpand,
	                showLine: false,
	                selectedMulti: false,
	                expandSpeed: ($.browser.msie && parseInt($.browser.version) <= 6) ? "" : "fast"
	            },
	            callback: {
	                beforeClick: function(treeId, treeNode) {
	            		var parentWin = window.parent.frames['category_main'];
	                	var zTree = $.fn.zTree.getZTreeObj("MenuTree");
	                	if(treeNode.isParent && treeNode.isenable == <%=GlobalParam.DISABLE%>){
	                		treeNode.url = "";
	                		zTree.updateNode(treeNode);
							parentWin.$.dialog.alert("此项已被禁用,若想使用,请先启用!");
						}
	                },
	                beforeExpand:zTreeBeforeExpand,
	                onClick: treeNodeClick
	            }
	
	        };
		
		function zTreeBeforeExpand(treeId,treeNode){
			return treeNode.isParent && treeNode.isenable == <%=GlobalParam.ENABLE%>;
		}
		
		function dblClickExpand(treeId,treeNode){
			return treeNode.level >0 ;
		}
		
		function treeNodeClick(event, treeid, treeNode) {
			var parentWin = window.parent.frames['category_main'];
			if(!treeNode.isParent && treeNode.isenable == <%=GlobalParam.ENABLE%>){
				openDialog(treeNode.name,treeNode.url,800,600,parentWin);
			}
			if(!treeNode.isParent && treeNode.isenable == <%=GlobalParam.DISABLE%>){
				parentWin.$.dialog.alert("此项已被禁用,若想使用,请先启用!");
			}
		};
		
		function RefreshTreeNode(nodeID) {
	        var zTree = $.fn.zTree.getZTreeObj("MenuTree"),
			node = zTree.getNodeByParam("id", nodeID, null);
	        if (!node) {
	            return;
	        }
	        zTree.reAsyncChildNodes(node.getParentNode(), "refresh", false);
	    };
	    
	    function ajaxDataFilter(treeid, parentNode, childNodes) {
	    	if (!childNodes) return null;
	        for (var i=0, l=childNodes.length; i<l; i++) {
					if(childNodes[i].id == 0 ){
						childNodes[i].icon = "<%=basePath%>comm_script/ztree/css/zTreeStyle/img/display.png";
					}
					if(childNodes[i].isenable == <%=GlobalParam.DISABLE%>){
						childNodes[i].icon = "<%=basePath%>comm_script/ztree/css/zTreeStyle/img/diy/X.gif";
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
  
  <body>
    <div title="菜单列表" style="height:100%;padding:0;margin:0;width:170px;position:left">
            <ul id="MenuTree" class="ztree" style="width: 90%;">
            </ul>
    </div>
  </body>
</html>
