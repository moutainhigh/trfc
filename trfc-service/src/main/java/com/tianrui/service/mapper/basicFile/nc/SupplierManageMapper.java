package com.tianrui.service.mapper.basicFile.nc;

import java.util.List;

import com.tianrui.api.req.basicFile.nc.SupplierManageQuery;
import com.tianrui.service.bean.basicFile.nc.SupplierManage;
/**
 * 供应商管理Mapper接口
 * @author zhanggaohao
 * @createtime 2016年12月16日 上午10:20:37
 * @classname SupplierManageMapper.java
 */
public interface SupplierManageMapper {
    int deleteByPrimaryKey(String id);

    int insert(SupplierManage record);

    int insertSelective(SupplierManage record);

    SupplierManage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SupplierManage record);

    int updateByPrimaryKey(SupplierManage record);
    
    List<SupplierManage> selectSelective(SupplierManage record);
    
    List<SupplierManage> findSupplierPage(SupplierManageQuery query);
    
    long findSupplierPageCount(SupplierManageQuery query);
    /**
     * 获取最大时间戳
     */
    Long findMaxUtc();
    /**
     * 批量添加
     */
    int insertBatch(List<SupplierManage> list);
}