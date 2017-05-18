package com.tianrui.web.action.businessManage.purchaseManage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/trfc/purchaseVehicle")
public class PurchaseVehicleAction {
	
	@RequestMapping("/main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("businessManage/purchaseManage/purchaseVehicle");
		return view;
	}
	
}
