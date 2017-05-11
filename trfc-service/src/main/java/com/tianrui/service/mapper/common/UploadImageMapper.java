package com.tianrui.service.mapper.common;

import com.tianrui.service.bean.common.UploadImage;

public interface UploadImageMapper {
    int deleteByPrimaryKey(String id);

    int insert(UploadImage record);

    int insertSelective(UploadImage record);

    UploadImage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UploadImage record);

    int updateByPrimaryKey(UploadImage record);
}