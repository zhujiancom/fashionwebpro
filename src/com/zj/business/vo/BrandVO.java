package com.zj.business.vo;

import java.util.List;

import com.zj.business.po.Brand;
import com.zj.business.treenode.Menu;
import com.zj.common.utils.StringUtil;

public class BrandVO extends AbstractVO {
	
	private Brand brand;
	private String name;
	private String pricerange;
	private String targetcustomer;
	private String brandmission;
	private String estimate;
	private String latest;
	private String sellingpoint;
	private String operationmodel;
	private String countries;
	private String designername;
	private String styles;
	private String thumbnailUrl;
	private String detailContent;
	
	private List<Menu> menus;
	
	public BrandVO(Brand brand){
		super();
		this.brand = brand;
		this.thumbnailUrl = generateThumbnailUrl(brand.getBrandimg());
	}

	@Override
	public Language process(String lang) {
		if(EN_US.equalsIgnoreCase(lang)){
			setEnglishValue();
		}else{
			setChineseValue(lang);
		}
		return this;
	}

	public void setEnglishValue(){
		setName(brand.getBrandEname());
		String content = StringUtil.getStrFromBlob(brand.getDetailContentEN());
		setDetailContent(content);
	}
	
	private void setChineseValue(String language){
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPricerange() {
		return pricerange;
	}

	public void setPricerange(String pricerange) {
		this.pricerange = pricerange;
	}

	public String getTargetcustomer() {
		return targetcustomer;
	}

	public void setTargetcustomer(String targetcustomer) {
		this.targetcustomer = targetcustomer;
	}

	public String getBrandmission() {
		return brandmission;
	}

	public void setBrandmission(String brandmission) {
		this.brandmission = brandmission;
	}

	public String getEstimate() {
		return estimate;
	}

	public void setEstimate(String estimate) {
		this.estimate = estimate;
	}

	public String getLatest() {
		return latest;
	}

	public void setLatest(String latest) {
		this.latest = latest;
	}

	public String getSellingpoint() {
		return sellingpoint;
	}

	public void setSellingpoint(String sellingpoint) {
		this.sellingpoint = sellingpoint;
	}

	public String getOperationmodel() {
		return operationmodel;
	}

	public void setOperationmodel(String operationmodel) {
		this.operationmodel = operationmodel;
	}

	public String getCountries() {
		return countries;
	}

	public void setCountries(String countries) {
		this.countries = countries;
	}

	public String getDesignername() {
		return designername;
	}

	public void setDesignername(String designername) {
		this.designername = designername;
	}

	public String getStyles() {
		return styles;
	}

	public void setStyles(String styles) {
		this.styles = styles;
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

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	
}
