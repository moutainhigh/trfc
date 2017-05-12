package com.tianrui.service.mapper.system.merchants;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianrui.api.req.system.merchants.SupplierGroupQuery;
import com.tianrui.api.resp.system.merchants.AppSupplierGroup;
import com.tianrui.api.resp.system.merchants.SupplierGroupResp;
import com.tianrui.service.bean.system.merchants.SupplierGroup;

public interface SupplierGroupMapper {
    int deleteByPrimaryKey(String id);

    int insert(SupplierGroup record);

    int insertSelective(SupplierGroup record);

    SupplierGroup selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SupplierGroup record);

    int updateByPrimaryKey(SupplierGroup record);
    
    List<SupplierGroupResp> selectSupplierGroupPage(SupplierGroupQuery query);
    
    long selectSupplierGroupPageCount(SupplierGroupQuery query);
    
    SupplierGroup validateSupplier(String supplierid);

	List<AppSupplierGroup> selectSupplierByGroupId(@Param("groupid")String groupid);
	
	int insertBatch(List<SupplierGroup> list);

	List<SupplierGroupResp> supplierGroupDetail(SupplierGroupQuery query);
    
}