package com.tianrui.service.mapper.basicFile.measure;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianrui.api.req.basicFile.measure.TransportunitManageQuery;
import com.tianrui.service.bean.basicFile.measure.TransportunitManage;

public interface TransportunitManageMapper {
    int deleteByPrimaryKey(String id);

    int insert(TransportunitManage record);

    int insertSelective(TransportunitManage record);

    TransportunitManage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TransportunitManage record);

    int updateByPrimaryKey(TransportunitManage record);
    /**
     * 根据字段查询数据
     * @param record
     * @return
     */
    List<TransportunitManage> selectSelective(TransportunitManage record);
    /**
     * 根据条件查询数据总数
     * @param query
     * @return
     */
    long findTransportunitPageCount(TransportunitManageQuery query);
    /**
     * 根据条件进行分页查询
     * @param query
     * @return
     */
	List<TransportunitManage> findTransportunitPage(TransportunitManageQuery query);

    List<TransportunitManage> autoCompleteSearch(@Param("likeName")String likeName);
}