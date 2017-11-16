package com.tianrui.service.mapper.businessManage.examine;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tianrui.api.req.businessManage.examine.ExceptionAuditQuery;
import com.tianrui.api.resp.businessManage.examine.ExceptionAuditQueryResp;
import com.tianrui.api.resp.businessManage.examine.ExceptionAuditResp;
import com.tianrui.service.bean.businessManage.examine.ExceptionAudit;

public interface ExceptionAuditMapper {
    int deleteByPrimaryKey(String id);

    int insert(ExceptionAudit record);

    int insertSelective(ExceptionAudit record);

    ExceptionAudit selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ExceptionAudit record);

    int updateByPrimaryKey(ExceptionAudit record);
    
    List<ExceptionAuditQueryResp> listByPageParams(ExceptionAuditQuery req);
    
    long countByPageParams(ExceptionAuditQuery req);
    //单表的条件查询
    List<ExceptionAudit> listByCondition(ExceptionAuditQuery req);
    //单表的总数条件查询
    long countByCondition(ExceptionAuditQuery req);
    
    List<ExceptionAudit> listByPnId(@Param("type")String type, @Param("pnId")String pnId);
}