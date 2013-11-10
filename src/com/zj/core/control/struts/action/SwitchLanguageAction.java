package com.zj.core.control.struts.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zj.bigdefine.GlobalParam;
import com.zj.common.utils.StringUtil;
import com.zj.core.control.struts.BaseAction;

@Component("switchLanguageAction")
@Scope("prototype")
public class SwitchLanguageAction extends BaseAction {
	private static final Log log = LogFactory.getLog(SwitchLanguageAction.class);
	private String referer;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1413903144595287865L;
	
	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String request_locale = request.getParameter("request_locale");
		log.debug("request locale is "+request_locale);
		if(StringUtil.isEmpty(request_locale)){
			request_locale = request.getLocale().getLanguage()+"_"+request.getLocale().getCountry();
		}
		log.debug("locale will be written to cookie value is "+request_locale);
		Cookie cookie = new Cookie(GlobalParam.FRONTEND_REQUEST_LANG,request_locale);
		cookie.setMaxAge(31536000);
		cookie.setPath("/");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.addCookie(cookie);
		String ref = (String) request.getHeader("referer");
		if (null != ref) {
			setReferer(ref);
		}
		return SUCCESS;
	}

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}
	

}
