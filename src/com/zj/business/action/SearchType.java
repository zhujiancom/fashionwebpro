package com.zj.business.action;

public enum SearchType {
	BRAND,DESIGNER,STYLE,ALL;

	@Override
	public String toString() {
		return super.toString().toLowerCase();
	}
	
	
}
