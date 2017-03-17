package com.tianrui.service.mapper.businessManage.salesManage;

import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationJoinPoundNote;

public interface SalesApplicationJoinPoundNoteMapper {
    int deleteByPrimaryKey(String id);

    int insert(SalesApplicationJoinPoundNote record);

    int insertSelective(SalesApplicationJoinPoundNote record);

    SalesApplicationJoinPoundNote selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SalesApplicationJoinPoundNote record);

    int updateByPrimaryKey(SalesApplicationJoinPoundNote record);
}