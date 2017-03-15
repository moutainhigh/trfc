package com.tianrui.web.action.businessManage.purchaseManage;

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
import com.tianrui.api.intf.businessManage.purchaseManage.IPurchaseArriveService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.businessManage.purchaseManage.PurchaseArriveQuery;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

@RequestMapping("/trfc/purchaseReturn")
@Controller
public class PurchaseReturnAction {
	
	private Logger log = LoggerFactory.getLogger(PurchaseReturnAction.class);
	
	@Autowired
	private IPurchaseArriveService purchaseArriveService;
	@Autowired
	private ISupplierManageService supplierManageService;
	@Autowired
	private IVehicleManageService vehicleManageService;
	@Autowired
	private IMaterielManageService materielManageService;
	@Autowired
	private IDriverManageService driverManageService;
	@Autowired
	private ISystemCodeService systemCodeService;
	
	@RequestMapping("/main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("businessManage/purchaseManage/purchaseReturn");
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
	public Result page(PurchaseArriveQuery query){
		Result result = Result.getSuccessResult();
		try {
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
}