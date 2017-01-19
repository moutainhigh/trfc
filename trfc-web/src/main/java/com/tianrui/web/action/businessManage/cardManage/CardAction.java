package com.tianrui.web.action.businessManage.cardManage;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.businessManage.cardManage.ICardService;
import com.tianrui.api.req.businessManage.cardManage.CardReq;
import com.tianrui.api.req.businessManage.cardManage.CardSave;
import com.tianrui.api.resp.businessManage.cardManage.CardResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@RequestMapping("/trfc/card")
@Controller
public class CardAction {
	
	Logger log = LoggerFactory.getLogger(CardAction.class);
	
	@Autowired
	private ICardService cardService;
	
	@RequestMapping("/main")
	public ModelAndView main(HttpSession session){
		ModelAndView view = new ModelAndView("businessManage/cardManage/card");
		SystemUserResp user = (SystemUserResp) session.getAttribute("systemUser");
		view.addObject("user", user);
		return view;
	}
	
	@RequestMapping("/page")
	@ResponseBody
	public Result page(CardReq req){
		Result result = Result.getSuccessResult();
		try {
			PaginationVO<CardResp> page = cardService.page(req);
			result.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/addCard")
	@ResponseBody
	public Result addCard(CardSave req){
		Result result = Result.getSuccessResult();
		try {
			result = cardService.addCard(req);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/updateCard")
	@ResponseBody
	public Result updateCard(CardReq req){
		Result result = Result.getSuccessResult();
		try {
			int n = cardService.updateCard(req);
			if(n > 0){
				result.setData(n);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/delCard")
	@ResponseBody
	public Result delCard(CardReq req){
		Result result = Result.getSuccessResult();
		try {
			int n = cardService.delCard(req);
			if(n > 0){
				result.setData(n);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/findOne")
	@ResponseBody
	public Result findOne(String id){
		Result result = Result.getSuccessResult();
		try {
			if(StringUtils.isNotBlank(id)){
				result.setData(cardService.findOne(id));
			}else{
				result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}

}
