package com.tianrui.service.mapper.system.auth;

import java.util.Date;
import java.util.List;

import com.tianrui.service.bean.system.auth.SmUser;

public interface SmUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(SmUser record);

    int insertSelective(SmUser record);

    SmUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SmUser record);

    int updateByPrimaryKey(SmUser record);
    
    int insertBatch(List<SmUser> list);
    
	List<SmUser> selectSelective(SmUser record);
	
	Date findMaxUtc();
}