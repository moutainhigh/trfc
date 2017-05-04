package com.tianrui.web.action.system.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.system.auth.ISystemRoleService;
import com.tianrui.api.req.system.auth.SystemRoleQueryReq;
import com.tianrui.api.req.system.auth.SystemRoleSaveReq;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 角色管理Action
 * @author YangZhenFu
 * @createtime 2017年3月31日 上午10:28:40
 * @classname SystemRoleAction.java
 */
@Controller
@RequestMapping("trfc/system/auth/role")
public class SystemRoleAction {
	
	private Logger log=LoggerFactory.getLogger(SystemRoleAction.class);
	
	@Autowired
	private ISystemRoleService systemRoleService;
	
	@RequestMapping("/main")
	public ModelAndView main(){
		return new ModelAndView("system/auth/role");
	}
	
	@RequestMapping(value="/page",method=RequestMethod.POST)
	@ResponseBody
	public Result page(SystemRoleQueryReq req){
		Result result=Result.getSuccessResult();
		try {
			result=systemRoleService.page(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Result add(SystemRoleSaveReq req){
		Result result=Result.getSuccessResult();
		try {
			result=systemRoleService.addRole(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Result edit(SystemRoleSaveReq req){
		Result result=Result.getSuccessResult();
		try {
			result=systemRoleService.editRole(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result delete(SystemRoleQueryReq req){
		Result result=Result.getSuccessResult();
		try {
			result=systemRoleService.delRole(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}

	/**
	 * 获取所有用户列表
	 */
	@RequestMapping(value="/queryAllRole",method=RequestMethod.POST)
	@ResponseBody
	public Result queryAllRole(){
		Result result= Result.getErrorResult();
		try {
			result = systemRoleService.queryAllRole();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
}
