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

@Entity
@Table(name="SYS_SEQUENCE_TAB" , catalog = GlobalParam.CATALOG_DB)
@SequenceGenerator(name = "commSEQ", allocationSize = 1, initialValue = 1)
public class SequenceTab implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4439015634808616455L;
	private long seqId;
	private long seqNum;
	private String seqCd;
	
	public SequenceTab() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SequenceTab(long seqNum, String seqCd) {
		super();
		this.seqNum = seqNum;
		this.seqCd = seqCd;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "commSEQ")
	@Column(name = "SEQ_ID", nullable = false)
	public long getSeqId() {
		return seqId;
	}
	public void setSeqId(long seqId) {
		this.seqId = seqId;
	}
	@Column(name = "SEQ_NUM", length=20,columnDefinition="INTEGER default '1000'")
	public long getSeqNum() {
		return seqNum;
	}
	public void setSeqNum(long seqNum) {
		this.seqNum = seqNum;
	}
	@Column(name = "SEQ_CODE")
	public String getSeqCd() {
		return seqCd;
	}
	public void setSeqCd(String seqCd) {
		this.seqCd = seqCd;
	}
	
}
