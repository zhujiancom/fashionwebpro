package com.zj.business.service;

import java.util.List;

import com.zj.business.po.Designer;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.PageInfo;
import com.zj.core.service.ICommonService;

public interface IDesignerService extends ICommonService {
	
	public PageInfo<Designer> loadDesignersForPage(int pageSize,int pageNum) throws ServiceException;
	public PageInfo<Designer> searchList(int pageSize,int pageNum,String queryKey,String queryValue) throws ServiceException;
	public List<Designer> fuzzySearchByName(String fuzzyName) throws ServiceException;
	public Designer searchByName(String ename) throws ServiceException;
}
