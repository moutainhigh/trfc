package com.tianrui.web.api.subsystem.action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.other.selfsystem.ISelfSystemService;
import com.tianrui.api.req.basicFile.measure.VehicleCheckApi;
import com.tianrui.api.req.common.ApiQueryReq;
import com.tianrui.api.req.common.ApiSelfSystemQueryReq;
import com.tianrui.api.req.other.selfsystem.ApiSendCardQueryReq;
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
			//rs = accessRecordService.enterFactoryCheckApi(checkApi);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	
	
}
