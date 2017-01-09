package com.tianrui.api.intf.basicFile.other;

import com.tianrui.api.req.basicFile.other.OtherBdVehicleReq;
import com.tianrui.api.resp.basicFile.other.OtherBdVehicleResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;

/**
 * 其他车辆Service接口
 * @author YangZhenFu
 */
public interface IOtherBdVehicleService {
	PaginationVO<OtherBdVehicleResp> page(OtherBdVehicleReq req) throws Exception;
	
	int addVehicle(OtherBdVehicleReq req) throws Exception;

	int editVehicle(OtherBdVehicleReq req) throws Exception;

	int deleteVehicle(String id);

	String getVehicleCode();

	String getVehicleInnercode();
	
	boolean checkName(String name) throws Exception;
}
