package com.tianrui.web.action.demo;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.demo.IDemoService;
import com.tianrui.api.req.demo.DemoReq;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.CurrUserUtils;

/**
 * 
  * @ClassName: DemoAction 
  * @Description: TODO
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年8月13日 下午4:16:02 
  *
 */
@Controller
@RequestMapping("/trwuliu/billdriver")
public class DemoAction {

	public static Logger loger = LoggerFactory.getLogger(DemoAction.class);

	@Autowired
	IDemoService demoService;

	@RequestMapping("/save")
	@ResponseBody
	public Result save(DemoReq req, HttpServletRequest request) throws Exception {
		req.setCurrId(CurrUserUtils.getCurrid(request));
		Result rs = demoService.saveDemo(req);
		return rs;
	}

	@RequestMapping("/del")
	@ResponseBody
	public Result del(DemoReq req, HttpServletRequest request) throws Exception {
		req.setCurrId(CurrUserUtils.getCurrid(request));
		Result rs = demoService.delDemo(req);
		return rs;
	}

	@RequestMapping("/page")
	@ResponseBody
	public Result page(DemoReq req, HttpServletRequest request) throws Exception {
		Result rs = Result.getSuccessResult();
		if (req != null) {
			req.setCurrId(CurrUserUtils.getCurrid(request));
			rs.setData(demoService.page(req));
		}
		return rs;
	}

}
