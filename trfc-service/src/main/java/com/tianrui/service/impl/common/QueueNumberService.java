package com.tianrui.service.impl.common;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.common.IQueueNumberService;
import com.tianrui.api.resp.common.QueueNumberResp;
import com.tianrui.service.mapper.common.QueueNumberMapper;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class QueueNumberService implements IQueueNumberService {

	@Autowired
	private QueueNumberMapper queueNumberMapper;
	
	@Override
	public Result queryWaiting() {
		Result result = Result.getSuccessResult();
		List<QueueNumberResp> list = queueNumberMapper.queryWaiting();
		if (CollectionUtils.isNotEmpty(list)) {
			result.setData(list);
		}
		return result;
	}

}
