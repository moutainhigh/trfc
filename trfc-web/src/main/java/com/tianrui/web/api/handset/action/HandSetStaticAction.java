package com.tianrui.web.api.handset.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.basicFile.measure.IYardManageService;
import com.tianrui.api.intf.basicFile.nc.ICustomerManageService;
import com.tianrui.api.intf.basicFile.nc.ISupplierManageService;
import com.tianrui.api.intf.basicFile.nc.IWarehouseManageService;
import com.tianrui.api.req.businessManage.handset.HandSetRequestParam;
import com.tianrui.api.resp.businessManage.handset.HandSetReturnResp;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.smvc.ApiNotTokenValidation;
import com.tianrui.web.smvc.ApiParamRawType;

@Controller
@RequestMapping("api/handset/static")
public class HandSetStaticAction {
	
	private Logger log = LoggerFactory.getLogger(HandSetStaticAction.class);
	
	@Autowired
	private ISupplierManageService supplierManageService;
	@Autowired
	private ICustomerManageService customerManageService;
	@Autowired
	private IWarehouseManageService warehouseManageService;
	@Autowired
	private IYardManageService yardManageService;

	@RequestMapping(value="/supplier",method = RequestMethod.POST)
	@ApiParamRawType(HandSetRequestParam.class)
	@ApiNotTokenValidation
	@ResponseBody
	public ApiResult supplier(ApiParam<HandSetRequestParam> req){
		Result rs = Result.getErrorResult();
		try {
			List<HandSetReturnResp> list = supplierManageService.handSetQueryAll(req.getBody());
			rs.setData(list);
			rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}

	@RequestMapping(value="/customer",method = RequestMethod.POST)
	@ApiParamRawType(HandSetRequestParam.class)
	@ApiNotTokenValidation
	@ResponseBody
	public ApiResult customer(ApiParam<HandSetRequestParam> req){
		Result rs = Result.getErrorResult();
		try {
			List<HandSetReturnResp> list = customerManageService.handSetQueryAll(req.getBody());
			rs.setData(list);
			rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}

	@RequestMapping(value="/warehouse",method = RequestMethod.POST)
	@ApiParamRawType(HandSetRequestParam.class)
	@ApiNotTokenValidation
	@ResponseBody
	public ApiResult warehouse(ApiParam<HandSetRequestParam> req){
		Result rs = Result.getErrorResult();
		try {
			List<HandSetReturnResp> list = warehouseManageService.handSetQueryAll(req.getBody());
			rs.setData(list);
			rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}

	@RequestMapping(value="/yard",method = RequestMethod.POST)
	@ApiParamRawType(HandSetRequestParam.class)
	@ApiNotTokenValidation
	@ResponseBody
	public ApiResult yard(ApiParam<HandSetRequestParam> req){
		Result rs = Result.getErrorResult();
		try {
			List<HandSetReturnResp> list = yardManageService.handSetQueryAll(req.getBody());
			rs.setData(list);
			rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
}
