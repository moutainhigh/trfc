package com.tianrui.service.impl.quality.file;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.quality.file.IQualityColumnService;
import com.tianrui.service.bean.quality.file.QualityColumn;
import com.tianrui.service.mapper.quality.file.QualityColumnMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class QualityColumnService implements IQualityColumnService {

	@Resource
	private QualityColumnMapper qualityColumnMapper;
	@Override
	public Result findOne(String id) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(StringUtils.isNotBlank(id)){
			QualityColumn qc = qualityColumnMapper.selectByPrimaryKey(id);
			rs = Result.getSuccessResult();
			rs.setData(qc);
		}
		return rs;
	}

	@Override
	public Result autoCompleteSearch(String likeName) throws Exception {
		Result rs = Result.getSuccessResult();
			List<QualityColumn> list = qualityColumnMapper.autoCompleteSearch(likeName.trim());
			if(list!=null){
				rs.setData(list);
			}else{
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		return rs;
	}

}
