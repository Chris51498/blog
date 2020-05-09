package com.blog.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求和响应的字符编码集设置
 * @author xj
 *
 */

@WebFilter("/*")
public class EncodingFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//System.out.println("字符编码集过滤器init...");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req; 
		HttpServletResponse response = (HttpServletResponse) resp;
		
		//设置编码集
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//System.out.println("字符编码集过滤");
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		//System.out.println("字符编码集过滤器destroy...");
	}

	

}
