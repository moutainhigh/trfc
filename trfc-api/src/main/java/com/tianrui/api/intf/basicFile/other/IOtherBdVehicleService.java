package com.tianrui.api.intf.basicFile.other;

import com.tianrui.api.req.basicFile.other.OtherBdVehicleReq;
import com.tianrui.api.resp.basicFile.other.OtherBdVehicleResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 其他车辆Service接口
 * @author YangZhenFu
 */
public interface IOtherBdVehicleService {
	/*
	 * 分页查询数据
	 */
	Result page(OtherBdVehicleReq req);
	
	/*
	 * 新增其他车辆信息
	 */
	Result addVehicle(OtherBdVehicleReq req);
	
	/*
	 * 修改其他车辆信息
	 */
	Result editVehicle(OtherBdVehicleReq req);
	
	/*
	 * 删除其他车辆信息
	 */
	Result deleteVehicle(String id);
	
	/*
	 * 获取车辆编号
	 */
	String getVehicleCode();
	
	/*
	 * 获取车辆内码
	 */
	String getVehicleInnercode();
	
	/*   
	 * 检查名称
	 */
	Result checkName(String name);
}
