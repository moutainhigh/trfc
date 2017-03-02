package com.tianrui.service.mapper.quality.sales;

import java.util.List;

import com.tianrui.service.bean.quality.sales.AssayReportMsg;

public interface AssayReportMsgMapper {
    int deleteByPrimaryKey(String id);

    int insert(AssayReportMsg record);

    int insertSelective(AssayReportMsg record);

    AssayReportMsg selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AssayReportMsg record);

    int updateByPrimaryKey(AssayReportMsg record);
    /**
     * 通过报告id查询数据
     */
    List<AssayReportMsg> selectByReportid(String reportid);
}