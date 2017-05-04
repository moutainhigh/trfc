package com.tianrui.service.impl.system.auth;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.system.auth.ISystemRoleService;
import com.tianrui.api.req.system.auth.SystemRoleQueryReq;
import com.tianrui.api.req.system.auth.SystemRoleSaveReq;
import com.tianrui.api.resp.system.auth.SystemRoleResp;
import com.tianrui.service.bean.system.auth.SystemRole;
import com.tianrui.service.mapper.system.auth.SystemRoleMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
/**
 * 角色管理Service
 * @author YangZhenFu
 * @createtime 2017年3月31日 上午10:10:40
 * @classname SystemRoleService.java
 */
@Service
public class SystemRoleService implements ISystemRoleService {

	@Autowired
	SystemRoleMapper roleMapper;
	
	@Override
	public Result page(SystemRoleQueryReq req) throws Exception {
		Result rs =Result.getSuccessResult();
		PaginationVO<SystemRoleResp> page = null;
		if(req != null){
			page = new PaginationVO<SystemRoleResp>();
			long count = roleMapper.countByCondition(req);
			if(count > 0){
				req.setStart((req.getPageNo()-1)*req.getPageSize());
				req.setLimit(req.getPageSize());
				List<SystemRole> list = roleMapper.selectByCondition(req);
				page.setList(copySystemUserBeanList2RespList(list));
			}
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
			page.setTotal(count);
		}
		rs.setData(page);
		return rs;
	}

	@Transactional
	@Override
	public Result detail(SystemRoleQueryReq req) throws Exception {
		Result rs =Result.getParamErrorResult();
		//主键不能为空
		if( req !=null && StringUtils.isNotBlank(req.getId())){
			SystemRole db=roleMapper.selectByPrimaryKey(req.getId());
			if( db !=null ){
				rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR6);
			}
		}
		return rs;
	}

	@Transactional
	@Override
	public Result addRole(SystemRoleSaveReq req) throws Exception {
		Result rs =Result.getParamErrorResult();
		//参数不能为空交验
		if(req!=null){
			//角色名称不能重复 
			SystemRoleQueryReq query =new SystemRoleQueryReq();
			query.setName(req.getName());
			List<SystemRole> list =roleMapper.selectByCondition(query);
			if( CollectionUtils.isEmpty(list) ){
				//角色编码不能 重复
				query =new SystemRoleQueryReq();
				query.setCode(req.getCode());
				list =roleMapper.selectByCondition(query);
				if( CollectionUtils.isEmpty(list) ){
					//TODO 保存数据
					SystemRole bean=new SystemRole();
					PropertyUtils.copyProperties(bean, req);
					bean.setId(UUIDUtil.getId());
					bean.setCreator(req.getCurrUId());
					bean.setCreatetime(System.currentTimeMillis());
					bean.setModifier(req.getCurrUId());
					bean.setModifytime(System.currentTimeMillis());
					if(roleMapper.insertSelective(bean)>0){
						rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
						rs.setData(bean);
					}else{
						rs.setErrorCode(ErrorCode.OPERATE_ERROR);
					}
				}else{
					rs.setErrorCode(ErrorCode.SYSTEM_ROLE_ERROR8);
					return rs;
				}
			}else{
				rs.setErrorCode(ErrorCode.SYSTEM_ROLE_ERROR7);
				return rs;
			}
		}
		
		return rs;
	}

	@Transactional
	@Override
	public Result editRole(SystemRoleSaveReq req) throws Exception {
		Result rs =Result.getParamErrorResult();
		//参数不能为空交验
		if(req!=null){
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
						SystemRole bean=new SystemRole();
						PropertyUtils.copyProperties(bean, req);
						bean.setModifier(req.getCurrUId());
						bean.setModifytime(System.currentTimeMillis());
						if(roleMapper.updateByPrimaryKeySelective(bean)>0){
							rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
							rs.setData("操作成功！");
						}else{
							rs.setErrorCode(ErrorCode.OPERATE_ERROR);
						}
					}else{
						rs.setErrorCode(ErrorCode.SYSTEM_ROLE_ERROR8);
					}
				}else{
					rs.setErrorCode(ErrorCode.SYSTEM_ROLE_ERROR7);
				}
			}else{
				rs.setErrorCode(ErrorCode.SYSTEM_ROLE_ERROR6);
			}	
		}
		return rs;
	}

	@Override
	public Result delRole(SystemRoleQueryReq req) throws Exception {
		Result rs =Result.getParamErrorResult();
		//参数不能为空交验
		if( req !=null && StringUtils.isNotBlank(req.getId()) ){
			//是否能查到数据
			SystemRole db=roleMapper.selectByPrimaryKey(req.getId());
			if(db !=null){
				//TODO关联关系删除
				if(roleMapper.deleteByPrimaryKey(req.getId())>0){
					rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}else{
					rs.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}else{
				rs.setErrorCode(ErrorCode.SYSTEM_ROLE_ERROR6);
			}
		}
		return rs;
	}
	
	
	
	/**
	 * 集合转换
	 * @param List<SystemRole> list
	 * @return List<SystemRoleResp> 
	 * @throws Exception
	 */
	private List<SystemRoleResp> copySystemUserBeanList2RespList(List<SystemRole> list) throws Exception {
		List<SystemRoleResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<SystemRoleResp>();
			for(SystemRole Role : list){
				listResp.add(copyBeanList2RespList(Role));
			}
		}
		return listResp;
	}
	
	
	
	/**
	 * 实体bean类型转换
	 * @param SystemRole bean
	 * @return SystemRoleResp
	 * @throws Exception
	 */
	private SystemRoleResp copyBeanList2RespList(SystemRole bean) throws Exception {
		SystemRoleResp resp = null;
		if(bean != null){
			resp = new SystemRoleResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}

	@Override
	public Result queryAllRole() throws Exception {
		Result result = Result.getSuccessResult();
		List<SystemRole> list = roleMapper.queryAllRole();
		result.setData(copySystemUserBeanList2RespList(list));
		return result;
	}


}