package com.tianrui.service.mapper.basicFile.measure;

import java.util.List;

import com.tianrui.api.req.basicFile.measure.MinemouthManageQuery;
import com.tianrui.service.bean.basicFile.measure.MinemouthManage;

public interface MinemouthManageMapper {
    int deleteByPrimaryKey(String id);

    int insert(MinemouthManage record);

    int insertSelective(MinemouthManage record);

    MinemouthManage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MinemouthManage record);

    int updateByPrimaryKey(MinemouthManage record);
    
    List<MinemouthManage> selectSelective(MinemouthManage record);
    
    long findMinemouthPageCount(MinemouthManageQuery query);

	List<MinemouthManage> findMinemouthPage(MinemouthManageQuery query);
}