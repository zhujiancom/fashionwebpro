package com.zj.common.ztreenode;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractMakeDataStrategy {
	protected static AtomicInteger id;
	
	static{
		id = new AtomicInteger(0);
	}
	
	public AbstractMakeDataStrategy(){
	}
	
	public AbstractMakeDataStrategy(Integer id){
	}
	
	protected Integer generateId(){
		return id.incrementAndGet();
	}
	
	public abstract List<Map<String,Object>> makedata();
}
