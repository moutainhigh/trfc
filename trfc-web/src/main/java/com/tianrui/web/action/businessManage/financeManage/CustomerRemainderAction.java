package com.tianrui.web.action.businessManage.financeManage;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.businessManage.financeManage.ICustomerRemainderQueryService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.businessManage.financeManage.CustomerBeginSave;
import com.tianrui.api.req.businessManage.financeManage.CustomerRemainderQuery;
import com.tianrui.api.req.businessManage.financeManage.CustomerRemainderSave;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.util.SessionManager;

/**
 * 客户余额
 * @author xcy
 * @date 2017年11月25日
 */
@Controller
@RequestMapping("/trfc/customerRemainder")
public class CustomerRemainderAction {

	private Logger log=LoggerFactory.getLogger(CustomerRemainderAction.class);
	
	@Autowired
	private ICustomerRemainderQueryService icustomerRemainderQueryService;
	
	@Autowired
	private ISystemCodeService systemCodeService;
	
	@RequestMapping("/main")
	public ModelAndView main(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		view.setViewName("businessManage/financeManage/customerRemainder");
		try {
			SystemUserResp user = SessionManager.getSessionUser(request);
			view.addObject("user",user);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return view;
	}
	
	/**
	 * 分页查询
	 * xcy 
	 * @return Result
	 * @date 2017年11月27日
	 */
	@RequestMapping(value="/page",method=RequestMethod.POST)
	@ResponseBody
	public Result queryCustomerRemainder(CustomerRemainderQuery crQuery){
		Result result = Result.getSuccessResult();
		try {
			result = icustomerRemainderQueryService.pageQuery(crQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获取编号Action
	 * @param save
	 * @param session
	 * @return
	 */
	@RequestMapping("/initAdd")
	@ResponseBody
	public Result initAdd(CustomerRemainderSave save, HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			SystemUserResp user = SessionManager.getSessionUser(request);
			GetCodeReq codeReq = new GetCodeReq();
			codeReq.setCode("XSQC");
			codeReq.setCodeType(true);
			codeReq.setUserid(user.getId());
			map.put("code", systemCodeService.getCode(codeReq).getData());
			map.put("nowDate", DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
			map.put("collectionunit", Constant.ORG_NAME);
			map.put("makebillname", user.getName());
			result.setData(map);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	/**
	 * 客户余额新增
	 * xcy 
	 * @return Result
	 * @date 2017年11月27日
	 *//*
	@RequestMapping("/add")
	@ResponseBody
	public Result add(CustomerRemainderSave crSave){
		Result result = Result.getSuccessResult();
		result = icustomerRemainderQueryService.customRemainSave(crSave);
		return result;
	}
	
	*//**
	 * 审核一条余额数据
	 * xcy 
	 * @return Result
	 * @date 2017年11月27日
	 *//*
	@RequestMapping("/audit")
	@ResponseBody
	public Result queryById(CustomerRemainderQuery Query){
		Result result = Result.getSuccessResult();
		result = icustomerRemainderQueryService.queryById(Query);
		return result;
	}*/
}
