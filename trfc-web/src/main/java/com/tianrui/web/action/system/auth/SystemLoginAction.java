package com.tianrui.web.action.system.auth;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.req.system.auth.UserReq;
import com.tianrui.api.resp.system.auth.SystemUserResp;
//import com.tianrui.api.resp.system.auth.SystemUserResp;
//import com.tianrui.service.bean.system.auth.SystemUser;
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
	public Result login(UserReq req,HttpServletRequest request){
		Result rs= Result.getErrorResult();
		try {
			rs = systemUserService.login(req);
			if(rs.getData()!=null){
				SystemUserResp user = (SystemUserResp)rs.getData();
				HttpSession session = request.getSession();
				session.setAttribute("userId", user.getId());
				session.setAttribute("userName", user.getName());
			}
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	
	//列表数据
	@RequestMapping(value="/index")
	public ModelAndView login(){
		ModelAndView view = new ModelAndView("index");
		return view;
	}
	
}
