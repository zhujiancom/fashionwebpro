package com.zj.core.service.impl;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.zj.bigdefine.GlobalParam;
import com.zj.bigdefine.SQLBuilder;
import com.zj.common.cache.EHCacheService;
import com.zj.common.exception.DAOException;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.PageInfo;
import com.zj.common.ztreenode.ZTreeNode;
import com.zj.core.po.SysCategory;
import com.zj.core.po.SysCategoryGroup;
import com.zj.core.service.ISysCategoryService;

@Component("categoryService")
public class SysCategoryServiceImpl extends CommonServiceImpl implements ISysCategoryService {
	private static final Logger log = Logger.getLogger(SysCategoryServiceImpl.class);
	@Resource
	private EHCacheService cacheService;
	
	@Override
	public List<ZTreeNode> getCategoryTreeList(long parentId)
			throws ServiceException {
		List<ZTreeNode> treelist = null;
		if(parentId == 0){
			List<Map<String,Object>> list = dao.querySQL(SQLBuilder.CATEGORY_FIRST_LEVEL_TREE);
			for(int i=0;i<list.size();i++){
				Map<String,Object> record = list.get(i);
				String groupCd = (String) record.get("CODE");
				record.put("url", "backend/modules/CategoryAdmin/category_sub_main.jsp?groupCd="+groupCd+"");
			}
			String url = "backend/modules/CategoryAdmin/category_main.jsp";
			String target = "category_main";
			ZTreeNode rootnode = new ZTreeNode(0,null,"字典根目录",true,true,url,target);
			treelist = getZTreeNode(list,null,rootnode);
			return treelist;
		}else{
			Object[] arguments = new Object[]{parentId};
			String sql = MessageFormat.format(SQLBuilder.CATEGORY_SECONDARY_TREE, arguments);
			List<Map<String,Object>> list = dao.querySQL(sql);
			treelist = getZTreeNode(list, null, null);
			return treelist;
		}
	}

	@Override
	public PageInfo<SysCategoryGroup> loadCategoryGroupList(int pageSize,
			int pageNum) throws ServiceException {
		return dao.queryHQLForPage("from SysCategoryGroup group ", pageSize, pageNum);
	}

	@Override
	public PageInfo<SysCategoryGroup> searchGroupList(int pageSize,
			int pageNum, String queryKey, String queryValue)
			throws ServiceException {
		if(GlobalParam.ISENABLE.equalsIgnoreCase(queryKey)){
			if(GlobalParam.ENABLE_DESC_EN.equalsIgnoreCase(queryValue) || GlobalParam.ENABLE_DESC_CH.equalsIgnoreCase(queryValue)){
				queryValue = String.valueOf(GlobalParam.ENABLE);
			}
			if(GlobalParam.DISABLE_DESC_EN.equalsIgnoreCase(queryValue) || GlobalParam.DISABLE_DESC_CH.equalsIgnoreCase(queryValue)){
				queryValue = String.valueOf(GlobalParam.DISABLE);
			}
		}
		return dao.queryHQLForPage("from SysCategoryGroup group where group."+queryKey+" like '%"+queryValue+"%'", pageSize, pageNum);
	}

	@Override
	public PageInfo<SysCategory> loadCategoryItemList(int pageSize, int pageNum,String groupCd) throws ServiceException {
		PageInfo<SysCategory> itemList;
		try{
			itemList = dao.queryHQLForPage("from SysCategory category where category.parent.categoryGroupCd='"+groupCd+"'", pageSize, pageNum);
			if(itemList == null){
				itemList = new PageInfo<SysCategory>();
			}
		}catch(Exception e){
			throw new ServiceException(e);
		}
		return itemList;
	}
	
	@Override
	public List<SysCategory> loadAllItems(String groupCd) throws ServiceException{
		List<SysCategory> items = cacheService.getCategoryCache().get(groupCd);
		if(items == null || items.isEmpty()){
			items = dao.queryHQL("from SysCategory category where category.parent.categoryGroupCd='"+groupCd+"'");
			if(items == null || items.isEmpty()){
				throw new ServiceException("CategoryItem Data not found !");
			}else{
				cacheService.getCategoryCache().put(groupCd, items);
			}
		}
		return items;
	}
	
	@Override
	public PageInfo<SysCategory> searchItemList(int pageSize, int pageNum,
			String queryKey, String queryValue) throws ServiceException {
		if(GlobalParam.ISENABLE.equalsIgnoreCase(queryKey)){
			if(GlobalParam.ENABLE_DESC_EN.equalsIgnoreCase(queryValue) || GlobalParam.ENABLE_DESC_CH.equalsIgnoreCase(queryValue)){
				queryValue = String.valueOf(GlobalParam.ENABLE);
			}
			if(GlobalParam.DISABLE_DESC_EN.equalsIgnoreCase(queryValue) || GlobalParam.DISABLE_DESC_CH.equalsIgnoreCase(queryValue)){
				queryValue = String.valueOf(GlobalParam.DISABLE);
			}
		}
		return dao.queryHQLForPage("from SysCategory category where category."+queryKey+" like '%"+queryValue+"%'", pageSize, pageNum);
	}

	@Override
	public SysCategoryGroup getGroupByCode(String groupCd)
			throws ServiceException {
		return (SysCategoryGroup) dao.queryHQL("from SysCategoryGroup group where group.categoryGroupCd='"+groupCd+"'").get(0);
	}

	public void bulkDelete(Class<SysCategory> clazz, Long[] ids , String groupCd)throws ServiceException {
		super.bulkDelete(clazz, ids);
		cacheService.getCategoryCache().remove(groupCd);
	}

	@Override
	public SysCategory loadItem(Class<SysCategory> clazz, Long id) throws ServiceException{
		SysCategory item = null;
		try{
			item = dao.load(clazz, id);
			if(item == null){
				throw new DAOException("No data found!");
			}
		}catch(DAOException daoEx){
			log.info("load category item error", daoEx);
			throw new ServiceException(daoEx);
		}
		return item;
	}
}
