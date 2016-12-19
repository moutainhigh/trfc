package com.tianrui.api.intf.basicFile.nc;

import com.tianrui.api.req.basicFile.nc.SupplierManageReq;
import com.tianrui.api.resp.basicFile.nc.SupplierManageResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;
/**
 * 供应商管理ISupplierManageService接口
 * @author zhanggaohao
 * @createtime 2016年12月19日 上午9:54:48
 * @classname ISupplierManageService.java
 */
public interface ISupplierManageService {

	PaginationVO<SupplierManageResp> page(SupplierManageReq req) throws Exception;

	int updateSupplier(SupplierManageReq req) throws Exception;
	
}
