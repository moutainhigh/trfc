package com.tianrui.service.mapper.quality.file;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianrui.api.req.quality.file.QualityItemReq;
import com.tianrui.service.bean.quality.file.QualityItem;

public interface QualityItemMapper {

	/**
	 * 删除数据
	 */
    int deleteByPrimaryKey(String id);
    /**
	 * 新增数据
	 */
    int insert(QualityItem record);
    /**
	 * 新增数据(动�??)
	 */
    int insertSelective(QualityItem record);
    /**
	 * 查询数据(主键id)
	 */
    QualityItem selectByPrimaryKey(String id);
    /**
	 * 查询数据(主键id,state)
	 */
    QualityItem selectOne(String id);
    /**
	 * 更新数据(动�??)
	 */
    int updateByPrimaryKeySelective(QualityItem record);
    /**
	 * 更新数据
	 */
    int updateByPrimaryKey(QualityItem record);
    /**
     * 分页查询数据
     */
    List<QualityItem> page(QualityItemReq req);
    /**
     * 查询数据总数
     */
    int count(QualityItemReq req);
    /**
     * 下拉框
     */
    List<QualityItem> autoCompleteSearch(@Param("likeName")String likeName);
}