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
import com.tianrui.api.req.basicFile.nc.WarehouseManageQuery;
import com.tianrui.service.impl.basicFile.nc.WarehouseManageService;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;


/**
 * 仓库对接数据中心
 * @author lixp 2017年1月7日 09:24:36
 *
 */
@Controller
@RequestMapping("api/dc/warehouse")
public class ApiWarehouseAction {

	private Logger log = LoggerFactory.getLogger(ApiWarehouseAction.class);
	@Autowired
	WarehouseManageService warehouseService;
	
	/**
	 * 获取最大时间戳
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/getLastUTC",method=RequestMethod.POST)
	@ApiParamRawType(WarehouseManageQuery.class)
	@ResponseBody
	public ApiResult getLastUTC(ApiParam<WarehouseManageQuery> req){
		Result rs=Result.getErrorResult();

		WarehouseManageQuery cardApi =req.getBody();
		try {
			rs = warehouseService.findMaxUtc(cardApi);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * 批量更新数据
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/updateData",method=RequestMethod.POST)
	@ApiParamRawType(List.class)
	@ResponseBody
	public ApiResult updateData(ApiParam<List<JSONObject>> req){
		Result rs=Result.getErrorResult();

		List<JSONObject> list=req.getBody();
		try {
			rs = warehouseService.updateDataWithDC(list);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	
	
}
