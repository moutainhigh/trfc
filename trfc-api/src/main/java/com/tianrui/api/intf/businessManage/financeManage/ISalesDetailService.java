package com.tianrui.api.intf.businessManage.financeManage;

import com.tianrui.api.req.businessManage.financeManage.SalesDetailQuery;
import com.tianrui.api.req.businessManage.financeManage.SalesDetailSave;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 销售明细Service接口
 * @author YangZhenFu
 *
 */
public interface ISalesDetailService {
	
	Result page(SalesDetailQuery query) throws Exception;
	
	Result add(SalesDetailSave save) throws Exception;
	
}
