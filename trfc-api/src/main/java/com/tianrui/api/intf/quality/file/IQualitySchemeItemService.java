package com.tianrui.api.intf.quality.file;

import com.tianrui.api.req.quality.file.QualitySchemeItemReq;
import com.tianrui.smartfactory.common.vo.Result;

public interface IQualitySchemeItemService {
	
	/**
	 *	删除数据
	 */
	Result delete(QualitySchemeItemReq req) throws Exception;
	/**
	 * 清除数据
	 */
	Result deleteBatch(QualitySchemeItemReq req) throws Exception;
	/**
	 * 新增数据
	 */
	Result add(QualitySchemeItemReq req) throws Exception;
	/**
	 * 批量新增数据
	 */
	Result addBatch(QualitySchemeItemReq req) throws Exception;
	/**
	 * 更新数据
	 */
	Result update(QualitySchemeItemReq req) throws Exception;
	/**
	 * 查询数据
	 */
	Result findBySchemeId(QualitySchemeItemReq req) throws Exception;
	/**
	 * 获取下拉框数据(质检项目)
	 */
	Result itemData() throws Exception;
}