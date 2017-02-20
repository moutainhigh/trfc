package com.tianrui.service.mapper.system.auth;

import com.tianrui.service.bean.system.auth.SmUser;

public interface SmUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(SmUser record);

    int insertSelective(SmUser record);

    SmUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SmUser record);

    int updateByPrimaryKey(SmUser record);
}