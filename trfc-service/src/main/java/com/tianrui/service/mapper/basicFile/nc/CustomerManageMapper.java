package com.tianrui.service.mapper.basicFile.nc;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianrui.api.req.basicFile.nc.CustomerManageQuery;
import com.tianrui.api.req.businessManage.handset.HandSetRequestParam;
import com.tianrui.api.resp.businessManage.handset.HandSetReturnResp;
import com.tianrui.service.bean.basicFile.nc.CustomerManage;

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
	
	int insertBatch(List<CustomerManage> list);
    
	Long findMaxUtc();

	List<CustomerManage> autoCompleteSearch(@Param("likeName")String likeName);
	/**
	 * @Description 手持机获取客户集合接口
	 * @author zhanggaohao
	 * @version 2017年4月21日 下午2:48:41
	 * @param req
	 * @return
	 */
	List<HandSetReturnResp> handSetQueryAll(HandSetRequestParam req);
}