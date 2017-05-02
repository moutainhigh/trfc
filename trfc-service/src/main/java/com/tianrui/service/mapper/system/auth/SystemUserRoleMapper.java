package com.tianrui.service.mapper.system.auth;

import com.tianrui.service.bean.system.auth.SystemUserRole;
/**
 * @Description 用户与角色关系Mapper
 * @author zhanggaohao
 * @version 2017年4月27日 上午11:34:07
 */
public interface SystemUserRoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(SystemUserRole record);

    int insertSelective(SystemUserRole record);

    SystemUserRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemUserRole record);

    int updateByPrimaryKey(SystemUserRole record);
}