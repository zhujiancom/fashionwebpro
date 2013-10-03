package com.zj.common.utils;

import com.zj.common.annotation.JsonData;

public class SimpleCategory {
	String categoryCd;
	String categoryEname;
	String categoryCname;
	
	public SimpleCategory(String categoryCd, String categoryEname,
			String categoryCname) {
		super();
		this.categoryCd = categoryCd;
		this.categoryEname = categoryEname;
		this.categoryCname = categoryCname;
	}
	
	@JsonData
	public String getCategoryCd() {
		return categoryCd;
	}
	public void setCategoryCd(String categoryCd) {
		this.categoryCd = categoryCd;
	}
	@JsonData
	public String getCategoryEname() {
		return categoryEname;
	}
	public void setCategoryEname(String categoryEnam) {
		this.categoryEname = categoryEnam;
	}
	@JsonData
	public String getCategoryCname() {
		return categoryCname;
	}
	public void setCategoryCname(String categoryCname) {
		this.categoryCname = categoryCname;
	}

	@Override
	public String toString() {
		return "SimpleCategory [categoryCd=" + categoryCd
				+ ", categoryEname=" + categoryEname + ", categoryCname="
				+ categoryCname + "]";
	}

	@Override
	public int hashCode() {
		int result = 17;
		int c = this.getCategoryEname().hashCode();
		return result+c;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == this){
			return true;
		}
		if(!(obj instanceof SimpleCategory)){
			return false;
		}
		SimpleCategory sc = (SimpleCategory) obj;
		return this.getCategoryCd().equals(sc.getCategoryCd()) || this.getCategoryCname().equals(sc.getCategoryCname()) || this.getCategoryEname().equals(sc.getCategoryEname());
	}
}
