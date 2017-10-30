package com.tianrui.web.action.quality.purchase;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.quality.purchase.IPurchaseMixedService;
import com.tianrui.api.req.quality.purchase.PurchaseMixedReq;
import com.tianrui.api.resp.businessManage.handset.HandSetReturnResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("/trfc/quality/purchase/mixed")
public class PurchaseMixedAction {
    
    private Logger log = LoggerFactory.getLogger(PurchaseAssayAction.class);

    @Autowired
    private IPurchaseMixedService purchaseMixedService;
    
    @RequestMapping("main")
    public ModelAndView main() {
        ModelAndView view = new ModelAndView("quality/purchase/mixed");
        view.addObject("yesterday", DateUtil.parse(DateUtil.getYesterday(), DateUtil.Y_M_D));
        return view;
    }
    
    @RequestMapping("page")
    @ResponseBody
    public Result page(PurchaseMixedReq req) {
        Result result = Result.getErrorResult();
        try {
            result = purchaseMixedService.page(req);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.setErrorCode(ErrorCode.SYSTEM_ERROR);
        }
        return result;
    }
    
    @RequestMapping("supplierAutoComplate")
    @ResponseBody
    public List<HandSetReturnResp> listAutoComplate(String term) {
         return purchaseMixedService.supplierAutoComplate(term);
    }
    
    @RequestMapping("minemouthAutoComplate")
    @ResponseBody
    public List<HandSetReturnResp> minemouthAutoComplate(String term) {
        return purchaseMixedService.minemouthAutoComplate(term);
    }
    
    @RequestMapping("materialAutoComplate")
    @ResponseBody
    public List<HandSetReturnResp> materialAutoComplate(String term) {
        return purchaseMixedService.materialAutoComplate(term);
    }
    
}
