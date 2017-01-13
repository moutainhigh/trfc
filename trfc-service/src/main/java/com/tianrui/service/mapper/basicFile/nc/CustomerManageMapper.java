package com.tianrui.service.mapper.basicFile.nc;

import java.util.List;

import com.tianrui.api.req.basicFile.nc.CustomerManageQuery;
import com.tianrui.service.bean.basicFile.nc.CustomerManage;

/**
 * 客户管理Mapper接口
 * @author zhanggaohao
 * @createtime 2016年12月27日 下午3:38:57
 * @classname CustomerManageMapper.java
 */
public interface CustomerManageMapper {
    int deleteByPrimaryKey(String id);

    int insert(CustomerManage record);

    int insertSelective(CustomerManage record);

    CustomerManage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CustomerManage record);

    int updateByPrimaryKey(CustomerManage record);

	List<CustomerManage> findCustomerPage(CustomerManageQuery query);

	long findCustomerPageCount(CustomerManageQuery query);
	
	List<CustomerManage> selectSelective(CustomerManage record);
}