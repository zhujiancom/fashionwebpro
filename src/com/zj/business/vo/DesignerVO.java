package com.zj.business.vo;

import com.zj.business.po.Designer;
import com.zj.common.utils.StringUtil;

public class DesignerVO extends AbstractVO {
	private Designer designer;
	private String name;
	private String borncity;
	private String borncountry;
	private String nationality;
	private String livingcountry;
	private String livingcity;
	private String idealclient;
	private String trademarkpiece;
	private String educationbg;
	private String personalcareerexpr;
	private String gender;
	private String brands;
	private String profile;
	private String thumbnailUrl;
	
	public DesignerVO(){}
	
	public DesignerVO(Designer designer) {
		super();
		this.designer = designer;
		this.thumbnailUrl = generateThumbnailUrl(designer.getImgURL());
	}

	@Override
	public DesignerVO process(String lang) {
		if(EN_US.equalsIgnoreCase(lang)){
			setEnglishValue();
		}else{
			setChineseValue(lang);
		}
		return this;
	}

	private void setEnglishValue(){
		setName(designer.getEname());
		String content = StringUtil.getStrFromBlob(designer.getDetailContentEN());
		setProfile(content);
	}
	
	private void setChineseValue(String language){
		setName(convertTCSC(designer.getCname(),language));
		setProfile(convertTCSC(StringUtil.getStrFromBlob(designer.getDetailContentCH()),language));
	}
	
	public Designer getDesigner() {
		return designer;
	}

	public void setDesigner(Designer designer) {
		this.designer = designer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBorncity() {
		return borncity;
	}

	public void setBorncity(String borncity) {
		this.borncity = borncity;
	}

	public String getBorncountry() {
		return borncountry;
	}

	public void setBorncountry(String borncountry) {
		this.borncountry = borncountry;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getLivingcountry() {
		return livingcountry;
	}

	public void setLivingcountry(String livingcountry) {
		this.livingcountry = livingcountry;
	}

	public String getLivingcity() {
		return livingcity;
	}

	public void setLivingcity(String livingcity) {
		this.livingcity = livingcity;
	}

	public String getIdealclient() {
		return idealclient;
	}

	public void setIdealclient(String idealclient) {
		this.idealclient = idealclient;
	}

	public String getTrademarkpiece() {
		return trademarkpiece;
	}

	public void setTrademarkpiece(String trademarkpiece) {
		this.trademarkpiece = trademarkpiece;
	}

	public String getEducationbg() {
		return educationbg;
	}

	public void setEducationbg(String educationbg) {
		this.educationbg = educationbg;
	}

	public String getPersonalcareerexpr() {
		return personalcareerexpr;
	}

	public void setPersonalcareerexpr(String personalcareerexpr) {
		this.personalcareerexpr = personalcareerexpr;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBrands() {
		return brands;
	}

	public void setBrands(String brands) {
		this.brands = brands;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

}
