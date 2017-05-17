package com.tianrui.web.api.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.basicFile.measure.IDriverManageService;
import com.tianrui.api.intf.basicFile.measure.IVehicleManageService;
import com.tianrui.api.intf.basicFile.nc.IMaterielManageService;
import com.tianrui.api.intf.businessManage.poundNoteMaintain.IPoundNoteService;
import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseApplicationService;
import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseArriveService;
import com.tianrui.api.intf.businessManage.salesManage.ISalesApplicationService;
import com.tianrui.api.intf.businessManage.salesManage.ISalesArriveService;
import com.tianrui.api.intf.common.IAppVersionService;
import com.tianrui.api.intf.system.auth.ISystemUserService;
import com.tianrui.api.intf.system.merchants.ISupplierGroupService;
import com.tianrui.api.req.businessManage.app.AppDriverSaveReq;
import com.tianrui.api.req.businessManage.app.AppNoticeOrderReq;
import com.tianrui.api.req.businessManage.app.AppOrderReq;
import com.tianrui.api.req.businessManage.app.AppOrderSaveReq;
import com.tianrui.api.req.businessManage.app.AppPoundOrderReq;
import com.tianrui.api.req.businessManage.app.AppPoundOrderResp;
import com.tianrui.api.req.businessManage.app.AppQueryReq;
import com.tianrui.api.req.businessManage.app.AppUserEditReq;
import com.tianrui.api.req.businessManage.app.AppVersionReq;
import com.tianrui.api.req.system.auth.AppUserReq;
import com.tianrui.api.req.system.merchants.AppUserGroupReq;
import com.tianrui.api.resp.businessManage.app.AppDriverResp;
import com.tianrui.api.resp.businessManage.app.AppMaterialResp;
import com.tianrui.api.resp.businessManage.app.AppMsgCountResp;
import com.tianrui.api.resp.businessManage.app.AppMsgResp;
import com.tianrui.api.resp.businessManage.app.AppNoticeOrderResp;
import com.tianrui.api.resp.businessManage.app.AppOrderResp;
import com.tianrui.api.resp.businessManage.app.AppVehicleResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.smvc.ApiAuthValidation;
import com.tianrui.web.smvc.ApiParamRawType;


/**
 * 用户验证相关
 * @author lixp 2017年1月7日 09:24:36
 *
 */
@Controller
@RequestMapping("api/android/static")
public class ApiStaticAction {

	private Logger log = LoggerFactory.getLogger(ApiStaticAction.class);
	
	@Autowired
	private ISystemUserService systemUserService;
	@Autowired
	private ISalesApplicationService salesApplicationService;
	@Autowired
	private IPurchaseApplicationService purchaseApplicationService;
	@Autowired
	private ISalesArriveService salesArriveService;
	@Autowired
	private IPurchaseArriveService purchaseArriveService;
	@Autowired
	private IPoundNoteService poundNoteService;
	@Autowired
	private IVehicleManageService vehicleManageService;
	@Autowired
	private IDriverManageService driverManageService;
	@Autowired
	private IAppVersionService appVersionService;
	@Autowired
	private IMaterielManageService materielManageService;
	@Autowired
	private ISupplierGroupService supplierGroupService;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ApiParamRawType(AppUserReq.class)
	@ResponseBody
	public ApiResult login(ApiParam<AppUserReq> req){
		Result rs = Result.getErrorResult();
		try {
			rs = systemUserService.appLogin(req.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
	
	@RequestMapping(value="/updatePswd",method=RequestMethod.POST)
	@ApiParamRawType(AppUserReq.class)
	@ApiAuthValidation(callType="3")
	@ResponseBody
	public ApiResult updatePswd(ApiParam<AppUserReq> req){
		Result rs = Result.getErrorResult();
		try {
			rs = systemUserService.appUpdatePswd(req.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * 订单列表
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/orderPage",method=RequestMethod.POST)
	@ApiParamRawType(AppOrderReq.class)
	@ApiAuthValidation(callType="3")
	@ResponseBody
	public ApiResult orderPage(ApiParam<AppOrderReq> appParam){
		Result rs = Result.getSuccessResult();
		try {
			AppOrderReq req = appParam.getBody();
			PaginationVO<AppOrderResp> page = null;
			SystemUserResp user = systemUserService.getUser(appParam.getHead().getUserId());
			if(user != null){
				req.setUserId(user.getNcid());
				if(StringUtils.equals(user.getIdentityTypes(), Constant.USER_SUPPLIER)){
					page = purchaseApplicationService.appToPage(req);
					rs.setData(page);
				}
				if(StringUtils.equals(user.getIdentityTypes(), Constant.USER_CUSTOMER)){
					page = salesApplicationService.appToPage(req);
					rs.setData(page);
				}
			}else{
				rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR1);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * 订单详情
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/orderDetail",method=RequestMethod.POST)
	@ApiParamRawType(AppOrderReq.class)
	@ResponseBody
	public ApiResult orderDetail(ApiParam<AppOrderReq> appParam){
		Result rs = Result.getErrorResult();
		try {
			AppOrderReq req = appParam.getBody();
			SystemUserResp user = systemUserService.getUser(appParam.getHead().getUserId());
			if(user != null){
				if(StringUtils.equals(user.getIdentityTypes(), Constant.USER_SUPPLIER)){
					rs = purchaseApplicationService.appToDetail(req);
				}
				if(StringUtils.equals(user.getIdentityTypes(), Constant.USER_CUSTOMER)){
					rs = salesApplicationService.appToDetail(req);
				}
			}else{
				rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR1);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * 通知单列表
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/noticeOderPage",method=RequestMethod.POST)
	@ApiParamRawType(AppNoticeOrderReq.class)
	@ResponseBody
	public ApiResult noticeOderPage(ApiParam<AppNoticeOrderReq> appParam){
		Result rs = Result.getSuccessResult();
		try {
			AppNoticeOrderReq req = appParam.getBody();
			SystemUserResp user = systemUserService.getUser(appParam.getHead().getUserId());
			PaginationVO<AppNoticeOrderResp> page = null;
			if(user != null){
				req.setUserId(user.getId());
				if(StringUtils.equals(user.getIdentityTypes(), Constant.USER_SUPPLIER)){
					page = purchaseArriveService.appToPage(req);
					rs.setData(page);
				}
				if(StringUtils.equals(user.getIdentityTypes(), Constant.USER_CUSTOMER)){
					page = salesArriveService.appToPage(req);
					rs.setData(page);
				}
			}else{
				rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR1);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * 通知单详情
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/noticeOderDetail",method=RequestMethod.POST)
	@ApiParamRawType(AppNoticeOrderReq.class)
	@ResponseBody
	public ApiResult noticeOderDetail(ApiParam<AppNoticeOrderReq> appParam){
		Result rs = Result.getErrorResult();
		try {
			AppNoticeOrderReq req = appParam.getBody();
			SystemUserResp user = systemUserService.getUser(appParam.getHead().getUserId());
			if(user != null){
				if(StringUtils.equals(user.getIdentityTypes(), Constant.USER_SUPPLIER)){
					rs = purchaseArriveService.appToDetail(req);
				}
				if(StringUtils.equals(user.getIdentityTypes(), Constant.USER_CUSTOMER)){
					rs = salesArriveService.appToDetail(req);
				}
			}else{
				rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR1);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * 过榜单分页
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/overListPage",method=RequestMethod.POST)
	@ApiParamRawType(AppPoundOrderReq.class)
	@ResponseBody
	public ApiResult overListPage(ApiParam<AppPoundOrderReq> appParam){
		Result rs = Result.getSuccessResult();
		try {
			AppPoundOrderReq req = appParam.getBody();
			SystemUserResp user = systemUserService.getUser(appParam.getHead().getUserId());
			if(user != null){
				req.setUserId(user.getNcid());
				req.setIdentityTypes(user.getIdentityTypes());
				PaginationVO<AppPoundOrderResp> page = poundNoteService.appToPage(req);
				rs.setData(page);
			}else{
				rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR1);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * 过榜单详情
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/overListDetail",method=RequestMethod.POST)
	@ApiParamRawType(AppPoundOrderReq.class)
	@ResponseBody
	public ApiResult overListDetail(ApiParam<AppPoundOrderReq> appParam){
		Result rs = Result.getErrorResult();
		try {
			AppPoundOrderReq req = appParam.getBody();
			SystemUserResp user = systemUserService.getUser(appParam.getHead().getUserId());
			if(user != null){
				req.setIdentityTypes(user.getIdentityTypes());
				rs = poundNoteService.appToDetail(req);
			}else{
				rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR1);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * 车牌号查询
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/vehicleList",method=RequestMethod.POST)
	@ApiParamRawType(AppQueryReq.class)
	@ResponseBody
	public ApiResult vehicleList(ApiParam<AppQueryReq> appParam){
		Result rs = Result.getSuccessResult();
		try {
			PaginationVO<AppVehicleResp> page = vehicleManageService.appToPage(appParam.getBody());
			rs.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * 司机查询
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/driverList",method=RequestMethod.POST)
	@ApiParamRawType(AppQueryReq.class)
	@ResponseBody
	public ApiResult driverList(ApiParam<AppQueryReq> appParam){
		Result rs = Result.getSuccessResult();
		try {
			PaginationVO<AppDriverResp> page = driverManageService.appToPage(appParam.getBody());
			rs.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * 派单
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/orderCreate",method=RequestMethod.POST)
	@ApiParamRawType(AppOrderSaveReq.class)
	@ResponseBody
	public ApiResult orderCreate(ApiParam<AppOrderSaveReq> appParam){
		Result rs = Result.getErrorResult();
		try {
			AppOrderSaveReq req = appParam.getBody();
			SystemUserResp user = systemUserService.getUser(appParam.getHead().getUserId());
			if(user != null){
				req.setUserId(user.getId());
				if(StringUtils.equals(user.getIdentityTypes(), Constant.USER_SUPPLIER)){
					rs = purchaseArriveService.appToAddNotice(req);
				}
				if(StringUtils.equals(user.getIdentityTypes(), Constant.USER_CUSTOMER)){
					rs = salesArriveService.appToAddNotice(req);
				}
			}else{
				rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR1);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * 新增司机
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/driverCreate",method=RequestMethod.POST)
	@ApiParamRawType(AppDriverSaveReq.class)
	@ResponseBody
	public ApiResult driverCreate(ApiParam<AppDriverSaveReq> appParam){
		Result rs = Result.getErrorResult();
		try {
			rs = driverManageService.appDriverCreate(appParam.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * 在厂车辆查询
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/vehicleInFactory",method=RequestMethod.POST)
	@ApiParamRawType(AppOrderReq.class)
	@ResponseBody
	public ApiResult vehicleInFactory(ApiParam<AppOrderReq> appParam){
		Result rs = Result.getErrorResult();
		try {
			AppOrderReq req = appParam.getBody();
			SystemUserResp user = systemUserService.getUser(appParam.getHead().getUserId());
			if(user != null){
				req.setUserId(user.getNcid());
				if(StringUtils.equals(user.getIdentityTypes(), Constant.USER_SUPPLIER)){
					rs = purchaseArriveService.appInfoFactoryVehicleAndMaterial(req);
				}
				if(StringUtils.equals(user.getIdentityTypes(), Constant.USER_CUSTOMER)){
					rs = salesArriveService.appInfoFactoryVehicleAndMaterial(req);
				}
			}else{
				rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR1);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * 未读消息数量
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/countMsgUnread",method=RequestMethod.POST)
	@ApiParamRawType(AppQueryReq.class)
	@ResponseBody
	public ApiResult countMsgUnread(ApiParam<AppQueryReq> req){
		Result rs=Result.getSuccessResult();
		AppMsgCountResp item =new AppMsgCountResp();
		item.setMsgCount("20");
		rs.setData(item);
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * 消息列表
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/msgList",method=RequestMethod.POST)
	@ApiParamRawType(AppQueryReq.class)
	@ResponseBody
	public ApiResult msgList(ApiParam<AppQueryReq> req){
		PaginationVO<AppMsgResp> page=new PaginationVO<AppMsgResp>();
		Result rs=Result.getSuccessResult();
		List<AppMsgResp> list =new ArrayList<AppMsgResp>();
		AppMsgResp item =new AppMsgResp();
		item.setId(UUIDUtil.getId());
		item.setTitle("系统消息");
		item.setContent("您有一条系统消息,来源与张先生");
		item.setCreateTime("2017-02-02 12:12");
		item.setFromUser("张先生");
		item.setStatus("1");
		list.add(item);
		
		page.setPageNo(1);
		page.setTotal(19);
		page.setList(list);
		
		rs.setData(page);
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * 版本是否需要更新查询
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/versionQuery",method=RequestMethod.POST)
	@ApiParamRawType(AppVersionReq.class)
	@ResponseBody
	public ApiResult versionQuery(ApiParam<AppVersionReq> appParam){
		Result rs=Result.getErrorResult();
		try {
			rs = appVersionService.validVersionApp(appParam.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * 用户修改
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/userEdit",method=RequestMethod.POST)
	@ApiParamRawType(AppUserEditReq.class)
	@ResponseBody
	public ApiResult userEdit(ApiParam<AppUserEditReq> appParam){
		Result rs = Result.getSuccessResult();
		try {
			rs = systemUserService.appUpdateUser(appParam.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}

	/**
	 * 物料列表
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/materialList",method=RequestMethod.POST)
	@ApiParamRawType(AppQueryReq.class)
	@ResponseBody
	public ApiResult materialList(ApiParam<AppQueryReq> appParam){
		Result rs = Result.getSuccessResult();
		try {
			PaginationVO<AppMaterialResp> page = materielManageService.materialList(appParam.getBody());
			rs.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}

	/**
	 * 查询用户组成员
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/queryGroupUser",method=RequestMethod.POST)
	@ApiParamRawType(AppUserReq.class)
	@ResponseBody
	public ApiResult queryGroupUser(ApiParam<AppUserReq> appParam){
		Result rs = Result.getSuccessResult();
		try {
			SystemUserResp user = systemUserService.getUser(appParam.getHead().getUserId());
			if(StringUtils.equals(user.getIdentityTypes(), Constant.USER_SUPPLIER)){
				rs = supplierGroupService.supplierGroupCutover(user.getNcid());
			}
			if(StringUtils.equals(user.getIdentityTypes(), Constant.USER_CUSTOMER)){
				
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}

	/**
	 * 切换用户
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/userCutover",method=RequestMethod.POST)
	@ApiParamRawType(AppUserGroupReq.class)
	@ResponseBody
	public ApiResult userCutover(ApiParam<AppUserGroupReq> appParam){
		Result rs = Result.getSuccessResult();
		try {
			AppUserGroupReq req = appParam.getBody();
			req.setKey(appParam.getHead().getKey());
			req.setCurrId(appParam.getHead().getUserId());
			SystemUserResp user = systemUserService.getUser(appParam.getHead().getUserId());
			if(StringUtils.equals(user.getIdentityTypes(), Constant.USER_SUPPLIER)){
				rs = supplierGroupService.userCutover(req);
			}
			if(StringUtils.equals(user.getIdentityTypes(), Constant.USER_CUSTOMER)){
				
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * @Description 绑定手机号
	 * @author zhanggaohao
	 * @version 2017年5月11日 下午4:17:45
	 * @param appParam
	 * @return
	 */
	@RequestMapping(value="/bindPhone",method=RequestMethod.POST)
	@ApiParamRawType(AppUserReq.class)
	@ResponseBody
	public ApiResult bindPhone(ApiParam<AppUserReq> appParam){
		Result rs = Result.getSuccessResult();
		try {
			AppUserReq req = appParam.getBody();
			req.setId(appParam.getHead().getUserId());
			rs = systemUserService.bindPhone(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
	
	/**
	 * @Description 解绑手机号
	 * @author zhanggaohao
	 * @version 2017年5月11日 下午4:20:07
	 * @param appParam
	 * @return
	 */
	@RequestMapping(value="/unBindPhone",method=RequestMethod.POST)
	@ApiParamRawType(AppUserReq.class)
	@ResponseBody
	public ApiResult unBindPhone(ApiParam<AppUserReq> appParam){
		Result rs = Result.getSuccessResult();
		try {
			AppUserReq req = appParam.getBody();
			req.setId(appParam.getHead().getUserId());
			rs = systemUserService.unBindPhone(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
	
}
