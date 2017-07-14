package com.tianrui.web.action.basicFile.measure;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.measure.IBlacklistManageService;
import com.tianrui.api.req.basicFile.measure.BlacklistManageQuery;
import com.tianrui.api.req.basicFile.measure.BlacklistManageSave;
import com.tianrui.api.resp.basicFile.measure.BlacklistResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.SessionManager;

@Controller
@RequestMapping("/trfc/blacklist")
public class BlacklistManageAction {
private Logger log=LoggerFactory.getLogger(BlacklistManageAction.class);
	
	@Autowired
	private IBlacklistManageService blacklistManageService;
	
	
	
	@RequestMapping("/main")
	public ModelAndView main(){
		ModelAndView view=new ModelAndView("basicFile/measure/blacklist");
		return view;
	}
	
	/**
	 * 分页查询数据
	 * @param query
	 * @return
	 */
	@RequestMapping(value="/page",method=RequestMethod.POST)
	@ResponseBody
	public Result page(BlacklistManageQuery query){
		Result result = Result.getSuccessResult();
		try {
		    PaginationVO<BlacklistResp> page = blacklistManageService.page(query);
		    result.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Result add(BlacklistManageSave save, HttpServletRequest request){
		Result result = Result.getErrorResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			save.setCurrId(user.getId());
			result = blacklistManageService.add(save);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	/**
	 * 删除车辆黑名单信息
	 * @param query
	 * @return
	 */
	@RequestMapping(value="/del",method=RequestMethod.POST)
	@ResponseBody
	public Result del(BlacklistManageQuery query, HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
            SystemUserResp user = SessionManager.getSessionUser(request);
            query.setCurrId(user.getId());
			result = blacklistManageService.del(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
}
