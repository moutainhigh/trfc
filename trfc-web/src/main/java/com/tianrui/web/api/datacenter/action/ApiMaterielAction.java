package com.tianrui.web.api.datacenter.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.req.basicFile.nc.MaterielManageQuery;
import com.tianrui.service.impl.basicFile.nc.MaterielManageService;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;

/**
 * 物料对接数据中心
 * @author Yangzhenfu
 * @createtime 2017年1月18日 下午16:44:30
 */
public class ApiMaterielAction {
	private Logger log = LoggerFactory.getLogger(ApiCustomerAction.class);
	@Autowired
	MaterielManageService materielService;
	
	/**
	 * 获取最大时间戳
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/getLastUTC",method=RequestMethod.POST)
	@ApiParamRawType(MaterielManageQuery.class)
	@ResponseBody
	public ApiResult getLastUTC(ApiParam<MaterielManageQuery> req){
		Result rs=Result.getErrorResult();

		MaterielManageQuery cardApi =req.getBody();
		try {
			rs = materielService.findMaxUtc(cardApi);
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
			rs = materielService.updateDataWithDC(list);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	
}
