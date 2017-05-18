package com.tianrui.web.action.system.auth;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.system.auth.ISystemRolePermissionsService;
import com.tianrui.api.req.system.auth.SystemRoleMenuSave;
import com.tianrui.api.req.system.auth.SystemUserQueryReq;
import com.tianrui.api.req.system.auth.SystemUserRoleSave;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.SessionManager;

@Controller
@RequestMapping("trfc/system/auth/rolePermissions")
public class SystemRolePermissionsAction {
	
	private Logger logger = LoggerFactory.getLogger(SystemRolePermissionsAction.class);
	
	@Autowired
	private ISystemRolePermissionsService systemRolePermissionsService;
	
	//显示当前页
	@RequestMapping("main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("system/auth/rolePermissions");
		view.addObject("orgname", Constant.ORG_NAME);
		return view;
	}
	
	@RequestMapping("queryUserByRole")
	@ResponseBody
	public Result queryUserByRole(SystemUserQueryReq req){
		Result result = Result.getErrorResult();
		try {
			result = systemRolePermissionsService.queryUserByRole(req);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("queryAllUserByRole")
	@ResponseBody
	public Result queryAllUserByRole(SystemUserQueryReq req){
		Result result = Result.getErrorResult();
		try {
			result = systemRolePermissionsService.queryAllUserByRole(req);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("addUserToRole")
	@ResponseBody
	public Result addUserToRole(SystemUserRoleSave save, HttpServletRequest request){
		Result result = Result.getErrorResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			save.setCurrId(user.getId());
			result = systemRolePermissionsService.addUserToRole(save);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("deleteUserToRole")
	@ResponseBody
	public Result deleteUserToRole(SystemUserRoleSave save, HttpServletRequest request){
		Result result = Result.getErrorResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			save.setCurrId(user.getId());
			result = systemRolePermissionsService.deleteUserToRole(save);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}

	@RequestMapping("queryMenuByRole")
	@ResponseBody
	public Result queryMenuByRole(SystemUserQueryReq req){
		Result result = Result.getErrorResult();
		try {
			result = systemRolePermissionsService.queryMenuByRole(req);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("authorizeMenuToRole")
	@ResponseBody
	public Result authorizeMenuToRole(SystemRoleMenuSave save){
		Result result = Result.getErrorResult();
		try {
			result = systemRolePermissionsService.authorizeMenuToRole(save);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("resetMenuToRole")
	@ResponseBody
	public Result resetMenuToRole(SystemRoleMenuSave save){
		Result result = Result.getErrorResult();
		try {
			result = systemRolePermissionsService.resetMenuToRole(save);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
}
