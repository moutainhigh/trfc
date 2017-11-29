package com.tianrui.web.action.businessManage.report;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.businessManage.report.IInPoundService;
import com.tianrui.api.req.businessManage.report.InOutDaoPoundQuery;
import com.tianrui.api.resp.businessManage.report.InOutDaoPoundResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.SessionManager;


/**
 * 其他入库逐车明细
 * @author lenovo
 *
 */
@RequestMapping("/trfc/inPound")
@Controller
public class InPoundAction {
	private Logger log = LoggerFactory.getLogger(InPoundAction.class);
	@Resource
	private IInPoundService inPoundService;
	
	/**
	 * 其他入库逐车明细分页方法
	 * @param req
	 * @param request
	 * @return
	 */
	@RequestMapping("/putInPage")
	@ResponseBody
	public Result putInPage(InOutDaoPoundQuery req,HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			if(req==null){
				req =new InOutDaoPoundQuery();
			}
			req.setCurrUid(user.getId());
			PaginationVO<InOutDaoPoundResp> page = inPoundService.page(req);
			result.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
		
	}
	
	@RequestMapping("/putInList")
	@ResponseBody
	public Result putInList(InOutDaoPoundQuery req,HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			if(req==null){
				req =new InOutDaoPoundQuery();
			}
			req.setCurrUid(user.getId());
			List<InOutDaoPoundResp> list = inPoundService.list(req);
			
			result.setData(list);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
}
