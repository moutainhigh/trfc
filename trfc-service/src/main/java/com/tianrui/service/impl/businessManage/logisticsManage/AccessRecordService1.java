package com.tianrui.service.impl.businessManage.logisticsManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.businessManage.cardManage.ICardService;
import com.tianrui.api.intf.businessManage.logisticsManage.IAccessRecordService1;
import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseArriveService;
import com.tianrui.api.intf.businessManage.salesManage.ISalesArriveService;
import com.tianrui.api.req.businessManage.logisticsManage.AccessRecordQuery;
import com.tianrui.api.resp.businessManage.cardManage.CardResp;
import com.tianrui.api.resp.businessManage.logisticsManage.AccessRecordResp;
import com.tianrui.api.resp.businessManage.purchaseManage.PurchaseArriveResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesArriveResp;
import com.tianrui.service.bean.businessManage.logisticsManage.AccessRecord;
import com.tianrui.service.mapper.businessManage.logisticsManage.AccessRecordMapper1;
import com.tianrui.smartfactory.common.vo.PaginationVO;

@Service
public class AccessRecordService1 implements IAccessRecordService1 {

	@Autowired
	private AccessRecordMapper1 accessRecordMapper;
	@Autowired
	private IPurchaseArriveService purchaseArriveService;
	@Autowired
	private ISalesArriveService salesArriveService;
	
	@Autowired
	private ICardService cardService;
	
	@Override
	public PaginationVO<AccessRecordResp> page(AccessRecordQuery query) throws Exception{
		PaginationVO<AccessRecordResp> page = null;
		if(query != null){
			page = new PaginationVO<AccessRecordResp>();
			query.setState("1");
			long count = accessRecordMapper.findAccessRecordPageCount(query);
			if(count > 0){
				query.setStart((query.getPageNo() - 1) * query.getPageSize());
				query.setLimit(query.getPageSize());
				List<AccessRecord> list = accessRecordMapper.findAccessRecordPage(query);
				page.setList(copyBeanList2RespList(list, true));
			}
			page.setTotal(count);
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
		}
		return page;
	}
	
	private void SetPurchaseViewData(List<AccessRecordResp> list, String type) throws Exception{
		List<String> ids = new ArrayList<>();
		for(AccessRecordResp resp : list){
			if (StringUtils.equals(resp.getBusinesstype(), type)) {
				ids.add(resp.getNoticeid());
			}
		}
		List<PurchaseArriveResp> listArrive = purchaseArriveService.selectByIds(ids);
		if(CollectionUtils.isNotEmpty(listArrive)){
			for(AccessRecordResp resp : list){
				for(PurchaseArriveResp arriveResp : listArrive){
					if(StringUtils.equals(resp.getNoticeid(), arriveResp.getId())){
						resp.setVehicleno(arriveResp.getVehicleno());
						resp.setMaterielname(arriveResp.getPurchaseApplicationDetailResp().getMaterielname());
						resp.setRfid(arriveResp.getVehiclerfid());
						resp.setOtherparty(arriveResp.getPurchaseApplicationResp().getSuppliername());
						if(StringUtils.isNotBlank(arriveResp.getIcardid())){
							CardResp card = cardService.findOne(arriveResp.getIcardid());
							if(card != null){
								resp.setIcardno(card.getCardno());
								resp.setIcardcode(card.getCardcode());
							}
						}
					}
				}
			}
		}
	}
	
	private void SetSalesViewData(List<AccessRecordResp> list, String type) throws Exception{
		List<String> ids = new ArrayList<>();
		for(AccessRecordResp resp : list){
			if (StringUtils.equals(resp.getBusinesstype(), type)) {
				ids.add(resp.getNoticeid());
			}
		}
		List<SalesArriveResp> salesList = salesArriveService.selectByIds(ids);
		if(CollectionUtils.isNotEmpty(salesList)){
			for(AccessRecordResp resp : list){
				for(SalesArriveResp salesResp : salesList){
					if(StringUtils.equals(resp.getNoticeid(), salesResp.getId())){
						resp.setVehicleno(salesResp.getVehicleno());
						resp.setMaterielname(salesResp.getMainApplicationDetail().getMaterielname());
						resp.setRfid(salesResp.getVehiclerfid());
						resp.setOtherparty(salesResp.getMainApplication().getCustomername());
						if(StringUtils.isNotBlank(salesResp.getIcardid())){
							CardResp card = cardService.findOne(salesResp.getIcardid());
							if(card != null){
								resp.setIcardno(card.getCardno());
								resp.setIcardcode(card.getCardcode());
							}
						}
					}
				}
			}
		}
	}
	
	private List<AccessRecordResp> copyBeanList2RespList(List<AccessRecord> list, boolean setNotice) throws Exception {
		List<AccessRecordResp> listResp = null;
		if(CollectionUtils.isNotEmpty(list)){
			listResp = new ArrayList<AccessRecordResp>();
			for(AccessRecord sa : list){
				listResp.add(copyBean2Resp(sa, setNotice));
			}
			if(setNotice){
				//set采购信息
				SetPurchaseViewData(listResp, "1");
				//set销售信息
				SetSalesViewData(listResp, "2");
			}
		}
		return listResp;
	}
	
	private AccessRecordResp copyBean2Resp(AccessRecord bean, boolean setNotice) throws Exception {
		AccessRecordResp resp = null;
		if(bean != null){
			resp = new AccessRecordResp();
			PropertyUtils.copyProperties(resp, bean);
//			if(setNotice){
//				groupSetViewData(resp);
//			}
		}
		return resp;
	}
	
//	private AccessRecordResp groupSetViewData(AccessRecordResp resp) throws Exception{
//		if(resp != null){
//			switch (resp.getBusinesstype()) {
//			case "1":
//				//采购
//				setPurchaseViewData(resp);
//				break;
//			case "2":
//				setSalesViewData(resp);
//				break;
//			default:
//				break;
//			}
//		}
//		return resp;
//	}
//
//	//添加采购信息
//	private void setPurchaseViewData(AccessRecordResp resp) throws Exception {
//		PurchaseArriveResp arriveResp = purchaseArriveService.findOne(resp.getNoticeid());
//		resp.setVehicleno(arriveResp.getVehicleno());
//		resp.setMaterielname(arriveResp.getPurchaseApplicationDetailResp().getMaterielname());
//		resp.setRfid(arriveResp.getVehiclerfid());
//		resp.setOtherparty(arriveResp.getPurchaseApplicationResp().getSuppliername());
//		if(StringUtils.isNotBlank(arriveResp.getIcardid())){
//			CardResp card = cardService.findOne(arriveResp.getIcardid());
//			if(card != null){
//				resp.setIcardno(card.getCardno());
//				resp.setIcardcode(card.getCardcode());
//			}
//		}
//	}
//	
//	//添加销售信息
//	private void setSalesViewData(AccessRecordResp resp) throws Exception {
//		SalesArriveResp salesResp = salesArriveService.findOne(resp.getNoticeid());
//		resp.setVehicleno(salesResp.getVehicleno());
//		resp.setMaterielname(salesResp.getSalesApplication().getDetailResp().getMaterielname());
//		resp.setRfid(salesResp.getVehiclerfid());
//		resp.setOtherparty(salesResp.getSalesApplication().getCustomername());
//		if(StringUtils.isNotBlank(salesResp.getIcardid())){
//			CardResp card = cardService.findOne(salesResp.getIcardid());
//			if(card != null){
//				resp.setIcardno(card.getCardno());
//				resp.setIcardcode(card.getCardcode());
//			}
//		}
//	}
	
}
