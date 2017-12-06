package com.tianrui.web.api.subsystem.action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.businessManage.salesManage.ISalesArriveService;
import com.tianrui.api.req.businessManage.salesManage.ApiSalesArriveQuery;
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
	
	@Autowired
	private ISalesArriveService salesArriveService;
	

	/**
	 * 通知单详情
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/detail",method=RequestMethod.POST)
	@ApiParamRawType(ApiSalesArriveQuery.class)
	@ApiAuthValidation(callType="1,3,4")
	@ResponseBody
	public ApiResult detail(ApiParam<ApiSalesArriveQuery> req){
		Result rs=Result.getErrorResult();
		ApiSalesArriveQuery query = req.getBody();
		query.setCurrUid(req.getHead().getUserId());
		try {
			rs=salesArriveService.detailApi(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
	
}
