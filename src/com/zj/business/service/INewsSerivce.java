package com.zj.business.service;

import java.util.List;

import com.zj.business.po.News;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.PageInfo;
import com.zj.core.service.ICommonService;

public interface INewsSerivce extends ICommonService {
	public PageInfo<News> loadNewssForPage(int pageSize,int pageNum) throws ServiceException;
	public PageInfo<News> searchList(int pageSize,int pageNum,String queryKey,String queryValue) throws ServiceException;
	public List<News> loadAllNews() throws ServiceException;
	public News getPreNews(long currentNewsId) throws ServiceException;
	public News getNextNews(long currentNewsId) throws ServiceException;
}
