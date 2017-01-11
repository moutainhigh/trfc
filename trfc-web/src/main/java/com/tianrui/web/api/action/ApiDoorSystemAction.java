package com.tianrui.web.api.action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.req.businessManage.cardManage.VehicleCheckReq;
import com.tianrui.api.req.businessManage.salesManage.ApiDoorQueueQuery;
import com.tianrui.api.req.businessManage.salesManage.ApiDoorSystemSave;
import com.tianrui.api.req.common.RFIDReq;
import com.tianrui.api.resp.businessManage.salesManage.ApiDoorQueueResp;
import com.tianrui.api.resp.businessManage.salesManage.ApiSalesArriveResp;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.ApiResult;
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
	
	
	/**
	 * 门禁记录
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/record",method=RequestMethod.POST)
	@ApiParamRawType(ApiDoorSystemSave.class)
	@ApiAuthValidation(callType="2")
	@ResponseBody
	public ApiResult record(ApiParam<ApiDoorSystemSave> req){
		Result rs=Result.getSuccessResult();
		return ApiResult.valueOf(rs);
	}
	
	
	/**
	 * 出厂验证
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/leaveFactoryCheck",method=RequestMethod.POST)
	@ApiParamRawType(VehicleCheckReq.class)
	@ApiAuthValidation(callType="2")
	@ResponseBody
	public ApiResult leaveFactoryCheck(ApiParam<VehicleCheckReq> req){
		Result rs=Result.getSuccessResult();
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * 销售排队号
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/queueNumber",method=RequestMethod.POST)
	@ApiParamRawType(ApiDoorQueueQuery.class)
	@ApiAuthValidation(callType="2")
	@ResponseBody
	public ApiResult queueNumber(ApiParam<ApiDoorQueueQuery> req){
		Result rs=Result.getErrorResult();
		rs.setData(getData());
		return ApiResult.valueOf(rs);
	}
	
	private ApiDoorQueueResp getData(){
		ApiDoorQueueResp resp =new ApiDoorQueueResp();
		resp.setQueuenumber("1");
		resp.setSmallticket("0");
		return resp;
	}
	
}
