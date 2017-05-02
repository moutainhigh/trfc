package com.tianrui.web.action.system.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.system.auth.ISystemUserService;
import com.tianrui.smartfactory.common.constants.Constant;

@Controller
@RequestMapping("trfc/system/auth/userPermissions")
public class SystemUserPermissionsAction {
	
	private Logger logger = LoggerFactory.getLogger(SystemUserPermissionsAction.class);
	
	@Autowired
	private ISystemUserService systemUserService;
	
	//显示当前页
	@RequestMapping("main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("system/auth/userPermissions");
		view.addObject("orgname", Constant.ORG_NAME);
		return view;
	}
	
}
