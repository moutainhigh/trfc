package com.tianrui.service.impl.businessManage.financeManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.businessManage.financeManage.ICustomerBeginService;
import com.tianrui.api.req.businessManage.financeManage.CustomerBeginQuery;
import com.tianrui.api.req.businessManage.financeManage.CustomerBeginSave;
import com.tianrui.api.resp.businessManage.financeManage.CustomerBeginResp;
import com.tianrui.service.bean.basicFile.nc.CustomerManage;
import com.tianrui.service.bean.businessManage.financeManage.CustomerBegin;
import com.tianrui.service.mapper.basicFile.nc.CustomerManageMapper;
import com.tianrui.service.mapper.businessManage.financeManage.CustomerBeginMapper;
import com.tianrui.service.mapper.system.auth.SystemUserMapper;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
/**
 * 客户期初Service
 * @author YangZhenFu
 * @createtime 2017年3月6日 下午15:23:52
 * @classname CustomerBeginService.java
 */
@Service
public class CustomerBeginService implements ICustomerBeginService{
	
	@Autowired
	CustomerBeginMapper customerBeginMapper;
	@Autowired 
	CustomerManageMapper customerManageMapper;
	@Autowired
	SystemUserMapper systemUserMapper;
	
	@Override
	public Result page(CustomerBeginQuery query) throws Exception {
		Result result=Result.getParamErrorResult();
		if(query!=null){
			PaginationVO<CustomerBeginResp> page=new PaginationVO<CustomerBeginResp>();
			query.setState("1");
			Long count=customerBeginMapper.findCustomerBeginPageCount(query);
			if(count>0){
				query.setStart((query.getPageNo()-1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				List<CustomerBegin> list=customerBeginMapper.findCustomerBeginPage(query);
				page.setList(copyBeanList2RespList(list));
				page.setTotal(count);
				page.setPageNo(query.getPageNo());
				page.setPageSize(query.getPageSize());
				result.setData(page);
			}else{
				page.setTotal(count);
				page.setPageNo(query.getPageNo());
				page.setPageSize(query.getPageSize());
				result.setData(page);
			}
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return result;
	}
	
	@Transactional
	@Override
	public Result add(CustomerBeginSave save) throws Exception {
		Result result=Result.getSuccessResult();
		if(save!=null){
			CustomerManage customer=new CustomerManage();
			customer.setState("1");
			customer.setName(save.getCustomername());
			List<CustomerManage> list=customerManageMapper.selectSelective(customer);
			if(list!=null && list.size()>0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				return result;
			}
			CustomerBegin bean=new CustomerBegin();
			PropertyUtils.copyProperties(bean, save);
			bean.setId(UUIDUtil.getId());
			//未审核
			bean.setAuditstatus("0");
			bean.setOrgid(Constant.ORG_ID);
			bean.setOrgname(Constant.ORG_NAME);
			bean.setBilldate(System.currentTimeMillis());
			bean.setMakeid(save.getUser());
			bean.setMakebilltime(System.currentTimeMillis());
			bean.setState("1");
			bean.setCreator(save.getUser());
			bean.setCreatetime(System.currentTimeMillis());
			bean.setModifier(save.getUser());
			bean.setModifytime(System.currentTimeMillis());
			bean.setUtc(System.currentTimeMillis());
			if(customerBeginMapper.insertSelective(bean)>0){
				result.setData(bean);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	@Transactional
	@Override
	public Result audit(CustomerBeginQuery query) throws Exception {
		Result result=Result.getSuccessResult();
		if(query!=null && StringUtils.isNotBlank(query.getId())){
			CustomerBegin bean=new CustomerBegin();
			bean.setId(query.getId());
			bean.setAuditstatus("1");
			bean.setAuditid(query.getAuditid());
			bean.setAuditname(query.getAuditname());
			bean.setAudittime(System.currentTimeMillis());
			bean.setModifier(query.getAuditid());
			bean.setModifytime(System.currentTimeMillis());
			if(customerBeginMapper.updateByPrimaryKeySelective(bean)>0){
				result.setData("操作成功！");
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}else{
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}
	
	@Transactional
	@Override
	public Result delete(CustomerBeginQuery query) throws Exception {
		Result result=Result.getSuccessResult();
		if(query!=null && StringUtils.isNotBlank(query.getId())){
			CustomerBegin bean=new CustomerBegin();
			bean.setId(query.getId());
			bean.setState("0");
			bean.setModifier(query.getUser());
			bean.setModifytime(System.currentTimeMillis());
			if(customerBeginMapper.updateByPrimaryKeySelective(bean)>0){
				result.setData("操作成功！");
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}else{
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;
	}
	
	
	/**
	 * 集合转换
	 * @param List<CustomerBegin> list
	 * @return List<CustomerBeginResp> 
	 * @throws Exception
	 */
	private List<CustomerBeginResp> copyBeanList2RespList(List<CustomerBegin> list) throws Exception {
		List<CustomerBeginResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<CustomerBeginResp>();
			for(CustomerBegin begin : list){
				listResp.add(copyBeanList2RespList(begin));
			}
		}
		return listResp;
	}
	
	
	
	/**
	 * 实体bean类型转换
	 * @param CustomerBegin bean
	 * @return CustomerBeginResp
	 * @throws Exception
	 */
	private CustomerBeginResp copyBeanList2RespList(CustomerBegin bean) throws Exception {
		CustomerBeginResp resp = null;
		if(bean != null){
			resp = new CustomerBeginResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}
}
