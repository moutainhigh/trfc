package com.tianrui.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

public class PortalsFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse)response;
		req.setAttribute("basePath", "/resources");
		req.setAttribute("staticBasePath", "/resources");

		//获取路径
		String p = req.getServletPath();
		//过滤所有'/trfc'开头的路径
		if(p.length()<5 || !("/trfc".equals(p.substring(0,5)))) {
			chain.doFilter(request, response);
			return;
		}
		//从session中获取账号
		HttpSession session = req.getSession();
		String userId = (String)
				session.getAttribute("userId");
		//根据账号判断用户是否登录
		if(userId == null || !StringUtils.isNotBlank(userId)) {
			//没登录,重定向到登录页
			resp.sendRedirect(
					"/index");
		} else {
			//已登录,请求继续执行
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
