package com.tianrui.web.action.basicFile.nc;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.nc.ICustomerManageService;
import com.tianrui.api.req.basicFile.nc.CustomerManageQuery;
import com.tianrui.api.req.basicFile.nc.CustomerManageSave;
import com.tianrui.api.resp.basicFile.nc.CustomerManageResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@RequestMapping("/trfc/customer")
@Controller
public class CustomerManageAction {

	private Logger log = LoggerFactory.getLogger(CustomerManageAction.class);
	
	@Autowired
	private ICustomerManageService customerManageService;
	
	@RequestMapping("/main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("basicFile/nc/customer");
		return view;
	}
	
	@RequestMapping("/page")
	@ResponseBody
	public Result page(CustomerManageQuery query){
		Result result = Result.getSuccessResult();
		try {
			PaginationVO<CustomerManageResp> page = customerManageService.page(query);
			result.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/updateCustomer")
	@ResponseBody
	public Result updateCustomer(CustomerManageSave req, HttpSession session){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = (SystemUserResp) session.getAttribute("systemUser");
			req.setCurrUId(user.getId());
			result = customerManageService.updateCustomer(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
}
