package com.tianrui.web.action.quality.purchase;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.quality.purchase.IPurchaseSamplingService;
import com.tianrui.api.req.quality.purchase.PurchaseSamplingReq;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("/trfc/quality/purchase/sampling")
public class PurchaseSamplingAction {
	Logger log = LoggerFactory.getLogger(PurchaseSamplingAction.class);
	
	@Resource
	private IPurchaseSamplingService purchaseSamplingService;
	
	@RequestMapping("/main")
	private ModelAndView main(){
		ModelAndView view = new ModelAndView("quality/purchase/sampling");
		return view;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Result add(PurchaseSamplingReq req){
		Result result=Result.getErrorResult();
		try {
			result = purchaseSamplingService.add(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}

	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result delete(PurchaseSamplingReq req){
		Result result=Result.getErrorResult();
		try {
			result = purchaseSamplingService.delete(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}

	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public Result update(PurchaseSamplingReq req){
		Result result=Result.getErrorResult();
		try {
			result = purchaseSamplingService.update(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}

	@RequestMapping(value="/page",method=RequestMethod.POST)
	@ResponseBody
	public Result page(PurchaseSamplingReq req){
		Result result=Result.getErrorResult();
		try {
			result = purchaseSamplingService.page(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	@RequestMapping(value="/getDetailData",method=RequestMethod.POST)
	@ResponseBody
	public Result getDetailData(PurchaseSamplingReq req){
		Result result=Result.getErrorResult();
		try {
			result = purchaseSamplingService.getDetailData(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	@RequestMapping(value="/findByCode",method=RequestMethod.POST)
	@ResponseBody
	public Result findByCode(PurchaseSamplingReq req){
		Result result=Result.getErrorResult();
		try {
			result = purchaseSamplingService.findByCode(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
}
