package com.tianrui.quartz.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.businessManage.purchaseManage.IPushSingleService;
import com.tianrui.api.intf.businessManage.salesManage.ISalesApplicationService;
import com.tianrui.api.intf.system.auth.ISystemUserService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.businessManage.purchaseManage.PushSingleReq;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.quartz.common.ApiParamUtils;
import com.tianrui.quartz.common.HttpUtils;
import com.tianrui.service.bean.basicFile.measure.DriverManage;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;
import com.tianrui.service.bean.basicFile.nc.CustomerManage;
import com.tianrui.service.bean.basicFile.nc.SupplierManage;
import com.tianrui.service.bean.businessManage.otherManage.OtherArrive;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseArrive;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplication;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationArrive;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationDetail;
import com.tianrui.service.bean.businessManage.salesManage.SalesArrive;
import com.tianrui.service.bean.system.auth.SmUser;
import com.tianrui.service.bean.system.auth.SystemUser;
import com.tianrui.service.mapper.basicFile.measure.DriverManageMapper;
import com.tianrui.service.mapper.basicFile.measure.VehicleManageMapper;
import com.tianrui.service.mapper.basicFile.nc.CustomerManageMapper;
import com.tianrui.service.mapper.basicFile.nc.SupplierManageMapper;
import com.tianrui.service.mapper.businessManage.otherManage.OtherArriveMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseArriveMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationArriveMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationDetailMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesArriveMapper;
import com.tianrui.service.mapper.system.auth.SmUserMapper;
import com.tianrui.service.mapper.system.auth.SystemUserMapper;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.BusinessConstants;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.Md5Utils;
import com.tianrui.smartfactory.common.utils.UUIDUtil;

@Service
public class TaskJobService {
	
	@Autowired
	private ISalesApplicationService salesApplicationService;
	@Autowired
	private SalesApplicationMapper salesApplicationMapper;
	@Autowired
	private SalesApplicationDetailMapper salesApplicationDetailMapper;
	@Autowired
	private ISystemUserService systemUserService;
	@Autowired
	private SmUserMapper smUserMapper;
	@Autowired
	private CustomerManageMapper customerManageMapper;
	@Autowired
	private SupplierManageMapper supplierManageMapper;
	@Autowired
	private SystemUserMapper systemUserMapper;
	@Autowired
	private IPushSingleService pushSingleService;
	@Autowired
	private SalesArriveMapper salesArriveMapper;
	@Autowired
	private PurchaseArriveMapper purchaseArriveMapper;
	@Autowired
	private OtherArriveMapper otherArriveMapper;
	@Autowired
	private VehicleManageMapper vehicleManageMapper;
	@Autowired
	private DriverManageMapper driverManageMapper;
	@Autowired
	private ISystemCodeService systemCodeService;
	@Autowired
	private SalesApplicationArriveMapper salesApplicationArriveMapper;

	@Transactional
	public void returnSalesApplication() throws Exception {
		SalesApplication sa = new SalesApplication();
		sa.setSource(Constant.ONE_STRING);
		sa.setState(Constant.ONE_STRING);
		sa.setValidStatus(Constant.ZERO_STRING);
		List<SalesApplication> list1 = salesApplicationMapper.selectSelective(sa);
		if(CollectionUtils.isNotEmpty(list1)){
			Set<String> billIds = new HashSet<String>();
			for(SalesApplication bean : list1){
				billIds.add(bean.getId());
			}
			List<SalesApplicationResp> listSales = salesApplicationService.selectByIds(new ArrayList<String>(billIds), true);
			if(CollectionUtils.isNotEmpty(listSales)){
				List<SmUser> smUserList = null;
				for(SalesApplicationResp resp : listSales){
					smUserList = getSmUser(resp.getAuditid());
					if(CollectionUtils.isNotEmpty(smUserList)){
						resp.setAuditid(smUserList.get(0).getId());
					}
					smUserList = getSmUser(resp.getMakerid());
					if(CollectionUtils.isNotEmpty(smUserList)){
						resp.setMakerid(smUserList.get(0).getId());
					}
					smUserList = getSmUser(resp.getCreator());
					if(CollectionUtils.isNotEmpty(smUserList)){
						resp.setCreator(smUserList.get(0).getId());
					}
					smUserList = getSmUser(resp.getModifier());
					if(CollectionUtils.isNotEmpty(smUserList)){
						resp.setModifier(smUserList.get(0).getId());
					}
				}
			}
			PushSingleReq ps = new PushSingleReq();
			ps.setRequisitionType(Constant.TWO_STRING);
			ps.setCreatetime(System.currentTimeMillis());
			ps.setModifytime(System.currentTimeMillis());
			ApiResult apiResult = HttpUtils.post(ApiParamUtils.getApiParam(listSales), Constant.URL_DOMAIN + Constant.URL_RETURN_SALESAPPLICATION);
			if(apiResult != null){
				if (StringUtils.equals(apiResult.getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())) {
					SalesApplication bill = new SalesApplication();
					bill.setSource(Constant.ZERO_STRING);
					bill.setPushStatus(Constant.ONE_STRING);
					for(String billId : billIds){
						bill.setId(billId);
						salesApplicationMapper.updateByPrimaryKeySelective(bill);
					}
	                ps.setPushStatus(Constant.ONE_STRING);
				} else {
	                ps.setPushStatus(Constant.THREE_STRING);
				}
	            ps.setReasonFailure(apiResult.getError());
	            ps.setDesc1(apiResult.getCode());
			} else {
				ps.setPushStatus(Constant.THREE_STRING);
			    ps.setReasonFailure("FC-DC销售申请单推单失败，连接超时。");
			    ps.setDesc1("-1");
			}
			for (SalesApplication bean : list1) {
				ps.setId(UUIDUtil.getId());
				ps.setRequisitionNum(bean.getCode());
				pushSingleService.savePushSingle(ps);
			}
		}
	}

	private List<SmUser> getSmUser(String id) throws Exception {
		List<SmUser> smUserList = null;
		SystemUserResp user = systemUserService.getUser(id);
		if(user != null){
			SmUser smUser = new SmUser();
			smUser.setCode(user.getCode());
			smUserList = smUserMapper.selectSelective(smUser);
		}
		return smUserList;
	}

	@Transactional
	public void customer_supplier_user() throws Exception {
		List<CustomerManage> customerList = customerManageMapper.findCustomerNotSystemUser();
		List<SupplierManage> supplierList = supplierManageMapper.findSupplierNotSystemUser();
		List<SystemUser> list = new ArrayList<SystemUser>();
		if(CollectionUtils.isNotEmpty(customerList)){
			list.clear();
			for(CustomerManage cm : customerList){
				SystemUser user = new SystemUser();
				user.setId(UUIDUtil.getId());
				user.setNcid(cm.getId());
				user.setCode(cm.getCode());
				user.setName(cm.getName());
				user.setAccount(cm.getCode());
				user.setPassword(Md5Utils.MD5("666666"));
				user.setIdentityTypes("1");
				user.setMobilePhone(" ");
				user.setOrgid(Constant.ORG_ID);
				user.setSource("1");
				user.setIsvalid(BusinessConstants.USER_VALID_BYTE);
				user.setIslock(BusinessConstants.USER_INVALID_BYTE);
				user.setCreator("admin");
				user.setCreatetime(System.currentTimeMillis());
				user.setUtc(new Date());
				list.add(user);
			}
			int count = systemUserMapper.insertBatch(list);
			System.out.println("客户同步到用户表成功：共"+count+"条！");
		}
		if(CollectionUtils.isNotEmpty(supplierList)){
			list.clear();
			for(SupplierManage sm : supplierList){
				if (systemUserMapper.selectByPrimaryKey(sm.getId()) == null) {
					SystemUser user = new SystemUser();
					user.setId(UUIDUtil.getId());
					user.setNcid(sm.getId());
					user.setCode(sm.getCode());
					user.setAccount(sm.getCode());
					user.setName(sm.getName());
					user.setPassword(Md5Utils.MD5("666666"));
					user.setIdentityTypes("2");
					user.setMobilePhone(" ");
					user.setOrgid(Constant.ORG_ID);
					user.setSource("1");
					user.setIsvalid(BusinessConstants.USER_VALID_BYTE);
					user.setIslock(BusinessConstants.USER_INVALID_BYTE);
					user.setCreator("admin");
					user.setCreatetime(System.currentTimeMillis());
					user.setUtc(new Date());
					list.add(user);
				}
			}
			int count = systemUserMapper.insertBatch(list);
			System.out.println("供应商同步到用户表成功：共"+count+"条！");
		}
		
	}

	public void oneBilOneCarSaveNotice() throws Exception {
		SalesApplication bean = new SalesApplication();
		bean.setStatus(Constant.ONE_STRING);
		bean.setBilltypeid(Constant.ZERO_STRING);
		bean.setValidStatus(Constant.ZERO_STRING);
		bean.setNcStatus(Constant.TWO_STRING);
		List<SalesApplication> list = salesApplicationMapper.selectSelective(bean);
		if (CollectionUtils.isNotEmpty(list)) {
			for (SalesApplication sa : list) {
				if (sa.getBillSource() == Constant.ZERO_NUMBER) {
					//nc
					if (StringUtils.isNotBlank(sa.getVehicleNo())) {
						List<SalesApplicationDetail> detailList = salesApplicationDetailMapper.selectBySalesId(sa.getId());
						SalesArrive notice = new SalesArrive();
						notice.setBillid(sa.getId());
						List<SalesArrive> noticeList = salesArriveMapper.selectSelective(notice);
						if (CollectionUtils.isEmpty(noticeList)) {
							VehicleManage vehicle = new VehicleManage();
							vehicle.setId(UUIDUtil.getId());
							vehicle.setCode(getCode("CL", sa.getMakerid(), true));
							vehicle.setInternalcode(getCode("CL", sa.getMakerid(), false));
							vehicle.setVehicleno(sa.getVehicleNo());
							vehicle.setTransporttype(Constant.ZERO_STRING);
							vehicle.setOrgid(Constant.ORG_ID);
							vehicle.setOrgname(Constant.ORG_NAME);
							vehicle.setIsvalid(Constant.ONE_STRING);
							vehicle.setIsblacklist(Constant.ZERO_STRING);
							vehicle.setState(Constant.ONE_STRING);
							vehicleManageMapper.insertSelective(vehicle);
							saveOneBillOneCarNotice(sa, detailList.get(0), vehicle, null);
						}
					}
				} else {
					List<SalesApplicationDetail> detailList = salesApplicationDetailMapper.selectBySalesId(sa.getId());
					SalesArrive notice = new SalesArrive();
					notice.setBillid(sa.getId());
					List<SalesArrive> noticeList = salesArriveMapper.selectSelective(notice);
					if (CollectionUtils.isEmpty(noticeList)) {
						//app 和 平台
						VehicleManage vehicle = vehicleManageMapper.selectByPrimaryKey(sa.getVehicleId());
						if (validVehicle(vehicle)) {
							if (StringUtils.isNotBlank(sa.getDriverId())) {
								DriverManage driver = driverManageMapper.selectByPrimaryKey(sa.getDriverId());
								if (validDriver(driver)) {
									saveOneBillOneCarNotice(sa, detailList.get(0), vehicle, driver);
								}
							} else {
								saveOneBillOneCarNotice(sa, detailList.get(0), vehicle, null);
							}
						}
					}
				}
			}
		}
	}

	private void saveOneBillOneCarNotice(SalesApplication sa, SalesApplicationDetail sad,
			VehicleManage vehicle, DriverManage driver) throws Exception {
		SalesArrive bean = new SalesArrive();
		bean.setId(UUIDUtil.getId());
		bean.setCode(getCode("TH", sa.getMakerid(), true));
		bean.setAuditstatus(Constant.ONE_STRING);
		bean.setSource(Constant.TWO_STRING);
		bean.setStatus(Constant.ZERO_STRING);
		bean.setVehicleid(vehicle.getId());
		bean.setVehicleno(vehicle.getVehicleno());
		bean.setVehiclerfid(vehicle.getRfid());
		if (driver != null) {
			bean.setDriverid(driver.getId());
			bean.setDrivername(driver.getName());
			bean.setDriveridentityno(driver.getIdentityno());
		}
		bean.setBillid(sa.getId());
		bean.setBillcode(sa.getCode());
		bean.setBilldetailid(sad.getId());
		bean.setUnit(sad.getUnit());
		bean.setTakeamount(sad.getSalessum());
		bean.setState(Constant.ONE_STRING);
		bean.setMakerid(sa.getMakerid());
		bean.setMakebillname(sa.getMakebillname());
		bean.setMakebilltime(System.currentTimeMillis());
		bean.setCreator(sa.getMakerid());
		bean.setCreatetime(System.currentTimeMillis());
		salesArriveMapper.insertSelective(bean);
		updateCode("TH", sa.getMakerid());
		SalesApplicationArrive join = new SalesApplicationArrive();
		join.setId(UUIDUtil.getId());
		join.setBillId(sa.getId());
		join.setBillDetailId(sad.getId());
		join.setNoticeId(bean.getId());
		join.setNumber(sad.getSalessum());
		join.setSequence(1);
		salesApplicationArriveMapper.insertSelective(join);
		sad.setMargin(0D);
		sad.setPretendingtake(sad.getSalessum());
		salesApplicationDetailMapper.updateByPrimaryKeySelective(sad);
	}

	private boolean validVehicle(VehicleManage vehicle) {
		boolean flag = false;
		if (vehicle != null && StringUtils.equals(vehicle.getState(), Constant.ONE_STRING)) {
			if (StringUtils.equals(vehicle.getIsvalid(), Constant.ONE_STRING)) {
				if (StringUtils.equals(vehicle.getIsblacklist(), Constant.ZERO_STRING)) {
					PurchaseArrive pa = new PurchaseArrive();
					pa.setVehicleid(vehicle.getId());
					List<PurchaseArrive> paList = purchaseArriveMapper.checkDriverAndVehicleIsUse(pa);
					if (CollectionUtils.isEmpty(paList)) {
		            	SalesArrive sa = new SalesArrive();
		                sa.setVehicleid(vehicle.getId());
		                List<SalesArrive> saList = salesArriveMapper.checkDriverAndVehicleIsUse(sa);
		                if (CollectionUtils.isEmpty(saList)) {
		                	OtherArrive oa = new OtherArrive();
		                    oa.setVehicleid(vehicle.getId());
		                    List<OtherArrive> oaList = otherArriveMapper.checkDriverAndVehicleAndIcardIsUse(oa);
		                    if (CollectionUtils.isNotEmpty(oaList)) {
		                        if(!StringUtils.equals(oaList.get(0).getBusinesstype(), Constant.FIVE_STRING) && !StringUtils.equals(oaList.get(0).getBusinesstype(), Constant.SEVEN_STRING)){
		                        	flag = true;
		                        }
		                    } else {
	                        	flag = true;
	                        }
		                }
		            }
				}
			}
		}
		return flag;
	}
	
	private boolean validDriver(DriverManage driver) {
		boolean flag = false;
		if (driver != null && StringUtils.equals(driver.getState(), Constant.ONE_STRING)) {
			if (StringUtils.equals(driver.getIsvalid(), Constant.ONE_STRING)) {
				flag = true;
			}
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
}
