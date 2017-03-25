package com.tianrui.service.impl.system.auth;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.system.auth.ISystemMenuService;
import com.tianrui.api.req.system.auth.SystemMenuQueryReq;
import com.tianrui.api.req.system.auth.SystemMenuSaveReq;
import com.tianrui.service.bean.system.auth.SystemMenu;
import com.tianrui.service.bean.system.auth.SystemUser;
import com.tianrui.service.mapper.system.auth.SystemMenuMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class SystemMenuService implements ISystemMenuService {

	@Autowired
	SystemMenuMapper systemMenuMapper;
	
	
	@Override
	public Result page(SystemMenuQueryReq req) throws Exception {
		Result rs =Result.getSuccessResult();
		PaginationVO<SystemMenuQueryReq> page = null;
		if(req != null){
			page = new PaginationVO<SystemMenuQueryReq>();
			long count = systemMenuMapper.countByCondition(req);
			if(count > 0){
				req.setStart((req.getPageNo()-1)*req.getPageSize());
				req.setLimit(req.getPageSize());
				List<SystemMenu> list = systemMenuMapper.selectByCondition(req);
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
	public Result detail(SystemMenuQueryReq req) throws Exception {
		Result rs =Result.getParamErrorResult();
		//主键不能为空
		if( req !=null && StringUtils.isNotBlank(req.getId())){
			SystemMenu db=systemMenuMapper.selectByPrimaryKey(req.getId());
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
	public Result addMenu(SystemMenuSaveReq req) throws Exception {
		Result rs =Result.getParamErrorResult();
		//参数不能为空交验
		//菜单名称不能重复 
		SystemMenuQueryReq query =new SystemMenuQueryReq();
		query.setName(req.getName());
		List<SystemMenu> list =systemMenuMapper.selectByCondition(query);
		if( CollectionUtils.isEmpty(list) ){
			//登录账户不能 重复
			query =new SystemMenuQueryReq();
			query.setCode(req.getCode());
			list =systemMenuMapper.selectByCondition(query);
			if( CollectionUtils.isEmpty(list) ){
				//TODO 保存数据
			}else{
				rs.setErrorCode(ErrorCode.SYSTEM_MENU_ERROR8);
			}
		}else{
			rs.setErrorCode(ErrorCode.SYSTEM_MENU_ERROR7);
		}
		return rs;
	}

	@Override
	public Result editMenu(SystemMenuSaveReq req) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result delMenu(SystemMenuQueryReq req) throws Exception {
		Result rs =Result.getParamErrorResult();
		//参数不能为空交验
		if( req !=null && StringUtils.isBlank(req.getId()) ){
			//是否能查到数据
			SystemMenu db=systemMenuMapper.selectByPrimaryKey(req.getId());
			if(db !=null){
				//TODO关联关系删除
				systemMenuMapper.deleteByPrimaryKey(req.getId());
				rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				rs.setErrorCode(ErrorCode.SYSTEM_MENU_ERROR6);
			}
		}
		return rs;
	}
	

}