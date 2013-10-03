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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang.xwork.builder.EqualsBuilder;
import org.apache.commons.lang.xwork.builder.HashCodeBuilder;

import com.zj.bigdefine.GlobalParam;
import com.zj.common.annotation.JsonData;

/**
 * 
 * @author zj
 * 
 *         项目名称：baseProject
 * 
 *         类名称：SysCategoryGroup
 * 
 *         包名称：com.zj.common.po
 * 
 *         Operate Time: 2013-4-27 下午09:19:21
 * 
 *         remark (备注):
 * 
 *         文件名称：SysCategoryGroup.java
 * 
 */
@Entity
@Table(name = "SYS_CATEGORY_GROUP", catalog = GlobalParam.CATALOG_DB)
@SequenceGenerator(name = "commSEQ", allocationSize = 1, initialValue = 1)
public class SysCategoryGroup extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9092951425818482309L;
	private long categoryGroupId;
	private String cname;
	private String ename;
	private Integer isEnable = 1;
	private String categoryGroupCd;
	private Set<SysCategory> subCategories;

	public SysCategoryGroup() {
	}

	public SysCategoryGroup(String categoryGroupCd) {
		super();
		this.categoryGroupCd = categoryGroupCd;
	}

	@Id
	@GeneratedValue(generator = "commSEQ", strategy = GenerationType.IDENTITY)
	@Column(name = "CATEGORY_GROUP_ID", nullable = false)
	@JsonData(type=GlobalParam.JSONTYPE_ID)
	public long getCategoryGroupId() {
		return categoryGroupId;
	}

	public void setCategoryGroupId(long categoryGroupId) {
		this.categoryGroupId = categoryGroupId;
	}

	@Column(name = "CNAME")
	@JsonData
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	@Column(name = "ENAME")
	@JsonData
	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	@Column(name = "ENABLE", precision = 1,columnDefinition="int(1) DEFAULT 1")
	@JsonData
	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	@Column(name = "CATEGORY_GROUP_CD")
	@JsonData
	public String getCategoryGroupCd() {
		return categoryGroupCd;
	}

	public void setCategoryGroupCd(String categoryGroupCd) {
		this.categoryGroupCd = categoryGroupCd;
	}

	@OneToMany(mappedBy="parent",cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@JsonData(type=GlobalParam.JSONTYPE_COLLECTION)
	public Set<SysCategory> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(Set<SysCategory> subCategories) {
		this.subCategories = subCategories;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(categoryGroupId)
									.append(cname)
									.append(ename)
									.append(categoryGroupCd).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean isEqual = false;
		if(obj != null){
			SysCategoryGroup another = (SysCategoryGroup) obj;
			isEqual = new EqualsBuilder().append(categoryGroupId, another.getCategoryGroupId())
										 .append(ename, another.getEname())
										 .append(cname, another.getCname())
										 .append(categoryGroupCd, another.getCategoryGroupCd())
										 .isEquals();
		}
		return isEqual;
	}

	@Override
	public String toString() {
		return "SysCategoryGroup [ groupId="+this.categoryGroupId+", groupCd="
				+this.categoryGroupCd+", cname="
				+this.cname+", ename="
				+this.ename+" ]"; 
	}
	
	

}
