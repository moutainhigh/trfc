package com.tianrui.service.mapper.basicFile.measure;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianrui.api.req.basicFile.measure.VehicleManageQuery;
import com.tianrui.api.req.businessManage.app.AppQueryReq;
import com.tianrui.api.resp.businessManage.app.AppVehicleResp;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;

public interface VehicleManageMapper {
    int deleteByPrimaryKey(String id);

    int insert(VehicleManage record);

    int insertSelective(VehicleManage record);

    VehicleManage selectByPrimaryKey(String id);
    
    VehicleManage selectByVehicleno(String vehicleno);
    
    List<VehicleManage> selectSelective(VehicleManage record);

    int updateByPrimaryKeySelective(VehicleManage record);

    int updateByPrimaryKey(VehicleManage record);

	long findVehiclePageCount(VehicleManageQuery query);

	List<VehicleManage> findVehiclePage(VehicleManageQuery query);

	List<VehicleManage> autoCompleteSearch(@Param("isCutover")String isCutover, @Param("likeName")String likeName, @Param("isBlack")String isBlack);

	VehicleManage getByVehicleNoAndRfid(@Param("vehicleno")String vehicleno, @Param("rfid")String rfid);

	long appQueryVehiclePageCount(AppQueryReq req);

	List<AppVehicleResp> appQueryVehiclePage(AppQueryReq req);

    VehicleManage  getVehicleByNo(String vehicleNo);
    
}