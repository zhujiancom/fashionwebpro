package com.zj.business.po;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.zj.bigdefine.GlobalParam;
import com.zj.bigdefine.ReferenceKey;
import com.zj.common.annotation.CategoryGroup;
import com.zj.common.annotation.DisplayName;
import com.zj.common.annotation.JsonData;
import com.zj.common.annotation.UpdateColumn;
import com.zj.core.po.AbstractEntity;


@Entity
@Table(name="BUS_DESIGNER" , catalog = GlobalParam.CATALOG_DB)
@SequenceGenerator(name="commSEQ" , catalog=GlobalParam.CATALOG_DB ,allocationSize=1,initialValue=1)
public class Designer extends AbstractEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1896663969362221348L;

	private long designerId;
	@UpdateColumn
	private String cname;
	@DisplayName
	@UpdateColumn
	private String ename;
	@CategoryGroup(referneceKey=ReferenceKey.SEX)
	@UpdateColumn
	private String gender;
	@UpdateColumn
	private String bornyear;
	@UpdateColumn
	private Integer rank;
	@UpdateColumn
	private String borncountry_zh;
	@UpdateColumn
	private String borncountry_en;
	@UpdateColumn
	private String borncity_zh;
	@UpdateColumn
	private String borncity_en;
	@CategoryGroup(referneceKey=ReferenceKey.NATIONALITY)
	@UpdateColumn
	private String nationality_zh;
	@CategoryGroup(referneceKey=ReferenceKey.NATIONALITY)
	@UpdateColumn
	private String nationality_en;
	@UpdateColumn
	private String livingcountry_zh;
	@UpdateColumn
	private String livingcountry_en;
	@UpdateColumn
	private String livingcity_zh;
	@UpdateColumn
	private String livingcity_en;
	@UpdateColumn
	private String idealclient_zh;
	@UpdateColumn
	private String idealclient_en;
	@UpdateColumn
	private String trademarkpiece_zh;
	@UpdateColumn
	private String trademarkpiece_en;
	@UpdateColumn
	private String educationbg_zh;
	@UpdateColumn
	private String educationbg_en;
	@UpdateColumn
	private String personalcareerexpr_zh;
	@UpdateColumn
	private String personalcareerexpr_en;
	@UpdateColumn
	private String contactlist;
	@UpdateColumn
	private String officialwebsite;
	@UpdateColumn(filterColumn=true)
	private String imgURL;
	@UpdateColumn
	private String signature;
	@UpdateColumn
	private Set<Brand> brands;
	@UpdateColumn
	private Set<Interview> interviews;
	@UpdateColumn
	private Set<Report> reports;
	private Blob detailContentCH;
	private Blob detailContentEN;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator = "commSEQ")
	@Column(name="DESIGNER_ID", nullable=false)
	@JsonData(type=GlobalParam.JSONTYPE_ID)
	public long getDesignerId() {
		return designerId;
	}
	public void setDesignerId(long designerId) {
		this.designerId = designerId;
	}
	
	@Column(name="C_NAME",length=100)
	@JsonData
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	@Column(name="E_NAME",length=100)
	@JsonData
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	@Column(name="GENDER",length=20)
	@JsonData
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Column(name="BORNYEAR",length=4)
	@JsonData
	public String getBornyear() {
		return bornyear;
	}
	public void setBornyear(String bornyear) {
		this.bornyear = bornyear;
	}
	
	@Column(name="RANK",length=4)
	@JsonData
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
	@Column(name="BORNCOUNTRY_ZH",length=50)
	@JsonData
	public String getBorncountry_zh() {
		return borncountry_zh;
	}
	public void setBorncountry_zh(String borncountry_zh) {
		this.borncountry_zh = borncountry_zh;
	}
	
	@Column(name="BORNCOUNTRY_EN",length=50)
	@JsonData
	public String getBorncountry_en() {
		return borncountry_en;
	}
	public void setBorncountry_en(String borncountry_en) {
		this.borncountry_en = borncountry_en;
	}
	
	@Column(name="BORNCITY_ZH",length=50)
	@JsonData
	public String getBorncity_zh() {
		return borncity_zh;
	}
	public void setBorncity_zh(String borncity_zh) {
		this.borncity_zh = borncity_zh;
	}
	
	@Column(name="BORNCITY_EN",length=50)
	@JsonData
	public String getBorncity_en() {
		return borncity_en;
	}
	public void setBorncity_en(String borncity_en) {
		this.borncity_en = borncity_en;
	}
	
	@Column(name="NATIONALITY_ZH",length=50)
	@JsonData
	public String getNationality_zh() {
		return nationality_zh;
	}
	public void setNationality_zh(String nationality_zh) {
		this.nationality_zh = nationality_zh;
	}
	
	@Column(name="NATIONALITY_EN",length=50)
	@JsonData
	public String getNationality_en() {
		return nationality_en;
	}
	public void setNationality_en(String nationality_en) {
		this.nationality_en = nationality_en;
	}
	
	@Column(name="LIVINGCOUNTRY_ZH",length=50)
	@JsonData
	public String getLivingcountry_zh() {
		return livingcountry_zh;
	}
	public void setLivingcountry_zh(String livingcountry_zh) {
		this.livingcountry_zh = livingcountry_zh;
	}
	
	@Column(name="LIVINGCOUNTRY_EN",length=50)
	@JsonData
	public String getLivingcountry_en() {
		return livingcountry_en;
	}
	public void setLivingcountry_en(String livingcountry_en) {
		this.livingcountry_en = livingcountry_en;
	}
	
	@Column(name="LIVINGCITY_ZH",length=50)
	@JsonData
	public String getLivingcity_zh() {
		return livingcity_zh;
	}
	public void setLivingcity_zh(String livingcity_zh) {
		this.livingcity_zh = livingcity_zh;
	}
	
	@Column(name="LIVINGCITY_EN",length=50)
	@JsonData
	public String getLivingcity_en() {
		return livingcity_en;
	}
	public void setLivingcity_en(String livingcity_en) {
		this.livingcity_en = livingcity_en;
	}
	
	@Column(name="IDEALCLIENT_ZH",length=500)
	@JsonData
	public String getIdealclient_zh() {
		return idealclient_zh;
	}
	public void setIdealclient_zh(String idealclient_zh) {
		this.idealclient_zh = idealclient_zh;
	}
	
	@Column(name="IDEALCLIENT_EN",length=500)
	@JsonData
	public String getIdealclient_en() {
		return idealclient_en;
	}
	public void setIdealclient_en(String idealclient_en) {
		this.idealclient_en = idealclient_en;
	}
	
	@Column(name="TRADEMARKPIECE_ZH",length=500)
	@JsonData
	public String getTrademarkpiece_zh() {
		return trademarkpiece_zh;
	}
	public void setTrademarkpiece_zh(String trademarkpiece_zh) {
		this.trademarkpiece_zh = trademarkpiece_zh;
	}
	
	@Column(name="TRADEMARKPIECE_EN",length=500)
	@JsonData
	public String getTrademarkpiece_en() {
		return trademarkpiece_en;
	}
	public void setTrademarkpiece_en(String trademarkpiece_en) {
		this.trademarkpiece_en = trademarkpiece_en;
	}
	
	@Column(name="EDUCATIONBG_ZH",length=1000)
	public String getEducationbg_zh() {
		return educationbg_zh;
	}
	public void setEducationbg_zh(String educationbg_zh) {
		this.educationbg_zh = educationbg_zh;
	}
	
	@Column(name="EDUCATIONBG_EN",length=1000)
	public String getEducationbg_en() {
		return educationbg_en;
	}
	public void setEducationbg_en(String educationbg_en) {
		this.educationbg_en = educationbg_en;
	}
	
	@Column(name="PERSONALCAREEREXPR_ZH",length=1000)
	public String getPersonalcareerexpr_zh() {
		return personalcareerexpr_zh;
	}
	public void setPersonalcareerexpr_zh(String personalcareerexpr_zh) {
		this.personalcareerexpr_zh = personalcareerexpr_zh;
	}
	
	@Column(name="PERSONALCAREEREXPR_EN")
	public String getPersonalcareerexpr_en() {
		return personalcareerexpr_en;
	}
	public void setPersonalcareerexpr_en(String personalcareerexpr_en) {
		this.personalcareerexpr_en = personalcareerexpr_en;
	}
	
	@Column(name="CONTACTLIST",length=1000)
	@JsonData
	public String getContactlist() {
		return contactlist;
	}
	public void setContactlist(String contactlist) {
		this.contactlist = contactlist;
	}
	
	@Column(name="OFFICIALWEBSITE",length=200)
	@JsonData
	public String getOfficialwebsite() {
		return officialwebsite;
	}
	public void setOfficialwebsite(String officialwebsite) {
		this.officialwebsite = officialwebsite;
	}
	@Column(name="IMAGE",length=200)
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	@Column(name="SIGNATURE",length=200)
	@JsonData
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	@OneToMany(mappedBy="designer",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	public Set<Brand> getBrands() {
		return brands;
	}
	public void setBrands(Set<Brand> brands) {
		this.brands = brands;
	}
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="DESIGNER_ID")
	public Set<Interview> getInterviews() {
		return interviews;
	}
	public void setInterviews(Set<Interview> interviews) {
		this.interviews = interviews;
	}
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="DESIGNER_ID")
	public Set<Report> getReports() {
		return reports;
	}
	public void setReports(Set<Report> reports) {
		this.reports = reports;
	}
	@Lob
	@Basic(fetch = FetchType.LAZY) 
	@Column(name="DETAIL_CONTENT_CH", columnDefinition="blob")
	public Blob getDetailContentCH() {
		return detailContentCH;
	}
	public void setDetailContentCH(Blob detailContentCH) {
		this.detailContentCH = detailContentCH;
	}
	@Lob
	@Basic(fetch = FetchType.LAZY) 
	@Column(name="DETAIL_CONTENT_EN", columnDefinition="blob")
	public Blob getDetailContentEN() {
		return detailContentEN;
	}
	public void setDetailContentEN(Blob detailContentEN) {
		this.detailContentEN = detailContentEN;
	}

	
}
