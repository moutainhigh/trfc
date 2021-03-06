package com.tianrui.web.api.subsystem.action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.intf.businessManage.logisticsManage.IAccessRecordService;
import com.tianrui.api.intf.businessManage.salesManage.ISalesArriveService;
import com.tianrui.api.intf.common.IQueueNumberService;
import com.tianrui.api.req.basicFile.measure.VehicleCheckApi;
import com.tianrui.api.req.businessManage.salesManage.ApiDoorQueueQuery;
import com.tianrui.api.req.businessManage.salesManage.ApiDoorSystemSave;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.smvc.ApiAuthValidation;
import com.tianrui.web.smvc.ApiParamRawType;


/**
 * 门禁相关
 * @author lixp 2017年1月7日 09:24:36
 *
 */
@Controller
@RequestMapping("api/doorSystem")
public class ApiDoorSystemAction {

	private Logger log = LoggerFactory.getLogger(ApiDoorSystemAction.class);
	
	@Autowired
	private IAccessRecordService accessRecordService;
	
	@Autowired
	private ISalesArriveService salesArriveService;
	
	@Autowired
	private IQueueNumberService queueNumberService;
	
	/**
	 * @annotation 上传入厂门禁（新）
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/uploadInfoAR",method=RequestMethod.POST)
	@ApiParamRawType(ApiDoorSystemSave.class)
	@ApiAuthValidation(callType="1")
	@ResponseBody
	public ApiResult uploadInfoAR(ApiParam<ApiDoorSystemSave> req){
		ApiDoorSystemSave apiDoor = req.getBody();
		apiDoor.setCurrUid(req.getHead().getUserId());
		Result rs=Result.getErrorResult();
		try {
			LoggerFactory.getLogger("accessRecord").info("通知单号{},上传入厂门禁参数:{}", apiDoor.getNotionformcode(), JSON.toJSONString(apiDoor));
			rs = accessRecordService.uploadInfoAccessRecord(apiDoor);
			LoggerFactory.getLogger("accessRecord").info("通知单号{},上传入厂门禁返回结果:{}", apiDoor.getNotionformcode(), JSON.toJSONString(rs));
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * 门禁记录
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/record",method=RequestMethod.POST)
	@ApiParamRawType(ApiDoorSystemSave.class)
	@ApiAuthValidation(callType="1")
	@ResponseBody
	public ApiResult record(ApiParam<ApiDoorSystemSave> req){
		ApiDoorSystemSave apiDoor = req.getBody();
		apiDoor.setCurrUid(req.getHead().getUserId());
		Result rs=Result.getErrorResult();
		try {
			LoggerFactory.getLogger("accessRecord").info("通知单号{},上传门禁参数:{}", apiDoor.getNotionformcode(), JSON.toJSONString(apiDoor));
			rs = accessRecordService.addAccessRecord(apiDoor);
			LoggerFactory.getLogger("accessRecord").info("通知单号{},上传门禁返回结果:{}", apiDoor.getNotionformcode(), JSON.toJSONString(rs));
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * 入厂验证
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/enterFactoryCheck",method=RequestMethod.POST)
	@ApiParamRawType(VehicleCheckApi.class)
	@ApiAuthValidation(callType="1")
	@ResponseBody
	public ApiResult enterFactoryCheck(ApiParam<VehicleCheckApi> req){
		VehicleCheckApi checkApi = req.getBody();
		checkApi.setCurrUid(req.getHead().getUserId());
		Result rs=Result.getErrorResult();
		try {
			rs = accessRecordService.enterFactoryCheckApi(checkApi);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * 出厂验证
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/leaveFactoryCheck",method=RequestMethod.POST)
	@ApiParamRawType(VehicleCheckApi.class)
	@ApiAuthValidation(callType="1")
	@ResponseBody
	public ApiResult leaveFactoryCheck(ApiParam<VehicleCheckApi> req){
		VehicleCheckApi checkApi = req.getBody();
		checkApi.setCurrUid(req.getHead().getUserId());
		Result rs=Result.getErrorResult();
		try {
			rs = accessRecordService.leaveFactoryCheckApi(checkApi);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * 销售排队号
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/queueNumber",method=RequestMethod.POST)
	@ApiParamRawType(ApiDoorQueueQuery.class)
	@ApiAuthValidation(callType="1")
	@ResponseBody
	public ApiResult queueNumber(ApiParam<ApiDoorQueueQuery> req){
		ApiDoorQueueQuery queue = req.getBody();
		queue.setCurrUid(req.getHead().getUserId());
		Result rs=Result.getErrorResult();
		try {
			rs = salesArriveService.selectWaitingNumber(queue);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * 袋装排队接口
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/queryWaiting",method=RequestMethod.POST)
	@ApiParamRawType(ApiDoorQueueQuery.class)
	@ApiAuthValidation(callType="4")
	@ResponseBody
	public ApiResult queryWaiting(ApiParam<ApiDoorQueueQuery> req){
		ApiDoorQueueQuery queue = req.getBody();
		queue.setCurrUid(req.getHead().getUserId());
		Result rs=Result.getErrorResult();
		try {
			rs = queueNumberService.queryWaiting();
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	
}
