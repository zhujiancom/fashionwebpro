package com.zj.core.web.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.zj.bigdefine.GlobalParam;

public class SessionListener implements HttpSessionListener,HttpSessionAttributeListener {

	static {
		System.out.println(">>>>>>>>>>>>>启动SessionListener监听器");
	}

	public void sessionCreated(HttpSessionEvent event) {
	}

	public void sessionDestroyed(HttpSessionEvent event) {
	}

	/**
	 * 用户登录时
	 */
	public void attributeAdded(HttpSessionBindingEvent event) {
		String name = event.getName();
//		System.out.println(name);
//		if(GlobalParam.LOGIN_USER_SESSION.equals(event.getName())){
//			SysUser user = (SysUser) event.getValue();
//			OnlineUsers.addUser(user);
//			event.getSession().setMaxInactiveInterval(60*20);
//		}
	}
	/**
	 * 用户注销退出时
	 */
	public void attributeRemoved(HttpSessionBindingEvent event) {
		String name = event.getName();
//		System.out.println(name);
//		if(GlobalParam.LOGIN_USER_SESSION.equals(event.getName())){
//			SysUser user = (SysUser) event.getValue();
//			OnlineUsers.removeUser(user);
//			user = null;
//		}
	}

	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
