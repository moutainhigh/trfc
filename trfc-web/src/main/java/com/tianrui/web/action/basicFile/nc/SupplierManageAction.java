package com.tianrui.web.action.basicFile.nc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.nc.ISupplierManageService;
import com.tianrui.api.intf.common.IUpdateFromDcService;
import com.tianrui.api.req.basicFile.nc.SupplierManageQuery;
import com.tianrui.api.req.basicFile.nc.SupplierManageSave;
import com.tianrui.api.resp.basicFile.nc.SupplierManageResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.SessionManager;

@RequestMapping("/trfc/supplier")
@Controller
public class SupplierManageAction {
	
	Logger log = LoggerFactory.getLogger(SupplierManageAction.class);
	
	@Autowired
	private ISupplierManageService supplierManageService;
	@Autowired
	private IUpdateFromDcService updateFromDcService;
	
	@RequestMapping("/main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("basicFile/nc/supplier");
		return view;
	}
	
	@RequestMapping("/page")
	@ResponseBody
	public Result page(SupplierManageQuery query){
		Result result = Result.getSuccessResult();
		try {
			PaginationVO<SupplierManageResp> page = supplierManageService.page(query);
			result.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/updateSupplier")
	@ResponseBody
	public Result updateSupplier(SupplierManageSave req, HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			req.setCurrUId(user.getId());
			result = supplierManageService.updateSupplier(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/autoCompleteSearch")
	@ResponseBody
	public List<SupplierManageResp> autoCompleteSearch(String term){
		List<SupplierManageResp> list = null;
		try {
			list = supplierManageService.autoCompleteSearch(term.trim());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return list;
	}
	
	@RequestMapping("/findOne")
	@ResponseBody
	public SupplierManageResp findOne(String id){
		SupplierManageResp resp = null;
		try {
			resp = supplierManageService.findOne(id);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return resp;
	}
	
	@RequestMapping("/updateFromDc")
	@ResponseBody
	public Result updateFromDc(){
		Result result = Result.getErrorResult();
		try {
			result = updateFromDcService.updateFromDc("3");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
}
