package com.tianrui.api.intf.businessManage.cardManage;

import com.tianrui.api.req.businessManage.cardManage.CardReq;
import com.tianrui.api.resp.businessManage.cardManage.CardResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;

/**
 * 卡务管理Service接口
 * @author zhanggaohao
 * @createtime 2016年12月25日 上午9:42:13
 * @classname ICardService.java
 */
public interface ICardService {

	PaginationVO<CardResp> page(CardReq req) throws Exception;

	int addCard(CardReq req) throws Exception;

	int updateCard(CardReq req) throws Exception;

	int deleteCard(String id);

	int delCard(CardReq req) throws Exception;

	CardResp findOne(String id) throws Exception;

}
