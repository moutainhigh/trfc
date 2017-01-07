package com.tianrui.web.action.basicFile.measure;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.measure.IVehicleManageService;
import com.tianrui.api.req.basicFile.measure.VehicleManageReq;
import com.tianrui.api.req.basicFile.measure.VehicleSaveReq;
import com.tianrui.api.resp.basicFile.measure.VehicleManageResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;


@Controller
@RequestMapping("vehicle")
public class VehicleManageAction {
	
	private Logger log = LoggerFactory.getLogger(VehicleManageAction.class);
	
	@Autowired
	private IVehicleManageService vehicleManageService;
	
	@RequestMapping("main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("basicFile/measure/vehicle");
		return view;
	}
	

	@RequestMapping("page")
	@ResponseBody
	public Result page(VehicleManageReq req){
		Result result = Result.getSuccessResult();
		try {
			PaginationVO<VehicleManageResp> page = vehicleManageService.page(req);
			result.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
			return result;
		}
		return result;
	}
				
	@RequestMapping("addVehicle")
	@ResponseBody
	public Result addVehicle(VehicleSaveReq req){
		Result result = Result.getErrorResult();
		try {
			result = vehicleManageService.addVehicle(req);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("editVehicle")
	@ResponseBody
	public Result editVehicle(VehicleManageReq req){
		Result result = Result.getSuccessResult();
		try {
			int n = vehicleManageService.editVehicle(req);
			if(n > 0){
				result.setData(n);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("deleteVehicle")
	@ResponseBody
	public Result deleteVehicle(String id){
		Result result = Result.getSuccessResult();
		try {
			if(StringUtils.isNotBlank(id)){
				int n = vehicleManageService.deleteVehicle(id);
				if(n > 0){
					result.setData(n);
				}else{
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}else{
				result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
			return result;
		}
		return result;
	}
	
	@RequestMapping("delblacklist")
	@ResponseBody
	public Result delblacklist(VehicleManageReq req){
		Result result = Result.getSuccessResult();
		try {
			boolean flag = vehicleManageService.delblacklist(req);
			if(flag){
				result.setData(flag);
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
	
	@RequestMapping("addblacklist")
	@ResponseBody
	public Result addblacklist(VehicleManageReq req){
		Result result = Result.getSuccessResult();
		try {
			boolean flag = vehicleManageService.addblacklist(req);
			if(flag){
				result.setData(flag);
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
