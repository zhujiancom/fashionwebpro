package com.zj.business.po;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.zj.bigdefine.GlobalParam;
import com.zj.common.annotation.JsonData;
import com.zj.common.annotation.UpdateColumn;
import com.zj.core.po.AbstractEntity;
@Entity
@Table(name="BUS_STYLE" , catalog = GlobalParam.CATALOG_DB)
@SequenceGenerator(name="commSEQ" , catalog=GlobalParam.CATALOG_DB ,allocationSize=1,initialValue=1)
public class Style extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5784799296331859400L;

	private long styleid;
	@UpdateColumn(filterColumn=true)
	private String styleimg;
	@UpdateColumn
	private String styleCname;
	@UpdateColumn
	private String styleEname;
	@UpdateColumn
	private Set<Brand> brands;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator = "commSEQ")
	@Column(name="STYLE_ID", nullable=false)
	@JsonData(type=GlobalParam.JSONTYPE_ID)
	public long getStyleid() {
		return styleid;
	}
	public void setStyleid(long styleid) {
		this.styleid = styleid;
	}
	
	@Column(name="STYLEIMG",length=200)
	public String getStyleimg() {
		return styleimg;
	}
	public void setStyleimg(String styleimg) {
		this.styleimg = styleimg;
	}
	
	@Column(name="STYLECNAME",length=50)
	@JsonData
	public String getStyleCname() {
		return styleCname;
	}
	public void setStyleCname(String styleCname) {
		this.styleCname = styleCname;
	}
	
	@Column(name="STYLEENAME",length=50)
	@JsonData
	public String getStyleEname() {
		return styleEname;
	}
	public void setStyleEname(String styleEname) {
		this.styleEname = styleEname;
	}
	@ManyToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "styles", fetch = FetchType.LAZY)
	public Set<Brand> getBrands() {
		return brands;
	}
	public void setBrands(Set<Brand> brands) {
		this.brands = brands;
	}
}
