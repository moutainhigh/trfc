package com.tianrui.service.mapper.quality.sales.batchnum;

import java.util.List;

import com.tianrui.api.req.quality.sales.batchnum.SalesBatchnumReq;
import com.tianrui.service.bean.quality.sales.batchnum.SalesBatchnum;

public interface SalesBatchnumMapper {
	/**
	 * 删除数据(主键id)
	 */
    int deleteByPrimaryKey(String id);
    /**
     * 新增数据
     */
    int insert(SalesBatchnum record);
    /**
     * 新增数据(动态)
     */
    int insertSelective(SalesBatchnum record);
    /**
     * 通过id查询数据
     */
    SalesBatchnum selectByPrimaryKey(String id);
    /**
     * 更新数据
     */
    int updateByPrimaryKeySelective(SalesBatchnum record);
    /**
     * 更新数据(动态)
     */
    int updateByPrimaryKey(SalesBatchnum record);
    /**
     * 查询数据总数
     */
    int count(SalesBatchnumReq req);
    /**
     * 查询数据
     */
    List<SalesBatchnum> page(SalesBatchnumReq req);
    /**
     * 批量插入数据
     */
    int insertBatch(List<SalesBatchnum> list);
    
}