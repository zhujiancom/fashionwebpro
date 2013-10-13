package com.zj.business.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zj.bigdefine.GlobalParam;
import com.zj.business.po.Brand;
import com.zj.business.po.Lookbook;
import com.zj.business.service.IBrandService;
import com.zj.business.service.ILookbookService;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.PageInfo;
import com.zj.core.service.impl.CommonServiceImpl;

@Component("lookbookService")
public class LookbookServiceImpl extends CommonServiceImpl implements
		ILookbookService {
	private static final Logger log = Logger.getLogger(EditorialServiceImpl.class);
	
	@Resource
	private IBrandService brandService;
	
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
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void save(Lookbook lookbook, Brand brand) throws ServiceException {
		if(brand != null && !"".equals(brand.getBrandEname())){
			log.debug("read related brand info in <lookbook save> transaction");
			brand = brandService.searchByName(brand.getBrandEname());
			if(brand != null){
				lookbook.setBrand(brand);
				brand.getLookbooks().add(lookbook);
			}
			insert(lookbook);
		}else{
			throw new ServiceException("you didn't select brand, please type a brand!");
		}
		log.debug("out of <lookbook save> transaction");
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void update(Lookbook lookbook, Brand brand) throws ServiceException{
		if(brand != null && !"".equals(brand.getBrandEname())){
			log.debug("read related brand info in <lookbook update> transaction");
			brand = brandService.searchByName(brand.getBrandEname());
			if(brand != null){
				lookbook.setBrand(brand);
				brand.getLookbooks().add(lookbook);
			}
			update(lookbook);
		}else{
			throw new ServiceException("you didn't select brand, please type a brand!");
		}
		log.debug("out of <lookbook update> transaction");
	}

}
