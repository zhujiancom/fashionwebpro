package com.zj.common.cache;

import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.config.CacheConfiguration;

import com.zj.core.po.SysCategory;

public class CategoryCache extends AbstractEHCache<String,List<SysCategory>> {
	public CategoryCache(){
		super("categoryCache",1);
	}
	
	public CategoryCache(CacheConfiguration cacheConfig){
		super(cacheConfig);
	}
	
	public CategoryCache(Cache cache){
		super(cache);
	}
}
