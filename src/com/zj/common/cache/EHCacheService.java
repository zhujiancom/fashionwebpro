package com.zj.common.cache;

import java.util.List;

import javax.annotation.PostConstruct;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.DiskStoreConfiguration;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.zj.business.po.HomePager;
import com.zj.common.ztreenode.ZTreeNode;
import com.zj.core.po.SysCategory;

@Component("ehCacheService")
public class EHCacheService {
	private static final Logger log = Logger.getLogger(EHCacheService.class);
	
	@Value("#{envConfig['cache.diskStore.path']}")
	private String cacheDiskStorePath;
	
	private CacheManager cacheManager;
	private CategoryCache categoryCache;
	private HomePagerCache homepagerCache;
	private ModuleCache moduleCache;
	
	public String getCacheDiskStorePath() {
		return cacheDiskStorePath;
	}

	public EHCacheService(){
	}
	
	@PostConstruct
	public void init(){
		String cacheManagerName = "FashionEHCacheManager";
		Configuration cacheManagerConfig = new Configuration().diskStore(new DiskStoreConfiguration().path(cacheDiskStorePath));
		cacheManagerConfig.setName(cacheManagerName);
		cacheManager = new CacheManager(cacheManagerConfig);
		if(cacheManager.cacheExists("categoryCache")){
			log.info("there have cache on the disk!");
			Cache cache = cacheManager.getCache("categoryCache");
			categoryCache = new CategoryCache(cache);
		}else{
			log.info("there is no cache on the disk!");
			categoryCache = new CategoryCache();
			cacheManager.addCache(categoryCache.getCache());
		}
		homepagerCache = new HomePagerCache();
		cacheManager.addCache(homepagerCache.getCache());
		
		moduleCache = new ModuleCache();
		cacheManager.addCache(moduleCache.getCache());
		
		log.info("Added cache: "+categoryCache.getCacheName()+" to the cache manager --> "+cacheManagerName);
		log.info("Added cache: "+homepagerCache.getCacheName()+" to the cache manager --> "+cacheManagerName);
		log.info("Added cache: "+moduleCache.getCacheName()+" to the cache manager --> "+cacheManagerName);
	}
	
	
	public void addOrUpdateCategoryCache(String groupCd, List<SysCategory> items){
		categoryCache.put(groupCd, items);
	}
	
	public void addOrUpdateHomepagerCache(Long homeId,HomePager home){
		homepagerCache.put(homeId, home);
	}
	
	public void addOrUpdateModuleCache(String userIdParentId,List<ZTreeNode> modulelist){
		moduleCache.put(userIdParentId, modulelist);
	}

	public CategoryCache getCategoryCache() {
		return categoryCache;
	}

	public HomePagerCache getHomepagerCache() {
		return homepagerCache;
	}
	
	public ModuleCache getModuleCache(){
		return moduleCache;
	}
}
