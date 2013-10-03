package com.zj.business.service;

import java.util.List;

import com.zj.business.po.Brand;
import com.zj.business.po.Lookbook;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.PageInfo;
import com.zj.core.service.ICommonService;

public interface ILookbookService extends ICommonService {
	public PageInfo<Lookbook> loadLookbooksForPage(int pageSize,int pageNum) throws ServiceException;
	public PageInfo<Lookbook> searchList(int pageSize,int pageNum,String queryKey,String queryValue) throws ServiceException;
	public List<Lookbook> getLookbookByBrand(long brandId) throws ServiceException;
	public void save(Lookbook lookbook, Brand brand) throws ServiceException;
	public void update(Lookbook lookbook, Brand brand) throws ServiceException;
}
