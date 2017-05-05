package com.tianrui.api.intf.quality.purchase;

import com.tianrui.api.req.quality.purchase.PurchaseAssayReq;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 采购化验管理 接口
 * @author LXY
 *
 */
public interface IPurchaseAssayService {
	//删除数据
	Result delete(PurchaseAssayReq req) throws Exception;
	//新增数据
	Result add(PurchaseAssayReq req) throws Exception;
	//更新数据		
	Result update(PurchaseAssayReq req) throws Exception;
	//分页查询
	Result page(PurchaseAssayReq req) throws Exception;
}
