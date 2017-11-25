package com.tianrui.service.api.android.imple;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.tianrui.api.resp.system.merchants.AppCutoverGroup;
import com.tianrui.service.bean.basicFile.measure.DriverManage;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;
import com.tianrui.service.bean.basicFile.nc.CustomerManage;
import com.tianrui.service.bean.basicFile.nc.MaterielManage;
import com.tianrui.service.bean.businessManage.otherManage.OtherArrive;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplication;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplicationDetail;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseArrive;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplication;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationArrive;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationDetail;
import com.tianrui.service.bean.businessManage.salesManage.SalesArrive;
import com.tianrui.service.bean.common.ReturnQueue;
import com.tianrui.service.bean.common.UserDriver;
import com.tianrui.service.bean.common.UserVehicle;
import com.tianrui.service.bean.system.auth.Organization;
import com.tianrui.service.bean.system.auth.SystemUser;
import com.tianrui.service.bean.system.merchants.CustomerGroup;
import com.tianrui.service.mapper.basicFile.measure.DriverManageMapper;
import com.tianrui.service.mapper.basicFile.measure.VehicleManageMapper;
import com.tianrui.service.mapper.basicFile.nc.CustomerManageMapper;
import com.tianrui.service.mapper.basicFile.nc.MaterielManageMapper;
import com.tianrui.service.mapper.businessManage.otherManage.OtherArriveMapper;
import com.tianrui.service.mapper.businessManage.poundNoteMaintain.PoundNoteMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseArriveMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationArriveMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationDetailMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesArriveMapper;
import com.tianrui.service.mapper.common.ReturnQueueMapper;
import com.tianrui.service.mapper.common.UserDriverMapper;
import com.tianrui.service.mapper.common.UserVehicleMapper;
import com.tianrui.service.mapper.system.auth.OrganizationMapper;
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
	private ReturnQueueMapper returnQueueMapper;
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
				SalesApplication sa = setSalesApplication(param, user);
				SalesApplicationDetail sad = setSalesApplicationDetail(param, sa.getId());
				salesApplicationMapper.insertSelective(sa);
				salesApplicationDetailMapper.insertSelective(sad);
				updateCode("XXSO", param.getUserId());
				//自制订单列队
				ReturnQueue queue = new ReturnQueue();
				queue.setId(UUIDUtil.getId());
				queue.setDataid(sa.getId());
				queue.setDatatype(Constant.ZERO_STRING);
				queue.setCreator(sa.getMakerid());
				queue.setCreatetime(System.currentTimeMillis());
				returnQueueMapper.insertSelective(queue);
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			} else {
				result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR1);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	private SalesApplication setSalesApplication(BillSave param, SystemUser user) throws Exception {
		SalesApplication sa = new SalesApplication();
		sa.setId(UUIDUtil.getId());
		sa.setCode(getCode("XXSO", param.getUserId(), true));
		sa.setStatus(Constant.ZERO_STRING);
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
		if (StringUtils.isNotBlank(param.getVehicle())) {
			sa.setVehicleId(param.getVehicle());
			VehicleManage vehicle = vehicleManageMapper.selectByPrimaryKey(param.getVehicle());
			if (vehicle != null) {
				sa.setVehicleNo(vehicle.getVehicleno());
				sa.setRfid(vehicle.getRfid());
			}
		}
		if (StringUtils.isNotBlank(param.getDriver())) {
			sa.setDriverId(param.getDriver());
			DriverManage driver = driverManageMapper.selectByPrimaryKey(param.getDriver());
			if (driver != null) {
				sa.setDriverName(driver.getName());
			}
		}
		sa.setBillSource(Constant.TWO_NUMBER);
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
		sad.setMargin(param.getNumber());
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
				List<SalesApplicationDetail> list = salesApplicationDetailMapper.selectBySalesId(sa.getId());
			if (sa != null && CollectionUtils.isNotEmpty(list)) {
				if (StringUtils.equals(sa.getCustomerid(), param.getNcId())) {
					if (StringUtils.equals(sa.getStatus(), Constant.ZERO_STRING)) {
						if (StringUtils.equals(sa.getSource(), Constant.ONE_STRING)) {
							Map<String, String> map = new HashMap<String, String>();
							map.put("id", sa.getId());
							map.put("detailId", list.get(0).getId());
							//上传dc，申请作废，状态改为作废中
							//由dc回写作废状态
							//记录推送日志
							result = pushDC(param, sa, map);
						} else {
							result.setErrorCode(ErrorCode.APPLICATION_NOT_DELETE2);
						}
					} else {
						result.setErrorCode(ErrorCode.APPLICATION_NOT_DELETE1);
					}
				} else {
					result.setErrorCode(ErrorCode.APPLICATION_NOT_EXIST);
				}
			} else {
				result.setErrorCode(ErrorCode.APPLICATION_NOT_EXIST);
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
		    ps.setReasonFailure("FC-DC客商APP自制一单一车推单失败，连接超时。");
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
				&& StringUtils.isNotBlank(param.getMaterial())) {
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
	public AppResult moreSendCar(NoticeSave param) {
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

	private AppResult customerMoreSendCar(NoticeSave param) {
		AppResult result = AppResult.getAppResult();
		SystemUser user = userMapper.selectByPrimaryKey(param.getUserId());
		if (user != null) {
			if (param.getNumber() > 0) {
				JSONArray ids = JSON.parseArray(param.getIds());
				List<List<Object>> list = new ArrayList<List<Object>>();
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
				//对余量进行排序，升序
				Collections.sort(list, new Comparator<List<Object>>() {
					@Override
					public int compare(List<Object> o1, List<Object> o2) {
						int index = 0;
						SalesApplicationDetail s1 = (SalesApplicationDetail) o1.get(1);
						SalesApplicationDetail s2 = (SalesApplicationDetail) o2.get(1);
						double margin1 = s1.getMargin();
						double margin2 = s2.getMargin();
						if (margin1 >= margin2) {
							index = 1;
						} else {
							index = -1;
						}
						return index;
					}
				});
				// TODO
				//循环list 记录绑定关系和减扣量，并回写子表 量。
				//以哪个订单为参考生成通知单，明天带讨论
				
			} else {
				result.setErrorCode(ErrorCode.NOTICE_NUMBER_ERROR);
			}
		} else {
			result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR17);
		}
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
				// TODO
				
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			} else {
				result.setErrorCode(ErrorCode.DATE_NOT_UPDATE);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	@Transactional
	@Override
	public AppResult noticeCancel(NoticeListParam param) {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getUserId())
				&& StringUtils.isNotBlank(param.getIDType())
				&& StringUtils.isNotBlank(param.getId())) {
			
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
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
			long count = poundNoteMapper.appPnCusListCount(param);
			if (count > 0) {
				param.setStart((param.getPageNo() - 1) * param.getPageSize());
				param.setLimit(param.getPageSize());
				List<MyPnListVo> list = poundNoteMapper.appPnCusList(param);
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
			result.setData(poundNoteMapper.appPnCusDetail(param));
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

}
