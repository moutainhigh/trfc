package com.tianrui.service.mapper.quality.file;

import java.util.List;

import com.tianrui.api.req.quality.file.SupplierSchemeReq;
import com.tianrui.service.bean.quality.file.SupplierScheme;
/**
 * 供应商标准方案Mapper接口
 * @author Administrator
 *
 */
public interface SupplierSchemeMapper {
	
	/**
	 * 删除数据
	 */
    int deleteByPrimaryKey(String id);
	/**
	 * 新增数据
	 */
    int insert(SupplierScheme record);
	/**
	 * 新增数据(动�??)
	 */
    int insertSelective(SupplierScheme record);
	/**
	 * 查询数据
	 */
    SupplierScheme selectByPrimaryKey(String id);
	/**
	 * 查询数据(id,state)
	 */
    SupplierScheme selectOne(SupplierSchemeReq req);
	/**
	 * 更新数据(动�??)
	 */
    int updateByPrimaryKeySelective(SupplierScheme record);
	/**
	 * 更新数据
	 */
    int updateByPrimaryKey(SupplierScheme record);
    /**
     * 分页查询
     */
    List<SupplierScheme> page(SupplierSchemeReq req);
    /**
     * 数据总数
     */
    int count(SupplierSchemeReq req);
}