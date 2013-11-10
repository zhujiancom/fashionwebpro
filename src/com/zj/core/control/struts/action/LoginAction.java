package com.zj.core.control.struts.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.zj.bigdefine.GlobalParam;
import com.zj.common.exception.ServiceException;
import com.zj.common.utils.JSONUtil;
import com.zj.core.control.struts.BaseAction;
import com.zj.core.po.SysUser;
import com.zj.core.service.ISysUserService;

@Component("loginAction")
@Scope("prototype")
public class LoginAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 968864689911534893L;
	@Resource
	private ISysUserService userService;
	
	private SysUser user;
	
	public void login()throws Exception {
		SysUser login_user = null;
		String json = "";
		try {
			login_user = userService.login(user);
			if (login_user == null) {
				json = JSONUtil.stringToJson("UserName or Password error !");
				sendJSONdata(json);
			}
			if (GlobalParam.ENABLE != login_user.getIsEnable()) {
				json = JSONUtil.stringToJson("The Account has been disabled, please reEnable !");
				sendJSONdata(json);
			}
			session.put(GlobalParam.LOGIN_USER_SESSION, login_user);
			sendJSONdata(JSONUtil.stringToJson(json));
		} catch (Exception e) {
			e.printStackTrace();
			json = e.getMessage();
			sendJSONdata(json);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public String logout(){
		SysUser u = (SysUser) session.get(GlobalParam.LOGIN_USER_SESSION);
		try {
			userService.logout(u);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
//		getValueStack().pop();
		session.clear();
		return "logout_success";
	}

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}
}
