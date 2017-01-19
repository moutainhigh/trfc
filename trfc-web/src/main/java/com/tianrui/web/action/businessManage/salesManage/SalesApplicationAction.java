package com.tianrui.web.action.businessManage.salesManage;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.nc.ICustomerManageService;
import com.tianrui.api.intf.businessManage.salesManage.ISalesApplicationService;
import com.tianrui.api.intf.common.IBillTypeService;
import com.tianrui.api.req.businessManage.salesManage.SalesApplicationQuery;
import com.tianrui.api.req.businessManage.salesManage.SalesApplicationSave;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
/**
 * 销售订单
 * @author Administrator
 *
 */
@RequestMapping("/trfc/salesApplication")
@Controller
public class SalesApplicationAction {

	private Logger log = LoggerFactory.getLogger(SalesApplicationAction.class);
	
	@Autowired
	private ISalesApplicationService salesApplicationService;
	
	@Autowired
	private IBillTypeService billTypeService;
	
	@Autowired
	private ICustomerManageService customerManageService;
	
	@RequestMapping("/main")
	public ModelAndView main(HttpSession session){
		ModelAndView view = new ModelAndView("businessManage/salesManage/salesApplication");
		try {
			view.addObject("billType", billTypeService.findListByParmas(null).getData());
			view.addObject("customer", customerManageService.findListByParmas(null).getData());
			SystemUserResp user = (SystemUserResp) session.getAttribute("systemUser");
			view.addObject("user", user);
			view.addObject("orgid", Constant.ORG_ID);
			view.addObject("orgname", Constant.ORG_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
	@RequestMapping("/page")
	@ResponseBody
	public Result page(SalesApplicationQuery query){
		Result result = Result.getSuccessResult();
		try {
			PaginationVO<SalesApplicationResp> page = salesApplicationService.page(query);
			result.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/initAdd")
	@ResponseBody
	public Result initAdd(SalesApplicationSave save, HttpSession session){
		Result result = Result.getSuccessResult();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", "XXS"+(DateUtil.getNowDateString("yyyyMMddHHmmss")));
			map.put("nowDate", DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
			result.setData(map);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Result add(SalesApplicationSave save, HttpSession session){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = (SystemUserResp) session.getAttribute("systemUser");
			save.setCreator(user.getId());
			result = salesApplicationService.add(save);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Result update(SalesApplicationSave save){
		Result result = Result.getSuccessResult();
		try {
			result = salesApplicationService.update(save);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/audit")
	@ResponseBody
	public Result audit(SalesApplicationQuery query, HttpSession session){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = (SystemUserResp) session.getAttribute("systemUser");
			query.setAuditid(user.getId());
			query.setAuditname(user.getName());
			result = salesApplicationService.audit(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/unaudit")
	@ResponseBody
	public Result unaudit(SalesApplicationQuery query, HttpSession session){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = (SystemUserResp) session.getAttribute("systemUser");
			query.setAuditid(user.getId());
			query.setAuditname(user.getName());
			result = salesApplicationService.unaudit(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(SalesApplicationQuery query, HttpSession session){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = (SystemUserResp) session.getAttribute("systemUser");
			query.setCurrid(user.getId());
			query.setCurrname(user.getName());
			result = salesApplicationService.delete(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
}
