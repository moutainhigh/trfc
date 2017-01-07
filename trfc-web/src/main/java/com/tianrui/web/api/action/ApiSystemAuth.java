package com.tianrui.web.api.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.system.auth.ISystemUserService;
import com.tianrui.api.req.system.auth.UserReq;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;


/**
 * 用户验证相关
 * @author lixp 2017年1月7日 09:24:36
 *
 */
@Controller
@RequestMapping("api/system")
public class ApiSystemAuth {

	private Logger log = LoggerFactory.getLogger(ApiSystemAuth.class);
	
	@Autowired
	ISystemUserService systemUserService;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ApiParamRawType(UserReq.class)
	//@ApiTokenValidation
	@ResponseBody
	public ApiResult login(ApiParam<UserReq> req){
		UserReq userReq =req.getBody();
		Result rs=Result.getErrorResult();
		try {
			rs = systemUserService.apiLogin(userReq);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
}
