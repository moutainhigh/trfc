package com.tianrui.service.mapper.businessManage.cardManage;

import java.util.List;

import com.tianrui.api.req.businessManage.cardManage.CardReq;
import com.tianrui.service.bean.businessManage.cardManage.Card;
/**
 * 卡务管理Mapper接口
 * @author zhanggaohao
 * @createtime 2016年12月25日 上午9:42:53
 * @classname CardMapper.java
 */
public interface CardMapper {
    int deleteByPrimaryKey(String id);

    int insert(Card record);

    int insertSelective(Card record);

    Card selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Card record);

    int updateByPrimaryKey(Card record);

	long findCardPageCount(CardReq req);

	List<Card> findCardPage(CardReq req);

	List<Card> selectSelective(Card card);
	
	Card selectByCardno(String cardno);
}