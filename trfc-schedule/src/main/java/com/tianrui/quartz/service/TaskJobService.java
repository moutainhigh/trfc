package com.tianrui.quartz.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.intf.businessManage.salesManage.ISalesApplicationService;
import com.tianrui.api.req.businessManage.salesManage.SalesApplicationQuery;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationResp;
import com.tianrui.service.bean.common.ReturnQueue;
import com.tianrui.service.mapper.common.ReturnQueueMapper;

@Service
public class TaskJobService {
	
	@Autowired
	private ReturnQueueMapper returnQueueMapper;
	
	@Autowired
	private ISalesApplicationService salesApplicationService;

	public void returnDataCenter() throws Exception{
		List<ReturnQueue> list = returnQueueMapper.selectSelective(null);
		if(list != null && list.size() > 0){
			for(ReturnQueue rq : list){
				String dataType = rq.getDatatype();
				switch (dataType) {
				case "0":
					returnSalesApplication(rq);
					break;
				default:
					break;
				}
			}
		}
	}

	@Transactional
	private void returnSalesApplication(ReturnQueue rq) throws Exception {
		if(rq != null){
			if(StringUtils.isNotBlank(rq.getDataid())){
				SalesApplicationQuery query = new SalesApplicationQuery();
				query.setId(rq.getDataid());
				SalesApplicationResp resp = salesApplicationService.findOne(query);
				System.out.println(JSON.toJSONString(resp));
				
				//回传成功从队列里面删除
				returnQueueMapper.deleteByPrimaryKey(rq.getId());
			}
		}
	}
}
