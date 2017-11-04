package com.tianrui.service.mapper.system.auth;

import java.util.List;

import com.tianrui.api.req.system.auth.SystemMenuQueryReq;
import com.tianrui.service.bean.system.auth.SystemMenu;
/**
 * 手持机管理 
  * <p>Title:SystemIphoneMapper </p>
  * <p>Description:TODO </p>
  * <p>Company: </p> 
  * @author   yangbobo
  * @date   2017年11月3日 上午10:06:58
 */
public interface SystemIphoneMapper {

	int deleteByPrimaryKey(String id);

    int insert(SystemMenu record);

    int insertSelective(SystemMenu record);

    SystemMenu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemMenu record);

    int updateByPrimaryKey(SystemMenu record);
    
    /**
     * 根据对象查询
     * @param queryReq
     * @return
     */
    List<SystemMenu> selectByCondition(SystemMenuQueryReq queryReq );
    /**
     * 查询总数
     * @param queryReq
     * @return
     */
    long countByCondition(SystemMenuQueryReq queryReq );
    
    List<SystemMenu>  selectSelective(SystemMenu record);
    /**
     * @Description 根据用户id查询拥有的角色的菜单权限
     * @author zhanggaohao
     * @version 2017年5月7日 上午10:12:03
     * @param userid
     * @return
     */
    List<SystemMenu>  selectMenuByUserRoleId(String userid);
}
