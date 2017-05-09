package com.tianrui.web.action.system.auth;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.system.auth.ISystemMenuService;
import com.tianrui.api.req.system.auth.SystemMenuQueryReq;
import com.tianrui.api.req.system.auth.SystemMenuSaveReq;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 菜单管理Action
 * @author YangZhenFu
 * @createtime 2017年3月27日 上午11:11:40
 * @classname SystemMenuAction.java
 */
@Controller
@RequestMapping("trfc/system/auth/menu")
public class SystemMenuAction {

	private Logger log=LoggerFactory.getLogger(SystemMenuAction.class);
	
	@Autowired
	private ISystemMenuService systemMenuService;
	
	
	@RequestMapping("/main")
	public ModelAndView main(){
		return new ModelAndView("system/auth/menu");
	}
	
	
	@RequestMapping(value="/page",method=RequestMethod.POST)
	@ResponseBody
	public Result page(SystemMenuQueryReq req){
		Result result=Result.getSuccessResult();
		try {
			result=systemMenuService.page(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.OPERATE_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value="/findMenuByUserId",method=RequestMethod.POST)
	@ResponseBody
	public Result findMenuByUserId(HttpSession session){
		Result result=Result.getSuccessResult();
		try {
			SystemUserResp user = (SystemUserResp) session.getAttribute("systemUser");
			result=systemMenuService.findMenuByUserId(user.getId());
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
			result=systemMenuService.addMenu(req);
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
			result=systemMenuService.editMenu(req);
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
			result=systemMenuService.delMenu(req);
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
			result=systemMenuService.getTreeData();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.OPERATE_ERROR);
		}
		return result;
	}
}
