package com.tianrui.api.intf.system.merchants;

import com.tianrui.api.req.system.merchants.AppUserGroupReq;
import com.tianrui.api.req.system.merchants.SupplierGroupQuery;
import com.tianrui.api.req.system.merchants.SupplierGroupSave;
import com.tianrui.api.resp.system.merchants.SupplierGroupResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * @Description 供应商用户接口
 * @author zhanggaohao
 * @version 2017年5月9日 上午9:19:16
 */
public interface ISupplierGroupService {
	/**
	 * @Description 查询供应商用户分页接口 
	 * @author zhanggaohao
	 * @version 2017年5月9日 下午1:45:22
	 * @param query
	 * @return
	 */
	PaginationVO<SupplierGroupResp> page(SupplierGroupQuery query);
	/**
	 * @Description TODO
	 * @author zhanggaohao
	 * @version 2017年5月10日 上午9:27:32
	 * @param save
	 * @return
	 */
	Result addSupplierGroup(SupplierGroupSave save);
	/**
	 * @Description 查询组成员
	 * @author zhanggaohao
	 * @version 2017年5月10日 下午1:38:25
	 * @param groupId
	 * @return
	 */
	Result supplierGroupCutover(String groupId);
	/**
	 * @Description TODO
	 * @author zhanggaohao
	 * @version 2017年5月10日 下午3:01:37
	 * @param req
	 * @return
	 * @throws Exception 
	 */
	Result userCutover(AppUserGroupReq req) throws Exception;

}
