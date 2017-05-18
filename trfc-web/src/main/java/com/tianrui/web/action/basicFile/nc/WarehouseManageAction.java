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

import com.tianrui.api.intf.basicFile.nc.IWarehouseManageService;
import com.tianrui.api.intf.common.IUpdateFromDcService;
import com.tianrui.api.req.basicFile.nc.WarehouseManageQuery;
import com.tianrui.api.req.basicFile.nc.WarehouseManageSave;
import com.tianrui.api.resp.basicFile.nc.WarehouseManageResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.SessionManager;

/**
 * 仓库管理Action
 * @author zhanggaohao
 * @createtime 2016年12月12日 上午10:23:52
 * @classname WarehouseManageAction.java
 */
@Controller
@RequestMapping("/trfc/warehouse")
public class WarehouseManageAction {
	
	private Logger log = LoggerFactory.getLogger(WarehouseManageAction.class);
	
	@Autowired
	private IWarehouseManageService warehouseManageService;
	@Autowired
	private IUpdateFromDcService updateFromDcService;
	
	@RequestMapping("/main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("basicFile/nc/warehouse");
		return view;
	}
	
	@RequestMapping("/page")
	@ResponseBody
	public Result page(WarehouseManageQuery query){
		Result result = Result.getSuccessResult();
		try {
			PaginationVO<WarehouseManageResp> page = warehouseManageService.page(query);
			result.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/updateWarehouse")
	@ResponseBody
	public Result updateMater(WarehouseManageSave req, HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			req.setCurrUId(user.getId());
			result = warehouseManageService.updateWarehouse(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
	public Result findAll(WarehouseManageSave req){
		Result result = Result.getSuccessResult();
		try {
			result = warehouseManageService.findListByParmas(null);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/autoCompleteSearch")
	@ResponseBody
	public List<WarehouseManageResp> autoCompleteSearch(String term){
		List<WarehouseManageResp> list = null;
		try {
			list = warehouseManageService.autoCompleteSearch(term.trim());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return list;
	}
	
	@RequestMapping("/findOne")
	@ResponseBody
	public WarehouseManageResp findOne(String id){
		WarehouseManageResp resp = null;
		try {
			WarehouseManageQuery req = new WarehouseManageQuery();
			req.setId(id);
			resp = warehouseManageService.findOne(req);
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
			result = updateFromDcService.updateFromDc("4");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
}