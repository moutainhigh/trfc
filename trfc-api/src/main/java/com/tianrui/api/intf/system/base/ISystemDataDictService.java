package com.tianrui.api.intf.system.base;

import com.tianrui.api.req.system.base.SystemDataDictItemReq;
import com.tianrui.api.req.system.base.SystemDataDictReq;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 系统数据字典Service接口
 * @author YangZhenFu
 *
 */
public interface ISystemDataDictService {
	/*
	 * 查询所有的数据字典类别
	 */
	Result findSystemDataDicts(SystemDataDictReq req) throws Exception;
	/*
	 * 增加数据字典类别
	 */
	Result addSystemDataDict(SystemDataDictReq req) throws Exception;
	/*
	 * 修改数据字典类别
	 */
	Result editSystemDataDict(SystemDataDictReq req) throws Exception;
	/*
	 * 删除数据字典类别
	 */
	Result deleteSystemDataDict(String id) throws Exception;
	
	/*
	 * 查找所有的数据字典明细
	 */
	Result findSystemDataDictItems(SystemDataDictItemReq req) throws Exception;
	/*
	 * 增加数据字典明细
	 */
	Result addSystemDataDictItem(SystemDataDictItemReq req) throws Exception;
	/*
	 * 修改数据字典明细
	 */
	Result editSystemDataDictItem(SystemDataDictItemReq req) throws Exception;
	/*
	 * 删除数据字典明细
	 */
	Result deleteSystemDataDictItem(String id) throws Exception;
	/**
	 * 模糊查询 下拉框
	 */
	Result autoCompleteSearch(SystemDataDictItemReq req) throws Exception;
}
