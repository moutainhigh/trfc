package com.tianrui.web.action.businessManage.cardManage;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.businessManage.cardManage.ICardReissueService;
import com.tianrui.api.req.businessManage.cardManage.CardReissueReq;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

@RequestMapping("/trfc/cardReissue")
@Controller
public class CardReissueAction {
	
	Logger log = LoggerFactory.getLogger(CardReissueAction.class);
	
	@Autowired
	private ICardReissueService cardSeissueService;
	
	@RequestMapping("/main")
	public ModelAndView main(HttpSession session){
		ModelAndView view = new ModelAndView("businessManage/cardManage/card_reissue");
		return view;
	}
	@RequestMapping("/detail")
	public ModelAndView detail(HttpSession session){
		ModelAndView view = new ModelAndView("businessManage/cardManage/card_detail");
		return view;
	}
	@ResponseBody
	@RequestMapping("/getAccessData")
	public Result getAccessData(CardReissueReq req){
		Result result = Result.getSuccessResult();
		try {
			result = cardSeissueService.getAccessData(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("/page")
	public Result page(CardReissueReq req){
		Result result = Result.getSuccessResult();
		try {
			result = cardSeissueService.page(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
}
