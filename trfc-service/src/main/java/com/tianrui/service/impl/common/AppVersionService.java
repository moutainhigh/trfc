package com.tianrui.service.impl.common;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.common.IAppVersionService;
import com.tianrui.api.req.businessManage.app.AppVersionReq;
import com.tianrui.api.resp.businessManage.app.AppVersionResp;
import com.tianrui.service.bean.common.AppVersion;
import com.tianrui.service.mapper.common.AppVersionMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class AppVersionService implements IAppVersionService{
	
	@Autowired
	private AppVersionMapper appVersionMapper;
	
	@Override
	public Result validVersionApp(AppVersionReq req){
		Result result = Result.getParamErrorResult();
		if(req != null
				&& StringUtils.isNotBlank(req.getPhoneType())
				&& StringUtils.isNotBlank(req.getCurrVersion())){
			AppVersion version = appVersionMapper.selectByPrimaryKey(req.getPhoneType());
			if(version != null){
				AppVersionResp resp = new AppVersionResp();
				if(!StringUtils.equals(req.getCurrVersion(), version.getCode())){
					resp.setUpdateFlag("1");
					resp.setUpdateUrl(version.getVersionurl());
				}else{
					resp.setUpdateFlag("0");
				}
				result.setData(resp);
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.APP_VERSION_EXIST);
			}
		}
		return result;
	}
	
}