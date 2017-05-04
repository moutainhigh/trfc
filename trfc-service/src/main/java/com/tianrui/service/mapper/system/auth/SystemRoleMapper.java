package com.tianrui.service.mapper.system.auth;

import java.util.List;

import com.tianrui.api.req.system.auth.SystemRoleQueryReq;
import com.tianrui.service.bean.system.auth.SystemRole;

/**
 * 系统角色
 * @author lixp 2017年1月14日 15:47:53
 *
 */
public interface SystemRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SystemRole record);

    int insertSelective(SystemRole record);

    SystemRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemRole record);

    int updateByPrimaryKey(SystemRole record);
    
    /**
     * 根据对象查询
     * @param queryReq
     * @return
     */
    List<SystemRole> selectByCondition(SystemRoleQueryReq queryReq );
    /**
     * 查询总数
     * @param queryReq
     * @return
     */
    Long countByCondition(SystemRoleQueryReq queryReq );
    /**
     * @Description 查询所有角色列表
     * @author zhanggaohao
     * @version 2017年5月2日 下午2:31:22
     * @return
     */
	List<SystemRole> queryAllRole();
}