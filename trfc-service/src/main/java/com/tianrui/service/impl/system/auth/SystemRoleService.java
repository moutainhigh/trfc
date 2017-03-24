package com.tianrui.service.impl.system.auth;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.system.auth.ISystemRoleService;
import com.tianrui.api.req.system.auth.SystemRoleQueryReq;
import com.tianrui.api.req.system.auth.SystemRoleSaveReq;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.service.bean.system.auth.SystemRole;
import com.tianrui.service.mapper.system.auth.SystemRoleMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class SystemRoleService implements ISystemRoleService {

	@Autowired
	SystemRoleMapper roleMapper;
	
	@Override
	public Result page(SystemRoleQueryReq req) throws Exception {
		Result rs =Result.getSuccessResult();
		PaginationVO<SystemUserResp> page = null;
		if(req != null){
			page = new PaginationVO<SystemUserResp>();
			long count = roleMapper.countByCondition(req);
			if(count > 0){
				req.setStart((req.getPageNo()-1)*req.getPageSize());
				req.setLimit(req.getPageSize());
				List<SystemRole> list = roleMapper.selectByCondition(req);
				//page.setList(copySystemUserBeanList2RespList(list));
			}
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
			page.setTotal(count);
		}
		rs.setData(page);
		return rs;
	}

	@Override
	public Result detail(SystemRoleQueryReq req) throws Exception {
		Result rs =Result.getParamErrorResult();
		//主键不能为空
		if( req !=null && StringUtils.isNotBlank(req.getId())){
			SystemRole db=roleMapper.selectByPrimaryKey(req.getId());
			if( db !=null ){
				rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				//rs.setData(copySystemUserBean2Resp(db));
			}else{
				rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR6);
			}
		}
		return rs;
	}

	@Override
	public Result addRole(SystemRoleSaveReq req) throws Exception {
		Result rs =Result.getParamErrorResult();
		//参数不能为空交验
		//橘色名称不能重复 
		SystemRoleQueryReq query =new SystemRoleQueryReq();
		query.setName(req.getName());
		List<SystemRole> list =roleMapper.selectByCondition(query);
		if( CollectionUtils.isEmpty(list) ){
			//橘色编码不能 重复
			query =new SystemRoleQueryReq();
			query.setCode(req.getCode());
			list =roleMapper.selectByCondition(query);
			if( CollectionUtils.isEmpty(list) ){
				//TODO 保存数据
			}else{
				rs.setErrorCode(ErrorCode.SYSTEM_ROLE_ERROR8);
			}
		}else{
			rs.setErrorCode(ErrorCode.SYSTEM_ROLE_ERROR7);
		}
		return rs;
	}

	@Override
	public Result editRole(SystemRoleSaveReq req) throws Exception {
		Result rs =Result.getParamErrorResult();
		//参数不能为空交验
		//用户需要能查询到
		SystemRole db=roleMapper.selectByPrimaryKey(req.getId());
		if( db !=null ){
			//角色名称不能重复 
			SystemRoleQueryReq query =new SystemRoleQueryReq();
			query.setName(req.getName());
			List<SystemRole> list =roleMapper.selectByCondition(query);
			if( CollectionUtils.isEmpty(list)  || list.size()==1 ){
				//角色编码不能 重复
				query =new SystemRoleQueryReq();
				query.setCode(req.getCode());
				list =roleMapper.selectByCondition(query);
				if( CollectionUtils.isEmpty(list) || list.size()==1){
					//TODO 保存数据
					
					rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}else{
					rs.setErrorCode(ErrorCode.SYSTEM_ROLE_ERROR8);
				}
			}else{
				rs.setErrorCode(ErrorCode.SYSTEM_ROLE_ERROR7);
			}
		}else{
			rs.setErrorCode(ErrorCode.SYSTEM_ROLE_ERROR6);
		}	
		return rs;
	}

	@Override
	public Result delRole(SystemRoleQueryReq req) throws Exception {
		Result rs =Result.getParamErrorResult();
		//参数不能为空交验
		if( req !=null && StringUtils.isBlank(req.getId()) ){
			//是否能查到数据
			SystemRole db=roleMapper.selectByPrimaryKey(req.getId());
			if(db !=null){
				//TODO关联关系删除
				roleMapper.deleteByPrimaryKey(req.getId());
				rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				rs.setErrorCode(ErrorCode.SYSTEM_ROLE_ERROR6);
			}
		}
		return rs;
	}
	

}