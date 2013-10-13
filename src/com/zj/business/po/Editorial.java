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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.xwork.builder.HashCodeBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.zj.bigdefine.GlobalParam;
import com.zj.common.annotation.JsonData;
import com.zj.common.annotation.UpdateColumn;
import com.zj.core.po.AbstractEntity;
@Entity
@Table(name="BUS_EDITORIAL" , catalog = GlobalParam.CATALOG_DB)
@SequenceGenerator(name="commSEQ" , catalog=GlobalParam.CATALOG_DB ,allocationSize=1,initialValue=1)
public class Editorial extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -456427999128756362L;

	private long editorialid;
	@UpdateColumn
	private String editorialCname;
	@UpdateColumn
	private String editorialEname;
	private String imgs;
	@UpdateColumn
	private Date editdate;
	@UpdateColumn
	private Brand brand;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator = "commSEQ")
	@Column(name="EDITORIAL_ID", nullable=false)
	@JsonData(type=GlobalParam.JSONTYPE_ID)
	public long getEditorialid() {
		return editorialid;
	}
	public void setEditorialid(long editorialid) {
		this.editorialid = editorialid;
	}
	
	@Column(name="EDITCNAME",length=50)
	@JsonData
	public String getEditorialCname() {
		return editorialCname;
	}
	public void setEditorialCname(String editorialCname) {
		this.editorialCname = editorialCname;
	}
	
	@Column(name="EDITENAME",length=50)
	@JsonData
	public String getEditorialEname() {
		return editorialEname;
	}
	public void setEditorialEname(String editorialEname) {
		this.editorialEname = editorialEname;
	}
	
	@Column(name="IMGS",length=2000)
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	
	@Column(name="EDITORIALDATE")
	@Temporal(TemporalType.DATE)
	@JsonData
	public Date getEditdate() {
		return editdate;
	}
	public void setEditdate(Date editdate) {
		this.editdate = editdate;
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
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(editorialid).toHashCode();
	}
	@Override
	public boolean equals(Object obj) {
		boolean isEqual = false;
		if(obj != null){
			Editorial another = (Editorial) obj;
			isEqual = new EqualsBuilder().append(editorialid, another.getEditorialid())
										 .isEquals();
		}
		return isEqual;
	}
	@Override
	public String toString() {
		return "Editorial [editorialid=" + editorialid + ", editorialEname="
				+ editorialEname + ", editorialCname="+editorialCname+", imgDirectory=<" + imgs + ">]";
	}
}
