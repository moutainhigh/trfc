package com.tianrui.web.action.businessManage.logisticsManage;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.businessManage.logisticsManage.IAccessRecordService;
import com.tianrui.api.req.businessManage.logisticsManage.AccessRecordQuery;
import com.tianrui.api.resp.businessManage.logisticsManage.AccessRecordResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.SessionManager;

@Controller
@RequestMapping("trfc/accessRecord")
public class AccessRecordAction {
	
	Logger logger = LoggerFactory.getLogger(AccessRecordAction.class);
	
	@Autowired
	private IAccessRecordService accessRecordService;
	
	@RequestMapping("main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("businessManage/logisticsManage/accessRecord");
		return view;
	}
	
	@RequestMapping("page")
	@ResponseBody
	public Result page(AccessRecordQuery query){
		Result result = Result.getSuccessResult();
		try {
			PaginationVO<AccessRecordResp> page = accessRecordService.page(query);
			result.setData(page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("findOne")
	@ResponseBody
	public Result findOne(AccessRecordQuery query){
		Result result = Result.getSuccessResult();
		try {
			result = accessRecordService.findOne(query);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("invalid")
	@ResponseBody
	public Result invalid(AccessRecordQuery query, HttpServletRequest request){
	    Result result = Result.getSuccessResult();
	    try {
	    	SystemUserResp user = SessionManager.getSessionUser(request);
	    	query.setCurrId(user.getId());
	        result = accessRecordService.invalid(query);
	    } catch (Exception e) {
	        logger.error(e.getMessage(), e);
	        result.setErrorCode(ErrorCode.SYSTEM_ERROR);
	    }
	    return result;
	}
}
