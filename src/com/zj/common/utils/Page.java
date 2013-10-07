package com.zj.common.utils;

import java.util.List;

public class Page<T> {
	private int totalRows;  //总行数
	private int pageSize;  //每页显示的行数
	private int currentPage;  //当前页号
	private int totalPages;  //总页数
	private int startRow;   //当前页在数据库中的起始行
	
	
	private List<T> list;
	
	public Page(int _totalRows,int currentPage,int _pageSize){
		totalRows = _totalRows;
		pageSize = _pageSize;
		//currentPage = _currentPage;
		//计算总页数
		totalPages = totalRows / pageSize;
		int mod = totalRows % pageSize;
		if(mod > 0){
			totalPages++;
		}
		this.currentPage = currentPage;
		startRow = 0;
	}
	
	public int getCurrentPage(){
		return currentPage;
	}
	public void setCurrentPage(int currentPage){
		this.currentPage = currentPage;
	}
	/*
	 * 获得每页显示的行数
	 * return
	 * */
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/*
	 * 获得当前页在数据库中的起始行
	 * return
	 * */

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	
	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	/*
	 * 第一页
	 */
	public void first(){
		currentPage = 1;
		startRow = 0;
	}
	/*
	 * 前一页
	 * */
	public void previous(){
		if(currentPage == 1){
			return;
		}
		currentPage--;
		startRow = (currentPage-1)*pageSize;
	}
	/*
	 * 下一页
	 * */
	public void next(){
		if(currentPage < totalPages){
			currentPage++;
		}
		startRow = (currentPage-1)*pageSize;
	}
	/*
	 * 最后一页
	 * */
	public void last(){
		currentPage = totalPages;
		startRow = (currentPage-1)*pageSize;
	}
	/*
	 * 跳转到某一页
	 * */
	public void any(){
		if(currentPage < 1){
			currentPage = 1;
		}
		if(currentPage > totalPages){
			currentPage = totalPages;
		}
		startRow = (currentPage-1)*pageSize;
	}
	public void refresh(int _currentPage){
		currentPage = _currentPage;
		if(currentPage > totalPages){
			last();
		}
	}
}
