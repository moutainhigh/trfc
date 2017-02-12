package com.tianrui.service.mapper.quality.file;

import java.util.List;

import com.tianrui.api.req.quality.file.MaterialSchemeReq;
import com.tianrui.service.bean.quality.file.MaterialScheme;

public interface MaterialSchemeMapper {
	/**
	 * 删除数据
	 */
    int deleteByPrimaryKey(String id);
    /**
	 * 新增数据
	 */
    int insert(MaterialScheme record);
    /**
	 * 新增数据(动�??)
	 */
    int insertSelective(MaterialScheme record);
    /**
	 * 通过id查询数据
	 */
    MaterialScheme selectByPrimaryKey(String id);
    /**
	 * 更新数据(动�??)
	 */
    int updateByPrimaryKeySelective(MaterialScheme record);
    /**
	 * (更新数据)
	 */
    int updateByPrimaryKey(MaterialScheme record);
    /**
	 * 查询数据总数
	 */
    int count(MaterialSchemeReq req);
    /**
     * 分页查询数据
     */
    List<MaterialScheme> page(MaterialSchemeReq req);
}