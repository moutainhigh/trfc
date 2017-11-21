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
import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseApplicationService;
import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseStorageService;
import com.tianrui.api.req.businessManage.purchaseManage.PurchaseApplicationQuery;
import com.tianrui.api.req.businessManage.purchaseManage.PurchaseStorageUpReq;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;

/**
 * 采购订单对接数据中心
 * @author YangZhenFu 2017年2月23日 09:36:36
 *
 */
@Controller
@RequestMapping("api/dc/purchaseApplication")
public class ApiPurchaseApplicationAction {
	
	private Logger log=LoggerFactory.getLogger(ApiPurchaseApplicationAction.class);
	
	@Autowired
	private IPurchaseApplicationService purchaseApplicationService;
	@Autowired
	private IPurchaseStorageService purchaseStorageService;
	
	/**
	 * 获取最大时间戳
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/getLastUTC",method=RequestMethod.POST)
	@ApiParamRawType(PurchaseApplicationQuery.class)
	@ResponseBody
	public ApiResult getLastUTC(ApiParam<PurchaseApplicationQuery> req){
		Result rs=Result.getErrorResult();

		PurchaseApplicationQuery cardApi =req.getBody();
		try {
			rs = purchaseApplicationService.findMaxUtc(cardApi);
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
		try {
			List<JSONObject> list=req.getBody();
			rs = purchaseApplicationService.updateDataWithDC(list);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	
	
	
	/**
	 * 入库单回传
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/poundPushStatusUp",method=RequestMethod.POST)
	@ApiParamRawType(PurchaseStorageUpReq.class)
	@ResponseBody
	public ApiResult poundPushStatusUp(ApiParam<PurchaseStorageUpReq> req){
		Result rs=Result.getErrorResult();
		try {
			PurchaseStorageUpReq purchaseStorageUpReq=req.getBody();
			rs=purchaseStorageService.poundPushUp(purchaseStorageUpReq);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
	
		return ApiResult.valueOf(rs);
	}
	
}
