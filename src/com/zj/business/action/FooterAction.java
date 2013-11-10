package com.zj.business.action;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
	private static final Log log = LogFactory.getLog(FooterAction.class);
	private Footer footer;
	private String type;
	@Resource
	private IFooterService footerService;
	@Resource
	private Language language;
	
	public String save(){
		try {
			footerService.update(footer);
		} catch (ServiceException e) {
			log.debug("update footer info error",e);
			e.printStackTrace();
		}
		return "save_success";
	}
	
	public String loadFooterInfoForBackend(){
		Footer f = null;
		try {
			f = footerService.get(Footer.class, 1L);
		} catch (ServiceException e) {
			log.info("add a new Footer info!");
			f = new Footer(1L);
			try {
				footerService.insert(f);
			} catch (ServiceException e1) {
				log.debug("add new footer info error",e1);
				f = new Footer(1L);
			}
		}finally{
			getValueStack().set("footer", f);
		}
		return "load_success";
	}
	
	public String getFooterInfo(){
		LanguageType type = getLanguageType();
//        Language language = Language.getInstance();
		try {
			Footer f = footerService.get(Footer.class, 1L);
			FooterVO vo = VOFactory.getObserverVO(FooterVO.class,f);
			language.addObserver(vo);
			language.setLanguage(type);
			getValueStack().set("footervo",vo);
		} catch (ServiceException e) {
			log.warn("accquire Footer info in frontend error!",e);
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
