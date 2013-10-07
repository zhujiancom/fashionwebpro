package com.zj.business.treenode;

import java.io.Serializable;

public abstract class AbstractMenu implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String name;
	protected String attributes;
	
	public AbstractMenu(){}
	
	public AbstractMenu(String name){
		this.name = name;
	}
	
	public AbstractMenu(String name,String attributes){
		this(name);
		this.attributes = attributes;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	
	
}
