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

import org.apache.commons.lang.StringUtils;

import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.web.util.SessionManager;

@WebFilter
public class PortalsFilter implements Filter {
    
    private final static String basePath = "/resources";
    
    private final static String staticBasePath = "/resources";

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse)response;
		req.setAttribute("basePath", basePath);
		req.setAttribute("staticBasePath", staticBasePath);
		//获取路径
		String p = req.getServletPath();
		if (StringUtils.isNotBlank(p) && !StringUtils.equals(p, "/")) {
            if (p.startsWith("/trfc")) {
                SystemUserResp user = SessionManager.getSessionUser(req);
                if (user != null) {
                    request.setAttribute("userId", user.getId());
                    request.setAttribute("userName", user.getName());
                    chain.doFilter(request, response);
                } else {
                    resp.sendRedirect("/index");
                }
            } else {
                chain.doFilter(request, response);
            }
        } else {
            resp.sendRedirect("/index");
        }
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
