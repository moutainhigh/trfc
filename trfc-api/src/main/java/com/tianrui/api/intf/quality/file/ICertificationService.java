package com.tianrui.api.intf.quality.file;

import com.tianrui.api.req.quality.file.CertificationReq;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 合格证维护
 * @author Administrator
 *
 */
public interface ICertificationService {
	/**
	 * 删除数据
	 */
	Result delete(CertificationReq req) throws Exception;
	/**
	 * 新增数据
	 */
	Result add(CertificationReq req) throws Exception;
	/**
	 * 更新数据
	 */
	Result update(CertificationReq req) throws Exception;
	/**
	 * 分页查询
	 */
	Result page(CertificationReq req) throws Exception;
	/**
	 * 获取下拉框数据
	 */
	Result materialData() throws Exception;
}
