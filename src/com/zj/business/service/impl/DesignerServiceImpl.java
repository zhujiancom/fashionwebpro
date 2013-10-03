package com.zj.business.service.impl;



import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zj.bigdefine.GlobalParam;
import com.zj.business.po.Designer;
import com.zj.business.service.IDesignerService;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.PageInfo;
import com.zj.core.service.impl.CommonServiceImpl;

@Component("designerService")
public class DesignerServiceImpl extends CommonServiceImpl implements
		IDesignerService {

	@Override
	public PageInfo<Designer> loadDesignersForPage(int pageSize,
			int pageNum) {
		String hql = "from Designer designer";
		PageInfo<Designer> designers = dao.queryHQLForPage(hql, pageSize, pageNum);
		return designers;
	}

	@Override
	public PageInfo<Designer> searchList(int pageSize, int pageNum,
			String queryKey, String queryValue) throws ServiceException {
		if(GlobalParam.ISENABLE.equalsIgnoreCase(queryKey)){
			if(GlobalParam.ENABLE_DESC_EN.equalsIgnoreCase(queryValue) || GlobalParam.ENABLE_DESC_CH.equalsIgnoreCase(queryValue)){
				queryValue = String.valueOf(GlobalParam.ENABLE);
			}
			if(GlobalParam.DISABLE_DESC_EN.equalsIgnoreCase(queryValue) || GlobalParam.DISABLE_DESC_CH.equalsIgnoreCase(queryValue)){
				queryValue = String.valueOf(GlobalParam.DISABLE);
			}
		}
		return dao.queryHQLForPage("from Designer designer where designer."+queryKey+" like '%"+queryValue+"%'",pageSize,pageNum);
	}

	@Override
	public List<Designer> fuzzySearchByName(String fuzzyName)
			throws ServiceException {
		List<Designer> designers = dao.queryHQL("from Designer designer where designer.ename like '%"+fuzzyName+"%'");
		if(designers == null || designers.isEmpty()){
			throw new ServiceException("there are no match designer!");
		}
		return designers;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Designer searchByName(String ename) throws ServiceException {
		List<Designer> designers = dao.queryHQL("from Designer designer where designer.ename='"+ename+"'");
		if(designers != null && !designers.isEmpty()){
			return designers.get(0);
		}else{
			return null;
		}
	}
}
