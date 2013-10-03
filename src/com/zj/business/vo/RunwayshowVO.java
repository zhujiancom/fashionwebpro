package com.zj.business.vo;

import com.zj.business.po.Runwayshow;

public class RunwayshowVO extends AbstractVO{
	
	private Runwayshow runwayshow;
	private String content;
	
	public RunwayshowVO(Runwayshow runwayshow){
		super();
		this.runwayshow = runwayshow;
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
		setContent(runwayshow.getRunwayshowEintro());
	}
	public void setChineseValue(){
		setContent(runwayshow.getRunwayshowCintro());
	}
	public void setTWChineseValue(){
		setContent(runwayshow.getRunwayshowCintro());
	}
	public Runwayshow getRunwayshow() {
		return runwayshow;
	}

	public void setRunwayshow(Runwayshow runwayshow) {
		this.runwayshow = runwayshow;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
