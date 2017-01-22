package com.tianrui.web.api.subsystem.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.tianrui.api.intf.common.IFileService;
import com.tianrui.api.req.common.FileUploadReq;
import com.tianrui.smartfactory.common.api.ApiParam;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

@RequestMapping("trfc/upload")
@Controller
public class ApiUploadFileAction {
	
	private Logger log = LoggerFactory.getLogger(ApiUploadFileAction.class);
	
	@Autowired
	private IFileService fileService;
	
	
	@RequestMapping("image")
	@ResponseBody
	public ApiResult uploadImg(ApiParam<FileUploadReq> apiParam, MultipartFile file){
		FileUploadReq req = apiParam.getBody();
		req.setuId(apiParam.getHead().getUserId());
		Result rs=Result.getErrorResult();
		try {
			req.setFileByte(file.getBytes());
			rs = fileService.uploadImg(req);
		} catch (Exception e) {
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
			log.error(e.getMessage(),e);
		}
		return ApiResult.valueOf(rs);
	}
	
}
