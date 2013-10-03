package com.zj.business.vo;

import com.zj.business.po.Interview;

public class InterviewVO  extends AbstractVO{

	private Interview interview;
	private String content;
	
	public InterviewVO(Interview interview){
		super();
		this.interview = interview;
		
	}

	@Override
	public Language process(String lang) {
		if(EN_US.equalsIgnoreCase(lang)){
			setEnglishValue();
		}else if(ZH_CN.equalsIgnoreCase(lang)){
			setChineseValue();
		}else if(ZH_TW.equalsIgnoreCase(lang)){
			
		}
		return this;
	}
	
	public void setEnglishValue(){
		setContent(interview.getInterviewEintro());
	}
	
	public void setChineseValue(){
		setContent(interview.getInterviewCintro());
	}

	public void setTWChineseValue(){
		setContent(interview.getInterviewCintro());
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Interview getInterview() {
		return interview;
	}

	public void setInterview(Interview interview) {
		this.interview = interview;
	}
}
