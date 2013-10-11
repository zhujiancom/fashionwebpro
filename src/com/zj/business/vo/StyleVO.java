package com.zj.business.vo;

import com.zj.business.observer.LanguageType;
import com.zj.business.po.Style;

public class StyleVO extends AbstractVO{

	private Style style;
	private String title;
	
	public StyleVO(Style style){
		super();
		this.style = style;
	}
	
	public void setEnglishValue(){
		setTitle(style.getStyleEname());
	}
	
	public void setChineseValue(){
		setTitle(style.getStyleCname());
	}
	
	public void setTWChineseValue(){
		setTitle(style.getStyleCname());
	}

	public Style getStyle() {
		return style;
	}

	public void setStyle(Style style) {
		this.style = style;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	protected void setChineseValue(LanguageType language) {
		// TODO Auto-generated method stub
		
	}

}
