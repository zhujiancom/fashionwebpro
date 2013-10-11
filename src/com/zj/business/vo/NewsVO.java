package com.zj.business.vo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.zj.business.observer.LanguageType;
import com.zj.business.po.News;

public class NewsVO extends AbstractVO{

	private News news;
	private String headImg;
	private final String basepath;
	private List<String> images;
	private String title;
	private String content;
	
	public NewsVO(News news, String basepath) {
		super();
		this.news = news;
		this.basepath = basepath;
		init();
	}
	
	public void init(){
		String absoluteDirPath = basepath + news.getImageDir();
		images = new ArrayList<String>();
		File dir = new File(absoluteDirPath);
		if(dir.exists()){
			File file[] = dir.listFiles();
			if(file.length > 0){
				String tempUrl = file[0].getAbsolutePath();
				headImg = tempUrl.substring(basepath.length()).replace('\\', '/');
				for(int i=0;i<file.length;i++){
					String imagesPath = file[i].getAbsolutePath().substring(basepath.length()).replace('\\', '/');
					images.add(imagesPath);
				}
			}
		}
	}
	
	public void setEnglishValue(){
		setTitle(news.getTitleEn());
		setContent(news.getContentEn());
	}
	
	public void setChineseValue(){
		setTitle(news.getTitleZh());
		setContent(news.getContentZh());
	}
	
	public void setTWChineseValue(){
		setTitle(news.getTitleZh());
		setContent(news.getContentZh());
	}
	
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
	public String getHeadImg() {
		return headImg;
	}
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	protected void setChineseValue(LanguageType language) {
		// TODO Auto-generated method stub
		
	}
}
