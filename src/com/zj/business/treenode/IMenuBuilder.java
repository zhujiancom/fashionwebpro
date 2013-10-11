package com.zj.business.treenode;

import java.util.List;

import com.zj.common.exception.ServiceException;


public interface IMenuBuilder {
	public List<Menu> createMenuTree() throws ServiceException;
}
