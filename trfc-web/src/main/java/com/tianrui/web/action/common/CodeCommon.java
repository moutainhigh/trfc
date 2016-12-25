package com.tianrui.web.action.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("/common/code")
public class CodeCommon {

	
	private Logger log = LoggerFactory.getLogger(CodeCommon.class);
	
	@RequestMapping("/vehicleCode")
	@ResponseBody
	public Result vehicleCode() throws Exception{
		Result result = Result.getSuccessResult();
		try {
			String code = "CL"+(int)(Math.random()*1000000);
			
			result.setData(code);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
			return result;
		}
		return result;
	}
}
