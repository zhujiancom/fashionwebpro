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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.zj.bigdefine.GlobalParam;
import com.zj.bigdefine.ReferenceKey;
import com.zj.common.annotation.CategoryGroup;
import com.zj.common.annotation.DisplayName;
import com.zj.common.annotation.JsonData;
import com.zj.common.annotation.UpdateColumn;
import com.zj.core.po.AbstractEntity;


@Entity
@Table(name="BUS_BRAND" , catalog = GlobalParam.CATALOG_DB)
@SequenceGenerator(name="commSEQ" , catalog=GlobalParam.CATALOG_DB ,allocationSize=1,initialValue=1)
public class Brand extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8929879124385476713L;

	private long brandid;
	@UpdateColumn(filterColumn=true)
	private String brandimg;
	@UpdateColumn
	private String brandCname;
	@DisplayName
	@UpdateColumn
	private String brandEname;
	@UpdateColumn
	private String establishyear;
	@CategoryGroup(referneceKey=ReferenceKey.PRICE_RANGE)
	@UpdateColumn
	private String pricerange;
	@UpdateColumn
	private Integer storenum;
	@UpdateColumn
	private String targetcustomerZh;
	@UpdateColumn
	private String targetcustomerEn;
	@UpdateColumn
	private String brandmissionZh;
	@UpdateColumn
	private String brandmissionEn;
	@CategoryGroup(referneceKey=ReferenceKey.SALES_VOLUME)
	@UpdateColumn
	private String estimatedanto;
	@CategoryGroup(referneceKey=ReferenceKey.SALES_VOLUME)
	@UpdateColumn
	private String lastestanto;
	@UpdateColumn
	private String uniquespZh;
	@UpdateColumn
	private String uniquespEn;
	@CategoryGroup(referneceKey=ReferenceKey.OPERATION_MODE)
	@UpdateColumn
	private String primaryomZh;
	@CategoryGroup(referneceKey=ReferenceKey.OPERATION_MODE)
	@UpdateColumn
	private String primaryomEn;
	@UpdateColumn
	private String sellincountryZh;
	@UpdateColumn
	private String sellincountryEn;
	@UpdateColumn
	private String storeaddr;
	@UpdateColumn
	private String officialwebsite;
	@UpdateColumn
	private Designer designer;
	@UpdateColumn
	private Set<Style> styles;
	@UpdateColumn
	private Set<Editorial> editorials;
	@UpdateColumn
	private Set<Lookbook> lookbooks;
	@UpdateColumn
	private Set<Runwayshow> runwayshows;
	
	private Blob detailContentCH;
	
	private Blob detailContentEN;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator = "commSEQ")
	@Column(name="BRAND_ID", nullable=false)
	@JsonData(type=GlobalParam.JSONTYPE_ID)
	public long getBrandid() {
		return brandid;
	}
	public void setBrandid(long brandid) {
		this.brandid = brandid;
	}
	
	@Column(name="BRANDIMG",length=200)
	public String getBrandimg() {
		return brandimg;
	}
	public void setBrandimg(String brandimg) {
		this.brandimg = brandimg;
	}
	
	@JsonData
	@Column(name="BRANDCNAME",length=50)
	public String getBrandCname() {
		return brandCname;
	}
	public void setBrandCname(String brandCname) {
		this.brandCname = brandCname;
	}
	
	@JsonData
	@Column(name="BRANDENAME",length=50)
	public String getBrandEname() {
		return brandEname;
	}
	public void setBrandEname(String brandEname) {
		this.brandEname = brandEname;
	}
	
	@JsonData
	@Column(name="ESTABLISHYEAR",length=4)
	public String getEstablishyear() {
		return establishyear;
	}
	public void setEstablishyear(String establishyear) {
		this.establishyear = establishyear;
	}
	
	@JsonData
	@Column(name="PRICERANGE",length=50)
	public String getPricerange() {
		return pricerange;
	}
	public void setPricerange(String pricerange) {
		this.pricerange = pricerange;
	}
	
	@JsonData
	@Column(name="STORENUM",length=50)
	public Integer getStorenum() {
		return storenum;
	}
	public void setStorenum(Integer storenum) {
		this.storenum = storenum;
	}
	
	@Column(name="TARGETCUSZH",length=500)
	public String getTargetcustomerZh() {
		return targetcustomerZh;
	}
	public void setTargetcustomerZh(String targetcustomerZh) {
		this.targetcustomerZh = targetcustomerZh;
	}
	
	@Column(name="TARGETCUSEN",length=500)
	public String getTargetcustomerEn() {
		return targetcustomerEn;
	}
	public void setTargetcustomerEn(String targetcustomerEn) {
		this.targetcustomerEn = targetcustomerEn;
	}
	
	@Column(name="BRANDMISIONZH",length=500)
	public String getBrandmissionZh() {
		return brandmissionZh;
	}
	public void setBrandmissionZh(String brandmissionZh) {
		this.brandmissionZh = brandmissionZh;
	}
	
	@Column(name="BRANDMISSIONEN",length=500)
	public String getBrandmissionEn() {
		return brandmissionEn;
	}
	public void setBrandmissionEn(String brandmissionEn) {
		this.brandmissionEn = brandmissionEn;
	}
	
	@JsonData
	@Column(name="ESTIMATEDANTO",length=500)
	public String getEstimatedanto() {
		return estimatedanto;
	}
	public void setEstimatedanto(String estimatedanto) {
		this.estimatedanto = estimatedanto;
	}
	
	@JsonData
	@Column(name="LASTESTANTO",length=500)
	public String getLastestanto() {
		return lastestanto;
	}
	public void setLastestanto(String lastestanto) {
		this.lastestanto = lastestanto;
	}
	
	@Column(name="UNIQUESPZH",length=500)
	public String getUniquespZh() {
		return uniquespZh;
	}
	public void setUniquespZh(String uniquespZh) {
		this.uniquespZh = uniquespZh;
	}
	
	@Column(name="UNIQUESEPEN",length=500)
	public String getUniquespEn() {
		return uniquespEn;
	}
	public void setUniquespEn(String uniquespEn) {
		this.uniquespEn = uniquespEn;
	}
	
	@JsonData
	@Column(name="PRIMARYOMZH",length=500)
	public String getPrimaryomZh() {
		return primaryomZh;
	}
	public void setPrimaryomZh(String primaryomZh) {
		this.primaryomZh = primaryomZh;
	}
	
	@JsonData
	@Column(name="PRIMARYOMEN",length=500)
	public String getPrimaryomEn() {
		return primaryomEn;
	}
	public void setPrimaryomEn(String primaryomEn) {
		this.primaryomEn = primaryomEn;
	}
	
	@Column(name="SELLINCOUNTRYZH",length=500)
	public String getSellincountryZh() {
		return sellincountryZh;
	}
	public void setSellincountryZh(String sellincountryZh) {
		this.sellincountryZh = sellincountryZh;
	}
	
	@Column(name="SELLINCOUNTRYEN",length=500)
	public String getSellincountryEn() {
		return sellincountryEn;
	}
	public void setSellincountryEn(String sellincountryEn) {
		this.sellincountryEn = sellincountryEn;
	}
	
	@JsonData
	@Column(name="STOREADDR",length=500)
	public String getStoreaddr() {
		return storeaddr;
	}
	public void setStoreaddr(String storeaddr) {
		this.storeaddr = storeaddr;
	}
	
	@JsonData
	@Column(name="OFFICIALWEBSITE",length=500)
	public String getOfficialwebsite() {
		return officialwebsite;
	}
	public void setOfficialwebsite(String officialwebsite) {
		this.officialwebsite = officialwebsite;
	}
	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "DESIGNER_ID",referencedColumnName="DESIGNER_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	@JsonData(type=GlobalParam.JSONTYPE_REFERENCE)
	public Designer getDesigner() {
		return designer;
	}
	public void setDesigner(Designer designer) {
		this.designer = designer;
	}
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "STYLE_BRAND_REL", joinColumns = {@JoinColumn(name ="BRAND_ID" )}, inverseJoinColumns = { @JoinColumn(name = "STYLE_ID") })
	public Set<Style> getStyles() {
		return styles;
	}
	public void setStyles(Set<Style> styles) {
		this.styles = styles;
	}
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="BRAND_ID")
	public Set<Editorial> getEditorials() {
		return editorials;
	}
	public void setEditorials(Set<Editorial> editorials) {
		this.editorials = editorials;
	}
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="BRAND_ID")
	public Set<Lookbook> getLookbooks() {
		return lookbooks;
	}
	public void setLookbooks(Set<Lookbook> lookbooks) {
		this.lookbooks = lookbooks;
	}
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="BRAND_ID")
	public Set<Runwayshow> getRunwayshows() {
		return runwayshows;
	}
	public void setRunwayshows(Set<Runwayshow> runwayshows) {
		this.runwayshows = runwayshows;
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
