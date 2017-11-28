package com.tianrui.service.impl.businessManage.salesManage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.tianrui.api.intf.basicFile.measure.IDriverManageService;
import com.tianrui.api.intf.basicFile.measure.IVehicleManageService;
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
import com.tianrui.api.resp.businessManage.logisticsManage.SalesLogisticsResp;
import com.tianrui.api.resp.businessManage.otherManage.OtherArriveResp;
import com.tianrui.api.resp.businessManage.poundNoteMaintain.PoundNoteResp;
import com.tianrui.api.resp.businessManage.salesManage.ApiDoorQueueResp;
import com.tianrui.api.resp.businessManage.salesManage.ApiNoticeResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationDetailResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesArriveResp;
import com.tianrui.service.bean.basicFile.businessControl.PrimarySetting;
import com.tianrui.service.bean.basicFile.measure.DriverManage;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;
import com.tianrui.service.bean.businessManage.cardManage.Card;
import com.tianrui.service.bean.businessManage.logisticsManage.AccessRecord;
import com.tianrui.service.bean.businessManage.otherManage.OtherArrive;
import com.tianrui.service.bean.businessManage.poundNoteMaintain.PoundNote;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplication;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplicationDetail;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseArrive;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplication;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationArrive;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationDetail;
import com.tianrui.service.bean.businessManage.salesManage.SalesArrive;
import com.tianrui.service.bean.common.QueueNumber;
import com.tianrui.service.bean.common.RFID;
import com.tianrui.service.bean.system.auth.SystemUser;
import com.tianrui.service.impl.businessManage.otherManage.OtherArriveService;
import com.tianrui.service.mapper.basicFile.businessControl.PrimarySettingMapper;
import com.tianrui.service.mapper.basicFile.measure.DriverManageMapper;
import com.tianrui.service.mapper.basicFile.measure.VehicleManageMapper;
import com.tianrui.service.mapper.businessManage.cardManage.CardMapper;
import com.tianrui.service.mapper.businessManage.logisticsManage.AccessRecordMapper;
import com.tianrui.service.mapper.businessManage.otherManage.OtherArriveMapper;
import com.tianrui.service.mapper.businessManage.poundNoteMaintain.PoundNoteMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseApplicationDetailMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseApplicationMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseArriveMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationArriveMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationDetailMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesArriveMapper;
import com.tianrui.service.mapper.common.QueueNumberMapper;
import com.tianrui.service.mapper.common.RFIDMapper;
import com.tianrui.service.mapper.system.auth.SystemUserMapper;
import com.tianrui.service.mongo.impl.CodeGenDaoImpl;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.enums.BillTypeEnum;
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
	private DriverManageMapper driverManageMapper;
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
	private CardMapper cardMapper;
	@Autowired
	private PurchaseApplicationMapper purchaseApplicationMapper;
	@Autowired
	private PurchaseApplicationDetailMapper purchaseApplicationDetailMapper;
	@Autowired
	private PoundNoteMapper poundNoteMapper;
	@Autowired
	private AccessRecordMapper accessRecordMapper;
	@Autowired
	private OtherArriveMapper otherArriveMapper;
	@Autowired
	private OtherArriveService otherArriveService;
	@Autowired
	private QueueNumberMapper queueNumberMapper;
	@Autowired
	private PrimarySettingMapper primarySettingMapper;
	@Autowired
	private SystemUserMapper userMapper;
	@Autowired
	private SalesApplicationArriveMapper salesApplicationArriveMapper;

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

	private void ListArriveRespSetListApplicationResp(List<SalesArriveResp> listArrive) throws Exception{
		if(CollectionUtils.isNotEmpty(listArrive)){
			List<String> noticeIds = new ArrayList<String>();
			for(SalesArriveResp resp : listArrive){
				noticeIds.add(resp.getId());
				//获取磅单信息
				PoundNote pound = poundNoteMapper.selectByNoticeId(resp.getId());
				if(pound!=null){
					PoundNoteResp poundResp = new PoundNoteResp();
					PropertyUtils.copyProperties(poundResp, pound);
					resp.setPoundNoteResp(poundResp);
				}
				//获取出入厂时间
				AccessRecord access = accessRecordMapper.selectByNoticeId(resp.getId());
				if(access!=null){
					resp.setEnterTime(access.getEntertime());
					resp.setOutTime(access.getOuttime());
				}
			}
			setListApplication(listArrive, noticeIds);
		}
	}

	private void setListApplication(List<SalesArriveResp> listArrive, List<String> noticeIds) throws Exception {
		List<SalesApplicationArrive> listJoin = salesApplicationArriveMapper.listByNoticeIds(noticeIds);
		List<String> billIds = new ArrayList<String>();
		List<String> billDetailIds = new ArrayList<String>();
		for (SalesApplicationArrive join : listJoin) {
			billIds.add(join.getBillId());
			billDetailIds.add(join.getBillDetailId());
		}
		List<SalesApplicationResp> listApplication = salesApplicationService.selectByIds(billIds, false);
		List<SalesApplicationDetailResp> listApplicationDetail = salesApplicationDetailService.selectByIds(billDetailIds);
		for(SalesArriveResp resp : listArrive){
			for(SalesApplicationArrive join : listJoin){
				if (StringUtils.equals(resp.getId(), join.getNoticeId())) {
					for(SalesApplicationResp applicationResp : listApplication){
						if(StringUtils.equals(applicationResp.getId(), join.getBillId())){
							for(SalesApplicationDetailResp detailResp : listApplicationDetail){
								if(StringUtils.equals(detailResp.getId(), join.getBillDetailId())){
									applicationResp.getList().add(detailResp);
									resp.getListApplication().add(applicationResp);
								}
							}
						}
					}
				}
			}
		}
	}
	
	//校验车辆是否可用
	private boolean validVehicle(VehicleManage vehicle, Result result) {
		boolean flag = false;
		if (vehicle != null && StringUtils.equals(vehicle.getState(), Constant.ONE_STRING)) {
			if (StringUtils.equals(vehicle.getIsvalid(), Constant.ONE_STRING)) {
				if (StringUtils.equals(vehicle.getIsblacklist(), Constant.ZERO_STRING)) {
					PurchaseArrive pa = new PurchaseArrive();
					pa.setVehicleid(vehicle.getId());
					List<PurchaseArrive> paList = purchaseArriveMapper.checkDriverAndVehicleIsUse(pa);
					if (CollectionUtils.isNotEmpty(paList)) {
		                result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
		                result.setError("此车辆己有到货通知单、待出厂后进行派车，现有车辆业务单据号为:"+paList.get(0).getCode()+"，如有疑问请与销售处联系！");
		            } else {
		            	SalesArrive sa = new SalesArrive();
		                sa.setVehicleid(vehicle.getId());
		                List<SalesArrive> saList = salesArriveMapper.checkDriverAndVehicleIsUse(sa);
		                if (CollectionUtils.isNotEmpty(saList)) {
		                    result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
		                    result.setError("此车辆己有提货通知单、待出厂后进行派车，现有车辆业务单据号为:"+saList.get(0).getCode()+"，如有疑问请与销售处联系！");
		                } else {
		                	OtherArrive oa = new OtherArrive();
		                    oa.setVehicleid(vehicle.getId());
		                    List<OtherArrive> oaList = otherArriveMapper.checkDriverAndVehicleAndIcardIsUse(oa);
		                    if (CollectionUtils.isNotEmpty(oaList)) {
		                        if(StringUtils.equals(oaList.get(0).getBusinesstype(), Constant.FIVE_STRING)){
		                            result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
		                            result.setError("此车辆己有其他入库通知单、待出厂后进行派车，现有车辆业务单据号为:"+oaList.get(0).getCode()+"，如有疑问请与销售处联系！");
		                        }else if(StringUtils.equals(oaList.get(0).getBusinesstype(), Constant.SEVEN_STRING)){
		                            result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
		                            result.setError("此车辆己有其他出库通知单、待出厂后进行派车，现有车辆业务单据号为:"+oaList.get(0).getCode()+"，如有疑问请与销售处联系！");
		                        } else {
		                        	flag = true;
		                        }
		                    } else {
	                        	flag = true;
	                        }
		                }
		            }
				} else {
					result.setErrorCode(ErrorCode.VEHICLE_IS_BLACK);
				}
			} else {
				result.setErrorCode(ErrorCode.VEHICLE_IS_WX);
			}
		} else {
			result.setErrorCode(ErrorCode.VEHICLE_NOT_EXIST);
		}
		return flag;
	}
	
	private boolean validDriver(DriverManage driver, Result result) {
		boolean flag = false;
		if (driver != null && StringUtils.equals(driver.getState(), Constant.ONE_STRING)) {
			if (StringUtils.equals(driver.getIsvalid(), Constant.ONE_STRING)) {
				flag = true;
			} else {
				result.setErrorCode(ErrorCode.DRIVER_IS_WX);
			}
		} else {
			result.setErrorCode(ErrorCode.DRIVER_NOT_EXIST);
		}
		return flag;
	}
	
	private boolean validCard(SalesArriveSave save, Result result) {
		boolean flag = false;
		if(StringUtils.isNotBlank(save.getIcardno())){
            Card card = cardMapper.selectByCardno(save.getIcardno());
            if(card!=null){
                //ic卡是否占用
                SalesArrive sales = salesArriveMapper.checkICUse(card.getId());
                PurchaseArrive purchase = purchaseArriveMapper.checkICUse(card.getId());
                OtherArrive oa = new OtherArrive();
                oa.setIcardid(card.getId());
                List<OtherArrive> listOther = otherArriveMapper.checkDriverAndVehicleAndIcardIsUse(oa);
                if(sales == null && purchase == null && CollectionUtils.isEmpty(listOther)) {
                	flag = true;
                	save.setIcardid(card.getId());
                }else{
                    result.setErrorCode(ErrorCode.CARD_IN_USE);
                    return false;
                }
            }else{
                result.setErrorCode(ErrorCode.CARD_NOT_EXIST);
                return false;
            }
        } else {
        	flag = true;
        }
		return flag;
	}
	
	private String getCode(String code, String userId, boolean flag) throws Exception {
		GetCodeReq codeReq = new GetCodeReq();
		codeReq.setCode(code);
		codeReq.setCodeType(flag);
		codeReq.setUserid(userId);
		return systemCodeService.getCode(codeReq).getData().toString();
	}
	
	private void updateCode(String code, String userId) throws Exception {
		GetCodeReq codeReq = new GetCodeReq();
		codeReq.setCode(code);
		codeReq.setCodeType(true);
		codeReq.setUserid(userId);
		systemCodeService.updateCodeItem(codeReq);
	}
	
	@Transactional
	@Override
	public Result add(SalesArriveSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null && StringUtils.isNotBlank(save.getBillid()) 
				&& StringUtils.isNotBlank(save.getBilldetailid())
				&& StringUtils.isNotBlank(save.getMaindeduction())
				&& StringUtils.isNotBlank(save.getVehicleid())
				&& save.getTakeamount() != null
				&& StringUtils.isNotBlank(save.getIds())){
			SystemUser user = userMapper.selectByPrimaryKey(save.getCurrUId());
			if (user != null) {
				if (save.getTakeamount() > 0) {
					SalesApplication refBill = salesApplicationMapper.selectByPrimaryKey(save.getBillid());
					SalesApplicationDetail refBillDetail = salesApplicationDetailMapper.selectByPrimaryKey(save.getBilldetailid());
					if (refBill != null && refBillDetail != null) {
						if (StringUtils.equals(refBill.getValidStatus(), Constant.ZERO_STRING)) {
							if (StringUtils.equals(refBill.getBilltypeid(), BillTypeEnum.BILL_TYPE_MORE_CAR.getCode())) {
								//TODO
								JSONArray ids = JSON.parseArray(save.getIds());
								List<List<Object>> list = new ArrayList<List<Object>>();
								//总余量
								double sumMargin = 0D;
								for (int i = 0; i < ids.size(); i++) {
									JSONArray arr = ids.getJSONArray(i);
									String id = arr.getString(0);
									String detailId = arr.getString(1);
									SalesApplication sa = salesApplicationMapper.selectByPrimaryKey(id);
									SalesApplicationDetail sad = salesApplicationDetailMapper.selectByPrimaryKey(detailId);
									sumMargin += sad.getMargin();
									List<Object> item = new ArrayList<Object>();
									item.add(sa);
									item.add(sad);
									list.add(item);
								}
								if (save.getTakeamount() <= sumMargin) {
									final String mainBillDetailId = save.getBilldetailid();
									final String maindeduction = save.getMaindeduction();
									//对余量进行排序，升序（由小到大）
									sortMorBill(list, mainBillDetailId, maindeduction);
									//判断预提量是否满足多单合并规则
									if (validateMoreBillMargin(save, result, list)) {
										if (validCard(save, result)) {
											VehicleManage vehicle = vehicleManageMapper.selectByPrimaryKey(save.getVehicleid());
											if(validVehicle(vehicle, result)){
												if (StringUtils.isNotBlank(save.getDriverid())) {
													DriverManage driver = driverManageMapper.selectByPrimaryKey(save.getDriverid());
													if (validDriver(driver, result)) {
														result = putNoticeValue(save, user, list, refBill, refBillDetail, vehicle, driver);
													}
												} else {
													result = putNoticeValue(save, user, list, refBill, refBillDetail, vehicle, null);
												}
											}
										}
									}
								} else {
									result.setErrorCode(ErrorCode.NOTICE_NUMBER_ERROR);
								}
							} else {
								result.setErrorCode(ErrorCode.APPLICATION_DONT_SEND_CAR);
							}
						} else {
							result.setErrorCode(ErrorCode.APPLICATION_IS_VALID_ERROR);
						}
					} else {
						result.setErrorCode(ErrorCode.APPLICATION_NOT_EXIST);
					}
				} else {
					result.setErrorCode(ErrorCode.NOTICE_NUMBER_ERROR);
				}
			} else {
				result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR17);
			}
		}
		return result;
	}
	
	//判断预提量是否满足多单合并规则
	private boolean validateMoreBillMargin(SalesArriveSave save, Result result, List<List<Object>> list) {
		double number = save.getTakeamount();
		//判断预提量是否满足多单合并规则（预提量必须满足多单的余量都有扣减行为,且除最后一个扣减单外，其余的余量剩余0）
		for (int i = 0; i < list.size(); i++) {
			SalesApplicationDetail sad = (SalesApplicationDetail) list.get(i).get(1);
			if (i < list.size() - 1) {
				if (number - sad.getMargin() <= 0) {
					result.setErrorCode(ErrorCode.NOTICE_SEND_CAR_ERROR);
					return false;
				}
				number -= sad.getMargin();
			}
		}
		return true;
	}

	//对余量进行排序，升序（由小到大）
	private void sortMorBill(List<List<Object>> list, final String mainBillDetailId, final String maindeduction) {
		Collections.sort(list, new Comparator<List<Object>>() {
			@Override
			public int compare(List<Object> o1, List<Object> o2) {
				int index = 0;
				SalesApplicationDetail s1 = (SalesApplicationDetail) o1.get(1);
				SalesApplicationDetail s2 = (SalesApplicationDetail) o2.get(1);
				//如果有勾选主扣单量，将该订单放在第一位
				if (StringUtils.equals(maindeduction, Constant.ONE_STRING)
						&& StringUtils.equals(s2.getId(), mainBillDetailId) ) {
					index = -1;
				} else {
					double margin1 = s1.getMargin();
					double margin2 = s2.getMargin();
					if (margin1 > margin2) {
						index = 1;
					} else {
						index = -1;
					}
				}
				return index;
			}
		});
	}

	//保存通知单和多单合并关系记录及回写订单余量、预提量
	private Result putNoticeValue(SalesArriveSave save, SystemUser user, List<List<Object>> list, SalesApplication refBill,
			SalesApplicationDetail refBillDetail, VehicleManage vehicle, DriverManage driver) throws Exception {
		Result result = Result.getSuccessResult();
		SalesArrive bean = new SalesArrive();
		bean.setId(UUIDUtil.getId());
		bean.setCode(getCode("TH", user.getId(), true));
		bean.setAuditstatus(Constant.ONE_STRING);
		bean.setSource(Constant.ZERO_STRING);
		bean.setStatus(Constant.ZERO_STRING);
		bean.setVehicleid(vehicle.getId());
		bean.setVehicleno(vehicle.getVehicleno());
		bean.setVehiclerfid(vehicle.getRfid());
		if (driver != null) {
			bean.setDriverid(driver.getId());
			bean.setDrivername(driver.getName());
			bean.setDriveridentityno(driver.getIdentityno());
		}
		bean.setBillid(refBill.getId());
		bean.setBillcode(refBill.getCode());
		bean.setBilldetailid(refBillDetail.getId());
		bean.setUnit(refBillDetail.getUnit());
		bean.setTakeamount(save.getTakeamount());
		bean.setIcardid(save.getIcardid());
		bean.setIcardno(save.getIcardno());
		bean.setState(Constant.ONE_STRING);
		bean.setMaindeduction(save.getMaindeduction());
		bean.setMakerid(user.getId());
		bean.setMakebillname(user.getName());
		bean.setMakebilltime(System.currentTimeMillis());
		bean.setCreator(user.getId());
		bean.setCreatetime(System.currentTimeMillis());
		bean.setRemarks(save.getRemarks());
		bean.setForceOutFactory(Constant.ZERO_NUMBER);
		bean.setValidStatus(Constant.ZERO_STRING);
		//保存通知单
		salesArriveMapper.insertSelective(bean);
		updateCode("TH", user.getId());
		// TODO
		//循环list 记录绑定关系和减扣量、扣减顺序，并回写子表 量。
		double number = save.getTakeamount();
		int idex = 1;
		for (List<Object> item : list) {
			SalesApplication sa = (SalesApplication) item.get(0);
			SalesApplicationDetail sad = (SalesApplicationDetail) item.get(1);
			double subNum = sad.getMargin();
			if (number > sad.getMargin()) {
				number -= subNum;
			} else {
				subNum = number;
			}
			SalesApplicationArrive join = new SalesApplicationArrive();
			join.setId(UUIDUtil.getId());
			join.setBillId(sa.getId());
			join.setBillDetailId(sad.getId());
			join.setNoticeId(bean.getId());
			join.setNumber(subNum);
			join.setSequence(idex);
			idex++;
			salesApplicationArriveMapper.insertSelective(join);
			sad.setMargin(sad.getMargin() - subNum);
			sad.setPretendingtake(sad.getPretendingtake() + subNum);
			salesApplicationDetailMapper.updateByPrimaryKeySelective(sad);
		}
		return result;
	}

	private boolean validVehicleAndDriver(SalesArriveSave save, Result result, SalesArrive bean) throws Exception {
		boolean flag = true;
		VehicleManageResp vehicle = vehicleManageService.findOne(save.getVehicleid());
        if(vehicle != null){
            bean.setVehicleid(save.getVehicleid());
            bean.setVehicleno(vehicle.getVehicleno());
            bean.setVehiclerfid(vehicle.getRfid());
            List<SalesArrive> listVehicle = salesArriveMapper.checkDriverAndVehicleIsUse(bean);
            if(listVehicle != null && listVehicle.size() > 0){
                result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
                result.setError("此车辆己有提货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listVehicle.get(0).getCode()+"，如有疑问请与销售处联系！");
                return false;
            }
            PurchaseArrive pa = new PurchaseArrive();
            pa.setVehicleid(save.getVehicleid());
            List<PurchaseArrive> listVehicle1 = purchaseArriveMapper.checkDriverAndVehicleIsUse(pa);
            if(listVehicle1 != null && listVehicle1.size() > 0){
                result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
                result.setError("此车辆己有到货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listVehicle1.get(0).getCode()+"，如有疑问请与销售处联系！");
                return false;
            }
            //验证其他业务中的通知单
            OtherArrive oa = new OtherArrive();
            oa.setVehicleid(save.getVehicleid());
            List<OtherArrive> listVehicle2 = otherArriveMapper.checkDriverAndVehicleAndIcardIsUse(oa);
            if(listVehicle2!=null && listVehicle2.size()>0){
                if(StringUtils.equals(listVehicle2.get(0).getBusinesstype(), "5")){
                    result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
                    result.setError("此车辆己有其他入库通知单、待出厂后进行派车，现有车辆业务单据号为:"+listVehicle2.get(0).getCode()+"，如有疑问请与销售处联系！");
                    return false;
                }else if(StringUtils.equals(listVehicle2.get(0).getBusinesstype(), "7")){
                    result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
                    result.setError("此车辆己有其他出库通知单、待出厂后进行派车，现有车辆业务单据号为:"+listVehicle2.get(0).getCode()+"，如有疑问请与销售处联系！");
                    return false;
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
                        return false;
                    }
                }else{
                    result.setErrorCode(ErrorCode.CARD_NOT_EXIST);
                    return false;
                }
            }
        } else {
            result.setErrorCode(ErrorCode.VEHICLE_NOT_EXIST);
            return false;
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

	@Transactional
	@Override
	public Result update(SalesArriveSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null && StringUtils.isNotBlank(save.getId())
				&& StringUtils.isNotBlank(save.getBillid()) 
				&& StringUtils.isNotBlank(save.getBilldetailid())
				&& StringUtils.isNotBlank(save.getMaindeduction())
				&& StringUtils.isNotBlank(save.getVehicleid())
				&& save.getTakeamount() != null
				&& StringUtils.isNotBlank(save.getIds())){
			SystemUser user = userMapper.selectByPrimaryKey(save.getCurrUId());
			if (user != null) {
				if (save.getTakeamount() > 0) {
					SalesApplication refBill = salesApplicationMapper.selectByPrimaryKey(save.getBillid());
					SalesApplicationDetail refBillDetail = salesApplicationDetailMapper.selectByPrimaryKey(save.getBilldetailid());
					if (refBill != null && refBillDetail != null) {
						if (StringUtils.equals(refBill.getValidStatus(), Constant.ZERO_STRING)) {
							if (StringUtils.equals(refBill.getBilltypeid(), BillTypeEnum.BILL_TYPE_MORE_CAR.getCode())) {
								SalesArrive notice = salesArriveMapper.selectByPrimaryKey(save.getId());
								//仅有效的、未入厂的、未审核的通知单允许修改
								if (notice != null && StringUtils.equals(notice.getState(), Constant.ONE_STRING)) {
									if (!StringUtils.equals(notice.getStatus(), Constant.THREE_STRING)) {
										if (StringUtils.equals(notice.getAuditstatus(), Constant.ZERO_STRING)) {
											//查询原多单合并关系集
											List<SalesApplicationArrive> oldJoin = salesApplicationArriveMapper.listByNoticeId(save.getId());
											//循环回滚订单余量、预提量
											for (SalesApplicationArrive join : oldJoin) {
												SalesApplicationDetail sad = salesApplicationDetailMapper.selectByPrimaryKey(join.getBillDetailId());
												sad.setMargin(sad.getMargin() + join.getNumber());
												sad.setPretendingtake(sad.getPretendingtake() - join.getNumber());
												salesApplicationDetailMapper.updateByPrimaryKeySelective(sad);
											}
											//解除原多单合并关系
											salesApplicationArriveMapper.deleteByNoticeId(save.getId());
											//TODO
											JSONArray ids = JSON.parseArray(save.getIds());
											List<List<Object>> list = new ArrayList<List<Object>>();
											//总余量
											double sumMargin = 0D;
											for (int i = 0; i < ids.size(); i++) {
												JSONArray arr = ids.getJSONArray(i);
												String id = arr.getString(0);
												String detailId = arr.getString(1);
												SalesApplication sa = salesApplicationMapper.selectByPrimaryKey(id);
												SalesApplicationDetail sad = salesApplicationDetailMapper.selectByPrimaryKey(detailId);
												sumMargin += sad.getMargin();
												List<Object> item = new ArrayList<Object>();
												item.add(sa);
												item.add(sad);
												list.add(item);
											}
											if (save.getTakeamount() <= sumMargin) {
												final String mainBillDetailId = save.getBilldetailid();
												final String maindeduction = save.getMaindeduction();
												//对余量进行排序，升序（由小到大）
												sortMorBill(list, mainBillDetailId, maindeduction);
												//判断预提量是否满足多单合并规则
												if (validateMoreBillMargin(save, result, list)) {
													VehicleManage vehicle = vehicleManageMapper.selectByPrimaryKey(save.getVehicleid());
													if(StringUtils.equals(notice.getVehicleid(), vehicle.getId()) || validVehicle(vehicle, result)){
														if (StringUtils.isNotBlank(save.getDriverid())) {
															DriverManage driver = driverManageMapper.selectByPrimaryKey(save.getDriverid());
															if (validDriver(driver, result)) {
																result = updateNotice(save, user, list, refBill, refBillDetail, vehicle, driver);
															}
														} else {
															result = updateNotice(save, user, list, refBill, refBillDetail, vehicle, null);
														}
													}
												}
											} else {
												result.setErrorCode(ErrorCode.NOTICE_NUMBER_ERROR);
											}
										} else {
											result.setErrorCode(ErrorCode.NOTICE_DONT_UPDATE_ERROR);
										}
									} else {
										result.setErrorCode(ErrorCode.NOTICE_ON_INVALID);
									}
								} else {
									result.setErrorCode(ErrorCode.NOTICE_NOT_EXIST);
								}
							} else {
								result.setErrorCode(ErrorCode.APPLICATION_DONT_SEND_CAR);
							}
						} else {
							result.setErrorCode(ErrorCode.APPLICATION_IS_VALID_ERROR);
						}
					} else {
						result.setErrorCode(ErrorCode.APPLICATION_NOT_EXIST);
					}
				} else {
					result.setErrorCode(ErrorCode.NOTICE_NUMBER_ERROR);
				}
			} else {
				result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR17);
			}
		}
		return result;
	}

	//修改通知单，解除原多单合并关系记录和回滚订单余量和预提量，并重新绑定多单合并关系记录和回写订单余量和预提量
	private Result updateNotice(SalesArriveSave save, SystemUser user, List<List<Object>> list,
			SalesApplication refBill, SalesApplicationDetail refBillDetail, VehicleManage vehicle, DriverManage driver) {
		Result result = Result.getSuccessResult();
		SalesArrive bean = new SalesArrive();
		bean.setId(save.getId());
		bean.setVehicleid(vehicle.getId());
		bean.setVehicleno(vehicle.getVehicleno());
		bean.setVehiclerfid(vehicle.getRfid());
		if (driver != null) {
			bean.setDriverid(driver.getId());
			bean.setDrivername(driver.getName());
			bean.setDriveridentityno(driver.getIdentityno());
		}
		bean.setBillid(refBill.getId());
		bean.setBillcode(refBill.getCode());
		bean.setBilldetailid(refBillDetail.getId());
		bean.setUnit(refBillDetail.getUnit());
		bean.setTakeamount(save.getTakeamount());
		bean.setMaindeduction(save.getMaindeduction());
		bean.setRemarks(save.getRemarks());
		bean.setModifier(user.getId());
		bean.setModifytime(System.currentTimeMillis());
		salesArriveMapper.updateByPrimaryKeySelective(bean);
		//重新绑定多单合并关系
		//循环list 记录绑定关系和减扣量、扣减顺序，并回写子表 量。
		double number = save.getTakeamount();
		int idex = 1;
		for (List<Object> item : list) {
			SalesApplication sa = (SalesApplication) item.get(0);
			SalesApplicationDetail sad = (SalesApplicationDetail) item.get(1);
			double subNum = sad.getMargin();
			if (number > sad.getMargin()) {
				number -= subNum;
			} else {
				subNum = number;
			}
			SalesApplicationArrive join = new SalesApplicationArrive();
			join.setId(UUIDUtil.getId());
			join.setBillId(sa.getId());
			join.setBillDetailId(sad.getId());
			join.setNoticeId(bean.getId());
			join.setNumber(subNum);
			join.setSequence(idex);
			idex++;
			salesApplicationArriveMapper.insertSelective(join);
			sad.setMargin(sad.getMargin() - subNum);
			sad.setPretendingtake(sad.getPretendingtake() + subNum);
			salesApplicationDetailMapper.updateByPrimaryKeySelective(sad);
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
				List<SalesApplicationArrive> list = salesApplicationArriveMapper.listByNoticeId(bean.getId());
				List<String> billIds = new ArrayList<String>();
				List<String> billDetailIds = new ArrayList<String>();
				for (SalesApplicationArrive join : list) {
					billIds.add(join.getBillId());
					billDetailIds.add(join.getBillDetailId());
				}
				List<SalesApplicationResp> listApplication = salesApplicationService.selectByIds(billIds, false);
				List<SalesApplicationDetailResp> listApplicationDetail = salesApplicationDetailService.selectByIds(billDetailIds);
				for(SalesApplicationArrive join : list){
					for(SalesApplicationResp applicationResp : listApplication){
						if(StringUtils.equals(applicationResp.getId(), join.getBillId())){
							for(SalesApplicationDetailResp detailResp : listApplicationDetail){
								if(StringUtils.equals(detailResp.getId(), join.getBillDetailId())){
									applicationResp.getList().add(detailResp);
									resp.getListApplication().add(applicationResp);
								}
							}
							
						}
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
			sa.setForceOutFactory(Constant.ONE_NUMBER);
			sa.setForceOutFactoryPerson(query.getCurrUId());
			sa.setForceOutFactoryTime(System.currentTimeMillis());
			sa.setAbnormalperson(query.getCurrUId());
			sa.setAbnormalpersonname(systemUserService.getUser(query.getCurrUId()).getName());
			sa.setAbnormaltime(System.currentTimeMillis());
			sa.setModifier(query.getCurrUId());
			sa.setModifytime(System.currentTimeMillis());
			salesArriveMapper.updateByPrimaryKeySelective(sa);
			AccessRecord accessRecord = accessRecordMapper.selectByNoticeId(sa.getId());
			AccessRecord ar = new AccessRecord();
			ar.setId(accessRecord.getId());
			ar.setOuttime(System.currentTimeMillis());
			ar.setAccesstype(Constant.TWO_STRING);
			ar.setModifier(query.getCurrUId());
			ar.setModifytime(System.currentTimeMillis());
			accessRecordMapper.updateByPrimaryKeySelective(ar);
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
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
			//是否原发(0：否，1：是)
			if (application != null && applicationDetail != null) {
			    PrimarySetting record = new PrimarySetting();
			    record.setMaterialid(applicationDetail.getMaterielid());
			    record.setSupplierid(application.getSupplierid());
			    List<PrimarySetting> list = primarySettingMapper.selectSelective(record);
		        if(CollectionUtils.isNotEmpty(list)) {
		            api.setPrimary(Constant.ONE_STRING);
		        } else {
		            api.setPrimary(Constant.ZERO_STRING);
		        }
			}
			api.setServicetype(listPurchase.get(0).getType());
			api.setNotionformcode(listPurchase.get(0).getCode());
			api.setMinemouth(application.getMinemouthname());
			api.setNumber(listPurchase.get(0).getArrivalamount() == null ? "" : listPurchase.get(0).getArrivalamount().toString());
			api.setStatus(listPurchase.get(0).getStatus());
			api.setSignStatus(listPurchase.get(0).getSignStatus()== null ? 0 : listPurchase.get(0).getSignStatus());
			api.setBillNo(application.getCode());
			api.setRemark(application.getRemark()==null?"":application.getRemark());
		}
		return api;
	}
	private ApiNoticeResp getOtherArriveDetail(String vehicleno, String vehiclerfid) {
		ApiNoticeResp api = null;
		if(StringUtils.isNotBlank(vehicleno)){
			VehicleManage vehicle = vehicleManageMapper.selectByVehicleno(vehicleno);
			if(vehicle!=null){
				List<OtherArrive> listPurchase = otherArriveMapper.validNoticeByVehicle(vehicle.getId());
				if(CollectionUtils.isNotEmpty(listPurchase)){
					api = new ApiNoticeResp();
//					PurchaseApplication application = purchaseApplicationMapper.selectByPrimaryKey(listPurchase.get(0).getBillid());
//					PurchaseApplicationDetail applicationDetail = purchaseApplicationDetailMapper.selectByPrimaryKey(listPurchase.get(0).getBilldetailid());
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
					api.setBillNo(oaResp.getCode());
					api.setRemark(oaResp.getRemark());
				}
			}
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
			api.setBillNo(resp.getCode());
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
				SalesApplicationArrive join = new SalesApplicationArrive();
				join.setId(UUIDUtil.getId());
				join.setBillId(bean.getBillid());
				join.setBillDetailId(bean.getBilldetailid());
				join.setNoticeId(bean.getId());
				join.setNumber(bean.getTakeamount());
				join.setSequence(1);
				if(salesArriveMapper.insertSelective(bean) > 0 
						&& StringUtils.equals(systemCodeService.updateCodeItem(codeReq).getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())
						&& salesApplicationArriveMapper.insertSelective(join) > 0){
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
					salesArriveMapper.emptyForceOutFactoryByVehicle(save.getVehicleid());
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
