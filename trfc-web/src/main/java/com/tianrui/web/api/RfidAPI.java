package com.tianrui.web.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.tianrui.api.intf.common.IRFIDService;
import com.tianrui.api.req.common.RFIDReq;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.action.basicFile.measure.VehicleManageAction;

@Controller
@RequestMapping("api/rfid")
public class RfidAPI {

	private Logger log = LoggerFactory.getLogger(VehicleManageAction.class);
	
	@Autowired
	private IRFIDService irfidService;
	
	@RequestMapping("save")
	@ResponseBody
	public Result save(RFIDReq req){
		Result result = Result.getSuccessResult();
		try {
			int n = irfidService.save(req);
			if(n > 0){
				result.setData(n);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
			return result;
		}
		return result;
	}
	
	@RequestMapping("saveList")
	@ResponseBody
	public Result saveList(String jsonList){
		Result result = Result.getSuccessResult();
		try {
			List<RFIDReq> list = JSONArray.parseArray(jsonList, RFIDReq.class);
			int n = irfidService.saveList(list);
			if(n > 0){
				result.setData(n);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
			return result;
		}
		return result;
	}
}