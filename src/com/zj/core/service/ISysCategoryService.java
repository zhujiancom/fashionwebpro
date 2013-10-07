package com.zj.core.service;

import java.util.List;

import com.zj.common.exception.ServiceException;
import com.zj.common.utils.PageInfo;
import com.zj.common.ztreenode.ZTreeNode;
import com.zj.core.po.SysCategory;
import com.zj.core.po.SysCategoryGroup;

public interface ISysCategoryService extends ICommonService{
	public List<ZTreeNode> getCategoryTreeList(long parentId) throws ServiceException;
	public PageInfo<SysCategoryGroup> loadCategoryGroupList(int pageSize,int pageNum) throws ServiceException;
	public PageInfo<SysCategoryGroup> searchGroupList(int pageSize,int pageNum,String queryKey,String queryValue) throws ServiceException;
	public PageInfo<SysCategory> loadCategoryItemList(int pageSize,int pageNum,String groupCd) throws ServiceException;
	public PageInfo<SysCategory> searchItemList(int pageSize,int pageNum,String queryKey,String queryValue) throws ServiceException;
	public SysCategoryGroup getGroupByCode(String groupCd) throws ServiceException;
	public List<SysCategory> loadAllItems(String groupCd) throws ServiceException;
	public void bulkDelete(Class<SysCategory> clazz, Long[] ids , String groupCd)throws ServiceException;
	public SysCategory loadItem(Class<SysCategory> clazz,Long id) throws ServiceException;
}
