package com.tianrui.api.intf.api.nc.imple;

import com.tianrui.api.req.basicFile.nc.oneBillOneCarReq;
import com.tianrui.smartfactory.common.vo.Result;

public interface IOneBillOneCarService {

	Result auditCallBack(oneBillOneCarReq req);

	Result validCallBack(oneBillOneCarReq body);
}

