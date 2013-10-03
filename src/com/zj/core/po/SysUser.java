package com.zj.core.po;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.zj.bigdefine.GlobalParam;
import com.zj.bigdefine.ReferenceKey;
import com.zj.common.annotation.CategoryGroup;
import com.zj.common.annotation.JsonData;
import com.zj.common.annotation.UpdateColumn;
import com.zj.common.utils.DateUtil;

/**
 * 
 * @author zj
 *	
 * 项目名称：baseProject
 *
 * 类名称：SysUser
 *
 * 包名称：com.zj.core.po
 *
 * Operate Time: 2013-6-6 下午11:32:00
 *
 * remark (备注):
 *
 * 文件名称：SysUser.java
 *
 */
@Entity
@Table(name="SYS_USER" , catalog = GlobalParam.CATALOG_DB)
@SequenceGenerator(name="commSEQ" , catalog=GlobalParam.CATALOG_DB ,allocationSize=1,initialValue=1)
public class SysUser extends AbstractEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5218540941483905119L;
	private long userId;
	@UpdateColumn
	private String cname;
	@UpdateColumn
	private String ename;
	@UpdateColumn
	private String nickname;
	@UpdateColumn
	private Date birthday;
	@UpdateColumn
	private String nationality;
	@UpdateColumn
	private String nativeplace;
	@UpdateColumn
	private String ipaddr = "127.0.0.1"; // 默认值为本机还回地址 127.0.0.1
	@UpdateColumn
	private String loginId;
	@UpdateColumn
	private String pswd = "888888";
	@UpdateColumn
	private Integer status = 0;
	@UpdateColumn
	private String email;
	@CategoryGroup(referneceKey=ReferenceKey.SEX)
	@UpdateColumn
	private String sex;
	@UpdateColumn
	private String moblie;
	@UpdateColumn
	private String qqnum;
	@UpdateColumn
	private int isEnable = 1;
	private Date registeTime = DateUtil.getDateFromMills(System
			.currentTimeMillis());
	@UpdateColumn
	private String selfsign;
	@UpdateColumn
	private String province;
	@UpdateColumn
	private String city;
	private Date cancelTime;
	@UpdateColumn(filterColumn=true)
	private String imgUrl;
	@UpdateColumn
	private Integer loginCount = 0;
	@UpdateColumn
	private Date loginTime;
	@UpdateColumn
	private Set<SysRole> sysRoles;
	
	public SysUser() {
		super();
	}

	public SysUser(String loginId) {
		super();
		this.loginId = loginId;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator = "commSEQ")
	@Column(name="USER_ID", nullable=false)
	@JsonData(type=GlobalParam.JSONTYPE_ID)
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Column(name="C_NAME",length=300)
	@JsonData
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name="E_NAME",length=300)
	@JsonData
	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	@Column(name="NICK_NAME",length=300)
	@JsonData
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name="BIRTHDAY")
	@Temporal(TemporalType.DATE)
	@JsonData
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name="NATIONALITY",length=100)
	@JsonData
	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	@Column(name="NATIVE_PLACE",length=300)
	@JsonData
	public String getNativeplace() {
		return nativeplace;
	}

	public void setNativeplace(String nativeplace) {
		this.nativeplace = nativeplace;
	}

	@Column(name="IP",length=200)
	public String getIpaddr() {
		return ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

	@Column(name="LOGIN_ID",nullable=false,length=300)
	@JsonData
	public String getLoginId() {
		return loginId;
	}
	
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	@Column(name="PSWD",length=300)
	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	@Column(name="STATUS")
	@JsonData
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name="EMAIL",length=100)
	@JsonData
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="SEX",length=10)
	@JsonData
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name="MOBILE_NUM",length=20)
	@JsonData
	public String getMoblie() {
		return moblie;
	}

	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}
	@Column(name="QQ_NUM",length=20)
	@JsonData
	public String getQqnum() {
		return qqnum;
	}

	public void setQqnum(String qqnum) {
		this.qqnum = qqnum;
	}

	
	@Column(name = "ENABLE", precision = 1, columnDefinition = "int(1) DEFAULT 1")
	@JsonData
	public int getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(int isEnable) {
		this.isEnable = isEnable;
	}

	@Column(name="REGISTE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonData
	public Date getRegisteTime() {
		return registeTime;
	}

	public void setRegisteTime(Date registeTime) {
		this.registeTime = registeTime;
	}

	@Column(name="SELF_SIGN",length=500)
	@JsonData
	public String getSelfsign() {
		return selfsign;
	}

	public void setSelfsign(String selfsign) {
		this.selfsign = selfsign;
	}

	@Column(name="PROVINCE",length=10)
	@JsonData
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name="CITY",length=100)
	@JsonData
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	@Column(name="IMG_URL",length=300)
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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
	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
	@ManyToMany(cascade={ CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.EAGER)
	@JoinTable(name="SYS_USER_ROLE_REL",joinColumns={@JoinColumn(name="USER_ID")},inverseJoinColumns={@JoinColumn(name="ROLE_ID")})
	@JsonData(type=GlobalParam.JSONTYPE_COLLECTION)
	public Set<SysRole> getSysRoles() {
		return sysRoles;
	}

	public void setSysRoles(Set<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}

	@Override
	public String toString() {
		return "SysUser[user_id="+this.userId+", loginId="+this.loginId+"," +
				" status="+this.status+", enable="+this.isEnable+", ename="+this.ename+", sex="+this.sex+" ]";
	}

}
