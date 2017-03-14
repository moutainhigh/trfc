package com.tianrui.service.mapper.businessManage.financeManage;

import java.util.List;

import com.tianrui.api.req.businessManage.financeManage.CustomerBackQuery;
import com.tianrui.service.bean.businessManage.financeManage.CustomerBack;

public interface CustomerBackMapper {
    int deleteByPrimaryKey(String id);

    int insert(CustomerBack record);

    int insertSelective(CustomerBack record);

    CustomerBack selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CustomerBack record);

    int updateByPrimaryKey(CustomerBack record);
    
    long findCustomerBackPageCount(CustomerBackQuery query);
    
    List<CustomerBack> findCustomerBackPage(CustomerBackQuery query);

   	List<CustomerBack> selectSelective(CustomerBack record);
}