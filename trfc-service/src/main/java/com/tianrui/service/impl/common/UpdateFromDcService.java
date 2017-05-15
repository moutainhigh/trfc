package com.tianrui.service.impl.common;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.common.IUpdateFromDcService;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.common.ApiParamUtils;
import com.tianrui.smartfactory.common.common.HttpUtils;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class UpdateFromDcService implements IUpdateFromDcService {

	@Override
	public Result updateFromDc(String type){
		Result result = Result.getParamErrorResult();
		if(StringUtils.isNotBlank(type)){
			ApiResult apiResult = HttpUtils.post(ApiParamUtils.getApiParam(type), Constant.URL_PULL_FROM_DC);
			if(apiResult != null && StringUtils.equals(apiResult.getCode(), Constant.SUCCESS)){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
}