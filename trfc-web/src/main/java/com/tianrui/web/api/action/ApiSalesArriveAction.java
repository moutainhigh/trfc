package com.tianrui.web.api.action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.req.businessManage.salesManage.ApiSalesArriveQuery;
import com.tianrui.api.resp.businessManage.salesManage.ApiSalesArriveResp;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.smvc.ApiAuthValidation;
import com.tianrui.web.smvc.ApiParamRawType;


/**
 * 销售通知单
 * @author lixp 2017年1月7日 09:24:36
 *
 */
@Controller
@RequestMapping("api/piSalesArrive")
public class ApiSalesArriveAction {

	private Logger log = LoggerFactory.getLogger(ApiSalesArriveAction.class);
	

	/**
	 * 通知单详情
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/detail",method=RequestMethod.POST)
	@ApiParamRawType(ApiSalesArriveQuery.class)
	@ApiAuthValidation(callType="2")
	@ResponseBody
	public ApiResult detail(ApiParam<ApiSalesArriveQuery> req){
		Result rs=Result.getErrorResult();
		rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		rs.setData(getData());
		return ApiResult.valueOf(rs);
	}
	
	
	private ApiSalesArriveResp  getData(){
		ApiSalesArriveResp resp = new ApiSalesArriveResp();
		
		resp.setVehicleid("PK41233");
		resp.setVehicleno("京A12345");
		
		resp.setCustomerid("999999");
		
		resp.setMateriel("32.5水泥");
		resp.setServicetype("2");
		resp.setNumber("30");
		resp.setNotionformcode("XS201701110001");
		
		resp.setCementtype("1");
		resp.setBatchnumber("PS001");
		return resp;
	}
	
	
}
