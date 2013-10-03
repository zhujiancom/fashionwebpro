package com.zj.core.po;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
import com.zj.common.annotation.DisplayName;
import com.zj.common.annotation.JsonData;

@Entity
@Table(name = "SYS_CATEGORY", catalog = GlobalParam.CATALOG_DB)
@SequenceGenerator(name = "commSEQ", allocationSize = 1, initialValue = 1)
public class SysCategory extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3470075115601854625L;
	private long categoryId;
	private String ename;
	private String cname;
	private SysCategoryGroup parent;
	@DisplayName
	private String categoryCd;
	private Integer isEanble =1;

	public SysCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SysCategory(String categoryCd) {
		super();
		this.categoryCd = categoryCd;
	}

	@Id
	@GeneratedValue(generator = "commSEQ", strategy = GenerationType.IDENTITY)
	@Column(name = "CATEGORY_ID", nullable = false)
	@JsonData(type=GlobalParam.JSONTYPE_ID)
	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "ENAME")
	@JsonData
	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	@Column(name = "CNAME")
	@JsonData
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@ManyToOne(optional = false, fetch = FetchType.EAGER,cascade={CascadeType.MERGE})
	@JoinColumn(name = "CATEGORY_GROUP_ID",referencedColumnName="CATEGORY_GROUP_ID",updatable=false)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public SysCategoryGroup getParent() {
		return parent;
	}

	public void setParent(SysCategoryGroup parent) {
		this.parent = parent;
	}

	@Column(name = "CATEGORY_CD" , updatable=false)
	@JsonData
	public String getCategoryCd() {
		return categoryCd;
	}

	public void setCategoryCd(String categoryCd) {
		this.categoryCd = categoryCd;
	}

	@Column(name = "ENABLE", precision = 1, columnDefinition = "int(1) default 1")
	@JsonData
	public Integer getIsEanble() {
		return isEanble;
	}

	public void setIsEanble(Integer isEanble) {
		this.isEanble = isEanble;
	}

	@Override
	public String toString() {
		return "SysCategory [categoryId=" + categoryId + ", ename=" + ename
				+ ", parentCD=" + parent.getCategoryGroupCd() + ", categoryCd="
				+ categoryCd + ", isEanble=" + isEanble + "]";
	}
}
