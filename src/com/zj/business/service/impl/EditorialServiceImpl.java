package com.zj.business.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zj.bigdefine.GlobalParam;
import com.zj.business.po.Brand;
import com.zj.business.po.Editorial;
import com.zj.business.service.IBrandService;
import com.zj.business.service.IEditorialService;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.PageInfo;
import com.zj.core.service.impl.CommonServiceImpl;

@Component("editorialService")
public class EditorialServiceImpl extends CommonServiceImpl implements
		IEditorialService {
	private static final Logger log = Logger.getLogger(EditorialServiceImpl.class);
	
	@Resource
	private IBrandService brandService;
	
	
	@Override
	public PageInfo<Editorial> loadEditorialsForPage(int pageSize, int pageNum)
			throws ServiceException {
		String hql = "from Editorial editorial";
		PageInfo<Editorial> editorial = dao.queryHQLForPage(hql, pageSize,
				pageNum);
		return editorial;
	}

	@Override
	public PageInfo<Editorial> searchList(int pageSize, int pageNum,
			String queryKey, String queryValue) throws ServiceException {
		if(GlobalParam.ISENABLE.equalsIgnoreCase(queryKey)){
			if(GlobalParam.ENABLE_DESC_EN.equalsIgnoreCase(queryValue) || GlobalParam.ENABLE_DESC_CH.equalsIgnoreCase(queryValue)){
				queryValue = String.valueOf(GlobalParam.ENABLE);
			}
			if(GlobalParam.DISABLE_DESC_EN.equalsIgnoreCase(queryValue) || GlobalParam.DISABLE_DESC_CH.equalsIgnoreCase(queryValue)){
				queryValue = String.valueOf(GlobalParam.DISABLE);
			}
		}
		return dao.queryHQLForPage("from Editorial editorial where editorial."+queryKey+" like '%"+queryValue+"%'",pageSize,pageNum);
	}

	@Override
	public List<Editorial> getEditorialByBrand(long brandId)
			throws ServiceException {
		String hql = "from Editorial e where e.brand.brandid="+brandId;
		List<Editorial> editorials = dao.queryHQL(hql);
		if(editorials == null || editorials.isEmpty()){
			throw new ServiceException("there is no match editorial! ");
		}
		return editorials;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void save(Editorial editorial, Brand brand) throws ServiceException {
		if(brand != null && !"".equals(brand.getBrandEname())){
			log.debug("read related brand info in <editorial save> transaction");
			brand = brandService.searchByName(brand.getBrandEname());
			if(brand != null){
				editorial.setBrand(brand);
				brand.getEditorials().add(editorial);
			}
		}
		insert(editorial);
		log.debug("out of <editorial save> transaction");
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void update(Editorial editorial, Brand brand)
			throws ServiceException {
		if(brand != null && !"".equals(brand.getBrandEname())){
			log.debug("read related brand info in <editorial update> transaction");
			brand = brandService.searchByName(brand.getBrandEname());
			if(brand != null){
				editorial.setBrand(brand);
				brand.getEditorials().add(editorial);
			}
		}
		update(editorial);
		log.debug("out of <editorial update> transaction");
	}

}
