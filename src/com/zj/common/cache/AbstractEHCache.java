package com.zj.common.cache;

import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

import org.apache.log4j.Logger;

public abstract class AbstractEHCache<K, V> {
	private static final Logger log = Logger.getLogger(AbstractEHCache.class);
	
	private Cache cache;
	private final CacheConfiguration cacheConfig;
	
	public AbstractEHCache(String cacheName,int maxElementInHeap){
		this.cacheConfig = new CacheConfiguration(cacheName, maxElementInHeap)
							.memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LRU)
							.overflowToDisk(true)
							.eternal(true)
							.diskPersistent(true)
							.diskExpiryThreadIntervalSeconds(0);
		cacheConfig.setMaxElementsOnDisk(0);
		this.cache = new Cache(cacheConfig);
	}
	
	public AbstractEHCache(CacheConfiguration cacheConfig){
		this.cacheConfig = cacheConfig;
		this.cache = new Cache(cacheConfig);
	}
	
	public String getCacheName(){
		return this.cacheConfig.getName();
	}
	
	public void flush(){
		log.info("Flushing "+this.getCacheName());
		log.debug("Before In Memory Size: "+cache.getMemoryStoreSize());
		log.debug("Before In Disk Size: "+cache.getDiskStoreSize());
		cache.flush();
		log.debug("After In Memory Size: "+cache.getMemoryStoreSize());
		log.debug("After In Disk Size: "+cache.getDiskStoreSize());
	}
	
	public void put(K k,V v){
		Element element = new Element(k,v);
		cache.put(element);
	}
	
	@SuppressWarnings("unchecked")
	public V get(K k){
		Element element = cache.get(k);
		if(element != null){
			return (V) element.getObjectValue();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<K> getAllKeys(){
		return cache.getKeys();
	}
	
	public boolean remove(K k){
		return cache.remove(k);
	}
	
	public boolean containsKey(K k){
		return cache.isKeyInCache(k);
	}
	
	public void removeAll(){
		cache.removeAll();
	}
	
	public Cache getCache(){
		return cache;
	}
}
