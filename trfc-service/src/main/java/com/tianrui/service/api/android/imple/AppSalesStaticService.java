package com.tianrui.service.api.android.imple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.tianrui.api.intf.api.android.imple.IAppSalesStaticService;
import com.tianrui.api.intf.businessManage.purchaseManage.IPushSingleService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.android.BillListParam;
import com.tianrui.api.req.android.BillSave;
import com.tianrui.api.req.android.HomePageParam;
import com.tianrui.api.req.android.LoginUserParam;
import com.tianrui.api.req.android.MyPnListParam;
import com.tianrui.api.req.android.MyVehicleListParam;
import com.tianrui.api.req.android.NoticeListParam;
import com.tianrui.api.req.android.NoticeSave;
import com.tianrui.api.req.android.SearchKeyParam;
import com.tianrui.api.req.businessManage.purchaseManage.PushSingleReq;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.android.BillListVo;
import com.tianrui.api.resp.android.HomeBillVo;
import com.tianrui.api.resp.android.HomeNoticeVo;
import com.tianrui.api.resp.android.MyPnListVo;
import com.tianrui.api.resp.android.MyVehicleListVo;
import com.tianrui.api.resp.android.NoticeListVo;
import com.tianrui.api.resp.android.UserDriverVo;
import com.tianrui.api.resp.android.UserVehicleVo;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationDetailResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationResp;
import com.tianrui.api.resp.system.merchants.AppCutoverGroup;
import com.tianrui.service.bean.basicFile.measure.DriverManage;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;
import com.tianrui.service.bean.basicFile.nc.CustomerManage;
import com.tianrui.service.bean.basicFile.nc.MaterielManage;
import com.tianrui.service.bean.businessManage.otherManage.OtherArrive;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseArrive;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplication;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationArrive;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationDetail;
import com.tianrui.service.bean.businessManage.salesManage.SalesArrive;
import com.tianrui.service.bean.common.UserDriver;
import com.tianrui.service.bean.common.UserVehicle;
import com.tianrui.service.bean.system.auth.Organization;
import com.tianrui.service.bean.system.auth.SmUser;
import com.tianrui.service.bean.system.auth.SystemUser;
import com.tianrui.service.bean.system.merchants.CustomerGroup;
import com.tianrui.service.mapper.basicFile.measure.DriverManageMapper;
import com.tianrui.service.mapper.basicFile.measure.VehicleManageMapper;
import com.tianrui.service.mapper.basicFile.nc.CustomerManageMapper;
import com.tianrui.service.mapper.basicFile.nc.MaterielManageMapper;
import com.tianrui.service.mapper.basicFile.nc.WarehouseManageMapper;
import com.tianrui.service.mapper.businessManage.otherManage.OtherArriveMapper;
import com.tianrui.service.mapper.businessManage.poundNoteMaintain.PoundNoteMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseArriveMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationArriveMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationDetailMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesArriveMapper;
import com.tianrui.service.mapper.common.UserDriverMapper;
import com.tianrui.service.mapper.common.UserVehicleMapper;
import com.tianrui.service.mapper.system.auth.OrganizationMapper;
import com.tianrui.service.mapper.system.auth.SmUserMapper;
import com.tianrui.service.mapper.system.auth.SystemUserMapper;
import com.tianrui.service.mapper.system.merchants.CustomerGroupMapper;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.common.ApiParamUtils;
import com.tianrui.smartfactory.common.common.HttpUtils;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.enums.BillTypeEnum;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.AppResult;
import com.tianrui.smartfactory.common.vo.PaginationVO;

@Service
public class AppSalesStaticService implements IAppSalesStaticService {

	@Autowired
	private SystemUserMapper userMapper;
	@Autowired
	private SystemUserMapper systemUserMapper;
	@Autowired
	private SalesApplicationMapper salesApplicationMapper;
	@Autowired
	private SalesApplicationDetailMapper salesApplicationDetailMapper;
	@Autowired
	private SalesApplicationArriveMapper salesApplicationArriveMapper;
	@Autowired
	private SalesArriveMapper salesArriveMapper;
	@Autowired
	private PurchaseArriveMapper purchaseArriveMapper;
	@Autowired
	private ISystemCodeService systemCodeService;
	@Autowired
	private CustomerManageMapper customerManageMapper;
	@Autowired
	private OrganizationMapper organizationMapper;
	@Autowired
	private VehicleManageMapper vehicleManageMapper;
	@Autowired
	private DriverManageMapper driverManageMapper;
	@Autowired
	private MaterielManageMapper materielManageMapper;
	@Autowired
	private UserVehicleMapper userVehicleMapper;
	@Autowired
	private UserDriverMapper userDriverMapper;
	@Autowired
	private PoundNoteMapper poundNoteMapper;
	@Autowired
	private OtherArriveMapper otherArriveMapper;
	@Autowired
	private CustomerGroupMapper customerGroupMapper;
	@Autowired
	private IPushSingleService pushSingleService;
	@Autowired
	private SmUserMapper smUserMapper;
	@Autowired
	private WarehouseManageMapper warehouseManageMapper;
	
	@Override
	public AppResult home(HomePageParam param) {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getUserId())
				&& StringUtils.isNotBlank(param.getIDType())) {
			if (StringUtils.equals(param.getIDType(), Constant.ONE_STRING)) {
				result = customerHome(param);
			} else {
				result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR14);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	/**
	 * @annotation 客户首页数据
	 * @param param
	 * @return
	 */
	private AppResult customerHome(HomePageParam param) {
		AppResult result = AppResult.getAppResult();
		if (StringUtils.isNotBlank(param.getSalesOrg())) {
			Object[] objArr = new Object[] {"","",""};
			List<HomeBillVo> billList = salesApplicationMapper.appHomeBill(param);
			if (CollectionUtils.isNotEmpty(billList)) {
				objArr[0] = billList.get(0);
			}
			List<HomeNoticeVo> noticeList = salesArriveMapper.appHomeNotice(param);
			if (CollectionUtils.isNotEmpty(noticeList)) {
				objArr[1] = noticeList.get(0);
			}
			List<String> vehicleList = salesArriveMapper.appHomeVehicle(param);
			if (CollectionUtils.isNotEmpty(vehicleList)) {
				String[] strArr = new String[] {"","",""};
				for (int i=0;i<vehicleList.size();i++) {
					if (i > 2) {
						break;
					}
					strArr[i] = vehicleList.get(i);
				}
				objArr[2] = strArr;
			}
			result.setData(objArr);
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	@Override
	public AppResult billList(BillListParam param) {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getUserId())
				&& StringUtils.isNotBlank(param.getIDType())
				&& StringUtils.isNotBlank(param.getType())) {
			if (StringUtils.equals(param.getIDType(), Constant.ONE_STRING)) {
				result = customerBillList(param);
			} else {
				result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR14);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	private AppResult customerBillList(BillListParam param) {
		AppResult result = AppResult.getAppResult();
		PaginationVO<BillListVo> page = new PaginationVO<BillListVo>();
		long count = salesApplicationMapper.appBillListCount(param);
		if (count > 0) {
			param.setStart((param.getPageNo() - 1) * param.getPageSize());
			param.setLimit(param.getPageSize());
			List<BillListVo> list = salesApplicationMapper.appBillList(param);
			page.setList(list);
		}
		page.setPageNo(param.getPageNo());
		page.setPageSize(param.getPageSize());
		page.setTotal(count);
		result.setData(page);
		result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		return result;
	}

	@Override
	public AppResult billDetail(BillListParam param) {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getId())
				&& StringUtils.isNotBlank(param.getDetailId())
				&& StringUtils.isNotBlank(param.getIDType())) {
			if (StringUtils.equals(param.getIDType(), Constant.ONE_STRING)) {
				result.setData(salesApplicationMapper.appBillDetail(param));
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			} else {
				result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR14);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	@Transactional
	@Override
	public AppResult saveBill(BillSave param) throws Exception {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getUserId())
				&& StringUtils.isNotBlank(param.getIDType())
				&& StringUtils.isNotBlank(param.getSalesOrg())
				&& StringUtils.isNotBlank(param.getMaterial())
				&& StringUtils.isNotBlank(param.getVehicle())
				&& param.getNumber() !=null
				&& StringUtils.isNotBlank(param.getUnit())
				&& param.getBillTime() !=null) {
			SystemUser user = systemUserMapper.selectByPrimaryKey(param.getUserId());
			if (user != null) {
				VehicleManage vehicle = vehicleManageMapper.selectByPrimaryKey(param.getVehicle());
				if (validVehicle(vehicle, result)) {
					SalesApplication sa = setSalesApplication(param, user, vehicle);
					SalesApplicationDetail sad = setSalesApplicationDetail(param, sa.getId());
					oneBillOneCarPush(sa, sad);
					salesApplicationMapper.insertSelective(sa);
					salesApplicationDetailMapper.insertSelective(sad);
					updateCode("XXSO", param.getUserId());
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}
			} else {
				result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR1);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	private void oneBillOneCarPush(SalesApplication sa, SalesApplicationDetail sad) throws Exception {
		SalesApplicationResp sar = new SalesApplicationResp(); 
		PropertyUtils.copyProperties(sar, sa);
		SalesApplicationDetailResp sadr = new SalesApplicationDetailResp(); 
		PropertyUtils.copyProperties(sadr, sad);
		
		List<SmUser> smUserList = null;
		smUserList = getSmUser(sar.getAuditid());
		if(CollectionUtils.isNotEmpty(smUserList)){
			sar.setAuditid(smUserList.get(0).getId());
		}
		smUserList = getSmUser(sar.getMakerid());
		if(CollectionUtils.isNotEmpty(smUserList)){
			sar.setMakerid(smUserList.get(0).getId());
		}
		smUserList = getSmUser(sar.getCreator());
		if(CollectionUtils.isNotEmpty(smUserList)){
			sar.setCreator(smUserList.get(0).getId());
		}
		smUserList = getSmUser(sar.getModifier());
		if(CollectionUtils.isNotEmpty(smUserList)){
			sar.setModifier(smUserList.get(0).getId());
		}
		
		List<SalesApplicationResp> sarList = new ArrayList<SalesApplicationResp>();
		List<SalesApplicationDetailResp> sadrList = new ArrayList<SalesApplicationDetailResp>();
		sadrList.add(sadr);
		sar.setList(sadrList);
		sarList.add(sar);
		
		ApiResult apiResult = HttpUtils.post(ApiParamUtils.getApiParam(sarList), Constant.URL_DOMAIN + Constant.URL_RETURN_SALESAPPLICATION);
		PushSingleReq ps = new PushSingleReq();
		ps.setId(UUIDUtil.getId());
		ps.setRequisitionNum(sa.getCode());
		ps.setRequisitionType(Constant.TWO_STRING);
		ps.setCreatetime(System.currentTimeMillis());
		ps.setModifytime(System.currentTimeMillis());
		if(apiResult != null){
			if (StringUtils.equals(apiResult.getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())) {
				sa.setSource(Constant.ZERO_STRING);
				sa.setNcAuditStatus(Constant.ONE_STRING);
                ps.setPushStatus(Constant.ONE_STRING);
			} else {
                ps.setPushStatus(Constant.THREE_STRING);
			}
            ps.setReasonFailure(apiResult.getError());
            ps.setDesc1(apiResult.getCode());
		} else {
			ps.setPushStatus(Constant.THREE_STRING);
		    ps.setReasonFailure("FC-DC客商APP自制销售一单一车申请单推单失败，连接超时。");
		    ps.setDesc1("-1");
		}
		pushSingleService.savePushSingle(ps);
	}

	private List<SmUser> getSmUser(String id) throws Exception {
		List<SmUser> smUserList = null;
		SystemUser user = systemUserMapper.selectByPrimaryKey(id);
		if(user != null){
			SmUser smUser = new SmUser();
			smUser.setCode(user.getCode());
			smUserList = smUserMapper.selectSelective(smUser);
		}
		return smUserList;
	}

	private SalesApplication setSalesApplication(BillSave param, SystemUser user, VehicleManage vehicle) throws Exception {
		SalesApplication sa = new SalesApplication();
		sa.setId(UUIDUtil.getId());
		sa.setCode(getCode("XXSO", param.getUserId(), true));
		sa.setStatus(Constant.ONE_STRING);
		sa.setSource(Constant.ONE_STRING);
		sa.setBilltypeid(BillTypeEnum.BILL_TYPE_ONE_CAR.getCode());
		sa.setBilltypename(BillTypeEnum.BILL_TYPE_ONE_CAR.getName());
		sa.setCustomerid(user.getNcid());
		sa.setCustomername(user.getName());
		CustomerManage customer = customerManageMapper.selectByPrimaryKey(user.getNcid());
		if (customer != null) {
			sa.setChannelcode(customer.getChannelcode());
			sa.setSalesmanid(customer.getSalesmanid());
			sa.setSalesmanname(customer.getSalesmanname());
		}
		sa.setBilltime(param.getBillTime());
		sa.setOrgid(param.getSalesOrg());
		Organization org = organizationMapper.selectByPrimaryKey(param.getSalesOrg());
		if (org != null) {
			sa.setOrgname(org.getName());
		}
		sa.setTransportcompanyid(Constant.ORG_ID);
		sa.setTransportcompanyname(Constant.ORG_NAME);
		sa.setState(Constant.ONE_STRING);
		sa.setMakerid(param.getUserId());
		sa.setMakebillname(sa.getCustomername());
		sa.setMakebilltime(System.currentTimeMillis());
		sa.setCreator(param.getUserId());
		sa.setCreatetime(System.currentTimeMillis());
		sa.setVehicleId(vehicle.getId());
		sa.setVehicleNo(vehicle.getVehicleno());
		sa.setRfid(vehicle.getRfid());
		if (StringUtils.isNotBlank(param.getDriver())) {
			DriverManage driver = driverManageMapper.selectByPrimaryKey(param.getDriver());
			if (driver != null) {
				sa.setDriverId(driver.getId());
				sa.setDriverName(driver.getName());
			}
		}
		sa.setBillSource(Constant.TWO_NUMBER);
		sa.setValidStatus(Constant.ZERO_STRING);
		sa.setNcAuditStatus(Constant.ZERO_STRING);
		return sa;
	}

	private SalesApplicationDetail setSalesApplicationDetail(BillSave param, String salesId) {
		SalesApplicationDetail sad = new SalesApplicationDetail();
		sad.setId(UUIDUtil.getId());
		sad.setSalesid(salesId);
		sad.setMaterielid(param.getMaterial());
		MaterielManage material = materielManageMapper.selectByPrimaryKey(param.getMaterial());
		sad.setMaterielname(material.getName());
		sad.setUnit(param.getUnit());
		sad.setSalessum(param.getNumber());
		sad.setMargin(0D);
		return sad;
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
	public AppResult billDelete(BillListParam param) throws Exception {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getUserId())
				&& StringUtils.isNotBlank(param.getId())) {
			SalesApplication sa = salesApplicationMapper.selectByPrimaryKey(param.getId());
			if (!StringUtils.equals(sa.getNcAuditStatus(), Constant.TWO_STRING)) {
				List<SalesApplicationDetail> list = salesApplicationDetailMapper.selectBySalesId(sa.getId());
				if (sa != null && CollectionUtils.isNotEmpty(list)) {
					if (StringUtils.equals(sa.getCustomerid(), param.getNcId())) {
						if (StringUtils.equals(sa.getBilltypeid(), Constant.ZERO_STRING)) {
							//一单一车作废
							if (StringUtils.equals(sa.getSource(), Constant.ONE_STRING) && 
									StringUtils.equals(sa.getNcAuditStatus(), Constant.ZERO_STRING)) {
								//脱机的未审核的直接作废
								sa.setValidStatus(Constant.TWO_STRING);
								salesApplicationMapper.updateByPrimaryKeySelective(sa);
							} else {
								//已经联机（推送到DC）的，发送作废请求
								Map<String, String> map = new HashMap<String, String>();
								map.put("id", sa.getId());
								map.put("detailId", list.get(0).getId());
								//上传dc，申请作废，状态改为作废中
								//由dc回写作废状态
								//记录推送日志
								result = pushDC(param, sa, map);
							}
						} else {
							//一单多车不允许作废
							result.setErrorCode(ErrorCode.APPLICATION_DONT_MORE_SEND_VALID);
						}
					} else {
						result.setErrorCode(ErrorCode.APPLICATION_NOT_EXIST);
					}
				} else {
					result.setErrorCode(ErrorCode.APPLICATION_NOT_EXIST);
				}
			} else {
				result.setErrorCode(ErrorCode.APPLICATION_NOT_DELETE1);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	private AppResult pushDC(BillListParam param, SalesApplication bill, Map<String, String> map) throws Exception {
		AppResult result = AppResult.getAppResult();
		PushSingleReq ps = new PushSingleReq();
		ps.setId(UUIDUtil.getId());
		ps.setRequisitionNum(bill.getCode());
		ps.setRequisitionType(Constant.TWO_STRING);
		ps.setCreator(param.getUserId());
		ps.setCreatetime(System.currentTimeMillis());
		ps.setModifytime(System.currentTimeMillis());
		ApiResult apiResult = HttpUtils.post(ApiParamUtils.getApiParam(map), Constant.URL_DOMAIN + Constant.URL_SALESAPPLICATION_DELETE);
		if (apiResult != null) {
			if (StringUtils.equals(apiResult.getCode(), Constant.SUCCESS)) {
		        ps.setPushStatus(Constant.ONE_STRING);
		        bill.setValidStatus(Constant.ONE_STRING);
		        salesApplicationMapper.updateByPrimaryKeySelective(bill);
		        result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		    } else {
		        ps.setPushStatus(Constant.THREE_STRING);
		        result.setErrorCode(ErrorCode.APP_BILL_RETURN_ERROR);
		        result.setMessage(apiResult.getError());
		    }
		    ps.setReasonFailure(apiResult.getError());
		    ps.setDesc1(apiResult.getCode());
		} else {
		    ps.setPushStatus(Constant.THREE_STRING);
		    ps.setReasonFailure("FC-DC客商APP自制一单一车订单作废推单失败，连接超时。");
		    ps.setDesc1("-1");
	        result.setErrorCode(ErrorCode.APP_BILL_RETURN_ERROR);
		}
		pushSingleService.savePushSingle(ps);
		return result;
	}

	@Transactional
	@Override
	public AppResult saveNotice(NoticeSave param) throws Exception {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getUserId())
				&& StringUtils.isNotBlank(param.getIDType())
				&& StringUtils.isNotBlank(param.getId())
				&& StringUtils.isNotBlank(param.getDetailId())
				&& StringUtils.isNotBlank(param.getVehicle())
				&& param.getNumber() != null) {
			result = saveCustomerNotice(param);
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}
	private AppResult saveCustomerNotice(NoticeSave param) throws Exception {
		AppResult result = AppResult.getAppResult();
		SystemUser user = userMapper.selectByPrimaryKey(param.getUserId());
		if (user != null) {
			if (param.getNumber() > 0) {
				SalesApplication sa = salesApplicationMapper.selectByPrimaryKey(param.getId());
				SalesApplicationDetail sad = salesApplicationDetailMapper.selectByPrimaryKey(param.getDetailId());
				if (sa != null && sad != null) {
					if (StringUtils.equals(sa.getValidStatus(), Constant.ZERO_STRING)) {
						if (StringUtils.equals(sa.getCustomerid(), param.getNcId())) {
							if (StringUtils.equals(sa.getBilltypeid(), BillTypeEnum.BILL_TYPE_MORE_CAR.getCode())) {
								if (param.getNumber() <= sad.getMargin()) {
									synchronized (this) {
										VehicleManage vehicle = vehicleManageMapper.selectByPrimaryKey(param.getVehicle());
										if (validVehicle(vehicle, result)) {
											if (StringUtils.isNotBlank(param.getDriver())) {
												DriverManage driver = driverManageMapper.selectByPrimaryKey(param.getDriver());
												if (validDriver(driver, result)) {
													result = putCustomerNoticeValue(param, user, sa, sad, vehicle, driver);
												}
											} else {
												result = putCustomerNoticeValue(param, user, sa, sad, vehicle, null);
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
							result.setErrorCode(ErrorCode.APPLICATION_NOT_EXIST);
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
		return result;
	}

	private AppResult putCustomerNoticeValue(NoticeSave param, SystemUser user, SalesApplication sa,
			SalesApplicationDetail sad, VehicleManage vehicle, DriverManage driver) throws Exception {
		AppResult result = AppResult.getAppResult();
		SalesArrive bean = new SalesArrive();
		bean.setId(UUIDUtil.getId());
		bean.setCode(getCode("TH", user.getId(), true));
		bean.setAuditstatus(Constant.ZERO_STRING);
		bean.setSource(Constant.TWO_STRING);
		bean.setStatus(Constant.ZERO_STRING);
		bean.setVehicleid(vehicle.getId());
		bean.setVehicleno(vehicle.getVehicleno());
		bean.setVehiclerfid(vehicle.getRfid());
		if (driver != null) {
			bean.setDriverid(driver.getId());
			bean.setDrivername(driver.getName());
			bean.setDriveridentityno(driver.getIdentityno());
			saveUserDriver(user.getId(), driver.getId());
		}
		bean.setBillid(sa.getId());
		bean.setBillcode(sa.getCode());
		bean.setBilldetailid(sad.getId());
		bean.setUnit(sad.getUnit());
		bean.setTakeamount(param.getNumber());
		bean.setState(Constant.ONE_STRING);
		bean.setMakerid(user.getId());
		bean.setMakebillname(user.getName());
		bean.setMakebilltime(System.currentTimeMillis());
		bean.setCreator(user.getId());
		bean.setCreatetime(System.currentTimeMillis());
		salesArriveMapper.insertSelective(bean);
		SalesApplicationArrive join = new SalesApplicationArrive();
		join.setId(UUIDUtil.getId());
		join.setBillId(sa.getId());
		join.setBillDetailId(sad.getId());
		join.setNoticeId(bean.getId());
		join.setNumber(param.getNumber());
		join.setSequence(1);
		salesApplicationArriveMapper.insertSelective(join);
		sad.setMargin(sad.getMargin() - param.getNumber());
		sad.setPretendingtake(sad.getPretendingtake() + param.getNumber());
		salesApplicationDetailMapper.updateByPrimaryKeySelective(sad);
		updateCode("TH", param.getUserId());
		saveUserVehicle(user.getId(), vehicle.getId());
		result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		return result;
	}

	//熟车记录更新次数
	private void saveUserVehicle(String userId, String vehicleId) {
		UserVehicle uv = userVehicleMapper.getByUIdAndVId(userId, vehicleId);
		if (uv != null) {
			uv.setNumber(uv.getNumber() + 1);
			userVehicleMapper.updateByPrimaryKeySelective(uv);
		} else {
			uv = new UserVehicle();
			uv.setId(UUIDUtil.getId());
			uv.setNumber(1);
			uv.setUserId(userId);
			uv.setVehicleId(vehicleId);
			userVehicleMapper.insertSelective(uv);
		}
	}
	
	//熟司机记录更新次数
	private void saveUserDriver(String userId, String driverId) {
		UserDriver ud = userDriverMapper.getByUIdAndDId(userId, driverId);
		if (ud != null) {
			ud.setNumber(ud.getNumber() + 1);
			userDriverMapper.updateByPrimaryKeySelective(ud);
		} else {
			ud = new UserDriver();
			ud.setId(UUIDUtil.getId());
			ud.setNumber(1);
			ud.setUserId(userId);
			ud.setDriverId(driverId);
			userDriverMapper.insertSelective(ud);
		}
	}

	private boolean validVehicle(VehicleManage vehicle, AppResult result) {
		boolean flag = false;
		if (vehicle != null && StringUtils.equals(vehicle.getState(), Constant.ONE_STRING)) {
			if (StringUtils.equals(vehicle.getIsvalid(), Constant.ONE_STRING)) {
				if (StringUtils.equals(vehicle.getIsblacklist(), Constant.ZERO_STRING)) {
					PurchaseArrive pa = new PurchaseArrive();
					pa.setVehicleid(vehicle.getId());
					List<PurchaseArrive> paList = purchaseArriveMapper.checkDriverAndVehicleIsUse(pa);
					if (CollectionUtils.isNotEmpty(paList)) {
		                result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
		                result.setMessage("此车辆己有到货通知单、待出厂后进行派车，现有车辆业务单据号为:"+paList.get(0).getCode()+"，如有疑问请与销售处联系！");
		            } else {
		            	SalesArrive sa = new SalesArrive();
		                sa.setVehicleid(vehicle.getId());
		                List<SalesArrive> saList = salesArriveMapper.checkDriverAndVehicleIsUse(sa);
		                if (CollectionUtils.isNotEmpty(saList)) {
		                    result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
		                    result.setMessage("此车辆己有提货通知单、待出厂后进行派车，现有车辆业务单据号为:"+saList.get(0).getCode()+"，如有疑问请与销售处联系！");
		                } else {
		                	OtherArrive oa = new OtherArrive();
		                    oa.setVehicleid(vehicle.getId());
		                    List<OtherArrive> oaList = otherArriveMapper.checkDriverAndVehicleAndIcardIsUse(oa);
		                    if (CollectionUtils.isNotEmpty(oaList)) {
		                        if(StringUtils.equals(oaList.get(0).getBusinesstype(), Constant.FIVE_STRING)){
		                            result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
		                            result.setMessage("此车辆己有其他入库通知单、待出厂后进行派车，现有车辆业务单据号为:"+oaList.get(0).getCode()+"，如有疑问请与销售处联系！");
		                        }else if(StringUtils.equals(oaList.get(0).getBusinesstype(), Constant.SEVEN_STRING)){
		                            result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
		                            result.setMessage("此车辆己有其他出库通知单、待出厂后进行派车，现有车辆业务单据号为:"+oaList.get(0).getCode()+"，如有疑问请与销售处联系！");
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
	
	private boolean validDriver(DriverManage driver, AppResult result) {
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

	@Override
	public AppResult listMoreBill(BillListParam param) {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getUserId())
				&& StringUtils.isNotBlank(param.getNcId())
				&& StringUtils.isNotBlank(param.getSalesOrg())
				&& StringUtils.isNotBlank(param.getDetailId())) {
			SalesApplicationDetail sad = salesApplicationDetailMapper.selectByPrimaryKey(param.getDetailId());
			param.setMaterial(sad.getMaterielid());
			List<BillListVo> list = salesApplicationMapper.appMoreBillList(param);
			result.setData(list);
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	@Transactional
	@Override
	public AppResult moreSendCar(NoticeSave param) throws Exception {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getUserId())
				&& StringUtils.isNotBlank(param.getNcId())
				&& StringUtils.isNotBlank(param.getSalesOrg())
				&& StringUtils.isNotBlank(param.getIDType())
				&& StringUtils.isNotBlank(param.getIds())
				&& StringUtils.isNotBlank(param.getVehicle())
				&& param.getNumber() != null) {
			result = customerMoreSendCar(param);
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	private synchronized AppResult customerMoreSendCar(NoticeSave param) throws Exception {
		AppResult result = AppResult.getAppResult();
		SystemUser user = userMapper.selectByPrimaryKey(param.getUserId());
		if (user != null) {
			if (param.getNumber() > 0) {
				JSONArray ids = JSON.parseArray(param.getIds());
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
					if (i < ids.size() - 1) {
						//判断预提量是否满足多单合并规则（预提量必须满足多单的余量都有扣减行为,且除最后一个扣减单外，其余的余量剩余0）
						if (param.getNumber() <= sumMargin) {
							result.setErrorCode(ErrorCode.NOTICE_SEND_CAR_ERROR);
							return result;
						}
					}
					List<Object> item = new ArrayList<Object>();
					item.add(sa);
					item.add(sad);
					list.add(item);
				}
				if (param.getNumber() <= sumMargin) {
					//对余量进行排序，升序
					Collections.sort(list, new Comparator<List<Object>>() {
						@Override
						public int compare(List<Object> o1, List<Object> o2) {
							int index = 0;
							SalesApplicationDetail s1 = (SalesApplicationDetail) o1.get(1);
							SalesApplicationDetail s2 = (SalesApplicationDetail) o2.get(1);
							double margin1 = s1.getMargin();
							double margin2 = s2.getMargin();
							if (margin1 > margin2) {
								index = 1;
							} else {
								index = -1;
							}
							return index;
						}
					});
					//以最后一个订单为主参考订单
					SalesApplication refBill = (SalesApplication) list.get(list.size() - 1).get(0);
					SalesApplicationDetail refBillDetail = (SalesApplicationDetail) list.get(list.size() - 1).get(1);
					VehicleManage vehicle = vehicleManageMapper.selectByPrimaryKey(param.getVehicle());
					if (validVehicle(vehicle, result)) {
						if (StringUtils.isNotBlank(param.getDriver())) {
							DriverManage driver = driverManageMapper.selectByPrimaryKey(param.getDriver());
							if (validDriver(driver, result)) {
								result = putCustomerMoreSendCarNoticeValue(param, user, list, refBill, refBillDetail, vehicle, driver);
							}
						} else {
							result = putCustomerMoreSendCarNoticeValue(param, user, list, refBill, refBillDetail, vehicle, null);
						}
					}
				} else {
					result.setErrorCode(ErrorCode.NOTICE_NUMBER_ERROR);
				}
			} else {
				result.setErrorCode(ErrorCode.NOTICE_NUMBER_ERROR);
			}
		} else {
			result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR17);
		}
		return result;
	}
	
	//多单合并派车
	private AppResult putCustomerMoreSendCarNoticeValue(NoticeSave param, SystemUser user, List<List<Object>> list, SalesApplication refBill,
			SalesApplicationDetail refBillDetail, VehicleManage vehicle, DriverManage driver) throws Exception {
		AppResult result = AppResult.getAppResult();
		SalesArrive bean = new SalesArrive();
		bean.setId(UUIDUtil.getId());
		bean.setCode(getCode("TH", param.getUserId(), true));
		bean.setAuditstatus(Constant.ZERO_STRING);
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
		bean.setBillid(refBill.getId());
		bean.setBillcode(refBill.getCode());
		bean.setBilldetailid(refBillDetail.getId());
		bean.setUnit(refBillDetail.getUnit());
		bean.setTakeamount(param.getNumber());
		bean.setState(Constant.ONE_STRING);
		bean.setMaindeduction(Constant.ZERO_STRING);
		bean.setMakerid(user.getId());
		bean.setMakebillname(user.getName());
		bean.setMakebilltime(System.currentTimeMillis());
		bean.setCreator(user.getId());
		bean.setCreatetime(System.currentTimeMillis());
		bean.setForceOutFactory(Constant.ZERO_NUMBER);
		bean.setValidStatus(Constant.ZERO_STRING);
		salesArriveMapper.insertSelective(bean);
		updateCode("TH", user.getId());
		// TODO
		//循环list 记录绑定关系和减扣量，并回写子表 量。
		double number = param.getNumber();
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
		result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		return result;
	}

	@Override
	public AppResult noticeList(NoticeListParam param) {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getUserId())
				&& StringUtils.isNotBlank(param.getNcId())
				&& StringUtils.isNotBlank(param.getSalesOrg())
				&& StringUtils.isNotBlank(param.getIDType())
				&& StringUtils.isNotBlank(param.getType())) {
			PaginationVO<NoticeListVo> page = new PaginationVO<NoticeListVo>();
			long count = salesArriveMapper.appNoticeListCount(param);
			if (count > 0) {
				param.setStart((param.getPageNo() - 1) * param.getPageSize());
				param.setLimit(param.getPageSize());
				List<NoticeListVo> list = salesArriveMapper.appNoticeList(param);
				page.setList(list);
			}
			page.setTotal(count);
			page.setPageNo(param.getPageNo());
			page.setPageSize(param.getPageSize());
			result.setData(page);
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	@Override
	public AppResult noticeDetail(NoticeListParam param) {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getId())
				&& StringUtils.isNotBlank(param.getIDType())) {
			result.setData(salesArriveMapper.appNoticeDetail(param));
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	@Transactional
	@Override
	public AppResult noticeUpdate(NoticeSave param) throws Exception {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getUserId())
				&& StringUtils.isNotBlank(param.getIDType())
				&& (StringUtils.isNotBlank(param.getId()))) {
			if (StringUtils.isNotBlank(param.getId())
					|| StringUtils.isNotBlank(param.getVehicle())
					|| StringUtils.isNotBlank(param.getDriver())
					|| param.getNumber() != null) {
				result = customerNoticeUpdate(param);
			} else {
				result.setErrorCode(ErrorCode.DATE_NOT_UPDATE);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	private synchronized AppResult customerNoticeUpdate(NoticeSave param) {
		AppResult result = AppResult.getAppResult();
		SystemUser user = userMapper.selectByPrimaryKey(param.getUserId());
		if (user != null) {
			SalesArrive notice = salesArriveMapper.selectByPrimaryKey(param.getId());
			if (notice != null && StringUtils.equals(notice.getState(), Constant.ONE_STRING)) {
				if (!StringUtils.equals(notice.getStatus(), Constant.THREE_STRING)) {
					if (StringUtils.equals(notice.getAuditstatus(), Constant.ZERO_STRING)) {
						boolean flag = false;
						//判断是否修改到货量
						SalesArrive bean = new SalesArrive();
						if (param.getNumber() != null) {
							if (param.getNumber() > 0) {
								// TODO
								double number = param.getNumber();
								//最后一个订单子表（余量最多）
								SalesApplicationDetail lastDetail = null;
								//最后一个关系扣减量
								SalesApplicationArrive lastJoin = null; 
								//最后一个订单（余量最多）的扣减量
								double lastNumber = 0D;
								List<SalesApplicationArrive> list = salesApplicationArriveMapper.listByNoticeId(notice.getId());
								for (int i = 0; i < list.size(); i++) {
									SalesApplicationArrive join = list.get(i);
									if (i < list.size() - 1) {
										if (number <= join.getNumber()) {
											result.setErrorCode(ErrorCode.NOTICE_SEND_CAR_ERROR);
											return result;
										}
										number -= join.getNumber();
									} else {
										lastJoin = join;
										lastDetail = salesApplicationDetailMapper.selectByPrimaryKey(join.getBillDetailId());
										lastNumber = join.getNumber();
									}
								}
								if (number <= lastNumber + lastDetail.getMargin()) {
									//由于限制多单派车根据余量降序，除去最后一个订单（余量最多）外，其他订单余量都必须扣减到0，
									//所以修改提货量只能修改到最后一个订单（余量最多）的余量
									bean.setTakeamount(param.getNumber());
									lastDetail.setMargin(lastDetail.getMargin() + lastNumber - number);
									lastDetail.setPretendingtake(lastDetail.getPretendingtake() -lastNumber + number );
									salesApplicationDetailMapper.updateByPrimaryKeySelective(lastDetail);
									lastJoin.setNumber(number);
									salesApplicationArriveMapper.updateByPrimaryKeySelective(lastJoin);
									flag = true;
								} else {
									result.setErrorCode(ErrorCode.NOTICE_NUMBER_ERROR);
								}
							} else {
								result.setErrorCode(ErrorCode.NOTICE_NUMBER_ERROR);
							}
						}
						//判断是否修改车辆
						if (StringUtils.isNotBlank(param.getVehicle()) && !StringUtils.equals(notice.getVehicleid(), param.getVehicle())) {
							VehicleManage vehicle = vehicleManageMapper.selectByPrimaryKey(param.getVehicle());
							if (validVehicle(vehicle, result)) {
								bean.setVehicleid(vehicle.getId());
								bean.setVehicleno(vehicle.getVehicleno());
								bean.setVehiclerfid(vehicle.getRfid());
								saveUserVehicle(user.getId(), vehicle.getId());
								flag = true;
							}
						}
						//判断是否修改司机
						if (StringUtils.isNotBlank(param.getDriver()) && !StringUtils.equals(notice.getDriverid(), param.getDriver())) {
							DriverManage driver = driverManageMapper.selectByPrimaryKey(param.getDriver());
							if (validDriver(driver, result)) {
								bean.setDriverid(driver.getId());
								bean.setDrivername(driver.getName());
								bean.setDriveridentityno(driver.getIdentityno());
								saveUserVehicle(user.getId(), driver.getId());
								flag = true;
							}
						}
						if (flag) {
							bean.setId(notice.getId());
							bean.setModifier(param.getUserId());
							bean.setModifytime(System.currentTimeMillis());
							salesArriveMapper.updateByPrimaryKeySelective(bean);
							result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
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
			result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR17);
		}
		return result;
	}

	@Transactional
	@Override
	public AppResult noticeCancel(NoticeListParam param) throws Exception {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getUserId())
				&& StringUtils.isNotBlank(param.getIDType())
				&& StringUtils.isNotBlank(param.getId())) {
			result = customerNoticeCancel(param);
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}
	
	private AppResult customerNoticeCancel(NoticeListParam param) throws Exception {
		AppResult result = AppResult.getAppResult();
		SystemUser user = systemUserMapper.selectByPrimaryKey(param.getUserId());
		if (user != null) {
			//判断是否为有效通知单
			SalesArrive sa = salesArriveMapper.selectByPrimaryKey(param.getId());
			if (sa != null && StringUtils.equals(sa.getState(), Constant.ONE_STRING)) {
				if (!StringUtils.equals(sa.getStatus(), Constant.THREE_STRING)) {
					//未入厂的才允许作废
					if (StringUtils.equals(sa.getStatus(), Constant.ZERO_STRING)) {
						//判断订单的订单类型
						//一单一车 修改通知单为作废中，并推送给DC， 等待DC回写（非实时）（回写内容：修改通知单为作废，修改订单为作废），订单不做变化
						//一单多车 修改通知单为作废，并回写订单 量 数据
						SalesApplication bill = salesApplicationMapper.selectByPrimaryKey(sa.getBillid());
						if (StringUtils.equals(bill.getBilltypeid(), Constant.ZERO_STRING)) {
							//一单一车
							result = oneBillOneCarCancel(user, sa);
						} else {
							//一单多车
							result = oneBillMoreCarCancel(user, sa);
						}
					} else {
						result.setErrorCode(ErrorCode.NOTICE_DONT_VALID_ERROR);
					}
				} else {
					result.setErrorCode(ErrorCode.NOTICE_ON_INVALID);
				}
			} else {
				result.setErrorCode(ErrorCode.NOTICE_NOT_EXIST);
			}
		} else {
			result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR17);
		}
		return result;
	}
	/**
	 * @annotation 一单一车作废
	 * @param param
	 * @param user
	 * @param sa
	 * @return
	 * @throws Exception 
	 */
	private AppResult oneBillOneCarCancel(SystemUser user, SalesArrive sa) throws Exception {
		AppResult result = AppResult.getAppResult();
		// 修改通知单为作废中，并推送给DC， 等待DC回写（非实时）（回写内容：修改通知单为作废，修改订单为作废），订单不做变化
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", sa.getBillid());
		map.put("detailId", sa.getBilldetailid());
		map.put("type", Constant.ONE_STRING);
		//上传dc，申请作废，状态改为作废中
		//由dc回写作废状态
		//记录推送日志
		PushSingleReq ps = new PushSingleReq();
		ps.setId(UUIDUtil.getId());
		ps.setRequisitionNum(sa.getBillcode());
		ps.setRequisitionType(Constant.FOUR_STRING);
		ps.setCreator(user.getId());
		ps.setCreatetime(System.currentTimeMillis());
		ps.setModifytime(System.currentTimeMillis());
		ApiResult apiResult = HttpUtils.post(ApiParamUtils.getApiParam(map), Constant.URL_DOMAIN + Constant.URL_SALESAPPLICATION_DELETE);
		if (apiResult != null) {
			if (StringUtils.equals(apiResult.getCode(), Constant.SUCCESS)) {
				//修改通知单为作废中
				sa.setStatus(Constant.THREE_STRING);
				sa.setValidStatus(Constant.ONE_STRING);
				sa.setAbnormalperson(user.getId());
				sa.setAbnormalpersonname(user.getName());
				sa.setAbnormaltime(System.currentTimeMillis());
				salesArriveMapper.updateByPrimaryKeySelective(sa);
		        ps.setPushStatus(Constant.ONE_STRING);
		        result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		    } else {
		        ps.setPushStatus(Constant.THREE_STRING);
		        result.setErrorCode(ErrorCode.APP_BILL_RETURN_ERROR);
		        result.setMessage(apiResult.getError());
		    }
		    ps.setReasonFailure(apiResult.getError());
		    ps.setDesc1(apiResult.getCode());
		} else {
		    ps.setPushStatus(Constant.THREE_STRING);
		    ps.setReasonFailure("FC-DC客商APP自制一单一车通知单作废推单失败，连接超时。");
		    ps.setDesc1("-1");
	        result.setErrorCode(ErrorCode.APP_BILL_RETURN_ERROR);
		}
		pushSingleService.savePushSingle(ps);
		return result;
	}
	/**
	 * @annotation 一单多车作废
	 * @param param
	 * @param user
	 * @param sa
	 * @return
	 */
	private AppResult oneBillMoreCarCancel(SystemUser user, SalesArrive sa) {
		//一单多车 修改通知单为作废，并回写订单 量 数据
		AppResult result = AppResult.getAppResult();
		sa.setStatus(Constant.THREE_STRING);
		sa.setValidStatus(Constant.TWO_STRING);
		sa.setAbnormalperson(user.getId());
		sa.setAbnormalpersonname(user.getName());
		sa.setAbnormaltime(System.currentTimeMillis());
		salesArriveMapper.updateByPrimaryKeySelective(sa);
		List<SalesApplicationArrive> list = salesApplicationArriveMapper.listByNoticeId(sa.getId());
		for (SalesApplicationArrive join : list) {
			SalesApplicationDetail sad = salesApplicationDetailMapper.selectByPrimaryKey(join.getBillDetailId());
			sad.setMargin(sad.getMargin() + join.getNumber());
			sad.setPretendingtake(sad.getPretendingtake() - join.getNumber());
			salesApplicationDetailMapper.updateByPrimaryKeySelective(sad);
		}
		result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		return result;
	}

	@Override
	public AppResult myVehicle(MyVehicleListParam param) {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getIDType())
				&& StringUtils.isNotBlank(param.getNcId())
				&& StringUtils.isNotBlank(param.getType())) {
			switch (param.getType()) {
			case "2":
				//熟车
				result = myVehicleInSc(param);
				break;
			default:
				result = myVehicleInFc(param);
				break;
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}
	//熟车
	private AppResult myVehicleInSc(MyVehicleListParam param) {
		AppResult result = AppResult.getAppResult();
		PaginationVO<MyVehicleListVo> page = new PaginationVO<MyVehicleListVo>();
		long list1count = userVehicleMapper.listMyVehicleOrderNumCount(param);
		long list2count = userDriverMapper.listMyDriverOrderNumCount(param);
		long count = list1count;
		if (list2count > list1count) {
			count = list2count;
		}
		if (count > 0) {
			param.setStart((param.getPageNo() - 1) * param.getPageSize());
			param.setLimit(param.getPageSize());
			List<UserVehicleVo> list1 = userVehicleMapper.listMyVehicleOrderNum(param);
			List<UserDriverVo> list2 = userDriverMapper.listMyDriverOrderNum(param);
			int index = 0;
			if (CollectionUtils.isNotEmpty(list1)) {
				index = list1.size();
			}
			if (CollectionUtils.isNotEmpty(list2) && list2.size() > index) {
				index = list2.size();
			}
			List<MyVehicleListVo> list = new ArrayList<MyVehicleListVo>();
			for (int i = 0; i < index; i++) {
				MyVehicleListVo vo = new MyVehicleListVo();
				if (i < list1.size()) {
					vo.setVehicle(list1.get(i).getVehicleNo());
				}
				if (i < list2.size()) {
					vo.setDriver(list2.get(i).getName());
					vo.setTelephone(list2.get(i).getTelephone());
				}
				list.add(vo);
			}
			page.setList(list);
		}
		page.setTotal(count);
		page.setPageNo(param.getPageNo());
		page.setPageSize(param.getPageSize());
		result.setData(page);
		result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		return result;
	}
	//在运输车辆
	private AppResult myVehicleInFc(MyVehicleListParam param) {
		AppResult result = AppResult.getAppResult();
		PaginationVO<MyVehicleListVo> page = new PaginationVO<MyVehicleListVo>();
		long count = salesArriveMapper.appMyVehicleListCount(param);
		if (count > 0) {
			param.setStart((param.getPageNo() - 1) * param.getPageSize());
			param.setLimit(param.getPageSize());
			List<MyVehicleListVo> list = salesArriveMapper.appMyVehicleList(param);
			page.setList(list);
		}
		page.setTotal(count);
		page.setPageNo(param.getPageNo());
		page.setPageSize(param.getPageSize());
		result.setData(page);
		result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		return result;
	}

	@Override
	public AppResult myPn(MyPnListParam param) {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getNcId())
				&& StringUtils.isNotBlank(param.getIDType())) {
			PaginationVO<MyPnListVo> page = new PaginationVO<MyPnListVo>();
			long count = poundNoteMapper.appCusPnListCount(param);
			if (count > 0) {
				param.setStart((param.getPageNo() - 1) * param.getPageSize());
				param.setLimit(param.getPageSize());
				List<MyPnListVo> list = poundNoteMapper.appCusPnList(param);
				page.setList(list);
			}
			page.setTotal(count);
			page.setPageNo(param.getPageNo());
			page.setPageSize(param.getPageSize());
			result.setData(page);
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			return result;
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	@Override
	public AppResult myPnDetail(MyPnListParam param) {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getId())) {
			result.setData(poundNoteMapper.appCusPnetail(param));
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	@Override
	public AppResult customerGroupUser(LoginUserParam param) {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getNcId())
				&& StringUtils.isNotBlank(param.getIDType())) {
			CustomerGroup customerGroup = customerGroupMapper.validateCustomer(param.getNcId());
			if(customerGroup != null){
				List<AppCutoverGroup> list = customerGroupMapper.selectCustomerByGroupId(customerGroup.getGroupid());
				result.setData(list);
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.SUPPLIER_GROUP_ERROR1);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	/**
	 * 查询仓库id和名称
	 */
	@Override
	public AppResult queryWarehouse(SearchKeyParam param) {
		AppResult result = AppResult.getAppResult();
		if (param != null) {
			result.setData(warehouseManageMapper.queryWarehouse(param));
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

}
