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
import com.tianrui.api.intf.basicFile.finance.ICustomerRemainderService;
import com.tianrui.api.req.BaseReq;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;

/**
 * 余额对接数据中心
 * @author xcy
 * @date 2017年11月23日
 */
@Controller
@RequestMapping("api/dc/customerRemainder")
public class ApiCustomerRemainderAction {
	private Logger log = LoggerFactory.getLogger(ApiCustomerRemainderAction.class);
	
	@Autowired
	private ICustomerRemainderService icustomerRemainderService;
	
	/**
	 * 获取最大时间戳
	 * xcy 
	 * @return ApiResult
	 * @date 2017年11月23日
	 */
	@RequestMapping(value="/getLastUTC",method=RequestMethod.POST)
	@ApiParamRawType(BaseReq.class)
	@ResponseBody
	public ApiResult getLastUTC(ApiParam<BaseReq> req){
		Result rs=Result.getErrorResult();
		BaseReq baseReq =req.getBody();
		try {
			rs = icustomerRemainderService.findMaxUtc(baseReq);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * 批量更新数据
	 * xcy 
	 * @return ApiResult
	 * @date 2017年11月23日
	 */
	@RequestMapping(value="/updateData",method=RequestMethod.POST)
	@ApiParamRawType(List.class)
	@ResponseBody
	public ApiResult updateData(ApiParam<List<JSONObject>> req){
		Result rs=Result.getErrorResult();
		List<JSONObject> list=req.getBody();
		try {
			rs = icustomerRemainderService.updateDataWithDC(list);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	
}
