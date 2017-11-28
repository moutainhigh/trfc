package com.tianrui.web.action.basicFile.businessControl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/trfc/test")
public class TestAction {

	@RequestMapping("main")
	public ModelAndView main() {
		ModelAndView view = new ModelAndView();
		view.setViewName("basicFile/businessControl/primarySetting");
		return view;
	}
}
