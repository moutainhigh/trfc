package com.tianrui.web.action.system.auth;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.req.system.auth.UserReq;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.service.impl.system.auth.SystemUserService;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.SessionManager;

@Controller
public class SystemLoginAction {
	private Logger log = LoggerFactory.getLogger(SystemUserAction.class);

	@Resource
	private SystemUserService systemUserService;

	// 列表数据
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Result login(UserReq req, HttpServletResponse response) {
		Result rs = Result.getErrorResult();
		try {
			rs = systemUserService.login(req);
			if (rs.getData() != null) {
				SystemUserResp user = (SystemUserResp) rs.getData();
				SessionManager.setSessionUser(user, response);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
	}

	// 列表数据
	@RequestMapping(value = "/trfc/loginOut")
	public ModelAndView loginOut(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("index");
		SessionManager.removeSessionUser(request);
		return view;
	}

	// 列表数据
	@RequestMapping(value = "/index")
	public ModelAndView login(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("index");
		return view;
	}

}
