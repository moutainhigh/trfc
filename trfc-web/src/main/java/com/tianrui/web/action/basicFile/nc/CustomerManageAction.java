package com.tianrui.web.action.basicFile.nc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.nc.ICustomerManageService;
import com.tianrui.api.req.basicFile.nc.CustomerManageReq;
import com.tianrui.api.resp.basicFile.nc.CustomerManageResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@RequestMapping("customer")
@Controller
public class CustomerManageAction {

	private Logger log = LoggerFactory.getLogger(CustomerManageAction.class);
	
	@Autowired
	private ICustomerManageService customerManageService;
	
	@RequestMapping("main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("basicFile/nc/customer");
		return view;
	}
	
	@RequestMapping("page")
	@ResponseBody
	public Result page(CustomerManageReq req){
		Result result = Result.getSuccessResult();
		try {
			PaginationVO<CustomerManageResp> page = customerManageService.page(req);
			result.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("updateCustomer")
	@ResponseBody
	public Result updateCustomer(CustomerManageReq req){
		Result result = Result.getSuccessResult();
		try {
			int n = customerManageService.updateCustomer(req);
			if(n > 0){
				result.setData(n);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("findAll")
	@ResponseBody
	public Result findAll(){
		Result result = Result.getSuccessResult();
		try {
			result.setData(customerManageService.findAll());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
}
