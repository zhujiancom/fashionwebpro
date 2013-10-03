package com.zj.business.treenode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.zj.business.po.Brand;
import com.zj.business.vo.Language;
import com.zj.common.ztreenode.AbstractMakeDataStrategy;

public class BrandStrategy extends AbstractMakeDataStrategy {
	private long pid;
	private Collection<Brand> brands;
	private String language;

	public BrandStrategy(long pid, Collection<Brand> brands,String language) {
		super();
		this.pid = pid;
		this.brands = brands;
		this.language = language;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public Collection<Brand> getBrands() {
		return brands;
	}

	public void setBrands(Collection<Brand> brands) {
		this.brands = brands;
	}

	@Override
	public List<Map<String, Object>> makedata() {
		List<Map<String,Object>> datamap = new ArrayList<Map<String,Object>>();
		Iterator<Brand> it = brands.iterator();
		while(it.hasNext()){
			Brand b = it.next();
			Map<String,Object> brandMap = new HashMap<String,Object>();
			brandMap.put("treeid", generateId());
			brandMap.put("pid",pid);
			if(Language.ZH_CN.equals(language)||Language.ZH_TW.equals(language)){
				brandMap.put("name", b.getBrandCname());
			}else{
				brandMap.put("name", b.getBrandEname());
			}
			brandMap.put("isparent", "false");
			brandMap.put("nodetype", "brand");
			brandMap.put("url","frontend/brand_showBrandDetail.action?brand.brandid="+b.getBrandid());
			brandMap.put("target", "mainPanel");
			datamap.add(brandMap);
		}
		return datamap;
	}
}
