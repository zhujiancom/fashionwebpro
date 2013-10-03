package com.zj.business.service.impl;


import java.util.List;

import org.springframework.stereotype.Component;

import com.zj.bigdefine.GlobalParam;
import com.zj.business.po.Lookbook;
import com.zj.business.service.ILookbookService;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.PageInfo;
import com.zj.core.service.impl.CommonServiceImpl;

@Component("lookbookService")
public class LookbookServiceImpl extends CommonServiceImpl implements
		ILookbookService {

	@Override
	public PageInfo<Lookbook> loadLookbooksForPage(int pageSize, int pageNum)
			throws ServiceException {
		String hql = "from Lookbook lookbook";
		PageInfo<Lookbook> lookbook = dao.queryHQLForPage(hql, pageSize,
				pageNum);
		return lookbook;
	}

	@Override
	public PageInfo<Lookbook> searchList(int pageSize, int pageNum,
			String queryKey, String queryValue) throws ServiceException {
		if(GlobalParam.ISENABLE.equalsIgnoreCase(queryKey)){
			if(GlobalParam.ENABLE_DESC_EN.equalsIgnoreCase(queryValue) || GlobalParam.ENABLE_DESC_CH.equalsIgnoreCase(queryValue)){
				queryValue = String.valueOf(GlobalParam.ENABLE);
			}
			if(GlobalParam.DISABLE_DESC_EN.equalsIgnoreCase(queryValue) || GlobalParam.DISABLE_DESC_CH.equalsIgnoreCase(queryValue)){
				queryValue = String.valueOf(GlobalParam.DISABLE);
			}
		}
		return dao.queryHQLForPage("from Lookbook lookbook where lookbook."+queryKey+" like '%"+queryValue+"%'",pageSize,pageNum);
	}

	@Override
	public List<Lookbook> getLookbookByBrand(long brandId)
			throws ServiceException {
		String hql = "from Lookbook where brand.brandid="+brandId;
		List<Lookbook> lookbooks = dao.queryHQL(hql);
		if(lookbooks == null || lookbooks.isEmpty()){
			throw new ServiceException("there are no match data !");
		}
		return lookbooks;
	}

}
