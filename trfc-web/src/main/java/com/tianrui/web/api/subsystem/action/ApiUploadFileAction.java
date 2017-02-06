package com.tianrui.web.api.subsystem.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.common.IFileService;
import com.tianrui.api.req.common.FileUploadReq;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;
import com.tianrui.web.smvc.ApiAuthValidation;
import com.tianrui.web.smvc.ApiParamRawType;

@RequestMapping("api/upload")
@Controller
public class ApiUploadFileAction {
	
	private Logger log = LoggerFactory.getLogger(ApiUploadFileAction.class);
	
	@Autowired
	private IFileService fileService;
	
	
	@RequestMapping(value="/image",method=RequestMethod.POST)
	@ApiParamRawType(FileUploadReq.class)
	@ApiAuthValidation(callType="2")
	@ResponseBody
	public ApiResult uploadImg(ApiParam<FileUploadReq> apiParam){
		log.info("进入上传图片接口...", apiParam);
		FileUploadReq req = apiParam.getBody();
		req.setuId(apiParam.getHead().getUserId());
		Result rs=Result.getErrorResult();
		try {
			rs = fileService.uploadImg(req);
			log.info("上传图片返回结果...", rs);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}	
		return ApiResult.valueOf(rs);
	}
	
}
