package com.tianrui.api.intf.quality.file;

import com.tianrui.api.req.quality.file.QualitySchemeReq;
import com.tianrui.smartfactory.common.vo.Result;

public interface IQualitySchemeService {
	
	/**
	 * 删除数据
	 */
	Result delete(QualitySchemeReq req) throws Exception;
	/**
	 * 新增数据
	 */
	Result add(QualitySchemeReq req) throws Exception;
	/**
	 * 更新数据
	 */
	Result update(QualitySchemeReq req) throws Exception;
	/**
	 * 分页查询
	 */
	Result page(QualitySchemeReq req) throws Exception;
	/**
	 * 获取单据数据
	 */
	Result billsData() throws Exception;
	
	
}
