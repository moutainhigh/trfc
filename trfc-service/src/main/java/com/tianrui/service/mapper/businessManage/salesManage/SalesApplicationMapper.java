package com.tianrui.service.mapper.businessManage.salesManage;

import java.util.List;

import com.tianrui.api.req.businessManage.salesManage.SalesApplicationQuery;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplication;

public interface SalesApplicationMapper {
    int deleteByPrimaryKey(String id);

    int insert(SalesApplication record);

    int insertSelective(SalesApplication record);

    SalesApplication selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SalesApplication record);

    int updateByPrimaryKey(SalesApplication record);
    
    long findSalesApplicationPageCount(SalesApplicationQuery query);

	List<SalesApplication> findSalesApplicationPage(SalesApplicationQuery query);

	List<SalesApplication> selectSelective(SalesApplication record);
}