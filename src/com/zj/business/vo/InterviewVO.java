package com.zj.business.vo;

import com.zj.business.observer.LanguageType;
import com.zj.business.po.Interview;
import com.zj.common.utils.StringUtil;

public class InterviewVO  extends AbstractVO{
	private long id;
	private Interview interview;
	private String name;
	private String introduction;
	private String posterthumnail;
	private String videoUrl;
	private String videoName;
	private String type;
	
	public InterviewVO(){}
	
	public InterviewVO(Interview interview){
		super();
		this.interview = interview;
		init();
	}

	public void init(){
		setId(interview.getInterviewid());
		setType(interview.getInterviewtype());
		setVideoUrl(interview.getInterviewurl());
		setPosterthumnail(generateThumbnailUrl(interview.getPoster()));
		setName(interview.getInterviewEname());
		setIntroduction(interview.getInterviewEintro());
		if(!StringUtil.isEmpty(videoUrl)){
			int startIdx = videoUrl.lastIndexOf("/");
			this.videoName = videoUrl.substring(startIdx);
		}
	}
	
	@Override
	protected void setEnglishValue() {
		setName(interview.getInterviewEname());
		setIntroduction(interview.getInterviewEintro());
	}

	@Override
	protected void setChineseValue(LanguageType language) {
		setName(convertTCSC(interview.getInterviewCname(),language));
		setIntroduction(convertTCSC(interview.getInterviewCintro(),language));
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Interview getInterview() {
		return interview;
	}

	public void setInterview(Interview interview) {
		this.interview = interview;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	
}
