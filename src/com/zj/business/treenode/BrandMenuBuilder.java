package com.zj.business.treenode;

import com.zj.business.po.Designer;
import com.zj.business.vo.BrandVO;

public class BrandMenuBuilder implements IMenuBuilder {
	private BrandVO brand;

	public BrandMenuBuilder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BrandMenuBuilder(BrandVO brand) {
		super();
		this.brand = brand;
	}

	@Override
	public Menu createDesignerMenu() {
		Menu menu = new Menu(UNKNOW);
//		DesignerVO designervo = brand.getDesignervo();
		Designer designer = brand.getBrand().getDesigner();
		if (designer != null) {
			menu = new Menu(designer.getEname().toUpperCase());
			String attributes = "href='designer_showProfile.action?designer.designerId="
					+ designer.getDesignerId()
					+ "' target='mainPanel'";
			menu.setAttributes(attributes);
		}
		return menu;
	}

	@Override
	public Menu createBrandMenu() {
		Menu menu = new Menu(brand.getName().toUpperCase());
		String attributes = "href='brand_showBrandInfo.action?brand.brandid="
				+ brand.getBrand().getBrandid() + "' target=mainPanel";
		menu.setAttributes(attributes);
		return menu;
	}

	@Override
	public Menu createInterviewMenu() {
//		DesignerVO designervo = brand.getDesignervo();
		Designer designer = brand.getBrand().getDesigner();
		Menu menu = new Menu(INTERVIEWS);
		MenuItem videoItem = new MenuItem(VIDEOS);
		MenuItem audioItem = new MenuItem(AUDIOS);
		MenuItem pressreportItem = new MenuItem(PRESSROPORTS);
		if (designer != null) {
			String videoAttr = "href='interview_showInterviews.action?designer.designerId="
					+ designer.getDesignerId()
					+ "&type=video' target='mainPanel'";
			videoItem.setAttributes(videoAttr);
			String audioAttr = "href='interview_showInterviews.action?designer.designerId="
					+ designer.getDesignerId()
					+ "&type=audio' target='mainPanel'";
			audioItem.setAttributes(audioAttr);
			String reportAttr = "href='report_showReports.action?designer.designerId="
					+ designer.getDesignerId()
					+ "' target='mainPanel'";
			pressreportItem.setAttributes(reportAttr);
		}
		menu.addItem(videoItem);
		menu.addItem(audioItem);
		menu.addItem(pressreportItem);
		return menu;
	}

	@Override
	public Menu createCollectionMenu() {
		Menu menu = new Menu(COLLECTIONS);
		// lookbook Item
		MenuItem lookbookItem = new MenuItem(LOOKBOOK_IMAGES);
		String lookbookAttr = "href='lookbook_showByBrand.action?brand.brandid="
				+ brand.getBrand().getBrandid() + "'";
		lookbookItem.setAttributes(lookbookAttr);
		menu.addItem(lookbookItem);
		// editorial Item
		MenuItem editorialItem = new MenuItem(EDITORIAL_IMAGES);
		String editorialAttr = "href='editorial_showByBrand.action?brand.brandid="
				+ brand.getBrand().getBrandid() + "'";
		editorialItem.setAttributes(editorialAttr);
		menu.addItem(editorialItem);
		// runway shows Item
		MenuItem runwayshowItem = new MenuItem(RUNWAY_SHOWS);
		String runwayshowAttr = "href='runwayshow_showByBrand.action?brand.brandid="
				+ brand.getBrand().getBrandid() + "'";
		runwayshowItem.setAttributes(runwayshowAttr);
		menu.addItem(runwayshowItem);
		return menu;
	}

	public BrandVO getBrand() {
		return brand;
	}

	public void setBrand(BrandVO brand) {
		this.brand = brand;
	}

}
