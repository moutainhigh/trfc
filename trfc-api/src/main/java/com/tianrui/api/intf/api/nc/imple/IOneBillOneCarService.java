package com.tianrui.api.intf.api.nc.imple;

import com.tianrui.api.req.basicFile.nc.oneBillOneCar;
import com.tianrui.smartfactory.common.vo.Result;

public interface IOneBillOneCarService {

	Result auditCallBack(oneBillOneCar req);

	Result validCallBack(oneBillOneCar body);
}

