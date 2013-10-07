package com.zj.business.vo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.zj.business.po.Editorial;

public class EditorialVO extends AbstractVO{
	private Editorial editorial;
	private List<String> images;
	private final String basePath;
	private int length;
	private String title;
	public EditorialVO(Editorial editorial,String basePath) {
		super();
		this.editorial = editorial;
		this.basePath = basePath;
		init();
	}
	
	public void init(){
		String absoluteDirPath = basePath + editorial.getImgs();
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
		}else{
			setChineseValue(lang);
		}
		return this;
	}
	private void setEnglishValue(){
		setTitle(editorial.getEditorialEname());
	}
	private void setChineseValue(String language){
		setTitle(editorial.getEditorialCname());
	}
	
	public Editorial getEditorial() {
		return editorial;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
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
