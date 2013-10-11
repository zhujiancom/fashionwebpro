package com.zj.business.vo;

import java.util.List;

import com.zj.business.observer.LanguageType;
import com.zj.business.po.Designer;
import com.zj.business.po.Lookbook;

public class HomepagerVO extends AbstractVO {
	//below three attributes are used in backend
	private Designer featuredDesigner; 
	private List<Designer> designers;
	private List<Lookbook> lookbooks;
	
	
	private List<String> imageThumbnailUrls;
	private String posterThumbnailUrl;
	private String videoName;
	
	private DesignerVO featuredDesignerVO;
	private List<DesignerVO> designerVOs;
	private List<LookbookVO> lookbookVOs;
	private String videoUrl;
	private List<String> imageUrls;
	
	public HomepagerVO(){}
	
	public HomepagerVO(DesignerVO featuredDesignerVO,List<DesignerVO> designerVOs,List<LookbookVO> lookbookVOs,String videoUrl,List<String> imageUrls){
		this.featuredDesignerVO = featuredDesignerVO;
		this.designerVOs = designerVOs;
		this.lookbookVOs = lookbookVOs;
		this.videoUrl = videoUrl;
		this.imageUrls = imageUrls;
	}
	
	public List<String> getImageThumbnailUrls() {
		return imageThumbnailUrls;
	}

	public void setImageThumbnailUrls(List<String> imageThumbnailUrls) {
		this.imageThumbnailUrls = imageThumbnailUrls;
	}

	public String getPosterThumbnailUrl() {
		return posterThumbnailUrl;
	}

	public void setPosterThumbnailUrl(String posterThumbnailUrl) {
		this.posterThumbnailUrl = posterThumbnailUrl;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public DesignerVO getFeaturedDesignerVO() {
		return featuredDesignerVO;
	}

	public void setFeaturedDesignerVO(DesignerVO featuredDesignerVO) {
		this.featuredDesignerVO = featuredDesignerVO;
	}

	public List<DesignerVO> getDesignerVOs() {
		return designerVOs;
	}

	public void setDesignerVOs(List<DesignerVO> designerVOs) {
		this.designerVOs = designerVOs;
	}

	public List<LookbookVO> getLookbookVOs() {
		return lookbookVOs;
	}

	public void setLookbookVOs(List<LookbookVO> lookbookVOs) {
		this.lookbookVOs = lookbookVOs;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public List<String> getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}

	@Override
	protected void setEnglishValue() {
		throw new UnsupportedOperationException("not support setChineseValue in homepagervo");
	}

	@Override
	protected void setChineseValue(LanguageType language) {
		throw new UnsupportedOperationException("not support setChineseValue in homepagervo");
	}

	public Designer getFeaturedDesigner() {
		return featuredDesigner;
	}

	public void setFeaturedDesigner(Designer featuredDesigner) {
		this.featuredDesigner = featuredDesigner;
	}

	public List<Designer> getDesigners() {
		return designers;
	}

	public void setDesigners(List<Designer> designers) {
		this.designers = designers;
	}

	public List<Lookbook> getLookbooks() {
		return lookbooks;
	}

	public void setLookbooks(List<Lookbook> lookbooks) {
		this.lookbooks = lookbooks;
	}

}
