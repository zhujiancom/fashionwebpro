package com.zj.core.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.zj.common.annotation.UpdateColumn;


@MappedSuperclass
public class AbstractEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5988378095087262241L;
	private String creater;
	@UpdateColumn
	private String modifier;
	private Date createTime;
	@UpdateColumn
	private Date modifiedTime;

	@Column(name = "CREATER", length = 50,updatable=false)
	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	@Column(name = "MODIFIER", length = 50)
	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	@Column(name = "CREATE_TIME",updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "MODIFY_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

}
