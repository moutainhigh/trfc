package com.tianrui.web.api.android.action;

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

import com.tianrui.api.intf.businessManage.poundNoteMaintain.IPoundNoteService;
import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseApplicationService;
import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseArriveService;
import com.tianrui.api.intf.businessManage.salesManage.ISalesApplicationService;
import com.tianrui.api.intf.businessManage.salesManage.ISalesArriveService;
import com.tianrui.api.intf.system.auth.ISystemUserService;
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
import com.tianrui.api.req.system.auth.UserReq;
import com.tianrui.api.resp.businessManage.app.AppDriverResp;
import com.tianrui.api.resp.businessManage.app.AppMsgCountResp;
import com.tianrui.api.resp.businessManage.app.AppMsgResp;
import com.tianrui.api.resp.businessManage.app.AppNoticeOrderResp;
import com.tianrui.api.resp.businessManage.app.AppOrderResp;
import com.tianrui.api.resp.businessManage.app.AppOverListDetailResp;
import com.tianrui.api.resp.businessManage.app.AppVehicleInFactoryResp;
import com.tianrui.api.resp.businessManage.app.AppVehicleResp;
import com.tianrui.api.resp.businessManage.app.AppVersionResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
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
	 * 问题： 
	 * 
	 * 用户分类：客户/供应商
	 * 
	 * 根据id查询客户获取实体
	 * 
	 * 根据实体判定用户身份类型
	 * 
	 * 根据用户身份类型查询相应结果信息
	 * 
	 * 
	 * 
	 * 
	 * 以上问题解决方案：
	 * 
	 * 用户实体及用户表     增加身份类型字段 identityTypes
	 * 
	 */
	
	
	
	/**
	 * 订单列表
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/orderPage",method=RequestMethod.POST)
	@ApiParamRawType(AppOrderReq.class)
	@ResponseBody
	public ApiResult orderPage(ApiParam<AppOrderReq> appParam){
		Result rs = Result.getSuccessResult();
		try {
			AppOrderReq req = appParam.getBody();
			PaginationVO<AppOrderResp> page = null;
			SystemUserResp user = systemUserService.get(appParam.getHead().getUserId());
			if(user != null){
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
			SystemUserResp user = systemUserService.get(appParam.getHead().getUserId());
			if(user != null){
				if(StringUtils.equals(user.getIdentityTypes(), Constant.USER_SUPPLIER)){
					rs.setData(purchaseApplicationService.appToDetail(req));
				}
				if(StringUtils.equals(user.getIdentityTypes(), Constant.USER_CUSTOMER)){
					rs.setData(salesApplicationService.appToDetail(req));
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
			SystemUserResp user = systemUserService.get(appParam.getHead().getUserId());
			PaginationVO<AppNoticeOrderResp> page = null;
			if(user != null){
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
			SystemUserResp user = systemUserService.get(appParam.getHead().getUserId());
			if(user != null){
				if(StringUtils.equals(user.getIdentityTypes(), Constant.USER_SUPPLIER)){
					rs = purchaseArriveService.appToDetail(appParam.getBody());
				}
				if(StringUtils.equals(user.getIdentityTypes(), Constant.USER_CUSTOMER)){
					rs = salesArriveService.appToDetail(appParam.getBody());
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
			PaginationVO<AppPoundOrderResp> page = null;
			SystemUserResp user = systemUserService.get(appParam.getHead().getUserId());
			if(user != null){
				req.setIdentityTypes(user.getIdentityTypes());
				page = poundNoteService.appToPage(req);
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
		AppPoundOrderReq req = appParam.getBody();
		Result rs=Result.getSuccessResult();
		AppPoundOrderResp resp =new AppPoundOrderResp();
		rs.setData(resp);
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
	public ApiResult vehicleList(ApiParam<AppQueryReq> req){
		AppQueryReq userReq =req.getBody();
		Result rs=Result.getSuccessResult();
		
		
		PaginationVO<AppVehicleResp> page=new PaginationVO<AppVehicleResp>();
		
		List<AppVehicleResp> list =new ArrayList<AppVehicleResp>();
		AppVehicleResp item =new AppVehicleResp();
		item.setId(UUIDUtil.getId());
		item.setVehicle("临A12345");
		list.add(item);
		
		page.setPageNo(1);
		page.setTotal(19);
		page.setList(list);
		
		
		rs.setData(page);
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
	public ApiResult driverList(ApiParam<AppQueryReq> req){
		AppQueryReq userReq =req.getBody();
		PaginationVO<AppDriverResp> page=new PaginationVO<AppDriverResp>();
		
		Result rs=Result.getSuccessResult();
		List<AppDriverResp> list =new ArrayList<AppDriverResp>();
		AppDriverResp item =new AppDriverResp();
		item.setId(UUIDUtil.getId());
		item.setIdNo("4104821987xxxx6756");
		item.setName("李先生");
		list.add(item);
		
		page.setPageNo(1);
		page.setTotal(19);
		page.setList(list);
		
		rs.setData(page);
		return ApiResult.valueOf(rs);
	}
	/**
	 * 派单
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/orderCrate",method=RequestMethod.POST)
	@ApiParamRawType(AppOrderSaveReq.class)
	@ResponseBody
	public ApiResult orderCrate(ApiParam<AppOrderSaveReq> req){
		AppOrderSaveReq userReq =req.getBody();
		Result rs=Result.getSuccessResult();
		
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
	public ApiResult driverCreate(ApiParam<AppDriverSaveReq> req){
		AppDriverSaveReq userReq =req.getBody();
		Result rs=Result.getSuccessResult();

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
	public ApiResult vehicleInFactory(ApiParam<AppOrderReq> req){
		AppOrderReq userReq =req.getBody();
		PaginationVO<AppVehicleInFactoryResp> page=new PaginationVO<AppVehicleInFactoryResp>();
		Result rs=Result.getSuccessResult();
		List<AppVehicleInFactoryResp> list =new ArrayList<AppVehicleInFactoryResp>();
		AppVehicleInFactoryResp item =new AppVehicleInFactoryResp();
		item.setMaterName("水泥525");
		item.setVehicleCount("20");
		list.add(item);
		
		page.setPageNo(1);
		page.setTotal(19);
		page.setList(list);
		
		rs.setData(page);
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
		AppQueryReq userReq =req.getBody();
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
		AppQueryReq userReq =req.getBody();
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
	public ApiResult versionQuery(ApiParam<AppVersionReq> req){
		AppVersionReq userReq =req.getBody();
		Result rs=Result.getSuccessResult();
		AppVersionResp item =new AppVersionResp();
		item.setUpdateFlag("0");
		rs.setData(item);
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
	public ApiResult userEdit(ApiParam<AppUserEditReq> req){
		AppUserEditReq userReq =req.getBody();
		Result rs=Result.getSuccessResult();
		
		return ApiResult.valueOf(rs);
	}
	
	
}
