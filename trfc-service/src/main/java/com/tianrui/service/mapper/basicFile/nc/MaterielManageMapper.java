package com.tianrui.service.mapper.basicFile.nc;

import java.util.List;

import com.tianrui.api.req.basicFile.nc.MaterielManageQuery;
import com.tianrui.service.bean.basicFile.nc.MaterielManage;

/**
 * 物料管理Mapper接口
 * @author zhanggaohao
 * @createtime 2016年12月7日 上午9:30:39
 * @classname MaterielManageMapper.java
 */
public interface MaterielManageMapper {
    int deleteByPrimaryKey(String id);

    int insert(MaterielManage record);

    int insertSelective(MaterielManage record);

    MaterielManage selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MaterielManage record);

    int updateByPrimaryKey(MaterielManage record);
    
    long findMaterielManagePageCount(MaterielManageQuery req);
    
    List<MaterielManage> findMaterielManagePage(MaterielManageQuery req);
    
    List<MaterielManage> selectSelective(MaterielManage record);
    
    Long findMaxUtc();
    
}