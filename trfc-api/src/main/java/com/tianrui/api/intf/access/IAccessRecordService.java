package com.tianrui.api.intf.access;

import com.tianrui.api.req.basicFile.measure.VehicleCheckApi;
import com.tianrui.api.req.businessManage.salesManage.ApiDoorSystemSave;
import com.tianrui.smartfactory.common.vo.Result;

public interface IAccessRecordService {
	/**
	 * 新增数据
	 */
	Result add(ApiDoorSystemSave api);
	/**
	 * 出厂校验并记录
	 * @param checkApi
	 * @return
	 */
	Result leaveFactoryCheckApi(VehicleCheckApi checkApi);
	/**
	 * @Description 入厂验证
	 * @author zhanggaohao
	 * @version 2017年3月2日 上午9:17:56
	 * @param checkApi
	 * @return
	 */
	Result enterFactoryCheckApi(VehicleCheckApi checkApi);
}
