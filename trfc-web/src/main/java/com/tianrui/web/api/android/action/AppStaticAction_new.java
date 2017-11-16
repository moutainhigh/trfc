package com.tianrui.web.api.android.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.api.android.imple.IAppStaticService;
import com.tianrui.api.req.android.BillListParam;
import com.tianrui.api.req.android.BillSave;
import com.tianrui.api.req.android.HomePageParam;
import com.tianrui.api.req.android.LoginUserParam;
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
			param.getBody().setUserId(param.getHead().getNcId());
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
			param.getBody().setUserId(param.getHead().getNcId());
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
	
	@RequestMapping(value="/saveNotice",method=RequestMethod.POST)
	@ApiParamRawType(BillSave.class)
	@ResponseBody
	public AppResult saveNotice(ApiParam<BillSave> param){
		AppResult result = AppResult.getAppResult();
		try {
			param.getBody().setUserId(param.getHead().getUserId());
			param.getBody().setIDType(param.getHead().getIDType());
			param.getBody().setSalesOrg(param.getHead().getSalesOrg());
			result = appService.saveNotice(param.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
}
