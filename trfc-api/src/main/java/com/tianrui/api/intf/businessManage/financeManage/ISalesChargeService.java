package com.tianrui.api.intf.businessManage.financeManage;

import com.tianrui.api.req.businessManage.financeManage.SalesChargeQuery;
import com.tianrui.api.req.businessManage.financeManage.SalesChargeSave;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 销售收款Service接口
 * @author YangZhenFu
 *
 */
public interface ISalesChargeService {
	
	Result page(SalesChargeQuery query) throws Exception;
	
	Result add(SalesChargeSave save) throws Exception;
}
