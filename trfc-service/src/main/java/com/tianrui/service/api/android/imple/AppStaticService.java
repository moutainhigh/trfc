package com.tianrui.service.api.android.imple;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.api.android.imple.IAppStaticService;
import com.tianrui.api.intf.system.auth.ISystemUserService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.android.AppVersionParam;
import com.tianrui.api.req.android.BillListParam;
import com.tianrui.api.req.android.BillSave;
import com.tianrui.api.req.android.DriverSave;
import com.tianrui.api.req.android.HomePageParam;
import com.tianrui.api.req.android.LoginUserParam;
import com.tianrui.api.req.android.MyPnListParam;
import com.tianrui.api.req.android.MyVehicleListParam;
import com.tianrui.api.req.android.NoticeListParam;
import com.tianrui.api.req.android.NoticeSave;
import com.tianrui.api.req.android.SearchKeyParam;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.android.AppVersionVo;
import com.tianrui.api.resp.android.BillListVo;
import com.tianrui.api.resp.android.HomeBillVo;
import com.tianrui.api.resp.android.HomeNoticeVo;
import com.tianrui.api.resp.android.LoginUserVo;
import com.tianrui.api.resp.android.MyPnListVo;
import com.tianrui.api.resp.android.MyVehicleListVo;
import com.tianrui.api.resp.android.NoticeListVo;
import com.tianrui.api.resp.android.SearchListVo;
import com.tianrui.api.resp.android.UserDriverVehicleVo;
import com.tianrui.api.resp.android.UserDriverVo;
import com.tianrui.api.resp.android.UserVehicleVo;
import com.tianrui.api.resp.system.auth.SystemUserResp;
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
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationDetail;
import com.tianrui.service.bean.businessManage.salesManage.SalesArrive;
import com.tianrui.service.bean.common.AppVersion;
import com.tianrui.service.bean.common.ReturnQueue;
import com.tianrui.service.bean.common.UserDriver;
import com.tianrui.service.bean.common.UserVehicle;
import com.tianrui.service.bean.system.auth.Organization;
import com.tianrui.service.bean.system.auth.SystemUser;
import com.tianrui.service.bean.system.merchants.SupplierGroup;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.cache.CacheHelper;
import com.tianrui.service.cache.CacheModule;
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
	
	@Transactional
	@Override
	public AppResult appLogin(LoginUserParam param) throws Exception {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getAccount())
				&& StringUtils.isNotBlank(param.getPwd())
				&& StringUtils.isNotBlank(param.getIDType())) {
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
		LoginUserVo vo = new LoginUserVo();
		vo.setId(resp.getId());
		vo.setNcid(resp.getNcid());
		vo.setUserName(resp.getName());
		vo.setIDType(resp.getIdentityTypes());
		vo.setMobile(resp.getMobilePhone());
		vo.setToken(resp.getTokenId());
		vo.setOrgid(resp.getOrgid());
		vo.setOrgName(resp.getOrgName());
		return vo;
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
		return result;
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

	@Transactional
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
				result.setErrorCode(ErrorCode.APPLICATION_NOT_EXIST);
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
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
					if (StringUtils.equals(pa.getSupplierid(), param.getNcId())) {
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
		bean.setCode(getCode("DH", param.getUserId(), true));
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
			saveUserDriver(user.getId(), driver.getId());
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
		updateCode("DH", param.getUserId());
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
			param.setStart((param.getPageNo() - 1) * param.getPageSize());
			param.setLimit(param.getPageSize());
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
	public AppResult noticeUpdate(NoticeSave param) throws Exception {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getUserId())
				&& StringUtils.isNotBlank(param.getIDType())
				&& (StringUtils.isNotBlank(param.getId()))) {
			if (StringUtils.isNotBlank(param.getId())
					|| StringUtils.isNotBlank(param.getVehicle())
					|| StringUtils.isNotBlank(param.getDriver())
					|| param.getNumber() != null) {
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
				result.setErrorCode(ErrorCode.DATE_NOT_UPDATE);
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
			PurchaseArrive notice = purchaseArriveMapper.selectByPrimaryKey(param.getId());
			if (notice != null && StringUtils.equals(notice.getState(), Constant.ONE_STRING)) {
				if (!StringUtils.equals(notice.getStatus(), Constant.THREE_STRING)) {
					double number = notice.getArrivalamount();
					PurchaseApplication pa = purchaseApplicationMapper.selectByPrimaryKey(notice.getBillid());
					PurchaseApplicationDetail pad = purchaseApplicationDetailMapper.selectByPrimaryKey(notice.getBilldetailid());
					if (pa != null && pad != null && StringUtils.equals(pa.getSupplierid(), param.getNcId())) {
						boolean flag = false;
						//判断是否修改到货量
						PurchaseArrive bean = new PurchaseArrive();
						if (param.getNumber() != null) {
							if (param.getNumber() > 0) {
								if (param.getNumber() <= pad.getMargin() + number) {
									bean.setArrivalamount(param.getNumber());
									pad.setMargin(pad.getMargin() + number - param.getNumber());
									pad.setPretendingtake(pad.getPretendingtake() - number + param.getNumber());
									purchaseApplicationDetailMapper.updateByPrimaryKeySelective(pad);
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
							purchaseArriveMapper.updateByPrimaryKeySelective(bean);
							result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
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
			result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR17);
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
			switch (param.getIDType()) {
			//客户
			case "1":
				result = customerNoticeCancel(param);
				break;
			//供应商				
			case "2":
				result = supplierNoticeCancel(param);
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

	private AppResult customerNoticeCancel(NoticeListParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	private AppResult supplierNoticeCancel(NoticeListParam param) {
		AppResult result = AppResult.getAppResult();
		SystemUser user = userMapper.selectByPrimaryKey(param.getUserId());
		if (user != null) {
			PurchaseArrive notice = purchaseArriveMapper.selectByPrimaryKey(param.getId());
			if (notice != null) {
				PurchaseApplication pa = purchaseApplicationMapper.selectByPrimaryKey(notice.getBillid());
				PurchaseApplicationDetail pad = purchaseApplicationDetailMapper.selectByPrimaryKey(notice.getBilldetailid());
				if (pa != null && pad != null && StringUtils.equals(pa.getSupplierid(), param.getNcId())) {
					if (StringUtils.equals(notice.getAuditstatus(), Constant.ZERO_STRING)) {
						//关闭通知单并回写余量和预提量
						notice.setStatus(Constant.THREE_STRING);
						notice.setAbnormalperson(user.getId());
						notice.setAbnormalpersonname(user.getName());
						notice.setAbnormaltime(System.currentTimeMillis());
						purchaseArriveMapper.updateByPrimaryKeySelective(notice);
						pad.setMargin(pad.getMargin() + notice.getArrivalamount());
						pad.setPretendingtake(pad.getPretendingtake() - notice.getArrivalamount());
						purchaseApplicationDetailMapper.updateByPrimaryKeySelective(pad);
						result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
					} else {
						result.setErrorCode(ErrorCode.NOTICE_YES_AUDIT);
					}
				} else {
					result.setErrorCode(ErrorCode.APPLICATION_NOT_EXIST);
				}
			} else {
				result.setErrorCode(ErrorCode.NOTICE_NOT_EXIST);
			}
		} else {
			result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR17);
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
		switch (param.getIDType()) {
		//客户
		case "1":
			result = customerMyVehicle(param);
			break;
		//供应商				
		case "2":
			result = supplierMyVehicle(param);
			break;
		default:
			result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR14);
			break;
		}
		return result;
	}

	private AppResult customerMyVehicle(MyVehicleListParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	private AppResult supplierMyVehicle(MyVehicleListParam param) {
		AppResult result = AppResult.getAppResult();
		PaginationVO<MyVehicleListVo> page = new PaginationVO<MyVehicleListVo>();
		long count = purchaseArriveMapper.appMyVehicleListCount(param);
		if (count > 0) {
			param.setStart((param.getPageNo() - 1) * param.getPageSize());
			param.setLimit(param.getPageSize());
			List<MyVehicleListVo> list = purchaseArriveMapper.appMyVehicleList(param);
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
			switch (param.getIDType()) {
			//客户
			case "1":
				result = customerMyPn(param);
				break;
			//供应商				
			case "2":
				result = supplierMyPn(param);
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

	private AppResult customerMyPn(MyPnListParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	private AppResult supplierMyPn(MyPnListParam param) {
		AppResult result = AppResult.getAppResult();
		PaginationVO<MyPnListVo> page = new PaginationVO<MyPnListVo>();
		long count = poundNoteMapper.appPnSupListCount(param);
		if (count > 0) {
			param.setStart((param.getPageNo() - 1) * param.getPageSize());
			param.setLimit(param.getPageSize());
			List<MyPnListVo> list = poundNoteMapper.appSupPnList(param);
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
	public AppResult myPnDetail(MyPnListParam param) {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getId())) {
			result.setData(poundNoteMapper.appPnSupDetail(param));
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	@Override
	public AppResult saveDriver(DriverSave param) throws Exception {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getUserId())
				&& StringUtils.isNotBlank(param.getName())
				&& StringUtils.isNotBlank(param.getTelephone())
				&& StringUtils.isNotBlank(param.getIdNo())) {
			DriverManage driver = new DriverManage();
			driver.setId(UUIDUtil.getId());
			driver.setCode(getCode("DR", param.getUserId(), true));
			driver.setInternalcode(getCode("DR", param.getUserId(), false));
			driver.setName(param.getName());
			driver.setAbbrname(param.getAbbrName());
			driver.setTelephone(param.getTelephone());
			driver.setIdentityno(param.getIdNo());
			driver.setIsvalid(Constant.ONE_STRING);
			driver.setOrgid(Constant.ORG_ID);
			driver.setOrgname(Constant.ORG_NAME);
			driver.setState(Constant.ONE_STRING);
			driver.setCreator(param.getUserId());
			driver.setCreatetime(System.currentTimeMillis());
			driverManageMapper.insertSelective(driver);
			updateCode("DR", param.getUserId());
			result.setData(driver.getId());
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	@Override
	public AppResult vehicleSearch(SearchKeyParam param) {
		AppResult result = AppResult.getAppResult();
		if (param != null) {
			result.setData(vehicleManageMapper.appAutoCompleteSearch(param));
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	@Override
	public AppResult driverSearch(SearchKeyParam param) {
		AppResult result = AppResult.getAppResult();
		if (param != null) {
			result.setData(driverManageMapper.appAutoCompleteSearch(param));
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	@Override
	public AppResult materialSearch(SearchKeyParam param) {
		AppResult result = AppResult.getAppResult();
		if (param != null) {
			result.setData(materielManageMapper.appAutoCompleteSearch(param));
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
			switch (param.getIDType()) {
			//客户
			case "1":
				break;
			//供应商				
			case "2":
				result = supplierGroupUser(param);
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

	private AppResult supplierGroupUser(LoginUserParam param) {
		AppResult result = AppResult.getAppResult();
		SupplierGroup supplierGroup = supplierGroupMapper.validateSupplier(param.getNcId());
		if(supplierGroup != null){
			List<AppCutoverGroup> list = supplierGroupMapper.selectSupplierByGroupId(supplierGroup.getGroupid());
			result.setData(list);
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}else{
			result.setErrorCode(ErrorCode.SUPPLIER_GROUP_ERROR1);
		}
		return result;
	}

	@Transactional
	@Override
	public AppResult userCutover(LoginUserParam param) throws Exception {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getCutoverUserNCId())
				&& StringUtils.isNotBlank(param.getIDType())
				&& StringUtils.isNotBlank(param.getKey())) {
			SystemUser user = userMapper.selectByNcIdAndIdentityTypes(param.getCutoverUserNCId(), param.getIDType());
			if(user != null){
				//累计登录次数
				addLoginCount(user);
				//缓存默认保存一天 && 返回登录结果
				result.setData(cacheUserAndReturnLoginData(user.getId()));
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				cacheClient.remove(param.getKey());
			}
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	@Override
	public AppResult userVehicle(MyVehicleListParam param) {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getUserId())) {
			List<SearchListVo> list = userVehicleMapper.listUserVehicle(param);
			result.setData(list);
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}

	@Override
	public AppResult userDriver(MyVehicleListParam param) {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getUserId())) {
			List<SearchListVo> list = userDriverMapper.listUserDriver(param);
			result.setData(list);
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}
	
	@Override
	public AppResult appVersion(AppVersionParam param){
		AppResult result = AppResult.getAppResult();
		if(param != null && StringUtils.isNotBlank(param.getPhoneType())
				&& StringUtils.isNotBlank(param.getVersion())){
			AppVersion version = appVersionMapper.selectByPrimaryKey(param.getPhoneType());
			if(version != null){
				AppVersionVo vo = new AppVersionVo();
				if(!StringUtils.equals(param.getVersion(), version.getCode())){
					vo.setFlag(Constant.ONE_STRING);
					vo.setUrl(version.getVersionurl());
				}else{
					vo.setFlag(Constant.ZERO_STRING);
				}
				result.setData(vo);
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.APP_VERSION_EXIST);
			}
		}
		return result;
	}

	@Override
	public AppResult vcAndDr(MyVehicleListParam param) {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getUserId())) {
			param.setPageSize(1);
			List<SearchListVo> listVc = userVehicleMapper.listUserVehicle(param);
			List<SearchListVo> listDr = userDriverMapper.listUserDriver(param);
			UserDriverVehicleVo vo = new UserDriverVehicleVo();
			if (CollectionUtils.isNotEmpty(listVc)) {
				vo.setVehicleId(listVc.get(0).getId());
				vo.setVehicleNo(listVc.get(0).getName());
			}
			if (CollectionUtils.isNotEmpty(listDr)) {
				vo.setDriverId(listDr.get(0).getId());
				vo.setDriverName(listDr.get(0).getName());
			}
			result.setData(vo);
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		} else {
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}
}
