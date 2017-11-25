package com.tianrui.web.api.datacenter.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.api.nc.imple.IOneBillOneCarService;
import com.tianrui.api.req.basicFile.nc.oneBillOneCar;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;

/**
 * @annotation 一车一单
 * @author zhanggaohao
 *
 */
@Controller
@RequestMapping("api/dc/oneBillOneCar")
public class ApiOneBillOneCarAction {
	
	private Logger log = LoggerFactory.getLogger(ApiOneBillOneCarAction.class);
	@Autowired
	private IOneBillOneCarService oneBillOneCarService;

	/**
	 * 自制一单一车审核通过回传
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/audit",method=RequestMethod.POST)
	@ApiParamRawType(oneBillOneCar.class)
	@ResponseBody
	public ApiResult auditCallBack(ApiParam<oneBillOneCar> req){
		Result rs = Result.getErrorResult();
		try {
			rs = oneBillOneCarService.auditCallBack(req.getBody());
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	/**
	 * 订单作废回传接口
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/validCallBack",method=RequestMethod.POST)
	@ApiParamRawType(oneBillOneCar.class)
	@ResponseBody
	public ApiResult validCallBack(ApiParam<oneBillOneCar> req){
		Result rs = Result.getErrorResult();
		try {
			rs = oneBillOneCarService.validCallBack(req.getBody());
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	
}
