package com.tianrui.web.api.handset.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.basicFile.nc.ISupplierManageService;
import com.tianrui.api.req.businessManage.handset.HandSetRequestParam;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;

@Controller
@RequestMapping("api/handset/static")
public class HandSetStaticAction {
	
	private Logger log = LoggerFactory.getLogger(HandSetStaticAction.class);
	
	@Autowired
	private ISupplierManageService supplierManageService;
	
	@RequestMapping(value="/supplier",method = RequestMethod.POST)
	@ApiParamRawType(HandSetRequestParam.class)
	@ResponseBody
	public ApiResult login(ApiParam<HandSetRequestParam> req){
		Result rs = Result.getErrorResult();
		try {
			rs = supplierManageService.HandSetQueryAll(req.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
	
}
