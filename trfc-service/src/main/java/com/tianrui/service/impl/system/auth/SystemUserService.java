package com.tianrui.service.impl.system.auth;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.system.auth.ISystemRolePermissionsService;
import com.tianrui.api.intf.system.auth.ISystemUserService;
import com.tianrui.api.req.businessManage.app.AppUserEditReq;
import com.tianrui.api.req.system.auth.AppUserReq;
import com.tianrui.api.req.system.auth.SystemUserPswdReq;
import com.tianrui.api.req.system.auth.SystemUserQueryReq;
import com.tianrui.api.req.system.auth.SystemUserSaveReq;
import com.tianrui.api.req.system.auth.UserReq;
import com.tianrui.api.resp.system.auth.AppUserResp;
import com.tianrui.api.resp.system.auth.SystemRoleMenuResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.service.bean.system.auth.Organization;
import com.tianrui.service.bean.system.auth.SystemUser;
import com.tianrui.service.cache.CacheClient;
import com.tianrui.service.cache.CacheHelper;
import com.tianrui.service.cache.CacheModule;
import com.tianrui.service.mapper.system.auth.OrganizationMapper;
import com.tianrui.service.mapper.system.auth.SystemUserMapper;
import com.tianrui.smartfactory.common.constants.BusinessConstants;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.Md5Utils;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class SystemUserService implements ISystemUserService {
	@Autowired
	SystemUserMapper userMapper;
	@Autowired
	OrganizationMapper organizationMapper;
	@Autowired
	CacheClient cacheClient;
    @Autowired
    ISystemRolePermissionsService systemRolePermissionsService;
	
	@Override
	public Result apiLogin(UserReq req) throws Exception {
		Result rs =Result.getParamErrorResult();
		if (req!=null && StringUtils.isNotBlank(req.getPswd()) && StringUtils.isNotBlank(req.getAccount())) {
			SystemUserQueryReq query =new SystemUserQueryReq();
			query.setAccount(req.getAccount());
			List<SystemUser> list = userMapper.selectByCondition(query);
			if (CollectionUtils.isNotEmpty(list)) {
				if (StringUtils.equals(list.get(0).getPassword(),req.getPswd() )) {
					if ( list.get(0).getIsvalid() == BusinessConstants.USER_VALID_BYTE ) {
						if(list.get(0).getIslock() == BusinessConstants.USER_UNLOCK_BYTE) {
							if(StringUtils.equals(list.get(0).getIdentityTypes(), BusinessConstants.USER_PT_STR)) {
								SystemUserResp resp = new SystemUserResp();
								resp.setId(list.get(0).getId());
								resp.setName(list.get(0).getName());
								rs.setData(resp);
								rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
							}else{
								rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR11);
							}
						}else{
							rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR3);
						}
					}else{
						rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR5);
					}
				}else{
					rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR2);
				}
			}else{
				rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR1);
			}
		}
		
		return rs;
	}

	@Override
	public Result page(SystemUserQueryReq req) throws Exception {
		Result rs =Result.getSuccessResult();
		PaginationVO<SystemUserResp> page = null;
		if (req != null) {
			page = new PaginationVO<SystemUserResp>();
			long count = userMapper.countByCondition(req);
			if (count > 0) {
				req.setStart((req.getPageNo()-1)*req.getPageSize());
				req.setLimit(req.getPageSize());
				List<SystemUser> list = userMapper.selectByCondition(req);
				page.setList(copySystemUserBeanList2RespList(list));
			}
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
			page.setTotal(count);
		}
		rs.setData(page);
		return rs;
	}

	@Override
	public Result detail(SystemUserQueryReq req) throws Exception {
		Result rs =Result.getParamErrorResult();
		//主键不能为空
		if ( req !=null && StringUtils.isNotBlank(req.getId())) {
			SystemUser db=userMapper.selectByPrimaryKey(req.getId());
			if ( db !=null ) {
				rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				rs.setData(copySystemUserBean2Resp(db));
			}else{
				rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR6);
			}
		}
		return rs;
	}

	@Override
	public Result addUser(SystemUserSaveReq req) throws Exception {
		Result rs =Result.getParamErrorResult();
		//参数不能为空交验
		if ( validateReq(req,1) ) {
			//用户编码不能重复 
			SystemUserQueryReq query =new SystemUserQueryReq();
			query.setAccount(req.getAccount());
			List<SystemUser> list =userMapper.selectByCondition(query);
			if ( CollectionUtils.isEmpty(list) ) {
				//登录账户不能 重复
				query =new SystemUserQueryReq();
				query.setCode(req.getCode());
				list =userMapper.selectByCondition(query);
				if ( CollectionUtils.isEmpty(list) ) {
					//保存数据
					SystemUser save =new SystemUser();
					save.setId(UUIDUtil.getId());
					save.setOrgid(Constant.ORG_ID);
					save.setCode(req.getCode());
					save.setName(req.getName());
					save.setAccount(req.getAccount());
					//是否有效
					if ( StringUtils.equals(req.getIsvalid(), BusinessConstants.USER_VALID_STR)) {
						save.setIsvalid(BusinessConstants.USER_VALID_BYTE);
					}else{
						save.setIsvalid(BusinessConstants.USER_INVALID_BYTE);
					}
					save.setIslock(BusinessConstants.USER_INVALID_BYTE);
					save.setIdentityTypes("3");
					save.setSource("0");
					save.setPassword(Md5Utils.MD5(req.getPassword()));
					save.setCreatetime(System.currentTimeMillis());
					save.setModifier(req.getCurrUId());
					save.setModifytime(System.currentTimeMillis());
					save.setCreator(req.getCurrUId());
					userMapper.insert(save);
					rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}else{
					rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR8);
				}
			}else{
				rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR7);
			}
		}
		return rs;
	}

	@Override
	public Result editUser(SystemUserSaveReq req) throws Exception {
		Result rs =Result.getParamErrorResult();
		//参数不能为空交验
		if ( validateReq(req,2) ) {
			//用户需要能查询到
			SystemUser db=userMapper.selectByPrimaryKey(req.getId());
			if ( db !=null ) {
				//保存数据
				SystemUser update =new SystemUser();
				update.setId(UUIDUtil.getId());
				update.setPassword(Md5Utils.MD5(req.getPassword()));
				update.setRemark(req.getRemark());
				if (StringUtils.equals(req.getIsvalid(), BusinessConstants.USER_VALID_STR)) {
					update.setIsvalid(BusinessConstants.USER_VALID_BYTE);
				}else{
					update.setIsvalid(BusinessConstants.USER_INVALID_BYTE);
				}
				update.setModifier(req.getCurrUId());
				update.setModifytime(System.currentTimeMillis());
				userMapper.updateByPrimaryKeySelective(update);
				rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR1);
			}
		}
		return rs;
	}

	@Override
	public Result delUser(SystemUserQueryReq req) throws Exception {
		Result rs =Result.getParamErrorResult();
		//参数不能为空交验
		if ( req !=null && StringUtils.isNotBlank(req.getId()) ) {
			//是否能查到数据
			SystemUser db=userMapper.selectByPrimaryKey(req.getId());
			if (db !=null) {
				//TODO关联关系删除
				userMapper.deleteByPrimaryKey(req.getId());
				rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR1);
			}
		}
		return rs;
	}

	@Override
	public Result resetUser(SystemUserQueryReq req) throws Exception {
		Result rs =Result.getParamErrorResult();
		//参数不能为空交验
		if ( req !=null && StringUtils.isBlank(req.getId()) ) {
			//是否能查到数据
			SystemUser db=userMapper.selectByPrimaryKey(req.getId());
			if (db !=null) {
				//重置密码
				SystemUser update =new SystemUser();
				update.setId(req.getId());
				update.setPassword(Md5Utils.MD5(Constant.PASSWORD_INIT));
				userMapper.updateByPrimaryKeySelective(update);
				rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR1);
			}
		}
		return rs;
	}

	@Override
	public Result login(UserReq req) throws Exception {
		Result rs =Result.getParamErrorResult();
		//参数不能为空
		if (req!=null && StringUtils.isNotBlank(req.getPswd()) && StringUtils.isNotBlank(req.getAccount())) {
			SystemUserQueryReq query =new SystemUserQueryReq();
			query.setAccount(req.getAccount());
			List<SystemUser> list = userMapper.selectByCondition(query);
			if ( CollectionUtils.isNotEmpty(list) ) {
				//验证密码
				if (StringUtils.equals(list.get(0).getPassword(),req.getPswd() )) {
					if ( list.get(0).getIsvalid()==BusinessConstants.USER_VALID_BYTE ) {
						if(list.get(0).getIslock() == BusinessConstants.USER_UNLOCK_BYTE) {
							if(StringUtils.equals(list.get(0).getIdentityTypes(), BusinessConstants.USER_PT_STR)) {
								cumulativeLoginCount(list.get(0));
								rs.setData(copySystemUserBean2Resp(list.get(0)));
								rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
							}else{
								rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR11);
							}
						}else{
							rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR3);
						}
					}else{
						rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR2);
					}
				}else{
					rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR2);
				}
			}else{
				rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR1);
			}
		}
		return rs;
	}
	
	@Override
	public Result editPswd(SystemUserPswdReq req) throws Exception {
		Result rs =Result.getParamErrorResult();
		//参数不能为空交验
		if ( req !=null && StringUtils.isBlank(req.getId())
				&& StringUtils.isBlank(req.getOldPswd())
				&& StringUtils.isBlank(req.getNewPswd()) ) {
			//是否能查到数据
			SystemUser db=userMapper.selectByPrimaryKey(req.getId());
			if (db !=null) {
				if ( StringUtils.equals(db.getPassword(), req.getOldPswd()) ) {
					//修改密码
					SystemUser update =new SystemUser();
					update.setId(req.getId());
					update.setPassword(req.getNewPswd());
					userMapper.updateByPrimaryKeySelective(update);
					rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}else{
					//原密码错误
					rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR9);
				}
			}else{
				rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR1);
			}
		}
		return rs;
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
	//包装返回对象集合  
	private List<SystemUserResp> copySystemUserBeanList2RespList(List<SystemUser> list) throws Exception {
		List<SystemUserResp> resp = null;
		if (CollectionUtils.isNotEmpty(list)) {
			resp = new ArrayList<SystemUserResp>();
			for(SystemUser item:list) {
				resp.add(copySystemUserBean2Resp(item));
			}
		}
		return resp;
	}
	/**
	 * 验证用户保存是否为空
	 * @param req
	 * @param type  1:新增  2:修改 3:删除
	 * @return
	 */
	private boolean validateReq(SystemUserSaveReq req,int type) {
		boolean rs=false;
		if (req!=null) {
			if (   type==1 
					&& StringUtils.isNotBlank(req.getCode())
					&& StringUtils.isNotBlank(req.getName()) 
					&& StringUtils.isNotBlank(req.getAccount()) 
					&& StringUtils.isNotBlank(req.getPassword()) 
					&& StringUtils.isNotBlank(req.getIsvalid()) ) {
				rs =true;
			}else if (type==2
					&& StringUtils.isNotBlank(req.getId())
					&& StringUtils.isNotBlank(req.getPassword())
					&& StringUtils.isNotBlank(req.getIsvalid())
					&& StringUtils.isNotBlank(req.getRemark())) {
				rs =true;
			}
		}
		return rs;
	}
	
	@Override
	public SystemUserResp getUser(String id) throws Exception{
		SystemUser db = null;
		//主键不能为空
		if (StringUtils.isNotBlank(id)) {
			db = userMapper.selectByPrimaryKey(id);
		}
		return copySystemUserBean2Resp(db);
	}

	@Override
	public List<SystemUserResp> autoCompleteSearch(SystemUserQueryReq req) throws Exception {
		List<SystemUserResp> resps = new ArrayList<SystemUserResp>();
		List<SystemUser> list = userMapper.autoCompleteSearch(req);
		for(SystemUser su : list) {
			SystemUserResp resp = new SystemUserResp();
			PropertyUtils.copyProperties(resp,su);
			resps.add(resp);
		}
		return resps;
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

	@Override
	public Result appLogin(AppUserReq req) throws Exception {
		Result result = Result.getParamErrorResult();
		if (req != null && StringUtils.isNotBlank(req.getAccount())
				&& StringUtils.isNotBlank(req.getPswd())) {
			SystemUserQueryReq query =new SystemUserQueryReq();
			query.setAppAccount(req.getAccount());
			List<SystemUser> list = userMapper.selectByCondition(query);
			if (CollectionUtils.isNotEmpty(list)) {
				//验证密码
				if (StringUtils.equals(list.get(0).getPassword(), req.getPswd())) {
					if (list.get(0).getIsvalid()==BusinessConstants.USER_VALID_BYTE ) {
						if(list.get(0).getIslock() == BusinessConstants.USER_UNLOCK_BYTE) {
							if(StringUtils.equals(list.get(0).getIdentityTypes(), BusinessConstants.USER_KS_CUSTOMER_STR)
									|| StringUtils.equals(list.get(0).getIdentityTypes(), BusinessConstants.USER_KS_SUPPLIER_STR)) {
								//累计登录次数
								cumulativeLoginCount(list.get(0));
								
								String tokenId =UUIDUtil.getId();
								SystemUserResp user = get(list.get(0).getId(), true);
								//缓存默认保存一天
								String key = CacheHelper.buildKey(CacheModule.MEMBERLOGIN_APP, tokenId);
								cacheClient.saveObject(key, user, 7*24*60*60);
								user.setTokenId(tokenId);
								AppUserResp resp = new AppUserResp();
								resp.setId(user.getId());
								resp.setNcid(user.getNcid());
								resp.setToken(user.getTokenId());
								resp.setUserName(user.getName());
								resp.setMobile(user.getMobilePhone());
								resp.setOrgid(user.getOrgid());
								resp.setOrgName(user.getOrgName());
								resp.setIdentityTypes(user.getIdentityTypes());
								result.setData(resp);
								result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
							}else{
								result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR12);
							}
						}else{
							result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR3);
						}
					}else{
						result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR5);
					}
				}else{
					result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR2);
				}
			}else{
				result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR1);
			}
		}
		return result;
	}

	@Override
	public Result appUpdatePswd(AppUserReq req) throws Exception {
		Result result = Result.getParamErrorResult();
		if (req != null && StringUtils.isNotBlank(req.getId())
				&& StringUtils.isNotBlank(req.getPswd())) {
			SystemUser db=userMapper.selectByPrimaryKey(req.getId());
			if(db != null){
				if ( StringUtils.equals(db.getPassword(), req.getPswd()) ) {
					//修改密码
					SystemUser update =new SystemUser();
					update.setId(req.getId());
					update.setPassword(req.getNewPswd());
					if(userMapper.updateByPrimaryKeySelective(update) > 0){
						result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
					}else{
						result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR10);
					}
				}else{
					//原密码错误
					result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR9);
				}
			}else{
				result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR1);
			}
		}
		return result;
	}

	@Override
	public Result appUpdateUser(AppUserEditReq req) {
		Result result = Result.getParamErrorResult();
		if(req != null
				&& StringUtils.isNotBlank(req.getUserId())
				&& StringUtils.isNotBlank(req.getNickName())){
			SystemUser user = new SystemUser();
			user.setId(req.getUserId());
			user.setName(req.getNickName());
			if(userMapper.updateByPrimaryKeySelective(user) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	public Result queryAllUser(String orgid) {
		Result result = Result.getParamErrorResult();
		if(StringUtils.isNotBlank(orgid)){
			List<SystemUser> list = userMapper.queryAllUserByOrgId(orgid);
			result.setData(list);
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return result;
	}
	
	@Override
	public Result userCutover(String key, String ncid, String identityTypes) throws Exception{
		Result result = Result.getParamErrorResult();
		if(StringUtils.isNotBlank(key) && StringUtils.isNotBlank(ncid) && StringUtils.isNotBlank(identityTypes)){
			SystemUser systemUser = userMapper.selectByNcIdAndIdentityTypes(ncid, identityTypes);
			if(systemUser != null){
				//cumulativeLoginCount(systemUser);
				SystemUserResp user = copySystemUserBean2Resp(systemUser);
				String tokenId =UUIDUtil.getId();
				user.setTokenId(tokenId);
				cacheClient.saveObject(key, user, 7*24*60*60);
				AppUserResp resp = new AppUserResp();
				resp.setId(user.getId());
				resp.setNcid(user.getNcid());
				resp.setToken(tokenId);
				resp.setUserName(user.getName());
				resp.setMobile(user.getMobilePhone());
				resp.setOrgid(user.getOrgid());
				resp.setOrgName(user.getOrgName());
				resp.setIdentityTypes(user.getIdentityTypes());
				result.setData(resp);
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}
		}
		return result;
	}

	@Override
	public Result bindPhone(AppUserReq req) {
		Result result = Result.getParamErrorResult();
		if(req != null && StringUtils.isNotBlank(req.getId())
				&& StringUtils.isNotBlank(req.getMobilePhone())){
			if(userMapper.validPhoneIsOne(req.getMobilePhone()) == null){
				SystemUser bean = new SystemUser();
				bean.setId(req.getId());
				bean.setMobilePhone(req.getMobilePhone());
				bean.setModifier(req.getId());
				bean.setModifytime(System.currentTimeMillis());
				if(userMapper.updateByPrimaryKeySelective(bean) == 1){
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}else{
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}else{
				result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR13);
			}
		}
		return result;
	}
	
	@Override
	public Result unBindPhone(AppUserReq req) {
		Result result = Result.getParamErrorResult();
		if(req != null && StringUtils.isNotBlank(req.getId())){
			SystemUser bean = new SystemUser();
			bean.setId(req.getId());
			bean.setMobilePhone("");
			bean.setModifier(req.getId());
			bean.setModifytime(System.currentTimeMillis());
			if(userMapper.updateByPrimaryKeySelective(bean) == 1){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Override
	public Result resetPwd(SystemUserSaveReq req) {
		Result result = Result.getParamErrorResult();
		if(req != null && StringUtils.isNotBlank(req.getId())
				&& StringUtils.isNotBlank(req.getPassword())){
			SystemUser bean = new SystemUser();
			bean.setId(req.getId());
			bean.setPassword(Md5Utils.MD5(req.getPassword()));
			bean.setModifier(req.getId());
			bean.setModifytime(System.currentTimeMillis());
			if(userMapper.updateByPrimaryKeySelective(bean) == 1){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	//累加登录次数
	private void cumulativeLoginCount(SystemUser user){
		SystemUser bean = new SystemUser();
		bean.setId(user.getId());
		if(user.getLogincount() != null){
			bean.setLogincount(user.getLogincount() + 1);
		}else{
			bean.setLogincount(1);
		}
		userMapper.updateByPrimaryKeySelective(bean);
	}

    @Override
    public Result updatePwd(SystemUserPswdReq req) {
        Result result = Result.getParamErrorResult();
        if (req != null && StringUtils.isNotBlank(req.getCurrUId()) 
                && StringUtils.isNotBlank(req.getOldPswd()) 
                && StringUtils.isNotBlank(req.getNewPswd())) {
            SystemUser user = userMapper.selectByPrimaryKey(req.getCurrUId());
            if (user != null) {
                if (StringUtils.equals(user.getPassword(), req.getOldPswd())) {
                    //修改新密码
                    SystemUser bean = new SystemUser();
                    bean.setId(user.getId());
                    bean.setPassword(req.getNewPswd());
                    userMapper.updateByPrimaryKeySelective(bean);
                    result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
                } else {
                    //旧密码不正确
                    result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR9);
                }
            } else {
                //用户不存在
                result.setErrorCode(ErrorCode.SYSTEM_USER_ERROR1);
            }
        }
        return result;
    }

    @Override
    public Result handLogin(UserReq req) throws Exception {
        Result rs =Result.getParamErrorResult();
        //参数不能为空
        if (req!=null && StringUtils.isNotBlank(req.getPswd()) && StringUtils.isNotBlank(req.getAccount())) {
            SystemUserQueryReq query =new SystemUserQueryReq();
            query.setAccount(req.getAccount());
            List<SystemUser> list = userMapper.selectByCondition(query);
            if ( CollectionUtils.isNotEmpty(list) ) {
                //验证密码
                if (StringUtils.equals(list.get(0).getPassword(),req.getPswd() )) {
                    if ( list.get(0).getIsvalid()==BusinessConstants.USER_VALID_BYTE ) {
                        if(list.get(0).getIslock() == BusinessConstants.USER_UNLOCK_BYTE) {
                            if(StringUtils.equals(list.get(0).getIdentityTypes(), BusinessConstants.USER_PT_STR)) {
                                cumulativeLoginCount(list.get(0));
                                SystemUserResp user = copySystemUserBean2Resp(list.get(0));
                                List<SystemRoleMenuResp> menuList = systemRolePermissionsService.iphonRole(user.getId());
                                user.setMenuList(menuList);
                                rs.setData(user);
                                rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
                            }else{
                                rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR11);
                            }
                        }else{
                            rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR3);
                        }
                    }else{
                        rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR2);
                    }
                }else{
                    rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR2);
                }
            }else{
                rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR1);
            }
        }
        return rs;
    }
	
}