package com.tianrui.service.mapper.businessManage.financeManage;

import java.util.List;

import com.tianrui.service.bean.businessManage.financeManage.CustomerRemainder;

public interface CustomerRemainderMapper {
    int deleteByPrimaryKey(String id);

    int insert(CustomerRemainder record);
    
    int insertBatch(List<CustomerRemainder> list);

    int insertSelective(CustomerRemainder record);

    CustomerRemainder selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CustomerRemainder record);

    int updateByPrimaryKey(CustomerRemainder record);

    Long findMaxUtc(String orgId);

	List<String> queryIds();

}