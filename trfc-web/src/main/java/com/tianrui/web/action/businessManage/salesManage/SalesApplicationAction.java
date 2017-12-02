package com.tianrui.web.action.businessManage.salesManage;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.businessManage.salesManage.ISalesApplicationService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.businessManage.salesManage.SalesApplicationQuery;
import com.tianrui.api.req.businessManage.salesManage.SalesApplicationSave;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationJoinDetailResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.SessionManager;
/**
 * 销售订单
 * @author Administrator
 *
 */
@RequestMapping("/trfc/salesApplication")
@Controller
public class SalesApplicationAction {

	private Logger log = LoggerFactory.getLogger(SalesApplicationAction.class);
	
	@Autowired
	private ISalesApplicationService salesApplicationService;
	@Autowired
	private ISystemCodeService systemCodeService;
	
	@RequestMapping("/main")
	public ModelAndView main(HttpServletRequest request){
		ModelAndView view = new ModelAndView("businessManage/salesManage/salesApplication");
		view.addObject("orgId", Constant.ORG_ID);
		view.addObject("orgName", Constant.ORG_NAME);
		return view;
	}
	
	@RequestMapping("/page")
	@ResponseBody
	public Result page(SalesApplicationQuery query){
		Result result = Result.getSuccessResult();
		try {
			PaginationVO<SalesApplicationResp> page = salesApplicationService.page(query);
			result.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/pageGroupMateriel")
	@ResponseBody
	public Result pageGroupMateriel(SalesApplicationQuery query){
		Result result = Result.getSuccessResult();
		try {
			PaginationVO<SalesApplicationJoinDetailResp> page = salesApplicationService.pageGroupMateriel(query);
			result.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/initAdd")
	@ResponseBody
	public Result initAdd(HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			SystemUserResp user = SessionManager.getSessionUser(request);
			GetCodeReq codeReq = new GetCodeReq();
			codeReq.setCode("XXSO");
			codeReq.setCodeType(true);
			codeReq.setUserid(user.getId());
			map.put("code", systemCodeService.getCode(codeReq).getData());
			map.put("nowDate", DateUtil.getNowDateString(DateUtil.Y_M_D_H_M_S));
			result.setData(map);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Result add(SalesApplicationSave save, HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			save.setUserId(user.getId());
			save.setUserName(user.getName());
			result = salesApplicationService.add(save);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Result update(SalesApplicationSave save, HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			save.setUserId(user.getId());
			save.setUserName(user.getName());
			result = salesApplicationService.update(save);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/audit")
	@ResponseBody
	public Result audit(SalesApplicationQuery query, HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			query.setAuditid(user.getId());
			query.setAuditname(user.getName());
			result = salesApplicationService.audit(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/unaudit")
	@ResponseBody
	public Result unaudit(SalesApplicationQuery query, HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			query.setAuditid(user.getId());
			query.setAuditname(user.getName());
			result = salesApplicationService.unaudit(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(SalesApplicationQuery query, HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			query.setCurrid(user.getId());
			query.setCurrname(user.getName());
			result = salesApplicationService.delete(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
}
