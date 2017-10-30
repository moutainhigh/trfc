package com.tianrui.service.impl.businessManage.CardManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.businessManage.cardManage.ICardService;
import com.tianrui.api.intf.system.auth.ISystemUserService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.businessManage.cardManage.CardApi;
import com.tianrui.api.req.businessManage.cardManage.CardReq;
import com.tianrui.api.req.businessManage.cardManage.CardSave;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.businessManage.cardManage.CardResp;
import com.tianrui.api.resp.businessManage.cardManage.CardSubSystemVo;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.service.bean.businessManage.cardManage.Card;
import com.tianrui.service.bean.businessManage.otherManage.OtherArrive;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseArrive;
import com.tianrui.service.bean.businessManage.salesManage.SalesArrive;
import com.tianrui.service.mapper.businessManage.cardManage.CardMapper;
import com.tianrui.service.mapper.businessManage.otherManage.OtherArriveMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseArriveMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesArriveMapper;
import com.tianrui.smartfactory.common.constants.Constant;
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
	@Autowired
	private ISystemCodeService systemCodeService;
	@Autowired
	private ISystemUserService systemUserService;
	@Autowired
	private PurchaseArriveMapper purchaseArriveMapper;
	@Autowired
	private SalesArriveMapper salesArriveMapper;
	@Autowired
	private OtherArriveMapper otherArriveMapper;
	
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
				card.setCode(getCode(save.getCurrUid()));
				card.setState("1");
                SystemUserResp user = systemUserService.getUser(save.getCurrUid());
                card.setRegistrar(user.getName());
				card.setCreator(save.getCurrUid());
				card.setCreatetime(System.currentTimeMillis());
				card.setModifytime(System.currentTimeMillis());
				card.setModifier(save.getCurrUid());
				if (cardMapper.insert(card) == 1 && updateCode(save.getCurrUid())) {
					rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				} else {
					rs.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}
		}
		return rs;
	}
	
	private String getCode(String userId) throws Exception {
		GetCodeReq codeReq = new GetCodeReq();
		codeReq.setCode("IC");
		codeReq.setCodeType(true);
		codeReq.setUserid(userId);
		return systemCodeService.getCode(codeReq).getData().toString();
	}
	
	private boolean updateCode(String userId) throws Exception {
		boolean flag = false;
		GetCodeReq codeReq = new GetCodeReq();
		codeReq.setCode("IC");
		codeReq.setCodeType(true);
		codeReq.setUserid(userId);
		if (StringUtils.equals(systemCodeService.updateCodeItem(codeReq).getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())) {
			flag = true;
		}
		return flag;
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
				card.setCardtype(cardApi.getCardtype());
				card.setCode(getCode(cardApi.getCurrUid()));//IC卡单据编号
				SystemUserResp user = systemUserService.getUser(cardApi.getCurrUid());
				card.setRegistrar(user.getName());
				card.setCreator(cardApi.getCurrUid());
				card.setCreatetime(System.currentTimeMillis());
				card.setModifytime(System.currentTimeMillis());
				card.setModifier(cardApi.getCurrUid());
				cardMapper.insert(card);
				updateCode(cardApi.getCurrUid());
				rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}
		}
		return rs;
	}

	/**
	 * @annotation 判断IC卡是否正在使用
	 * @param cardId
	 * @return true: 未使用， false: 使用中
	 */
	private boolean icardIsUse(String cardId) {
	    boolean flag = false;
	    PurchaseArrive purchaseArrive = purchaseArriveMapper.checkICUse(cardId);
	    if (purchaseArrive == null) {
	        SalesArrive salesArrive = salesArriveMapper.checkICUse(cardId);
	        if (salesArrive == null) {
	            OtherArrive otherArrive = otherArriveMapper.checkICUse(cardId);
	            if (otherArrive == null) {
	                flag = true;
	            }
	        }
        }
	    return flag;
	}

    @Override
    public Result validate(CardApi cardApi) {
        Result result = Result.getParamErrorResult();
        if (cardApi != null && StringUtils.isNotBlank(cardApi.getCardno())) {
            Card card = new Card();
            card.setCardno(cardApi.getCardno());
            card.setState(Constant.ONE_STRING);
            List<Card> list = cardMapper.selectSelective(card);
            if (CollectionUtils.isNotEmpty(list)) {
                if (icardIsUse(list.get(0).getId())) {
                    card = list.get(0);
                    CardSubSystemVo vo = new CardSubSystemVo();
                    vo.setCardCode(card.getCardcode());
                    vo.setCardType(card.getCardtype());
                    vo.setIsValid(card.getCardstatus());
                    result.setData(vo);
                    result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
                } else {
                    result.setErrorCode(ErrorCode.CARD_IN_USE);
                }
            } else {
                result.setErrorCode(ErrorCode.CARD_NOT_EXIST);
            }
        }
        return result;
    }
}
