package com.tianrui.service.impl.businessManage.salesManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.intf.basicFile.measure.IDriverManageService;
import com.tianrui.api.intf.basicFile.measure.IVehicleManageService;
import com.tianrui.api.intf.businessManage.cardManage.ICardService;
import com.tianrui.api.intf.businessManage.salesManage.ISalesApplicationDetailService;
import com.tianrui.api.intf.businessManage.salesManage.ISalesApplicationService;
import com.tianrui.api.intf.businessManage.salesManage.ISalesArriveService;
import com.tianrui.api.intf.system.auth.ISystemUserService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.businessManage.salesManage.ApiDoorQueueQuery;
import com.tianrui.api.req.businessManage.salesManage.ApiSalesArriveQuery;
import com.tianrui.api.req.businessManage.salesManage.SalesArriveQuery;
import com.tianrui.api.req.businessManage.salesManage.SalesArriveSave;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.basicFile.measure.DriverManageResp;
import com.tianrui.api.resp.basicFile.measure.VehicleManageResp;
import com.tianrui.api.resp.businessManage.cardManage.CardResp;
import com.tianrui.api.resp.businessManage.poundNoteMaintain.PoundNoteResp;
import com.tianrui.api.resp.businessManage.salesManage.ApiDoorQueueResp;
import com.tianrui.api.resp.businessManage.salesManage.ApiSalesArriveResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationDetailResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesArriveResp;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;
import com.tianrui.service.bean.businessManage.cardManage.Card;
import com.tianrui.service.bean.businessManage.logisticsManage.AccessRecord;
import com.tianrui.service.bean.businessManage.poundNoteMaintain.PoundNote;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplication;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplicationDetail;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseArrive;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationDetail;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationJoinNatice;
import com.tianrui.service.bean.businessManage.salesManage.SalesArrive;
import com.tianrui.service.bean.common.RFID;
import com.tianrui.service.mapper.basicFile.measure.VehicleManageMapper;
import com.tianrui.service.mapper.businessManage.cardManage.CardMapper;
import com.tianrui.service.mapper.businessManage.logisticsManage.AccessRecordMapper1;
import com.tianrui.service.mapper.businessManage.poundNoteMaintain.PoundNoteMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseApplicationDetailMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseApplicationMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseArriveMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationDetailMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationJoinNaticeMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesArriveMapper;
import com.tianrui.service.mapper.common.RFIDMapper;
import com.tianrui.service.mongo.impl.CodeGenDaoImpl;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 销售提货通知单Service
 * @author zhanggaohao
 * @createtime 2017年1月9日 下午2:22:43
 * @classname SalesArriveService.java
 */
@Service
public class SalesArriveService implements ISalesArriveService {

	@Autowired
	private SalesArriveMapper salesArriveMapper;
	@Autowired
	private ISalesApplicationService salesApplicationService;
	@Autowired
	private ISalesApplicationDetailService salesApplicationDetailService;
	@Autowired
	private SalesApplicationDetailMapper salesApplicationDetailMapper;
	@Autowired
	private VehicleManageMapper vehicleManageMapper;
	@Autowired
	private RFIDMapper rfidMapper;
	@Autowired
	private CodeGenDaoImpl codeGenDaoImpl;
	@Autowired
	private ISystemUserService systemUserService;
	@Autowired
	private ISystemCodeService systemCodeService;
	@Autowired
	private PurchaseArriveMapper purchaseArriveMapper;
	@Autowired
	private IVehicleManageService vehicleManageService;
	@Autowired
	private IDriverManageService driverManageService;
	@Autowired
	private ICardService cardService;
	@Autowired
	private CardMapper cardMapper;
	@Autowired
	private SalesApplicationJoinNaticeMapper salesApplicationJoinNaticeMapper;
	@Autowired
	private PurchaseApplicationMapper purchaseApplicationMapper;
	@Autowired
	private PurchaseApplicationDetailMapper purchaseApplicationDetailMapper;
	@Autowired
	private PoundNoteMapper poundNoteMapper;
	@Autowired
	private AccessRecordMapper1 accessRecordMapper1;
	
	@Override
	public PaginationVO<SalesArriveResp> page(SalesArriveQuery query) throws Exception {
		PaginationVO<SalesArriveResp> page = null;
		if(query != null){
			page = new PaginationVO<SalesArriveResp>();
			query.setState("1");
			long count = salesArriveMapper.findSalesArrivePageCount(query);
			if(count > 0){
				query.setStart((query.getPageNo() - 1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				List<SalesArrive> list = salesArriveMapper.findSalesArrivePage(query);
				List<SalesArriveResp> listResp = copyBeanList2RespList(list, false);
				ListArriveRespSetListApplicationResp(listResp);
				page.setList(listResp);
			}
			page.setTotal(count);
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
		}
		return page;
	}
	
	@Transactional
	@Override
	public Result add(SalesArriveSave save, String bills) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null && StringUtils.isNotBlank(save.getBillid()) 
				&& StringUtils.isNotBlank(save.getVehicleid())
				&& StringUtils.isNotBlank(save.getBilldetailid())){
			SalesArrive bean = new SalesArrive();
			bean.setVehicleid(save.getVehicleid());
			List<SalesArrive> listVehicle = salesArriveMapper.checkDriverAndVehicleIsUse(bean);
			if(listVehicle != null && listVehicle.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				result.setError("此车辆己有提货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listVehicle.get(0).getCode()+"，如有疑问请与销售处联系！");
				return result;
			}
			PurchaseArrive pa = new PurchaseArrive();
			pa.setVehicleid(save.getVehicleid());
			List<PurchaseArrive> listVehicle1 = purchaseArriveMapper.checkDriverAndVehicleIsUse(pa);
			if(listVehicle1 != null && listVehicle1.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				result.setError("此车辆己有到货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listVehicle1.get(0).getCode()+"，如有疑问请与销售处联系！");
				return result;
			}
			bean.setVehicleid(null);
			bean.setDriverid(save.getDriverid());
			List<SalesArrive> listDriver = salesArriveMapper.checkDriverAndVehicleIsUse(bean);
			if(listDriver != null && listDriver.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				result.setError("此司机己有提货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listDriver.get(0).getCode()+"，如有疑问请与销售处联系！");
				return result;
			}
			pa.setVehicleid(null);
			pa.setDriverid(save.getDriverid());
			List<PurchaseArrive> listDriver1 = purchaseArriveMapper.checkDriverAndVehicleIsUse(pa);
			if(listDriver1 != null && listDriver1.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				result.setError("此司机己有到货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listDriver1.get(0).getCode()+"，如有疑问请与销售处联系！");
				return result;
			}
			PropertyUtils.copyProperties(bean, save);
			bean.setId(UUIDUtil.getId());
			VehicleManageResp vehicle = vehicleManageService.findOne(save.getVehicleid());
			if(vehicle != null){
				bean.setVehicleno(vehicle.getVehicleno());
				bean.setVehiclerfid(vehicle.getRfid());
			}
			DriverManageResp driver = driverManageService.findOne(save.getDriverid());
			if(driver != null){
				bean.setDrivername(driver.getName());
				bean.setDriveridentityno(driver.getIdentityno());
			}
			Card card = cardMapper.selectByCardno(save.getIcardno());
			if(card!=null){
				bean.setIcardid(card.getId());
				bean.setIcardno(save.getIcardno());
			}
			SalesApplicationResp salesApplicationResp = salesApplicationService.findOne(save.getBillid(), false);
			if(salesApplicationResp != null){
				bean.setBillcode(salesApplicationResp.getCode());
			}
			SalesApplicationDetailResp salesApplicationDetailResp = salesApplicationDetailService.findOne(save.getBilldetailid());
			if(salesApplicationDetailResp != null){
				bean.setUnit(salesApplicationDetailResp.getUnit());
			}
			GetCodeReq codeReq = new GetCodeReq();
			codeReq.setCode("TH");
			codeReq.setCodeType(true);
			codeReq.setUserid(save.getCurrUId());
			bean.setCode(systemCodeService.getCode(codeReq).getData().toString());
			bean.setAuditstatus("0");
			bean.setStatus("0");
			bean.setState("1");
			bean.setSource("0");
			bean.setMakerid(save.getCurrUId());
			bean.setMakebillname(systemUserService.getUser(save.getCurrUId()).getName());
			bean.setCreator(save.getCurrUId());
			bean.setCreatetime(System.currentTimeMillis());
			bean.setModifier(save.getCurrUId());
			bean.setModifytime(System.currentTimeMillis());
			JSONArray array = JSONArray.parseArray(bills);
			List<SalesApplicationJoinNatice> list = new ArrayList<SalesApplicationJoinNatice>();
			boolean flag = false;
			if(array != null && array.size() > 0){
				Double takeamount = bean.getTakeamount();
				for(Object object : array){
					SalesApplicationJoinNatice join = new SalesApplicationJoinNatice();
					JSONObject jsonObject = (JSONObject) object;
					String billid = jsonObject.getString("billid");
					String billdetailid = jsonObject.getString("billdetailid");
					join.setId(UUIDUtil.getId());
					join.setBillid(billid);
					join.setBilldetailid(billdetailid);
					join.setNaticeid(bean.getId());
					join.setTakeamount(bean.getTakeamount());
					join.setState("1");
					join.setCreator(bean.getCreator());
					join.setCreatetime(System.currentTimeMillis());
					join.setModifier(bean.getModifier());
					join.setModifytime(System.currentTimeMillis());
					list.add(join);
//					SalesApplicationResp application = salesApplicationService.findOne(billid, false);
					SalesApplicationDetailResp applicationDetailResp = salesApplicationDetailService.findOne(billdetailid);
					if(applicationDetailResp != null && takeamount > 0){
						join.setBillsum(applicationDetailResp.getSalessum());
						join.setMargin(applicationDetailResp.getMargin());
						join.setOutstoragequantity(applicationDetailResp.getStoragequantity());
						join.setUnoutstoragequantity(applicationDetailResp.getUnstoragequantity());
						join.setPretendingtake(applicationDetailResp.getPretendingtake());
						//回写订单预提占用
						if(takeamount > applicationDetailResp.getMargin()){
							SalesApplicationDetail applicationDetail = new SalesApplicationDetail();
							applicationDetail.setId(applicationDetailResp.getId());
							applicationDetail.setMargin(applicationDetailResp.getMargin() - applicationDetailResp.getMargin());
							applicationDetail.setPretendingtake(applicationDetailResp.getPretendingtake() + applicationDetailResp.getMargin());
							if(salesApplicationDetailMapper.updateByPrimaryKeySelective(applicationDetail) > 0){
								flag = true;
							}else{
								flag = false;
								break;
							}
							takeamount -= applicationDetailResp.getMargin();
						}else{
							SalesApplicationDetail applicationDetail = new SalesApplicationDetail();
							applicationDetail.setId(applicationDetailResp.getId());
							applicationDetail.setMargin(applicationDetailResp.getMargin() - takeamount);
							applicationDetail.setPretendingtake(applicationDetailResp.getPretendingtake() + takeamount);
							if(salesApplicationDetailMapper.updateByPrimaryKeySelective(applicationDetail) > 0){
								flag = true;
							}else{
								flag = false;
								break;
							}
							takeamount = 0D;
						}
					}
				}
			}
			if(flag && salesArriveMapper.insertSelective(bean) > 0 
					&& StringUtils.equals(systemCodeService.updateCodeItem(codeReq).getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())
					&& salesApplicationJoinNaticeMapper.insertBatch(list) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	@Override
	public Result updateCardno(SalesArriveSave save) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(StringUtils.isNotBlank(save.getId())){
			SalesArrive arrive = new SalesArrive();
			arrive.setId(save.getId());
			arrive.setIcardid(save.getIcardid());
			arrive.setIcardno(save.getIcardno());
			arrive.setModifytime(System.currentTimeMillis());
			arrive.setModifier(save.getModifier());
			int index = salesArriveMapper.updateByPrimaryKeySelective(arrive);
			if(index==1){
				rs = Result.getSuccessResult();
			}else{
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}
	
	@Override
	public Result update(SalesArriveSave save, String bills) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null && StringUtils.isNotBlank(save.getBillid()) 
				&& StringUtils.isNotBlank(save.getVehicleid())){
			SalesArrive bean = new SalesArrive();
			bean.setId(save.getId());
			bean.setVehicleid(save.getVehicleid());
			List<SalesArrive> listVehicle = salesArriveMapper.checkDriverAndVehicleIsUse(bean);
			if(listVehicle != null && listVehicle.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				result.setError("此车辆己有提货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listVehicle.get(0).getCode()+"，如有疑问请与销售处联系！");
				return result;
			}
			PurchaseArrive pa = new PurchaseArrive();
			pa.setVehicleid(save.getVehicleid());
			List<PurchaseArrive> listVehicle1 = purchaseArriveMapper.checkDriverAndVehicleIsUse(pa);
			if(listVehicle1 != null && listVehicle1.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				result.setError("此车辆己有到货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listVehicle1.get(0).getCode()+"，如有疑问请与销售处联系！");
				return result;
			}
			bean.setVehicleid(null);
			bean.setDriverid(save.getDriverid());
			List<SalesArrive> listDriver = salesArriveMapper.checkDriverAndVehicleIsUse(bean);
			if(listDriver != null && listDriver.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				result.setError("此司机己有提货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listDriver.get(0).getCode()+"，如有疑问请与销售处联系！");
				return result;
			}
			pa.setVehicleid(null);
			pa.setDriverid(save.getDriverid());
			List<PurchaseArrive> listDriver1 = purchaseArriveMapper.checkDriverAndVehicleIsUse(pa);
			if(listDriver1 != null && listDriver1.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				result.setError("此司机己有到货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listDriver1.get(0).getCode()+"，如有疑问请与销售处联系！");
				return result;
			}
			PropertyUtils.copyProperties(bean, save);
			VehicleManageResp vehicle = vehicleManageService.findOne(save.getVehicleid());
			if(vehicle != null){
				bean.setVehicleno(vehicle.getVehicleno());
				bean.setVehiclerfid(vehicle.getRfid());
			}
			DriverManageResp driver = driverManageService.findOne(save.getDriverid());
			if(driver != null){
				bean.setDrivername(driver.getName());
				bean.setDriveridentityno(driver.getIdentityno());
			}
			CardResp card = cardService.findOne(save.getIcardid());
			if(card != null){
				bean.setIcardno(card.getCardno());
			}
			SalesApplicationResp salesApplicationResp = salesApplicationService.findOne(save.getBillid(), false);
			if(salesApplicationResp != null){
				bean.setBillcode(salesApplicationResp.getCode());
			}
			SalesApplicationDetailResp salesApplicationDetailResp = salesApplicationDetailService.findOne(save.getBilldetailid());
			if(salesApplicationDetailResp != null){
				bean.setUnit(salesApplicationDetailResp.getUnit());
			}
			bean.setModifier(save.getCurrUId());
			bean.setModifytime(save.getCreatetime());
			if(salesArriveMapper.updateByPrimaryKeySelective(bean) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Override
	public SalesArriveResp findOne(String id) throws Exception {
		SalesArriveResp resp = null;
		if(StringUtils.isNotBlank(id)){
			resp = copyBean2Resp(salesArriveMapper.selectByPrimaryKey(id), true);
		}
		return resp;
	}
	
	@Override
	public List<SalesArriveResp> selectByIds(List<String> ids) throws Exception{
		List<SalesArriveResp> listResp = null;
		if(CollectionUtils.isNotEmpty(ids)){
			listResp = copyBeanList2RespList(salesArriveMapper.selectByIds(ids), false);
			ListArriveRespSetListApplicationResp(listResp);
		}
		return listResp;
	}
	
	private void ListArriveRespSetListApplicationResp(List<SalesArriveResp> listArrive) throws Exception{
		if(CollectionUtils.isNotEmpty(listArrive)){
			List<String> ids = new ArrayList<String>();
			List<String> detailIds = new ArrayList<String>();
			for(SalesArriveResp resp : listArrive){
				List<SalesApplicationJoinNatice> listJoin = salesApplicationJoinNaticeMapper.selectByNaticeId(resp.getId());
				for(SalesApplicationJoinNatice join : listJoin){
					ids.add(join.getBillid());
					detailIds.add(join.getBilldetailid());
					//获取磅单信息
					PoundNote pound = poundNoteMapper.selectByNoticeId(resp.getId());
					if(pound!=null){
						PoundNoteResp poundResp = new PoundNoteResp();
						PropertyUtils.copyProperties(poundResp, pound);
						resp.setPoundNoteResp(poundResp);
					}
					//获取出入厂时间
					AccessRecord access = accessRecordMapper1.selectByNoticeId(resp.getId());
					if(access!=null){
						resp.setEnterTime(access.getEntertime());
						resp.setOutTime(access.getOuttime());
					}
				}
				List<SalesApplicationResp> listApplication = salesApplicationService.selectByIds(ids, false);
				List<SalesApplicationDetailResp> listApplicationDetail = salesApplicationDetailService.selectByIds(detailIds);
				if(CollectionUtils.isNotEmpty(listApplication)){
					for(SalesArriveResp arriveResp : listArrive){
						List<SalesApplicationResp> list = arriveResp.getListApplication();
						if(CollectionUtils.isEmpty(list)){
							list = new ArrayList<SalesApplicationResp>();
						}
						for(SalesApplicationJoinNatice join : listJoin){
							if(StringUtils.equals(arriveResp.getId(), join.getNaticeid())){
								SalesApplicationResp application = null;
								for(SalesApplicationResp applicationResp : listApplication){
									if(StringUtils.equals(applicationResp.getId(), join.getBillid())){
										application = applicationResp;
									}
								}
								if(application != null){
									List<SalesApplicationDetailResp> listDetail = application.getList();
									if(CollectionUtils.isEmpty(listDetail)){
										listDetail = new ArrayList<SalesApplicationDetailResp>();
									}
									for(SalesApplicationDetailResp detailResp : listApplicationDetail){
										if(StringUtils.equals(detailResp.getId(), join.getBilldetailid())){
											listDetail.add(detailResp);
											application.setList(listDetail);
										}
									}
								}
								list.add(application);
							}
						}
						arriveResp.setListApplication(list);
					}
				}
				/*if(CollectionUtils.isNotEmpty(listApplicationDetail)){
					for(SalesArriveResp arriveResp : listArrive){
						List<SalesApplicationResp> list = arriveResp.getListApplication();
						for(SalesApplicationJoinNatice join : listJoin){
							if(StringUtils.equals(arriveResp.getId(), join.getNaticeid())){
								for(SalesApplicationDetailResp applicationDetailResp : listApplicationDetail){
									if(StringUtils.equals(applicationDetailResp.getId(), join.getBilldetailid())){
										arriveResp.setSalesApplicationDetail(applicationDetailResp);
									}
								}
							}
						}
					}
				}*/
			}
		}
	}
	
	private List<SalesArriveResp> copyBeanList2RespList(List<SalesArrive> list, boolean setApplication) throws Exception {
		List<SalesArriveResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<SalesArriveResp>();
			for(SalesArrive sa : list){
				listResp.add(copyBean2Resp(sa, setApplication));
			}
		}
		return listResp;
	}
	
	private SalesArriveResp copyBean2Resp(SalesArrive bean, boolean setApplication) throws Exception {
		SalesArriveResp resp = null;
		if(bean != null){
			resp = new SalesArriveResp();
			PropertyUtils.copyProperties(resp, bean);
			if(setApplication){
				List<SalesApplicationJoinNatice> list = salesApplicationJoinNaticeMapper.selectByNaticeId(resp.getId());
				if(CollectionUtils.isNotEmpty(list)){
					for(SalesApplicationJoinNatice join : list){
						SalesApplicationResp salesApplicationResp = salesApplicationService.findOne(join.getBillid(), false);
						if(salesApplicationResp != null){
							List<SalesApplicationDetailResp> listApplcationDetail = new ArrayList<SalesApplicationDetailResp>();
							listApplcationDetail.add(salesApplicationDetailService.findOne(join.getBilldetailid()));
							salesApplicationResp.setList(listApplcationDetail);
						}
						List<SalesApplicationResp> listApplication = resp.getListApplication();
						if(CollectionUtils.isEmpty(listApplication)){
							listApplication = new ArrayList<SalesApplicationResp>();
						}
						listApplication.add(salesApplicationResp);
						resp.setListApplication(listApplication);
					}
				}
			}
		}
		return resp;
	}

	@Override
	public Result audit(SalesArriveQuery query) {
		Result result = Result.getParamErrorResult();
		if(query != null && StringUtils.isNotBlank(query.getId())){
			SalesArrive sa = new SalesArrive();
			sa.setId(query.getId());
			sa.setAuditstatus("1");
			sa.setModifier(query.getCurrUId());
			sa.setModifytime(System.currentTimeMillis());
			if(salesArriveMapper.updateByPrimaryKeySelective(sa) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Override
	public Result unaudit(SalesArriveQuery query) {
		Result result = Result.getParamErrorResult();
		if(query != null && StringUtils.isNotBlank(query.getId())){
			SalesArrive sa = new SalesArrive();
			sa.setId(query.getId());
			sa.setAuditstatus("0");
			sa.setModifier(query.getCurrUId());
			sa.setModifytime(System.currentTimeMillis());
			if(salesArriveMapper.updateByPrimaryKeySelective(sa) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Override
	public Result invalid(SalesArriveQuery query) throws Exception {
		Result result = Result.getParamErrorResult();
		if(query != null && StringUtils.isNotBlank(query.getId())){
			SalesArrive sa = new SalesArrive();
			sa.setId(query.getId());
			sa.setStatus("3");
			sa.setAbnormalperson(query.getCurrUId());
			sa.setAbnormalpersonname(systemUserService.getUser(query.getCurrUId()).getName());
			sa.setAbnormaltime(System.currentTimeMillis());
			sa.setModifier(query.getCurrUId());
			sa.setModifytime(System.currentTimeMillis());
			if(salesArriveMapper.updateByPrimaryKeySelective(sa) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Override
	public Result outfactory(SalesArriveQuery query) throws Exception {
		Result result = Result.getParamErrorResult();
		if(query != null && StringUtils.isNotBlank(query.getId())){
			SalesArrive sa = new SalesArrive();
			sa.setId(query.getId());
			sa.setStatus("5");
			sa.setAbnormalperson(query.getCurrUId());
			sa.setAbnormalpersonname(systemUserService.getUser(query.getCurrUId()).getName());
			sa.setAbnormaltime(System.currentTimeMillis());
			sa.setModifier(query.getCurrUId());
			sa.setModifytime(System.currentTimeMillis());
			if(salesArriveMapper.updateByPrimaryKeySelective(sa) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	@Override
	public Result detailApi(ApiSalesArriveQuery query) throws Exception {
		Result result = Result.getParamErrorResult();
		if (query != null && StringUtils.isNotBlank(query.getVehicleno()) && StringUtils.isNotBlank(query.getRfid())) {
			VehicleManage vehicle = new VehicleManage();
			vehicle.setState("1");
			vehicle.setVehicleno(query.getVehicleno());
			List<VehicleManage> list = vehicleManageMapper.selectSelective(vehicle);
			//判断车牌号是否存在且唯一
			if (list != null && list.size() == 1) {
				RFID rfid = new RFID();
				rfid.setRfid(query.getRfid());
				rfid.setState(true);
				long count = rfidMapper.selectSelectiveCount(rfid);
				//判断RFID是否已注册且唯一
				if(count == 1){
					if(StringUtils.equals(query.getRfid(), list.get(0).getRfid())){
						ApiSalesArriveResp api = null;
						if((api = getSalesArriveDetail(list.get(0).getId(), query.getRfid())) != null){
							result.setData(api);
							result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
						}else if((api = getPurchaseArriveDetail(list.get(0).getId(), query.getRfid())) != null){
							result.setData(api);
							result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
						}else{
							result.setErrorCode(ErrorCode.VEHICLE_NOT_NOTICE);
						}
					}else{
						result.setErrorCode(ErrorCode.RFID_VEHICLE_NOT_EXIST);
					}
				}else{
					result.setErrorCode(ErrorCode.RFID_NOT_EXIST);
				}
			}else{
				result.setErrorCode(ErrorCode.VEHICLE_NOT_EXIST);
			}
		}
		return result;
	}
	
	private ApiSalesArriveResp getPurchaseArriveDetail(String vehicleid, String vehiclerfid) {
		ApiSalesArriveResp api = null;
		PurchaseArrive pa = new PurchaseArrive();
		pa.setState("1");
		pa.setVehicleid(vehicleid);
		pa.setVehiclerfid(vehiclerfid);
		List<PurchaseArrive> listPurchase = purchaseArriveMapper.selectSelective(pa);
		if(CollectionUtils.isNotEmpty(listPurchase)){
			api = new ApiSalesArriveResp();
			PurchaseApplication application = purchaseApplicationMapper.selectByPrimaryKey(listPurchase.get(0).getBillid());
			PurchaseApplicationDetail applicationDetail = purchaseApplicationDetailMapper.selectByPrimaryKey(listPurchase.get(0).getBilldetailid());
			api.setVehicleid(vehicleid);
			api.setVehicleno(listPurchase.get(0).getVehicleno());
			api.setCustomerid(application.getSupplierid());
			api.setCustomer(application.getSuppliername());
			api.setMaterielid(applicationDetail.getMaterielid());
			api.setMateriel(applicationDetail.getMaterielname());
			if(StringUtils.isNotBlank(applicationDetail.getMaterielname()) && applicationDetail.getMaterielname().contains("水泥")){
				if(applicationDetail.getMaterielname().contains("袋装")){
					api.setCementtype("1");
				}
				if(applicationDetail.getMaterielname().contains("散装")){
					api.setCementtype("2");
				}
			}
			api.setPrimary("");//是否原发？？？
			api.setServicetype("1");
			api.setNotionformcode(listPurchase.get(0).getCode());
			api.setMinemouth(application.getMinemouthname());
			api.setNumber(listPurchase.get(0).getArrivalamount() == null ? "" : listPurchase.get(0).getArrivalamount().toString());
			api.setStatus(listPurchase.get(0).getStatus());
		}
		return api;
	}

	private ApiSalesArriveResp getSalesArriveDetail(String vehicleid, String vehiclerfid) throws Exception{
		ApiSalesArriveResp api = null;
		SalesArrive sa = new SalesArrive();
		sa.setState("1");
		sa.setVehicleid(vehicleid);
		sa.setVehiclerfid(vehiclerfid);
		List<SalesArrive> listSales = salesArriveMapper.selectSelective(sa);
		if(CollectionUtils.isNotEmpty(listSales)){
			api = new ApiSalesArriveResp();
			SalesArriveResp resp = copyBean2Resp(listSales.get(0), true);
			SalesApplicationResp salesApplicationResp = resp.getMainApplication();
			SalesApplicationDetailResp salesApplicationDetailResp = resp.getMainApplicationDetail();
			api.setVehicleno(resp.getVehicleno());
			api.setCustomerid(salesApplicationResp.getCustomerid());
			api.setCustomer(salesApplicationResp.getCustomername());
			api.setMaterielid(salesApplicationDetailResp.getMaterielid());
			api.setMateriel(salesApplicationDetailResp.getMaterielname());
			if(StringUtils.isNotBlank(salesApplicationDetailResp.getMaterielname()) && salesApplicationDetailResp.getMaterielname().contains("水泥")){
				if(salesApplicationDetailResp.getMaterielname().contains("袋装")){
					api.setCementtype("1");
					api.setBatchnumber(resp.getSerialnumber());
				}
				if(salesApplicationDetailResp.getMaterielname().contains("散装")){
					api.setCementtype("2");
				}
			}
			api.setServicetype("2");
			api.setNotionformcode(resp.getCode());
			api.setPrimary("");
			api.setVehicleid(resp.getId());
			api.setMinemouth("");
			api.setNumber(String.valueOf(resp.getTakeamount()==null?"":resp.getTakeamount()));
		}
		return api;
	}
	
	@Override
	public Result selectWaitingNumber(ApiDoorQueueQuery api){
		Result result = Result.getSuccessResult();
		SalesArriveQuery query = new SalesArriveQuery();
		query.setMaterielid(api.getMateriel());
		query.setPackagetype(api.getPackagetype());
		int waitingnumber = salesArriveMapper.selectWaitingNumber(query);
		ApiDoorQueueResp resp =new ApiDoorQueueResp();
		resp.setQueuenumber(String.valueOf(waitingnumber));
		//序号。。。
		resp.setSmallticket(codeGenDaoImpl.codeGen(1));
		result.setData(resp);
		return result;
	}

}
