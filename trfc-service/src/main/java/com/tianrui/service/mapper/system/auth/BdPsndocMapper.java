package com.tianrui.service.mapper.system.auth;

import java.util.Date;
import java.util.List;

import com.tianrui.service.bean.system.auth.BdPsndoc;

public interface BdPsndocMapper {
    int deleteByPrimaryKey(String id);

    int insert(BdPsndoc record);

    int insertSelective(BdPsndoc record);

    BdPsndoc selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BdPsndoc record);

    int updateByPrimaryKey(BdPsndoc record);
    
    int insertBatch(List<BdPsndoc> list);
    
	List<BdPsndoc> selectSelective(BdPsndoc record);
	
	Date findMaxUtc();
}