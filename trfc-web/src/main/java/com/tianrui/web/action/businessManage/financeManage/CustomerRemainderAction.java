package com.tianrui.web.action.businessManage.financeManage;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.SessionManager;

/**
 * 客户余额
 * @author xcy
 * @date 2017年11月25日
 */
@Controller
@RequestMapping("trfc/customerRemainder")
public class CustomerRemainderAction {

	private Logger log=LoggerFactory.getLogger(CustomerRemainderAction.class);
	
	@RequestMapping("main")
	public ModelAndView main(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		view.setViewName("businessManage/financeManage/customerRemainder");
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			view.addObject("user",user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
	@RequestMapping("page")
	public Result queryCustomerRemainder(){
		Result result = new Result();
		
		return result;
	}
}
