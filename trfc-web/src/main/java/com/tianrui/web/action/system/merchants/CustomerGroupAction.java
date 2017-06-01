package com.tianrui.web.action.system.merchants;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.nc.ICustomerManageService;
import com.tianrui.api.intf.system.merchants.ICustomerGroupService;
import com.tianrui.api.req.system.merchants.CustomerGroupQuery;
import com.tianrui.api.req.system.merchants.CustomerGroupSave;
import com.tianrui.api.resp.basicFile.nc.CustomerManageResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.api.resp.system.merchants.CustomerGroupResp;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.SessionManager;

@Controller
@RequestMapping("/trfc/system/merchants/customerGroup")
public class CustomerGroupAction {
	
	private Logger logger = LoggerFactory.getLogger(CustomerGroupAction.class);
	
	@Autowired
	private ICustomerGroupService customerGroupService;
	@Autowired
	private ICustomerManageService customerManageService;
	
	@RequestMapping("/main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("system/merchants/customerGroup");
		view.addObject("orgname", Constant.ORG_NAME);
		return view;
	}
	
	@RequestMapping("page")
	@ResponseBody
	public Result page(CustomerGroupQuery query){
		Result result = Result.getSuccessResult();
		try {
			PaginationVO<CustomerGroupResp> page = customerGroupService.page(query);
			result.setData(page);
		} catch (Exception e) {
			 logger.error(e.getMessage(), e);
			 result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("customerAutoCompleteNotGroupSearch")
	@ResponseBody
	public List<CustomerManageResp> customerAutoCompleteNotGroupSearch(String term){
		List<CustomerManageResp> list = null;
		try {
			list = customerManageService.autoCompleteNotGroupSearch(term.trim());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}
	
	@RequestMapping("addCustomerGroup")
	@ResponseBody
	public Result addCustomerGroup(CustomerGroupSave save, HttpServletRequest request){
		Result result = Result.getErrorResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			save.setCurrId(user.getId());
			result = customerGroupService.addCustomerGroup(save);
		} catch (Exception e) {
			 logger.error(e.getMessage(), e);
			 result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("addCustomerToGroup")
	@ResponseBody
	public Result addCustomerToGroup(String groupid, String childrenList, HttpServletRequest request){
		Result result = Result.getErrorResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			result = customerGroupService.addCustomerToGroup(groupid, childrenList, user.getId());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("customerGroupDetail")
	@ResponseBody
	public Result customerGroupDetail(CustomerGroupQuery query){
		Result result = Result.getErrorResult();
		try {
			result = customerGroupService.customerGroupDetail(query);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
}
