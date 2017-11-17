package com.tianrui.api.intf.businessManage.purchaseManage;

import com.tianrui.api.req.businessManage.purchaseManage.PushSingleReq;
import com.tianrui.api.resp.businessManage.purchaseManage.PushSingleResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
public interface IPushSingleService {
	PaginationVO<PushSingleResp> page(PushSingleReq req) throws Exception;

	Result findPushSingle(PushSingleReq pushSingle) throws Exception;

	Result updatePushSingle(PushSingleReq pushSingle) throws Exception;

	Result savePushSingle(PushSingleReq pushSingle) throws Exception;

}
