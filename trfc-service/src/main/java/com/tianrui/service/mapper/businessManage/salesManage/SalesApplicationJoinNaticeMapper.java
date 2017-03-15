package com.tianrui.service.mapper.businessManage.salesManage;

import java.util.List;

import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationJoinNatice;

public interface SalesApplicationJoinNaticeMapper {
    int deleteByPrimaryKey(String id);

    int insert(SalesApplicationJoinNatice record);

    int insertSelective(SalesApplicationJoinNatice record);

    SalesApplicationJoinNatice selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SalesApplicationJoinNatice record);

    int updateByPrimaryKey(SalesApplicationJoinNatice record);

	int insertBatch(List<SalesApplicationJoinNatice> list);
}