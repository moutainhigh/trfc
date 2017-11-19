package com.tianrui.service.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianrui.api.req.android.MyVehicleListParam;
import com.tianrui.api.resp.android.SearchListVo;
import com.tianrui.api.resp.android.UserVehicleVo;
import com.tianrui.service.bean.common.UserVehicle;

public interface UserVehicleMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserVehicle record);

    int insertSelective(UserVehicle record);

    UserVehicle selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserVehicle record);

    int updateByPrimaryKey(UserVehicle record);
    
    List<UserVehicleVo> listMyVehicleOrderNum(MyVehicleListParam param);
    
    long listMyVehicleOrderNumCount(MyVehicleListParam param);

    UserVehicle getByUIdAndVId(@Param("userId")String userId, @Param("vehicleId")String vehicleId);

	List<SearchListVo> listUserVehicle(MyVehicleListParam param);
}