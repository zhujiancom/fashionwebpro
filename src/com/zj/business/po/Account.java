package com.zj.business.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.zj.bigdefine.GlobalParam;
import com.zj.bigdefine.ReferenceKey;
import com.zj.common.annotation.CategoryGroup;
import com.zj.common.annotation.JsonData;
import com.zj.core.po.AbstractEntity;

/**
 * 
 * @author zj
 *	
 * 项目名称：FashionWebSite
 *
 * 类名称：Account
 *
 * 包名称：com.zj.business.po
 *
 * Operate Time: 2013-7-13 下午02:54:13
 *
 * remark (备注):
 *
 * 文件名称：Account.java
 *
 */
@Entity
@Table(name="BUS_ACCOUNT" , catalog = GlobalParam.CATALOG_DB)
@SequenceGenerator(name="commSEQ" , catalog=GlobalParam.CATALOG_DB ,allocationSize=1,initialValue=1)
public class Account extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1189585142831433347L;
	private long accountId;
	private String ename;
	private String accountname;
	private String pswd = "888888";
	private String email;
	@CategoryGroup(referneceKey=ReferenceKey.SEX)
	private String gender;
	private String moblie;
	private int isEnable = 1;
	private String selfsign;
	private String nationality;
	@CategoryGroup(referneceKey=ReferenceKey.CAREER)
	private String career;
	private String knowapproach;
	private Boolean ispaid;
	private Date cancelTime;
	private String headerImgUrl;
	private Integer loginCount = 0;
	private Date lastLoginTime;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator = "commSEQ")
	@Column(name="ACCOUNT_ID", nullable=false)
	@JsonData(type=GlobalParam.JSONTYPE_ID)
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	@Column(name="E_NAME",length=300)
	@JsonData
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}

	@Column(name="ACCOUNT_NAME",length=100)
	@JsonData
	public String getAccountname() {
		return accountname;
	}
	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	
	@Column(name="PSWD",length=100)
	public String getPswd() {
		return pswd;
	}
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	
	@Column(name="EMAIL",length=100)
	@JsonData
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="GENDER",length=10)
	@JsonData
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Column(name="MOBILE_NUM",length=20)
	@JsonData
	public String getMoblie() {
		return moblie;
	}
	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}

	@Column(name = "ENABLE", precision = 1, columnDefinition = "int(1) DEFAULT 1")
	@JsonData
	public int getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(int isEnable) {
		this.isEnable = isEnable;
	}
	
	@Column(name="SELF_SIGN",length=500)
	@JsonData
	public String getSelfsign() {
		return selfsign;
	}
	public void setSelfsign(String selfsign) {
		this.selfsign = selfsign;
	}
	
	@Column(name="NATIONALITY",length=100)
	@JsonData
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Column(name="CANCEL_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonData
	public Date getCancelTime() {
		return cancelTime;
	}
	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}
	@Column(name="HEADER_IMG_URL",length=300)
	public String getHeaderImgUrl() {
		return headerImgUrl;
	}
	public void setHeaderImgUrl(String headerImgUrl) {
		this.headerImgUrl = headerImgUrl;
	}
	@Column(name="LOGIN_COUNT")
	@JsonData
	public Integer getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
	@Column(name="LONGIN_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonData
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	@Column(name="CAREER",length=100)
	@JsonData
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	@Column(name="KNOWAPPROACH",length=100)
	@JsonData
	public String getKnowapproach() {
		return knowapproach;
	}
	public void setKnowapproach(String knowapproach) {
		this.knowapproach = knowapproach;
	}
	
	@Column(name="ISPAID",length=10)
	@JsonData
	public Boolean getIspaid() {
		return ispaid;
	}
	public void setIspaid(Boolean ispaid) {
		this.ispaid = ispaid;
	}
	
	@Override
	public String toString() {
		return "Account [ename=" + ename + ", accountname=" + accountname
				+ ", email=" + email + ", isEnable=" + isEnable + "]";
	}
}
