package com.tianrui.api.intf.businessManage.purchaseManage;

import com.tianrui.api.req.businessManage.purchaseManage.PurchaseArriveQuery;
import com.tianrui.api.resp.businessManage.purchaseManage.PurchaseArriveResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;

public interface IPurchaseArriveService {

	PaginationVO<PurchaseArriveResp> page(PurchaseArriveQuery query) throws Exception;

}
