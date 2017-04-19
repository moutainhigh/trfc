package com.tianrui.api.intf.common;

import com.tianrui.api.req.businessManage.app.AppVersionReq;
import com.tianrui.smartfactory.common.vo.Result;

public interface IAppVersionService {

	Result validVersionApp(AppVersionReq req);
	
}