package com.tianrui.service.mapper.system.auth;

import com.tianrui.service.bean.system.auth.BdPsndoc;

public interface BdPsndocMapper {
    int deleteByPrimaryKey(String id);

    int insert(BdPsndoc record);

    int insertSelective(BdPsndoc record);

    BdPsndoc selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BdPsndoc record);

    int updateByPrimaryKey(BdPsndoc record);
}