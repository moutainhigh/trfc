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
	 * 查询审核信息
	 */
	Result findReportMsg(AssayReportReq req) throws Exception;
	
	/**
	 * 根据批号查询销售化验报告详情接口
	 */
	Result findSelectDetail(String factorycode)throws Exception;
	/**
	 * 根据批号校验报告天数
	 */
	Result selectBatchnumid(AssayReportReq req)throws Exception;
}
