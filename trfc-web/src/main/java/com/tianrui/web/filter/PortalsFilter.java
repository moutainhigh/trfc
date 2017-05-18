package com.tianrui.web.filter;

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

import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.web.util.SessionManager;

@WebFilter
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
		//System.out.println(p);
		//过滤所有'/trfc'开头的路径
		if(p != null && p.startsWith("/trfc")) {
			//从session中获取账号
			SystemUserResp user = SessionManager.getSessionUser(req);
			//根据账号判断用户是否登录
			if(user == null) {
				//没登录,重定向到登录页
				resp.sendRedirect("/index");
			} else {
				//已登录,请求继续执行
				chain.doFilter(request, response);
			}
		}else{
			chain.doFilter(request, response);
			return;
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
