package com.tianrui.service.mapper.businessManage.financeManage;

import java.util.List;

import com.tianrui.api.req.businessManage.financeManage.CustomerRemainderQuery;
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

	/**
	 * 查询数据的总条数
	 * xcy 
	 * @return Long
	 * @date 2017年11月27日
	 */
	Long findCustomerRemainderPageCount(CustomerRemainderQuery crQuery);

	/**
	 * 分页查询数据
	 * xcy 
	 * @return List<CustomerRemainder>
	 * @date 2017年11月27日
	 */
	List<CustomerRemainder> findCustomerRemainderPage(CustomerRemainderQuery crQuery);

}