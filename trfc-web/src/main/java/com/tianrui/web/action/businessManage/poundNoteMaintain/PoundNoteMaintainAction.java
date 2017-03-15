package com.tianrui.web.action.businessManage.poundNoteMaintain;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.businessManage.poundNoteMaintain.IPoundNoteService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.businessManage.poundNoteMaintain.PoundNoteQuery;
import com.tianrui.api.req.businessManage.poundNoteMaintain.PoundNoteSave;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.businessManage.poundNoteMaintain.PoundNoteResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("/trfc/poundNote")
public class PoundNoteMaintainAction {

	private Logger logger = LoggerFactory.getLogger(PoundNoteMaintainAction.class);
	
	@Autowired
	private IPoundNoteService poundNoteService;
	@Autowired
	private ISystemCodeService systemCodeService;
	
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
	
	@RequestMapping("/purchase/addView")
	@ResponseBody
	public ModelAndView purchaseAddView(HttpSession session){
		ModelAndView view = new ModelAndView("businessManage/poundNoteMaintain/purchasePoundNoteAdd");
		try {
			SystemUserResp user = (SystemUserResp) session.getAttribute("systemUser");
			GetCodeReq codeReq = new GetCodeReq();
			codeReq.setCode("RK");
			codeReq.setCodeType(true);
			codeReq.setUserid(user.getId());
			view.addObject("code", systemCodeService.getCode(codeReq).getData());
			view.addObject("nowDate", DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
			view.addObject("makebillname", user.getName());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return view;
	}
	
	@RequestMapping("/purchase/addPoundNote")
	@ResponseBody
	public Result addPurchase(PoundNoteSave save, HttpSession session){
		Result result = Result.getParamErrorResult();
		try {
			SystemUserResp user = (SystemUserResp) session.getAttribute("systemUser");
			save.setMakerid(user.getId());
			save.setMakebillname(user.getName());
			result = poundNoteService.addPurchase(save);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/purchase/returnAddView")
	@ResponseBody
	public ModelAndView returnAddView(String id, HttpSession session){
		ModelAndView view = new ModelAndView("businessManage/poundNoteMaintain/purchasePoundNoteReturnAdd");
		try {
			SystemUserResp user = (SystemUserResp) session.getAttribute("systemUser");
			GetCodeReq codeReq = new GetCodeReq();
			codeReq.setCode("RK");
			codeReq.setCodeType(true);
			codeReq.setUserid(user.getId());
			view.addObject("code", systemCodeService.getCode(codeReq).getData());
			view.addObject("poundNote", poundNoteService.findOne(id));
			view.addObject("nowDate", DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
			view.addObject("makebillname", user.getName());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return view;
	}
	
	@RequestMapping("/purchase/returnAddPoundNote")
	@ResponseBody
	public Result returnAddPoundNote(PoundNoteSave save, HttpSession session){
		Result result = Result.getParamErrorResult();
		try {
			SystemUserResp user = (SystemUserResp) session.getAttribute("systemUser");
			save.setMakerid(user.getId());
			save.setMakebillname(user.getName());
			result = poundNoteService.returnAddPoundNote(save);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/purchase/redcollide")
	@ResponseBody
	public Result purchaseRedcollide(PoundNoteQuery query, HttpSession session){
		Result result = Result.getParamErrorResult();
		try {
			SystemUserResp user = (SystemUserResp) session.getAttribute("systemUser");
			query.setCurrId(user.getId());
			result = poundNoteService.purchaseRedcollide(query);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/purchase/invalid")
	@ResponseBody
	public Result purchaseInvalid(PoundNoteQuery query, HttpSession session){
		Result result = Result.getParamErrorResult();
		try {
			SystemUserResp user = (SystemUserResp) session.getAttribute("systemUser");
			query.setCurrId(user.getId());
			result = poundNoteService.purchaseInvalid(query);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/purchase/detail")
	public ModelAndView purchasePoundNoteDetail(String id){
		ModelAndView view = new ModelAndView("businessManage/poundNoteMaintain/purchasePoundNoteDetail");
		try {
			view.addObject("poundNote", poundNoteService.findOne(id));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return view;
	}
	
}
