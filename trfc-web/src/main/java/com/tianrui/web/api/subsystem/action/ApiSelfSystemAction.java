package com.tianrui.web.api.subsystem.action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.other.selfsystem.ISelfSystemService;
import com.tianrui.api.req.other.selfsystem.ApiCheckRfidReq;
import com.tianrui.api.req.other.selfsystem.ApiInspectionReportQueryReq;
import com.tianrui.api.req.other.selfsystem.ApiPoundQueryReq;
import com.tianrui.api.req.other.selfsystem.ApiSendCardQueryReq;
import com.tianrui.api.req.other.selfsystem.ApiSendCardReq;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.smvc.ApiAuthValidation;
import com.tianrui.web.smvc.ApiParamRawType;


/**
 * 自助终端
 * @author lixp 2017年1月7日 09:24:36
 *
 */
@Controller
@RequestMapping("api/selfsystem")
public class ApiSelfSystemAction {

	private Logger log = LoggerFactory.getLogger(ApiSelfSystemAction.class);
	
	@Autowired
	ISelfSystemService  selfSystemService;
	
	/**
	 * 采购发卡
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/purchaseSendCard",method=RequestMethod.POST)
	@ApiParamRawType(ApiSendCardQueryReq.class)
	@ApiAuthValidation(callType="5")
	@ResponseBody
	public ApiResult purchaseSendCard(ApiParam<ApiSendCardQueryReq> req){
		ApiSendCardQueryReq checkApi = req.getBody();
		//checkApi.setCurrUid(req.getHead().getUserId());
		Result rs=Result.getErrorResult();
		try {
			rs = selfSystemService.purchaseSendCardQuery(checkApi);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * 销售发卡
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/saleSendCard",method=RequestMethod.POST)
	@ApiParamRawType(ApiSendCardQueryReq.class)
	@ApiAuthValidation(callType="5")
	@ResponseBody
	public ApiResult saleSendCard(ApiParam<ApiSendCardQueryReq> req){
		ApiSendCardQueryReq checkApi = req.getBody();
		//checkApi.setCurrUid(req.getHead().getUserId());
		Result rs=Result.getErrorResult();
		try {
			rs = selfSystemService.saleSendCardQuery(checkApi);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * 其他如发卡
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/otherEntrySendCardQuery",method=RequestMethod.POST)
	@ApiParamRawType(ApiSendCardQueryReq.class)
	@ApiAuthValidation(callType="5")
	@ResponseBody
	public ApiResult otherEntrySendCardQuery(ApiParam<ApiSendCardQueryReq> req){
		ApiSendCardQueryReq checkApi = req.getBody();
		//checkApi.setCurrUid(req.getHead().getUserId());
		Result rs=Result.getErrorResult();
		try {
			rs = selfSystemService.otherEntrySendCardQuery(checkApi);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * 其他出发卡
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/otherExitSendCardQuery",method=RequestMethod.POST)
	@ApiParamRawType(ApiSendCardQueryReq.class)
	@ApiAuthValidation(callType="5")
	@ResponseBody
	public ApiResult otherExitSendCardQuery(ApiParam<ApiSendCardQueryReq> req){
		ApiSendCardQueryReq checkApi = req.getBody();
		//checkApi.setCurrUid(req.getHead().getUserId());
		Result rs=Result.getErrorResult();
		try {
			rs = selfSystemService.otherExitSendCardQuery(checkApi);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	/**
	 * 发卡操作
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/sendCard",method=RequestMethod.POST)
	@ApiParamRawType(ApiSendCardReq.class)
	@ApiAuthValidation(callType="5")
	@ResponseBody
	public ApiResult sendCard(ApiParam<ApiSendCardReq> req){
		ApiSendCardReq checkApi = req.getBody();
		//checkApi.setCurrUid(req.getHead().getUserId());
		Result rs=Result.getErrorResult();
		try {
			rs = selfSystemService.sendCard(checkApi);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	/**
	 * 榜单查询
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/poundQuery",method=RequestMethod.POST)
	@ApiParamRawType(ApiPoundQueryReq.class)
	@ApiAuthValidation(callType="5")
	@ResponseBody
	public ApiResult poundQuery(ApiParam<ApiPoundQueryReq> req){
		ApiPoundQueryReq checkApi = req.getBody();
		//checkApi.setCurrUid(req.getHead().getUserId());
		Result rs=Result.getErrorResult();
		try {
			rs = selfSystemService.poundQuery(checkApi);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	/**
	 * 质检榜单打印操作
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/inspectionReportPrint",method=RequestMethod.POST)
	@ApiParamRawType(ApiInspectionReportQueryReq.class)
	@ApiAuthValidation(callType="5")
	@ResponseBody
	public ApiResult inspectionReportPrint(ApiParam<ApiInspectionReportQueryReq> req){
		ApiInspectionReportQueryReq checkApi = req.getBody();
		//checkApi.setCurrUid(req.getHead().getUserId());
		Result rs=Result.getErrorResult();
		try {
			rs = selfSystemService.inspectionReportPrint(checkApi);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	/**
	 * 质检榜单打印记录查询
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/inspectionReportPrintQuery",method=RequestMethod.POST)
	@ApiParamRawType(ApiInspectionReportQueryReq.class)
	@ApiAuthValidation(callType="5")
	@ResponseBody
	public ApiResult inspectionReportPrintQuery(ApiParam<ApiInspectionReportQueryReq> req){
		ApiInspectionReportQueryReq checkApi = req.getBody();
		//checkApi.setCurrUid(req.getHead().getUserId());
		Result rs=Result.getErrorResult();
		try {
			rs = selfSystemService.inspectionReportPrintQuery(checkApi);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	/**
	 * 质检榜单查询
	 * TODO 质检项需要分类查询
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/inspectionReportQuery",method=RequestMethod.POST)
	@ApiParamRawType(ApiInspectionReportQueryReq.class)
	@ApiAuthValidation(callType="5")
	@ResponseBody
	public ApiResult inspectionReportQuery(ApiParam<ApiInspectionReportQueryReq> req){
		ApiInspectionReportQueryReq checkApi = req.getBody();
		//checkApi.setCurrUid(req.getHead().getUserId());
		Result rs=Result.getErrorResult();
		try {
			rs = selfSystemService.inspectionReportQuery(checkApi);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	/**
	 * 符合卡验证查询
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/checkCompositecard",method=RequestMethod.POST)
	@ApiParamRawType(ApiCheckRfidReq.class)
	@ApiAuthValidation(callType="5")
	@ResponseBody
	public ApiResult checkCompositecard(ApiParam<ApiCheckRfidReq> req){
		ApiCheckRfidReq checkApi = req.getBody();
		//checkApi.setCurrUid(req.getHead().getUserId());
		Result rs=Result.getErrorResult();
		try {
			rs = selfSystemService.checkCompositecard(checkApi);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	
	
}
