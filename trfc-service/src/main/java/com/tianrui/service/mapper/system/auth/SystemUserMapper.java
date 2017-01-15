package com.tianrui.service.mapper.system.auth;

import java.util.List;

import com.tianrui.api.req.system.auth.SystemUserQueryReq;
import com.tianrui.service.bean.system.auth.SystemUser;
/**
 * 系统用户
 * @author lixp 2017年1月14日 15:47:53
 *
 */
public interface SystemUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(SystemUser record);

    int insertSelective(SystemUser record);

    SystemUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SystemUser record);

    int updateByPrimaryKey(SystemUser record);
    /**
     * 根据对象查询
     * @param queryReq
     * @return
     */
    List<SystemUser> selectByCondition(SystemUserQueryReq queryReq );
    /**
     * 查询总数
     * @param queryReq
     * @return
     */
    long countByCondition(SystemUserQueryReq queryReq );
}