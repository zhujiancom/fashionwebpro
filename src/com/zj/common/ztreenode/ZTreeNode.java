package com.zj.common.ztreenode;

/**
 * 
 * @author zj
 * 
 *         项目名称：onecard_v2
 * 
 *         类名称：ZTreeNode
 * 
 *         包名称：com.zj.common.tree
 * 
 *         Operate Time: 2012-7-8 下午01:02:55
 * 
 *         remark (备注): 树形节点类，属性跟ztree的treeNode属性匹配
 * 
 *         文件名称：ZTreeNode.java
 * 
 */
public class ZTreeNode {
	private Object id;
	private Object treeid;
	private Object pid;
	private Object name;
	private Object isenable;
	private Object nodetype; // 节点类型
	private Object isParent; // 是否为父节点
	private Object deptno; // 使用部门
	private Object url; // 节点链接地址
	private Object icon; // 图标
	private Object target; 
	private Object open;  //是否打开
	private Object sortnum; //排序号

	public ZTreeNode() {
	}

	public ZTreeNode(Object id, Object pid, Object name, Object isenable,
			Object nodetype, Object isParent, Object deptno, Object url) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.isenable = isenable;
		this.nodetype = nodetype;
		this.isParent = isParent;
		this.deptno = deptno;
		this.url = url;
	}
	
	public ZTreeNode(Object id,Object pid, Object name,Object isParent,Object open,Object url,Object target){
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.isParent = isParent;
		this.open = open;
		this.url = url;
		this.target = target;
	}

	public ZTreeNode(Object id,Object pid,Object name,Object isParent,Object nodetype,Object url){
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.isParent = isParent;
		this.nodetype = nodetype;
		this.url = url;
	}
	
	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public Object getPid() {
		return pid;
	}

	public void setPid(Object pid) {
		this.pid = pid;
	}

	public Object getName() {
		return name;
	}

	public void setName(Object name) {
		this.name = name;
	}

	public Object getIsenable() {
		return isenable;
	}

	public void setIsenable(Object isenable) {
		this.isenable = isenable;
	}

	public Object getNodetype() {
		return nodetype;
	}

	public void setNodetype(Object nodetype) {
		this.nodetype = nodetype;
	}

	public Object getIsParent() {
		return isParent;
	}

	public void setIsParent(Object isParent) {
		this.isParent = isParent;
	}

	public Object getDeptno() {
		return deptno;
	}

	public void setDeptno(Object deptno) {
		this.deptno = deptno;
	}

	public Object getUrl() {
		return url;
	}

	public void setUrl(Object url) {
		this.url = url;
	}

	public Object getIcon() {
		return icon;
	}

	public void setIcon(Object icon) {
		this.icon = icon;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public Object getOpen() {
		return open;
	}

	public void setOpen(Object open) {
		this.open = open;
	}

	public Object getSortnum() {
		return sortnum;
	}

	public void setSortnum(Object sortnum) {
		this.sortnum = sortnum;
	}

	public Object getTreeid() {
		return treeid;
	}

	public void setTreeid(Object treeid) {
		this.treeid = treeid;
	}

	@Override
	public String toString() {
		return "ZTreeNode [id=" + id + ", pid=" + pid + ", name=" + name
				+ ", isenable=" + isenable + ", nodetype=" + nodetype
				+ ", isParent=" + isParent + ", deptno=" + deptno + ", url="
				+ url + ", icon=" + icon + ", target=" + target + ", open="
				+ open + ", sortnum=" + sortnum + "]";
	}
	
}
