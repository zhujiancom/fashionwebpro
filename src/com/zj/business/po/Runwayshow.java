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
@Table(name="BUS_RUNWAYSHOW" , catalog = GlobalParam.CATALOG_DB)
@SequenceGenerator(name="commSEQ" , catalog=GlobalParam.CATALOG_DB ,allocationSize=1,initialValue=1)
public class Runwayshow extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3383270083383967035L;

	private long runwayshowid;
	@UpdateColumn
	private String runwayshowCname;
	@UpdateColumn
	private String runwayshowEname;
	@UpdateColumn(filterColumn=true)
	private String runwayshowUrl;
	@UpdateColumn
	private Date runwayshowdate;
	@UpdateColumn
	private String runwayshowCintro;
	@UpdateColumn
	private String runwayshowEintro;
	@UpdateColumn(filterColumn=true)
	private String poster;
	@UpdateColumn
	private Brand brand;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator = "commSEQ")
	@Column(name="RUNWAYSHOW_ID", nullable=false)
	@JsonData(type=GlobalParam.JSONTYPE_ID)
	public long getRunwayshowid() {
		return runwayshowid;
	}
	public void setRunwayshowid(long runwayshowid) {
		this.runwayshowid = runwayshowid;
	}
	
	@Column(name="RUNWAYSHOWCNAME",length=50)
	@JsonData
	public String getRunwayshowCname() {
		return runwayshowCname;
	}
	public void setRunwayshowCname(String runwayshowCname) {
		this.runwayshowCname = runwayshowCname;
	}
	
	@Column(name="RUNWAYSHOWENAME",length=50)
	@JsonData
	public String getRunwayshowEname() {
		return runwayshowEname;
	}
	public void setRunwayshowEname(String runwayshowEname) {
		this.runwayshowEname = runwayshowEname;
	}
	
	@Column(name="RUNWAYSHOWURL",length=200)
	public String getRunwayshowUrl() {
		return runwayshowUrl;
	}
	public void setRunwayshowUrl(String runwayshowUrl) {
		this.runwayshowUrl = runwayshowUrl;
	}
	
	@Column(name="LOOKBOOKDATE")
	@Temporal(TemporalType.DATE)
	@JsonData
	public Date getRunwayshowdate() {
		return runwayshowdate;
	}
	public void setRunwayshowdate(Date runwayshowdate) {
		this.runwayshowdate = runwayshowdate;
	}
	
	@Column(name="RUNWAYSHOWCINTRO",length=1000)
	public String getRunwayshowCintro() {
		return runwayshowCintro;
	}
	public void setRunwayshowCintro(String runwayshowCintro) {
		this.runwayshowCintro = runwayshowCintro;
	}
	
	@Column(name="RUNWAYSHOWEINTRO",length=1000)
	public String getRunwayshowEintro() {
		return runwayshowEintro;
	}
	public void setRunwayshowEintro(String runwayshowEintro) {
		this.runwayshowEintro = runwayshowEintro;
	}
	@Column(name="POSTER",length=200)
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
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
