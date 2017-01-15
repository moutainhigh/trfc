package com.tianrui.web.action.system.auth;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.req.system.auth.UserReq;
import com.tianrui.service.impl.system.auth.SystemUserService;
import com.tianrui.smartfactory.common.vo.Result;
@Controller
public class SystemLoginAction {
	private Logger log = LoggerFactory.getLogger(SystemUserAction.class);
	
	@Resource
	private SystemUserService systemUserService;
	
	
	//列表数据
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Result login(UserReq req){
		Result rs= Result.getErrorResult();
		try {
			rs = systemUserService.login(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	
	//列表数据
	@RequestMapping(value="/index")
	public ModelAndView login(){
		ModelAndView view = new ModelAndView("system/auth/userMgr");
		return view;
	}
	
}
