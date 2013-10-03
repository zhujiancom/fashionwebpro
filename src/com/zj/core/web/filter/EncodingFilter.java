package com.zj.core.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {

	static {
		System.out.println(">>>>>>>>>> 启动EncodingFilter.");
	}
	protected String _encoding = null;
	protected FilterConfig _filterConfig = null;

	protected String selectEncoding(ServletRequest request) {
		return (this._encoding);
	}

	public void destroy() {
		this._encoding = null;
		this._filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String encoding = selectEncoding(request);

		if (encoding != null) {
			request.setCharacterEncoding(encoding);
			response.setContentType("text/html;charset=" + encoding);
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this._filterConfig = filterConfig;
		this._encoding = filterConfig.getInitParameter("encoding");
	}

}
