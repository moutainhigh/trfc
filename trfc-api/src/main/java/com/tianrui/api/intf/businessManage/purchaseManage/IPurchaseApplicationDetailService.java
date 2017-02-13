package com.tianrui.api.intf.businessManage.purchaseManage;

import java.util.List;

import com.tianrui.api.resp.businessManage.purchaseManage.PurchaseApplicationDetailResp;

public interface IPurchaseApplicationDetailService {

	List<PurchaseApplicationDetailResp> selectByPurchaseId(String purchaseId) throws Exception;

}
