package com.zj.business.service;

import java.util.List;

import com.zj.business.po.Brand;
import com.zj.business.po.Designer;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.PageInfo;
import com.zj.core.service.ICommonService;

public interface IBrandService extends ICommonService {
	
	public PageInfo<Brand> loadBrandsForPage(int pageSize,int pageNum) throws ServiceException;
	public PageInfo<Brand> searchList(int pageSize,int pageNum,String queryKey,String queryValue) throws ServiceException;
	public Brand searchByName(String ename) throws ServiceException;
	public List<Brand> fuzzySearchByName(String fuzzyName) throws ServiceException;
	public void save(Brand brand,Designer designer) throws ServiceException;
	public void update(Brand brand,Designer designer) throws ServiceException;
}
