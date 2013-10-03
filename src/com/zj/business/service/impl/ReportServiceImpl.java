package com.zj.business.service.impl;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zj.bigdefine.GlobalParam;
import com.zj.business.po.Designer;
import com.zj.business.po.Report;
import com.zj.business.service.IDesignerService;
import com.zj.business.service.IReportService;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.PageInfo;
import com.zj.core.service.impl.CommonServiceImpl;

@Component("reportService")
public class ReportServiceImpl extends CommonServiceImpl implements
		IReportService {
	private static final Logger log = Logger.getLogger(ReportServiceImpl.class);
	@Resource
	private IDesignerService designerService;
	
	@Override
	public PageInfo<Report> loadreportsForPage(int pageSize, int pageNum)
			throws ServiceException {
		String hql = "from Report report";
		PageInfo<Report> report = dao.queryHQLForPage(hql, pageSize,
				pageNum);
		return report;
	}

	@Override
	public PageInfo<Report> searchList(int pageSize, int pageNum,
			String queryKey, String queryValue) throws ServiceException {
		if(GlobalParam.ISENABLE.equalsIgnoreCase(queryKey)){
			if(GlobalParam.ENABLE_DESC_EN.equalsIgnoreCase(queryValue) || GlobalParam.ENABLE_DESC_CH.equalsIgnoreCase(queryValue)){
				queryValue = String.valueOf(GlobalParam.ENABLE);
			}
			if(GlobalParam.DISABLE_DESC_EN.equalsIgnoreCase(queryValue) || GlobalParam.DISABLE_DESC_CH.equalsIgnoreCase(queryValue)){
				queryValue = String.valueOf(GlobalParam.DISABLE);
			}
		}
		return dao.queryHQLForPage("from Report report where report."+queryKey+" like '%"+queryValue+"%'",pageSize,pageNum);
	}

	@Override
	public List<Report> getReportsByDesinger(long designerId)
			throws ServiceException {
		List<Report> reports = new ArrayList<Report>();
		Designer designer = dao.get(Designer.class, designerId);
		if(designer == null){
			throw new ServiceException("there is no designer in db");
		}
		Set<Report> reportsSet = designer.getReports();
		Iterator<Report> it = reportsSet.iterator();
		while(it.hasNext()){
			Report report = it.next();
			reports.add(report);
		}
		return reports;
	}

	@Override
	public Report getPreReport(long currentReportId) throws ServiceException {
		String hql = "from Report n where n.reportid>"+currentReportId+" order by n.reportid asc limit 1";
		List<Report> reportlist = dao.queryHQL(hql);
		if(reportlist == null || reportlist.isEmpty()){
			throw new ServiceException("there is no data in db");
		}
		return reportlist.get(0);
	}

	@Override
	public Report getNextReport(long currentReportId) throws ServiceException {
		String hql = "from Report n where n.reportid<"+currentReportId+" order by n.reportid desc limit 1";
		List<Report> reportlist = dao.queryHQL(hql);
		if(reportlist == null || reportlist.isEmpty()){
			throw new ServiceException("there is no data in db");
		}
		return reportlist.get(0);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void save(Report report, Designer designer) throws ServiceException {
		if(designer != null && !"".equals(designer.getEname())){
			log.debug("read related brand info in <pressreport save> transaction");
			designer = designerService.searchByName(designer.getEname());
			if(designer != null){
				report.setDesigner(designer);
				designer.getReports().add(report);
			}
		}
		insert(report);
		log.debug("out of <pressreport save> transaction");
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void update(Report report, Designer designer)
			throws ServiceException {
		if(designer != null && !"".equals(designer.getEname())){
			log.debug("read related brand info in <pressreport update> transaction");
			designer = designerService.searchByName(designer.getEname());
			if(designer != null){
				report.setDesigner(designer);
				designer.getReports().add(report);
			}
		}
		update(report);
		log.debug("out of <pressreport update> transaction");
	}


}
