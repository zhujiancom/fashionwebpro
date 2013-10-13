package com.zj.business.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zj.bigdefine.GlobalParam;
import com.zj.business.po.Brand;
import com.zj.business.po.Runwayshow;
import com.zj.business.service.IBrandService;
import com.zj.business.service.IRunwayshowService;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.PageInfo;
import com.zj.core.service.impl.CommonServiceImpl;

@Component("runwayshowService")
public class RunwayshowServiceImpl extends CommonServiceImpl implements
		IRunwayshowService {
	private static final Logger log = Logger.getLogger(EditorialServiceImpl.class);
	
	@Resource
	private IBrandService brandService;
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
	public List<Runwayshow> getRunwayShowByBrand(long brandId)
			throws ServiceException {
		String hql = "from Runwayshow where brand.brandid="+brandId;
		List<Runwayshow> runwayshows = dao.queryHQL(hql);
		if(runwayshows == null || runwayshows.isEmpty()){
			throw new ServiceException("there are no runwayshows in db! ");
		}
		return runwayshows;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void save(Runwayshow runwayshow, Brand brand)
			throws ServiceException {
		if(brand != null && !"".equals(brand.getBrandEname())){
			log.debug("read related brand info in <runwayshow save> transaction");
			brand = brandService.searchByName(brand.getBrandEname());
			if(brand != null){
				runwayshow.setBrand(brand);
				brand.getRunwayshows().add(runwayshow);
			}
			insert(runwayshow);
		}else{
			throw new ServiceException("you didn't select brand, please type a brand!");
		}
		log.debug("out of <runwayshow save> transaction");
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void update(Runwayshow runwayshow, Brand brand)
			throws ServiceException {
		if(brand != null && !"".equals(brand.getBrandEname())){
			log.debug("read related brand info in <runwayshow update> transaction");
			brand = brandService.searchByName(brand.getBrandEname());
			if(brand != null){
				runwayshow.setBrand(brand);
				brand.getRunwayshows().add(runwayshow);
			}
			update(runwayshow);
		}else{
			throw new ServiceException("you didn't select brand, please type a brand!");
		}
		log.debug("out of <runwayshow update> transaction");
	}


}
