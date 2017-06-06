package com.tianrui.web.action.businessManage.logisticsManage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.businessManage.salesManage.ISalesArriveService;
import com.tianrui.api.req.businessManage.logisticsManage.SalesLogisticsQuery;
import com.tianrui.api.resp.businessManage.logisticsManage.SalesLogisticsResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("trfc/salesLogistics")
public class SalesLogisticsAction {
	
	Logger logger = LoggerFactory.getLogger(SalesLogisticsAction.class);
	
	@Autowired
	private ISalesArriveService salesArriveService;
	
	@RequestMapping("main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("businessManage/logisticsManage/salesLogistics");
		return view;
	}
	
	@RequestMapping("page")
	@ResponseBody
	public Result page(SalesLogisticsQuery query){
		Result result = Result.getSuccessResult();
		try {
			PaginationVO<SalesLogisticsResp> page = salesArriveService.logisticsPage(query);
			result.setData(page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
}
