package com.tianrui.service.impl.businessManage.CardManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.businessManage.cardManage.ICardService;
import com.tianrui.api.req.businessManage.cardManage.CardApi;
import com.tianrui.api.req.businessManage.cardManage.CardReq;
import com.tianrui.api.req.businessManage.cardManage.CardSave;
import com.tianrui.api.resp.businessManage.cardManage.CardResp;
import com.tianrui.service.bean.businessManage.cardManage.Card;
import com.tianrui.service.mapper.businessManage.cardManage.CardMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
/**
 * 卡务管理Service
 * @author zhanggaohao
 * @createtime 2016年12月25日 上午9:41:24
 * @classname CardService.java
 */
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
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
	public int updateCard(CardReq req) throws Exception {
		if(req != null){
			Card card = new Card();
			PropertyUtils.copyProperties(card, req);
			card.setModifier(req.getCurrUId());
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
			card.setModifier(req.getCurrUId());
			card.setModifytime(System.currentTimeMillis());
			return cardMapper.updateByPrimaryKeySelective(card);
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

	@Override
	public Result addCard(CardSave save) throws Exception {
		Result rs =Result.getParamErrorResult();
		if(save != null && StringUtils.isNotEmpty(save.getCardcode())
				&& StringUtils.isNotEmpty(save.getCardno())&& StringUtils.isNotEmpty(save.getCardtype())){
			//卡编码不能重复
			CardReq query = new CardReq();
			query.setState("1");
			query.setCardcode(save.getCardcode());
			long dbCodeSize =cardMapper.findCardPageCount(query) ;
			//卡序号也不能重复
			query = new CardReq();
			query.setState("1");
			query.setCardno(save.getCardno());
			long dbNoSize =cardMapper.findCardPageCount(query) ;
			if(dbCodeSize>0){
				rs.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			}else if(dbNoSize>0){
				rs.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			}else{
				Card card = new Card();
				PropertyUtils.copyProperties(card, save);
				card.setId(UUIDUtil.getId());
				card.setState("1");
				card.setCreator(save.getCurrUid());
				card.setCreatetime(System.currentTimeMillis());
				card.setModifytime(System.currentTimeMillis());
				card.setModifier(save.getCurrUid());
				cardMapper.insert(card);
				rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}
		}
		return rs;
	}
	
	@Override
	public Result addCardApi(CardApi cardApi) throws Exception {
		Result rs =Result.getParamErrorResult();
		if(cardApi != null && StringUtils.isNotEmpty(cardApi.getCardcode())
				&& StringUtils.isNotEmpty(cardApi.getCardno())&& StringUtils.isNotEmpty(cardApi.getCardtype())){
			//卡编码不能重复
			CardReq query = new CardReq();
			query.setState("1");
			query.setCardcode(cardApi.getCardcode());
			long dbCodeSize =cardMapper.findCardPageCount(query) ;
			//卡序号也不能重复
			query = new CardReq();
			query.setState("1");
			query.setCardno(cardApi.getCardno());
			long dbNoSize =cardMapper.findCardPageCount(query) ;
			if(dbCodeSize>0){
				rs.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			}else if(dbNoSize>0){
				rs.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			}else{
				Card card = new Card();
				PropertyUtils.copyProperties(card, cardApi);
				card.setId(UUIDUtil.getId());
				card.setCardstatus("1");
				card.setState("1");
				card.setCode("");//IC卡单据编号
				card.setCreator(cardApi.getCurrUid());
				card.setCreatetime(System.currentTimeMillis());
				card.setModifytime(System.currentTimeMillis());
				card.setModifier(cardApi.getCurrUid());
				cardMapper.insert(card);
				rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}
		}
		return rs;
	}
}
