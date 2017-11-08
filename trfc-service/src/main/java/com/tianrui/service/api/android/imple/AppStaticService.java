package com.tianrui.service.api.android.imple;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.api.android.imple.IAppStaticService;
import com.tianrui.api.intf.system.auth.ISystemUserService;
import com.tianrui.api.req.android.HomePageParam;
import com.tianrui.api.req.android.LoginUserParam;
import com.tianrui.api.resp.android.LoginUserVo;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.service.bean.system.auth.SystemUser;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.cache.CacheHelper;
import com.tianrui.service.cache.CacheModule;
import com.tianrui.service.mapper.system.auth.SystemUserMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.AppResult;

@Service
public class AppStaticService implements IAppStaticService {

	@Autowired
	private SystemUserMapper userMapper;
	@Autowired
	private CacheClient cacheClient;
	@Autowired
	private ISystemUserService systemUserService;
	
	@Transactional
	@Override
	public AppResult appLogin(LoginUserParam param) throws Exception {
		AppResult result = AppResult.getInstance();
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
	public AppResult appUpdatePwd(LoginUserParam param) {
		AppResult result = AppResult.getInstance();
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
		AppResult result = AppResult.getInstance();
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
		AppResult result = AppResult.getInstance();
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
		AppResult result = AppResult.getInstance();
		if (param != null && StringUtils.isNotBlank(param.getUserId())
				&& StringUtils.isNotBlank(param.getIDType())) {
			switch (param.getIDType()) {
			//客户
			case "1":
				result = customerHome(param);
				break;
			//供应商				
			case "2":
//				result = supplierHome(param);
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

	private AppResult customerHome(HomePageParam param) {
		AppResult result = AppResult.getInstance();
		
		return result;
	}

}
