package com.tianrui.web.api.datacenter.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.req.basicFile.nc.SupplierManageQuery;
import com.tianrui.service.impl.basicFile.nc.SupplierManageService;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;

/**
 * 供应商对接数据中心
 */
@Controller
@RequestMapping("api/dc/supplier")
public class ApiSupplierAction {
	private Logger log = LoggerFactory.getLogger(ApiSupplierAction.class);
	@Autowired
	SupplierManageService supplierManageService;
	
	/**
	 * 获取最大时间戳
	 */
	@RequestMapping(value="/getLastUTC",method=RequestMethod.POST)
	@ApiParamRawType(SupplierManageQuery.class)
	@ResponseBody
	public ApiResult getLastUTC(ApiParam<SupplierManageQuery> req){
		Result rs = Result.getErrorResult();
		SupplierManageQuery query = req.getBody();
		try{
		rs = supplierManageService.findMaxUtc(query);
		}catch(Exception e){
			e.printStackTrace();
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	/**
	 * 批量更新数据
	 */
	@RequestMapping(value="/updateData",method=RequestMethod.POST)
	@ApiParamRawType(List.class)
	@ResponseBody
	public ApiResult updateData(ApiParam<List<JSONObject>> req){
		Result rs = Result.getErrorResult();
		List<JSONObject> list = req.getBody();
		try {
			rs = supplierManageService.updateDataWithDC(list);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	
}
