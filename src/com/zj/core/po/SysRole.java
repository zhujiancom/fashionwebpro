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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.zj.bigdefine.GlobalParam;
import com.zj.common.annotation.DisplayName;
import com.zj.common.annotation.JsonData;

/**
 * 
 * @author zj
 * 
 *         项目名称：baseProject
 * 
 *         类名称：SysRole
 * 
 *         包名称：com.zj.common.po
 * 
 *         Operate Time: 2013-4-21 下午08:06:23
 * 
 *         remark (备注):
 * 
 *         文件名称：SysRole.java
 * 
 */
@Entity
@Table(name = "SYS_ROLE", catalog = GlobalParam.CATALOG_DB)
@SequenceGenerator(name = "commSEQ", allocationSize = 1, initialValue = 1)
public class SysRole extends AbstractEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long roleId;
	private String roleCname;
	@DisplayName
	private String roleEname;
	private String roleCd;
	private int isEnable = 1;
	private Set<SysUser> sysUsers;
	private Set<SysModule> sysModules;
	private Set<SysMenu> sysMenus;
	
	public SysRole() {
	}

	public SysRole(String roleEname) {
		super();
		this.roleEname = roleEname;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "commSEQ")
	@Column(name = "ROLE_ID", nullable = false)
	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	@Column(name = "ROLE_CNAME", length = 50)
	@JsonData
	public String getRoleCname() {
		return roleCname;
	}

	public void setRoleCname(String roleCname) {
		this.roleCname = roleCname;
	}

	@Column(name = "ROLE_ENAME", nullable = false, length = 50)
	public String getRoleEname() {
		return roleEname;
	}

	public void setRoleEname(String roleEname) {
		this.roleEname = roleEname;
	}

	@Column(name = "ROLE_CD", nullable = false, length = 50)
	public String getRoleCd() {
		return roleCd;
	}

	public void setRoleCd(String roleCd) {
		this.roleCd = roleCd;
	}

	@Column(name = "ENABLE", columnDefinition = "int(1) default 1")
	public int getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(int isEnable) {
		this.isEnable = isEnable;
	}

	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.LAZY,mappedBy="sysRoles")
	public Set<SysUser> getSysUsers() {
		return sysUsers;
	}

	public void setSysUsers(Set<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}

	@OneToMany(mappedBy="role",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	public Set<SysModule> getSysModules() {
		return sysModules;
	}

	public void setSysModules(Set<SysModule> sysModules) {
		this.sysModules = sysModules;
	}

	@OneToMany(mappedBy="role",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	public Set<SysMenu> getSysMenus() {
		return sysMenus;
	}

	public void setSysMenus(Set<SysMenu> sysMenus) {
		this.sysMenus = sysMenus;
	}

	@Override
	public String toString() {
		return "SysRole [roleId=" + this.roleId +", roleCd="+ this.roleCd + ", roleEname=" + this.roleEname
				+ ", isenable=" + this.isEnable + " ]";
	}

}
