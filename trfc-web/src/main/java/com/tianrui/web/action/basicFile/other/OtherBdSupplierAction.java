package com.tianrui.web.action.basicFile.other;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.basicFile.other.IOtherBdSupplierService;
import com.tianrui.api.req.basicFile.other.OtherBdSupplierReq;
import com.tianrui.api.resp.basicFile.other.OtherBdSupplierResp;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("/trfc/basicFile/other/supplier")
public class OtherBdSupplierAction {
	Logger Logger = LoggerFactory.getLogger(OtherBdSupplierAction.class);
	@Resource
	private IOtherBdSupplierService otherBdSupplierService;
	
	@RequestMapping("/main")
	public ModelAndView main(){
		return new ModelAndView("/basicFile/other/supplier");
	}
	/**
	 * 分页数据显示
	 */
	@RequestMapping("/page")
	@ResponseBody
	public Result page(OtherBdSupplierReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = otherBdSupplierService.page(req);
		} catch (Exception e) {
			Logger.error(e.getMessage(),e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
	}
	/**
	 * 新增数据
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Result add(OtherBdSupplierReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = otherBdSupplierService.add(req);
		} catch (Exception e) {
			Logger.error(e.getMessage(),e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
	}
	/**
	 * 更新数据
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Result update(OtherBdSupplierReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = otherBdSupplierService.update(req);
		} catch (Exception e) {
			Logger.error(e.getMessage(),e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
	}
	/**
	 * 删除数据
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(OtherBdSupplierReq req){
		Result rs = Result.getErrorResult();
		try {
			rs = otherBdSupplierService.delete(req);
		} catch (Exception e) {
			Logger.error(e.getMessage(),e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
	}
	/**
	 * 获取内码 和编码
	 */
	@RequestMapping("/code")
	@ResponseBody
	public Result code(){
		Result rs = Result.getSuccessResult();
		OtherBdSupplierResp resp = new OtherBdSupplierResp();
		resp.setCode("CD"+(int)(Math.random()*10000));
		resp.setInnercode("ICD"+(int)(Math.random()*10000));
		rs.setData(resp);
		return rs;
	}
}
