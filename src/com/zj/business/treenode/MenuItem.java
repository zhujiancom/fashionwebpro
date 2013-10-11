package com.zj.business.treenode;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class MenuItem extends AbstractMenu implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MenuItem(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
}
