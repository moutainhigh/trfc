package com.tianrui.web.api.datacenter.action;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.intf.businessManage.salesManage.ISalesApplicationService;
import com.tianrui.api.req.businessManage.salesManage.SalesApplicationQuery;
import com.tianrui.api.req.dc.BillValidReq;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;


/**
 * 销售订单对接数据中心
 * @author lixp 2017年1月7日 09:24:36
 *
 */
@Controller
@RequestMapping("api/dc/saleAppliaction")
public class ApiSaleApplicationAction {

	private Logger log = LoggerFactory.getLogger(ApiSaleApplicationAction.class);
	@Autowired
	private ISalesApplicationService salesApplicationService;
	
	/**
	 * 获取最大时间戳
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/getLastUTC",method=RequestMethod.POST)
	@ApiParamRawType(SalesApplicationQuery.class)
	@ResponseBody
	public ApiResult getLastUTC(ApiParam<SalesApplicationQuery> req){
		Result rs=Result.getErrorResult();

		SalesApplicationQuery cardApi =req.getBody();
		try {
			rs = salesApplicationService.findMaxUtc(cardApi);
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
			rs = salesApplicationService.updateDataWithDC(list);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}

	/**
	 * NC销售订单同步厂区
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/writeBack",method=RequestMethod.POST)
	@ApiParamRawType(List.class)
	@ResponseBody
	public ApiResult writeBack(JSONArray req){
		Result rs=Result.getErrorResult();
		try {
//			rs = salesApplicationService.updateDataWithNC(req.getBody());
			System.out.println(JSON.toJSONString(req));
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}

	/**
	 * 自制订单审批回写
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/bill/audit/callback",method=RequestMethod.POST)
	@ApiParamRawType(BillValidReq.class)
	@ResponseBody
	public ApiResult auditCallBack(ApiParam<BillValidReq> req){
		Result rs = Result.getErrorResult();
		try {
			rs = salesApplicationService.billAuditCallBack(req.getBody());
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}

//	/**
//	 * 一车一单作废回写（未审核）
//	 * @param req
//	 * @return
//	 */
//	@RequestMapping(value="/bill/notAudit/valid",method=RequestMethod.POST)
//	@ApiParamRawType(BillValidReq.class)
//	@ResponseBody
//	public ApiResult billNotAuditValid(ApiParam<BillValidReq> req){
//		Result rs = Result.getErrorResult();
//		try {
//			rs = salesApplicationService.billNotAuditValid(req.getBody());
//		} catch (Exception e) {
//			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
//			log.error(e.getMessage(),e);
//		}
//		return ApiResult.valueOf(rs);
//	}
	
	/**
	 * 一车一单作废回写（已审核）
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/bill/yesAudit/valid",method=RequestMethod.POST)
	@ApiParamRawType(BillValidReq.class)
	@ResponseBody
	public ApiResult billYesAuditValid(ApiParam<BillValidReq> req){
		Result rs = Result.getErrorResult();
		try {
			rs = salesApplicationService.billYesAuditValid(req.getBody());
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}

	/**
	 * NC自制实时推送到FC
	 * @author xcy
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/bill/nc/save",method=RequestMethod.POST)
	@ApiParamRawType(JSONArray.class)
	@ResponseBody
	public ApiResult pushSalesTofc(ApiParam<JSONArray> req){
		Result rs = Result.getErrorResult();
		JSONArray array = req.getBody();
		try {
			rs = salesApplicationService.pushSalesTofc(array);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	
}
