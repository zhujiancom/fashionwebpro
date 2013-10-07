package com.zj.business.treenode;

import java.util.List;

import com.zj.business.vo.BrandVO;
import com.zj.business.vo.DesignerVO;

public class DesignerMenuBuilder implements IMenuBuilder {
	private DesignerVO designer;

	public DesignerMenuBuilder() {
		super();
	}

	public DesignerMenuBuilder(DesignerVO designer) {
		super();
		this.designer = designer;
	}

	@Override
	public Menu createDesignerMenu() {
		Menu menu = new Menu(designer.getName().toUpperCase());
		String attributes = "href='designer_showProfile.action?designer.designerId="
				+ designer.getDesigner().getDesignerId()
				+ "' target='mainPanel'";
		menu.setAttributes(attributes);
		return menu;
	}

	@Override
	public Menu createBrandMenu() {
		Menu menu = new Menu(BRAND);
		List<BrandVO> brandvos = designer.getBrandvos();
		for(BrandVO brandvo:brandvos){
			MenuItem item = new MenuItem(brandvo.getName().toUpperCase());
			String attributes = "href='brand_showBrandInfo.action?brand.brandid="+brandvo.getBrand().getBrandid()+"' target='mainPanel'";
			item.setAttributes(attributes);
			menu.addItem(item);
		}
		return menu;
	}

	@Override
	public Menu createInterviewMenu() {
		Menu menu = new Menu(INTERVIEWS);
		MenuItem videoItem = new MenuItem(VIDEOS);
		String videoAttr = "href='interview_showInterviews.action?designer.designerId="+designer.getDesigner().getDesignerId()+"&type=video' target='mainPanel'";
		videoItem.setAttributes(videoAttr);
		menu.addItem(videoItem);
		MenuItem audioItem = new MenuItem(AUDIOS);
		String audioAttr = "href='interview_showInterviews.action?designer.designerId="+designer.getDesigner().getDesignerId()+"&type=audio' target='mainPanel'";
		audioItem.setAttributes(audioAttr);
		menu.addItem(audioItem);
		MenuItem pressreportItem = new MenuItem(PRESSROPORTS);
		String reportAttr="href='report_showReports.action?designer.designerId="+designer.getDesigner().getDesignerId()+"' target='mainPanel'";
		pressreportItem.setAttributes(reportAttr);
		menu.addItem(pressreportItem);
		return menu;
	}

	@Override
	public Menu createCollectionMenu() {
		Menu menu = new Menu(COLLECTIONS);
		List<BrandVO> brandvos = designer.getBrandvos();
		for(BrandVO brandvo:brandvos){
			Menu subMenu = new Menu(brandvo.getName().toUpperCase());
			//lookbook Item
			MenuItem lookbookItem = new MenuItem(LOOKBOOK_IMAGES);
			String lookbookAttr="href='lookbook_showByBrand.action?brand.brandid="+brandvo.getBrand().getBrandid()+"'";
			lookbookItem.setAttributes(lookbookAttr);
			subMenu.addItem(lookbookItem);
			//editorial Item
			MenuItem editorialItem = new MenuItem(EDITORIAL_IMAGES);
			String editorialAttr="href='editorial_showByBrand.action?brand.brandid="+brandvo.getBrand().getBrandid()+"'";
			editorialItem.setAttributes(editorialAttr);
			subMenu.addItem(editorialItem);
			//runway shows Item
			MenuItem runwayshowItem = new MenuItem(RUNWAY_SHOWS);
			String runwayshowAttr="href='runwayshow_showByBrand.action?brand.brandid="+brandvo.getBrand().getBrandid()+"'";
			runwayshowItem.setAttributes(runwayshowAttr);
			subMenu.addItem(runwayshowItem);
			menu.addSubMenu(subMenu);
		}
		return menu;
	}

	public DesignerVO getDesigner() {
		return designer;
	}

	public void setDesigner(DesignerVO designer) {
		this.designer = designer;
	}

}
