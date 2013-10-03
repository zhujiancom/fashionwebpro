package com.zj.core.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zj.common.log.Log;

public class LoginFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String scheme = request.getScheme(); // http
		String contextPath = request.getContextPath();  // project name
		String serverName = request.getServerName(); //localhost
		int port = request.getServerPort(); // 8080
		String basePath = scheme+"://"+serverName+":"+port+contextPath+"/";
		
		String url=request.getServletPath(); 
		if(url.equals("")) url+="/";
//		if(url.startsWith("/backend/") && !"/backend/index.jsp".equals(url)){//若访问后台资源 过滤到login  
//			SysUser user = (SysUser) request.getSession().getAttribute(GlobalParam.LOGIN_USER_SESSION);
//			if(user == null){
//				response.sendRedirect(contextPath+"/sessionTimeout.jsp");
//			}
//		}
		
		chain.doFilter(request, response);   
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		Log.info(this.getClass(),"LoginFilter init...");
	}

}
