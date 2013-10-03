package com.zj.core.control.struts.intercept;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.zj.bigdefine.GlobalParam;
import com.zj.core.bean.OnlineSysUser;
import com.zj.core.po.SysUser;

public class OnlineUserStatistic implements Interceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = -437586460520359274L;

	private String faragoMessage;
	private ActionContext ctx;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map sessionMap = invocation.getInvocationContext().getSession(); 
		if (sessionMap.get(GlobalParam.LOGIN_USER_SESSION) != null) {  
			SysUser user = (SysUser) sessionMap.get(GlobalParam.LOGIN_USER_SESSION);
			if(OnlineSysUser.isOld(user)){
				 sessionMap.remove(GlobalParam.LOGIN_USER_SESSION);// 清除session
				 faragoMessage = "对不起，您的帐号[" + user.getLoginId() + "]已在别的地方登录，您已被迫退出。若有疑问请联系管理员，谢谢！";  
				 ctx.put("faragoMessage", faragoMessage);
				 return Action.ERROR;
			}
		}
			return invocation.invoke();
	}

}
