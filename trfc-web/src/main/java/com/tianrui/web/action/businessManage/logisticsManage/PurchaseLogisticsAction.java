package com.tianrui.web.action.businessManage.logisticsManage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseArriveService;
import com.tianrui.api.req.businessManage.logisticsManage.PurchaseLogisticsQuery;
import com.tianrui.api.resp.businessManage.logisticsManage.PurchaseLogisticsResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("trfc/purchaseLogistics")
public class PurchaseLogisticsAction {
	
	Logger logger = LoggerFactory.getLogger(PurchaseLogisticsAction.class);
	
	@Autowired
	private IPurchaseArriveService purchaseArriveService;
	
	@RequestMapping("main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("businessManage/logisticsManage/purchaseLogistics");
		return view;
	}
	
	@RequestMapping("page")
	@ResponseBody
	public Result page(PurchaseLogisticsQuery query){
		Result result = Result.getSuccessResult();
		try {
			PaginationVO<PurchaseLogisticsResp> page = purchaseArriveService.logisticsPage(query);
			result.setData(page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
}
