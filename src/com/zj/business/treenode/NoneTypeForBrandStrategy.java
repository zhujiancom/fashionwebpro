package com.zj.business.treenode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zj.business.po.Brand;
import com.zj.business.vo.Language;
import com.zj.common.ztreenode.AbstractMakeDataStrategy;

public class NoneTypeForBrandStrategy extends AbstractMakeDataStrategy {
	private Brand brand;
	private String language;
	public NoneTypeForBrandStrategy() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoneTypeForBrandStrategy(Brand brand,String language) {
		super();
		this.brand = brand;
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
	
	public List<Map<String, Object>> makeEn(){
		long designerId = brand.getDesigner().getDesignerId();
		List<Map<String,Object>> nodemaps = new ArrayList<Map<String,Object>>();
		Map<String,Object> profileMap = new HashMap<String,Object>();
		profileMap.put("treeid", generateId());
		profileMap.put("pid",0);
		profileMap.put("name", "Profile");
		profileMap.put("isparent", "false");
		profileMap.put("nodetype", "profile");
		profileMap.put("url", "frontend/designer_showProfile.action?designer.designerId="+designerId);
		profileMap.put("target", "mainPanel");
		
		Map<String,Object> brandIntroMap = new HashMap<String,Object>();
		brandIntroMap.put("treeid", generateId());
		brandIntroMap.put("pid",0);
		brandIntroMap.put("name", "Brand Instruction");
		brandIntroMap.put("isparent", "false");
		brandIntroMap.put("url", "frontend/brand_showBrandInfo.action?brand.brandid="+brand.getBrandid());
		brandIntroMap.put("target", "mainPanel");
		
		Map<String,Object> pressMap = new HashMap<String,Object>();
		pressMap.put("treeid", generateId());
		pressMap.put("pid",0);
		pressMap.put("name", "Press");
		pressMap.put("isparent", "true");
		pressMap.put("nodetype", "pressforbrand");
		
		Map<String,Object> collectionMap = new HashMap<String,Object>();
		collectionMap.put("treeid", generateId());
		collectionMap.put("pid",0);
		collectionMap.put("name", "Collection");
		collectionMap.put("isparent", "true");
		collectionMap.put("nodetype", "collectionforbrand");
		
		Map<String,Object> rootnodeMap = new HashMap<String,Object>();
		rootnodeMap.put("treeid",0);
		rootnodeMap.put("name", "MENU");
		rootnodeMap.put("isparent", true);
		rootnodeMap.put("open", true);
		
		nodemaps.add(rootnodeMap);
		nodemaps.add(brandIntroMap);
		nodemaps.add(profileMap);
		nodemaps.add(pressMap);
		nodemaps.add(collectionMap);
		
 		return nodemaps;
	}
	public List<Map<String, Object>> makeZh(){
		long designerId = brand.getDesigner().getDesignerId();
		List<Map<String,Object>> nodemaps = new ArrayList<Map<String,Object>>();
		Map<String,Object> profileMap = new HashMap<String,Object>();
		profileMap.put("treeid", generateId());
		profileMap.put("pid",0);
		profileMap.put("name", "简介");
		profileMap.put("isparent", "false");
		profileMap.put("nodetype", "profile");
		profileMap.put("url", "frontend/designer_showProfile.action?designer.designerId="+designerId);
		profileMap.put("target", "mainPanel");
		
		Map<String,Object> brandIntroMap = new HashMap<String,Object>();
		brandIntroMap.put("treeid", generateId());
		brandIntroMap.put("pid",0);
		brandIntroMap.put("name", "品牌介绍");
		brandIntroMap.put("isparent", "false");
		brandIntroMap.put("url", "frontend/brand_showBrandInfo.action?brand.brandid="+brand.getBrandid());
		brandIntroMap.put("target", "mainPanel");
		
		Map<String,Object> pressMap = new HashMap<String,Object>();
		pressMap.put("treeid", generateId());
		pressMap.put("pid",0);
		pressMap.put("name", "采访");
		pressMap.put("isparent", "true");
		pressMap.put("nodetype", "pressforbrand");
		
		Map<String,Object> collectionMap = new HashMap<String,Object>();
		collectionMap.put("treeid", generateId());
		collectionMap.put("pid",0);
		collectionMap.put("name", "作品集");
		collectionMap.put("isparent", "true");
		collectionMap.put("nodetype", "collectionforbrand");
		
		Map<String,Object> rootnodeMap = new HashMap<String,Object>();
		rootnodeMap.put("treeid",0);
		rootnodeMap.put("name", "目录");
		rootnodeMap.put("isparent", true);
		rootnodeMap.put("open", true);
		
		nodemaps.add(rootnodeMap);
		nodemaps.add(brandIntroMap);
		nodemaps.add(profileMap);
		nodemaps.add(pressMap);
		nodemaps.add(collectionMap);
		
 		return nodemaps;
	}

}
