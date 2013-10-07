package com.zj.common.utils;

import java.util.HashSet;
import java.util.Set;

public class CategoryMap {
	public Set<SimpleCategory> simpleCategories ;
	private Long groupId;
	
	{
		simpleCategories = new HashSet<SimpleCategory>();
		SimpleCategory firstSelect = new SimpleCategory("default", "---Plseas Select---","---请选择---");
		simpleCategories.add(firstSelect);
	}
	
	public CategoryMap() {
		super();
	}

	public CategoryMap(Long groupId) {
		super();
		this.groupId = groupId;
	}

	public void put(SimpleCategory sc){
		simpleCategories.add(sc);
	}
	
	public SimpleCategory get(String key){
		for(SimpleCategory sc:simpleCategories){
			if(key.equalsIgnoreCase(sc.getCategoryCd()) || key.equalsIgnoreCase(sc.getCategoryEname()) || key.equalsIgnoreCase(sc.getCategoryCname())){
				return sc;
			}
		}
		return null;
	}
	
	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Set<SimpleCategory> getSimpleCategories() {
		return simpleCategories;
	}

	public void setSimpleCategories(Set<SimpleCategory> simpleCategories) {
		this.simpleCategories = simpleCategories;
	}

	@Override
	public String toString() {
		return "CategoryMap [groupId=" + groupId + "]";
	}
}
