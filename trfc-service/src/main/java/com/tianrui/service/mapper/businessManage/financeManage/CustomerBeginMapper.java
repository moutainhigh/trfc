package com.tianrui.service.mapper.businessManage.financeManage;

import java.util.List;

import com.tianrui.api.req.businessManage.financeManage.CustomerBeginQuery;
import com.tianrui.service.bean.businessManage.financeManage.CustomerBegin;

public interface CustomerBeginMapper {
    int deleteByPrimaryKey(String id);

    int insert(CustomerBegin record);

    int insertSelective(CustomerBegin record);

    CustomerBegin selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CustomerBegin record);

    int updateByPrimaryKey(CustomerBegin record);
    
    long findCustomerBeginPageCount(CustomerBeginQuery query);
    
    List<CustomerBegin> findCustomerBeginPage(CustomerBeginQuery query);
}