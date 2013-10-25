package com.zj.business.treenode;

import java.util.LinkedList;
import java.util.List;

import com.zj.bigdefine.CommonConstant;
import com.zj.business.vo.BrandVO;
import com.zj.business.vo.DesignerVO;
import com.zj.business.vo.MenuVO;

public class BrandMenuBuilder implements IMenuBuilder {
	private BrandVO brand;
	private DesignerVO designer;
	private boolean isPermission;

	public BrandMenuBuilder(BrandVO brand, DesignerVO designer) {
		super();
		this.brand = brand;
		this.designer = designer;
	}

	public BrandMenuBuilder(BrandVO brand, DesignerVO designer,
			boolean isPermission) {
		super();
		this.brand = brand;
		this.designer = designer;
		this.isPermission = isPermission;
	}

	public Menu createBrandMenu() {
		Menu menu = new Menu(brand.getName().toUpperCase());
//		String attributes = "href='brand_showBrandInfo.action?brand.brandid="
//				+ brand.getId() + "' target=mainPanel";
		String attributes = "href='brand/brand_"+brand.getId()+".html' target='mainPanel'";
		menu.setAttributes(attributes);
		return menu;
	}

	public Menu createDesignerMenu() {
		Menu menu = new Menu(MenuVO.menuMap.get(CommonConstant.UNKNOW));
		if (designer != null) {
			menu = new Menu(designer.getName().toUpperCase());
//			String attributes = "href='designer_showProfile.action?designer.designerId="
//					+ designer.getId() + "' target='mainPanel'";
			String attributes = "href='designer/"+ designer.getId() + ".html' target='mainPanel'";
			menu.setAttributes(attributes);
		}
		return menu;
	}

	public Menu createInterviewMenu() {
		Menu menu = new Menu(MenuVO.menuMap.get(CommonConstant.INTERVIEWS));
		MenuItem videoItem = new MenuItem(
				MenuVO.menuMap.get(CommonConstant.VIDEOS));
		MenuItem audioItem = new MenuItem(
				MenuVO.menuMap.get(CommonConstant.AUDIOS));
		MenuItem pressreportItem = new MenuItem(
				MenuVO.menuMap.get(CommonConstant.PRESSROPORTS));
		if (designer != null) {
//			String videoAttr = "href='interview_showInterviews.action?designer.designerId="
//					+ designer.getId() + "&type=video' target='mainPanel'";
			
			String videoAttr = "href='interview/videos/"+designer.getId()+"' target='mainPanel'";
			videoItem.setAttributes(videoAttr);
//			String audioAttr = "href='interview_showInterviews.action?designer.designerId="
//					+ designer.getId() + "&type=audio' target='mainPanel'";
			String audioAttr = "href='interview/audios/"+designer.getId()+"' target='mainPanel'";

			audioItem.setAttributes(audioAttr);
//			String reportAttr = "href='report_showReports.action?designer.designerId="
//					+ designer.getId() + "' target='mainPanel'";
			String reportAttr = "href='reports/"+designer.getId()+"' target='mainPanel'";
			pressreportItem.setAttributes(reportAttr);
		}
		menu.addItem(videoItem);
		menu.addItem(audioItem);
		menu.addItem(pressreportItem);
		return menu;
	}

	public Menu createCollectionMenu() {
		Menu menu = new Menu(MenuVO.menuMap.get(CommonConstant.COLLECTIONS));
		// lookbook Item
		MenuItem lookbookItem = new MenuItem(
				MenuVO.menuMap.get(CommonConstant.LOOKBOOK_IMAGES));
		// editorial Item
		MenuItem editorialItem = new MenuItem(
				MenuVO.menuMap.get(CommonConstant.EDITORIAL_IMAGES));
		// runway shows Item
		MenuItem runwayshowItem = new MenuItem(
				MenuVO.menuMap.get(CommonConstant.RUNWAY_SHOWS));
		if (brand != null) {
//			String lookbookAttr = "href='lookbook_showByBrand.action?brand.brandid="
//					+ brand.getId() + "' target='mainPanel'";
			String lookbookAttr = "href='collections/"+brand.getId()+"/lookbooks/' target='mainPanel'";
			lookbookItem.setAttributes(lookbookAttr);
//			String editorialAttr = "href='editorial_showByBrand.action?brand.brandid="
//					+ brand.getId() + "' target='mainPanel'";
			String editorialAttr = "href='collections/"+brand.getId()+"/editorials/' target='mainPanel'";
			editorialItem.setAttributes(editorialAttr);
//			String runwayshowAttr = "href='runwayshow_showByBrand.action?brand.brandid="
//					+ brand.getId() + "' target='mainPanel'";
			String runwayshowAttr = "href='collections/"+brand.getId()+"/runwayshows/' target='mainPanel'";
			runwayshowItem.setAttributes(runwayshowAttr);
		}

		menu.addItem(lookbookItem);
		menu.addItem(editorialItem);
		menu.addItem(runwayshowItem);
		return menu;
	}

	@Override
	public List<Menu> createMenuTree() {
		List<Menu> menuTree = new LinkedList<Menu>();
		menuTree.add(createBrandMenu());
		menuTree.add(createDesignerMenu());
		if (isPermission) {
			menuTree.add(createInterviewMenu());
			menuTree.add(createCollectionMenu());
		}
		return menuTree;
	}

	public BrandVO getBrand() {
		return brand;
	}

	public void setBrand(BrandVO brand) {
		this.brand = brand;
	}

	public DesignerVO getDesigner() {
		return designer;
	}

	public void setDesigner(DesignerVO designer) {
		this.designer = designer;
	}

	public boolean isPermission() {
		return isPermission;
	}

	public void setPermission(boolean isPermission) {
		this.isPermission = isPermission;
	}
}
