package com.tianrui.service.impl.system.auth;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.system.auth.ISystemUserService;
import com.tianrui.api.req.system.auth.UserReq;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.Md5Utils;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class SystemUserService implements ISystemUserService {

	@Override
	public Result apiLogin(UserReq req) throws Exception {
		Result rs =Result.getParamErrorResult();
		if(req!=null && StringUtils.isNotBlank(req.getPswd()) && StringUtils.isNotBlank(req.getAccount())){
			if( StringUtils.equals(req.getAccount(), "test") ){
				if( StringUtils.equals(req.getPswd(), Md5Utils.MD5("123456")) ){
					SystemUserResp userResp = new SystemUserResp();
					userResp.setId("9999999999");
					userResp.setName("测试员金鑫");
					rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
					rs.setData(userResp);
				}else{
					rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR2);
				}
			}else{
				rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR1);
			}
		}
		
		return rs;
	}
	
}