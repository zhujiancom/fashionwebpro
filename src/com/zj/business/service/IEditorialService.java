package com.zj.business.service;

import java.util.List;

import com.zj.business.po.Brand;
import com.zj.business.po.Editorial;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.PageInfo;
import com.zj.core.service.ICommonService;

public interface IEditorialService extends ICommonService {
	public PageInfo<Editorial> loadEditorialsForPage(int pageSize,int pageNum) throws ServiceException;
	public PageInfo<Editorial> searchList(int pageSize,int pageNum,String queryKey,String queryValue) throws ServiceException;
	public List<Editorial> getEditorialByBrand(long brandId) throws ServiceException;
	public void save(Editorial editorial,Brand brand) throws ServiceException;
	public void update(Editorial editorial,Brand brand) throws ServiceException;
}
