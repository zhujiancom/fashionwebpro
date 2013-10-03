<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>菜单页面</title>
    <LINK href="<%=basePath %>back/css/admin.css" type="text/css" rel="stylesheet">
    <link rel="stylesheet" href="../comm_script/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"></link>
 	<script type="text/javascript" src="../comm_script/jquery-1.6.2.min.js"></script>
	<script type="text/javascript" src="../comm_script/ztree/jquery.ztree.all-3.2.min.js"></script>
	<script type="text/javascript">
	var rootPath = "../";
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
                url: rootPath + "menu/menu_ztree.action",
                dataFilter: ajaxDataFilter,
                autoParam: ["id", "nodetype"]
            },
            view: {
                showLine: false,
                selectedMulti: false,
                expandSpeed: ($.browser.msie && parseInt($.browser.version) <= 6) ? "" : "fast"
            },
            callback: {
                beforeClick: function(treeId, treeNode) {
                var zTree = SkyTree.getZTreeObj("MenuTree");
                if (treeNode.isParent
                    && treeNode.nodetype != "tailorform"
                    && treeNode.nodetype != "newtailorform"
                    && treeNode.nodetype != "workflowchart"
                    && treeNode.nodetype != "newworkflowchart"
                    && treeNode.nodetype != "newpunishworkflow") {
                        zTree.expandNode(treeNode);
                        return false;
                    }
                    else {
                        return true;
                    }
                },
                onClick: treeNodeClick
            }

        };
	
	function treeNodeClick(event, treeid, treeNode) {};
	
	function RefreshTreeNode(nodeID) {
        var zTree = SkyTree.getZTreeObj("MenuTree"),
		node = zTree.getNodeByParam("id", nodeID, null);
        if (!node) {
            return;
        }
        zTree.reAsyncChildNodes(node.getParentNode(), "refresh", false);
    };
    
    function ajaxDataFilter(treeid, parentNode, childNodes) {
        if (!childNodes) return null;
        return childNodes;
    }
    $(function(){
    	var ztreeNode = $.fn.zTree.init($("#MenuTree"),ZtreeSetting,null);
    });
	</script>
	</head>
 <body>
	<div title="菜单列表" style="background-image:url(images/menu_bg.jpg);height:100%;padding:0;margin:0;width:170px" position="left">
            <ul id="MenuTree" class="ztree" style="width: 90%;">
            </ul>
    </div>
  </body>
</html>
