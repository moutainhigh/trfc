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
import com.tianrui.api.intf.system.auth.ISystemUserService;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationResp;
import com.tianrui.api.resp.system.auth.SystemUserResp;
import com.tianrui.quartz.common.ApiParamUtils;
import com.tianrui.quartz.common.HttpUtils;
import com.tianrui.service.bean.common.ReturnQueue;
import com.tianrui.service.bean.system.auth.SmUser;
import com.tianrui.service.mapper.common.ReturnQueueMapper;
import com.tianrui.service.mapper.system.auth.SmUserMapper;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;

@Service
public class TaskJobService {
	
	@Autowired
	private ReturnQueueMapper returnQueueMapper;
	@Autowired
	private ISalesApplicationService salesApplicationService;
	@Autowired
	private ISystemUserService systemUserService;
	@Autowired
	private SmUserMapper smUserMapper;

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
			List<SalesApplicationResp> listSales = salesApplicationService.selectByIds(new ArrayList<String>(dataIds), true);
			if(CollectionUtils.isNotEmpty(listSales)){
				List<SmUser> smUserList = null;
				for(SalesApplicationResp resp : listSales){
					smUserList = getSmUser(resp.getAuditid());
					if(CollectionUtils.isNotEmpty(smUserList)){
						resp.setAuditid(smUserList.get(0).getId());
					}
					smUserList = getSmUser(resp.getMakerid());
					if(CollectionUtils.isNotEmpty(smUserList)){
						resp.setMakerid(smUserList.get(0).getId());
					}
					smUserList = getSmUser(resp.getCreator());
					if(CollectionUtils.isNotEmpty(smUserList)){
						resp.setCreator(smUserList.get(0).getId());
					}
					smUserList = getSmUser(resp.getModifier());
					if(CollectionUtils.isNotEmpty(smUserList)){
						resp.setModifier(smUserList.get(0).getId());
					}
				}
			}
			ApiResult apiResult = HttpUtils.post(ApiParamUtils.getApiParam(listSales), Constant.URL_RETURN_SALESAPPLICATION);
			if(StringUtils.equals(apiResult.getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())){
				returnQueueMapper.deteleByIds(queueIds);
			}
		}
	}

	private List<SmUser> getSmUser(String id) throws Exception {
		List<SmUser> smUserList = null;
		SystemUserResp user = systemUserService.getUser(id);
		if(user != null){
			SmUser smUser = new SmUser();
			smUser.setCode(user.getCode());
			smUserList = smUserMapper.selectSelective(smUser);
		}
		return smUserList;
	}
}
