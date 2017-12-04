package com.tianrui.service.api.android.imple;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tianrui.api.intf.api.android.imple.IAppStaticUserService;
import com.tianrui.api.intf.system.auth.ISystemUserService;
import com.tianrui.api.req.android.LoginUserParam;
import com.tianrui.api.resp.android.LoginUserVo;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.service.bean.system.auth.Organization;
import com.tianrui.service.bean.system.auth.SystemUser;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.cache.CacheHelper;
import com.tianrui.service.cache.CacheModule;
import com.tianrui.service.mapper.system.auth.OrganizationMapper;
import com.tianrui.service.mapper.system.auth.SystemUserclientMapper;
import com.tianrui.service.mapper.system.auth.SystemUsersupplierMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.AppResult;

@Service
public class AppStaticUserService implements IAppStaticUserService {
	@Autowired
	private CacheClient cacheClient;
	@Autowired
	SystemUserclientMapper userclientMapper;
	@Autowired
	SystemUsersupplierMapper usersupplierMapper;
	@Autowired
	private OrganizationMapper organizationMapper;
	@Transactional
	@Override
	public AppResult appLogin(LoginUserParam param) throws Exception {
		AppResult result = AppResult.getAppResult();
		if (param != null && StringUtils.isNotBlank(param.getAccount())
				&& StringUtils.isNotBlank(param.getPwd())
				&& StringUtils.isNotBlank(param.getIDType())) {
//			SystemUser user = userMapper.getByAccount(param.getAccount(), param.getIDType());
			SystemUser user = usersupplierMapper.getByAccount(param.getAccount());
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
		SystemUserResp resp = get(userId, true);
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
		usersupplierMapper.updateByPrimaryKeySelective(bean);
	}
	
	

	@Override
	public SystemUserResp getUser(String id) throws Exception{
		SystemUser db = null;
		//主键不能为空
		if (StringUtils.isNotBlank(id)) {
			db = usersupplierMapper.selectByPrimaryKey(id);
			db.setIdentityTypes("2");
			db.setNcid(db.getId());
		}
		return copySystemUserBean2Resp(db);
	}
	@Override
	public SystemUserResp get(String id, boolean isFlush) throws Exception {
		SystemUserResp user = null;
		if (StringUtils.isNotBlank(id)) {
			String key = CacheHelper.buildKey(CacheModule.MEMBERVO, id);
			if (isFlush) {
				user = getUser(id);
				cacheClient.saveObject(key, user);
			} else {
				user = get(id);
			}
		}
		return user;
	}
	@Override
	public SystemUserResp get(String id) throws Exception{
		SystemUserResp user = null;
		if (StringUtils.isNotBlank(id)) {
			String key = CacheHelper.buildKey(CacheModule.MEMBERVO, id);
			user = cacheClient.getObj(key, SystemUserResp.class);
			if (user == null) {
				user = getUser(id);
				cacheClient.saveObject(key, user);
			}
		}
		return user;
	}
	//包装返回对象
	private SystemUserResp copySystemUserBean2Resp(SystemUser bean) throws Exception {
		SystemUserResp resp = null;
		if (bean != null) {
			resp =new SystemUserResp();
			PropertyUtils.copyProperties(resp, bean);
			if(StringUtils.isNotBlank(bean.getOrgid())){
				Organization org = organizationMapper.selectByPrimaryKey(bean.getOrgid());
				if(org != null){
					resp.setOrgName(org.getName());
				}
			}
		}
		return resp;
	}
}
