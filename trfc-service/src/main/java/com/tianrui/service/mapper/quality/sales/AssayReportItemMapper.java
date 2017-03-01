package com.tianrui.service.mapper.quality.sales;

import com.tianrui.service.bean.quality.sales.AssayReportItem;

public interface AssayReportItemMapper {
	/**
	 * 删除数据
	 */
    int deleteByPrimaryKey(String id);
	/**
	 * 新增数据
	 */
    int insert(AssayReportItem record);
	/**
	 * 新增数据(动态)
	 */
    int insertSelective(AssayReportItem record);
	/**
	 * 查询数据(通过id)
	 */
    AssayReportItem selectByPrimaryKey(String id);
	/**
	 * 更新数据(动态)
	 */
    int updateByPrimaryKeySelective(AssayReportItem record);
	/**
	 * 更新数据
	 */
    int updateByPrimaryKey(AssayReportItem record);
    /**
     * 查询数据(通过itemid,assayid)
     */
    AssayReportItem findOne(AssayReportItem record);
}