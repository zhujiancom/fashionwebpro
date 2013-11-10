package com.zj.business.treenode;

import java.util.LinkedList;
import java.util.List;

import com.zj.bigdefine.CommonConstant;
import com.zj.business.vo.BrandVO;
import com.zj.business.vo.DesignerVO;
import com.zj.business.vo.MenuVO;
import com.zj.common.exception.ServiceException;

public class DesignerMenuBuilder implements IMenuBuilder {
	private DesignerVO designer;

	private List<BrandVO> brands;
	private boolean isPermission;

	public DesignerMenuBuilder(DesignerVO designer, List<BrandVO> brands) {
		super();
		this.designer = designer;
		this.brands = brands;
	}

	public DesignerMenuBuilder(DesignerVO designer, List<BrandVO> brands,
			boolean isPermission) {
		super();
		this.designer = designer;
		this.brands = brands;
		this.isPermission = isPermission;
	}

	public Menu createDesingerMenu() {
		Menu menu = new Menu(designer.getName().toUpperCase());
//		String attributes = "href='designer_showProfile.action?designer.designerId="
//				+ designer.getId() + "' target='mainPanel'";
		String attributes = "href='designer/"+ designer.getId() + ".html' target='mainPanel'";
		menu.setAttributes(attributes);
		return menu;
	}

	public Menu createBrandsMenu() {
		Menu menu = new Menu(MenuVO.menuMap.get(CommonConstant.BRAND));
		for (BrandVO brand : brands) {
			MenuItem item = new MenuItem(brand.getName().toUpperCase());
//			String attributes = "href='brand_showBrandInfo.action?brand.brandid="
//					+ brand.getId() + "' target='mainPanel'";
			String attributes = "href='brand/brand_"+brand.getId()+".html' target='mainPanel'";
			item.setAttributes(attributes);
			menu.addItem(item);
		}
		return menu;
	}

	public Menu createInterviewMenu() {
		Menu menu = new Menu(MenuVO.menuMap.get(CommonConstant.INTERVIEWS));
		MenuItem videoItem = new MenuItem(
				MenuVO.menuMap.get(CommonConstant.VIDEOS));
//		String videoAttr = "href='interview_showInterviews.action?designer.designerId="
//				+ designer.getId()
//				+ "&type=video' target='mainPanel'";
		String videoAttr = "href='interview/video/"+designer.getId()+"' target='mainPanel'";
		videoItem.setAttributes(videoAttr);
		menu.addItem(videoItem);
		MenuItem audioItem = new MenuItem(
				MenuVO.menuMap.get(CommonConstant.AUDIOS));
//		String audioAttr = "href='interview_showInterviews.action?designer.designerId="
//				+ designer.getId()
//				+ "&type=audio' target='mainPanel'";
		String audioAttr = "href='interview/audio/"+designer.getId()+"' target='mainPanel'";
		audioItem.setAttributes(audioAttr);
		menu.addItem(audioItem);
		MenuItem pressreportItem = new MenuItem(
				MenuVO.menuMap.get(CommonConstant.PRESSREPORTS));
//		String reportAttr = "href='report_showReports.action?designer.designerId="
//				+ designer.getId() + "' target='mainPanel'";
		String reportAttr = "href='reports/"+designer.getId()+"' target='mainPanel'";
		pressreportItem.setAttributes(reportAttr);
		menu.addItem(pressreportItem);
		return menu;
	}

	public Menu createCollectionMenu() {
		Menu menu = new Menu(MenuVO.menuMap.get(CommonConstant.COLLECTIONS));
		for (BrandVO brandvo : brands) {
			Menu subMenu = new Menu(brandvo.getName().toUpperCase());
			// lookbook Item
			MenuItem lookbookItem = new MenuItem(
					MenuVO.menuMap.get(CommonConstant.LOOKBOOK_IMAGES));
//			String lookbookAttr = "href='lookbook_showByBrand.action?brand.brandid="
//					+ brandvo.getId() + "' target='mainPanel'";
			String lookbookAttr = "href='collections/"+brandvo.getId()+"/lookbooks/' target='mainPanel'";
			lookbookItem.setAttributes(lookbookAttr);
			subMenu.addItem(lookbookItem);
			// editorial Item
			MenuItem editorialItem = new MenuItem(
					MenuVO.menuMap.get(CommonConstant.EDITORIAL_IMAGES));
//			String editorialAttr = "href='editorial_showByBrand.action?brand.brandid="
//					+ brandvo.getId() + "' target='mainPanel'";
			String editorialAttr = "href='collections/"+brandvo.getId()+"/editorials/' target='mainPanel'";
			editorialItem.setAttributes(editorialAttr);
			subMenu.addItem(editorialItem);
			// runway shows Item
			MenuItem runwayshowItem = new MenuItem(
					MenuVO.menuMap.get(CommonConstant.RUNWAY_SHOWS));
//			String runwayshowAttr = "href='runwayshow_showByBrand.action?brand.brandid="
//					+ brandvo.getId() + "' target='mainPanel'";
			String runwayshowAttr = "href='collections/"+brandvo.getId()+"/runwayshows/' target='mainPanel'";
			runwayshowItem.setAttributes(runwayshowAttr);
			subMenu.addItem(runwayshowItem);
			menu.addSubMenu(subMenu);
		}
		return menu;
	}

	@Override
	public List<Menu> createMenuTree() throws ServiceException{
		try{
			List<Menu> menuTree = new LinkedList<Menu>();
			menuTree.add(createDesingerMenu());
			menuTree.add(createBrandsMenu());
			if (isPermission) {
				menuTree.add(createInterviewMenu());
				menuTree.add(createCollectionMenu());
			}
			return menuTree;
		}catch(Exception e){
			throw new ServiceException("create menu Tree error!",e);
		}
	}

	public DesignerVO getDesigner() {
		return designer;
	}

	public void setDesigner(DesignerVO designer) {
		this.designer = designer;
	}

	public List<BrandVO> getBrands() {
		return brands;
	}

	public void setBrands(List<BrandVO> brands) {
		this.brands = brands;
	}

	public boolean isPermission() {
		return isPermission;
	}

	public void setPermission(boolean isPermission) {
		this.isPermission = isPermission;
	}
}
