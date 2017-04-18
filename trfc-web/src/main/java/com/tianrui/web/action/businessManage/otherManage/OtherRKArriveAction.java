package com.tianrui.web.action.businessManage.otherManage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.businessManage.otherManage.IOtherRKArriveService;
import com.tianrui.api.req.businessManage.otherManage.OtherRKArriveReq;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("trfc/otherRKArrive")
public class OtherRKArriveAction {
	Logger logger = LoggerFactory.getLogger(OtherRKArriveAction.class);
	
	@Autowired
	private IOtherRKArriveService otherRKArriveService;
	
	@RequestMapping("main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("businessManage/logisticsManage/accessRecord");
		return view;
	}
	
	@RequestMapping("update")
	public Result update(OtherRKArriveReq req){
		Result rs = Result.getSuccessResult();
		try {
			rs = otherRKArriveService.update(req);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
	}
	
	@RequestMapping("add")
	public Result add(OtherRKArriveReq req){
		Result rs = Result.getSuccessResult();
		try {
			rs = otherRKArriveService.add(req);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
	}
	
	@RequestMapping("page")
	public Result page(OtherRKArriveReq req){
		Result rs = Result.getSuccessResult();
		try {
			rs = otherRKArriveService.page(req);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
	}
	
}
