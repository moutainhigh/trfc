package com.tianrui.api.intf.quality.file;

import com.tianrui.api.req.quality.file.QualitySchemeItemReq;
import com.tianrui.smartfactory.common.vo.Result;
/**
 * 质检方案-项目接口
 * @author Administrator
 *
 */
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
	 * 批量更新数据
	 */
	Result updateBatch(QualitySchemeItemReq req) throws Exception;
	/**
	 * 查询数据
	 */
	Result findBySchemeId(QualitySchemeItemReq req) throws Exception;
	/**
	 * 获取下拉框数据(质检项目)
	 */
	Result itemData() throws Exception;
	/**
	 * 化验报告获取 详情+检测值
	 */
	Result findDetailandVal(QualitySchemeItemReq req) throws Exception;
}
