package com.zj.business.service;

import java.util.List;

import com.zj.business.po.Style;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.PageInfo;
import com.zj.core.service.ICommonService;

public interface IStyleService extends ICommonService {

	public PageInfo<Style> loadStylesForPage(int pageSize,int pageNum) throws ServiceException;
	public PageInfo<Style> searchList(int pageSize,int pageNum,String queryKey,String queryValue) throws ServiceException;
	public List<Style> fuzzySearchByName(String keywords) throws ServiceException;
}
