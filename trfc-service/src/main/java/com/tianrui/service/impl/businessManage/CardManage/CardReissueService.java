package com.tianrui.service.impl.businessManage.CardManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.businessManage.cardManage.ICardReissueService;
import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseArriveService;
import com.tianrui.api.intf.businessManage.salesManage.ISalesArriveService;
import com.tianrui.api.req.businessManage.cardManage.CardReissueReq;
import com.tianrui.api.req.businessManage.logisticsManage.AccessRecordQuery;
import com.tianrui.api.req.businessManage.purchaseManage.PurchaseArriveSave;
import com.tianrui.api.req.businessManage.salesManage.SalesArriveSave;
import com.tianrui.api.resp.businessManage.cardManage.CardReissueResp;
import com.tianrui.api.resp.businessManage.purchaseManage.PurchaseArriveResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesArriveResp;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;
import com.tianrui.service.bean.basicFile.nc.MaterielManage;
import com.tianrui.service.bean.businessManage.cardManage.Card;
import com.tianrui.service.bean.businessManage.logisticsManage.AccessRecord;
import com.tianrui.service.bean.businessManage.poundNoteMaintain.PoundNote;
import com.tianrui.service.bean.quality.sales.SalesBatchnum;
import com.tianrui.service.mapper.basicFile.measure.VehicleManageMapper;
import com.tianrui.service.mapper.basicFile.nc.MaterielManageMapper;
import com.tianrui.service.mapper.businessManage.cardManage.CardMapper;
import com.tianrui.service.mapper.businessManage.logisticsManage.AccessRecordMapper;
import com.tianrui.service.mapper.businessManage.poundNoteMaintain.PoundNoteMapper;
import com.tianrui.service.mapper.quality.sales.SalesBatchnumMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class CardReissueService implements ICardReissueService {
	@Autowired
	private AccessRecordMapper accessRecordMapper;
	@Autowired
	private IPurchaseArriveService purchaseArriveService;
	@Autowired
	private ISalesArriveService salesArriveService;
	@Autowired
	private CardMapper cardMapper;
	@Autowired
	private PoundNoteMapper poundNoteMapper;
	@Autowired
	private SalesBatchnumMapper salesBatchnumMapper;
	@Autowired
	private MaterielManageMapper materielManageMapper;
	@Autowired
	private VehicleManageMapper vehicleManageMapper;

	/**
	 * 获取详情
	 */
	@Override
	public Result getAccessData(CardReissueReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req != null) {
			CardReissueResp resp = new CardReissueResp();
			AccessRecord record = accessRecordMapper.selectByPrimaryKey(req.getId());
			if(record!=null){
				resp.setAccesscode(record.getCode());
				resp.setEntertime(record.getEntertime());
				resp.setNoticecode(record.getNoticecode());
				resp.setBusinesstype(record.getBusinesstype());
				if(StringUtils.isNotBlank(record.getNoticeid())){
					//判断业务类型为  采购 
					if(( "1" ).equals(record.getBusinesstype())){
						//获取通知单详情
						PurchaseArriveResp par = purchaseArriveService.findOne(record.getNoticeid());
						resp.setVehicleno(par.getVehicleno());
						resp.setVehicleid(par.getVehicleid());
						//获取车辆信息
						VehicleManage vm = vehicleManageMapper.selectByPrimaryKey(par.getVehicleid());
						if(vm!=null){
							resp.setVehiclecode(vm.getCode());
						}
						resp.setMaterielname(par.getPurchaseApplicationDetailResp().getMaterielname());
						String materielid = par.getPurchaseApplicationDetailResp().getMaterielid();
						//获取物料信息
						if(StringUtils.isNotBlank(materielid)){
							MaterielManage mm = materielManageMapper.selectByPrimaryKey(materielid);
							if(mm!=null){
								resp.setMaterieltype(mm.getPackagetype());
							}
						}
						resp.setSuppliername(par.getPurchaseApplicationResp().getSuppliername());
						resp.setSupplierid(par.getPurchaseApplicationResp().getSupplierid());
						resp.setSupplierremark(par.getPurchaseApplicationResp().getSupplierremark());
						resp.setRfid(par.getVehiclerfid());
						resp.setMinemouthname(par.getPurchaseApplicationResp().getMinemouthname());
						resp.setApplicationcode(par.getPurchaseApplicationResp().getCode());
						resp.setBilltime(par.getPurchaseApplicationResp().getBilltimeStr());
						resp.setOrgname(par.getPurchaseApplicationResp().getOrgname());
						resp.setDrivername(par.getDrivername());
						resp.setDriveridentityno(par.getDriveridentityno());
						resp.setArrivalamount(par.getArrivalamount());
						resp.setStatus(par.getStatus());
						resp.setNoticeid(par.getId());
						resp.setMakebilltime(par.getPurchaseApplicationResp().getMakebilltimeStr());
						resp.setMakebillname(par.getPurchaseApplicationResp().getMakebillname());
						resp.setDepartmentname(par.getPurchaseApplicationResp().getDepartmentname());
						resp.setApplicationremark(par.getPurchaseApplicationResp().getRemark());
						resp.setPurchasesum(par.getPurchaseApplicationDetailResp().getPurchasesum());
						resp.setPrice(par.getPurchaseApplicationDetailResp().getPrice());
						//获取磅单详情
						String applicationid = par.getId();
						if(StringUtils.isNotBlank(applicationid)){
							PoundNote pound = poundNoteMapper.selectByNoticeId(applicationid);
							if(pound!=null){
								resp.setPoundcode(pound.getCode());
								resp.setWarehousename(pound.getWarehousename());
								resp.setGrossweight(pound.getGrossweight());
								resp.setTareweight(pound.getTareweight());
								resp.setNetweight(pound.getNetweight());
								resp.setWeighername(pound.getWeighername());
								resp.setLighttime(pound.getLighttime());
								resp.setWeighttime(pound.getWeighttime());
							}
						}
						//获取ic卡信息
						if(StringUtils.isNotBlank(par.getIcardid())){
							Card card = cardMapper.selectByPrimaryKey(par.getIcardid());
							resp.setIcardno(card.getCardno());
							resp.setIcardcode(card.getCardcode());
						}
						//判断业务类型为 销售
					}else if(( "2" ).equals(record.getBusinesstype())){
						SalesArriveResp par = salesArriveService.findOne(record.getNoticeid());
						resp.setVehicleno(par.getVehicleno());
						resp.setVehicleid(par.getVehicleid());
						VehicleManage vm = vehicleManageMapper.selectByPrimaryKey(par.getVehicleid());
						if(vm!=null){
							resp.setVehiclecode(vm.getCode());
						}
						resp.setMaterielname(par.getMainApplicationDetail().getMaterielname());
						String materielid = par.getMainApplicationDetail().getMaterielid();
						if(StringUtils.isNotBlank(materielid)){
							MaterielManage mm = materielManageMapper.selectByPrimaryKey(materielid);
							resp.setMaterieltype(mm.getPackagetype());
						}
						resp.setCustomername(par.getMainApplication().getCustomername());
						resp.setCustomerid(par.getMainApplication().getCustomerid());
						resp.setSpraycode(par.getSpraycode());
						resp.setRfid(par.getVehiclerfid());
						resp.setNoticeid(par.getId());
						resp.setApplicationcode(par.getMainApplication().getCode());
						resp.setBilltime(par.getMainApplication().getBilltimeStr());
						resp.setOrgname(par.getMainApplication().getOrgname());
						resp.setDrivername(par.getDrivername());
						resp.setDriveridentityno(par.getDriveridentityno());
						resp.setStatus(par.getStatus());
						resp.setTakeamount(par.getTakeamount());
						resp.setMakebilltime(par.getMainApplication().getMakebilltimeStr());
						resp.setMakebillname(par.getMainApplication().getMakebillname());
						resp.setDepartmentname(par.getMainApplication().getDepartmentname());
						resp.setPrice(par.getMainApplicationDetail().getTaxprice());
						resp.setApplicationremark(par.getMainApplication().getRemarks());
						resp.setPurchasesum(par.getMainApplicationDetail().getSalessum());
						String applicationid = par.getId();
						if(StringUtils.isNotBlank(applicationid)){
							PoundNote pound = poundNoteMapper.selectByNoticeId(applicationid);
							if(pound!=null){
								resp.setPoundcode(pound.getCode());
								resp.setWarehousename(pound.getWarehousename());
								resp.setGrossweight(pound.getGrossweight());
								resp.setTareweight(pound.getTareweight());
								resp.setNetweight(pound.getNetweight());
								resp.setWeighername(pound.getWeighername());
								resp.setLighttime(pound.getLighttime());
								resp.setWeighttime(pound.getWeighttime());
								//获取编号
								if(StringUtils.isNotBlank(pound.getBatchnumberid())){
									SalesBatchnum batch = salesBatchnumMapper.selectByPrimaryKey(pound.getBatchnumberid());
									if(batch!=null){
										resp.setBatchnum(batch.getFactorycode());
									}
								}
							}
						}
						//获取ic卡信息
						if(StringUtils.isNotBlank(par.getIcardid())){
							Card card = cardMapper.selectByPrimaryKey(par.getIcardid());
							resp.setIcardno(card.getCardno());
							resp.setIcardcode(card.getCardcode());
						}
					}
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
			//转换类型
			AccessRecordQuery query = card2AccessQuery(req);
			if(query != null){
				query.setStart((pageNo-1)*pageSize);
				query.setLimit(pageSize);
				//获取数据总数
				long count = accessRecordMapper.findAccessRecordPageCount(query);
				page.setTotal(count);
				page.setPageNo(pageNo);
				page.setPageSize(pageSize);
				List<CardReissueResp> resps = new ArrayList<CardReissueResp>();
				//查询数据
				if(count>0){
					List<AccessRecord> list = accessRecordMapper.findAccessRecordPage(query);
					//转换类型
					access2cardResp(resps,list);
				}
				page.setList(resps);
				rs = Result.getSuccessResult();
				rs.setData(page);
			}else{
				rs.setErrorCode(ErrorCode.CARD_NOT_EXIST);
			}
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
				resp.setAccessid(record.getId());
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
		query.setVehicleid(req.getVehicleid());
		query.setCode(req.getAccesscode());
		query.setBusinesstype(req.getBusinesstype());
		query.setAccesstype(req.getAccesstype());
		if(StringUtils.isNotBlank(req.getIcardno())){
			Card card = cardMapper.selectByCardno(req.getIcardno());
			if(card!=null){
				query.setIcardid(card.getId());
			}else{
				//ic卡未注册 返回null
				query = null;
			}
		}
		return query;
	}

	@Override
	public Result updateCard(CardReissueReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(StringUtils.isNotBlank(req.getIcardno())){
			//获取ic卡信息
			Card card = cardMapper.selectByCardno(req.getIcardno());
			if("1".equals(req.getBusinesstype())){
				//设置参数
				PurchaseArriveSave save = new PurchaseArriveSave();
				save.setId(req.getNoticeid());
				save.setIcardid(card.getId());
				save.setCurrId(req.getUserid());
				//更新数据
				rs = purchaseArriveService.updateOperation(save);
			}else if("2".equals(req.getBusinesstype())){
				SalesArriveSave save = new SalesArriveSave();
				save.setId(req.getNoticeid());
				save.setIcardid(card.getId());
				save.setIcardno(card.getCardno());
				save.setModifier(req.getUserid());
				rs = salesArriveService.updateCardno(save);
			}
		}
		return rs;
	}




}
