package com.zj.business.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.zj.bigdefine.GlobalParam;
import com.zj.common.annotation.JsonData;
import com.zj.common.annotation.UpdateColumn;
import com.zj.core.po.AbstractEntity;
@Entity
@Table(name="BUS_NEWS" , catalog = GlobalParam.CATALOG_DB)
@SequenceGenerator(name="commSEQ" , catalog=GlobalParam.CATALOG_DB ,allocationSize=1,initialValue=1)
public class News extends AbstractEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4284565684171849991L;

	
	private long newsId;
	private String titleZh;
	private String titleEn;
	@UpdateColumn(filterColumn=true)
	private String imageDir;
	private Date publishDate;
	@UpdateColumn
	private String contentZh;
	@UpdateColumn
	private String contentEn;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator = "commSEQ")
	@Column(name="NEWS_ID", nullable=false)
	@JsonData(type=GlobalParam.JSONTYPE_ID)
	public long getNewsId() {
		return newsId;
	}
	public void setNewsId(long newsId) {
		this.newsId = newsId;
	}
	
	@Column(name="TITLE_ZH")
	@JsonData
	public String getTitleZh() {
		return titleZh;
	}
	public void setTitleZh(String titleZh) {
		this.titleZh = titleZh;
	}
	
	@Column(name="TITLE_EN")
	@JsonData
	public String getTitleEn() {
		return titleEn;
	}
	public void setTitleEn(String titleEn) {
		this.titleEn = titleEn;
	}
	@Column(name="IMAGE_DIR")
	public String getImageDir() {
		return imageDir;
	}
	public void setImageDir(String imageDir) {
		this.imageDir = imageDir;
	}
	@Column(name="PUBLISH_DATE")
	@Temporal(TemporalType.DATE)
	@JsonData
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	@Column(name="CONTENT_ZH", length=2000)
	public String getContentZh() {
		return contentZh;
	}
	public void setContentZh(String contentZh) {
		this.contentZh = contentZh;
	}
	@Column(name="CONTENT_EN", length=2000)
	public String getContentEn() {
		return contentEn;
	}
	public void setContentEn(String contentEn) {
		this.contentEn = contentEn;
	}
	@Override
	public String toString() {
		return "News [newsId=" + newsId + ", titleEn=" + titleEn
				+ ", imageDir=" + imageDir + ", publishDate=" + publishDate
				+ ", contentEn=" + contentEn + "]";
	}
}
