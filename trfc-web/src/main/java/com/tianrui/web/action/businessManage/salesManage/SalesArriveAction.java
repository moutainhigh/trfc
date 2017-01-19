package com.tianrui.web.action.businessManage.salesManage;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.measure.IDriverManageService;
import com.tianrui.api.intf.basicFile.measure.IVehicleManageService;
import com.tianrui.api.intf.basicFile.nc.ICustomerManageService;
import com.tianrui.api.intf.basicFile.nc.IMaterielManageService;
import com.tianrui.api.intf.businessManage.salesManage.ISalesArriveService;
import com.tianrui.api.req.basicFile.nc.CustomerManageQuery;
import com.tianrui.api.req.businessManage.salesManage.SalesArriveQuery;
import com.tianrui.api.req.businessManage.salesManage.SalesArriveSave;
import com.tianrui.api.resp.businessManage.salesManage.SalesArriveResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.DateUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

/**
 * 销售通知单
 * @author Administrator
 *
 */
@RequestMapping("/trfc/salesArrive")
@Controller
public class SalesArriveAction {

	private Logger log = LoggerFactory.getLogger(SalesArriveAction.class);
	
	@Autowired
	private ISalesArriveService salesArriveService;
	@Autowired
	private IVehicleManageService vehicleManageService; 
	@Autowired
	private IMaterielManageService materielManageService; 
	@Autowired
	private IDriverManageService driverManageService; 
	@Autowired
	private ICustomerManageService customerManageService; 
	
	@RequestMapping("/main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("businessManage/salesManage/salesArrive");
		try {
			view.addObject("customer", customerManageService.findListByParmas(null).getData());
			view.addObject("vehicle", vehicleManageService.findListByParmas(null).getData());
			view.addObject("materiel", materielManageService.findListByParmas(null).getData());
			view.addObject("driver", driverManageService.findListByParmas(null).getData());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return view;
	}
	
	@RequestMapping("/page")
	@ResponseBody
	public Result page(SalesArriveQuery query){
		Result result = Result.getSuccessResult();
		try {
			PaginationVO<SalesArriveResp> page = salesArriveService.page(query);
			result.setData(page);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/addView")
	public ModelAndView addView(){
		ModelAndView view = new ModelAndView("businessManage/salesManage/salesArriveAdd");
		try {
			view.addObject("code", "TH"+(int)(Math.random()*1000000));
			view.addObject("v_code", "CL"+(int)(Math.random()*1000000));
			view.addObject("d_code", "DR"+(int)(Math.random()*100000000));
			view.addObject("d_internalcode", ((int)(Math.random()*1000000)+"").substring(2));
			view.addObject("orgid", "0");
			view.addObject("orgname", "天瑞集团");
			view.addObject("createtimeStr", DateUtil.getNowDateString("yyyy-MM-dd HH:mm:ss"));
			view.addObject("materiel", materielManageService.findListByParmas(null).getData());
			view.addObject("customer", customerManageService.findListByParmas(null).getData());
			view.addObject("vehicle", vehicleManageService.findListByParmas(null).getData());
			view.addObject("driver", driverManageService.findListByParmas(null).getData());
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return view;
	}

	@RequestMapping("/add")
	@ResponseBody
	public Result add(SalesArriveSave save, HttpSession session){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = (SystemUserResp) session.getAttribute("systemUser");
			save.setCurrUId(user.getId());
			result = salesArriveService.add(save);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/updateView")
	public ModelAndView updateView(String id){
		ModelAndView view = new ModelAndView("businessManage/salesManage/salesArriveUpdate");
		try {
			view.addObject("v_code", "CL"+(int)(Math.random()*1000000));
			view.addObject("d_code", "DR"+(int)(Math.random()*100000000));
			view.addObject("d_internalcode", ((int)(Math.random()*1000000)+"").substring(2));
			view.addObject("orgid", "0");
			view.addObject("orgname", "天瑞集团");
			view.addObject("materiel", materielManageService.findListByParmas(null).getData());
			view.addObject("customer", customerManageService.findListByParmas(null).getData());
			view.addObject("vehicle", vehicleManageService.findListByParmas(null).getData());
			view.addObject("driver", driverManageService.findListByParmas(null).getData());
			SalesArriveQuery query = new SalesArriveQuery();
			query.setId(id);
			view.addObject("salesArrive", salesArriveService.findOne(query));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return view;
	}

	@RequestMapping("/update")
	@ResponseBody
	public Result update(SalesArriveSave save, HttpSession session){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = (SystemUserResp) session.getAttribute("systemUser");
			save.setCurrUId(user.getId());
			result = salesArriveService.update(save);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/audit")
	@ResponseBody
	public Result audit(SalesArriveQuery query, HttpSession session){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = (SystemUserResp) session.getAttribute("systemUser");
			query.setCurrUId(user.getId());
			result = salesArriveService.audit(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/unaudit")
	@ResponseBody
	public Result unaudit(SalesArriveQuery query, HttpSession session){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = (SystemUserResp) session.getAttribute("systemUser");
			query.setCurrUId(user.getId());
			result = salesArriveService.unaudit(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/invalid")
	@ResponseBody
	public Result invalid(SalesArriveQuery query, HttpSession session){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = (SystemUserResp) session.getAttribute("systemUser");
			query.setCurrUId(user.getId());
			result = salesArriveService.invalid(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/outfactory")
	@ResponseBody
	public Result outfactory(SalesArriveQuery query, HttpSession session){
		Result result = Result.getSuccessResult();
		try {
			SystemUserResp user = (SystemUserResp) session.getAttribute("systemUser");
			query.setCurrUId(user.getId());
			result = salesArriveService.outfactory(query);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}

	@RequestMapping("/detailView")
	public ModelAndView detailView(String id){
		ModelAndView view = new ModelAndView("businessManage/salesManage/salesArriveDetail");
		try {
			SalesArriveQuery query = new SalesArriveQuery();
			query.setId(id);
			view.addObject("salesArrive", salesArriveService.findOne(query));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return view;
	}
	
}
