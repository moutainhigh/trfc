package com.tianrui.api.intf.basicFile.measure;

import com.tianrui.api.req.basicFile.measure.VehicleManageQuery;
import com.tianrui.api.req.basicFile.measure.VehicleManageSave;

import java.util.List;

import com.tianrui.api.req.basicFile.measure.VehicleManageApi;
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

	PaginationVO<VehicleManageResp> page(VehicleManageQuery query) throws Exception;

	Result add(VehicleManageSave save) throws Exception;

	Result update(VehicleManageSave save) throws Exception;

	Result delete(VehicleManageQuery query);
	
	Result delblacklist(VehicleManageQuery query) throws Exception;

	Result addblacklist(VehicleManageQuery query) throws Exception;

	Result findListByParmas(VehicleManageQuery query) throws Exception;

	VehicleManageResp findOne(String id) throws Exception;
	
	Result addVehicleApi(VehicleManageApi vehicleSaveReq);

	Result vehicleCheck(VehicleManageApi vehicleManageApi);

	List<VehicleManageResp> autoCompleteSearch(String term) throws Exception;

}
