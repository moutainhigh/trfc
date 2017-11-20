package com.tianrui.api.intf.businessManage.purchaseManage;

import com.tianrui.api.req.businessManage.purchaseManage.PurchaseStorageUpReq;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * @Description 入库单业务操作
 * @author zhanggaohao
 * @version 2017年2月14日 下午4:07:09
 */
public interface IPurchaseStorageService {

	
	public Result poundPushUp(PurchaseStorageUpReq req ) throws Exception; 
}
