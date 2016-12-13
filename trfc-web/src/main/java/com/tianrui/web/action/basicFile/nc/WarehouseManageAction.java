package com.tianrui.web.action.basicFile.nc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.nc.IWarehouseManageService;
import com.tianrui.api.req.basicFile.nc.WarehouseManageReq;
import com.tianrui.api.resp.basicFile.nc.WarehouseManageResp;
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
	public Result page(WarehouseManageReq req){
		Result result = Result.getSuccessResult();
		try {
			PaginationVO<WarehouseManageResp> page = warehouseManageService.page(req);
			result.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setCode("-1");
			result.setError("系统异常，请联系管理员！");
			return result;
		}
		return result;
	}
	
	@RequestMapping("updateWarehouse")
	@ResponseBody
	public Result updateMater(WarehouseManageReq req){
		Result result = Result.getSuccessResult();
		try {
			int count = warehouseManageService.updateWarehouse(req);
			if(count == 1){
				result.setData(count);
			}else{
				result.setCode("-2");
				result.setError("修改失败，请稍后重试！");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setCode("-1");
			result.setError("系统异常，请联系管理员！");
			return result;
		}
		return result;
	}
	
}