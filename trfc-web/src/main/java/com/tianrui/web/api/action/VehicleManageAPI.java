package com.tianrui.web.api.action;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.basicFile.measure.IVehicleManageService;
import com.tianrui.api.req.basicFile.measure.VehicleManageReq;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.action.basicFile.measure.VehicleManageAction;

@Controller
@RequestMapping("api/vehicle")
public class VehicleManageAPI {

	private Logger log = LoggerFactory.getLogger(VehicleManageAction.class);
	
	@Autowired
	private IVehicleManageService vehicleManageService;
	
	@RequestMapping("addVehicle")
	@ResponseBody
	public Result addVehicle(VehicleManageReq req){
		Result result = Result.getSuccessResult();
		try {
			if(StringUtils.isBlank(req.getRfid()) 
					|| StringUtils.isBlank(req.getVehicleno()) 
					|| StringUtils.isBlank(req.getOrgid())
					|| StringUtils.isBlank(req.getOrgname()) 
					|| StringUtils.isBlank(req.getCreator())){
				result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
				return result;
			}
			int n = vehicleManageService.addVehicle(req);
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
}