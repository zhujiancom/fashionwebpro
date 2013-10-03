package com.zj.core.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.zj.bigdefine.GlobalParam;
/**
 * 
 * @author zj
 *	
 * 项目名称：baseProject
 *
 * 类名称：SysModuleOperation
 *
 * 包名称：com.zj.common.po
 *
 * Operate Time: 2013-4-27 下午11:21:38
 *
 * remark (备注):
 *
 * 文件名称：SysModuleOperation.java
 *
 */
@Entity
@Table(name="SYS_MODULE_OPERATION" , catalog=GlobalParam.CATALOG_DB)
@SequenceGenerator(name = "commSEQ", allocationSize = 1, initialValue = 1)
public class SysModuleOperation extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7701527679239727663L;
	private long operateId;
	private String operateCd; 
	private int isEnable =1;
	private String operateEname;
	private String operateCname;
	
	public SysModuleOperation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SysModuleOperation(String operateEname) {
		super();
		this.operateEname = operateEname;
	}

	public SysModuleOperation(String operateCd, String operateEname) {
		super();
		this.operateCd = operateCd;
		this.operateEname = operateEname;
	}
	@Id
	@GeneratedValue(generator = "commSEQ", strategy = GenerationType.IDENTITY)
	@Column(name = "OPERATE_ID", nullable = false)
	public long getOperateId() {
		return operateId;
	}
	public void setOperateId(long operateId) {
		this.operateId = operateId;
	}
	@Column(name="OPERATE_CD")
	public String getOperateCd() {
		return operateCd;
	}
	public void setOperateCd(String operateCd) {
		this.operateCd = operateCd;
	}
	@Column(name="ENABLE")
	public int getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(int isEnable) {
		this.isEnable = isEnable;
	}
	@Column(name="OPERATE_ENAME")
	public String getOperateEname() {
		return operateEname;
	}
	public void setOperateEname(String operateEname) {
		this.operateEname = operateEname;
	}
	@Column(name="OPERATE_CNAME")
	public String getOperateCname() {
		return operateCname;
	}
	public void setOperateCname(String operateCname) {
		this.operateCname = operateCname;
	}
	@Override
	public String toString() {
		return "SysModuleOperation [operateId=" + operateId + ", operateCd="
				+ operateCd +", operateEname=" +this.operateEname + ", isEnable=" + isEnable + "]";
	}
	
}
