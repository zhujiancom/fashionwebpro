package com.zj.business.treenode;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class Menu extends AbstractMenu implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4773541903157712966L;
	
	private List<MenuItem> items;
	private List<Menu> subMenus;
	
	public Menu(String menuName) {
		super(menuName);
		this.items = new LinkedList<MenuItem>();
		this.subMenus = new LinkedList<Menu>();
	}
	
	public boolean addItem(MenuItem item){
		return items.add(item);
	}
	
	public boolean removeItem(MenuItem item){
		return items.remove(item);
	}
	
	public Integer getItemCount(){
		return items.size();
	}
	
	public boolean addSubMenu(Menu subMenu){
		return subMenus.add(subMenu);
	}

	public boolean removeSubMenu(Menu subMenu){
		return subMenus.remove(subMenu);
	}
	
	public Integer getSubMenuCount(){
		return subMenus.size();
	}
	
	public List<MenuItem> getItems() {
		return items;
	}

	public void setItems(List<MenuItem> items) {
		this.items = items;
	}
	
	public List<Menu> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<Menu> subMenus) {
		this.subMenus = subMenus;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
