package com.tianrui.web.action.basicFile.other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.other.IOtherBdVehicleService;
import com.tianrui.api.req.basicFile.other.OtherBdVehicleReq;
import com.tianrui.api.resp.basicFile.other.OtherBdVehicleResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("otherVehicle")
public class OtherBdVehicleAction {
	
	private Logger log = LoggerFactory.getLogger(OtherBdVehicleAction.class);
	
	@Autowired
	private IOtherBdVehicleService otherBdVehicleService;
	
	@RequestMapping("main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("basicFile/other/other_car");
		return view;
	}
	
	@RequestMapping("page")
	@ResponseBody
	public Result page(OtherBdVehicleReq req){
		Result result = Result.getSuccessResult();
		try {
			PaginationVO<OtherBdVehicleResp> page = otherBdVehicleService.page(req);
			result.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
			return result;
		}
		return result;
	}
	
	
	@RequestMapping("toAddOtherVehicle")
	@ResponseBody
	public Result toAddOtherVehicle(){
		Result result = Result.getSuccessResult();
		try {
			OtherBdVehicleResp resp = new OtherBdVehicleResp();
			resp.setCode(otherBdVehicleService.getVehicleCode());
			resp.setInnercode(otherBdVehicleService.getVehicleInnercode());
			result.setData(resp);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
			return result;
		}
		return result;
	}
	
	
	@RequestMapping("addOtherVehicle")
	@ResponseBody
	public Result addOtherVehicle(OtherBdVehicleReq req){
		Result result = Result.getSuccessResult();
		try {
			int n = otherBdVehicleService.addVehicle(req);
			if(n > 0){
				result.setData(n);
			}else if(n == -1){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
			return result;
		}
		return result;
	}
	
	@RequestMapping("editOtherVehicle")
	@ResponseBody
	public Result editOtherVehicle(OtherBdVehicleReq req){
		Result result = Result.getSuccessResult();
		try {
			int n = otherBdVehicleService.editVehicle(req);
			if(n > 0){
				result.setData(n);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
			return result;
		}
		return result;
	}
	
	@RequestMapping("deleteOtherVehicle")
	@ResponseBody
	public Result deleteOtherVehicle(String id){
		Result result = Result.getSuccessResult();
		try {
			int n = otherBdVehicleService.deleteVehicle(id);
			if(n > 0){
				result.setData(n);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
			return result;
		}
		return result;
	}
	
	
}
