package com.tianrui.api.intf.basicFile.nc;

import com.tianrui.api.req.basicFile.nc.MaterielManageQuery;
import com.tianrui.api.req.basicFile.nc.SupplierManageQuery;
import com.tianrui.api.req.basicFile.nc.SupplierManageSave;
import com.tianrui.api.resp.basicFile.nc.SupplierManageResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
/**
 * 供应商管理ISupplierManageService接口
 * @author zhanggaohao
 * @createtime 2016年12月19日 上午9:54:48
 * @classname ISupplierManageService.java
 */
public interface ISupplierManageService {

	PaginationVO<SupplierManageResp> page(SupplierManageQuery query) throws Exception;

	Result updateSupplier(SupplierManageSave save) throws Exception;
	
	Result findListByParmas(SupplierManageQuery query) throws Exception;
	
	Result findMaxUtc(SupplierManageQuery query) throws Exception;
}
