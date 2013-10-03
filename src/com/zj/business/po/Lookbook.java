package com.zj.business.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.zj.bigdefine.GlobalParam;
import com.zj.common.annotation.JsonData;
import com.zj.common.annotation.UpdateColumn;
import com.zj.core.po.AbstractEntity;

@Entity
@Table(name="BUS_LOOKBOOK" , catalog = GlobalParam.CATALOG_DB)
@SequenceGenerator(name="commSEQ" , catalog=GlobalParam.CATALOG_DB ,allocationSize=1,initialValue=1)
public class Lookbook extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1689519668748855962L;

	private long lookbookid;
	@UpdateColumn
	private String lookbookCname;
	@UpdateColumn
	private String lookbookEname;
	@UpdateColumn
	private String imgs;
	@UpdateColumn
	private Date lookbookdate;
	@UpdateColumn
	private Brand brand;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator = "commSEQ")
	@Column(name="LOOKBOOK_ID", nullable=false)
	@JsonData(type=GlobalParam.JSONTYPE_ID)
	public long getLookbookid() {
		return lookbookid;
	}
	public void setLookbookid(long lookbookid) {
		this.lookbookid = lookbookid;
	}
	
	@Column(name="LOOKBOOKCNAME",length=50)
	@JsonData
	public String getLookbookCname() {
		return lookbookCname;
	}
	public void setLookbookCname(String lookbookCname) {
		this.lookbookCname = lookbookCname;
	}
	
	@Column(name="LOOKBOOKENAME",length=50)
	@JsonData
	public String getLookbookEname() {
		return lookbookEname;
	}
	public void setLookbookEname(String lookbookEname) {
		this.lookbookEname = lookbookEname;
	}
	
	@Column(name="IMGS",length=2000)
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	
	@Column(name="LOOKBOOKDATE")
	@Temporal(TemporalType.DATE)
	@JsonData
	public Date getLookbookdate() {
		return lookbookdate;
	}
	public void setLookbookdate(Date lookbookdate) {
		this.lookbookdate = lookbookdate;
	}
	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "BRAND_ID",referencedColumnName="BRAND_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
}
