package com.tianrui.service.mapper.basicFile.businessControl;

import com.tianrui.service.bean.basicFile.businessControl.PrimarySetting;

public interface PrimarySettingMapper {
    int deleteByPrimaryKey(String id);

    int insert(PrimarySetting record);

    int insertSelective(PrimarySetting record);

    PrimarySetting selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PrimarySetting record);

    int updateByPrimaryKey(PrimarySetting record);
}