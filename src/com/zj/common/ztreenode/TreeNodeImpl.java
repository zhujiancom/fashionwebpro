package com.zj.common.ztreenode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.zj.common.utils.ClassUtil;

public class TreeNodeImpl implements TreeNode{
	private AbstractMakeDataStrategy strategy;
	
	public TreeNodeImpl(AbstractMakeDataStrategy strategy) {
		super();
		this.strategy = strategy;
	}



	public AbstractMakeDataStrategy getStrategy() {
		return strategy;
	}



	public void setStrategy(AbstractMakeDataStrategy strategy) {
		this.strategy = strategy;
	}



	@Override
	public List<ZTreeNode> generateNode() {
		List<ZTreeNode> treelist = new ArrayList<ZTreeNode>();
		if(strategy != null){
			List<Map<String,Object>> datamaps = strategy.makedata();
			for(int i=0;i<datamaps.size();i++){
				Map<String,Object> data = datamaps.get(i);
				ZTreeNode node = new ZTreeNode();
				node = (ZTreeNode) ClassUtil.setValue(node, data);
				treelist.add(node);
			}
		}
		return treelist;
	}
	
	
}
