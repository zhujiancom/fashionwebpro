package com.zj.core.init;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.zj.bigdefine.SQLBuilder;
import com.zj.business.po.HomePager;
import com.zj.common.exception.InitializeException;
import com.zj.common.utils.CategoryMap;
import com.zj.common.utils.SimpleCategory;
import com.zj.core.dao.ICommonDAO;


public class ApplicationInitialize extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4998650810412616724L;
	private static final Logger log = Logger.getLogger(ApplicationInitialize.class);
	private ICommonDAO dao;
	public static Map<String,CategoryMap> categoryMap = new HashMap<String,CategoryMap>();
	
	public ICommonDAO getDao() {
		return dao;
	}

	public void setDao(ICommonDAO dao) {
		this.dao = dao;
	}

	public void init() throws ServletException{
		log.info(">>>>>>>>>> Initialized Appliation Start >>>>>>>>>>");
//		try {
//			initCategory();
//		} catch (InitializeException e) {
//			e.printStackTrace();
//			Log.debug(clazz, "Init category value error!");
//		}
		
		HomePager homepager = dao.get(HomePager.class, 1L);
		if(homepager == null){
			homepager = new HomePager();
			dao.create(homepager);
		}
		log.info(">>>>>>>>>> Initialized Appliation End >>>>>>>>>>");
	}
	
	public void initCategory() throws InitializeException {
		List<Map<String,Object>> result = dao.querySQL(SQLBuilder.INIT_CATEGORY_MAP);
		for(int i=0;i<result.size();i++){
			Map<String,Object> data = result.get(i);
			Long categoryGroupId = (Long) data.get("CATEGORY_GROUP_ID");
			String categoryGroupKey = (String) data.get("CATEGORY_GROUP_CD");
			String categoryCd = (String) data.get("CATEGORY_CD");
			String categoryEnam = (String) data.get("ENAME");
			String categoryCname = (String) data.get("CNAME");
			SimpleCategory category = null;
			if(categoryMap.get(categoryGroupKey) == null){
				CategoryMap categoryValue = new CategoryMap(categoryGroupId);
				if(categoryCd != null){
					category = new  SimpleCategory(categoryCd, categoryEnam, categoryCname);
					categoryValue.put(category);
				}
				categoryMap.put(categoryGroupKey, categoryValue);
			}else{
				CategoryMap categoryValue = categoryMap.get(categoryGroupKey);
				if(categoryCd != null){
					category = new  SimpleCategory(categoryCd, categoryEnam, categoryCname);
					categoryValue.put(category);
				}
			}
		}
	}
}
