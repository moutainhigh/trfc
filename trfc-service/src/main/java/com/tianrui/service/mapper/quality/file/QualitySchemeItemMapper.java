package com.tianrui.service.mapper.quality.file;

import java.util.List;

import com.tianrui.api.req.quality.file.QualitySchemeItemReq;
import com.tianrui.service.bean.quality.file.QualitySchemeItem;

public interface QualitySchemeItemMapper {
	
	/**
	 * 删除数据
	 */
    int deleteByPrimaryKey(String id);
    /**
	 * 新增数据
	 */
    int insert(QualitySchemeItem record);
    /**
	 * 新增数据(动�??)
	 */
    int insertSelective(QualitySchemeItem record);
    /**
	 * 查询数据(主键id)
	 */
    QualitySchemeItem selectByPrimaryKey(String id);
    /**
  	 * 查询数据(主键id,state)
  	 */
      QualitySchemeItem selectOne(QualitySchemeItemReq req);
    /**
	 * 更新数据(动�??)
	 */
    int updateByPrimaryKeySelective(QualitySchemeItem record);
    /**
	 * 更新数据
	 */
    int updateByPrimaryKey(QualitySchemeItem record);
    /**
     * 批量删除数据
     */
    int deleteBatch(String schemeid);
    /**
     * 查询数据(质检方案id)
     */
    List<QualitySchemeItem> findBySchemeId(QualitySchemeItemReq req);
    /**
     * 批量添加数据
     */
    int insertBatch(List<QualitySchemeItem> list);
}