package com.tianrui.quartz.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.businessManage.salesManage.ISalesApplicationService;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationResp;
import com.tianrui.quartz.common.ApiParamUtils;
import com.tianrui.quartz.common.HttpUtils;
import com.tianrui.service.bean.common.ReturnQueue;
import com.tianrui.service.mapper.common.ReturnQueueMapper;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;

@Service
public class TaskJobService {
	
	@Autowired
	private ReturnQueueMapper returnQueueMapper;
	
	@Autowired
	private ISalesApplicationService salesApplicationService;

	public void returnDataCenter() throws Exception{
		List<ReturnQueue> list = returnQueueMapper.selectSelective(null);
		//销售申请单回传
		returnSalesApplication(groupList(list, "0"));
	}
	
	private List<ReturnQueue> groupList(List<ReturnQueue> list, String dataType){
		List<ReturnQueue> groupList = null;
		if(CollectionUtils.isNotEmpty(list)){
			groupList = new ArrayList<ReturnQueue>();
			for(ReturnQueue rq : list){
				//销售申请单
				if(StringUtils.equals(rq.getDatatype(), dataType)){
					groupList.add(rq);
				}
			}
		}
		return groupList;
	}

	@Transactional
	private void returnSalesApplication(List<ReturnQueue> list) throws Exception {
		if(list != null){
			Set<String> dataIds = new HashSet<String>();
			List<String> queueIds = new ArrayList<String>();
			for(ReturnQueue rq : list){
				dataIds.add(rq.getDataid());
				queueIds.add(rq.getId());
			}
			List<SalesApplicationResp> listSales = salesApplicationService.selectByIds(new ArrayList<String>(dataIds));
			ApiResult apiResult = HttpUtils.post(ApiParamUtils.getApiParam(listSales), Constant.URL_RETURN_SALESAPPLICATION);
			if(StringUtils.equals(apiResult.getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())){
				returnQueueMapper.deteleByIds(queueIds);
			}
		}
	}
}
