package com.tianrui.service.impl.system.auth;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.system.auth.ISystemMenuService;
import com.tianrui.api.req.system.auth.SystemMenuQueryReq;
import com.tianrui.api.req.system.auth.SystemMenuSaveReq;
import com.tianrui.api.resp.system.auth.MenuTreeVo;
import com.tianrui.api.resp.system.auth.SystemMenuResp;
import com.tianrui.service.bean.system.auth.SystemMenu;
import com.tianrui.service.mapper.system.auth.SystemMenuMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
/**
 * 菜单管理Service
 * @author YangZhenFu
 * @createtime 2017年3月27日 上午10:02:40
 * @classname SystemMenuService.java
 */
@Service
public class SystemMenuService implements ISystemMenuService {

	@Autowired
	SystemMenuMapper systemMenuMapper;
	
	
	@Override
	public Result page(SystemMenuQueryReq req) throws Exception {
		Result rs =Result.getSuccessResult();
		PaginationVO<SystemMenuResp> page = null;
		if(req != null){
			page = new PaginationVO<SystemMenuResp>();
			long count = systemMenuMapper.countByCondition(req);
			if(count > 0){
				req.setStart((req.getPageNo()-1)*req.getPageSize());
				req.setLimit(req.getPageSize());
				List<SystemMenu> list = systemMenuMapper.selectByCondition(req);
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
		List<SystemMenu> list = systemMenuMapper.selectByCondition(queryReq);
		rs.setData(getTreeDataStr(list));
		return rs;
	}
	
	
	private List<MenuTreeVo> getTreeDataStr(List<SystemMenu> list){
		List<MenuTreeVo> treeList = new ArrayList<MenuTreeVo>();
		List<SystemMenu> childrenList = new ArrayList<SystemMenu>();
		//先添加根菜单
		for(SystemMenu menu : list){
			MenuTreeVo tree = new MenuTreeVo();
			tree.setId(menu.getId());
			tree.setText(menu.getName());
			if(StringUtils.isBlank(menu.getRoleid())){
				treeList.add(tree);
			}else{
				childrenList.add(menu);
			}
		}
		for(MenuTreeVo vo : treeList){
			setChildMenu(childrenList, vo);
		}
		return treeList;
	}
	
	private void setChildMenu(List<SystemMenu> menuList, MenuTreeVo treeVo){
		for(SystemMenu menu : menuList){
			MenuTreeVo tree = new MenuTreeVo();
			tree.setId(menu.getId());
			tree.setText(menu.getName());
			if(StringUtils.equals(menu.getRoleid(), treeVo.getId())){
				if(CollectionUtils.isNotEmpty(treeVo.getChildren())){
					treeVo.getChildren().add(tree);
				}else{
					List<MenuTreeVo> childMenuList = new ArrayList<MenuTreeVo>();
					childMenuList.add(tree);
					treeVo.setChildren(childMenuList);
				}
				setChildMenu(menuList, tree);
			}
		}
	}
	
	public List<MenuTreeVo> iterateMenus(List<MenuTreeVo> menuList,String pid){  
        List<MenuTreeVo> result = new ArrayList<MenuTreeVo>();  
        for (MenuTreeVo treeVo : menuList) { 
            if(StringUtils.isNotBlank(treeVo.getPid())){  
                if(StringUtils.equals(treeVo.getPid(), pid)){  
                    List<MenuTreeVo> iterateMenu = iterateMenus(menuList,treeVo.getId());  
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

	@Transactional
	@Override
	public Result addMenu(SystemMenuSaveReq req) throws Exception {
		Result rs =Result.getSuccessResult();
		//参数不能为空交验
		if(req!=null){
			//菜单名称不能重复 
			SystemMenu query =new SystemMenu();
			query.setName(req.getName());
			List<SystemMenu> _list =systemMenuMapper.selectSelective(query);
			if( _list!=null && _list.size()>0 ){
				rs.setErrorCode(ErrorCode.SYSTEM_MENU_ERROR7);
				return rs;
				
			}
			//菜单编号不能重复
			query =new SystemMenu();
			query.setCode(req.getCode());
			List<SystemMenu> list =systemMenuMapper.selectSelective(query);
			if( list!=null && list.size()>0){
				rs.setErrorCode(ErrorCode.SYSTEM_MENU_ERROR8);
				return rs;
			}
			//获取深度
			SystemMenu sm = systemMenuMapper.selectByPrimaryKey(req.getRoleid());
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
			if(systemMenuMapper.insertSelective(bean)>0){
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
			SystemMenu sm = systemMenuMapper.selectByPrimaryKey(req.getRoleid());
			if(sm!=null){
				req.setDeep(sm.getDeep()+1);
			}
			
			PropertyUtils.copyProperties(bean, req);
			if(bean.getImgType()==null){
				bean.setImgType("");
			}
			bean.setModifier(req.getCurrUId());
			bean.setModifytime(System.currentTimeMillis());
			if(systemMenuMapper.updateByPrimaryKeySelective(bean)>0){
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
			SystemMenu db=systemMenuMapper.selectByPrimaryKey(req.getId());
			if(db !=null){
				//TODO关联关系删除
				if(systemMenuMapper.deleteByPrimaryKey(req.getId())>0){
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




	

}