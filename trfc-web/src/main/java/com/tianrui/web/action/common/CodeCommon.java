package com.tianrui.web.action.common;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("/trfc/common/code")
public class CodeCommon {

	
	private Logger log = LoggerFactory.getLogger(CodeCommon.class);
	
	@RequestMapping("/vehicleCode")
	@ResponseBody
	public Result vehicleCode() throws Exception{
		Result result = Result.getSuccessResult();
		try {
			String code = "CL"+(int)(Math.random()*1000000);
			
			result.setData(code);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	@RequestMapping("/cardCode")
	@ResponseBody
	public Result cardCode() throws Exception{
		Result result = Result.getSuccessResult();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("nowtime", System.currentTimeMillis());
			map.put("code", "IC"+(int)(Math.random()*1000000));
			result.setData(map);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	@RequestMapping("/driverCode")
	@ResponseBody
	public Result driverCode() throws Exception{
		Result result = Result.getSuccessResult();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String code = (int)(Math.random()*100000000)+"";
			map.put("code", "DR"+code);
			map.put("internalcode", code.substring(2));
			result.setData(map);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/transportCode")
	@ResponseBody
	public Result transportCode() throws Exception{
		Result result = Result.getSuccessResult();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String code = (int)(Math.random()*100000000)+"";
			map.put("code", "TC"+code);
			map.put("internalcode", code.substring(2));
			result.setData(map);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	@RequestMapping("/minemouthCode")
	@ResponseBody
	public Result minemouthCode() throws Exception{
		Result result = Result.getSuccessResult();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String code = (int)(Math.random()*100000000)+"";
			map.put("code", "KD"+code);
			result.setData(map);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			result.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return result;
	}
}
