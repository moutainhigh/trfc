package com.tianrui.api.intf.businessManage.cardManage;

import com.tianrui.api.req.businessManage.cardManage.CardReissueReq;
import com.tianrui.smartfactory.common.vo.Result;

public interface ICardReissueService {
	/**
	 * 获取门禁详情
	 */
	Result getAccessData(CardReissueReq req) throws Exception;
	/**
	 * 获取门禁列表
	 */
	Result page(CardReissueReq req) throws Exception;
	/**
	 * 更新ic卡信息到 通知单
	 * @param req
	 * @author lxy
	 * @return
	 * @throws Exception
	 */
	Result updateCard(CardReissueReq req) throws Exception;
}
