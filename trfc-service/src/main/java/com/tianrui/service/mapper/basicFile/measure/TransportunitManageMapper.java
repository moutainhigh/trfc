package com.tianrui.service.mapper.basicFile.measure;

import java.util.List;

import com.tianrui.api.req.basicFile.measure.TransportunitManageQuery;
import com.tianrui.service.bean.basicFile.measure.TransportunitManage;

public interface TransportunitManageMapper {
    int deleteByPrimaryKey(String id);

    int insert(TransportunitManage record);

    int insertSelective(TransportunitManage record);

    TransportunitManage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TransportunitManage record);

    int updateByPrimaryKey(TransportunitManage record);
    
    List<TransportunitManage> selectSelective(TransportunitManage record);
    
    long findTransportunitPageCount(TransportunitManageQuery query);

	List<TransportunitManage> findTransportunitPage(TransportunitManageQuery query);
}