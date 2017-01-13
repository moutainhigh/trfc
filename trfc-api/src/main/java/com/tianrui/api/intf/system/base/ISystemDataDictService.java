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
	Result findSystemDataDicts(SystemDataDictReq req);
	/*
	 * 增加数据字典类别
	 */
	Result addSystemDataDict(SystemDataDictReq req);
	/*
	 * 修改数据字典类别
	 */
	Result editSystemDataDict(SystemDataDictReq req);
	/*
	 * 删除数据字典类别
	 */
	Result deleteSystemDataDict(String id);
	
	/*
	 * 查找所有的数据字典明细
	 */
	Result findSystemDataDictItems(SystemDataDictItemReq req);
	/*
	 * 增加数据字典明细
	 */
	Result addSystemDataDictItem(SystemDataDictItemReq req);
	/*
	 * 修改数据字典明细
	 */
	Result editSystemDataDictItem(SystemDataDictItemReq req);
	/*
	 * 删除数据字典明细
	 */
	Result deleteSystemDataDictItem(String id);
}
