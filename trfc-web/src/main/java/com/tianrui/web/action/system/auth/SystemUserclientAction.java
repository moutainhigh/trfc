package com.tianrui.web.action.system.auth;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.system.auth.ISystemUserService;
import com.tianrui.api.req.system.auth.SystemUserPswdReq;
import com.tianrui.api.req.system.auth.SystemUserQueryReq;
import com.tianrui.api.req.system.auth.SystemUserSaveReq;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.SessionManager;
@Controller
@RequestMapping("trfc/system/auth/userClient")
public class SystemUserclientAction {
	
	private Logger log = LoggerFactory.getLogger(SystemUserclientAction.class);
	
	@Resource
	private ISystemUserService systemUserService;
	
	//显示当前页
	@RequestMapping("main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("system/auth/userClientMgr");
		view.addObject("orgId", Constant.ORG_ID);
		view.addObject("orgName", Constant.ORG_NAME);
		return view;
	}
	
	//列表数据
	@RequestMapping(value="/page",method=RequestMethod.POST)
	@ResponseBody
	public Result page(SystemUserQueryReq req){
		Result rs= Result.getErrorResult();
		try {
			rs = systemUserService.pages(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	
	//新增数据
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	@ResponseBody
	public Result addUser(SystemUserSaveReq req, HttpServletRequest request){
		Result rs= Result.getErrorResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			req.setCurrUId(user.getId());
			rs = systemUserService.addUser(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	
	//编辑数据
	@RequestMapping(value="/editUser",method=RequestMethod.POST)
	@ResponseBody
	public Result editUser(SystemUserSaveReq req, HttpServletRequest request){
		Result rs= Result.getErrorResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			req.setCurrUId(user.getId());
			rs = systemUserService.editUser(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	//编辑数据
	@RequestMapping(value="/selectAccountUser",method=RequestMethod.POST)
	@ResponseBody
	public Result selectAccountUser(SystemUserSaveReq req, HttpServletRequest request) throws Exception {
		Result rs= Result.getErrorResult();
		rs = systemUserService.selectAccountUser(req.getAccount());
		return rs;
		
	}
	//删除数据
	@RequestMapping(value="/deleteUser",method=RequestMethod.POST)
	@ResponseBody
	public Result deleteUser(SystemUserQueryReq req){
		Result rs= Result.getErrorResult();
		try {
			rs = systemUserService.delUser(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	//删除数据
	@RequestMapping(value="/resetPwd",method=RequestMethod.POST)
	@ResponseBody
	public Result resetPwd(SystemUserSaveReq req, HttpServletRequest request){
		Result rs= Result.getErrorResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			req.setCurrUId(user.getId());
			rs = systemUserService.resetPwd(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	
	//详情
	@RequestMapping(value="/detail",method=RequestMethod.POST)
	@ResponseBody
	public Result detail(SystemUserQueryReq req){
		Result rs= Result.getErrorResult();
		try {
			rs = systemUserService.detail(req);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	/**
	 * 获取下拉框数据(likename)
	 */
	@RequestMapping(value="/autoCompleteSearch",method=RequestMethod.POST)
	@ResponseBody
	public Result autoCompleteSearch(SystemUserQueryReq req){
		Result rs= Result.getErrorResult();
		try {
			List<SystemUserResp> list = systemUserService.autoCompleteSearch(req);
			rs = Result.getSuccessResult();
			rs.setData(list);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		return rs;
	}
	/**
	 * 获取所有用户列表
	 */
	@RequestMapping(value="/queryAllUser",method=RequestMethod.POST)
	@ResponseBody
	public Result queryAllUser(){
		Result rs= Result.getErrorResult();
		try {
			rs = systemUserService.queryAllUser(Constant.ORG_ID);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
	}
    //删除数据
    @RequestMapping(value="/updatePwd",method=RequestMethod.POST)
    @ResponseBody
    public Result updatePwd(SystemUserPswdReq req, HttpServletRequest request){
        Result rs= Result.getErrorResult();
        try {
            SystemUserResp user = SessionManager.getSessionUser(request);
            req.setCurrUId(user.getId());
            rs = systemUserService.updatePwd(req);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return rs;
    }
}
