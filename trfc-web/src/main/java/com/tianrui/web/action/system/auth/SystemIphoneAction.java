package com.tianrui.web.action.system.auth;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.tianrui.api.intf.system.auth.ISystemIphoneService;
import com.tianrui.api.req.system.auth.SystemMenuQueryReq;
import com.tianrui.api.req.system.auth.SystemMenuSaveReq;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.SessionManager;

@Controller
@RequestMapping("trfc/system/auth/iphone")
public class SystemIphoneAction {
	
	private Logger log=LoggerFactory.getLogger(SystemIphoneAction.class);
	
	@Autowired
 	private ISystemIphoneService systemIphoneService;
	
	@RequestMapping("/main")
	public ModelAndView main(){
		return new ModelAndView("system/auth/iphone");
	}
	@RequestMapping(value="/page",method=RequestMethod.POST)
	@ResponseBody
	public Result page(SystemMenuQueryReq req){
		Result result=Result.getSuccessResult();
		try {
			result=systemIphoneService.page(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.OPERATE_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value="/findMenuByUserId",method=RequestMethod.POST)
	@ResponseBody
	public Result findMenuByUserId(HttpServletRequest request){
		Result result=Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			result=systemIphoneService.findMenuByUserId(user.getId());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.OPERATE_ERROR);
		}
		return result;
	}
	
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Result add(SystemMenuSaveReq req){
		Result result=Result.getSuccessResult();
		try {
			result=systemIphoneService.addMenu(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.OPERATE_ERROR);
		}
		return result;
	}
	
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Result edit(SystemMenuSaveReq req){
		Result result=Result.getSuccessResult();
		try {
			result=systemIphoneService.editMenu(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.OPERATE_ERROR);
		}
		return result;
	}
	
	
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result delete(SystemMenuQueryReq req){
		Result result=Result.getSuccessResult();
		try {
			result=systemIphoneService.delMenu(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.OPERATE_ERROR);
		}
		return result;
	}
	@RequestMapping(value="/treeData",method=RequestMethod.POST)
	@ResponseBody
	public Result treeData(){
		Result result=Result.getSuccessResult();
		try {
			result=systemIphoneService.getTreeData();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.OPERATE_ERROR);
		}
		return result;
	}
}
