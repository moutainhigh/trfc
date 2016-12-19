package com.tianrui.web.action.basicFile.nc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.nc.ISupplierManageService;
import com.tianrui.api.req.basicFile.nc.SupplierManageReq;
import com.tianrui.api.resp.basicFile.nc.SupplierManageResp;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@RequestMapping("supplier")
@Controller
public class SupplierManageAction {
	
	Logger log = LoggerFactory.getLogger(SupplierManageAction.class);
	
	@Autowired
	private ISupplierManageService supplierManageService;
	
	@RequestMapping("main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("basicFile/nc/supplier");
		return view;
	}
	
	@RequestMapping("page")
	@ResponseBody
	public Result page(SupplierManageReq req){
		Result result = Result.getSuccessResult();
		try {
			PaginationVO<SupplierManageResp> page = supplierManageService.page(req);
			result.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setCode("-1");
			result.setError("系统异常，请联系管理员！");
			return result;
		}
		return result;
	}
	
	@RequestMapping("updateSupplier")
	@ResponseBody
	public Result updateSupplier(SupplierManageReq req){
		Result result = Result.getSuccessResult();
		try {
			int count = supplierManageService.updateSupplier(req);
			if(count > 0){
				result.setData("修改成功！");
			}else{
				result.setCode("-2");
				result.setError("修改失败，请稍后重试！");
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setCode("-1");
			result.setError("系统异常，请联系管理员！");
			return result;
		}
		return result;
	}
	
}
