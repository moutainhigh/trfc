package com.tianrui.quartz.service;

import java.util.ArrayList;
import java.util.Date;
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
import com.tianrui.service.bean.basicFile.nc.CustomerManage;
import com.tianrui.service.bean.basicFile.nc.SupplierManage;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplication;
import com.tianrui.service.bean.common.ReturnQueue;
import com.tianrui.service.bean.system.auth.SmUser;
import com.tianrui.service.bean.system.auth.SystemUser;
import com.tianrui.service.mapper.basicFile.nc.CustomerManageMapper;
import com.tianrui.service.mapper.basicFile.nc.SupplierManageMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationMapper;
import com.tianrui.service.mapper.common.ReturnQueueMapper;
import com.tianrui.service.mapper.system.auth.SmUserMapper;
import com.tianrui.service.mapper.system.auth.SystemUserMapper;
import com.tianrui.smartfactory.common.api.ApiResult;
import com.tianrui.smartfactory.common.constants.BusinessConstants;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.Md5Utils;

@Service
public class TaskJobService {
	
	@Autowired
	private ReturnQueueMapper returnQueueMapper;
	@Autowired
	private ISalesApplicationService salesApplicationService;
	@Autowired
	private SalesApplicationMapper salesApplicationMapper;
	@Autowired
	private ISystemUserService systemUserService;
	@Autowired
	private SmUserMapper smUserMapper;
	@Autowired
	private CustomerManageMapper customerManageMapper;
	@Autowired
	private SupplierManageMapper supplierManageMapper;
	@Autowired
	private SystemUserMapper systemUserMapper;

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
				if(returnQueueMapper.deteleByIds(queueIds) > 0){
					SalesApplication application = new SalesApplication();
					for(ReturnQueue queue : list){
						application.setId(queue.getDataid());
						application.setSource("0");
						salesApplicationMapper.updateByPrimaryKeySelective(application);
					}
				}
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

	@Transactional
	public void customer_supplier_user() throws Exception {
		List<CustomerManage> customerList = customerManageMapper.findCustomerNotSystemUser();
		List<SupplierManage> supplierList = supplierManageMapper.findSupplierNotSystemUser();
		List<SystemUser> list = new ArrayList<SystemUser>();
		if(CollectionUtils.isNotEmpty(customerList)){
			list.clear();
			for(CustomerManage cm : customerList){
				SystemUser user = new SystemUser();
				user.setId(cm.getId());
				user.setCode("");
				user.setName(cm.getName());
				user.setAccount(cm.getCode());
				user.setPassword(Md5Utils.MD5("666666"));
				user.setIdentityTypes("1");
				user.setMobilePhone(" ");
				user.setOrgid(Constant.ORG_ID);
				user.setSource("1");
				user.setIsvalid(BusinessConstants.USER_VALID_BYTE);
				user.setCreator("0000");
				user.setCreatetime(System.currentTimeMillis());
				user.setUtc(new Date());
				list.add(user);
			}
			int count = systemUserMapper.insertBatch(list);
			System.out.println("客户同步到用户表成功：共"+count+"条！");
		}
		if(CollectionUtils.isNotEmpty(supplierList)){
			list.clear();
			for(SupplierManage sm : supplierList){
				if (systemUserMapper.selectByPrimaryKey(sm.getId()) == null) {
					SystemUser user = new SystemUser();
					user.setId(sm.getId());
					user.setCode("");
					user.setAccount(sm.getCode());
					user.setName(sm.getName());
					user.setPassword(Md5Utils.MD5("666666"));
					user.setIdentityTypes("2");
					user.setMobilePhone(" ");
					user.setOrgid(Constant.ORG_ID);
					user.setSource("1");
					user.setIsvalid(BusinessConstants.USER_VALID_BYTE);
					user.setCreator("0000");
					user.setCreatetime(System.currentTimeMillis());
					user.setUtc(new Date());
					list.add(user);
				}
			}
			int count = systemUserMapper.insertBatch(list);
			System.out.println("供应商同步到用户表成功：共"+count+"条！");
		}
		
	}
}
