package com.zj.business.service;

import java.util.List;

import com.zj.business.po.Designer;
import com.zj.business.po.Report;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.PageInfo;
import com.zj.core.service.ICommonService;

public interface IReportService extends ICommonService {
	public PageInfo<Report> loadreportsForPage(int pageSize,int pageNum) throws ServiceException;
	public PageInfo<Report> searchList(int pageSize,int pageNum,String queryKey,String queryValue) throws ServiceException;
	public List<Report> getReportsByDesinger(long designerId) throws ServiceException;
	public Report getPreReport(long currentReportId) throws ServiceException;
	public Report getNextReport(long currentReportId) throws ServiceException;
	public void save(Report report,Designer designer) throws ServiceException;
	public void update(Report report,Designer designer) throws ServiceException;
}
