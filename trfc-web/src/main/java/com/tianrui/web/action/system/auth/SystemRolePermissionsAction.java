package com.tianrui.web.action.system.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.system.auth.ISystemRolePermissionsService;
import com.tianrui.api.req.system.auth.SystemUserQueryReq;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

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
	
}
