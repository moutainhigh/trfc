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
import com.tianrui.api.req.businessManage.report.InOutDaoPoundQuery;
import com.tianrui.api.resp.businessManage.report.InOutDaoPoundResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.SessionManager;

/**
 * 调拨逐车明细Action
 * @author lenovo
 *
 */
@RequestMapping("/trfc/allotPound")
@Controller
public class AllotPoundAction {
	private Logger log = LoggerFactory.getLogger(AllotPoundAction.class);
	@Resource
	private IInPoundService allotPoundService;
	
	@RequestMapping("/main")
	public ModelAndView main(){
	    ModelAndView view = new ModelAndView("businessManage/Report/ReportAllocting");
	    return view;
	}
	/**
	 * 调拨逐车明细分页展示
	 * @param req
	 * @param request
	 * @return
	 */
	@RequestMapping("/allotPage")
	@ResponseBody
	public Result allotPage(InOutDaoPoundQuery req,HttpServletRequest request){
		Result result = Result.getParamErrorResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			if (req == null) {
				req = new InOutDaoPoundQuery();
			}
			req.setCurrUid(user.getId());
			PaginationVO<InOutDaoPoundResp> page = allotPoundService.page(req);
			result.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		
		return result;
		
	}
	
	@RequestMapping("/allotList")
	@ResponseBody
	public Result allotList(InOutDaoPoundQuery req,HttpServletRequest request){
		Result result = Result.getParamErrorResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			
			if (req ==null) {
				req = new InOutDaoPoundQuery(); 
			}
			req.setCurrUid(user.getId());
			List<InOutDaoPoundResp> list = allotPoundService.list(req);
			result.setData(list);
		} catch (Exception e) {
			// TODO: handle exception
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		
		return result;
	}
	
}
