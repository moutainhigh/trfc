package com.tianrui.service.api.android.imple;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.api.android.imple.IAppSalesStaticService;
import com.tianrui.api.intf.system.auth.ISystemUserService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.android.BillListParam;
import com.tianrui.api.req.android.BillSave;
import com.tianrui.api.req.android.HomePageParam;
import com.tianrui.api.req.android.LoginUserParam;
import com.tianrui.api.req.android.MyPnListParam;
import com.tianrui.api.req.android.MyVehicleListParam;
import com.tianrui.api.req.android.NoticeListParam;
import com.tianrui.api.req.android.NoticeSave;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.android.BillListVo;
import com.tianrui.api.resp.android.HomeBillVo;
import com.tianrui.api.resp.android.HomeNoticeVo;
import com.tianrui.api.resp.android.LoginUserVo;
import com.tianrui.api.resp.android.MyPnListVo;
import com.tianrui.api.resp.android.MyVehicleListVo;
import com.tianrui.api.resp.android.NoticeListVo;
import com.tianrui.api.resp.android.UserDriverVo;
import com.tianrui.api.resp.android.UserVehicleVo;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.api.resp.system.merchants.AppCutoverGroup;
import com.tianrui.service.bean.basicFile.measure.DriverManage;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;
import com.tianrui.service.bean.basicFile.nc.CustomerManage;
import com.tianrui.service.bean.basicFile.nc.MaterielManage;
import com.tianrui.service.bean.businessManage.otherManage.OtherArrive;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseArrive;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplication;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationDetail;
import com.tianrui.service.bean.businessManage.salesManage.SalesArrive;
import com.tianrui.service.bean.common.ReturnQueue;
import com.tianrui.service.bean.common.UserDriver;
import com.tianrui.service.bean.common.UserVehicle;
import com.tianrui.service.bean.system.auth.Organization;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.mapper.basicFile.measure.DriverManageMapper;
import com.tianrui.service.mapper.basicFile.measure.VehicleManageMapper;
import com.tianrui.service.mapper.basicFile.nc.CustomerManageMapper;
import com.tianrui.service.mapper.basicFile.nc.MaterielManageMapper;
import com.tianrui.service.mapper.businessManage.otherManage.OtherArriveMapper;
import com.tianrui.service.mapper.businessManage.poundNoteMaintain.PoundNoteMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseApplicationDetailMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseApplicationMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseArriveMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationDetailMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesArriveMapper;
import com.tianrui.service.mapper.common.AppVersionMapper;
import com.tianrui.service.mapper.common.ReturnQueueMapper;
import com.tianrui.service.mapper.common.UserDriverMapper;
import com.tianrui.service.mapper.common.UserVehicleMapper;
import com.tianrui.service.mapper.system.auth.OrganizationMapper;
import com.tianrui.service.mapper.system.auth.SystemUserMapper;
import com.tianrui.service.mapper.system.merchants.SupplierGroupMapper;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.AppResult;
import com.tianrui.smartfactory.common.vo.PaginationVO;

@Service
public class AppSalesStaticService implements IAppSalesStaticService {

	@Autowired
	private SystemUserMapper userMapper;
	@Autowired
	private CacheClient cacheClient;
	@Autowired
	private ISystemUserService systemUserService;
	@Autowired
	private SalesApplicationMapper salesApplicationMapper;
	@Autowired
	private SalesApplicationDetailMapper salesApplicationDetailMapper;
	@Autowired
	private SalesArriveMapper salesArriveMapper;
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
	private PurchaseApplicationMapper purchaseApplicationMapper;
	@Autowired
	private PurchaseApplicationDetailMapper purchaseApplicationDetailMapper;
	@Autowired
	private PurchaseArriveMapper purchaseArriveMapper;
	@Autowired
	private UserVehicleMapper userVehicleMapper;
	@Autowired
	private UserDriverMapper userDriverMapper;
	@Autowired
	private PoundNoteMapper poundNoteMapper;
	@Autowired
	private OtherArriveMapper otherArriveMapper;
	@Autowired
	private SupplierGroupMapper supplierGroupMapper;
	@Autowired
	private AppVersionMapper appVersionMapper;
	
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
			SystemUserResp user = systemUserService.getUser(param.getUserId());
			if (user != null) {
				SalesApplication sa = setSalesApplication(param, user);
				SalesApplicationDetail sad = setSalesApplicationDetail(param, sa.getId());
				salesApplicationMapper.insertSelective(sa);
				salesApplicationDetailMapper.insertSelective(sad);
				updateCode("XXSO", param.getUserId());
				//自制订单列队
				ReturnQueue returnQueue = new ReturnQueue();
				returnQueue.setId(UUIDUtil.getId());
				returnQueue.setDataid(sa.getId());
				returnQueue.setDatatype(Constant.ZERO_STRING);
				returnQueue.setCreator(sa.getMakerid());
				returnQueue.setCreatetime(System.currentTimeMillis());
				returnQueueMapper.insertSelective(returnQueue);
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			} else {
				result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR1);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	private SalesApplication setSalesApplication(BillSave param, SystemUserResp user) throws Exception {
		SalesApplication sa = new SalesApplication();
		sa.setId(UUIDUtil.getId());
		sa.setCode(getCode("XXSO", param.getUserId(), true));
		sa.setStatus(Constant.ZERO_STRING);
		sa.setSource(Constant.ONE_STRING);
		sa.setBilltypeid(Constant.ONE_STRING);
		sa.setBilltypename("一单一车");
		sa.setCustomerid(param.getUserId());
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
		sa.setVehicleId(param.getVehicle());
		VehicleManage vehicle = vehicleManageMapper.selectByPrimaryKey(param.getVehicle());
		if (vehicle != null) {
			sa.setVehicleNo(vehicle.getVehicleno());
			sa.setRfid(vehicle.getRfid());
		}
		sa.setDriverId(param.getDriver());
		DriverManage driver = driverManageMapper.selectByPrimaryKey(param.getDriver());
		if (driver != null) {
			sa.setDriverName(driver.getName());
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

	@Override
	public AppResult billDelete(BillListParam param) {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getUserId())
				&& StringUtils.isNotBlank(param.getId())) {
			SalesApplication sa = salesApplicationMapper.selectByPrimaryKey(param.getId());
			if (sa != null) {
				if (StringUtils.equals(sa.getCustomerid(), param.getNcId())) {
					if (StringUtils.equals(sa.getStatus(), Constant.ZERO_STRING)) {
						if (StringUtils.equals(sa.getSource(), Constant.ONE_STRING)) {
							
							//上传dc，申请作废，状态改为作废中
							//由dc回写作废状态
							
							/*
							SalesApplication bean = new SalesApplication();
							bean.setId(sa.getId());
							bean.setState(Constant.ZERO_STRING);
							salesApplicationMapper.updateByPrimaryKeySelective(bean);
							result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
							
							*/
							result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
							
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

	@Override
	public AppResult saveNotice(NoticeSave param) throws Exception {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getUserId())
				&& StringUtils.isNotBlank(param.getIDType())
				&& StringUtils.isNotBlank(param.getId())
				&& StringUtils.isNotBlank(param.getDetailId())
				&& StringUtils.isNotBlank(param.getVehicle())
				&& param.getNumber() != null) {
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
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
	public AppResult noticeList(NoticeListParam param) {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getUserId())
				&& StringUtils.isNotBlank(param.getIDType())
				&& StringUtils.isNotBlank(param.getType())) {
			PaginationVO<NoticeListVo> page = new PaginationVO<NoticeListVo>();
			List<NoticeListVo> list = new ArrayList<>();
			NoticeListVo vo = new NoticeListVo();
			list.add(vo);
			page.setList(list);
			page.setTotal(1);
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
			NoticeListVo vo = new NoticeListVo();
			result.setData(vo);
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

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
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			} else {
				result.setErrorCode(ErrorCode.DATE_NOT_UPDATE);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

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
		
		result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		return result;
	}

	@Override
	public AppResult myPn(MyPnListParam param) {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getNcId())
				&& StringUtils.isNotBlank(param.getIDType())) {
			PaginationVO<MyPnListVo> page = new PaginationVO<MyPnListVo>();
			List<MyPnListVo> list = new ArrayList<>();
			MyPnListVo vo = new MyPnListVo();
			list.add(vo);
			page.setList(list);
			page.setTotal(1);
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
	public AppResult myPnDetail(MyPnListParam param) {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getId())) {
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	@Override
	public AppResult queryGroupUser(LoginUserParam param) {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getNcId())
				&& StringUtils.isNotBlank(param.getIDType())) {
			List<AppCutoverGroup> list = new ArrayList<>();
			AppCutoverGroup a = new AppCutoverGroup();
			list.add(a);
			result.setData(list);
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	@Override
	public AppResult listMoreBill(BillListParam param) {
		AppResult result = AppResult.getAppResult();
		
		result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		return result;
	}

	@Override
	public AppResult moreSendCar(NoticeSave body) {
		AppResult result = AppResult.getAppResult();
		
		result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		return result;
	}

	@Override
	public AppResult userCutover(LoginUserParam body) {
		AppResult result = AppResult.getAppResult();
		result.setData(new LoginUserVo());
		result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		return result;
	}

}
