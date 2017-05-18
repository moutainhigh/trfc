package com.tianrui.web.action.system.main;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.web.util.SessionManager;

@Controller
@RequestMapping("trfc/system")
public class SystemMainAction {
	
	//显示当前页
	@RequestMapping("/main")
	public ModelAndView main(HttpServletRequest request){
		ModelAndView view = new ModelAndView("system/main");
		SystemUserResp user = SessionManager.getSessionUser(request);
		view.addObject("name", user.getName());
		view.addObject("phone", user.getMobilePhone());
		view.addObject("nowTime", DateUtil.getNowDateString(DateUtil.Y_M_D_H_M_S));
		view.addObject("orgId", Constant.ORG_ID);
		view.addObject("orgId", Constant.ORG_ID);
		view.addObject("orgName", Constant.ORG_NAME);
		return view;
	}
}
