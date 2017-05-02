package com.tianrui.web.action.system.auth;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.system.auth.ISystemUserService;
import com.tianrui.api.req.system.auth.SystemUserQueryReq;
import com.tianrui.api.req.system.auth.SystemUserSaveReq;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.CurrUserUtils;
@Controller
@RequestMapping("trfc/system/auth/user")
public class SystemUserAction {
	
	private Logger log = LoggerFactory.getLogger(SystemUserAction.class);
	
	@Resource
	private ISystemUserService systemUserService;
	
	//显示当前页
	@RequestMapping("main")
	public ModelAndView main(HttpServletRequest request){
		ModelAndView view = new ModelAndView("system/auth/userMgr");
		view.addObject("orgId",CurrUserUtils.getCurrOrgid(request));
		view.addObject("orgName",CurrUserUtils.getCurrOrgName(request));
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
	public Result addUser(HttpServletRequest request,SystemUserSaveReq req){
		Result rs= Result.getErrorResult();
		try {
			req.setCurrUId(CurrUserUtils.getCurrid(request));
			rs = systemUserService.addUser(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	//删除数据
		@RequestMapping(value="/deleteUser",method=RequestMethod.POST)
		@ResponseBody
		public Result deleteUser(SystemUserQueryReq req){
			Result rs= Result.getErrorResult();
			try {
				rs = systemUserService.delUser(req);
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
	/**
	 * 获取下拉框数据(likename)
	 */
	@RequestMapping(value="/autoCompleteSearch",method=RequestMethod.POST)
	@ResponseBody
	public Result autoCompleteSearch(SystemUserQueryReq req){
		Result rs= Result.getErrorResult();
		try {
			rs = systemUserService.autoCompleteSearch(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	/**
	 * 获取所有用户列表
	 */
	@RequestMapping(value="/queryAllUser",method=RequestMethod.POST)
	@ResponseBody
	public Result autoCompleteSearch(){
		Result rs= Result.getErrorResult();
		try {
			rs = systemUserService.queryAllUser(Constant.ORG_ID);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
}
