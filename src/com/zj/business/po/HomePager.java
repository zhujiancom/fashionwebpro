package com.zj.business.po;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.zj.bigdefine.GlobalParam;

@Entity
@Table(name="BUS_HOMEPAGER" , catalog = GlobalParam.CATALOG_DB)
@SequenceGenerator(name="commSEQ" , catalog=GlobalParam.CATALOG_DB ,allocationSize=1,initialValue=1)
public class HomePager implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1141542814176827229L;
	private Long homeId;
	private String imageDir;
	private Set<Designer> designers;
	private Long featuredDesigner;  //首页特别介绍的designer
	private Set<News> news;
	private String videoUrl;
	private String poster;
	private Set<Lookbook> lookbooks;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator = "commSEQ")
	@Column(name="HOME_ID", nullable=false)
	public Long getHomeId() {
		return homeId;
	}
	public void setHomeId(Long homeId) {
		this.homeId = homeId;
	}
	@Column(name="IMAGEDIR",length=2000)
	public String getImageDir() {
		return imageDir;
	}
	public void setImageDir(String imageDir) {
		this.imageDir = imageDir;
	}
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="HOME_ID")
	public Set<Designer> getDesigners() {
		return designers;
	}
	public void setDesigners(Set<Designer> designers) {
		this.designers = designers;
	}
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="HOME_ID")
	public Set<News> getNews() {
		return news;
	}
	public void setNews(Set<News> news) {
		this.news = news;
	}
	@Column(name="VIDEOURL",length=2000)
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	@Column(name="POSTERURL",length=2000)
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="HOME_ID")
	public Set<Lookbook> getLookbooks() {
		return lookbooks;
	}
	public void setLookbooks(Set<Lookbook> lookbooks) {
		this.lookbooks = lookbooks;
	}
	@Column(name="FEATURED_DESIGNER_ID")
	public Long getFeaturedDesigner() {
		return featuredDesigner;
	}
	public void setFeaturedDesigner(Long featuredDesigner) {
		this.featuredDesigner = featuredDesigner;
	}
	
}
