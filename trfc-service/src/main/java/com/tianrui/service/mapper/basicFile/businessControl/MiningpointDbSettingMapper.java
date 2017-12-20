package com.tianrui.service.mapper.basicFile.businessControl;

import com.tianrui.service.bean.basicFile.businessControl.MiningpointDbSetting;

public interface MiningpointDbSettingMapper {
    int deleteByPrimaryKey(String id);

    int insert(MiningpointDbSetting record);

    int insertSelective(MiningpointDbSetting record);

    MiningpointDbSetting selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MiningpointDbSetting record);

    int updateByPrimaryKey(MiningpointDbSetting record);
}