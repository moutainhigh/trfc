package com.tianrui.web.action.system.merchants;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.nc.ISupplierManageService;
import com.tianrui.api.intf.system.merchants.ISupplierGroupService;
import com.tianrui.api.req.system.merchants.SupplierGroupQuery;
import com.tianrui.api.req.system.merchants.SupplierGroupSave;
import com.tianrui.api.resp.basicFile.nc.SupplierManageResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.api.resp.system.merchants.SupplierGroupResp;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("/trfc/system/merchants/supplierGroup")
public class SupplierGroupAction {
	
	private Logger logger = LoggerFactory.getLogger(SupplierGroupAction.class);
	
	@Autowired
	private ISupplierGroupService supplierGroupService;
	@Autowired
	private ISupplierManageService supplierManageService;
	
	@RequestMapping("/main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("system/merchants/supplierGroup");
		view.addObject("orgname", Constant.ORG_NAME);
		return view;
	}
	
	@RequestMapping("page")
	@ResponseBody
	public Result page(SupplierGroupQuery query){
		Result result = Result.getSuccessResult();
		try {
			PaginationVO<SupplierGroupResp> page = supplierGroupService.page(query);
			result.setData(page);
		} catch (Exception e) {
			 logger.error(e.getMessage(), e);
			 result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("supplierAutoCompleteNotGroupSearch")
	@ResponseBody
	public List<SupplierManageResp> supplierAutoCompleteNotGroupSearch(String term){
		List<SupplierManageResp> list = null;
		try {
			list = supplierManageService.autoCompleteNotGroupSearch(term.trim());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}
	
	@RequestMapping("addSupplierGroup")
	@ResponseBody
	public Result addSupplierGroup(SupplierGroupSave save, HttpSession session){
		Result result = Result.getErrorResult();
		try {
			SystemUserResp user = (SystemUserResp) session.getAttribute("systemUser");
			save.setCurrId(user.getId());
			result = supplierGroupService.addSupplierGroup(save);
		} catch (Exception e) {
			 logger.error(e.getMessage(), e);
			 result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
}
