package com.tianrui.web.action.common;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.common.IBillTypeService;
import com.tianrui.api.resp.common.BillTypeResp;
/**
 * @Description 订单类型Action
 * @author zhanggaohao
 * @version 2017年2月25日 下午5:01:09
 */
@Controller
@RequestMapping("/trfc/billType")
public class BillTypeAction {
	
	private Logger log = LoggerFactory.getLogger(BillTypeAction.class);
	
	@Autowired
	private IBillTypeService billTypeService;
	
	@RequestMapping("/autoCompleteSearch")
	@ResponseBody
	public List<BillTypeResp> autoCompleteSearch(String term){
		List<BillTypeResp> list = null;
		try {
			list = billTypeService.autoCompleteSearch(term.trim());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return list;
	}
	
}
