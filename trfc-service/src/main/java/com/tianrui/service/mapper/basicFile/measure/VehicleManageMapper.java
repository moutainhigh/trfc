package com.tianrui.service.mapper.basicFile.measure;

import java.util.List;

import com.tianrui.api.req.basicFile.measure.VehicleManageReq;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;

public interface VehicleManageMapper {
    int deleteByPrimaryKey(String id);

    int insert(VehicleManage record);

    int insertSelective(VehicleManage record);

    VehicleManage selectByPrimaryKey(String id);
    
    List<VehicleManage> selectSelective(VehicleManage record);

    int updateByPrimaryKeySelective(VehicleManage record);

    int updateByPrimaryKey(VehicleManage record);

	long findVehiclePageCount(VehicleManageReq req);

	List<VehicleManage> findVehiclePage(VehicleManageReq req);
    
}