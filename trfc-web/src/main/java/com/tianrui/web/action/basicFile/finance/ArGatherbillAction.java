package com.tianrui.web.action.basicFile.finance;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.ResultMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sun.tools.internal.ws.processor.model.Request;
import com.tianrui.api.intf.basicFile.finance.IArGatherbillService;
import com.tianrui.api.req.basicFile.finance.ArGatherbillQuery;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.service.bean.basicFile.finance.ArGatherbill;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.SessionManager;
/**
 * 销售收款action
 * 
 * @author lenovo
 *
 */
@Controller
@RequestMapping("/trfc/arGatherbill")
public class ArGatherbillAction {

	private Logger log=LoggerFactory.getLogger(ArGatherbill.class);
	@Resource
	private IArGatherbillService arGatherbillService;
	
	
	@RequestMapping("/main")
	public ModelAndView main(HttpServletRequest request){
		ModelAndView view = new ModelAndView("basicFile/finance/ArGatherbill");
		SystemUserResp user = SessionManager.getSessionUser(request);
		view.addObject("user",user);
		return view;
	}
	
	@RequestMapping(value="/page",method=RequestMethod.POST)
	@ResponseBody
	public Result page(ArGatherbillQuery query,HttpServletRequest request){
		Result result=Result.getSuccessResult();
		try {

			if(query ==null){
				query =new ArGatherbillQuery();
			}
			SystemUserResp user = SessionManager.getSessionUser(request);
			query.setCurrId(user.getId());
			
			result=arGatherbillService.page(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
}
