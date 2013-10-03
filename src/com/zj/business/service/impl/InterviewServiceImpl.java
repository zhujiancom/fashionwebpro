package com.zj.business.service.impl;


import java.util.List;

import org.springframework.stereotype.Component;

import com.zj.bigdefine.GlobalParam;
import com.zj.business.po.Interview;
import com.zj.business.service.IInterviewService;
import com.zj.common.annotation.UpdateMode;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.PageInfo;
import com.zj.core.service.impl.CommonServiceImpl;

@Component("interviewService")
public class InterviewServiceImpl extends CommonServiceImpl implements
		IInterviewService {

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

	public boolean updateInterviewAttachInfo(Interview interview, boolean isUpdatePoster,boolean isUpdateVideo) throws ServiceException{
		UpdateMode mode = UpdateMode.NORMAL;
		if(!isUpdatePoster && !isUpdateVideo){
			mode = UpdateMode.MINI;
		}
		try{
			merge(interview, interview.getInterviewid(), mode);
			return true;
		}catch(Exception e){
			return false;
		}
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
}
