package com.tianrui.api.intf.businessManage.financeManage;

import com.tianrui.api.req.businessManage.financeManage.SalesLedgerQuery;
import com.tianrui.api.req.businessManage.financeManage.SalesLedgerSave;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 收款台账Service接口
 * @author YangZhenFu
 *
 */
public interface ISalesLedgerService {
	Result page(SalesLedgerQuery query) throws Exception;
	
	Result add(SalesLedgerSave save) throws Exception;
}
