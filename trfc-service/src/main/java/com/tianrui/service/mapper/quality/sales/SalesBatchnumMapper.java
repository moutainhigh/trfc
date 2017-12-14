package com.tianrui.service.mapper.quality.sales;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianrui.api.req.quality.sales.SalesBatchnumReq;
import com.tianrui.service.bean.quality.sales.SalesBatchnum;

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
     * 新增数据(动�??)
     */
    int insertSelective(SalesBatchnum record);
    /**
     * 通过id查询数据
     */
    SalesBatchnum selectByPrimaryKey(String id);
    /**
     * 通过批号查询数据
     */
    SalesBatchnum selectByFactoryCode(String factorycode);
    /**
     * 更新数据
     */
    int updateByPrimaryKeySelective(SalesBatchnum record);
    /**
     * 更新数据(动�??)
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
    /**
     * 下拉框自动搜索
     */
    List<SalesBatchnum> autoCompleteSearch(@Param("likeName")String likeName);
    
    SalesBatchnum selectIdMargin(SalesBatchnum s);

}