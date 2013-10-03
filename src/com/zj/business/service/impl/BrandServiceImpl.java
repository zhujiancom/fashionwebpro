package com.zj.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zj.bigdefine.GlobalParam;
import com.zj.business.po.Brand;
import com.zj.business.po.Designer;
import com.zj.business.service.IBrandService;
import com.zj.business.service.IDesignerService;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.PageInfo;
import com.zj.core.service.impl.CommonServiceImpl;

@Component("brandService")
public class BrandServiceImpl extends CommonServiceImpl implements
		IBrandService {
	private static final Logger log = Logger.getLogger(BrandServiceImpl.class);

	@Resource
	private IDesignerService designerService;
	
	@Override
	public PageInfo<Brand> loadBrandsForPage(int pageSize, int pageNum)
			throws ServiceException {
		String hql = "from Brand brand";
		PageInfo<Brand> brands = dao.queryHQLForPage(hql, pageSize, pageNum);
		return brands;
	}

	@Override
	public PageInfo<Brand> searchList(int pageSize, int pageNum,
			String queryKey, String queryValue) throws ServiceException {
		if(GlobalParam.ISENABLE.equalsIgnoreCase(queryKey)){
			if(GlobalParam.ENABLE_DESC_EN.equalsIgnoreCase(queryValue) || GlobalParam.ENABLE_DESC_CH.equalsIgnoreCase(queryValue)){
				queryValue = String.valueOf(GlobalParam.ENABLE);
			}
			if(GlobalParam.DISABLE_DESC_EN.equalsIgnoreCase(queryValue) || GlobalParam.DISABLE_DESC_CH.equalsIgnoreCase(queryValue)){
				queryValue = String.valueOf(GlobalParam.DISABLE);
			}
		}
		return dao.queryHQLForPage("from Brand brand where brand."+queryKey+" like '%"+queryValue+"%'",pageSize,pageNum);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Brand searchByName(String ename) throws ServiceException {
		List<Brand> brands = dao.queryHQL("from Brand brand where brand.brandEname='"+ename+"'");
		if(brands != null && !brands.isEmpty()){
			return brands.get(0);
		}else{
			return null;
		}
	}

	@Override
	public List<Brand> fuzzySearchByName(String fuzzyName)
			throws ServiceException {
		List<Brand> brands = dao.queryHQL("from Brand brand where brand.brandEname like '%"+fuzzyName+"%'");
		if(brands == null || brands.isEmpty()){
			throw new ServiceException("there are no match brand!");
		}
		return brands;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void save(Brand brand, Designer designer) throws ServiceException {
		if(designer != null && !"".equals(designer.getEname())){
			log.debug("read related designer info in <brand save> transaction");
			designer = designerService.searchByName(designer.getEname());
			if(designer != null){
				brand.setDesigner(designer);
				designer.getBrands().add(brand);
			}
		}
		insert(brand);
		log.debug("out of <brand save> transaction");
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void update(Brand brand, Designer designer) throws ServiceException {
		if (designer != null && !"".equals(designer.getEname())) {
			log.debug("read related designer info in <brand update> transaction");
			designer = designerService.searchByName(designer.getEname());
			if (designer != null) {
				brand.setDesigner(designer);
				designer.getBrands().add(brand);
			}
		}
		update(brand);
		log.debug("out of <brand update> transaction");
	}


}
