package com.tianrui.api.intf.basicFile.measure;

import com.tianrui.api.req.basicFile.measure.VehicleManageQuery;
import com.tianrui.api.req.basicFile.measure.VehicleManageSave;
import com.tianrui.api.req.businessManage.app.AppQueryReq;

import java.util.List;

import com.tianrui.api.req.basicFile.measure.VehicleManageApi;
import com.tianrui.api.resp.basicFile.measure.VehicleManageResp;
import com.tianrui.api.resp.businessManage.app.AppVehicleResp;
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
	
	Result white(VehicleManageQuery query) throws Exception;

	Result black(VehicleManageQuery query) throws Exception;

	Result findListByParmas(VehicleManageQuery query) throws Exception;

	VehicleManageResp findOne(String id) throws Exception;
	
	Result addVehicleApi(VehicleManageApi vehicleManageApi) throws Exception;
	
	Result selectByVehicleNo(VehicleManageApi vehicleSaveReq) throws Exception;

	Result vehicleCheck(VehicleManageApi vehicleManageApi);

	List<VehicleManageResp> autoCompleteSearch(String isCutover, String likeName, String isBlack) throws Exception;
	/**
	 * @Description app车辆分页查询接口
	 * @author zhanggaohao
	 * @version 2017年4月18日 上午10:56:49
	 * @param req
	 * @return
	 */
	PaginationVO<AppVehicleResp> appToPage(AppQueryReq req);



}
