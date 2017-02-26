package com.tianrui.web.action.quality.sales;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.quality.sales.IAssayReportService;

@Controller
@RequestMapping("/trfc/quality/sales/report")
public class AssayReportAction {
	@Resource
	private IAssayReportService assayReportService;
	
	@RequestMapping("/main")
	private ModelAndView main(){
		ModelAndView view = new ModelAndView("quality/sales/assayReport");
		return view;
	}
	
}
