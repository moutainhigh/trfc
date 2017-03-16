package com.tianrui.web.action.businessManage.financeManage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.businessManage.financeManage.ISalesDetailService;
import com.tianrui.api.req.businessManage.financeManage.SalesDetailQuery;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("/trfc/salesdetail")
public class SalesDetailAction {
	
	private Logger log=LoggerFactory.getLogger(SalesDetailAction.class);
	
	@Autowired
	private ISalesDetailService salesDetailService;
	
	@RequestMapping("/main")
	public ModelAndView main(){
		ModelAndView view=new ModelAndView("businessManage/financeManage/salesdetail");
		return view;
	}
	
	
	@RequestMapping("/page")
	@ResponseBody
	public Result page(SalesDetailQuery query){
		Result result=Result.getSuccessResult();
		try {
			result=salesDetailService.page(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
}
