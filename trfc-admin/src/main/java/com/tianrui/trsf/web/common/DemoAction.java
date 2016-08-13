package com.tianrui.trsf.web.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
  * @ClassName: DemoAction 
  * @Description: TODO
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年8月13日 下午3:39:45 
  *
 */
@Controller
@RequestMapping("/demo")
public class DemoAction {
	public static Logger logger =LoggerFactory.getLogger(DemoAction.class);
	
	/**
	 * 货物跳转页面
	 * <p>
	 * @return 货物页面
	 * @author guyuke
	 * @throws Exception 
	 * @time 2016年5月22日 上午11:51:42
	 */
	@RequestMapping("/demo")
	public ModelAndView index() throws Exception{
		ModelAndView model= new ModelAndView();
		return model;
	}
	
	
}
