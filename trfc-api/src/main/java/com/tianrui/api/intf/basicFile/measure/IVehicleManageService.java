package com.tianrui.api.intf.basicFile.measure;

import com.tianrui.api.req.basicFile.measure.VehicleManageReq;
import com.tianrui.api.req.basicFile.measure.VehicleSaveReq;
import com.tianrui.api.resp.basicFile.measure.VehicleManageResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 车辆管理Service接口
 * @author zhanggaohao
 * @createtime 2016年12月20日 下午4:23:38
 * @classname IVehicleManageService.java
 */
public interface IVehicleManageService {

	PaginationVO<VehicleManageResp> page(VehicleManageReq req) throws Exception;

	Result addVehicle(VehicleSaveReq req) throws Exception;

	int editVehicle(VehicleManageReq req) throws Exception;

	int deleteVehicle(String id);

	boolean delblacklist(VehicleManageReq req) throws Exception;

	boolean addblacklist(VehicleManageReq req) throws Exception;
	
}
