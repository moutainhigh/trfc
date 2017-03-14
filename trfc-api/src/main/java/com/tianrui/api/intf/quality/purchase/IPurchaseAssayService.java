package com.tianrui.api.intf.quality.purchase;

import com.tianrui.api.req.quality.purchase.PurchaseAssayReq;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 采购化验管理 接口
 * @author LXY
 *
 */
public interface IPurchaseAssayService {

	Result delete(PurchaseAssayReq req) throws Exception;
	
	Result add(PurchaseAssayReq req) throws Exception;
	
	Result update(PurchaseAssayReq req) throws Exception;
	
	Result page(PurchaseAssayReq req) throws Exception;
}
