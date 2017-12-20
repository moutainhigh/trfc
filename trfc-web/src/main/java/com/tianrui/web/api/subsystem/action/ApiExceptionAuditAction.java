package com.tianrui.web.api.subsystem.action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.businessManage.examine.ITareWeightService;
import com.tianrui.api.req.businessManage.examine.ExceptionAuditReq;
import com.tianrui.api.req.businessManage.examine.ExceptionAuditSaveReq;
import com.tianrui.service.impl.businessManage.examine.ExceptionAuditService;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.smvc.ApiAuthValidation;
import com.tianrui.web.smvc.ApiParamRawType;


/**
 * 异常审批相关
 * @author lixp 2017年1月7日 09:24:36
 *
 */
@Controller
@RequestMapping("api/exceptionAudit")
public class ApiExceptionAuditAction {

	private Logger log = LoggerFactory.getLogger(ApiExceptionAuditAction.class);
	@Autowired
	ExceptionAuditService  exceptionAuditService;
	@Autowired
	ITareWeightService tareWeightService;
	/**
	 * 红外被挡申请
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/apply",method=RequestMethod.POST)
	@ApiParamRawType(ExceptionAuditSaveReq.class)
	@ApiAuthValidation(callType="3")
	@ResponseBody
	public ApiResult apply(ApiParam<ExceptionAuditSaveReq> req){
		ExceptionAuditSaveReq exceptionAuditSaveReq =req.getBody();
		exceptionAuditSaveReq.setCurrUid(req.getHead().getUserId());
		Result rs=Result.getErrorResult();
		try {
			rs = exceptionAuditService.apply(exceptionAuditSaveReq);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	/**
	 * 红外被挡申请查询
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/query",method=RequestMethod.POST)
	@ApiParamRawType(ExceptionAuditReq.class)
	@ApiAuthValidation(callType="3")
	@ResponseBody
	public ApiResult query(ApiParam<ExceptionAuditReq> req){
		ExceptionAuditReq exceptionAuditReq =req.getBody();
		exceptionAuditReq.setUserId(req.getHead().getUserId());
		Result rs=Result.getErrorResult();
		try {
			rs = exceptionAuditService.query(exceptionAuditReq);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	
	

	/**
	 * 皮重申请
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/tareWeightApply",method=RequestMethod.POST)
	@ApiParamRawType(ExceptionAuditSaveReq.class)
	@ApiAuthValidation(callType="3")
	@ResponseBody
	public ApiResult tareWeightApply(ApiParam<ExceptionAuditSaveReq> req){
		ExceptionAuditSaveReq exceptionAuditSaveReq =req.getBody();
		exceptionAuditSaveReq.setCurrUid(req.getHead().getUserId());
		Result rs=Result.getErrorResult();
		try {
			rs = tareWeightService.apply(exceptionAuditSaveReq);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	/**
	 * 皮重查询
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/tareWeightQuery",method=RequestMethod.POST)
	@ApiParamRawType(ExceptionAuditReq.class)
	@ApiAuthValidation(callType="3")
	@ResponseBody
	public ApiResult tareWeightQuery(ApiParam<ExceptionAuditReq> req){
		ExceptionAuditReq exceptionAuditReq =req.getBody();
		exceptionAuditReq.setUserId(req.getHead().getUserId());
		Result rs=Result.getErrorResult();
		try {
			rs = tareWeightService.query(exceptionAuditReq);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	
	
}
