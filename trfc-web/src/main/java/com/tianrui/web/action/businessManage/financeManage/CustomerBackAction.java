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

import com.tianrui.api.intf.businessManage.financeManage.ICustomerBackService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.businessManage.financeManage.CustomerBackQuery;
import com.tianrui.api.req.businessManage.financeManage.CustomerBackSave;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 客户退补Action
 * @author YangZhenFu
 */
@Controller
@RequestMapping("/trfc/customerback")
public class CustomerBackAction {

	private Logger log=LoggerFactory.getLogger(CustomerBackAction.class);
	
	@Autowired
	private ICustomerBackService customerBackService;
	@Autowired
	private ISystemCodeService systemCodeService;
	
	@RequestMapping("/main")
	public ModelAndView main(HttpSession session){
		ModelAndView view=new ModelAndView("businessManage/financeManage/customerback");
		try {
			SystemUserResp user=(SystemUserResp) session.getAttribute("systemUser");
			view.addObject("user",user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}
	
	
	@RequestMapping(value="/page",method=RequestMethod.POST)
	@ResponseBody
	public Result page(CustomerBackQuery query){
		Result result=Result.getSuccessResult();
		try {
			result=customerBackService.page(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.OPERATE_ERROR);
		}
		return result;
	}
	
	
	@RequestMapping(value="/initAdd",method=RequestMethod.POST)
	@ResponseBody
	public Result initAdd(HttpSession session){
		Result result=Result.getSuccessResult();
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			SystemUserResp user=(SystemUserResp) session.getAttribute("systemUser");
			GetCodeReq codeReq=new GetCodeReq();
			codeReq.setCode("XSTB");
			codeReq.setCodeType(true);
			codeReq.setUserid(user.getId());
			map.put("code", systemCodeService.getCode(codeReq).getData());
			map.put("nowDate", DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
			map.put("makebillname", user.getName());
			result.setData(map);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.OPERATE_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Result add(CustomerBackSave save){
		Result result=Result.getSuccessResult();
		try {
			result=customerBackService.add(save);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.OPERATE_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value="/audit",method=RequestMethod.POST)
	@ResponseBody
	public Result audit(CustomerBackQuery query,HttpSession session){
		Result result=Result.getSuccessResult();
		try {
			SystemUserResp user=(SystemUserResp) session.getAttribute("systemUser");
			query.setAuditid(user.getId());
			query.setAuditname(user.getName());
			result=customerBackService.audit(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.OPERATE_ERROR);
		}
		return result;
	}
	
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result delete(CustomerBackQuery query){
		Result result=Result.getSuccessResult();
		try {
			result=customerBackService.delete(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.OPERATE_ERROR);
		}
		return result;
	}
}
