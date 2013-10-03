package com.zj.business.service.impl;


import java.util.List;

import org.springframework.stereotype.Component;

import com.zj.bigdefine.GlobalParam;
import com.zj.business.po.Runwayshow;
import com.zj.business.service.IRunwayshowService;
import com.zj.common.annotation.UpdateMode;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.PageInfo;
import com.zj.core.service.impl.CommonServiceImpl;

@Component("runwayshowService")
public class RunwayshowServiceImpl extends CommonServiceImpl implements
		IRunwayshowService {

	@Override
	public PageInfo<Runwayshow> loadRunwayshowsForPage(int pageSize, int pageNum)
			throws ServiceException {
		String hql = "from Runwayshow runwayshow";
		PageInfo<Runwayshow> runwayshow = dao.queryHQLForPage(hql, pageSize,
				pageNum);
		return runwayshow;
	}

	@Override
	public PageInfo<Runwayshow> searchList(int pageSize, int pageNum,
			String queryKey, String queryValue) throws ServiceException {
		if(GlobalParam.ISENABLE.equalsIgnoreCase(queryKey)){
			if(GlobalParam.ENABLE_DESC_EN.equalsIgnoreCase(queryValue) || GlobalParam.ENABLE_DESC_CH.equalsIgnoreCase(queryValue)){
				queryValue = String.valueOf(GlobalParam.ENABLE);
			}
			if(GlobalParam.DISABLE_DESC_EN.equalsIgnoreCase(queryValue) || GlobalParam.DISABLE_DESC_CH.equalsIgnoreCase(queryValue)){
				queryValue = String.valueOf(GlobalParam.DISABLE);
			}
		}
		return dao.queryHQLForPage("from Runwayshow runway where runway."+queryKey+" like '%"+queryValue+"%'",pageSize,pageNum);
	}

	@Override
	public boolean updateRunwayshowAttachInfo(Runwayshow runwayshow,
			boolean isUpdatePoster, boolean isUpdateVideo)
			throws ServiceException {
		UpdateMode mode = UpdateMode.NORMAL;
		if(!isUpdatePoster && !isUpdateVideo){
			mode = UpdateMode.MINI;
		}
//		else if(isUpdatePoster && !isUpdateVideo){
//			//update poster, but not update video info, then need query db runwayshow ,and set the video url in db to new runwayshow
//			Runwayshow dbrunwayshow = dao.get(Runwayshow.class, runwayshow.getRunwayshowid());
//			runwayshow.setRunwayshowUrl(dbrunwayshow.getRunwayshowUrl());
//		}else if(!isUpdatePoster && isUpdateVideo){
//			//update video, but not update poster info, then need query db runwayshow, and set the poster url in db to new runwayshow
//			Runwayshow dbrunwayshow = dao.get(Runwayshow.class, runwayshow.getRunwayshowid());
//			runwayshow.setPoster(dbrunwayshow.getPoster());
//		}
		try{
			merge(runwayshow, runwayshow.getRunwayshowid(), mode);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public List<Runwayshow> getRunwayShowByBrand(long brandId)
			throws ServiceException {
		String hql = "from Runwayshow where brand.brandid="+brandId;
		List<Runwayshow> runwayshows = dao.queryHQL(hql);
		if(runwayshows == null || runwayshows.isEmpty()){
			throw new ServiceException("there are no runwayshows in db! ");
		}
		return runwayshows;
	}


}
