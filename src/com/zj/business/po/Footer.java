package com.zj.business.po;

import java.sql.Blob;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.zj.bigdefine.GlobalParam;

@Entity
@Table(name="BUS_FOOTER" , catalog = GlobalParam.CATALOG_DB)
@SequenceGenerator(name="commSEQ" , catalog=GlobalParam.CATALOG_DB ,allocationSize=1,initialValue=1)
public class Footer {
	private long footerId;
	private String aboutus_EN;
    private String aboutus_CH;
    private Blob legalstmt_EN;
    private Blob legalstmt_CH;
    private String links;
    
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator = "commSEQ")
	@Column(name="FOOTER_ID", nullable=false)
	public long getFooterId() {
		return footerId;
	}
	public void setFooterId(long footerId) {
		this.footerId = footerId;
	}
	@Column(name="ABOUTUS_EN",length=2000)
	public String getAboutus_EN() {
		return aboutus_EN;
	}
	public void setAboutus_EN(String aboutus_EN) {
		this.aboutus_EN = aboutus_EN;
	}
	@Column(name="ABOUTUS_CH",length=2000)
	public String getAboutus_CH() {
		return aboutus_CH;
	}
	public void setAboutus_CH(String aboutus_CH) {
		this.aboutus_CH = aboutus_CH;
	}
	@Lob
	@Basic(fetch = FetchType.LAZY) 
	@Column(name="LEGAL_STMT_EN", columnDefinition="blob")
	public Blob getLegalstmt_EN() {
		return legalstmt_EN;
	}
	public void setLegalstmt_EN(Blob legalstmt_EN) {
		this.legalstmt_EN = legalstmt_EN;
	}
	@Lob
	@Basic(fetch = FetchType.LAZY) 
	@Column(name="LEGAL_STMT_CH", columnDefinition="blob")
	public Blob getLegalstmt_CH() {
		return legalstmt_CH;
	}
	public void setLegalstmt_CH(Blob legalstmt_CH) {
		this.legalstmt_CH = legalstmt_CH;
	}
	@Column(name="LINKS",length=500)
	public String getLinks() {
		return links;
	}
	public void setLinks(String links) {
		this.links = links;
	}
}
