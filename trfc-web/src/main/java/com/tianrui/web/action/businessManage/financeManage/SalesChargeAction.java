package com.tianrui.web.action.businessManage.financeManage;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.businessManage.financeManage.ISalesChargeService;
import com.tianrui.api.req.businessManage.financeManage.SalesChargeQuery;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("/trfc/salescharge")
public class SalesChargeAction {
	
	private Logger log=LoggerFactory.getLogger(SalesChargeAction.class);
	
	@Autowired
	private ISalesChargeService salesChargeService;
	
	@RequestMapping("/main")
	public ModelAndView main(HttpSession session){
		ModelAndView view=new ModelAndView("businessManage/financeManage/salescharge");
		try {
			SystemUserResp user=(SystemUserResp) session.getAttribute("systemUser");
			view.addObject("user",user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
	@RequestMapping(value="/page",method=RequestMethod.POST)
	@ResponseBody
	public Result page(SalesChargeQuery query){
		Result result=Result.getSuccessResult();
		try {
			result=salesChargeService.page(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
}
