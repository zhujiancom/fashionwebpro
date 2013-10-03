package com.zj.core.po;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.zj.bigdefine.GlobalParam;
import com.zj.common.annotation.JsonData;
/**
 * 
 * @author zj
 *	
 * 项目名称：baseProject
 *
 * 类名称：SysModule
 *
 * 包名称：com.zj.common.po
 *
 * Operate Time: 2013-4-27 下午11:18:19
 *
 * remark (备注):
 *
 * 文件名称：SysModule.java
 *
 */
@Entity
@Table(name="SYS_MODULE",catalog=GlobalParam.CATALOG_DB)
@SequenceGenerator(name = "commSEQ",allocationSize = 1, initialValue = 1)
public class SysModule extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -205802040514627165L;
	private long moduleId;
	private long parentId;
	private String moduleCd;
	private String moduleUrl;
	private String moduleEname;
	private String moduleCname;
	private int moduleLevel;
	private int sortNum;
	private int isEnable = 1;
	private Set<SysModuleOperation> operations;
	private SysRole role;   // belong a role 
	
	public SysModule() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SysModule(String moduleEname) {
		super();
		this.moduleEname = moduleEname;
	}
	public SysModule(String moduleCd, String moduleEname) {
		super();
		this.moduleCd = moduleCd;
		this.moduleEname = moduleEname;
	}
	@Id
	@GeneratedValue(generator = "commSEQ", strategy = GenerationType.IDENTITY)
	@Column(name = "MODULE_ID", nullable = false)
	@JsonData(type=GlobalParam.JSONTYPE_ID)
	public long getModuleId() {
		return moduleId;
	}
	public void setModuleId(long moduleId) {
		this.moduleId = moduleId;
	}
	@Column(name="PARENT_MODULE_ID")
	@JsonData
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	@Column(name="MODULE_CD",nullable=false)
	@JsonData
	public String getModuleCd() {
		return moduleCd;
	}
	public void setModuleCd(String moduleCd) {
		this.moduleCd = moduleCd;
	}
	@Column(name="MODULE_URL")
	@JsonData
	public String getModuleUrl() {
		return moduleUrl;
	}
	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}
	@Column(name="MODULE_ENAME")
	@JsonData
	public String getModuleEname() {
		return moduleEname;
	}
	public void setModuleEname(String moduleEname) {
		this.moduleEname = moduleEname;
	}
	@Column(name="MODULE_CNAME")
	@JsonData
	public String getModuleCname() {
		return moduleCname;
	}
	public void setModuleCname(String moduleCname) {
		this.moduleCname = moduleCname;
	}
	@Column(name="MODULE_LEVEL")
	public int getModuleLevel() {
		return moduleLevel;
	}
	public void setModuleLevel(int moduleLevel) {
		this.moduleLevel = moduleLevel;
	}
	@Column(name="SORT_NUM")
	public int getSortNum() {
		return sortNum;
	}
	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}
	@Column(name="ENABLE" , precision=1,columnDefinition="int(1) DEFAULT 1")
	@JsonData
	public int getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(int isEnable) {
		this.isEnable = isEnable;
	}
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="MODULE_ID")
	public Set<SysModuleOperation> getOperations() {
		return operations;
	}
	public void setOperations(Set<SysModuleOperation> operations) {
		this.operations = operations;
	}
	
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID",referencedColumnName="ROLE_ID")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public SysRole getRole() {
		return role;
	}
	public void setRole(SysRole role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "SysModule [moduleId=" + moduleId + ", parentId=" + parentId
				+ ", moduleCd=" + moduleCd + ", moduleUrl=" + moduleUrl
				+ ", moduleEname=" + moduleEname + ", moduleLevel="
				+ moduleLevel + ", sortNum=" + sortNum + ", isEnable="
				+ isEnable + ", operations=" + ""
				+ ", role=" + role.getRoleId()
				+ "]";
	}
	
}
