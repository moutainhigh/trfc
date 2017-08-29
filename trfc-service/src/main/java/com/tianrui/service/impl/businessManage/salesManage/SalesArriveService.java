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
import com.tianrui.api.req.businessManage.app.AppNoticeOrderReq;
import com.tianrui.api.req.businessManage.app.AppOrderReq;
import com.tianrui.api.req.businessManage.app.AppOrderSaveReq;
import com.tianrui.api.req.businessManage.logisticsManage.SalesLogisticsQuery;
import com.tianrui.api.req.businessManage.salesManage.ApiDoorQueueQuery;
import com.tianrui.api.req.businessManage.salesManage.ApiSalesArriveQuery;
import com.tianrui.api.req.businessManage.salesManage.SalesArriveQuery;
import com.tianrui.api.req.businessManage.salesManage.SalesArriveSave;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.basicFile.measure.DriverManageResp;
import com.tianrui.api.resp.basicFile.measure.VehicleManageResp;
import com.tianrui.api.resp.businessManage.app.AppNoticeOrderResp;
import com.tianrui.api.resp.businessManage.cardManage.CardResp;
import com.tianrui.api.resp.businessManage.logisticsManage.SalesLogisticsResp;
import com.tianrui.api.resp.businessManage.otherManage.OtherArriveResp;
import com.tianrui.api.resp.businessManage.poundNoteMaintain.PoundNoteResp;
import com.tianrui.api.resp.businessManage.salesManage.ApiDoorQueueResp;
import com.tianrui.api.resp.businessManage.salesManage.ApiNoticeResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationDetailResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesArriveResp;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;
import com.tianrui.service.bean.businessManage.cardManage.Card;
import com.tianrui.service.bean.businessManage.logisticsManage.AccessRecord;
import com.tianrui.service.bean.businessManage.otherManage.OtherArrive;
import com.tianrui.service.bean.businessManage.poundNoteMaintain.PoundNote;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplication;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplicationDetail;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseArrive;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplication;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationDetail;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationJoinNatice;
import com.tianrui.service.bean.businessManage.salesManage.SalesArrive;
import com.tianrui.service.bean.common.QueueNumber;
import com.tianrui.service.bean.common.RFID;
import com.tianrui.service.impl.businessManage.otherManage.OtherArriveService;
import com.tianrui.service.mapper.basicFile.measure.VehicleManageMapper;
import com.tianrui.service.mapper.businessManage.cardManage.CardMapper;
import com.tianrui.service.mapper.businessManage.logisticsManage.AccessRecordMapper;
import com.tianrui.service.mapper.businessManage.otherManage.OtherArriveMapper;
import com.tianrui.service.mapper.businessManage.poundNoteMaintain.PoundNoteMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseApplicationDetailMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseApplicationMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseArriveMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationDetailMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationJoinNaticeMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesArriveMapper;
import com.tianrui.service.mapper.common.QueueNumberMapper;
import com.tianrui.service.mapper.common.RFIDMapper;
import com.tianrui.service.mongo.impl.CodeGenDaoImpl;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.DateUtil;
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
	private SalesApplicationMapper salesApplicationMapper;
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
	private AccessRecordMapper accessRecordMapper1;
	@Autowired
	private OtherArriveMapper otherArriveMapper;
	@Autowired
	private OtherArriveService otherArriveService;
	@Autowired
	private QueueNumberMapper queueNumberMapper;

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
			if(validVehicleAndDriver(save, result, bean)){
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
				bean.setAuditstatus(Constant.ONE_STRING);
				bean.setStatus(Constant.ZERO_STRING);
				bean.setState(Constant.ONE_STRING);
				bean.setSource(Constant.ZERO_STRING);
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
						join.setState(Constant.ONE_STRING);
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
		}
		return result;
	}

	private boolean validVehicleAndDriver(SalesArriveSave save, Result result, SalesArrive bean) {
		boolean flag = true;
		bean.setVehicleid(save.getVehicleid());
		List<SalesArrive> listVehicle = salesArriveMapper.checkDriverAndVehicleIsUse(bean);
		if(listVehicle != null && listVehicle.size() > 0){
			result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			result.setError("此车辆己有提货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listVehicle.get(0).getCode()+"，如有疑问请与销售处联系！");
			flag = false;
		}
		PurchaseArrive pa = new PurchaseArrive();
		pa.setVehicleid(save.getVehicleid());
		List<PurchaseArrive> listVehicle1 = purchaseArriveMapper.checkDriverAndVehicleIsUse(pa);
		if(listVehicle1 != null && listVehicle1.size() > 0){
			result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			result.setError("此车辆己有到货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listVehicle1.get(0).getCode()+"，如有疑问请与销售处联系！");
			flag = false;
		}
		bean.setVehicleid(null);
		bean.setDriverid(save.getDriverid());
		List<SalesArrive> listDriver = salesArriveMapper.checkDriverAndVehicleIsUse(bean);
		if(listDriver != null && listDriver.size() > 0){
			result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			result.setError("此司机己有提货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listDriver.get(0).getCode()+"，如有疑问请与销售处联系！");
			flag = false;
		}
		pa.setVehicleid(null);
		pa.setDriverid(save.getDriverid());
		List<PurchaseArrive> listDriver1 = purchaseArriveMapper.checkDriverAndVehicleIsUse(pa);
		if(listDriver1 != null && listDriver1.size() > 0){
			result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			result.setError("此司机己有到货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listDriver1.get(0).getCode()+"，如有疑问请与销售处联系！");
			flag = false;
		}
		//验证其他业务中的通知单
		OtherArrive oa = new OtherArrive();
		oa.setVehicleid(save.getVehicleid());
		List<OtherArrive> listVehicle2 = otherArriveMapper.checkDriverAndVehicleAndIcardIsUse(oa);
		if(listVehicle2!=null && listVehicle2.size()>0){
			if(StringUtils.equals(listVehicle2.get(0).getBusinesstype(), "5")){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				result.setError("此车辆己有其他入库通知单、待出厂后进行派车，现有车辆业务单据号为:"+listVehicle2.get(0).getCode()+"，如有疑问请与销售处联系！");
				flag = false;
			}else if(StringUtils.equals(listVehicle2.get(0).getBusinesstype(), "7")){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				result.setError("此车辆己有其他出库通知单、待出厂后进行派车，现有车辆业务单据号为:"+listVehicle2.get(0).getCode()+"，如有疑问请与销售处联系！");
				flag = false;
			}
		}
		oa.setVehicleid(null);
		oa.setDriverid(save.getDriverid());
		List<OtherArrive> listDriver2 = otherArriveMapper.checkDriverAndVehicleAndIcardIsUse(oa);
		if(listDriver2!=null && listDriver2.size()>0){
			if(StringUtils.equals(listDriver2.get(0).getBusinesstype(), "5")){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				result.setError("此司机己有其他入库通知单、待出厂后进行派车，现有车辆业务单据号为:"+listDriver2.get(0).getCode()+"，如有疑问请与销售处联系！");
				flag = false;
			}else if(StringUtils.equals(listDriver2.get(0).getBusinesstype(), "7")){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				result.setError("此司机己有其他出库通知单、待出厂后进行派车，现有车辆业务单据号为:"+listDriver2.get(0).getCode()+"，如有疑问请与销售处联系！");
				flag = false;
			}
		}
		//ic卡信息
		if(StringUtils.isNotBlank(save.getIcardno())){
			Card card = cardMapper.selectByCardno(save.getIcardno());
			if(card!=null){
				//ic卡是否占用
				SalesArrive sales1 = salesArriveMapper.checkICUse(card.getId());
				PurchaseArrive purchase1 = purchaseArriveMapper.checkICUse(card.getId());
				oa.setVehicleid(null);
				oa.setIcardid(card.getId());
				List<OtherArrive> listIcard2 = otherArriveMapper.checkDriverAndVehicleAndIcardIsUse(oa);
				if(sales1 == null && purchase1 == null && listIcard2.size()==0) {
					save.setIcardid(card.getId());
				}else{
					result.setErrorCode(ErrorCode.CARD_IN_USE);
					flag = false;
				}
			}else{
				result.setErrorCode(ErrorCode.CARD_NOT_EXIST);
				flag = false;
			}
		}
		return flag;
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
			if(validVehicleAndDriver(save, result, bean)){
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
						ApiNoticeResp api = null;
						if((api = getSalesArriveDetail(query.getVehicleno(), query.getRfid())) != null){
							result.setData(api);
							result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
						}else if((api = getPurchaseArriveDetail(query.getVehicleno(), query.getRfid())) != null){
							result.setData(api);
							result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
						}else if((api = getOtherArriveDetail(query.getVehicleno(), query.getRfid())) != null){
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
	
	private ApiNoticeResp getPurchaseArriveDetail(String vehileno, String vehiclerfid) {
		ApiNoticeResp api = null;
		List<PurchaseArrive> listPurchase = purchaseArriveMapper.validNoticeByVehicle(vehileno, vehiclerfid);
		if(CollectionUtils.isNotEmpty(listPurchase)){
			api = new ApiNoticeResp();
			PurchaseApplication application = purchaseApplicationMapper.selectByPrimaryKey(listPurchase.get(0).getBillid());
			PurchaseApplicationDetail applicationDetail = purchaseApplicationDetailMapper.selectByPrimaryKey(listPurchase.get(0).getBilldetailid());
			api.setVehicleid(listPurchase.get(0).getVehicleid());
			api.setVehicleno(listPurchase.get(0).getVehicleno());
			api.setCustomerid(application.getSupplierid());
			api.setCustomer(application.getSuppliername());
			api.setMaterielid(applicationDetail.getMaterielid());
			api.setMateriel(applicationDetail.getMaterielname());
			if(StringUtils.isNotBlank(applicationDetail.getMaterielname()) && applicationDetail.getMaterielname().contains("水泥")){
				if(applicationDetail.getMaterielname().contains("袋装")){
					api.setCementtype(Constant.ONE_STRING);
				}
				if(applicationDetail.getMaterielname().contains("散装")){
					api.setCementtype(Constant.TWO_STRING);
				}
			}
			api.setPrimary("");//是否原发？？？
			api.setServicetype(listPurchase.get(0).getType());
			api.setNotionformcode(listPurchase.get(0).getCode());
			api.setMinemouth(application.getMinemouthname());
			api.setNumber(listPurchase.get(0).getArrivalamount() == null ? "" : listPurchase.get(0).getArrivalamount().toString());
			api.setStatus(listPurchase.get(0).getStatus());
		}
		return api;
	}
	private ApiNoticeResp getOtherArriveDetail(String vehicleno, String vehiclerfid) {
		ApiNoticeResp api = null;
		OtherArrive oa = new OtherArrive();
		oa.setVehicleid("123");
		if(StringUtils.isNotBlank(vehicleno)){
			VehicleManage vehicle = vehicleManageMapper.selectByVehicleno(vehicleno);
			if(vehicle!=null){
				oa.setVehicleid(vehicle.getId());
			}
		}
		List<OtherArrive> listPurchase = otherArriveMapper.checkDriverAndVehicleAndIcardIsUse(oa);
		if(CollectionUtils.isNotEmpty(listPurchase)){
			api = new ApiNoticeResp();
//			PurchaseApplication application = purchaseApplicationMapper.selectByPrimaryKey(listPurchase.get(0).getBillid());
//			PurchaseApplicationDetail applicationDetail = purchaseApplicationDetailMapper.selectByPrimaryKey(listPurchase.get(0).getBilldetailid());
			OtherArriveResp oaResp = new OtherArriveResp();
			try {
				oaResp = (OtherArriveResp)otherArriveService.findOne(listPurchase.get(0).getId()).getData();
			} catch (Exception e) {
				e.printStackTrace();
			}
			api.setVehicleid(oaResp.getVehicleid());
			api.setVehicleno(oaResp.getVehicleno());
			if(StringUtils.isNotBlank(oaResp.getSupplierid())){				
				api.setCustomerid(oaResp.getSupplierid());
				api.setCustomer(oaResp.getSuppliername());
			}else{
				api.setCustomerid(oaResp.getCustomerid());
				api.setCustomer(oaResp.getCustomername());
			}
			api.setMaterielid(oaResp.getMaterielid());
			api.setMateriel(oaResp.getMaterielname());
			if(StringUtils.isNotBlank(oaResp.getMaterielname()) && oaResp.getMaterielname().contains("水泥")){
				if(oaResp.getMaterielname().contains("袋装")){
					api.setCementtype("1");
				}
				if(oaResp.getMaterielname().contains("散装")){
					api.setCementtype("2");
				}
			}
			api.setPrimary("");//是否原发？？？
			api.setServicetype(oaResp.getBusinesstype());
			api.setNotionformcode(oaResp.getCode());
			api.setNumber(oaResp.getCount() == null ? "" : oaResp.getCount().toString());
			api.setStatus(oaResp.getStatus());
		}
		return api;
	}
	private ApiNoticeResp getSalesArriveDetail(String vehicleno, String vehiclerfid) throws Exception{
		ApiNoticeResp api = null;
		List<SalesArrive> list = salesArriveMapper.validNoticeByVehicle(vehicleno, vehiclerfid);
		if(CollectionUtils.isNotEmpty(list)){
			api = new ApiNoticeResp();
			SalesArriveResp resp = copyBean2Resp(list.get(0), true);
			SalesApplicationResp salesApplicationResp = resp.getMainApplication();
			SalesApplicationDetailResp salesApplicationDetailResp = resp.getMainApplicationDetail();
			api.setVehicleid(resp.getVehicleid());
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
			api.setMinemouth("");
			api.setNumber(String.valueOf(resp.getTakeamount()==null?"":resp.getTakeamount()));
			api.setStatus(resp.getStatus());
		}
		return api;
	}

	@Transactional
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
		QueueNumber queue = new QueueNumber();
		queue.setNoticecode(api.getNoticecode());
		queue.setVehicleno(api.getVehicleno());
		queue.setRfid(api.getRfid());
		queue.setQueuenumber(resp.getSmallticket());
		queue.setWaitingnumber(waitingnumber);
		queueNumberMapper.deleteByPrimaryKey(queue.getNoticecode());
		queueNumberMapper.insert(queue);
		result.setData(resp);
		return result;
	}

	@Override
	public PaginationVO<AppNoticeOrderResp> appToPage(AppNoticeOrderReq req) {
		PaginationVO<AppNoticeOrderResp> page = null;
		if(req != null){
			page = new PaginationVO<AppNoticeOrderResp>();
			long count = salesArriveMapper.findAppNoticePageCount(req);
			if(count > 0){
				req.setStart((req.getPageNo() - 1) * req.getPageSize());
				req.setLimit(req.getPageSize());
				List<AppNoticeOrderResp> list = salesArriveMapper.findAppNoticePage(req);
				page.setList(list);
			}
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
			page.setTotal(count);
		}
		return page;
	}

	@Override
	public Result appToDetail(AppNoticeOrderReq req) {
		Result result = Result.getParamErrorResult();
		if(req != null && StringUtils.isNotBlank(req.getId())){
			AppNoticeOrderResp resp = new AppNoticeOrderResp();
			SalesArrive sa = salesArriveMapper.selectByPrimaryKey(req.getId());
			if(sa != null){
				resp.setId(sa.getId());
				resp.setCode(sa.getCode());
				resp.setNoticetime(DateUtil.parse(sa.getMakebilltime(), "yyyy-MM-dd HH:mm:ss"));
				resp.setVehicleno(sa.getVehicleno());
				resp.setNumber(sa.getTakeamount());
				resp.setSource(sa.getSource());
				resp.setStatus(sa.getStatus());
				SalesApplication application = salesApplicationMapper.selectByPrimaryKey(sa.getBillid());
				if(application != null){
					resp.setBillCode(application.getCode());
					resp.setBilltime(DateUtil.parse(application.getBilltime(), "yyyy-MM-dd HH:mm:ss"));
				}
				SalesApplicationDetail applicationDetail = salesApplicationDetailMapper.selectByPrimaryKey(sa.getBilldetailid());
				if(applicationDetail != null){
					resp.setMaterialName(applicationDetail.getMaterielname());
				}
				resp.setOrgName(Constant.ORG_NAME);
				result.setData(resp);
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.NOTICE_NOT_EXIST);
			}
		}
		return result;
	}

	@Override
	public Result appInfoFactoryVehicleAndMaterial(AppOrderReq req) {
		Result result = Result.getParamErrorResult();
		if(req != null && StringUtils.isNotBlank(req.getUserId())){
			result.setData(salesArriveMapper.appInfoFactoryVehicleAndMaterial(req));
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return result;
	}

	@Override
	public Result appToAddNotice(AppOrderSaveReq req) throws Exception {
		Result result = Result.getParamErrorResult();
		if(req != null
				&& StringUtils.isNotBlank(req.getUserId())
				&& StringUtils.isNotBlank(req.getBillId())
				&& StringUtils.isNotBlank(req.getBillDetailId())
				&& StringUtils.isNotBlank(req.getVehicleId())
				&& StringUtils.isNotBlank(req.getDriverId())
				&& StringUtils.isNotBlank(req.getNumber())){
			SalesArriveSave save = new SalesArriveSave();
			save.setVehicleid(req.getVehicleId());
			save.setDriverid(req.getDriverId());
			SalesArrive bean = new SalesArrive();
			if(validVehicleAndDriver(save, result, bean)){
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
				SalesApplicationResp salesApplicationResp = salesApplicationService.findOne(save.getBillid(), false);
				if(salesApplicationResp != null){
					bean.setBillcode(salesApplicationResp.getCode());
				}
				SalesApplicationDetailResp salesApplicationDetailResp = salesApplicationDetailService.findOne(save.getBilldetailid());
				if(salesApplicationDetailResp != null){
					bean.setUnit(salesApplicationDetailResp.getUnit());
				}
				bean.setId(UUIDUtil.getId());
				bean.setBillid(req.getBillId());
				bean.setBilldetailid(req.getBillDetailId());
				bean.setVehicleid(req.getVehicleId());
				bean.setDriverid(req.getDriverId());
				bean.setTakeamount(Double.parseDouble(req.getNumber()));
				GetCodeReq codeReq = new GetCodeReq();
				codeReq.setCode("TH");
				codeReq.setCodeType(true);
				codeReq.setUserid(req.getUserId());
				bean.setCode(systemCodeService.getCode(codeReq).getData().toString());
				bean.setAuditstatus("1");
				bean.setStatus("0");
				bean.setState("1");
				bean.setSource("2");
				bean.setMakerid(req.getUserId());
				bean.setMakebillname(systemUserService.getUser(req.getUserId()).getName());
				bean.setMakebilltime(System.currentTimeMillis());
				bean.setCreator(req.getUserId());
				bean.setCreatetime(System.currentTimeMillis());
				bean.setModifier(req.getUserId());
				bean.setModifytime(System.currentTimeMillis());
				SalesApplicationJoinNatice join = new SalesApplicationJoinNatice();
				join.setId(UUIDUtil.getId());
				join.setBillid(bean.getBillid());
				join.setBilldetailid(bean.getBilldetailid());
				join.setNaticeid(bean.getId());
				join.setTakeamount(bean.getTakeamount());
				join.setState("1");
				join.setCreator(bean.getCreator());
				join.setCreatetime(System.currentTimeMillis());
				join.setModifier(bean.getModifier());
				join.setModifytime(System.currentTimeMillis());
				if(salesArriveMapper.insertSelective(bean) > 0 
						&& StringUtils.equals(systemCodeService.updateCodeItem(codeReq).getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())
						&& salesApplicationJoinNaticeMapper.insertSelective(join) > 0){
					SalesApplicationDetail applicationDetail = salesApplicationDetailMapper.selectByPrimaryKey(bean.getBilldetailid());
					SalesApplicationDetail detail = new SalesApplicationDetail();
					detail.setId(applicationDetail.getId());
					detail.setMargin(applicationDetail.getMargin() - bean.getTakeamount());
					detail.setPretendingtake(applicationDetail.getPretendingtake() + bean.getTakeamount());
					if(salesApplicationDetailMapper.updateByPrimaryKeySelective(detail) > 0){
						result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
					}else{
						result.setErrorCode(ErrorCode.OPERATE_ERROR);
					}
				}else{
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}
		}
		return result;
	}

	@Override
	public Result appInvalid(AppNoticeOrderReq req) {
		Result result = Result.getParamErrorResult();
		if(req != null && StringUtils.isNotBlank(req.getId())
				&& StringUtils.isNotBlank(req.getUserId())){
			SalesArrive bean = new SalesArrive();
			bean.setId(req.getId());
			bean.setStatus("3");
			bean.setModifier(req.getUserId());
			bean.setModifytime(System.currentTimeMillis());
			if(salesArriveMapper.updateByPrimaryKeySelective(bean) == 1){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Override
	public PaginationVO<SalesLogisticsResp> logisticsPage(SalesLogisticsQuery query) {
		PaginationVO<SalesLogisticsResp> page = null;
		if(query != null){
			page = new PaginationVO<SalesLogisticsResp>();
			long count = salesArriveMapper.selectLogisticsPageCount(query);
			if(count > 0){
				query.setStart((query.getPageNo() - 1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				List<SalesLogisticsResp> list = salesArriveMapper.selectLogisticsPage(query);
				page.setList(list);
			}
			page.setTotal(count);
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
		}
		return page;
	}

}
