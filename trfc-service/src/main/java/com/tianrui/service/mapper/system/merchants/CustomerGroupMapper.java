package com.tianrui.service.mapper.system.merchants;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianrui.api.req.system.merchants.CustomerGroupQuery;
import com.tianrui.api.resp.system.merchants.AppCutoverGroup;
import com.tianrui.api.resp.system.merchants.CustomerGroupResp;
import com.tianrui.service.bean.system.merchants.CustomerGroup;

public interface CustomerGroupMapper {
    int deleteByPrimaryKey(String id);

    int insert(CustomerGroup record);

    int insertSelective(CustomerGroup record);

    CustomerGroup selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CustomerGroup record);

    int updateByPrimaryKey(CustomerGroup record);
    
    List<CustomerGroupResp> selectCustomerGroupPage(CustomerGroupQuery query);
    
    long selectCustomerGroupPageCount(CustomerGroupQuery query);
    
    CustomerGroup validateCustomer(String customerid);

	List<AppCutoverGroup> selectCustomerByGroupId(@Param("groupid")String groupid);
	
	int insertBatch(List<CustomerGroup> list);

	List<CustomerGroupResp> customerGroupDetail(CustomerGroupQuery query);
}