package com.tianrui.service.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianrui.api.req.android.MyVehicleListParam;
import com.tianrui.api.resp.android.SearchListVo;
import com.tianrui.api.resp.android.UserDriverVo;
import com.tianrui.service.bean.common.UserDriver;

public interface UserDriverMapper {
    int deleteByPrimaryKey(String id);

    int insert(UserDriver record);

    int insertSelective(UserDriver record);

    UserDriver selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserDriver record);

    int updateByPrimaryKey(UserDriver record);
    
    List<UserDriverVo> listMyDriverOrderNum(MyVehicleListParam param);

	long listMyDriverOrderNumCount(MyVehicleListParam param);

	UserDriver getByUIdAndDId(@Param("userId")String userId, @Param("driverId")String driverId);

	List<SearchListVo> listUserDriver(MyVehicleListParam param);
}