package com.zj.common.cache;

import java.util.List;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.DiskStoreConfiguration;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.zj.core.po.SysCategory;

@Component("ehCacheService")
public class EHCacheService {
	private static final Logger log = Logger.getLogger(EHCacheService.class);
	
	private final String cacheDiskStorePath;
	private CacheManager cacheManager;
	private CategoryCache categoryCache;
	
	public String getCacheDiskStorePath() {
		return cacheDiskStorePath;
	}

	@Autowired(required=true)
	public EHCacheService(@Qualifier("cacheDiskStorePath") String cacheDiskStorePath){
		this.cacheDiskStorePath = cacheDiskStorePath;
		String cacheManagerName = "FashionEHCacheManager";
		Configuration cacheManagerConfig = new Configuration().diskStore(new DiskStoreConfiguration().path(cacheDiskStorePath));
		cacheManagerConfig.setName(cacheManagerName);
		cacheManager = new CacheManager(cacheManagerConfig);
		categoryCache = new CategoryCache();
		cacheManager.addCache(categoryCache.getCache());
		log.info("Added cache: "+categoryCache.getCacheName()+" to the cache manager --> "+cacheManagerName);
	}
	
	public void addOrUpdateCategoryCache(String groupCd, List<SysCategory> items){
		categoryCache.put(groupCd, items);
	}

	public CategoryCache getCategoryCache() {
		return categoryCache;
	}
}
