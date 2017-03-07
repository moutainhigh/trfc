package com.tianrui.web.action.businessManage.poundNoteMaintain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.businessManage.poundNoteMaintain.IPoundNoteService;
import com.tianrui.api.req.businessManage.poundNoteMaintain.PoundNoteQuery;
import com.tianrui.api.resp.businessManage.poundNoteMaintain.PoundNoteResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("/trfc/poundNote")
public class PoundNoteMaintainAction {

	private Logger logger = LoggerFactory.getLogger(PoundNoteMaintainAction.class);
	
	@Autowired
	private IPoundNoteService poundNoteService;
	
	@RequestMapping("/purchase/main")
	public ModelAndView purchaseMain(){
		ModelAndView view = new ModelAndView("businessManage/poundNoteMaintain/purchasePoundNote");
		return view;
	}
	
	@RequestMapping("/purchase/page")
	@ResponseBody
	public Result purchasePage(PoundNoteQuery query){
		Result result = Result.getSuccessResult();
		try {
			PaginationVO<PoundNoteResp> page = poundNoteService.purchasePage(query);
			result.setData(page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
}
