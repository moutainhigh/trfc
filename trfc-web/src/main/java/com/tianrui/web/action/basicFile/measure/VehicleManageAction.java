package com.tianrui.web.action.basicFile.measure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.measure.IVehicleManageService;
import com.tianrui.api.req.basicFile.measure.VehicleManageQuery;
import com.tianrui.api.req.basicFile.measure.VehicleManageSave;
import com.tianrui.api.resp.basicFile.measure.VehicleManageResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;


@Controller
@RequestMapping("/trfc/vehicle")
public class VehicleManageAction {
	
	private Logger log = LoggerFactory.getLogger(VehicleManageAction.class);
	
	@Autowired
	private IVehicleManageService vehicleManageService;
	
	@RequestMapping("/main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("basicFile/measure/vehicle");
		return view;
	}
	

	@RequestMapping("/page")
	@ResponseBody
	public Result page(VehicleManageQuery query){
		Result result = Result.getSuccessResult();
		try {
			PaginationVO<VehicleManageResp> page = vehicleManageService.page(query);
			result.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
				
	@RequestMapping("/addVehicle")
	@ResponseBody
	public Result addVehicle(VehicleManageSave save){
		Result result = Result.getErrorResult();
		try {
			result = vehicleManageService.addVehicle(save);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/editVehicle")
	@ResponseBody
	public Result editVehicle(VehicleManageSave save){
		Result result = Result.getSuccessResult();
		try {
			result = vehicleManageService.editVehicle(save);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/deleteVehicle")
	@ResponseBody
	public Result deleteVehicle(VehicleManageQuery query){
		Result result = Result.getSuccessResult();
		try {
			result = vehicleManageService.deleteVehicle(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/delblacklist")
	@ResponseBody
	public Result delblacklist(VehicleManageQuery query){
		Result result = Result.getSuccessResult();
		try {
			result = vehicleManageService.delblacklist(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/addblacklist")
	@ResponseBody
	public Result addblacklist(VehicleManageQuery query){
		Result result = Result.getSuccessResult();
		try {
			result = vehicleManageService.addblacklist(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
}
