package com.zj.common.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.zj.bigdefine.GlobalParam;
import com.zj.core.control.struts.action.LoginAction;
import com.zj.core.po.SysUser;

public class BackSessionInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1923159682201108307L;

	@SuppressWarnings("rawtypes")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map session = ActionContext.getContext().getSession();
		Action action = (Action) invocation.getAction();
		if(action instanceof LoginAction){
			return invocation.invoke();
		}
		SysUser user = (SysUser) session.get(GlobalParam.LOGIN_USER_SESSION);
		if(user == null){
			return Action.LOGIN;
		}else{
			return invocation.invoke();
		}
	}
}
