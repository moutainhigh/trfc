package com.tianrui.service.impl.businessManage.examine;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.businessManage.examine.IExceptionAuditService;
import com.tianrui.api.req.businessManage.examine.ExceptionAuditQuery;
import com.tianrui.api.req.businessManage.examine.ExceptionAuditReq;
import com.tianrui.api.resp.businessManage.examine.ExceptionAuditQueryResp;
import com.tianrui.api.resp.businessManage.examine.ExceptionAuditResp;
import com.tianrui.service.bean.businessManage.examine.ExceptionAudit;
import com.tianrui.service.bean.system.auth.SystemUser;
import com.tianrui.service.mapper.businessManage.examine.ExceptionAuditMapper;
import com.tianrui.service.mapper.system.auth.SystemUserMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class ExceptionAuditService implements IExceptionAuditService {

    @Autowired
    private ExceptionAuditMapper exceptionAuditMapper;
    @Autowired
    private SystemUserMapper systemUserMapper;
    
    @Override
    public PaginationVO<ExceptionAuditQueryResp> page(ExceptionAuditQuery query) {
        PaginationVO<ExceptionAuditQueryResp> page = null;
        if (query != null) {
            page = new PaginationVO<ExceptionAuditQueryResp>();
            long count = exceptionAuditMapper.countByPageParams(query);
            if (count > 0) {
                query.setStart((query.getPageNo() - 1) * query.getPageSize());
                query.setLimit(query.getPageSize());
                List<ExceptionAuditQueryResp> list = exceptionAuditMapper.listByPageParams(query);
                page.setList(list);
            }
            page.setPageNo(query.getPageNo());
            page.setPageSize(query.getPageSize());
            page.setTotal(count);
        }
        return page;
    }

    @Override
    public Result audit(ExceptionAuditReq req) {
        Result result = Result.getParamErrorResult();
        if (req != null && StringUtils.isNotBlank(req.getId())
                && StringUtils.isNotBlank(req.getAuditOpinion())) {
            ExceptionAudit bean = exceptionAuditMapper.selectByPrimaryKey(req.getId());
            if (bean != null) {
                if (!bean.getAuditStatus()) {
                    bean.setAuditOpinion(req.getAuditOpinion());
                    bean.setAuditPerson(req.getUserId());
                    bean.setAuditTime(System.currentTimeMillis());
                    bean.setAuditStatus(true);
                    bean.setModifier(req.getUserId());
                    bean.setModifytime(System.currentTimeMillis());
                    exceptionAuditMapper.updateByPrimaryKeySelective(bean);
                    result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
                } else {
                    result.setErrorCode(ErrorCode.EXCEPTION_AUDIT_ERROR1);
                }
            } else {
                result.setErrorCode(ErrorCode.EXCEPTION_AUDIT_ERROR);
            }
        }
        return result;
    }
    
    @Override
    public ExceptionAuditResp getById(String id) throws Exception {
        ExceptionAuditResp resp = null;
        if (StringUtils.isNotBlank(id)) {
            resp = new ExceptionAuditResp();
            ExceptionAudit bean = exceptionAuditMapper.selectByPrimaryKey(id);
            PropertyUtils.copyProperties(resp, bean);
            SystemUser user = systemUserMapper.selectByPrimaryKey(resp.getAuditPerson());
            if (user != null) {
                resp.setAuditPersonName(user.getName());
            }
        }
        return resp;
    }

}
