package com.tianrui.service.impl.businessManage.CardManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.businessManage.cardManage.ICardService;
import com.tianrui.api.req.businessManage.cardManage.CardReq;
import com.tianrui.api.resp.businessManage.cardManage.CardResp;
import com.tianrui.service.bean.businessManage.cardManage.Card;
import com.tianrui.service.mapper.businessManage.cardManage.CardMapper;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
/**
 * 卡务管理Service
 * @author zhanggaohao
 * @createtime 2016年12月25日 上午9:41:24
 * @classname CardService.java
 */
import com.tianrui.smartfactory.common.vo.PaginationVO;
@Service
public class CardService implements ICardService {
	
	@Autowired
	private CardMapper cardMapper;
	
	@Override
	public PaginationVO<CardResp> page(CardReq req) throws Exception {
		PaginationVO<CardResp> page = null;
		if(req != null){
			page = new PaginationVO<CardResp>();
			req.setState("1");
			long count = cardMapper.findCardPageCount(req);
			if(count > 0){
				req.setStart((req.getPageNo()-1)*req.getPageSize());
				req.setLimit(req.getPageSize());
				List<Card> list = cardMapper.findCardPage(req);
				page.setList(copyBeanList2RespList(list));
			}
			page.setTotal(count);
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		return page;
	}
	
	@Transactional
	@Override
	public int addCard(CardReq req) throws Exception {
		if(req != null){
			Card card = new Card();
			card.setCardcode(req.getCardcode());
			if(cardMapper.selectSelective(card).size() > 0){
				return -1;
			}
			PropertyUtils.copyProperties(card, req);
			card.setId(UUIDUtil.getId());
			card.setState("1");
//			card.setCreator("");
			card.setCreatetime(System.currentTimeMillis());
//			card.setModifier("");
			card.setModifytime(System.currentTimeMillis());
			return cardMapper.insert(card);
		}
		return 0;
	}
	
	@Transactional
	@Override
	public int updateCard(CardReq req) throws Exception {
		if(req != null){
			Card card = new Card();
			PropertyUtils.copyProperties(card, req);
//			card.setModifier("");
			card.setModifytime(System.currentTimeMillis());
			return cardMapper.updateByPrimaryKeySelective(card);
		}
		return 0;
	}
	
	@Transactional
	@Override
	public int delCard(CardReq req) throws Exception {
		if(req != null){
			Card card = new Card();
			PropertyUtils.copyProperties(card, req);
			card.setState("0");
//			card.setModifier("");
			card.setModifytime(System.currentTimeMillis());
			return cardMapper.updateByPrimaryKeySelective(card);
		}
		return 0;
	}
	
	@Transactional
	@Override
	public int deleteCard(String id){
		if(StringUtils.isNotBlank(id)){
			return cardMapper.deleteByPrimaryKey(id);
		}
		return 0;
	}
	
	@Override
	public CardResp findOne(String id) throws Exception {
		if(StringUtils.isNotBlank(id)){
			Card card = cardMapper.selectByPrimaryKey(id);
			return copyBean2Resp(card);
		}
		return null;
	}

	private List<CardResp> copyBeanList2RespList(List<Card> list) throws Exception {
		List<CardResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<CardResp>();
			for(Card card : list){
				listResp.add(copyBean2Resp(card));
			}
		}
		return listResp;
	}
	
	private CardResp copyBean2Resp(Card bean) throws Exception {
		CardResp resp = null;
		if(bean != null){
			resp = new CardResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}
}
