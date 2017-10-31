package com.tianrui.web.action.businessManage.purchaseManage;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseArriveService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.businessManage.purchaseManage.PurchaseArriveQuery;
import com.tianrui.api.req.businessManage.purchaseManage.PurchaseArriveSave;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.businessManage.purchaseManage.PurchaseArriveResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.SessionManager;

@RequestMapping("/trfc/purchaseArrive")
@Controller
public class PurchaseArriveAction {
	
	private Logger log = LoggerFactory.getLogger(PurchaseArriveAction.class);
	
	@Autowired
	private IPurchaseArriveService purchaseArriveService;
	@Autowired
	private ISystemCodeService systemCodeService;
	
	@RequestMapping("/main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("businessManage/purchaseManage/purchaseArrive");
		return view;
	}
	
	@RequestMapping("page")
	@ResponseBody
	public Result page(PurchaseArriveQuery query){
		Result result = Result.getSuccessResult();
		try {
			query.setType("0");
			PaginationVO<PurchaseArriveResp> page = purchaseArriveService.page(query);
			result.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("addView")
	public ModelAndView addView(HttpServletRequest request){
		ModelAndView view = new ModelAndView("businessManage/purchaseManage/purchaseArriveAdd");
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			GetCodeReq codeReq = new GetCodeReq();
			codeReq.setCode("DH");
			codeReq.setCodeType(true);
			codeReq.setUserid(user.getId());
			view.addObject("code", systemCodeService.getCode(codeReq).getData());
			codeReq.setCode("CL");
			codeReq.setCodeType(true);
			view.addObject("v_code", systemCodeService.getCode(codeReq).getData());
			codeReq.setCode("DR");
			codeReq.setCodeType(true);
			view.addObject("d_code", systemCodeService.getCode(codeReq).getData());
			codeReq.setCode("DR");
			codeReq.setCodeType(false);
			view.addObject("d_internalcode", systemCodeService.getCode(codeReq).getData());
			view.addObject("orgname", Constant.ORG_NAME);
			view.addObject("nowDate", DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return view;
	}
	
	@RequestMapping("add")
	@ResponseBody
	public Result add(PurchaseArriveSave save, HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			save.setCurrId(user.getId());
			save.setType("0");
			result = purchaseArriveService.add(save);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("updateView")
	public ModelAndView updateView(String id, HttpServletRequest request){
		ModelAndView view = new ModelAndView("businessManage/purchaseManage/purchaseArriveUpdate");
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			GetCodeReq codeReq = new GetCodeReq();
			codeReq.setCode("CL");
			codeReq.setCodeType(true);
			codeReq.setUserid(user.getId());
			view.addObject("v_code", systemCodeService.getCode(codeReq).getData());
			codeReq.setCode("DR");
			codeReq.setCodeType(true);
			view.addObject("d_code", systemCodeService.getCode(codeReq).getData());
			codeReq.setCode("DR");
			codeReq.setCodeType(false);
			view.addObject("d_internalcode", systemCodeService.getCode(codeReq).getData());
			view.addObject("orgname", Constant.ORG_NAME);
			view.addObject("nowDate", DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
			view.addObject("purchaseArrive", purchaseArriveService.findOne(id));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return view;
	}
	
	@RequestMapping("update")
	@ResponseBody
	public Result update(PurchaseArriveSave update, HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			update.setCurrId(user.getId());
			result = purchaseArriveService.update(update);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("detailView")
	public ModelAndView detailView(String id){
		ModelAndView view = new ModelAndView("businessManage/purchaseManage/purchaseArriveDetail");
		try {
			view.addObject("purchaseArrive", purchaseArriveService.findOne(id));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return view;
	}

	@RequestMapping("findOne")
	@ResponseBody
	public Result findOne(PurchaseArriveSave update){
		Result result = Result.getSuccessResult();
		try {
			PurchaseArriveResp resp  = purchaseArriveService.findOne(update.getId());
			result.setData(resp);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("audit")
	@ResponseBody
	public Result audit(PurchaseArriveSave update, HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			update.setCurrId(user.getId());
			update.setAuditstatus("1");
			result = purchaseArriveService.updateOperation(update);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("unaudit")
	@ResponseBody
	public Result unaudit(PurchaseArriveSave update, HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			update.setCurrId(user.getId());
			update.setAuditstatus("0");
			result = purchaseArriveService.updateOperation(update);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("invalid")
	@ResponseBody
	public Result invalid(PurchaseArriveSave update, HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			update.setCurrId(user.getId());
			update.setStatus("3");
			result = purchaseArriveService.updateOperation(update);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("outfactory")
	@ResponseBody
	public Result outfactory(PurchaseArriveSave update, HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			update.setCurrId(user.getId());
			update.setStatus("8");
			result = purchaseArriveService.updateOperation(update);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	@RequestMapping("VehiclePage")
	@ResponseBody
	public Result VehiclePage(PurchaseArriveQuery query){
		Result result = Result.getSuccessResult();
		try {
			query.setType("");
			PaginationVO<PurchaseArriveResp> page = purchaseArriveService.page(query);
			result.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
}