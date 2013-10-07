package com.zj.core.po;

import java.io.Serializable;

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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.zj.bigdefine.GlobalParam;

/**
 * 
 * @author zj
 *	
 * 项目名称：baseProject
 *
 * 类名称：SysMenu
 *
 * 包名称：com.zj.common.po
 *
 * Operate Time: 2013-4-27 下午10:57:11
 *
 * remark (备注):
 *
 * 文件名称：SysMenu.java
 *
 */
@Entity
@Table(name="SYS_MENU" ,catalog=GlobalParam.CATALOG_DB)
@SequenceGenerator(name = "commSEQ", allocationSize = 1, initialValue = 1)
public class SysMenu extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4936907187490797927L;
	private long menuId;
	private long parentId;
	private String menuCd;
	private String menuCname;
	private String menuEname;
	private String menuUrl;
	private Integer isEnable = 1;
	private Integer sortNum;
	private Integer menuLevel;
	private SysRole role;
	
	public SysMenu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SysMenu(String menuCd) {
		super();
		this.menuCd = menuCd;
	}
	public SysMenu(String menuCd, String menuEname) {
		super();
		this.menuCd = menuCd;
		this.menuEname = menuEname;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "commSEQ")
	@Column(name = "MENU_ID", nullable = false)
	public long getMenuId() {
		return menuId;
	}
	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}
	
	@Column(name="PARENT_MENU_ID")
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	@Column(name="MENU_CD")
	public String getMenuCd() {
		return menuCd;
	}
	public void setMenuCd(String menuCd) {
		this.menuCd = menuCd;
	}
	@Column(name="MENU_CNAME")
	public String getMenuCname() {
		return menuCname;
	}
	public void setMenuCname(String menuCname) {
		this.menuCname = menuCname;
	}
	@Column(name="MENU_ENAME")
	public String getMenuEname() {
		return menuEname;
	}
	public void setMenuEname(String menuEname) {
		this.menuEname = menuEname;
	}
	@Column(name="MENU_URL")
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	@Column(name="ENABLE",precision=1,columnDefinition="int(1) default 1")
	public Integer getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}
	@Column(name="SORT_NUM")
	public Integer getSortNum() {
		return sortNum;
	}
	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	@Column(name="MENU_LEVEL")
	public Integer getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
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
		return "SysMenu [menuId=" + menuId + ", parentId=" + parentId
				+ ", menuCd=" + menuCd + ", menuEname=" + menuEname
				+ ", menuUrl=" + menuUrl + ", isEnable=" + isEnable
				+ ", sortNum=" + sortNum + ", menuLevel=" + menuLevel 
				+ ", role=" + role.getRoleEname()
				+ "]";
	}
	
	
	
}
