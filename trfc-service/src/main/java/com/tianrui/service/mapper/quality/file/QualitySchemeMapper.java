package com.tianrui.service.mapper.quality.file;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianrui.api.req.quality.file.QualitySchemeReq;
import com.tianrui.service.bean.quality.file.QualityScheme;
/**
 * 质检方案Mapper接口
 * @author Administrator
 *
 */
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
	 * 新增数据(动�??)
	 */
    int insertSelective(QualityScheme record);
    /**
	 * 查询数据(id)
	 */
    QualityScheme selectByPrimaryKey(String id);
    /**
 	 * 查询数据(id,state)
 	 */
     QualityScheme selectOne(String id);
    /**
	 * 更新数据(动�??)
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
    /**
     * 下拉框自动搜索
     */
    List<QualityScheme> autoCompleteSearch(@Param("likeName")String likeName);

}