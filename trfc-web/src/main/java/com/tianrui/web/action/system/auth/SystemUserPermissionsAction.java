package com.tianrui.web.action.system.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.smartfactory.common.constants.Constant;

@Controller
@RequestMapping("trfc/system/auth/userPermissions")
public class SystemUserPermissionsAction {
	
	//显示当前页
	@RequestMapping("main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("system/auth/userPermissions");
		view.addObject("orgname", Constant.ORG_NAME);
		return view;
	}
	
}
