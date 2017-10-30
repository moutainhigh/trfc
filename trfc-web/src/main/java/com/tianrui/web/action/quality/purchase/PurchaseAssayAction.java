package com.tianrui.web.action.quality.purchase;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.quality.purchase.IPurchaseAssayService;
import com.tianrui.api.req.quality.purchase.PurchaseAssayReq;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.SessionManager;

@Controller
@RequestMapping("/trfc/quality/purchase/assay")
public class PurchaseAssayAction {
	Logger log = LoggerFactory.getLogger(PurchaseAssayAction.class);
	@Autowired
	private IPurchaseAssayService purchaseAssayService;
	
	@RequestMapping("/main")
	private ModelAndView main(){
		ModelAndView view = new ModelAndView("quality/purchase/assay");
		return view;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Result add(PurchaseAssayReq req, HttpServletRequest request){
		Result result=Result.getErrorResult();
		try {
		    SystemUserResp user = SessionManager.getSessionUser(request);
		    req.setUser(user.getId());
			result = purchaseAssayService.add(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value="/page",method=RequestMethod.POST)
	@ResponseBody
	public Result page(PurchaseAssayReq req){
		Result result=Result.getErrorResult();
		try {
			result = purchaseAssayService.page(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Result update(PurchaseAssayReq req){
		Result result=Result.getErrorResult();
		try {
			result = purchaseAssayService.update(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result delete(PurchaseAssayReq req){
		Result result=Result.getErrorResult();
		try {
			result = purchaseAssayService.delete(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
}
