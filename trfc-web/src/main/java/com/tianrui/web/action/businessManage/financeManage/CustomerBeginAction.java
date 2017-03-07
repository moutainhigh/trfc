package com.tianrui.web.action.businessManage.financeManage;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.businessManage.financeManage.ICustomerBeginService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.businessManage.financeManage.CustomerBeginQuery;
import com.tianrui.api.req.businessManage.financeManage.CustomerBeginSave;
import com.tianrui.api.req.businessManage.salesManage.SalesApplicationSave;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 客户期初Action
 * @author YangZhenFu
 */
@Controller
@RequestMapping("/trfc/customerbegin")
public class CustomerBeginAction {
	
	private Logger log=LoggerFactory.getLogger(CustomerBeginAction.class);
	
	@Autowired
	private ICustomerBeginService customerBeginService;
	@Autowired
	private ISystemCodeService systemCodeService;
	
	
	@RequestMapping("/main")
	public ModelAndView main(HttpSession session){
		ModelAndView view = new ModelAndView("businessManage/financeManage/customerbegin");
		try {
			SystemUserResp user = (SystemUserResp) session.getAttribute("systemUser");
			view.addObject("user", user);
			view.addObject("orgid", Constant.ORG_ID);
			view.addObject("orgname", Constant.ORG_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
	
	@RequestMapping(value="/page",method=RequestMethod.POST)
	@ResponseBody
	public Result page(CustomerBeginQuery query){
		Result result = Result.getSuccessResult();
		try {
			result = customerBeginService.page(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/initAdd")
	@ResponseBody
	public Result initAdd(CustomerBeginSave save, HttpSession session){
		Result result = Result.getSuccessResult();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			SystemUserResp user = (SystemUserResp) session.getAttribute("systemUser");
			GetCodeReq codeReq = new GetCodeReq();
			codeReq.setCode("XSQC");
			codeReq.setCodeType(true);
			codeReq.setUserid(user.getId());
			map.put("code", systemCodeService.getCode(codeReq).getData());
			map.put("nowDate", DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
			map.put("collectionunit", Constant.ORG_NAME);
			map.put("makebillname", user.getName());
			System.out.println(map);
			result.setData(map);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	
	@RequestMapping("/add")
	@ResponseBody
	public Result add(CustomerBeginSave save, HttpSession session){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = (SystemUserResp) session.getAttribute("systemUser");
			save.setMakeid(user.getId());
			save.setMakebillname(user.getName());
			result = customerBeginService.add(save);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/audit")
	@ResponseBody
	public Result audit(CustomerBeginQuery query, HttpSession session){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = (SystemUserResp) session.getAttribute("systemUser");
			query.setAuditid(user.getId());
			query.setAuditname(user.getName());
			result = customerBeginService.audit(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(CustomerBeginQuery query, HttpSession session){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = (SystemUserResp) session.getAttribute("systemUser");
			result = customerBeginService.delete(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
}
