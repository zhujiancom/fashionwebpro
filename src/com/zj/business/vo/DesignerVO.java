package com.zj.business.vo;

import java.util.Iterator;
import java.util.Set;

import com.zj.bigdefine.ReferenceKey;
import com.zj.business.po.Brand;
import com.zj.business.po.Designer;
import com.zj.core.init.ApplicationInitialize;

public class DesignerVO extends AbstractVO {
	private Designer po;
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
	
	public DesignerVO(Designer po) {
		super();
		this.po = po;
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
		setName(po.getEname());
		setBorncity(po.getBorncity_en());
		setBorncountry(po.getBorncountry_en());
//		setNationality(ApplicationInitialize.categoryMap.get(ReferenceKey.NATIONALITY).get(po.getNationality_en()).getCategoryEname());
		setLivingcountry(po.getLivingcountry_en());
		setLivingcity(po.getLivingcity_en());
		setIdealclient(po.getIdealclient_en());
		setTrademarkpiece(po.getTrademarkpiece_en());
		setEducationbg(po.getEducationbg_en());
		setPersonalcareerexpr(po.getPersonalcareerexpr_en());
//		setGender(ApplicationInitialize.categoryMap.get(ReferenceKey.SEX).get(po.getGender()).getCategoryEname());
		
		Set<Brand> brandSet = po.getBrands();
		Iterator<Brand> it = brandSet.iterator();
		StringBuilder sb = new StringBuilder();
		while(it.hasNext()){
			Brand brand = it.next();
			sb.append(brand.getBrandEname()).append(",");
		}
		if(sb.length()>0){
			sb.setCharAt(sb.length()-1, ' ');
		}
		setBrands(sb.toString());
	}
	
	private void setChineseValue(String language){
		setName(convertTCSC(po.getCname(),language));
		setBorncity(convertTCSC(po.getBorncity_zh(),language));
		setBorncountry(convertTCSC(po.getBorncountry_zh(),language));
//		setNationality(convertTCSC(ApplicationInitialize.categoryMap.get(ReferenceKey.NATIONALITY).get(po.getNationality_zh()).getCategoryCname(),language));
		setLivingcountry(convertTCSC(po.getLivingcountry_zh(),language));
		setLivingcity(convertTCSC(po.getLivingcity_zh(),language));
		setIdealclient(convertTCSC(po.getIdealclient_zh(),language));
		setTrademarkpiece(convertTCSC(po.getTrademarkpiece_zh(),language));
		setEducationbg(convertTCSC(po.getEducationbg_zh(),language));
		setPersonalcareerexpr(convertTCSC(po.getPersonalcareerexpr_zh(),language));
//		setGender(convertTCSC(ApplicationInitialize.categoryMap.get(ReferenceKey.SEX).get(po.getGender()).getCategoryCname(),language));
		Set<Brand> brandSet = po.getBrands();
		Iterator<Brand> it = brandSet.iterator();
		StringBuilder sb = new StringBuilder();
		while(it.hasNext()){
			Brand brand = it.next();
			sb.append(brand.getBrandCname()).append(",");
		}
		if(sb.length()>0){
			sb.setCharAt(sb.length()-1, ' ');
		}
		setBrands(convertTCSC(sb.toString(),language));
	}
	
	public Designer getPo() {
		return po;
	}

	public void setPo(Designer po) {
		this.po = po;
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

}
