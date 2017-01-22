package com.tianrui.api.intf.basicFile.other;

import com.tianrui.api.req.basicFile.other.OtherBdSupplierReq;
import com.tianrui.smartfactory.common.vo.Result;

public interface IOtherBdSupplierService {
	/**
	 * 列表数据
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Result page(OtherBdSupplierReq req) throws Exception;
	/**
	 * 新增数据
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Result add(OtherBdSupplierReq req) throws Exception;
	/**
	 * 更新数据
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Result update(OtherBdSupplierReq req) throws Exception;
	/**
	 * 删除数据
	 * @param req
	 * @return
	 * @throws Exception
	 */
	Result delete(OtherBdSupplierReq req) throws Exception;
}
