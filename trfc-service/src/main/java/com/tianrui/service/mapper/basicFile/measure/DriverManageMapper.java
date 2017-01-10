package com.tianrui.service.mapper.basicFile.measure;

import java.util.List;

import com.tianrui.api.req.basicFile.measure.DriverManageQuery;
import com.tianrui.service.bean.basicFile.measure.DriverManage;

public interface DriverManageMapper {
    int deleteByPrimaryKey(String id);

    int insert(DriverManage record);

    int insertSelective(DriverManage record);

    DriverManage selectByPrimaryKey(String id);
    
    List<DriverManage> selectSelective(DriverManage record);

    int updateByPrimaryKeySelective(DriverManage record);

    int updateByPrimaryKey(DriverManage record);

	long findDriverPageCount(DriverManageQuery query);

	List<DriverManage> findDriverPage(DriverManageQuery query);
	
}