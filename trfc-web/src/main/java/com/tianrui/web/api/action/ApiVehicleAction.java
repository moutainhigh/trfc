package com.tianrui.web.api.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.basicFile.measure.IVehicleManageService;
import com.tianrui.api.req.basicFile.measure.VehicleSaveReq;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.action.basicFile.measure.VehicleManageAction;
import com.tianrui.web.smvc.ApiParamRawType;

@Controller
@RequestMapping("api/vehicle")
public class ApiVehicleAction {

	private Logger log = LoggerFactory.getLogger(VehicleManageAction.class);
	
	@Autowired
	private IVehicleManageService vehicleManageService;
	
	@RequestMapping("addVehicle")
	@ApiParamRawType(VehicleSaveReq.class)
	@ResponseBody
	public ApiResult addVehicle(ApiParam<VehicleSaveReq> req){
		Result rs=Result.getErrorResult();
		VehicleSaveReq vehicleSaveReq =req.getBody();
		vehicleSaveReq.setCurrUid(req.getHead().getUserId());
		try {
			vehicleManageService.addVehicle(vehicleSaveReq);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
}