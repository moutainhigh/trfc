package com.tianrui.web.action.businessManage.salesManage;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.businessManage.salesManage.ISalesApplicationService;
import com.tianrui.api.req.businessManage.salesManage.SalesApplicationQuery;
import com.tianrui.api.req.businessManage.salesManage.SalesApplicationSave;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@RequestMapping("salesApplication")
@Controller
public class SalesApplicationAction {

	private Logger log = LoggerFactory.getLogger(SalesApplicationAction.class);
	
	@Autowired
	private ISalesApplicationService salesApplicationService;
	
	@RequestMapping("main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("businessManage/salesManage/salesApplication");
		return view;
	}
	
	@RequestMapping("page")
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
	
	@RequestMapping("initAdd")
	@ResponseBody
	public Result initAdd(SalesApplicationSave req){
		Result result = Result.getSuccessResult();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", "XXS"+(DateUtil.getNowDateString("yyyyMMddHHmmss")));
			map.put("orgid", "0");
			map.put("orgname", "天瑞集团");
			map.put("creator", "0");
			map.put("creatname", "try");
			map.put("nowDate", DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
			result.setData(map);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("add")
	@ResponseBody
	public Result add(SalesApplicationSave req){
		Result result = Result.getSuccessResult();
		try {
			result = salesApplicationService.add(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("update")
	@ResponseBody
	public Result update(SalesApplicationSave req){
		Result result = Result.getSuccessResult();
		try {
			result = salesApplicationService.update(req);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("audit")
	@ResponseBody
	public Result audit(String id){
		Result result = Result.getSuccessResult();
		try {
			result = salesApplicationService.audit(id);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("unaudit")
	@ResponseBody
	public Result unaudit(String id){
		Result result = Result.getSuccessResult();
		try {
			result = salesApplicationService.unaudit(id);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("delete")
	@ResponseBody
	public Result delete(String id){
		Result result = Result.getSuccessResult();
		try {
			result = salesApplicationService.delete(id);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
}
