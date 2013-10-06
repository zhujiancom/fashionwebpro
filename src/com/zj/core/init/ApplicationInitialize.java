package com.zj.core.init;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.zj.business.po.HomePager;
import com.zj.common.cache.EHCacheService;
import com.zj.core.dao.ICommonDAO;


public class ApplicationInitialize extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4998650810412616724L;
	private static final Logger log = Logger.getLogger(ApplicationInitialize.class);
	private ICommonDAO dao;
	private EHCacheService ehCacheService;
	
	public void setEhCacheService(EHCacheService ehCacheService) {
		this.ehCacheService = ehCacheService;
	}

	public ICommonDAO getDao() {
		return dao;
	}

	public void setDao(ICommonDAO dao) {
		this.dao = dao;
	}

	public void init() throws ServletException{
		log.info(">>>>>>>>>> Initialized Appliation Start >>>>>>>>>>");
		Properties properties = System.getProperties();
		try {
			properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.setProperties(properties);
		HomePager homepager = dao.get(HomePager.class, 1L);
		if(homepager == null){
			homepager = new HomePager();
			dao.create(homepager);
			homepager = dao.get(HomePager.class, 1L);
		}
		ehCacheService.addOrUpdateHomepagerCache(homepager.getHomeId(), homepager);
		log.info(">>>>>>>>>> Initialized Appliation End >>>>>>>>>>");
	}
}
