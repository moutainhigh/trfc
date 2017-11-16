package com.tianrui.service.api.android.imple;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.api.android.imple.IAppStaticService;
import com.tianrui.api.intf.system.auth.ISystemUserService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.android.BillListParam;
import com.tianrui.api.req.android.BillSave;
import com.tianrui.api.req.android.HomePageParam;
import com.tianrui.api.req.android.LoginUserParam;
import com.tianrui.api.req.android.NoticeListParam;
import com.tianrui.api.req.android.NoticeSave;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.android.BillListVo;
import com.tianrui.api.resp.android.HomeBillVo;
import com.tianrui.api.resp.android.HomeNoticeVo;
import com.tianrui.api.resp.android.LoginUserVo;
import com.tianrui.api.resp.android.NoticeListVo;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.service.bean.basicFile.measure.DriverManage;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;
import com.tianrui.service.bean.basicFile.nc.CustomerManage;
import com.tianrui.service.bean.basicFile.nc.MaterielManage;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplication;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplicationDetail;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseArrive;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplication;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationDetail;
import com.tianrui.service.bean.common.ReturnQueue;
import com.tianrui.service.bean.system.auth.Organization;
import com.tianrui.service.bean.system.auth.SystemUser;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.cache.CacheHelper;
import com.tianrui.service.cache.CacheModule;
import com.tianrui.service.mapper.basicFile.measure.DriverManageMapper;
import com.tianrui.service.mapper.basicFile.measure.VehicleManageMapper;
import com.tianrui.service.mapper.basicFile.nc.CustomerManageMapper;
import com.tianrui.service.mapper.basicFile.nc.MaterielManageMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseApplicationDetailMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseApplicationMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseArriveMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationDetailMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesArriveMapper;
import com.tianrui.service.mapper.common.ReturnQueueMapper;
import com.tianrui.service.mapper.system.auth.OrganizationMapper;
import com.tianrui.service.mapper.system.auth.SystemUserMapper;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.AppResult;
import com.tianrui.smartfactory.common.vo.PaginationVO;

@Service
public class AppStaticService implements IAppStaticService {

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
	
	@Transactional
	@Override
	public AppResult appLogin(LoginUserParam param) throws Exception {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getAccount())
				&& StringUtils.isNotBlank(param.getPwd())
				&& StringUtils.isNotBlank(param.getIDType())) {
			if (StringUtils.equals(param.getIDType(), Constant.ONE_STRING)) {
				result.setCode("-1");
				result.setMessage("待开发。");
				return result;
			}
			SystemUser user = userMapper.getByAccount(param.getAccount(), param.getIDType());
			if (user != null) {
				if (StringUtils.equals(user.getPassword(), param.getPwd())) {
					if (user.getIslock() == 0) {
						if (user.getIsvalid() == 1) {
							//累计登录次数
							addLoginCount(user);
							//缓存默认保存一天 && 返回登录结果
							result.setData(cacheUserAndReturnLoginData(user.getId()));
							result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
						} else {
							//用户无效
							result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR5);
						}
					} else {
						//用户被锁定
						result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR3);
					}
				} else {
					//密码不正确
					result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR2);
				}
			} else {
				//帐号不正确，找不到该用户
				result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR0);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	/**
	 * @annotation 缓存用户信息并返回登录结果对象
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	private LoginUserVo cacheUserAndReturnLoginData(String userId) throws Exception {
		//缓存默认保存一天
		SystemUserResp resp = systemUserService.get(userId, true);
		String tokenId =UUIDUtil.getId();
		resp.setTokenId(tokenId);
		String key = CacheHelper.buildKey(CacheModule.MEMBERLOGIN_APP, tokenId);
		cacheClient.saveObject(key, resp, 7*24*60*60);
		//整理登录结果对象
		LoginUserVo appResp = new LoginUserVo();
		appResp.setId(resp.getId());
		appResp.setNcid(resp.getNcid());
		appResp.setUserName(resp.getName());
		appResp.setIDType(resp.getIdentityTypes());
		appResp.setMobile(resp.getMobilePhone());
		appResp.setToken(resp.getTokenId());
		appResp.setOrgid(resp.getOrgid());
		appResp.setOrgName(resp.getOrgName());
		return appResp;
	}

	/**
	 * @annotation 累计登录次数
	 * @param user
	 */
	private void addLoginCount(SystemUser user) {
		SystemUser bean = new SystemUser();
		bean.setId(user.getId());
		bean.setLogincount(user.getLogincount()+1);
		bean.setLastLogintime(System.currentTimeMillis());
		userMapper.updateByPrimaryKeySelective(bean);
	}

	@Override
	public AppResult appLoginOut(LoginUserParam param) {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getId())) {
			cacheClient.remove(param.getId());
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}
	
	@Override
	public AppResult appUpdatePwd(LoginUserParam param) {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getId())
				&& StringUtils.isNotBlank(param.getPwd())
				&& StringUtils.isNotBlank(param.getNewPwd())) {
			SystemUser user = userMapper.selectByPrimaryKey(param.getId());
			if (StringUtils.equals(user.getPassword(), param.getPwd())) {
				user.setPassword(param.getNewPwd());
				userMapper.updateByPrimaryKeySelective(user);
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			} else {
				result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR9);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}
	
	@Override
	public AppResult appBindPhoneNumber(LoginUserParam param) {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getId())
				&& StringUtils.isNotBlank(param.getMobilePhone())){
			SystemUser user = userMapper.validPhoneIsOne(param.getMobilePhone());
			if (user == null) {
				SystemUser bean = new SystemUser();
				bean.setId(param.getId());
				bean.setMobilePhone(param.getMobilePhone());
				bean.setModifier(param.getId());
				bean.setModifytime(System.currentTimeMillis());
				userMapper.updateByPrimaryKeySelective(bean);
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			} else {
				result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR13);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}
	
	@Override
	public AppResult appUnBindPhoneNumber(LoginUserParam param) {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getId())){
			SystemUser bean = new SystemUser();
			bean.setId(param.getId());
			bean.setMobilePhone("");
			bean.setModifier(param.getId());
			bean.setModifytime(System.currentTimeMillis());
			userMapper.updateByPrimaryKeySelective(bean);
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	@Override
	public AppResult home(HomePageParam param) {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getUserId())
				&& StringUtils.isNotBlank(param.getIDType())) {
			switch (param.getIDType()) {
			//客户
			case "1":
				result = customerHome(param);
				break;
			//供应商				
			case "2":
				result = supplierHome(param);
				break;
			default:
				result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR14);
				break;
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
	/**
	 * @annotation 供应商首页数据
	 * @param param
	 * @return
	 */
	private AppResult supplierHome(HomePageParam param) {
		AppResult result = AppResult.getAppResult();
		Object[] objArr = new Object[] {"","",""};
		List<HomeBillVo> billList = purchaseApplicationMapper.appHomeBill(param);
		if (CollectionUtils.isNotEmpty(billList)) {
			objArr[0] = billList.get(0);
		}
		List<HomeNoticeVo> noticeList = purchaseArriveMapper.appHomeNotice(param);
		if (CollectionUtils.isNotEmpty(noticeList)) {
			objArr[1] = noticeList.get(0);
		}
		List<String> vehicleList = purchaseArriveMapper.appHomeVehicle(param);
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
		return result;
	}

	@Override
	public AppResult billList(BillListParam param) {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getUserId())
				&& StringUtils.isNotBlank(param.getIDType())
				&& StringUtils.isNotBlank(param.getSalesOrg())
				&& StringUtils.isNotBlank(param.getType())) {
			switch (param.getIDType()) {
			//客户
			case "1":
				result = customerBillList(param);
				break;
			//供应商				
			case "2":
				result = supplierBillList(param);
				break;
			default:
				result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR14);
				break;
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

	private AppResult supplierBillList(BillListParam param) {
		AppResult result = AppResult.getAppResult();
		PaginationVO<BillListVo> page = new PaginationVO<BillListVo>();
		long count = purchaseApplicationMapper.appBillListCount(param);
		if (count > 0) {
			param.setStart((param.getPageNo() - 1) * param.getPageSize());
			param.setLimit(param.getPageSize());
			List<BillListVo> list = purchaseApplicationMapper.appBillList(param);
			page.setList(list);
		}
		page.setPageNo(param.getPageNo());
		page.setPageSize(param.getPageSize());
		page.setTotal(count);
		result.setData(page);
		result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		return null;
	}

	@Override
	public AppResult billDetail(BillListParam param) {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getId())
				&& StringUtils.isNotBlank(param.getDetailId())
				&& StringUtils.isNotBlank(param.getIDType())) {
			switch (param.getIDType()) {
			//客户
			case "1":
				result.setData(salesApplicationMapper.appBillDetail(param));
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				break;
			//供应商				
			case "2":
				result.setData(purchaseApplicationMapper.appBillDetail(param));
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				break;
			default:
				result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR14);
				break;
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
		sa.setCode(getCode("XXSO", param.getUserId()));
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
	
	private String getCode(String code, String userId) throws Exception {
		GetCodeReq codeReq = new GetCodeReq();
		codeReq.setCode(code);
		codeReq.setCodeType(true);
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
				if (StringUtils.equals(sa.getStatus(), Constant.ZERO_STRING)) {
					if (StringUtils.equals(sa.getSource(), Constant.ONE_STRING)) {
						SalesApplication bean = new SalesApplication();
						bean.setId(sa.getId());
						bean.setState(Constant.ZERO_STRING);
						salesApplicationMapper.updateByPrimaryKeySelective(bean);
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
				&& StringUtils.isNotBlank(param.getDriver())
				&& param.getNumber() != null) {
			switch (param.getIDType()) {
			//客户
			case "1":
				result = saveCustomerNotice(param);
				break;
			//供应商				
			case "2":
				result = saveSupplierNotice(param);
				break;
			default:
				result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR14);
				break;
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	private AppResult saveCustomerNotice(NoticeSave param) {
		// TODO Auto-generated method stub
		return null;
	}
	//保存到货通知单
	private AppResult saveSupplierNotice(NoticeSave param) throws Exception {
		AppResult result = AppResult.getAppResult();
		SystemUser user = userMapper.selectByPrimaryKey(param.getUserId());
		if (user != null) {
			if (param.getNumber() > 0) {
				PurchaseApplication pa = purchaseApplicationMapper.selectByPrimaryKey(param.getId());
				PurchaseApplicationDetail pad = purchaseApplicationDetailMapper.selectByPrimaryKey(param.getDetailId());
				if (pa != null && pad != null) {
					if (param.getNumber() <= pad.getMargin()) {
						VehicleManage vehicle = vehicleManageMapper.selectByPrimaryKey(param.getVehicle());
						if (validVehicle(vehicle, result)) {
							if (StringUtils.isNotBlank(param.getDriver())) {
								DriverManage driver = driverManageMapper.selectByPrimaryKey(param.getDriver());
								if (validDriver(driver, result)) {
									result = putSupplierNoticeValue(param, user, pa, pad, vehicle, driver);
								}
							} else {
								result = putSupplierNoticeValue(param, user, pa, pad, vehicle, null);
							}
						}
					} else {
						result.setErrorCode(ErrorCode.NOTICE_NUMBER_ERROR);
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

	private AppResult putSupplierNoticeValue(NoticeSave param, SystemUser user, PurchaseApplication pa,
			PurchaseApplicationDetail pad, VehicleManage vehicle, DriverManage driver) throws Exception {
		AppResult result = AppResult.getAppResult();
		PurchaseArrive bean = new PurchaseArrive();
		bean.setId(UUIDUtil.getId());
		bean.setCode(getCode("DH", param.getUserId()));
		bean.setAuditstatus(Constant.ZERO_STRING);
		bean.setSource(Constant.TWO_STRING);
		bean.setStatus(Constant.ZERO_STRING);
		bean.setType(Constant.ZERO_STRING);
		bean.setVehicleid(vehicle.getId());
		bean.setVehicleno(vehicle.getVehicleno());
		bean.setVehiclerfid(vehicle.getRfid());
		if (driver != null) {
			bean.setDriverid(driver.getId());
			bean.setDrivername(driver.getName());
			bean.setDriveridentityno(driver.getIdentityno());
		}
		bean.setBillid(pa.getId());
		bean.setBillcode(pa.getCode());
		bean.setBilldetailid(pad.getId());
		bean.setArrivalamount(param.getNumber());
		bean.setUnit(pad.getUnit());
		bean.setMakerid(param.getUserId());
		bean.setMakebillname(user.getName());
		bean.setMakebilltime(System.currentTimeMillis());
		bean.setState(Constant.ONE_STRING);
		bean.setCreator(param.getUserId());
		bean.setCreatetime(System.currentTimeMillis());
		purchaseArriveMapper.insertSelective(bean);
		pad.setMargin(pad.getMargin() - param.getNumber());
		pad.setPretendingtake(pad.getPretendingtake() + param.getNumber());
		purchaseApplicationDetailMapper.updateByPrimaryKeySelective(pad);
		result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		return result;
	}

	private boolean validVehicle(VehicleManage vehicle, AppResult result) {
		boolean flag = false;
		if (vehicle != null && StringUtils.equals(vehicle.getState(), Constant.ONE_STRING)) {
			if (StringUtils.equals(vehicle.getIsvalid(), Constant.ONE_STRING)) {
				if (StringUtils.equals(vehicle.getIsblacklist(), Constant.ZERO_STRING)) {
					flag = true;
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
			switch (param.getIDType()) {
			//客户
			case "1":
				result = customerNoticeList(param);
				break;
			//供应商				
			case "2":
				result = supplierNoticeList(param);
				break;
			default:
				result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR14);
				break;
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	private AppResult customerNoticeList(NoticeListParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	private AppResult supplierNoticeList(NoticeListParam param) {
		AppResult result = AppResult.getAppResult();
		PaginationVO<NoticeListVo> page = new PaginationVO<NoticeListVo>();
		long count = purchaseArriveMapper.appNoticeListCount(param);
		if (count > 0) {
			List<NoticeListVo> list = purchaseArriveMapper.appNoticeList(param);
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
	public AppResult noticeDetail(NoticeListParam param) {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getId())
				&& StringUtils.isNotBlank(param.getIDType())) {
			switch (param.getIDType()) {
			//客户
			case "1":
				// TODO
				break;
			//供应商				
			case "2":
				result.setData(purchaseArriveMapper.appNoticeDetail(param));
				break;
			default:
				result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR14);
				break;
			}
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
				&& StringUtils.isNotBlank(param.getId())
				&& StringUtils.isNotBlank(param.getVehicle())
				&& StringUtils.isNotBlank(param.getDriver())
				&& param.getNumber() != null) {
			switch (param.getIDType()) {
			//客户
			case "1":
				// TODO
				break;
			//供应商				
			case "2":
				result = supplierNoticeUpdate(param);
				break;
			default:
				result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR14);
				break;
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	private AppResult supplierNoticeUpdate(NoticeSave param) throws Exception {
		AppResult result = AppResult.getAppResult();
		SystemUser user = userMapper.selectByPrimaryKey(param.getUserId());
		if (user != null) {
			if (param.getNumber() > 0) {
				PurchaseArrive notice = purchaseArriveMapper.selectByPrimaryKey(param.getId());
				if (notice != null && StringUtils.equals(notice.getState(), Constant.ONE_STRING)) {
					if (!StringUtils.equals(notice.getStatus(), Constant.THREE_STRING)) {
						double number = notice.getArrivalamount();
						PurchaseApplication pa = purchaseApplicationMapper.selectByPrimaryKey(param.getId());
						PurchaseApplicationDetail pad = purchaseApplicationDetailMapper.selectByPrimaryKey(param.getDetailId());
						if (pa != null && pad != null) {
							if (param.getNumber() <= pad.getMargin() + number) {
								VehicleManage vehicle = vehicleManageMapper.selectByPrimaryKey(param.getVehicle());
								if (validVehicle(vehicle, result)) {
									if (StringUtils.isNotBlank(param.getDriver())) {
										DriverManage driver = driverManageMapper.selectByPrimaryKey(param.getDriver());
										if (validDriver(driver, result)) {
											notice.setVehicleid(vehicle.getId());
											notice.setVehicleno(vehicle.getVehicleno());
											notice.setVehiclerfid(vehicle.getRfid());
											notice.setDriverid(driver.getId());
											notice.setDrivername(driver.getName());
											notice.setDriveridentityno(driver.getIdentityno());
											notice.setArrivalamount(param.getNumber());
											notice.setModifier(param.getUserId());
											notice.setModifytime(System.currentTimeMillis());
											purchaseArriveMapper.updateByPrimaryKeySelective(notice);
											pad.setMargin(pad.getMargin() + number - param.getNumber());
											pad.setPretendingtake(pad.getPretendingtake() - number + param.getNumber());
											purchaseApplicationDetailMapper.updateByPrimaryKeySelective(pad);
											result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
										}
									} else {
										result = putSupplierNoticeValue(param, user, pa, pad, vehicle, null);
									}
								}
							} else {
								result.setErrorCode(ErrorCode.NOTICE_NUMBER_ERROR);
							}
						} else {
							result.setErrorCode(ErrorCode.APPLICATION_NOT_EXIST);
						}
					} else {
						result.setErrorCode(ErrorCode.NOTICE_ON_INVALID);
					}
				} else {
					result.setErrorCode(ErrorCode.NOTICE_NOT_EXIST);
				}
			} else {
				result.setErrorCode(ErrorCode.NOTICE_NUMBER_ERROR);
			}
		} else {
			result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR17);
		}
		return result;
	}
	
}
