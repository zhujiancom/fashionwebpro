package com.zj.business.vo;

import com.zj.business.observer.LanguageType;
import com.zj.business.po.Brand;
import com.zj.common.utils.StringUtil;

public class BrandVO extends AbstractVO{
	private Brand brand;
	private long id;
	private String name;
	private String thumbnailUrl;
	private String detailContent;
	
	
	public BrandVO(Brand brand){
		super();
		this.brand = brand;
		this.thumbnailUrl = generateThumbnailUrl(brand.getBrandimg());
		setId(brand.getBrandid());
	}

	@Override
	protected void setEnglishValue() {
		setName(brand.getBrandEname());
		String content = StringUtil.getStrFromBlob(brand.getDetailContentEN());
		setDetailContent(content);
	}

	@Override
	protected void setChineseValue(LanguageType language) {
		setName(convertTCSC(brand.getBrandCname(),language));
		String content = StringUtil.getStrFromBlob(brand.getDetailContentCH());
		setDetailContent(convertTCSC(content,language));
	}


	public Brand getBrand() {
		return brand;
	}


	public void setBrand(Brand brand) {
		this.brand = brand;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getThumbnailUrl() {
		return thumbnailUrl;
	}


	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}


	public String getDetailContent() {
		return detailContent;
	}


	public void setDetailContent(String detailContent) {
		this.detailContent = detailContent;
	}
}
