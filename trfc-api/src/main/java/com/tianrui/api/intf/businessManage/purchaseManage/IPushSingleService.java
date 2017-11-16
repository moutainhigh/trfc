package com.tianrui.api.intf.businessManage.purchaseManage;

import com.tianrui.api.req.businessManage.purchaseManage.PushSingleReq;
import com.tianrui.api.resp.businessManage.purchaseManage.PushSingleResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;

public interface IPushSingleService {
	PaginationVO<PushSingleResp> page(PushSingleReq req) throws Exception;
}
