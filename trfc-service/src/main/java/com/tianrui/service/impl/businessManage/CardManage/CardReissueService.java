package com.tianrui.service.impl.businessManage.CardManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.businessManage.cardManage.ICardReissueService;
import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseArriveService;
import com.tianrui.api.intf.businessManage.salesManage.ISalesArriveService;
import com.tianrui.api.req.businessManage.cardManage.CardReissueReq;
import com.tianrui.api.req.businessManage.logisticsManage.AccessRecordQuery;
import com.tianrui.api.resp.businessManage.cardManage.CardReissueResp;
import com.tianrui.api.resp.businessManage.purchaseManage.PurchaseArriveResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesArriveResp;
import com.tianrui.service.bean.businessManage.cardManage.Card;
import com.tianrui.service.bean.businessManage.logisticsManage.AccessRecord;
import com.tianrui.service.mapper.businessManage.cardManage.CardMapper;
import com.tianrui.service.mapper.businessManage.logisticsManage.AccessRecordMapper1;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class CardReissueService implements ICardReissueService {
	@Autowired
	private AccessRecordMapper1 accessRecordMapper;
	@Autowired
	private IPurchaseArriveService purchaseArriveService;
	@Autowired
	private ISalesArriveService salesArriveService;
	@Autowired
	private CardMapper cardMapper;
	
	
	@Override
	public Result getAccessData(CardReissueReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req != null) {
			AccessRecord ar = accessRecordMapper.selectByPrimaryKey(req.getId());
			if(ar!=null){
				CardReissueResp resp = new CardReissueResp();
				PropertyUtils.copyProperties(resp, ar);
				if(StringUtils.isNotBlank(ar.getNoticeid())){
					SalesArriveResp sar = salesArriveService.findOne(ar.getNoticeid());
					resp.setVehicleno(sar.getVehicleno());
					resp.setMaterielname("待开发");
//					resp.setOtherparty("待开发");
				}
				rs = Result.getSuccessResult();
				rs.setData(resp);
			}else{
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}

	/**
	 * 分页查询
	 */
	@Override
	public Result page(CardReissueReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null){
			PaginationVO<CardReissueResp> page = new PaginationVO<CardReissueResp>();
			int pageNo = req.getPageNo();
			int pageSize = req.getPageSize();
			AccessRecordQuery query = card2AccessQuery(req);
			query.setStart((pageNo-1)*pageSize);
			query.setLimit(pageSize);
			long count = accessRecordMapper.findAccessRecordPageCount(query);
			page.setTotal(count);
			page.setPageNo(pageNo);
			page.setPageSize(pageSize);
			List<CardReissueResp> resps = new ArrayList<CardReissueResp>();
			if(count>0){
				List<AccessRecord> list = accessRecordMapper.findAccessRecordPage(query);
				access2cardResp(resps,list);
			}
			page.setList(resps);
			rs = Result.getSuccessResult();
			rs.setData(page);
		}
		return rs;
	}
	/**
	 * 转换类型 AccessRecord --> CardReissueResp;
	 */
	private void access2cardResp(List<CardReissueResp> list1,List<AccessRecord> list2) throws Exception{
		if(list2 !=null && list2.size()>0){
			for(AccessRecord record : list2){
				CardReissueResp resp = new CardReissueResp();
				resp.setAccesscode(record.getCode());
				resp.setBusinesstype(record.getBusinesstype());
				resp.setAccesstype(record.getAccesstype());
				resp.setApplicationcode(record.getNoticecode());
				resp.setEntertime(record.getEntertime());
				if(StringUtils.isNotBlank(record.getNoticeid())){
					//判断业务类型为  采购 
					if(( "1" ).equals(record.getBusinesstype())){
						PurchaseArriveResp par = purchaseArriveService.findOne(record.getNoticeid());
						resp.setVehicleno(par.getVehicleno());
						resp.setMaterielname(par.getPurchaseApplicationDetailResp().getMaterielname());
						resp.setCompanyname(par.getPurchaseApplicationResp().getSuppliername());
						resp.setSupplierremark(par.getPurchaseApplicationResp().getSupplierremark());
						resp.setRfid(par.getVehiclerfid());
						resp.setMinemouthname(par.getPurchaseApplicationResp().getMinemouthname());
						//获取ic卡信息
						if(StringUtils.isNotBlank(par.getIcardid())){
							Card card = cardMapper.selectByPrimaryKey(par.getIcardid());
							resp.setIcardno(card.getCardno());
							resp.setIcardcode(card.getCardcode());
							resp.setCardtype(card.getCardtype());
						}
						//判断业务类型为 销售
					}else if(( "2" ).equals(record.getBusinesstype())){
						SalesArriveResp par = salesArriveService.findOne(record.getNoticeid());
						resp.setVehicleno(par.getVehicleno());
						resp.setMaterielname(par.getMainApplicationDetail().getMaterielname());
						resp.setCompanyname(par.getMainApplication().getCustomername());
						resp.setSupplierremark("");
						resp.setRfid(par.getVehiclerfid());
						//获取ic卡信息
						if(StringUtils.isNotBlank(par.getIcardid())){
							Card card = cardMapper.selectByPrimaryKey(par.getIcardid());
							resp.setIcardno(card.getCardno());
							resp.setIcardcode(card.getCardcode());
							resp.setCardtype(card.getCardtype());
						}
					}
					
				}
				list1.add(resp);
			}
		}
	}
	
	/**
	 * 转换类型 CardReissueReq --> AccessRecordQuery
	 */
	private AccessRecordQuery card2AccessQuery(CardReissueReq req){
		AccessRecordQuery query = new AccessRecordQuery();
		query.setStarttime(req.getStarttime());
		query.setEndtime(req.getEndtime());
		query.setMaterielid(req.getMaterialid());
		query.setVehicleid(req.getVahicleid());
		query.setCode(req.getAccesscode());
		query.setBusinesstype(req.getBusinesstype());
		query.setAccesstype(req.getAccesstype());
		query.setIcardno(req.getIcardno());
		return query;
	}
	
}
