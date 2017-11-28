package com.tianrui.web.action.businessManage.financeManage;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.businessManage.financeManage.ISalesDetailService;
import com.tianrui.api.req.basicFile.finance.ArGatherbillQuery;
import com.tianrui.api.req.businessManage.financeManage.SalesDetailQuery;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.SessionManager;

@Controller
@RequestMapping("/trfc/salesdetail")
public class SalesDetailAction {
	
	private Logger log=LoggerFactory.getLogger(SalesDetailAction.class);
	
	@Autowired
	private ISalesDetailService salesDetailService;
	
	@RequestMapping("/main")
	public ModelAndView main(HttpServletRequest request){
		ModelAndView view=new ModelAndView("businessManage/financeManage/salesdetail");
		SystemUserResp user = SessionManager.getSessionUser(request);
		view.addObject("user",user);
		return view;
	}
	
	/**
	 * 分页查询
	 * @param query
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/page",method=RequestMethod.POST)
	@ResponseBody
	public Result page(SalesDetailQuery query,HttpServletRequest request){
		Result result=Result.getSuccessResult();
		try {
			if(query ==null){
				query =new SalesDetailQuery();
			}
			SystemUserResp user = SessionManager.getSessionUser(request);
			query.setCurrId(user.getId());
			result=salesDetailService.page(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
}
