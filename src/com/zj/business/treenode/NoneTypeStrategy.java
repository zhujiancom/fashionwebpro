package com.zj.business.treenode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zj.business.vo.Language;
import com.zj.common.ztreenode.AbstractMakeDataStrategy;

public class NoneTypeStrategy extends AbstractMakeDataStrategy {
	private long designerId;
	private String language;
	
	public NoneTypeStrategy() {
	}

	public NoneTypeStrategy(long designerId , String language) {
		super();
		this.designerId = designerId;
		this.language = language;
	}

	@Override
	public List<Map<String, Object>> makedata() {
		if(Language.EN_US.equals(language)){
			return makeEn();
		}else{
			return makeCh();
		}
	}
	
	public List<Map<String, Object>> makeEn(){
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
		brandIntroMap.put("isparent", "true");
		brandIntroMap.put("nodetype", "brand");
		
		Map<String,Object> pressMap = new HashMap<String,Object>();
		pressMap.put("treeid", generateId());
		pressMap.put("pid",0);
		pressMap.put("name", "Press");
		pressMap.put("isparent", "true");
		pressMap.put("nodetype", "press");
		
		Map<String,Object> collectionMap = new HashMap<String,Object>();
		collectionMap.put("treeid", generateId());
		collectionMap.put("pid",0);
		collectionMap.put("name", "Collection");
		collectionMap.put("isparent", "true");
		collectionMap.put("nodetype", "collectionone");
		
		Map<String,Object> rootnodeMap = new HashMap<String,Object>();
		rootnodeMap.put("treeid",0);
		rootnodeMap.put("name", "MENU");
		rootnodeMap.put("isparent", true);
		rootnodeMap.put("open", true);
		
		nodemaps.add(rootnodeMap);
		nodemaps.add(profileMap);
		nodemaps.add(brandIntroMap);
		nodemaps.add(pressMap);
		nodemaps.add(collectionMap);
		return nodemaps;
	}

	public List<Map<String, Object>> makeCh(){
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
		brandIntroMap.put("isparent", "true");
		brandIntroMap.put("nodetype", "brand");
		
		Map<String,Object> pressMap = new HashMap<String,Object>();
		pressMap.put("treeid", generateId());
		pressMap.put("pid",0);
		pressMap.put("name", "采访");
		pressMap.put("isparent", "true");
		pressMap.put("nodetype", "press");
		
		Map<String,Object> collectionMap = new HashMap<String,Object>();
		collectionMap.put("treeid", generateId());
		collectionMap.put("pid",0);
		collectionMap.put("name", "作品集");
		collectionMap.put("isparent", "true");
		collectionMap.put("nodetype", "collectionone");
		
		Map<String,Object> rootnodeMap = new HashMap<String,Object>();
		rootnodeMap.put("treeid",0);
		rootnodeMap.put("name", "目录");
		rootnodeMap.put("isparent", true);
		rootnodeMap.put("open", true);
		
		nodemaps.add(rootnodeMap);
		nodemaps.add(profileMap);
		nodemaps.add(brandIntroMap);
		nodemaps.add(pressMap);
		nodemaps.add(collectionMap);
		return nodemaps;
	}
	public long getDesignerId() {
		return designerId;
	}

	public void setDesignerId(long designerId) {
		this.designerId = designerId;
	}

}
