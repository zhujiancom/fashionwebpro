package com.zj.business.vo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.zj.business.po.Report;

public class ReportVO  extends AbstractVO{
	private Report report;
	private String headImg;
	private List<String> images;
	private final String basePath;
	private String title;
	private String content;
	
	public ReportVO(Report report, String basePath){
		super();
		this.report = report;
		this.basePath = basePath;
		init();
	}
	
	public void init(){
		String absoluteDirPath = basePath + report.getReportimg();
		images = new ArrayList<String>();
		File dir = new File(absoluteDirPath);
		if(dir.exists()){
			File file[] = dir.listFiles();
			if(file.length > 0){
				String tempUrl = file[0].getAbsolutePath();
				headImg = tempUrl.substring(basePath.length()).replace('\\', '/');
				for(int i=0;i<file.length;i++){
					String imagesPath = file[i].getAbsolutePath().substring(basePath.length()).replace('\\', '/');
					images.add(imagesPath);
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
			setTWValue();
		}
		return this;
	}
	
	public void setEnglishValue(){
		setTitle(report.getReportEname());
		setContent(report.getReportEcontent());
	}
	public void setChineseValue(){
		setTitle(report.getReportCname());
		setContent(report.getReportCname());
	}
	public void setTWValue(){
		setTitle(report.getReportCname());
		setContent(report.getReportCname());
	}
	public Report getReport() {
		return report;
	}
	public void setReport(Report report) {
		this.report = report;
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

	
}
