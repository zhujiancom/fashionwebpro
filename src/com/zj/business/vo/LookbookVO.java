package com.zj.business.vo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.zj.business.po.Lookbook;

public class LookbookVO  extends AbstractVO{
	private Lookbook lookbook;
	private final String basePath;
	private List<String> images;
	private int length;
	private String title;
	
	public LookbookVO(Lookbook lookbook, String basePath){
		super();
		this.lookbook = lookbook;
		this.basePath = basePath;
		init();
	}
	
	public void init(){
		String absoluteDirPath = basePath + lookbook.getImgs();
		images = new ArrayList<String>();
		File dir = new File(absoluteDirPath);
		if(dir.exists()){
			File[] fileList = dir.listFiles();
			if(fileList != null){
				length = fileList.length;
				if(length > 0){
					for(int i=0;i<length;i++){
						String imagesPath = fileList[i].getAbsolutePath().substring(basePath.length()).replace('\\', '/');
						images.add(imagesPath);
					}
				}
			}
		}
	}

	@Override
	public Language process(String lang) {
		if(EN_US.equalsIgnoreCase(lang)){
			setEnglishValue();
		}else if(ZH_CN.equalsIgnoreCase(lang)){
			setChineseValue();
		}else if(ZH_TW.equalsIgnoreCase(lang)){
			setTWChineseValue();
		}
		return this;
	}
	public void setEnglishValue(){
		setTitle(lookbook.getLookbookEname());
	}
	public void setChineseValue(){
		setTitle(lookbook.getLookbookCname());
	}
	public void setTWChineseValue(){
		setTitle(lookbook.getLookbookCname());
	}
	public Lookbook getLookbook() {
		return lookbook;
	}

	public void setLookbook(Lookbook lookbook) {
		this.lookbook = lookbook;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
}
