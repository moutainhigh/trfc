package com.tianrui.web.action.businessManage.salesManage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.measure.IDriverManageService;
import com.tianrui.api.intf.basicFile.measure.IVehicleManageService;
import com.tianrui.api.intf.basicFile.nc.IMaterielManageService;
import com.tianrui.api.intf.basicFile.nc.ISupplierManageService;
import com.tianrui.api.intf.businessManage.salesManage.ISalesArriveService;
import com.tianrui.api.req.businessManage.salesManage.SalesArriveQuery;
import com.tianrui.api.resp.businessManage.salesManage.SalesArriveResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@RequestMapping("salesArrive")
@Controller
public class SalesArriveAction {

	private Logger log = LoggerFactory.getLogger(SalesArriveAction.class);
	
	@Autowired
	private ISalesArriveService salesArriveService;
	@Autowired
	private ISupplierManageService supplierManageService; 
	@Autowired
	private IVehicleManageService vehicleManageService; 
	@Autowired
	private IMaterielManageService materielManageService; 
	@Autowired
	private IDriverManageService driverManageService; 
	
	@RequestMapping("main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("businessManage/salesManage/salesArrive");
		try {
			view.addObject("supplier", supplierManageService.findListByParmas(null).getData());
			view.addObject("vehicle", vehicleManageService.findListByParmas(null).getData());
			view.addObject("materiel", materielManageService.findListByParmas(null).getData());
			view.addObject("driver", driverManageService.findListByParmas(null).getData());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return view;
	}
	
	@RequestMapping("page")
	@ResponseBody
	public Result page(SalesArriveQuery query){
		Result result = Result.getSuccessResult();
		try {
			PaginationVO<SalesArriveResp> page = salesArriveService.page(query);
			result.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
}
