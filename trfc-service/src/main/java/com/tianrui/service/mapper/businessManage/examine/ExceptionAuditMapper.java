package com.tianrui.service.mapper.businessManage.examine;

import java.util.List;

import com.tianrui.api.req.businessManage.examine.ExceptionAuditQuery;
import com.tianrui.api.resp.businessManage.examine.ExceptionAuditQueryResp;
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
}