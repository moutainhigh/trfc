package com.tianrui.service.mapper.basicFile.measure;

import java.util.List;

import com.tianrui.api.req.basicFile.measure.YardManageQuery;
import com.tianrui.service.bean.basicFile.measure.YardManage;

public interface YardManageMapper {
    int deleteByPrimaryKey(String id);

    int insert(YardManage record);

    int insertSelective(YardManage record);

    YardManage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(YardManage record);

    int updateByPrimaryKey(YardManage record);
    
    long findYardPageCount(YardManageQuery query);
    
    List<YardManage> findYardPage(YardManageQuery query);
    
    List<YardManage> selectSelective(YardManage record);
}