package com.zj.business.service.impl;


import java.util.List;

import org.springframework.stereotype.Component;

import com.zj.bigdefine.GlobalParam;
import com.zj.business.po.News;
import com.zj.business.service.INewsSerivce;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.PageInfo;
import com.zj.core.service.impl.CommonServiceImpl;

@Component("newsService")
public class NewsServiceImpl extends CommonServiceImpl implements INewsSerivce {

	@Override
	public PageInfo<News> loadNewssForPage(int pageSize, int pageNum)
			throws ServiceException {
		String hql = "from News news";
		PageInfo<News> news = dao.queryHQLForPage(hql, pageSize,
				pageNum);
		return news;
	}

	@Override
	public PageInfo<News> searchList(int pageSize, int pageNum,
			String queryKey, String queryValue) throws ServiceException {
		if(GlobalParam.ISENABLE.equalsIgnoreCase(queryKey)){
			if(GlobalParam.ENABLE_DESC_EN.equalsIgnoreCase(queryValue) || GlobalParam.ENABLE_DESC_CH.equalsIgnoreCase(queryValue)){
				queryValue = String.valueOf(GlobalParam.ENABLE);
			}
			if(GlobalParam.DISABLE_DESC_EN.equalsIgnoreCase(queryValue) || GlobalParam.DISABLE_DESC_CH.equalsIgnoreCase(queryValue)){
				queryValue = String.valueOf(GlobalParam.DISABLE);
			}
		}
		return dao.queryHQLForPage("from News news where news."+queryKey+" like '%"+queryValue+"%'",pageSize,pageNum);
	}

	@Override
	public List<News> loadAllNews() throws ServiceException {
		String hql = "from News order by newsId desc";
		List<News> newslist = dao.queryHQL(hql);
		if(newslist == null || newslist.isEmpty()){
			throw new ServiceException("there is no data in db");
		}
		return newslist;
	}

	@Override
	public News getPreNews(long currentNewsId) throws ServiceException {
		String hql = "from News n where n.newsId>"+currentNewsId+" order by n.newsId asc limit 1";
		List<News> newslist = dao.queryHQL(hql);
		if(newslist == null || newslist.isEmpty()){
			throw new ServiceException("there is no data in db");
		}
		return newslist.get(0);
	}

	@Override
	public News getNextNews(long currentNewsId) throws ServiceException {
		String hql = "from News n where n.newsId<"+currentNewsId+" order by n.newsId desc limit 1";
		List<News> newslist = dao.queryHQL(hql);
		if(newslist == null || newslist.isEmpty()){
			throw new ServiceException("there is no data in db");
		}
		return newslist.get(0);
	}


}
