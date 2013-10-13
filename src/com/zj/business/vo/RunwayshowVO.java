package com.zj.business.vo;

import com.zj.business.observer.LanguageType;
import com.zj.business.po.Runwayshow;
import com.zj.common.utils.StringUtil;

public class RunwayshowVO extends AbstractVO{
	private long id;
	private Runwayshow runwayshow;
	private String name;
	private String introduction;
	private String posterthumnail;
	private String videoUrl;
	private String videoName;
	
	public RunwayshowVO(){}
	
	public RunwayshowVO(Runwayshow runwayshow){
		super();
		this.runwayshow = runwayshow;
		init();
	}

	public void init(){
		setId(runwayshow.getRunwayshowid());
		setVideoUrl(runwayshow.getRunwayshowUrl());
		setPosterthumnail(generateThumbnailUrl(runwayshow.getPoster()));
		setName(runwayshow.getRunwayshowEname());
		setIntroduction(runwayshow.getRunwayshowEintro());
		if(!StringUtil.isEmpty(videoUrl)){
			int startIdx = videoUrl.lastIndexOf("/");
			this.videoName = videoUrl.substring(startIdx);
		}
	}

	@Override
	protected void setEnglishValue() {
		setName(runwayshow.getRunwayshowEname());
		setIntroduction(runwayshow.getRunwayshowEintro());
	}

	@Override
	protected void setChineseValue(LanguageType language) {
		setName(convertTCSC(runwayshow.getRunwayshowCname(),language));
		setIntroduction(convertTCSC(runwayshow.getRunwayshowCintro(),language));
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Runwayshow getRunwayshow() {
		return runwayshow;
	}

	public void setRunwayshow(Runwayshow runwayshow) {
		this.runwayshow = runwayshow;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getPosterthumnail() {
		return posterthumnail;
	}

	public void setPosterthumnail(String posterthumnail) {
		this.posterthumnail = posterthumnail;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	

}
