package com.zj.core.web.listener;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import com.zj.common.log.Log;

/**
 * 
 * @author zhujian
 *	request监听器
 */
public class RequestListener implements  ServletRequestListener,ServletRequestAttributeListener {

	static{
		Log.info(RequestListener.class, "================ RequestListener Start! ================");
	}
	
	public void requestDestroyed(ServletRequestEvent event) {
		Log.info(this.getClass(), "================ RequestListener End! ================");
	}
	/**
	 * 当请求一个网页时会调用
	 */
	public void requestInitialized(ServletRequestEvent event) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
		String fallUrl = request.getRequestURI();
		String filetype = fallUrl.substring(fallUrl.lastIndexOf(".")+1);
		String url = fallUrl.substring(fallUrl.lastIndexOf("/"));
		if("jsp".equalsIgnoreCase(filetype) || "html".equalsIgnoreCase(filetype)){
			Log.info(this.getClass(),"----------------->reference page url = "+url);
		}
//		String deployPath = PlatForm.getDeployPath();
//		if(deployPath == null || "".equals(deployPath)){
//			String path = request.getContextPath();
//			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
//			PlatForm.setDeployPath(basePath);
//		}
	}

	public void attributeAdded(ServletRequestAttributeEvent event) {
		// TODO Auto-generated method stub
		
	}

	public void attributeRemoved(ServletRequestAttributeEvent event) {
		// TODO Auto-generated method stub
	}

	public void attributeReplaced(ServletRequestAttributeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
