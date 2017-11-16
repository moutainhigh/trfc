package com.tianrui.api.intf.businessManage.examine;

import com.tianrui.api.req.businessManage.examine.ExceptionAuditQuery;
import com.tianrui.api.req.businessManage.examine.ExceptionAuditReq;
import com.tianrui.api.req.businessManage.examine.ExceptionAuditSaveReq;
import com.tianrui.api.resp.businessManage.examine.ExceptionAuditQueryResp;
import com.tianrui.api.resp.businessManage.examine.ExceptionAuditResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

public interface IExceptionAuditService {

    PaginationVO<ExceptionAuditQueryResp> page(ExceptionAuditQuery query);
    
    //红外被挡分页
    PaginationVO<ExceptionAuditResp> pageForInfraredBlock(ExceptionAuditQuery query);

    Result audit(ExceptionAuditReq req);
    //红外被挡申请
    Result apply(ExceptionAuditSaveReq req);
    
    //查询审批红外被挡审批状态
    Result query(ExceptionAuditReq req);
    
    ExceptionAuditResp getById(String id) throws Exception;

}
