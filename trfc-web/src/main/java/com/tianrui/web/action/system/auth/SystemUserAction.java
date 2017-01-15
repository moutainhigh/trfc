package com.tianrui.web.action.system.auth;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.req.system.auth.SystemUserQueryReq;
import com.tianrui.service.impl.system.auth.SystemUserService;
import com.tianrui.smartfactory.common.vo.Result;
@Controller
@RequestMapping("system/auth/user")
public class SystemUserAction {
	
	private Logger log = LoggerFactory.getLogger(SystemUserAction.class);
	
	@Resource
	private SystemUserService systemUserService;
	
	//显示当前页
	@RequestMapping("main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("system/auth/userMgr");
		return view;
	}
	
	//列表数据
	@RequestMapping(value="/page",method=RequestMethod.POST)
	@ResponseBody
	public Result page(SystemUserQueryReq req){
		Result rs= Result.getErrorResult();
		try {
			rs = systemUserService.page(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	
	//新增数据
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	@ResponseBody
	public Result addUser(SystemUserQueryReq req){
		Result rs= Result.getErrorResult();
		try {
			rs = systemUserService.page(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	
	//详情
	@RequestMapping(value="/detail",method=RequestMethod.POST)
	@ResponseBody
	public Result detail(SystemUserQueryReq req){
		Result rs= Result.getErrorResult();
		try {
			rs = systemUserService.detail(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	
}
