package com.zj.business.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.zj.bigdefine.GlobalParam;
import com.zj.common.annotation.JsonData;
import com.zj.common.annotation.UpdateColumn;
import com.zj.core.po.AbstractEntity;
@Entity
@Table(name="BUS_CONTACT" , catalog = GlobalParam.CATALOG_DB)
@SequenceGenerator(name="commSEQ" , catalog=GlobalParam.CATALOG_DB ,allocationSize=1,initialValue=1)
public class ContactUs extends AbstractEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4632904242206674299L;
	private long contactId;
	@UpdateColumn
	private String contactName;
	@UpdateColumn
	private String email;
	@UpdateColumn
	private String Tel;
	@UpdateColumn
	private String address;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator = "commSEQ")
	@Column(name="CONTACT_ID", nullable=false)
	@JsonData(type=GlobalParam.JSONTYPE_ID)
	public long getContactId() {
		return contactId;
	}
	public void setContactId(long contactId) {
		this.contactId = contactId;
	}
	@JsonData
	@Column(name="CONTACT_NAME",length=100)
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	@JsonData
	@Column(name="CONTACT_EMAIL",length=100)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@JsonData
	@Column(name="CONTACT_TEL",length=20)
	public String getTel() {
		return Tel;
	}
	public void setTel(String tel) {
		Tel = tel;
	}
	@JsonData
	@Column(name="CONTACT_ADDR",length=1000)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "ContactUs [contactName=" + contactName + ", email=" + email
				+ ", Tel=" + Tel + ", address=" + address + "]";
	}
	
}
