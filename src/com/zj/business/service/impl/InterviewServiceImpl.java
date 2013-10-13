package com.zj.business.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zj.bigdefine.GlobalParam;
import com.zj.business.po.Designer;
import com.zj.business.po.Interview;
import com.zj.business.service.IDesignerService;
import com.zj.business.service.IInterviewService;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.PageInfo;
import com.zj.core.service.impl.CommonServiceImpl;

@Component("interviewService")
public class InterviewServiceImpl extends CommonServiceImpl implements
		IInterviewService {
	private static final Logger log = Logger.getLogger(EditorialServiceImpl.class);
	
	@Resource
	private IDesignerService designerService;
	@Override
	public PageInfo<Interview> loadInterviewsForPage(int pageSize, int pageNum)
			throws ServiceException {
		String hql = "from Interview interview";
		PageInfo<Interview> interviews = dao.queryHQLForPage(hql, pageSize,
				pageNum);
		return interviews;
	}

	@Override
	public PageInfo<Interview> searchList(int pageSize, int pageNum,
			String queryKey, String queryValue) throws ServiceException {
		if(GlobalParam.ISENABLE.equalsIgnoreCase(queryKey)){
			if(GlobalParam.ENABLE_DESC_EN.equalsIgnoreCase(queryValue) || GlobalParam.ENABLE_DESC_CH.equalsIgnoreCase(queryValue)){
				queryValue = String.valueOf(GlobalParam.ENABLE);
			}
			if(GlobalParam.DISABLE_DESC_EN.equalsIgnoreCase(queryValue) || GlobalParam.DISABLE_DESC_CH.equalsIgnoreCase(queryValue)){
				queryValue = String.valueOf(GlobalParam.DISABLE);
			}
		}
		return dao.queryHQLForPage("from Interview interview where interview."+queryKey+" like '%"+queryValue+"%'",pageSize,pageNum);
	}

	@Override
	public List<Interview> getInterviewsByDesingerAndType(long designerId,
			String type) throws ServiceException {
		String hql = "from Interview interview where interview.interviewtype='"+type+"' and interview.designer.designerId="+designerId;
		List<Interview> interviews = dao.queryHQL(hql);
		if(interviews == null || interviews.isEmpty()){
			throw new ServiceException("there are no interviews in db! ");
		}
		return interviews;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void save(Interview interview, Designer designer) throws ServiceException {
		if(designer != null && !"".equals(designer.getEname())){
			log.debug("read related designer info in <interview save> transaction");
			designer = designerService.searchByName(designer.getEname());
			if(designer != null){
				interview.setDesigner(designer);
				designer.getInterviews().add(interview);
			}
			insert(interview);
		}else{
			throw new ServiceException("you didn't select designer, please type a designer!");
		}
		log.debug("out of <interview save> transaction");
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void update(Interview interview, Designer designer)
			throws ServiceException {
		if(designer != null && !"".equals(designer.getEname())){
			log.debug("read related designer info in <interview update> transaction");
			designer = designerService.searchByName(designer.getEname());
			if(designer != null){
				interview.setDesigner(designer);
				designer.getInterviews().add(interview);
			}
			update(interview);
		}else{
			throw new ServiceException("you didn't select designer, please type a designer!");
		}
		log.debug("out of <interview update> transaction");
		
	}
}
