package com.tianrui.web.api.android.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.api.android.imple.IAppStaticService;
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
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.AppResult;
import com.tianrui.web.smvc.ApiParamRawType;

/**
 * @annotation 客商APP接口
 * @author zhanggaohao
 */
@Controller
@RequestMapping("api/android/static_new")
public class AppStaticAction_new {

	private Logger log = LoggerFactory.getLogger(AppStaticAction_new.class);

	@Autowired
	private IAppStaticService appService;

	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ApiParamRawType(LoginUserParam.class)
	@ResponseBody
	public AppResult login(ApiParam<LoginUserParam> param){
		AppResult result = AppResult.getAppResult();
		try {
			result = appService.appLogin(param.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}

	@RequestMapping(value="/loginOut",method=RequestMethod.POST)
	@ApiParamRawType(LoginUserParam.class)
	@ResponseBody
	public AppResult loginOut(ApiParam<LoginUserParam> param){
		AppResult result = AppResult.getAppResult();
		try {
			param.getBody().setId(param.getHead().getKey());
			result = appService.appLoginOut(param.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value="/updatePwd",method=RequestMethod.POST)
	@ApiParamRawType(LoginUserParam.class)
	@ResponseBody
	public AppResult updatePwd(ApiParam<LoginUserParam> param){
		AppResult result = AppResult.getAppResult();
		try {
			param.getBody().setId(param.getHead().getUserId());
			result = appService.appUpdatePwd(param.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value="/bindPhoneNumber",method=RequestMethod.POST)
	@ApiParamRawType(LoginUserParam.class)
	@ResponseBody
	public AppResult bindPhoneNumber(ApiParam<LoginUserParam> param){
		AppResult result = AppResult.getAppResult();
		try {
			param.getBody().setId(param.getHead().getUserId());
			result = appService.appBindPhoneNumber(param.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value="/unBindPhoneNumber",method=RequestMethod.POST)
	@ApiParamRawType(LoginUserParam.class)
	@ResponseBody
	public AppResult unBindPhoneNumber(ApiParam<LoginUserParam> param){
		AppResult result = AppResult.getAppResult();
		try {
			param.getBody().setId(param.getHead().getUserId());
			result = appService.appUnBindPhoneNumber(param.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value="/home",method=RequestMethod.POST)
	@ApiParamRawType(HomePageParam.class)
	@ResponseBody
	public AppResult home(ApiParam<HomePageParam> param){
		AppResult result = AppResult.getAppResult();
		try {
			param.getBody().setUserId(param.getHead().getUserId());
			param.getBody().setNcId(param.getHead().getNcId());
			param.getBody().setIDType(param.getHead().getIDType());
			param.getBody().setSalesOrg(param.getHead().getSalesOrg());
			result = appService.home(param.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value="/bill/list",method=RequestMethod.POST)
	@ApiParamRawType(BillListParam.class)
	@ResponseBody
	public AppResult billList(ApiParam<BillListParam> param){
		AppResult result = AppResult.getAppResult();
		try {
			param.getBody().setUserId(param.getHead().getUserId());
			param.getBody().setNcId(param.getHead().getNcId());
			param.getBody().setIDType(param.getHead().getIDType());
			param.getBody().setSalesOrg(param.getHead().getSalesOrg());
			result = appService.billList(param.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value="/bill/detail",method=RequestMethod.POST)
	@ApiParamRawType(BillListParam.class)
	@ResponseBody
	public AppResult billDetail(ApiParam<BillListParam> param){
		AppResult result = AppResult.getAppResult();
		try {
			param.getBody().setIDType(param.getHead().getIDType());
			result = appService.billDetail(param.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value="/bill/delete",method=RequestMethod.POST)
	@ApiParamRawType(BillListParam.class)
	@ResponseBody
	public AppResult billDelete(ApiParam<BillListParam> param){
		AppResult result = AppResult.getAppResult();
		try {
			param.getBody().setUserId(param.getHead().getUserId());
			param.getBody().setNcId(param.getHead().getNcId());
			result = appService.billDelete(param.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value="/saveBill",method=RequestMethod.POST)
	@ApiParamRawType(BillSave.class)
	@ResponseBody
	public AppResult saveBill(ApiParam<BillSave> param){
		AppResult result = AppResult.getAppResult();
		try {
			param.getBody().setUserId(param.getHead().getUserId());
			param.getBody().setIDType(param.getHead().getIDType());
			param.getBody().setSalesOrg(param.getHead().getSalesOrg());
			result = appService.saveBill(param.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value="/sendCar",method=RequestMethod.POST)
	@ApiParamRawType(NoticeSave.class)
	@ResponseBody
	public AppResult saveNotice(ApiParam<NoticeSave> param){
		AppResult result = AppResult.getAppResult();
		try {
			param.getBody().setUserId(param.getHead().getUserId());
			param.getBody().setNcId(param.getHead().getNcId());
			param.getBody().setIDType(param.getHead().getIDType());
			result = appService.saveNotice(param.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value="/notice/list",method=RequestMethod.POST)
	@ApiParamRawType(NoticeListParam.class)
	@ResponseBody
	public AppResult noticeList(ApiParam<NoticeListParam> param){
		AppResult result = AppResult.getAppResult();
		try {
			param.getBody().setUserId(param.getHead().getUserId());
			param.getBody().setNcId(param.getHead().getNcId());
			param.getBody().setIDType(param.getHead().getIDType());
			param.getBody().setSalesOrg(param.getHead().getSalesOrg());
			result = appService.noticeList(param.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value="/notice/detail",method=RequestMethod.POST)
	@ApiParamRawType(NoticeListParam.class)
	@ResponseBody
	public AppResult noticeDetail(ApiParam<NoticeListParam> param){
		AppResult result = AppResult.getAppResult();
		try {
			param.getBody().setUserId(param.getHead().getUserId());
			param.getBody().setIDType(param.getHead().getIDType());
			result = appService.noticeDetail(param.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value="/notice/update",method=RequestMethod.POST)
	@ApiParamRawType(NoticeSave.class)
	@ResponseBody
	public AppResult noticeUpdate(ApiParam<NoticeSave> param){
		AppResult result = AppResult.getAppResult();
		try {
			param.getBody().setUserId(param.getHead().getUserId());
			param.getBody().setNcId(param.getHead().getNcId());
			param.getBody().setIDType(param.getHead().getIDType());
			result = appService.noticeUpdate(param.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value="/notice/cancel",method=RequestMethod.POST)
	@ApiParamRawType(NoticeListParam.class)
	@ResponseBody
	public AppResult noticeCancel(ApiParam<NoticeListParam> param){
		AppResult result = AppResult.getAppResult();
		try {
			param.getBody().setUserId(param.getHead().getUserId());
			param.getBody().setNcId(param.getHead().getNcId());
			param.getBody().setIDType(param.getHead().getIDType());
			result = appService.noticeCancel(param.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value="/my/vehicle",method=RequestMethod.POST)
	@ApiParamRawType(MyVehicleListParam.class)
	@ResponseBody
	public AppResult myVehicle(ApiParam<MyVehicleListParam> param){
		AppResult result = AppResult.getAppResult();
		try {
			param.getBody().setUserId(param.getHead().getUserId());
			param.getBody().setNcId(param.getHead().getNcId());
			param.getBody().setIDType(param.getHead().getIDType());
			result = appService.myVehicle(param.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value="/my/pn",method=RequestMethod.POST)
	@ApiParamRawType(MyPnListParam.class)
	@ResponseBody
	public AppResult myPn(ApiParam<MyPnListParam> param){
		AppResult result = AppResult.getAppResult();
		try {
			param.getBody().setUserId(param.getHead().getUserId());
			param.getBody().setNcId(param.getHead().getNcId());
			param.getBody().setIDType(param.getHead().getIDType());
			param.getBody().setSalesOrg(param.getHead().getSalesOrg());
			result = appService.myPn(param.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value="/my/pn/detail",method=RequestMethod.POST)
	@ApiParamRawType(MyPnListParam.class)
	@ResponseBody
	public AppResult myPnDetail(ApiParam<MyPnListParam> param){
		AppResult result = AppResult.getAppResult();
		try {
			result = appService.myPnDetail(param.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value="/save/driver",method=RequestMethod.POST)
	@ApiParamRawType(DriverSave.class)
	@ResponseBody
	public AppResult saveDriver(ApiParam<DriverSave> param){
		AppResult result = AppResult.getAppResult();
		try {
			param.getBody().setUserId(param.getHead().getUserId());
			result = appService.saveDriver(param.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value="/vehicle/search",method=RequestMethod.POST)
	@ApiParamRawType(SearchKeyParam.class)
	@ResponseBody
	public AppResult vehicleSearch(ApiParam<SearchKeyParam> param){
		AppResult result = AppResult.getAppResult();
		try {
			result = appService.vehicleSearch(param.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value="/driver/search",method=RequestMethod.POST)
	@ApiParamRawType(SearchKeyParam.class)
	@ResponseBody
	public AppResult driverSearch(ApiParam<SearchKeyParam> param){
		AppResult result = AppResult.getAppResult();
		try {
			result = appService.driverSearch(param.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value="/material/search",method=RequestMethod.POST)
	@ApiParamRawType(SearchKeyParam.class)
	@ResponseBody
	public AppResult materialSearch(ApiParam<SearchKeyParam> param){
		AppResult result = AppResult.getAppResult();
		try {
			result = appService.materialSearch(param.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}

	/**
	 * 查询用户组成员
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/group/user",method=RequestMethod.POST)
	@ApiParamRawType(LoginUserParam.class)
	@ResponseBody
	public AppResult queryGroupUser(ApiParam<LoginUserParam> param){
		AppResult result = AppResult.getAppResult();
		try {
			param.getBody().setUserId(param.getHead().getUserId());
			param.getBody().setNcId(param.getHead().getNcId());
			param.getBody().setIDType(param.getHead().getIDType());
			result = appService.queryGroupUser(param.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}

	/**
	 * 切换用户
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/user/cutover",method=RequestMethod.POST)
	@ApiParamRawType(LoginUserParam.class)
	@ResponseBody
	public AppResult userCutover(ApiParam<LoginUserParam> param){
		AppResult result = AppResult.getAppResult();
		try {
			param.getBody().setNcId(param.getHead().getNcId());
			param.getBody().setIDType(param.getHead().getIDType());
			param.getBody().setKey(param.getHead().getKey());
			result = appService.userCutover(param.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}

	/**
	 * 常用的车辆
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/user/vehicle",method=RequestMethod.POST)
	@ApiParamRawType(MyVehicleListParam.class)
	@ResponseBody
	public AppResult userVehicle(ApiParam<MyVehicleListParam> param){
		AppResult result = AppResult.getAppResult();
		try {
			param.getBody().setUserId(param.getHead().getUserId());
			result = appService.userVehicle(param.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}

	/**
	 * 常用的司机
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/user/driver",method=RequestMethod.POST)
	@ApiParamRawType(MyVehicleListParam.class)
	@ResponseBody
	public AppResult userDriver(ApiParam<MyVehicleListParam> param){
		AppResult result = AppResult.getAppResult();
		try {
			param.getBody().setUserId(param.getHead().getUserId());
			result = appService.userDriver(param.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	/**
	 * 版本是否需要更新查询
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/version",method=RequestMethod.POST)
	@ApiParamRawType(AppVersionParam.class)
	@ResponseBody
	public AppResult versionQuery(ApiParam<AppVersionParam> param){
		AppResult result = AppResult.getAppResult();
		try {
			result = appService.appVersion(param.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	/**
	 * 常用车辆和司机
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/common/vcAndDr",method=RequestMethod.POST)
	@ApiParamRawType(MyVehicleListParam.class)
	@ResponseBody
	public AppResult vcAndDr(ApiParam<MyVehicleListParam> param){
		AppResult result = AppResult.getAppResult();
		try {
			result = appService.vcAndDr(param.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
}
