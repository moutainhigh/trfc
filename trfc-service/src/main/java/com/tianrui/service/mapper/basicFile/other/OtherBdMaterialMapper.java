package com.tianrui.service.mapper.basicFile.other;

import java.util.List;

import com.tianrui.api.req.basicFile.nc.MaterielManageQuery;
import com.tianrui.api.req.basicFile.other.OtherBdMaterialReq;
import com.tianrui.service.bean.basicFile.nc.MaterielManage;
import com.tianrui.service.bean.basicFile.other.OtherBdMaterial;

public interface OtherBdMaterialMapper {
    
    int deleteByPrimaryKey(String id);
    
    int insert(OtherBdMaterial record);
    
    int insertSelective(OtherBdMaterial record);
    
    OtherBdMaterial selectByPrimaryKey(String id);
    
    int updateByPrimaryKeySelective(OtherBdMaterial record);
    
    int updateByPrimaryKey(OtherBdMaterialReq req);
    
    int updateByPrimaryKeySelective(OtherBdMaterialReq req);
    /**
     * 新增分页方法
     * @param req
     * @return
     */
    long findMaterialPageCount(OtherBdMaterialReq req);
    
    List<OtherBdMaterial> findMaterialPage(OtherBdMaterialReq req);
}