package com.tianrui.web.action.businessManage.report;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.businessManage.report.IInPoundService;
import com.tianrui.api.intf.businessManage.report.ISalesPoundService;
import com.tianrui.api.req.businessManage.report.InOutDaoPoundQuery;
import com.tianrui.api.resp.businessManage.report.InOutDaoPoundResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.SessionManager;

/**
 * 销售逐车明细Action
 * @author lenovo
 *
 */
@RequestMapping("/trfc/salesPound")
@Controller
public class SalesPoundAction {
	private Logger log = LoggerFactory.getLogger(SalesPoundAction.class);
	@Resource
	private ISalesPoundService salesPoundService;
	
	@RequestMapping("/main")
	public ModelAndView main(){
	    ModelAndView view = new ModelAndView("businessManage/Report/ReportSales");
	    return view;
	}
	/**
	 * 分页展示
	 * @param req
	 * @param request
	 * @return
	 */
	@RequestMapping("/salesPage")
	@ResponseBody
	public Result salesPage(InOutDaoPoundQuery req,HttpServletRequest request){
		Result result = Result.getParamErrorResult();
		
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			if (req == null) {
				req = new InOutDaoPoundQuery();
			}
			req.setCurrUid(user.getId());
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			PaginationVO<InOutDaoPoundResp> page = salesPoundService.page(req);
			result.setData(page);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		
		
		return result;
	} 
	@RequestMapping("/salesList")
	@ResponseBody
	public Result salesList(InOutDaoPoundQuery req ,HttpServletRequest request){
		Result result = Result.getParamErrorResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			if (req == null) {
				req = new InOutDaoPoundQuery();
			}
			req.setCurrUid(user.getId());
			List<InOutDaoPoundResp> list = salesPoundService.list(req);
			result.setData(list);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
}
