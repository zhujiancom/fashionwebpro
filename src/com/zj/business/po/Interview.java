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
@Table(name="BUS_INTERVIEW" , catalog = GlobalParam.CATALOG_DB)
@SequenceGenerator(name="commSEQ" , catalog=GlobalParam.CATALOG_DB ,allocationSize=1,initialValue=1)
public class Interview extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2141696349590324160L;

	private long interviewid;
	@UpdateColumn
	private String interviewEname;
	@UpdateColumn
	private String interviewCname;
	private String interviewurl;
	@UpdateColumn
	private String interviewer;
	@UpdateColumn
	private Date interviewdate;
	@UpdateColumn
	private String interviewtype;
	@UpdateColumn
	private String interviewCintro;
	@UpdateColumn
	private String interviewEintro;
	private String poster;
	@UpdateColumn
	private Designer designer;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator = "commSEQ")
	@Column(name="INTERVIEW_ID", nullable=false)
	@JsonData(type=GlobalParam.JSONTYPE_ID)
	public long getInterviewid() {
		return interviewid;
	}
	public void setInterviewid(long interviewid) {
		this.interviewid = interviewid;
	}
	
	@Column(name="INTERVIEWENAME",length=100)
	@JsonData
	public String getInterviewEname() {
		return interviewEname;
	}
	public void setInterviewEname(String interviewEname) {
		this.interviewEname = interviewEname;
	}
	
	@Column(name="INTERVIEWCNAME",length=100)
	@JsonData
	public String getInterviewCname() {
		return interviewCname;
	}
	public void setInterviewCname(String interviewCname) {
		this.interviewCname = interviewCname;
	}
	
	@Column(name="INTERVIEWURL",length=200)
	public String getInterviewurl() {
		return interviewurl;
	}
	public void setInterviewurl(String interviewurl) {
		this.interviewurl = interviewurl;
	}
	
	@Column(name="INTERVIEWER",length=100)
	@JsonData
	public String getInterviewer() {
		return interviewer;
	}
	public void setInterviewer(String interviewer) {
		this.interviewer = interviewer;
	}
	
	@Column(name="INTERVIEWDATE")
	@Temporal(TemporalType.DATE)
	@JsonData
	public Date getInterviewdate() {
		return interviewdate;
	}
	public void setInterviewdate(Date interviewdate) {
		this.interviewdate = interviewdate;
	}
	
	@Column(name="INTERVIEWTYPE",length=100)
	@JsonData
	public String getInterviewtype() {
		return interviewtype;
	}
	public void setInterviewtype(String interviewtype) {
		this.interviewtype = interviewtype;
	}
	
	@Column(name="INTERVIEWCINTRO",length=1000)
	public String getInterviewCintro() {
		return interviewCintro;
	}
	public void setInterviewCintro(String interviewCintro) {
		this.interviewCintro = interviewCintro;
	}
	
	@Column(name="INTERVIEWEINTRO",length=1000)
	public String getInterviewEintro() {
		return interviewEintro;
	}
	public void setInterviewEintro(String interviewEintro) {
		this.interviewEintro = interviewEintro;
	}
	@Column(name="POSTER",length=200)
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "DESIGNER_ID",referencedColumnName="DESIGNER_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public Designer getDesigner() {
		return designer;
	}
	public void setDesigner(Designer designer) {
		this.designer = designer;
	}
}
