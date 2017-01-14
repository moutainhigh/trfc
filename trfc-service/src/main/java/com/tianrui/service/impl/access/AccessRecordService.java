package com.tianrui.service.impl.access;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.access.IAccessRecordService;
import com.tianrui.api.req.access.AccessRecordReq;
import com.tianrui.service.bean.access.AccessRecord;
import com.tianrui.service.mapper.access.AccessRecordMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class AccessRecordService implements IAccessRecordService {

	@Autowired
	private AccessRecordMapper accessRecordMapper;
	
	/**
	 * 新增数据
	 */
	@Transactional
	@Override
	public Result add(AccessRecordReq req) {
		Result result = Result.getParamErrorResult();
		if(req!=null && StringUtils.isNotBlank(req.getSalesarriveid()) 
				&& StringUtils.isNotBlank(req.getIcardid())){
			AccessRecord record = new AccessRecord();
			//类型转换
			try {
				PropertyUtils.copyProperties(record, req);
			} catch (Exception e) {
				e.printStackTrace();
				result.setErrorCode(ErrorCode.PARAM_ERROR);
				return result;
			}
			record.setId(UUIDUtil.getId());
			//此处添加 创建者 创建时间 修改者 修改时间
			
			int index = accessRecordMapper.insertSelective(record);
			//判断操作失败
			if(index<=0){
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
				return result;
			}
			//操作成功
			result = Result.getSuccessResult();
			result.setData(index>0);
		}
		return result;
	}

}
