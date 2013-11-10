package com.zj.common.cache;

import java.util.List;

import com.zj.common.ztreenode.ZTreeNode;

public class ModuleCache extends AbstractEHCache<String, List<ZTreeNode>> {

	public ModuleCache() {
		super("modulelistCache", 1);
	}
}
