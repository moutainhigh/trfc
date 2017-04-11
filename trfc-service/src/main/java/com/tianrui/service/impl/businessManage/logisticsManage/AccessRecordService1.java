package com.tianrui.service.impl.businessManage.logisticsManage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.businessManage.logisticsManage.IAccessRecordService1;
import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseArriveService;
import com.tianrui.api.intf.businessManage.salesManage.ISalesArriveService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.basicFile.measure.VehicleCheckApi;
import com.tianrui.api.req.businessManage.logisticsManage.AccessRecordQuery;
import com.tianrui.api.req.businessManage.salesManage.ApiDoorSystemSave;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.businessManage.logisticsManage.AccessRecordResp;
import com.tianrui.api.resp.businessManage.purchaseManage.PurchaseArriveResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesArriveResp;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;
import com.tianrui.service.bean.businessManage.cardManage.Card;
import com.tianrui.service.bean.businessManage.logisticsManage.AccessRecord;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplicationDetail;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseArrive;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationDetail;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationJoinNatice;
import com.tianrui.service.bean.businessManage.salesManage.SalesArrive;
import com.tianrui.service.bean.common.RFID;
import com.tianrui.service.mapper.basicFile.measure.VehicleManageMapper;
import com.tianrui.service.mapper.businessManage.cardManage.CardMapper;
import com.tianrui.service.mapper.businessManage.logisticsManage.AccessRecordMapper1;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseApplicationDetailMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseArriveMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationDetailMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationJoinNaticeMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesArriveMapper;
import com.tianrui.service.mapper.common.RFIDMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.CommonUtils;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class AccessRecordService1 implements IAccessRecordService1 {

	@Autowired
	private AccessRecordMapper1 accessRecordMapper;
	@Autowired
	private IPurchaseArriveService purchaseArriveService;
	@Autowired
	private PurchaseArriveMapper purchaseArriveMapper;
	@Autowired
	private ISalesArriveService salesArriveService;
	@Autowired
	private SalesArriveMapper salesArriveMapper;
	@Autowired
	private CardMapper cardMapper;
	@Autowired
	private ISystemCodeService systemCodeService;
	@Autowired
	private PurchaseApplicationDetailMapper purchaseApplicationDetailMapper;
	@Autowired
	private SalesApplicationDetailMapper salesApplicationDetailMapper;
	@Autowired
	private SalesApplicationJoinNaticeMapper salesApplicationJoinNaticeMapper;
	@Autowired
	private RFIDMapper rfidMapper;
	@Autowired
	private VehicleManageMapper vehicleManageMapper;
	
	@Override
	public PaginationVO<AccessRecordResp> page(AccessRecordQuery query) throws Exception{
		PaginationVO<AccessRecordResp> page = null;
		if (query != null) {
			page = new PaginationVO<AccessRecordResp>();
			query.setState("1");
			long count = accessRecordMapper.findAccessRecordPageCount(query);
			if (count > 0) {
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
		for (AccessRecordResp resp : list) {
			if (StringUtils.equals(resp.getBusinesstype(), type)) {
				ids.add(resp.getNoticeid());
			}
		}
		List<PurchaseArriveResp> listArrive = purchaseArriveService.selectByIds(ids);
		if (CollectionUtils.isNotEmpty(listArrive)) {
			for (AccessRecordResp resp : list) {
				for (PurchaseArriveResp arriveResp : listArrive) {
					if (StringUtils.equals(resp.getNoticeid(), arriveResp.getId())) {
						resp.setVehicleno(arriveResp.getVehicleno());
						resp.setMaterielname(arriveResp.getPurchaseApplicationDetailResp().getMaterielname());
						resp.setRfid(arriveResp.getVehiclerfid());
						resp.setOtherparty(arriveResp.getPurchaseApplicationResp().getSuppliername());
						if (StringUtils.isNotBlank(arriveResp.getIcardid())) {
							Card card = cardMapper.selectByPrimaryKey(arriveResp.getIcardid());
							if (card != null) {
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
		for (AccessRecordResp resp : list) {
			if (StringUtils.equals(resp.getBusinesstype(), type)) {
				ids.add(resp.getNoticeid());
			}
		}
		List<SalesArriveResp> salesList = salesArriveService.selectByIds(ids);
		if (CollectionUtils.isNotEmpty(salesList)) {
			for (AccessRecordResp resp : list) {
				for (SalesArriveResp salesResp : salesList) {
					if (StringUtils.equals(resp.getNoticeid(), salesResp.getId())) {
						resp.setVehicleno(salesResp.getVehicleno());
						resp.setMaterielname(salesResp.getMainApplicationDetail().getMaterielname());
						resp.setRfid(salesResp.getVehiclerfid());
						resp.setOtherparty(salesResp.getMainApplication().getCustomername());
						if (StringUtils.isNotBlank(salesResp.getIcardid())) {
							Card card = cardMapper.selectByPrimaryKey(salesResp.getIcardid());
							if (card != null) {
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
		if (CollectionUtils.isNotEmpty(list)) {
			listResp = new ArrayList<AccessRecordResp>();
			for (AccessRecord sa : list) {
				listResp.add(copyBean2Resp(sa, setNotice));
			}
			if (setNotice) {
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
		if (bean != null) {
			resp = new AccessRecordResp();
			PropertyUtils.copyProperties(resp, bean);
//			if (setNotice) {
//				groupSetViewData(resp);
//			}
		}
		return resp;
	}

	@Override
	public Result findOne(AccessRecordQuery query) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(query!=null){
			AccessRecord record = accessRecordMapper.selectByPrimaryKey(query.getId());
//			if(record!=null){
//				AccessRecordResp resp = new AccessRecordResp();
//				PropertyUtils.copyProperties(resp, record);
//				if(StringUtils.isNotBlank(record.getNoticeid())){
//					SalesArriveResp sar = salesArriveService.findOne(record.getNoticeid());
//					if(sar!=null){
//						resp.setRfid(sar.getVehiclerfid());
//						resp.setVehicleno(sar.getVehicleno());
//						resp.setOtherparty(sar.getUnit());
//						resp.setIcardno(sar.getIcardno());
//						resp.setSpraycode(sar.getSpraycode());
//					}
//				}
				rs = Result.getSuccessResult();
				rs.setData(record);
//			}
		}
		return rs;
	}
	
//	private AccessRecordResp groupSetViewData(AccessRecordResp resp) throws Exception{
//		if (resp != null) {
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
//		if (StringUtils.isNotBlank(arriveResp.getIcardid())) {
//			CardResp card = cardService.findOne(arriveResp.getIcardid());
//			if (card != null) {
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
//		if (StringUtils.isNotBlank(salesResp.getIcardid())) {
//			CardResp card = cardService.findOne(salesResp.getIcardid());
//			if (card != null) {
//				resp.setIcardno(card.getCardno());
//				resp.setIcardcode(card.getCardcode());
//			}
//		}
//	}
	
	@Transactional
	@Override
	public Result addAccessRecord(ApiDoorSystemSave apiParam) throws Exception {
		Result result = Result.getParamErrorResult();
		if (apiParam!=null && StringUtils.isNotBlank(apiParam.getNotionformcode()) 
				&& StringUtils.isNotBlank(apiParam.getIcardno())
				&& StringUtils.isNotBlank(apiParam.getServicetype())
				&& StringUtils.isNotBlank(apiParam.getType()) 
				&& StringUtils.isNotBlank(apiParam.getTime())
				&& StringUtils.isNotBlank(apiParam.getCurrUid())) {
			Card card = cardMapper.selectByCardno(apiParam.getIcardno());
			if (card != null) {
				ErrorCode ec = ErrorCode.OPERATE_ERROR;
				switch (apiParam.getServicetype()) {
				//采购到货通知单
				case "0":
					ec = setICardToPurchaseArrive(card, apiParam);
					break;
				//采购退货通知单
				case "1":
					ec = setICardToPurchaseReturnArrive(card, apiParam);
					break;
				//销售提货通知单
				case "2":
					ec = setICardToSalesArrive(card, apiParam);
					break;
				default:
					break;
				}
				result.setErrorCode(ec);
			}else{
				result.setErrorCode(ErrorCode.CARD_NOT_EXIST);
			}
		}
		return result;
	}

	private ErrorCode setICardToPurchaseArrive(Card card, ApiDoorSystemSave apiParam) throws Exception {
		ErrorCode ec = ErrorCode.OPERATE_ERROR;
		PurchaseArrive purchase = purchaseArriveMapper.selectByCode(apiParam.getNotionformcode());
		if(StringUtils.equals(purchase.getAuditstatus(), "1")){
			if(!StringUtils.equals(purchase.getStatus(), "3")){
				//入厂
				if(StringUtils.equals(apiParam.getType(), "1")){
					PurchaseArrive pa = purchaseArriveMapper.checkICUse(card.getId());
					if(pa == null){
						//修改通知单状态并绑定IC卡
						pa = new PurchaseArrive();
						pa.setId(purchase.getId());
						pa.setStatus("6");
						pa.setIcardid(card.getId());
						//回写订单的未入库占用量和预提占用
						PurchaseApplicationDetail applicationDetail = purchaseApplicationDetailMapper.selectByPrimaryKey(purchase.getBilldetailid());
						PurchaseApplicationDetail save = new PurchaseApplicationDetail();
						save.setId(applicationDetail.getId());
						save.setUnstoragequantity(applicationDetail.getUnstoragequantity() + purchase.getArrivalamount());
						save.setPretendingtake(applicationDetail.getPretendingtake() - purchase.getArrivalamount());
						if(purchaseArriveMapper.updateByPrimaryKeySelective(pa) > 0
								&& purchaseApplicationDetailMapper.updateByPrimaryKeySelective(save) > 0){
							ec = addInfoAccessRecordApi(apiParam, purchase.getId(), purchase.getCode(), "1");
						}else{
							ec = ErrorCode.OPERATE_ERROR;
						}
					}else{
						ec = ErrorCode.CARD_IN_USE;
					}
				//出厂
				}else{
					//修改通知单状态
					PurchaseArrive pa = new PurchaseArrive();
					pa.setId(purchase.getId());
					pa.setStatus("5");
					if(purchaseArriveMapper.updateByPrimaryKeySelective(pa) > 0){
						AccessRecord access = accessRecordMapper.selectByNoticeId(purchase.getId());
						if(StringUtils.equals(access.getAccesstype(), "1")){
							ec = addOutAccessRecordApi(apiParam, access.getId());
						}else{
							ec = ErrorCode.NOTICE_OUT_FACTORY;
						}
					}else{
						ec = ErrorCode.OPERATE_ERROR;
					}
				}
			}else{
				ec = ErrorCode.NOTICE_ON_INVALID;
			}
		}else{
			ec = ErrorCode.NOTICE_NOT_AUDIT;
		}
		return ec;
	}
	
	private ErrorCode setICardToPurchaseReturnArrive(Card card, ApiDoorSystemSave apiParam) throws Exception {
		ErrorCode ec = ErrorCode.OPERATE_ERROR;
		PurchaseArrive purchase = purchaseArriveMapper.selectByCode(apiParam.getNotionformcode());
		if(StringUtils.equals(purchase.getAuditstatus(), "1")){
			if(!StringUtils.equals(purchase.getStatus(), "3")){
				//入厂
				if(StringUtils.equals(apiParam.getType(), "1")){
					PurchaseArrive pa = purchaseArriveMapper.checkICUse(card.getId());
					if(pa == null){
						//修改通知单状态并绑定IC卡
						pa = new PurchaseArrive();
						pa.setId(purchase.getId());
						pa.setStatus("6");
						pa.setIcardid(card.getId());
						//回写订单的未入库占用量和预提占用
						PurchaseApplicationDetail applicationDetail = purchaseApplicationDetailMapper.selectByPrimaryKey(purchase.getBilldetailid());
						PurchaseApplicationDetail save = new PurchaseApplicationDetail();
						save.setId(applicationDetail.getId());
						save.setPretendingtake(applicationDetail.getPretendingtake() - purchase.getArrivalamount());
						if(purchaseArriveMapper.updateByPrimaryKeySelective(pa) > 0
								&& purchaseApplicationDetailMapper.updateByPrimaryKeySelective(save) > 0){
							ec = addInfoAccessRecordApi(apiParam, purchase.getId(), purchase.getCode(), "1");
						}else{
							ec = ErrorCode.OPERATE_ERROR;
						}
					}else{
						ec = ErrorCode.CARD_IN_USE;
					}
				//出厂
				}else{
					//修改通知单状态
					PurchaseArrive pa = new PurchaseArrive();
					pa.setId(purchase.getId());
					pa.setStatus("5");
					if(purchaseArriveMapper.updateByPrimaryKeySelective(pa) > 0){
						AccessRecord access = accessRecordMapper.selectByNoticeId(purchase.getId());
						if(StringUtils.equals(access.getAccesstype(), "1")){
							ec = addOutAccessRecordApi(apiParam, access.getId());
						}else{
							ec = ErrorCode.NOTICE_OUT_FACTORY;
						}
					}else{
						ec = ErrorCode.OPERATE_ERROR;
					}
				}
			}else{
				ec = ErrorCode.NOTICE_ON_INVALID;
			}
		}else{
			ec = ErrorCode.NOTICE_NOT_AUDIT;
		}
		return ec;
	}

	private ErrorCode setICardToSalesArrive(Card card, ApiDoorSystemSave apiParam) throws Exception {
		ErrorCode ec = ErrorCode.OPERATE_ERROR;
		SalesArrive sales = salesArriveMapper.selectByCode(apiParam.getNotionformcode());
		if(StringUtils.equals(sales.getAuditstatus(), "1")){
			if(!StringUtils.equals(sales.getStatus(), "3")){
				//入厂
				if(StringUtils.equals(apiParam.getType(), "1")){
					SalesArrive sa = salesArriveMapper.checkICUse(card.getId());
					if(sa == null){
						//修改通知单状态并绑定IC卡
						sa = new SalesArrive();
						sa.setId(sales.getId());
						sa.setStatus("6");
						sa.setIcardid(card.getId());
						sa.setIcardno(card.getCardno());
						if(salesArriveMapper.updateByPrimaryKeySelective(sa) > 0){
							//回写订单的未入库占用量和预提占用
							List<SalesApplicationJoinNatice> list = salesApplicationJoinNaticeMapper.selectByNaticeId(sales.getId());
							if(CollectionUtils.isNotEmpty(list)){
								boolean flag = false;
								Double takeamount = sales.getTakeamount();
								for(SalesApplicationJoinNatice join : list){
									SalesApplicationDetail applicationDetail = salesApplicationDetailMapper.selectByPrimaryKey(join.getBilldetailid());
									if(applicationDetail != null && takeamount > 0){
										if(takeamount > join.getMargin()){
											SalesApplicationDetail sd = new SalesApplicationDetail();
											sd.setId(applicationDetail.getId());
											sd.setUnstoragequantity(applicationDetail.getUnstoragequantity() + join.getMargin());
											sd.setPretendingtake(applicationDetail.getPretendingtake() - join.getMargin());
											if(salesApplicationDetailMapper.updateByPrimaryKeySelective(sd) > 0){
												flag = true;
											}else{
												flag = false;
												break;
											}
											takeamount -= join.getMargin();
										}else{
											SalesApplicationDetail sd = new SalesApplicationDetail();
											sd.setId(applicationDetail.getId());
											sd.setUnstoragequantity(applicationDetail.getUnstoragequantity() + takeamount);
											sd.setPretendingtake(applicationDetail.getPretendingtake() - takeamount);
											if(salesApplicationDetailMapper.updateByPrimaryKeySelective(sd) > 0){
												flag = true;
											}else{
												flag = false;
												break;
											}
											takeamount = 0D;
										}
									}
								}
								if(flag){
									ec = addInfoAccessRecordApi(apiParam, sales.getId(), sales.getCode(), "2");
								}else{
									ec = ErrorCode.OPERATE_ERROR;
								}
							}	
						}else{
							ec = ErrorCode.OPERATE_ERROR;
						}
					}else{
						ec = ErrorCode.CARD_IN_USE;
					}
				//出厂
				}else{
					//修改通知单状态并绑定IC卡
					SalesArrive sa = new SalesArrive();
					sa.setId(sales.getId());
					sa.setStatus("5");
					if(salesArriveMapper.updateByPrimaryKeySelective(sa) > 0){
						AccessRecord access = accessRecordMapper.selectByNoticeId(sales.getId());
						if(access != null){
							ec = addOutAccessRecordApi(apiParam, access.getId());
						}else{
							ec = ErrorCode.VEHICLE_NOTICE_NOT_ENTER;
						}
					}else{
						ec = ErrorCode.OPERATE_ERROR;
					}
				}
			}else{
				ec = ErrorCode.NOTICE_ON_INVALID;
			}
		}else{
			ec = ErrorCode.NOTICE_NOT_AUDIT;
		}
		return ec;
	}

	//添加入厂门禁记录
	private ErrorCode addInfoAccessRecordApi(ApiDoorSystemSave apiParam, String noticeid, String noticecode, String businesstype) throws Exception {
		ErrorCode ec;
		AccessRecord access = new AccessRecord();
		access.setId(UUIDUtil.getId());
		GetCodeReq codeReq = new GetCodeReq();
		codeReq.setCode("ZW");
		codeReq.setCodeType(true);
		codeReq.setUserid(apiParam.getCurrUid());
		access.setCode(systemCodeService.getCode(codeReq).getData().toString());
		access.setBusinesstype(businesstype);
		access.setAccesstype("1");
		access.setNoticeid(noticeid);
		access.setNoticecode(noticecode);
		access.setEntersource("");
		access.setEntertime(DateUtil.parse(apiParam.getTime(), "yyyy-MM-dd HH:mm:ss"));
		access.setState("1");
		access.setCreator(apiParam.getCurrUid());
		access.setCreatetime(System.currentTimeMillis());
		access.setModifier(apiParam.getCurrUid());
		access.setModifytime(System.currentTimeMillis());
		if(accessRecordMapper.insertSelective(access) > 0
				&& StringUtils.equals(systemCodeService.updateCodeItem(codeReq).getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())){
			ec = ErrorCode.SYSTEM_SUCCESS;
		}else{
			ec = ErrorCode.OPERATE_ERROR;
		}
		return ec;
	}
	
	//添加出厂门禁记录
	private ErrorCode addOutAccessRecordApi(ApiDoorSystemSave apiParam, String accessId) {
		ErrorCode ec;
		AccessRecord ar = new AccessRecord();
		ar.setId(accessId);
		ar.setAccesstype("2");
		ar.setOutsource("");
		ar.setOuttime(DateUtil.parse(apiParam.getTime(), "yyyy-MM-dd HH:mm:ss"));
		ar.setModifier(apiParam.getCurrUid());
		ar.setModifytime(System.currentTimeMillis());
		if(accessRecordMapper.updateByPrimaryKeySelective(ar) > 0){
			ec = ErrorCode.SYSTEM_SUCCESS;
		}else{
			ec = ErrorCode.OPERATE_ERROR;
		}
		return ec;
	}
	
	@Override
	public Result leaveFactoryCheckApi(VehicleCheckApi checkApi) {
		Result result = Result.getParamErrorResult();
		if (checkApi != null 
				&& StringUtils.isNotBlank(checkApi.getVehicleNo()) 
				&& StringUtils.isNotBlank(checkApi.getRfid())) {
			if (validateRFID(checkApi.getRfid())) {
				if (validateVehicle(checkApi.getVehicleNo(), checkApi.getRfid())) {
					Map<String, Object> map = validateHasBill(checkApi.getVehicleNo());
					if (map != null && map.size() > 0) {
						Object object = map.get("notice");
						if (!StringUtils.equals(CommonUtils.method(object, "status").toString(), "3")) {
							if (StringUtils.equals(CommonUtils.method(object, "auditstatus").toString(), "1")) {
								if (StringUtils.equals(CommonUtils.method(object, "status").toString(), "2")) {
									//result.setData(map);
									result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
								} else {
									result.setErrorCode(ErrorCode.VEHICLE_NOTICE_NOT_TWO_WEIGHT);
								}
							} else {
								result.setErrorCode(ErrorCode.NOTICE_NOT_AUDIT);
							}
						} else {
							result.setErrorCode(ErrorCode.NOTICE_ON_INVALID);
						}
					} else {
						result.setErrorCode(ErrorCode.VEHICLE_NOT_NOTICE);
					}
				} else {
					result.setErrorCode(ErrorCode.RFID_VEHICLE_NOT_EXIST);
				}
			} else {
				result.setErrorCode(ErrorCode.RFID_NOT_EXIST);
			}
		}
		return result;
	}
	/**
	 * @Description 入厂验证
	 * @author zhanggaohao
	 * @version 2017年3月2日 下午3:49:33
	 * @param checkApi
	 * @return
	 */
	@Override
	public Result enterFactoryCheckApi(VehicleCheckApi checkApi) {
		Result result = Result.getParamErrorResult();
		if (checkApi != null 
				&& StringUtils.isNotBlank(checkApi.getVehicleNo()) 
				&& StringUtils.isNotBlank(checkApi.getRfid())) {
			if (validateRFID(checkApi.getRfid())) {
				if (validateVehicle(checkApi.getVehicleNo(), checkApi.getRfid())) {
					Map<String, Object> map = validateHasBill(checkApi.getVehicleNo());
					if (map != null && map.size() > 0) {
						Object object = map.get("notice");
						if (!StringUtils.equals(CommonUtils.method(object, "status").toString(), "3")) {
							if (StringUtils.equals(CommonUtils.method(object, "auditstatus").toString(), "1")) {
								if (StringUtils.equals(CommonUtils.method(object, "status").toString(), "0")) {
									result.setData(map.get("type"));
									result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
								} else {
									result.setErrorCode(ErrorCode.VEHICLE_NOTICE_ALREADY_ENTER);
								}
							} else {
								result.setErrorCode(ErrorCode.NOTICE_NOT_AUDIT);
							}
						} else {
							result.setErrorCode(ErrorCode.NOTICE_ON_INVALID);
						}
					} else {
						result.setErrorCode(ErrorCode.VEHICLE_NOT_NOTICE);
					}
				} else {
					result.setErrorCode(ErrorCode.RFID_VEHICLE_NOT_EXIST);
				}
			} else {
				result.setErrorCode(ErrorCode.RFID_NOT_EXIST);
			}
		}
		return result;
	}
	/**
	 * @Description 验证RFID是否已注册
	 * @author zhanggaohao
	 * @version 2017年3月2日 上午9:36:57
	 * @param rfid
	 * @return
	 */
	private boolean validateRFID(String rfid) {
		boolean flag = false;
		if (StringUtils.isNotBlank(rfid)) {
			RFID bean = rfidMapper.validateRFID(rfid);
			if (bean != null) {
				flag = true;
			}
		}
		return flag;
	}
	/**
	 * @Description 验证车牌号是否与RFID已绑定
	 * @author zhanggaohao
	 * @version 2017年3月2日 上午9:48:26
	 * @param vehicleno
	 * @param rfid
	 * @return
	 */
	private boolean validateVehicle(String vehicleno, String rfid) {
		boolean flag = false;
		if (StringUtils.isNotBlank(vehicleno) && StringUtils.isNotBlank(rfid)) {
			VehicleManage bean = vehicleManageMapper.validateVehicle(vehicleno, rfid);
			if (bean != null) {
				flag = true;
			}
		}
		return flag;
	}
	/**
	 * @Description 验证是否有通知单
	 * @author zhanggaohao
	 * @version 2017年3月2日 上午9:52:35
	 * @param vehicleNo
	 * @return
	 */
	private Map<String, Object> validateHasBill(String vehicleno) {
		Map<String, Object> map = null;
		if (StringUtils.isNotBlank(vehicleno)) {
			map = new HashMap<String, Object>();
			PurchaseArrive purchaseArrive = hasPurchaseArrive(vehicleno);
			//判断是否有采购到货通知单
			if (purchaseArrive == null) {
				SalesArrive salesArrive = hasSalesArrive(vehicleno);
				//判断是否有销售通知单
				if (salesArrive == null) {
					//
				} else {
					map.put("type", 2);
					map.put("notice", salesArrive);
				}
			} else {
				map.put("type", 0);
				map.put("notice", purchaseArrive);
			}
		}
		return map;
	}
	/**
	 * @Description 验证是否有采购到货通知单
	 * @author zhanggaohao
	 * @version 2017年3月2日 上午9:57:02
	 * @param vehicleno
	 * @return
	 */
	private PurchaseArrive hasPurchaseArrive(String vehicleno) {
		PurchaseArrive bean = null;
		if (StringUtils.isNotBlank(vehicleno)) {
			bean = purchaseArriveMapper.hasPurchaseArrive(vehicleno);
		}
		return bean;
	}
	/**
	 * @Description 验证是否有销售提货通知单
	 * @author zhanggaohao
	 * @version 2017年3月2日 上午10:31:40
	 * @param vehicleno
	 * @return
	 */
	private SalesArrive hasSalesArrive(String vehicleno) {
		SalesArrive bean = null;
		if (StringUtils.isNotBlank(vehicleno)) {
			bean = salesArriveMapper.hasPurchaseArrive(vehicleno);
		}
		return bean;
	}
}
