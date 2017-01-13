package com.tianrui.web.action.basicFile.nc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.nc.IWarehouseManageService;
import com.tianrui.api.req.basicFile.nc.WarehouseManageQuery;
import com.tianrui.api.req.basicFile.nc.WarehouseManageSave;
import com.tianrui.api.resp.basicFile.nc.WarehouseManageResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 仓库管理Action
 * @author zhanggaohao
 * @createtime 2016年12月12日 上午10:23:52
 * @classname WarehouseManageAction.java
 */
@Controller
@RequestMapping("warehouse")
public class WarehouseManageAction {
	
	private Logger log = LoggerFactory.getLogger(WarehouseManageAction.class);
	
	@Autowired
	private IWarehouseManageService warehouseManageService;
	
	@RequestMapping("main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("basicFile/nc/warehouse");
		return view;
	}
	
	@RequestMapping("page")
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
	
	@RequestMapping("updateWarehouse")
	@ResponseBody
	public Result updateMater(WarehouseManageSave req){
		Result result = Result.getSuccessResult();
		try {
			result = warehouseManageService.updateWarehouse(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("findAll")
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
	
}