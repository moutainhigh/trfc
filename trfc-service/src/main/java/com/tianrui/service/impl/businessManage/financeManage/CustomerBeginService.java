package com.tianrui.service.impl.businessManage.financeManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.businessManage.financeManage.ICustomerBeginService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.businessManage.financeManage.CustomerBeginQuery;
import com.tianrui.api.req.businessManage.financeManage.CustomerBeginSave;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.businessManage.financeManage.CustomerBeginResp;
import com.tianrui.service.bean.businessManage.financeManage.CustomerBegin;
import com.tianrui.service.mapper.basicFile.nc.CustomerManageMapper;
import com.tianrui.service.mapper.businessManage.financeManage.CustomerBeginMapper;
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
	private ISystemCodeService systemCodeService;
	
	/**
	 * 分页查询
	 */
	@Override
	public Result page(CustomerBeginQuery query) throws Exception {
		Result result=Result.getParamErrorResult();
		//参数不能为空
		if(query!=null){
			PaginationVO<CustomerBeginResp> page=new PaginationVO<CustomerBeginResp>();
			//只查询没有删除的(state=1)
			query.setState("1");
			//获取数据总数
			Long count=customerBeginMapper.findCustomerBeginPageCount(query);
			//当总数大于0时进行分页查询
			if(count>0){
				query.setStart((query.getPageNo()-1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				//分页查询数据
				List<CustomerBegin> list=customerBeginMapper.findCustomerBeginPage(query);
				page.setList(copyBeanList2RespList(list));
				page.setTotal(count);
				page.setPageNo(query.getPageNo());
				page.setPageSize(query.getPageSize());
				//保存数据
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
	
	/**
	 * 新增客户期初
	 */
	@Transactional
	@Override
	public Result add(CustomerBeginSave save) throws Exception {
		Result result=Result.getSuccessResult();
		//参数不能为空
		if(save!=null){
			GetCodeReq codeReq = new GetCodeReq();
			codeReq.setCode("XSQC");
			codeReq.setCodeType(true);
			codeReq.setUserid(save.getMakeid());
			CustomerBegin bean=new CustomerBegin();
			bean.setCode(String.valueOf(systemCodeService.getCode(codeReq).getData()));
			//查询当前编号是否存在
			List<CustomerBegin> _list = customerBeginMapper.selectSelective(bean);
			//如果存在则提示错误信息
			if(_list != null && _list.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				return result;
			}
			bean=new CustomerBegin();
			bean.setState("1");
			bean.setCustomername(save.getCustomername());
			//查询客户是否已经存在(只查询未被删除的)
			List<CustomerBegin> list = customerBeginMapper.selectSelective(bean);
			//如果存在则提示错误信息
			if(list != null && list.size() > 0){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
				return result;
			}
			PropertyUtils.copyProperties(bean, save);
			bean.setId(UUIDUtil.getId());
			//未审核
			bean.setAuditstatus("0");
			bean.setOrgid(Constant.ORG_ID);
			bean.setOrgname(Constant.ORG_NAME);
			bean.setMakeid(save.getUser());
			bean.setMakebilltime(System.currentTimeMillis());
			bean.setState("1");
			bean.setCreator(save.getUser());
			bean.setCreatetime(System.currentTimeMillis());
			bean.setModifier(save.getUser());
			bean.setModifytime(System.currentTimeMillis());
			bean.setUtc(System.currentTimeMillis());
			//执行插入方法
			if(customerBeginMapper.insertSelective(bean)>0){
				//成功时保存数据
				result.setData(bean);
				//使编号自增
				if(!StringUtils.equals(systemCodeService.updateCodeItem(codeReq).getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())){
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}else{
				//失败时提示错误信息
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	/**
	 * 审核客户期初
	 */
	@Transactional
	@Override
	public Result audit(CustomerBeginQuery query) throws Exception {
		Result result=Result.getSuccessResult();
		//参数不能为空并且id能为空
		if(query!=null && StringUtils.isNotBlank(query.getId())){
			CustomerBegin bean=new CustomerBegin();
			bean.setId(query.getId());
			//已审核
			bean.setAuditstatus("1");
			bean.setAuditid(query.getAuditid());
			bean.setAuditname(query.getAuditname());
			bean.setAudittime(System.currentTimeMillis());
			bean.setModifier(query.getAuditid());
			bean.setModifytime(System.currentTimeMillis());
			//执行更新方法，失败时提示错误信息
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
	 * 删除客户期初
	 */
	@Transactional
	@Override
	public Result delete(CustomerBeginQuery query) throws Exception {
		Result result=Result.getSuccessResult();
		//参数不能为空并且id不能为空
		if(query!=null && StringUtils.isNotBlank(query.getId())){
			CustomerBegin bean=new CustomerBegin();
			bean.setId(query.getId());
			//设置state为0，执行假删除
			bean.setState("0");
			bean.setModifier(query.getUser());
			bean.setModifytime(System.currentTimeMillis());
			//执行更新方法，失败时提示错误信息
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
