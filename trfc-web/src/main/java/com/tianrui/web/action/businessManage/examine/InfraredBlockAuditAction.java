package com.tianrui.web.action.businessManage.examine;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.businessManage.examine.IExceptionAuditService;
import com.tianrui.api.req.businessManage.examine.ExceptionAuditQuery;
import com.tianrui.api.req.businessManage.examine.ExceptionAuditReq;
import com.tianrui.api.resp.businessManage.examine.ExceptionAuditResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.SessionManager;


/**
 * @annotation 红外被挡审批
 * @author zhanggaohao
 * @date 2017年10月29日 下午2:52:17
 */
@Controller
@RequestMapping("trfc/infraredBlock")
public class InfraredBlockAuditAction {
    
    private Logger log = LoggerFactory.getLogger(InfraredBlockAuditAction.class);
    @Autowired
    private IExceptionAuditService exceptionAuditService;
    
    @RequestMapping("main")
    public ModelAndView main() {
        ModelAndView view = new ModelAndView("businessManage/examine/infraredBlock");
        return view;
    }
    
    @RequestMapping("page")
    @ResponseBody
    public Result page(ExceptionAuditQuery query) {
        Result result = Result.getSuccessResult();
        try {
        	if( query ==null ){
        		query =new ExceptionAuditQuery();
        	}
        	query.setType(5);
            PaginationVO<ExceptionAuditResp> page = exceptionAuditService.pageForInfraredBlock(query);
            result.setData(page);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.setErrorCode(ErrorCode.SYSTEM_ERROR);
        }
        return result;
    }
    
    @RequestMapping("auditView")
    public ModelAndView auditView(String id) throws Exception {
       ModelAndView view = new ModelAndView("businessManage/examine/infraredBlockAuditView");
        ExceptionAuditResp resp = exceptionAuditService.getById(id);
        view.addObject("resp", resp);
        return view;
    }
    
    @RequestMapping("audit")
    @ResponseBody
    public Result audit(ExceptionAuditReq req, HttpServletRequest request) {
        Result result = Result.getErrorResult();
        try {
            SystemUserResp user = SessionManager.getSessionUser(request);
            req.setUserId(user.getId());
            result = exceptionAuditService.audit(req);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.setErrorCode(ErrorCode.SYSTEM_ERROR);
        }
        return result;
    }
    
}
