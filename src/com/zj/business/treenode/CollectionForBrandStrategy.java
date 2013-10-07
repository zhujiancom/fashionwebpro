package com.zj.business.treenode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zj.business.po.Brand;
import com.zj.business.vo.Language;
import com.zj.common.ztreenode.AbstractMakeDataStrategy;

public class CollectionForBrandStrategy extends AbstractMakeDataStrategy {
	private Brand brand;
	private long pid;
	private String language;
	
	public CollectionForBrandStrategy(Brand brand,long pid,String language) {
		super();
		this.brand = brand;
		this.pid = pid;
		this.language = language;
	}

	@Override
	public List<Map<String, Object>> makedata() {
		if(Language.EN_US.equals(language)){
			return makeEn();
		}else{
			return makeZh();
		}
	}

	public List<Map<String, Object>> makeZh(){
		long brandId = brand.getBrandid();
		List<Map<String,Object>> datamaps = new ArrayList<Map<String,Object>>();
		Map<String,Object> lookbook = new HashMap<String,Object>();
		lookbook.put("treeid", generateId());
		lookbook.put("pid", pid);
		lookbook.put("name", "型录");
		lookbook.put("isparent", "false");
		lookbook.put("url", "frontend/lookbook_showByBrand.action?brand.brandid="+brandId);
		lookbook.put("target", "mainPanel");
		
		Map<String,Object> editoral = new HashMap<String,Object>();
		editoral.put("treeid", generateId());
		editoral.put("pid", pid);
		editoral.put("name", "广告画");
		editoral.put("isparent", "false");
		editoral.put("url", "frontend/editorial_showByBrand.action?brand.brandid="+brandId);
		editoral.put("target", "mainPanel");
		
		Map<String,Object> runwayshow = new HashMap<String,Object>();
		runwayshow.put("treeid", generateId());
		runwayshow.put("pid", pid);
		runwayshow.put("name", "T 台秀");
		runwayshow.put("isparent", "false");
		runwayshow.put("url", "frontend/runwayshow_showByBrand.action?brand.brandid="+brandId);
		runwayshow.put("target", "mainPanel");
		
		datamaps.add(editoral);
		datamaps.add(runwayshow);
		datamaps.add(lookbook);
		return datamaps;
	}
	
	public List<Map<String, Object>> makeEn(){
		long brandId = brand.getBrandid();
		List<Map<String,Object>> datamaps = new ArrayList<Map<String,Object>>();
		Map<String,Object> lookbook = new HashMap<String,Object>();
		lookbook.put("treeid", generateId());
		lookbook.put("pid", pid);
		lookbook.put("name", "LookBook");
		lookbook.put("isparent", "false");
		lookbook.put("url", "frontend/lookbook_showByBrand.action?brand.brandid="+brandId);
		lookbook.put("target", "mainPanel");
		
		Map<String,Object> editoral = new HashMap<String,Object>();
		editoral.put("treeid", generateId());
		editoral.put("pid", pid);
		editoral.put("name", "Editorial Image");
		editoral.put("isparent", "false");
		editoral.put("url", "frontend/editorial_showByBrand.action?brand.brandid="+brandId);
		editoral.put("target", "mainPanel");
		
		Map<String,Object> runwayshow = new HashMap<String,Object>();
		runwayshow.put("treeid", generateId());
		runwayshow.put("pid", pid);
		runwayshow.put("name", "Runway Show");
		runwayshow.put("isparent", "false");
		runwayshow.put("url", "frontend/runwayshow_showByBrand.action?brand.brandid="+brandId);
		runwayshow.put("target", "mainPanel");
		
		datamaps.add(editoral);
		datamaps.add(runwayshow);
		datamaps.add(lookbook);
		return datamaps;
	}
}
