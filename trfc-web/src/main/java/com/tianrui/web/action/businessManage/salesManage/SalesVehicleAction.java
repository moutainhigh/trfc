package com.tianrui.web.action.businessManage.salesManage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/trfc/salesVehicle")
public class SalesVehicleAction {
	
	@RequestMapping("/main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("businessManage/salesManage/salesVehicle");
		return view;
	}
	
}
