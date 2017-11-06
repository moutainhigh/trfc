package com.tianrui.service.mapper.quality.file;

import java.util.List;

import com.tianrui.service.bean.quality.file.QualityColumn;

public interface QualityColumnMapper {
    int deleteByPrimaryKey(String id);

    int insert(QualityColumn record);

    int insertSelective(QualityColumn record);

    QualityColumn selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(QualityColumn record);

    int updateByPrimaryKey(QualityColumn record);
    /**
     * 下拉框
     */
    List<QualityColumn> autoCompleteSearch(String type);
}