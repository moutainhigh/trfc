package com.tianrui.service.mapper.businessManage.salesManage;

import java.util.List;

import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationArrive;

public interface SalesApplicationArriveMapper {
    int deleteByPrimaryKey(String id);

    int insert(SalesApplicationArrive record);

    int insertSelective(SalesApplicationArrive record);

    SalesApplicationArrive selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SalesApplicationArrive record);

    int updateByPrimaryKey(SalesApplicationArrive record);

	List<SalesApplicationArrive> listByNoticeId(String noticId);
}