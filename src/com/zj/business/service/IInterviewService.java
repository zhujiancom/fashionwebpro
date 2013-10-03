package com.zj.business.service;

import java.util.List;

import com.zj.business.po.Interview;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.PageInfo;
import com.zj.core.service.ICommonService;

public interface IInterviewService extends ICommonService {
	public PageInfo<Interview> loadInterviewsForPage(int pageSize,int pageNum) throws ServiceException;
	public PageInfo<Interview> searchList(int pageSize,int pageNum,String queryKey,String queryValue) throws ServiceException;
	public boolean updateInterviewAttachInfo(Interview interview, boolean isUpdatePoster,boolean isUpdateVideo) throws ServiceException;
	public List<Interview> getInterviewsByDesingerAndType(long designerId , String type) throws ServiceException;
}
