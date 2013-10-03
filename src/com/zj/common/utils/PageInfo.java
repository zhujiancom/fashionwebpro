package com.zj.common.utils;

import java.util.List;

/**
 * 
 * @author zj
 * 
 *         项目名称：basis
 * 
 *         类名称：PageInfo
 * 
 *         包名称：com.zj.common.util
 * 
 *         Operate Time: 2012-6-19 上午09:47:48
 * 
 *         remark (备注): 分页对象
 * 
 *         文件名称：PageInfo.java
 * 
 */
public class PageInfo<T> {
	private int pageSize; // 每页显示条数
	private int pageNum; // 当前页码
	private int rowCount; // 总行数
	private int pageCount; // 总页数
	List<T> objectList; // 页面内容

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List<T> getObjectList() {
		return objectList;
	}

	public void setObjectList(List<T> objectList) {
		this.objectList = objectList;
	}

}
