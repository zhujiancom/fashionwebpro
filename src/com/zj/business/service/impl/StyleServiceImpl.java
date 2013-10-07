package com.zj.business.service.impl;


import java.util.List;

import org.springframework.stereotype.Component;

import com.zj.bigdefine.GlobalParam;
import com.zj.business.po.Style;
import com.zj.business.service.IStyleService;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.PageInfo;
import com.zj.core.service.impl.CommonServiceImpl;

@Component("styleService")
public class StyleServiceImpl extends CommonServiceImpl implements
		IStyleService {

	@Override
	public PageInfo<Style> loadStylesForPage(int pageSize, int pageNum)
			throws ServiceException {
		String hql = "from Style style";
		PageInfo<Style> styles = dao.queryHQLForPage(hql, pageSize, pageNum);
		return styles;
	}

	@Override
	public PageInfo<Style> searchList(int pageSize, int pageNum,
			String queryKey, String queryValue) throws ServiceException {
		if(GlobalParam.ISENABLE.equalsIgnoreCase(queryKey)){
			if(GlobalParam.ENABLE_DESC_EN.equalsIgnoreCase(queryValue) || GlobalParam.ENABLE_DESC_CH.equalsIgnoreCase(queryValue)){
				queryValue = String.valueOf(GlobalParam.ENABLE);
			}
			if(GlobalParam.DISABLE_DESC_EN.equalsIgnoreCase(queryValue) || GlobalParam.DISABLE_DESC_CH.equalsIgnoreCase(queryValue)){
				queryValue = String.valueOf(GlobalParam.DISABLE);
			}
		}
		return dao.queryHQLForPage("from Style style where style."+queryKey+" like '%"+queryValue+"%'",pageSize,pageNum);
	}

	@Override
	public List<Style> fuzzySearchByName(String keywords)
			throws ServiceException {
		List<Style> styles = dao.queryHQL("from Style s where s.styleEname like '%"+keywords+"%'");
		if(styles == null || styles.isEmpty()){
			throw new ServiceException("there are no match style !");
		}
		return styles;
	}


}
