package com.tianrui.api.intf.quality.sales;

import com.tianrui.api.req.quality.sales.AssayReportReq;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 销售化验报告service接口
 * @author Administrator
 *
 */
public interface IAssayReportService {
	/**
	 * 删除
	 */
	Result delete(AssayReportReq req) throws Exception;
	/**
	 * 新增
	 */
	Result add(AssayReportReq req) throws Exception;
	/**
	 * 更新
	 */
	Result update(AssayReportReq req) throws Exception;
	/**
	 * 分页查询数据
	 */
	Result page(AssayReportReq req) throws Exception;
	/**
	 * 查询数据
	 */
	Result findOne(AssayReportReq req) throws Exception;
	/**
	 * 获取物料方案下拉框
	 */
	Result mschemeData() throws Exception;
}
