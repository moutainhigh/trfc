package com.tianrui.service.mapper.basicFile.measure;

import java.util.List;

import com.tianrui.api.req.basicFile.measure.VehicleManageQuery;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;

public interface VehicleManageMapper {
    int deleteByPrimaryKey(String id);

    int insert(VehicleManage record);

    int insertSelective(VehicleManage record);

    VehicleManage selectByPrimaryKey(String id);
    
    List<VehicleManage> selectSelective(VehicleManage record);

    int updateByPrimaryKeySelective(VehicleManage record);

    int updateByPrimaryKey(VehicleManage record);

	long findVehiclePageCount(VehicleManageQuery query);

	List<VehicleManage> findVehiclePage(VehicleManageQuery query);
    
}