package com.tianrui.web.action.quality.file;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.quality.file.IQualityColumnService;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

@Controller
@RequestMapping("/trfc/quality/sales/file/qualityColumn")
public class QualityColumnAction {
	Logger log = LoggerFactory.getLogger(QualityColumnAction.class);
	@Resource
	private IQualityColumnService qualityColumnService;
	
	/**
	 * 获取分页数据
	 */
	@ResponseBody
	@RequestMapping("/autoCompleteSearch")
	public Result autoCompleteSearch(String type){
		Result rs = Result.getErrorResult();
		try {
			rs = qualityColumnService.autoCompleteSearch(type.trim());
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			rs.setErrorCode(ErrorCode.SYSTEM_ERROR);
		}
		return rs;
	}
}
