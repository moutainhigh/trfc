package com.tianrui.web.action.businessManage.financeManage;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.businessManage.financeManage.ISalesLedgerService;
import com.tianrui.api.req.businessManage.financeManage.SalesLedgerQuery;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.SessionManager;

@Controller
@RequestMapping("/trfc/salesledger")
public class SalesLedgerAction {

	private Logger log=LoggerFactory.getLogger(SalesLedgerAction.class);
	
	@Autowired
	private ISalesLedgerService salesLedgerService;
	
	@RequestMapping("/main")
	public ModelAndView main(HttpServletRequest request){
		ModelAndView view=new ModelAndView("businessManage/financeManage/salesledger");
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			view.addObject("user", user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
	
	@RequestMapping("/page")
	@ResponseBody
	public Result page(SalesLedgerQuery query){
		Result result=Result.getSuccessResult();
		try {
			result=salesLedgerService.page(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
}
