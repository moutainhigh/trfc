package com.tianrui.web.action.businessManage.report;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.businessManage.report.IPurchaseReportService;
import com.tianrui.api.req.businessManage.report.ReportPurchaseQuery;
import com.tianrui.api.resp.businessManage.report.ReportPurchaseMaterResp;
import com.tianrui.api.resp.businessManage.report.ReportPurchaseResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.SessionManager;


/**
 * 采购报表
 * @author Administrator
 *
 */
@RequestMapping("/trfc/purchaseReport")
@Controller
public class PurchaseReportAction {
	private Logger log = LoggerFactory.getLogger(PurchaseReportAction.class);
	
	@Autowired
	private IPurchaseReportService purchaseReportService;
	
	
	@RequestMapping("/main")
	public ModelAndView main(){
	    ModelAndView view = new ModelAndView("businessManage/Report/ReportManagement");
	    return view;
	}
	/**
	 * 逐车明细
	 * @param req
	 * @return
	 */
	@RequestMapping("/commonPage")
	@ResponseBody
	public Result commonPage(ReportPurchaseQuery req,HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			if(req==null){
				req =new ReportPurchaseQuery();
			}
			req.setCurrUid(user.getId());
			PaginationVO<ReportPurchaseResp> page = purchaseReportService.page(req);
			result.setData(page);
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/commonList")
	@ResponseBody
	public Result commonList(ReportPurchaseQuery req,HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			if(req==null){
				req =new ReportPurchaseQuery();
			}
			req.setCurrUid(user.getId());
			List<ReportPurchaseResp> list = purchaseReportService.list(req);
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			result.setData(list);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	
	/**
	 * 物料明细
	 * @param req
	 * @return
	 */
	@RequestMapping("/materPage")
	@ResponseBody
	public Result materPage(ReportPurchaseQuery req,HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			if(req==null){
				req =new ReportPurchaseQuery();
			}
			req.setCurrUid(user.getId());
			PaginationVO<ReportPurchaseMaterResp> page = purchaseReportService.page1(req);
			result.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}

		return result;
	}
	
	@RequestMapping("/materList")
	@ResponseBody
	public Result materList(ReportPurchaseQuery req,HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			if(req==null){
				req =new ReportPurchaseQuery();
			}
			req.setCurrUid(user.getId());
			List<ReportPurchaseMaterResp> list1 = purchaseReportService.list1(req);
			result.setData(list1);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}

		return result;
	}
	
	
	/**
	 * 采购单位明细
	 * @param req
	 * @return
	 */
	@RequestMapping("/customPage")
	@ResponseBody
	public Result customPage(ReportPurchaseQuery req,HttpServletRequest request){
		Result result = Result.getSuccessResult();
		
			
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			if(req==null){
				req =new ReportPurchaseQuery();
			}
			req.setCurrUid(user.getId());
			PaginationVO<ReportPurchaseMaterResp> page = purchaseReportService.page2(req);
			result.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/customList")
	@ResponseBody
	public Result customList(ReportPurchaseQuery req,HttpServletRequest request){
		Result result = Result.getSuccessResult();
		
			
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			if(req==null){
				req =new ReportPurchaseQuery();
			}
			req.setCurrUid(user.getId());
			List<ReportPurchaseMaterResp> list2 = purchaseReportService.list2(req);
			result.setData(list2);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	
	/**
	 * 采购收料明细
	 * @param req
	 * @return
	 */
	@RequestMapping("/receivePage")
	@ResponseBody
	public Result receivePage(ReportPurchaseQuery req,HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			if(req==null){
				req =new ReportPurchaseQuery();
			}
			req.setCurrUid(user.getId());
			PaginationVO<ReportPurchaseResp> page = purchaseReportService.page3(req);
			result.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	@RequestMapping("/receiveList")
	@ResponseBody
	public Result receiveList(ReportPurchaseQuery req,HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			if(req==null){
				req =new ReportPurchaseQuery();
			}
			req.setCurrUid(user.getId());
			List<ReportPurchaseResp> list3 = purchaseReportService.list3(req);
			result.setData(list3);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
}
