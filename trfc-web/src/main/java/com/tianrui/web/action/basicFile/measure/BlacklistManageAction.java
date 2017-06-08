package com.tianrui.web.action.basicFile.measure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.basicFile.businessControl.PrimarySettingSave;
import com.tianrui.api.req.basicFile.measure.BlacklistManageQuery;
import com.tianrui.api.req.basicFile.measure.BlacklistManageSave;
import com.tianrui.api.req.basicFile.measure.TransportunitManageSave;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.basicFile.measure.BlacklistManageResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.service.impl.basicFile.measure.BlacklistManageService;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.DateUtil;
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
			result = blacklistManageService.page(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	/**
	 * 新增车辆黑名单
	 * @param save
	 * @return
	 */
	@RequestMapping("addCarBlacklist")
	@ResponseBody
	public Result addCarBlacklist(HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			SystemUserResp user = SessionManager.getSessionUser(request);
			map.put("creator", user.getName());
			map.put("createtime", DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
			result.setData(map);
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
			save.setCreator(user.getId());
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
	@RequestMapping(value="/delblacklist",method=RequestMethod.POST)
	@ResponseBody
	public Result deleteblacklist(BlacklistManageQuery query){
		Result result = Result.getSuccessResult();
		try {
			result = blacklistManageService.deleteblacklist(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	/**
	 * 修改运输单位信息
	 * @param save
	 * @return
	 */
	@RequestMapping(value="/updateBlacklist",method=RequestMethod.POST)
	@ResponseBody
	public Result updateBlacklist(BlacklistManageSave save,HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			save.setModifier(user.getId());
			result = blacklistManageService.updateBlacklist(save);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
}
