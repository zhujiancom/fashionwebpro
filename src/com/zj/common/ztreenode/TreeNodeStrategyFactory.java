package com.zj.common.ztreenode;

import java.util.Collection;

import com.zj.business.po.Brand;
import com.zj.business.treenode.BrandStrategy;
import com.zj.business.treenode.CollectionForBrandStrategy;
import com.zj.business.treenode.CollectionLevelOneStrategy;
import com.zj.business.treenode.CollectionLevelTwoStrategy;
import com.zj.business.treenode.NoneTypeForBrandStrategy;
import com.zj.business.treenode.NoneTypeStrategy;
import com.zj.business.treenode.PressForBrandStrategy;
import com.zj.business.treenode.PressStrategy;

public class TreeNodeStrategyFactory {
	public static TreeNodeStrategyFactory instance = null;
	
	private TreeNodeStrategyFactory(){};
	
	public synchronized TreeNodeStrategyFactory getInstance(){
		if(instance == null){
			return new TreeNodeStrategyFactory();
		}
		return instance;
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	public static AbstractMakeDataStrategy getNodeStrategy(NodeType nodetype,Object... objects){
		if(nodetype == NodeType.NONE){
			return new NoneTypeStrategy((Long) objects[0],(String) objects[1]);
		}else if(nodetype == NodeType.BRAND){
			return new BrandStrategy(Long.valueOf((String) objects[0]),(Collection)objects[1],(String) objects[2]);
		}else if(nodetype == NodeType.PRESS){
			return new PressStrategy((Long)objects[0], Long.valueOf((String) objects[1]),(String) objects[2]);
		}else if(nodetype == NodeType.COLLECTIONONE){
			return new CollectionLevelOneStrategy(Long.valueOf((String) objects[0]),(Collection)objects[1],(String) objects[2]);
		}else if(nodetype == NodeType.COLLECTIONTWO){
			return new CollectionLevelTwoStrategy((String)objects[0],(String) objects[1]);
		}else if(nodetype == NodeType.NONEFORBRAND){
			return new NoneTypeForBrandStrategy((Brand)objects[0],(String) objects[1]);
		}else if(nodetype == NodeType.PRESSFORBRAND){
			return new PressForBrandStrategy((Brand)objects[0],Long.valueOf((String)objects[1]),(String) objects[2]);
		}else if(nodetype == NodeType.COLLECTIONFORBRAND){
			return new CollectionForBrandStrategy((Brand)objects[0],Long.valueOf((String)objects[1]),(String) objects[2]);
		}
		return null;
	}
}
