package com.zj.business.vo;

import com.zj.business.observer.LanguageType;
import com.zj.business.po.Runwayshow;

public class RunwayshowVO extends AbstractVO{
	
	private Runwayshow runwayshow;
	private String content;
	
	public RunwayshowVO(Runwayshow runwayshow){
		super();
		this.runwayshow = runwayshow;
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


	@Override
	protected void setChineseValue(LanguageType language) {
		// TODO Auto-generated method stub
		
	}

}
