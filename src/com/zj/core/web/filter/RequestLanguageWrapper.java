package com.zj.core.web.filter;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import com.zj.bigdefine.GlobalParam;
import com.zj.business.observer.LanguageType;

public class RequestLanguageWrapper extends HttpServletRequestWrapper {
	private Locale locale = null;

	public RequestLanguageWrapper(HttpServletRequest request) {
		super(request);
		HttpSession session = request.getSession();
		locale = (Locale) session.getAttribute("WW_TRANS_I18N_LOCALE");
	}

	@Override
	public String getHeader(String name) {
		String value = super.getHeader(name);
		if ("Accept-Language".equals(name) && locale != null) {
			value = locale.getLanguage() + "_" + locale.getCountry()
					+ value.substring(6, value.length());
		}
		return value;
	}

	@Override
	public Locale getLocale() {
		if(locale != null){
			return locale;
		}else{
			HttpServletRequest req = (HttpServletRequest) getRequest();
			Cookie[] cookies = req.getCookies();
			if(cookies != null){
				for(int i=0;i<cookies.length;i++){
					if(cookies[i].getName().equalsIgnoreCase(GlobalParam.FRONTEND_REQUEST_LANG)){
						Cookie cookie = cookies[i];
						if(cookie.getValue().equalsIgnoreCase(LanguageType.ZH_CN.toString())){
							locale = Locale.CHINA;
						}else if(cookie.getValue().equalsIgnoreCase(LanguageType.EN_US.toString())){
							locale = Locale.US;
						}else if(cookie.getValue().equalsIgnoreCase(LanguageType.ZH_TW.toString())){
							locale = Locale.TAIWAN;
						}
						break;
					}
				}
			}
			if (locale != null) {
				return locale;
			}else{
				return Locale.US;
			}
		}
	}

}
