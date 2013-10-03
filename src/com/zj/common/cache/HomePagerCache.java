package com.zj.common.cache;

import com.zj.business.po.HomePager;

public class HomePagerCache extends AbstractEHCache<Long, HomePager> {

	public HomePagerCache() {
		super("homepagerCache", 1);
	}

}
