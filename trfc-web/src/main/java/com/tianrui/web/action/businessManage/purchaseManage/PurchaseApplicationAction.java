package com.tianrui.web.action.businessManage.purchaseManage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseApplicationService;
import com.tianrui.api.req.businessManage.purchaseManage.PurchaseApplicationReq;
import com.tianrui.api.resp.businessManage.purchaseManage.PurchaseApplicationResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@RequestMapping("purchaseApplication")
@Controller
public class PurchaseApplicationAction {
	
	private Logger log = LoggerFactory.getLogger(PurchaseApplicationAction.class);
	
	@Autowired
	private IPurchaseApplicationService purchaseApplicationService;
	
	@RequestMapping("main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("businessManage/purchaseManage/purchaseApplication");
		return view;
	}
	
	@RequestMapping("page")
	@ResponseBody
	public Result page(PurchaseApplicationReq req){
		Result result = Result.getSuccessResult();
		try {
			PaginationVO<PurchaseApplicationResp> page = purchaseApplicationService.page(req);
			result.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	
}