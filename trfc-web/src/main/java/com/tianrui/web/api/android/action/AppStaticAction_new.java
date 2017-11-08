package com.tianrui.web.api.android.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.api.android.imple.IAppStaticService;
import com.tianrui.api.req.android.HomePageParam;
import com.tianrui.api.req.android.LoginUserParam;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.AppResult;
import com.tianrui.web.smvc.ApiParamRawType;

/**
 * 用户验证相关
 * @author lixp 2017年1月7日 09:24:36
 *
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
		AppResult result = AppResult.getInstance();
		try {
			result = appService.appLogin(param.getBody());
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
		AppResult result = AppResult.getInstance();
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
		AppResult result = AppResult.getInstance();
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
		AppResult result = AppResult.getInstance();
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
		AppResult result = AppResult.getInstance();
		try {
			param.getBody().setUserId(param.getHead().getUserId());
			param.getBody().setIDType(param.getHead().getIDType());
			result = appService.home(param.getBody());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
}
