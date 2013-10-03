package com.zj.business.service;

import java.util.List;

import com.zj.business.po.Runwayshow;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.PageInfo;
import com.zj.core.service.ICommonService;

public interface IRunwayshowService extends ICommonService {
	public PageInfo<Runwayshow> loadRunwayshowsForPage(int pageSize,int pageNum) throws ServiceException;
	public PageInfo<Runwayshow> searchList(int pageSize,int pageNum,String queryKey,String queryValue) throws ServiceException;
	public boolean updateRunwayshowAttachInfo(Runwayshow runwayshow,boolean isUpdatePoster,boolean isUpdateVideo) throws ServiceException;
	public List<Runwayshow> getRunwayShowByBrand(long brandId) throws ServiceException;
}
