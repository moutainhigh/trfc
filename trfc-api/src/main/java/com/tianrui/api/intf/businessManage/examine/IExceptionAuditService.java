package com.tianrui.api.intf.businessManage.examine;

import com.tianrui.api.req.businessManage.examine.ExceptionAuditQuery;
import com.tianrui.api.req.businessManage.examine.ExceptionAuditReq;
import com.tianrui.api.resp.businessManage.examine.ExceptionAuditQueryResp;
import com.tianrui.api.resp.businessManage.examine.ExceptionAuditResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

public interface IExceptionAuditService {

    PaginationVO<ExceptionAuditQueryResp> page(ExceptionAuditQuery query);

    Result audit(ExceptionAuditReq req);
    
    ExceptionAuditResp getById(String id) throws Exception;

}
