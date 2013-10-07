package com.zj.business.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zj.business.po.Brand;
import com.zj.business.po.Designer;
import com.zj.business.po.Style;
import com.zj.business.service.IBrandService;
import com.zj.business.service.IDesignerService;
import com.zj.business.service.IStyleService;
import com.zj.common.exception.ServiceException;
import com.zj.core.control.struts.BaseAction;

@Component("searchAction")
@Scope("prototype")
public class SearchAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -833389843083658746L;
	private String keywords;
	private String searchType;
	@Resource
	private IDesignerService designerService;
	@Resource
	private IBrandService brandService;
	@Resource
	private IStyleService styleService;
	
	
	public String headSearch(){
		if(searchType.equals(SearchType.ALL.toString())){
			try {
				List<Designer> designers = designerService.fuzzySearchByName(keywords);
				getValueStack().set("designers", designers);
			} catch (ServiceException e) {
				getValueStack().set("designerMsg", e.getMessage());
			}
			try {
				List<Brand> brands = brandService.fuzzySearchByName(keywords);
				getValueStack().set("brands", brands);
			} catch (ServiceException e) {
				getValueStack().set("brandMsg", e.getMessage());
			}
			try {
				List<Style> styles = styleService.fuzzySearchByName(keywords);
				getValueStack().set("styles", styles);
			} catch (ServiceException e) {
				getValueStack().set("styleMsg", e.getMessage());
			}
		}else if(searchType.equals(SearchType.DESIGNER.toString())){
			try {
				List<Designer> designers = designerService.fuzzySearchByName(keywords);
				getValueStack().set("designers", designers);
			} catch (ServiceException e) {
				getValueStack().set("designerMsg", e.getMessage());
			}
		}else if(searchType.equals(SearchType.BRAND.toString())){
			try {
				List<Brand> brands = brandService.fuzzySearchByName(keywords);
				getValueStack().set("brands", brands);
			} catch (ServiceException e) {
				getValueStack().set("brandMsg", e.getMessage());
			}
		}else if(searchType.equals(SearchType.STYLE.toString())){
			try {
				List<Style> styles = styleService.fuzzySearchByName(keywords);
				getValueStack().set("styles", styles);
			} catch (ServiceException e) {
				getValueStack().set("styleMsg", e.getMessage());
			}
		}
		return "head_search";
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
}
