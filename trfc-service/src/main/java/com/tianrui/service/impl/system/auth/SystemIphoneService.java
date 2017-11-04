package com.tianrui.service.impl.system.auth;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.system.auth.ISystemIphoneService;
import com.tianrui.api.req.system.auth.SystemMenuQueryReq;
import com.tianrui.api.req.system.auth.SystemMenuSaveReq;
import com.tianrui.api.resp.system.auth.ComboTreeVo;
import com.tianrui.api.resp.system.auth.SystemMenuResp;
import com.tianrui.service.bean.system.auth.SystemMenu;
import com.tianrui.service.mapper.system.auth.SystemIphoneMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;


/**
 * 手持机管理
  * <p>Title:SystemIphoneService </p>
  * <p>Description:TODO </p>
  * <p>Company: </p> 
  * @author   yangbobo
  * @date   2017年11月3日 上午10:03:36
 */
@Service
public class SystemIphoneService implements ISystemIphoneService {

	@Autowired
	SystemIphoneMapper systemIPhoneMapper;
	

	@Override
	public Result page(SystemMenuQueryReq req) throws Exception {
		Result rs =Result.getSuccessResult();
		PaginationVO<SystemMenuResp> page = null;
		if(req != null){
			page = new PaginationVO<SystemMenuResp>();
			long count = systemIPhoneMapper.countByCondition(req);
			if(count > 0){
//				req.setStart((req.getPageNo()-1)*req.getPageSize());
//				req.setLimit(req.getPageSize());
				List<SystemMenu> list = systemIPhoneMapper.selectByCondition(req);
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
	public Result getTreeData() throws Exception {
		Result rs = Result.getSuccessResult();
		SystemMenuQueryReq queryReq = new SystemMenuQueryReq();
		queryReq.setStart(0);
		List<SystemMenu> list = systemIPhoneMapper.selectByCondition(queryReq);
		rs.setData(getTreeDataStr(list));
		return rs;
	}
	
	
	private List<ComboTreeVo> getTreeDataStr(List<SystemMenu> list){
		List<ComboTreeVo> treeList = new ArrayList<ComboTreeVo>();
		List<SystemMenu> childrenList = new ArrayList<SystemMenu>();
		//先添加根菜单
		for(SystemMenu menu : list){
			ComboTreeVo tree = new ComboTreeVo();
			tree.setId(menu.getId());
			tree.setText(menu.getName());
			if(StringUtils.isBlank(menu.getRoleid())){
				treeList.add(tree);
			}else{
				childrenList.add(menu);
			}
		}
		for(ComboTreeVo vo : treeList){
			setChildMenu(childrenList, vo);
		}
		return treeList;
	}
	
	private void setChildMenu(List<SystemMenu> menuList, ComboTreeVo treeVo){
		for(SystemMenu menu : menuList){
			ComboTreeVo tree = new ComboTreeVo();
			tree.setId(menu.getId());
			tree.setText(menu.getName());
			if(StringUtils.equals(menu.getRoleid(), treeVo.getId())){
				if(CollectionUtils.isNotEmpty(treeVo.getChildren())){
					treeVo.getChildren().add(tree);
				}else{
					List<ComboTreeVo> childMenuList = new ArrayList<ComboTreeVo>();
					childMenuList.add(tree);
					treeVo.setChildren(childMenuList);
				}
				setChildMenu(menuList, tree);
			}
		}
	}
	
	public List<ComboTreeVo> iterateMenus(List<ComboTreeVo> menuList,String pid){  
        List<ComboTreeVo> result = new ArrayList<ComboTreeVo>();  
        for (ComboTreeVo treeVo : menuList) { 
            if(StringUtils.isNotBlank(treeVo.getPid())){  
                if(StringUtils.equals(treeVo.getPid(), pid)){  
                    List<ComboTreeVo> iterateMenu = iterateMenus(menuList,treeVo.getId());  
                    treeVo.setChildren(iterateMenu);
                    result.add(treeVo);  
                }  
            }
        }  
        return result;  
    }  
	
	@Override
	public Result detail(SystemMenuQueryReq req) throws Exception {
		Result rs =Result.getParamErrorResult();
		//主键不能为空
		if( req !=null && StringUtils.isNotBlank(req.getId())){
			SystemMenu db=systemIPhoneMapper.selectByPrimaryKey(req.getId());
			if( db !=null ){
				rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				//rs.setData(copySystemUserBean2Resp(db));
			}else{
				rs.setErrorCode(ErrorCode.SYSTEM_USER_ERROR6);
			}
		}
		return rs;
	}

	@Transactional
	@Override
	public Result addMenu(SystemMenuSaveReq req) throws Exception {
		Result rs =Result.getSuccessResult();
		//参数不能为空交验
		if(req!=null){
			//菜单名称不能重复 
			SystemMenu query =new SystemMenu();
			query.setName(req.getName());
			List<SystemMenu> _list =systemIPhoneMapper.selectSelective(query);
			if( _list!=null && _list.size()>0 ){
				rs.setErrorCode(ErrorCode.SYSTEM_MENU_ERROR7);
				return rs;
				
			}
			//菜单编号不能重复
			query =new SystemMenu();
			query.setCode(req.getCode());
			List<SystemMenu> list =systemIPhoneMapper.selectSelective(query);
			if( list!=null && list.size()>0){
				rs.setErrorCode(ErrorCode.SYSTEM_MENU_ERROR8);
				return rs;
			}
			//获取深度
			SystemMenu sm = systemIPhoneMapper.selectByPrimaryKey(req.getRoleid());
			if(sm!=null){
				req.setDeep(sm.getDeep()+1);
			}
			//TODO 保存数据
			SystemMenu bean=new SystemMenu();
			PropertyUtils.copyProperties(bean, req);
			bean.setId(UUIDUtil.getId());
			bean.setCreator(req.getCurrUId());
			bean.setCreatetime(System.currentTimeMillis());
			bean.setModifier(req.getCurrUId());
			bean.setModifytime(System.currentTimeMillis());
			if(systemIPhoneMapper.insertSelective(bean)>0){
				rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				rs.setData(bean);
			}else{
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}else{
			rs.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return rs;
	}

	@Transactional
	@Override
	public Result editMenu(SystemMenuSaveReq req) throws Exception {
		Result result=Result.getParamErrorResult();
		//参数不能为空交验
		if(req!=null && StringUtils.isNotBlank(req.getId())){
			SystemMenu bean=new SystemMenu();
			
			//获取深度
			SystemMenu sm = systemIPhoneMapper.selectByPrimaryKey(req.getRoleid());
			if(sm!=null){
				req.setDeep(sm.getDeep()+1);
			}
			
			PropertyUtils.copyProperties(bean, req);
			if(bean.getImgType()==null){
				bean.setImgType("");
			}
			bean.setModifier(req.getCurrUId());
			bean.setModifytime(System.currentTimeMillis());
			if(systemIPhoneMapper.updateByPrimaryKeySelective(bean)>0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Transactional
	@Override
	public Result delMenu(SystemMenuQueryReq req) throws Exception {
		Result rs =Result.getParamErrorResult();
		//参数不能为空交验
		if( req !=null && StringUtils.isNotBlank(req.getId()) ){
			//是否能查到数据
			SystemMenu db=systemIPhoneMapper.selectByPrimaryKey(req.getId());
			if(db !=null){
				//TODO关联关系删除
				if(systemIPhoneMapper.deleteByPrimaryKey(req.getId())>0){
					rs.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				}else{
					rs.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}else{
				rs.setErrorCode(ErrorCode.SYSTEM_MENU_ERROR6);
			}
		}
		return rs;
	}
	
	
	
	/**
	 * 集合转换
	 * @param List<SystemMenu> list
	 * @return List<SystemMenuResp> 
	 * @throws Exception
	 */
	private List<SystemMenuResp> copySystemUserBeanList2RespList(List<SystemMenu> list) throws Exception {
		List<SystemMenuResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<SystemMenuResp>();
			for(SystemMenu menu : list){
				listResp.add(copyBeanList2RespList(menu));
			}
		}
		return listResp;
	}
	
	
	
	/**
	 * 实体bean类型转换
	 * @param SystemMenu bean
	 * @return SystemMenuResp
	 * @throws Exception
	 */
	private SystemMenuResp copyBeanList2RespList(SystemMenu bean) throws Exception {
		SystemMenuResp resp = null;
		if(bean != null){
			resp = new SystemMenuResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}

	@Override
	public Result findMenuByUserId(String uId) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(StringUtils.isNotBlank(uId)){
			List<SystemMenu> list = systemIPhoneMapper.selectMenuByUserRoleId(uId);
			rs.setData(copySystemUserBeanList2RespList(list));
		}
		return rs;
	}

}
