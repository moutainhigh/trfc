package com.tianrui.web.api.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.handSetStatic.IHandSetStaticService;
import com.tianrui.api.intf.system.auth.ISystemUserService;
import com.tianrui.api.req.businessManage.handset.HandSetRequestParam;
import com.tianrui.api.req.system.auth.UserReq;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("api/handset/static")
public class HandSetStaticAction {
	
	private Logger log = LoggerFactory.getLogger(HandSetStaticAction.class);
	
	@Autowired
	private IHandSetStaticService handSetStaticService;
	@Autowired
	private ISystemUserService systemUserService;

	@RequestMapping(value="/login",method=RequestMethod.POST)
    @ResponseBody
    public ApiResult login(UserReq req){
        Result rs = Result.getErrorResult();
        try {
            rs = systemUserService.login(req);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
        }
        return ApiResult.valueOf(rs);
    }
	
	@RequestMapping(value="/readICard",method = RequestMethod.POST)
	@ResponseBody
	public ApiResult readICard(HandSetRequestParam param){
		Result rs = Result.getErrorResult();
		try {
		    rs = handSetStaticService.readICard(param);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return ApiResult.valueOf(rs);
	}
	
	@RequestMapping(value="/receive", method = RequestMethod.POST)
	@ResponseBody
	public ApiResult receive(HandSetRequestParam param) {
	    Result rs = Result.getErrorResult();
        try {
            rs = handSetStaticService.receive(param);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
        }
        return ApiResult.valueOf(rs);
	}

}
