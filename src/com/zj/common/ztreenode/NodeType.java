package com.zj.common.ztreenode;

public enum NodeType {
	PROFILE,BRAND,PRESS,COLLECTIONONE,COLLECTIONTWO,NONE,
	PRESSFORBRAND,COLLECTIONFORBRAND,NONEFORBRAND;

	@Override
	public String toString() {
		return this.name().toLowerCase();
	}
	
}
