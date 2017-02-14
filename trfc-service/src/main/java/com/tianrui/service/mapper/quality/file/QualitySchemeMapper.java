package com.tianrui.service.mapper.quality.file;

import java.util.List;

import com.tianrui.api.req.quality.file.QualitySchemeReq;
import com.tianrui.service.bean.quality.file.QualityScheme;

public interface QualitySchemeMapper {
	/**
	 * 删除数据
	 */
    int deleteByPrimaryKey(String id);
    /**
	 * 新增数据
	 */
    int insert(QualityScheme record);
    /**
	 * 新增数据(动态)
	 */
    int insertSelective(QualityScheme record);
    /**
	 * 查询数据(id)
	 */
    QualityScheme selectByPrimaryKey(String id);
    /**
	 * 更新数据(动态)
	 */
    int updateByPrimaryKeySelective(QualityScheme record);
    /**
	 * 更新数据
	 */
    int updateByPrimaryKey(QualityScheme record);
    /**
	 * 获取分页数据
	 */
    List<QualityScheme> page(QualitySchemeReq req);
    /**
	 * 查询数据总数
	 */
    int count(QualitySchemeReq req);
}