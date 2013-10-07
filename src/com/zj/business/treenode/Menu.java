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
	public static void main(String[] args){
		Menu designer = new Menu("ERIC ZHU");
		
		Menu brandMenu = new Menu("BRAND");
		MenuItem brand1 = new MenuItem("HERMES");
		MenuItem brand2 = new MenuItem("LV");
		brandMenu.addItem(brand1);
		brandMenu.addItem(brand2);
		
		Menu interview = new Menu("INTERVIEWS");
		MenuItem video = new MenuItem("VIDEO");
		MenuItem audio = new MenuItem("AUDIO");
		MenuItem pressreport = new MenuItem("PRESSREPORT");
		interview.addItem(video);
		interview.addItem(audio);
		interview.addItem(pressreport);
		
		Menu collection = new Menu("COLLECTION");
		Menu submenu1 = new Menu("HERMES");
		MenuItem lookbook1 = new MenuItem("LOOKBOOK IMAGES");
		MenuItem editorial1 = new MenuItem("EDTIORIAL IMAGES");
		MenuItem runwayshow1 = new MenuItem("RUNWAY SHOWS");
		submenu1.addItem(lookbook1);
		submenu1.addItem(editorial1);
		submenu1.addItem(runwayshow1);
		Menu submenu2 = new Menu("LV");
		MenuItem lookbook2 = new MenuItem("LOOKBOOK IMAGES");
		MenuItem editorial2 = new MenuItem("EDTIORIAL IMAGES");
		MenuItem runwayshow2 = new MenuItem("RUNWAY SHOWS");
		submenu2.addItem(lookbook2);
		submenu2.addItem(editorial2);
		submenu2.addItem(runwayshow2);
		collection.addSubMenu(submenu1);
		collection.addSubMenu(submenu2);
		
		System.out.println(designer);
		System.out.println(brandMenu);
		System.out.println(interview);
		System.out.println(collection);
		
	}
}
