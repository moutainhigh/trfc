package com.tianrui.web.action.businessManage.examine;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.businessManage.examine.IExceptionAuditService;
import com.tianrui.api.intf.businessManage.poundNoteMaintain.IPoundNoteService;
import com.tianrui.api.req.businessManage.examine.ExceptionAuditQuery;
import com.tianrui.api.req.businessManage.examine.ExceptionAuditReq;
import com.tianrui.api.resp.businessManage.examine.ExceptionAuditQueryResp;
import com.tianrui.api.resp.businessManage.examine.ExceptionAuditResp;
import com.tianrui.api.resp.businessManage.poundNoteMaintain.PoundNoteResp;
import com.tianrui.api.resp.common.UploadImageResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.SessionManager;

/**
 * @annotation 业务审批
 * @author zhanggaohao
 * @date 2017年10月29日 下午2:52:17
 */
@Controller
@RequestMapping("trfc/exceptionAudit")
public class ExceptionAuditAction {
    
    private Logger log = LoggerFactory.getLogger(ExceptionAuditAction.class);
    
    @Autowired
    private IExceptionAuditService exceptionAuditService;
    @Autowired
    private IPoundNoteService poundNoteService;
    
    @RequestMapping({"emptyCarLeavingFactory/main", "noNeedToFillTheBag/main"})
    public ModelAndView emptyCarLeavingFactoryMain(String type) {
        ModelAndView view = new ModelAndView("businessManage/examine/exceptionAudit");
        view.addObject("type", type);
        return view;
    }
    
    @RequestMapping("page")
    @ResponseBody
    public Result page(ExceptionAuditQuery query) {
        Result result = Result.getSuccessResult();
        try {
            PaginationVO<ExceptionAuditQueryResp> page = exceptionAuditService.page(query);
            result.setData(page);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.setErrorCode(ErrorCode.SYSTEM_ERROR);
        }
        return result;
    }
    
    @RequestMapping({"emptyCarLeavingFactory/auditView","noNeedToFillTheBag/auditView"})
    public ModelAndView auditView(String id) throws Exception {
        ModelAndView view = new ModelAndView("businessManage/examine/exceptionAuditView");
        ExceptionAuditResp resp = exceptionAuditService.getById(id);
        view.addObject("resp", resp);
        PoundNoteResp poundNote = poundNoteService.findOne(resp.getPnId());
        view.addObject("poundNote", poundNote);
        List<UploadImageResp> list = poundNoteService.getPoundImages(poundNote.getNoticecode());
        String intoFcImg = null, lightImg = null, weightImg = null;
        if (CollectionUtils.isNotEmpty(list)) {
            for (UploadImageResp image : list) {
                switch (image.getSource()) {
                case "1":
                    intoFcImg = image.getImgurl();
                    break;
                case "2":
                    lightImg = image.getImgurl();
                    break;
                case "3":
                    weightImg = image.getImgurl();
                    break;
                default:
                    break;
                }
            }
        }
        view.addObject("intoFcImg", intoFcImg);
        view.addObject("lightImg", lightImg);
        view.addObject("weightImg", weightImg);
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
