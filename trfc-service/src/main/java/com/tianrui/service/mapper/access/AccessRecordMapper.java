package com.tianrui.service.mapper.access;

import com.tianrui.service.bean.access.AccessRecord;

public interface AccessRecordMapper {
	/**
	 * 删除数据(主键ID)
	 */
    int deleteByPrimaryKey(String id);
    /**
     * 新增数据
     */
    int insert(AccessRecord record);
    /**
     * 新增数据(动态)
     */
    int insertSelective(AccessRecord record);
    /**
     * 查询数据(主键ID)
     */
    AccessRecord selectByPrimaryKey(String id);
    /**
     * 更新数据(动态)
     */
    int updateByPrimaryKeySelective(AccessRecord record);
    /**
     * 更新数据
     */
    int updateByPrimaryKey(AccessRecord record);
}