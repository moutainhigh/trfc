package com.tianrui.api.intf.businessManage.salesManage;

import com.tianrui.api.req.businessManage.salesManage.SalesArriveQuery;
import com.tianrui.api.resp.businessManage.salesManage.SalesArriveResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;

/**
 * 销售到货通知单Service接口
 * @author zhanggaohao
 * @createtime 2017年1月9日 下午2:23:16
 * @classname ISalesArriveService.java
 */
public interface ISalesArriveService {

	/**
	 * 分页查询销售到货通知单
	 * @param query
	 * @return
	 * @throws Exception
	 */
	PaginationVO<SalesArriveResp> page(SalesArriveQuery query) throws Exception;

}
