package com.zj.business.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zj.business.observer.Language;
import com.zj.business.observer.LanguageType;
import com.zj.business.po.Footer;
import com.zj.business.service.IFooterService;
import com.zj.business.vo.FooterVO;
import com.zj.business.vo.VOFactory;
import com.zj.common.exception.ServiceException;
import com.zj.core.control.struts.BaseAction;

@Component("footerAction")
@Scope("prototype")
public class FooterAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 159219890483524436L;
	private Footer footer;
	private String type;
	@Resource
	private IFooterService footerService;
	
	public String save(){
		try {
			footerService.insert(footer);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "save_success";
	}
	
	public String getFooterInfo(){
		LanguageType type = getLanguageType();
        Language language = Language.getInstance();
		try {
			Footer f = footerService.get(Footer.class, 1L);
			FooterVO vo = VOFactory.getObserverVO(FooterVO.class,f);
			language.addObserver(vo);
			language.setLanguage(type);
			getValueStack().set("footervo",vo);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "load_success";
	}
	
	public Footer getFooter() {
		return footer;
	}

	public void setFooter(Footer footer) {
		this.footer = footer;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
