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
 * 其他出库逐车明细Action
 * @author lenovo
 *
 */
@RequestMapping("/trfc/outPound")
@Controller
public class OutPoundAction {
	private Logger log = LoggerFactory.getLogger(OutPoundAction.class);
	@Resource
	private IInPoundService outPoundService;
	
	/**
	 * 其他出库逐车明细分页展示
	 * @param req
	 * @param request
	 * @return
	 */
	@RequestMapping("/outStorePage")
	@ResponseBody
	public Result outStorePage(InOutDaoPoundQuery req,HttpServletRequest request){
		Result result = Result.getParamErrorResult();

		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			if (req==null) {
				req = new InOutDaoPoundQuery();  
			}
			req.setCurrUid(user.getId());
			PaginationVO<InOutDaoPoundResp> page = outPoundService.page(req);
			result.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	/**
	 * 返回全部
	 * @param req
	 * @param request
	 * @return
	 */
	@RequestMapping("/outStoreList")
	@ResponseBody
	public Result outStoreList(InOutDaoPoundQuery req,HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			if(req==null){
				req =new InOutDaoPoundQuery();
			}
			req.setCurrUid(user.getId());
			List<InOutDaoPoundResp> list = outPoundService.list(req);
			
			result.setData(list);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
}
