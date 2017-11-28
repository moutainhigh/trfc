package com.tianrui.service.impl.businessManage.financeManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.businessManage.financeManage.ICustomerRemainderQueryService;
import com.tianrui.api.req.businessManage.financeManage.CustomerRemainderQuery;
import com.tianrui.api.req.businessManage.financeManage.CustomerRemainderSave;
import com.tianrui.api.resp.businessManage.financeManage.CustomerRemainderResp;
import com.tianrui.service.bean.businessManage.financeManage.CustomerRemainder;
import com.tianrui.service.mapper.businessManage.financeManage.CustomerRemainderMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
@Service
public class CustomerRemainderQueryService implements ICustomerRemainderQueryService {

	@Autowired
	private CustomerRemainderMapper customerRemainderMapper;
	/**
	 * 分页查询客户余额
	 */
	@Override
	public Result pageQuery(CustomerRemainderQuery crQuery) throws Exception {
		Result result=Result.getParamErrorResult();
		//参数不能为空
		if(crQuery!=null){
			PaginationVO<CustomerRemainderResp> page=new PaginationVO<CustomerRemainderResp>();
			//只查询有效的(status=Y)
			crQuery.setStatus("Y");
			//获取数据的总条数
			Long count=customerRemainderMapper.findCustomerRemainderPageCount(crQuery);
			//当总数大于0时进行分页查询
			if(count>0){
				crQuery.setStart((crQuery.getPageNo()-1)*crQuery.getPageSize());
				crQuery.setLimit(crQuery.getPageSize());
				//分页查询数据
				List<CustomerRemainder> list=customerRemainderMapper.findCustomerRemainderPage(crQuery);
				page.setList(copyBeanList2RespList(list));
				page.setTotal(count);
				page.setPageNo(crQuery.getPageNo());
				page.setPageSize(crQuery.getPageSize());
				//保存数据
				result.setData(page);
			}else{
				page.setTotal(count);
				page.setPageNo(crQuery.getPageNo());
				page.setPageSize(crQuery.getPageSize());
				result.setData(page);
			}
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return result;
	}

	/**
	 * 新增客户余额
	 *//*
	@Override
	public Result customRemainSave(CustomerRemainderSave crSave) {
		Result result = Result.getParamErrorResult();
		if(crSave != null){
			
		}else{
			
		}
		return result;
	}

	*//**
	 * 审核一条数据
	 *//*
	@Override
	public Result queryById(CustomerRemainderQuery query) {
		Result result = Result.getParamErrorResult();
		if(query.getId()!=null && StringUtils.isNotBlank(query.getId()) ){
			CustomerRemainder customerRemaind = customerRemainderMapper.selectByPrimaryKey(query.getId());
			result.setData(customerRemaind);
			
		}
		return result;
	}*/
	
	/**
	 * 集合转换
	 * xcy 
	 * @return List<CustomerRemainderResp>
	 * @date 2017年11月27日
	 */
	private List<CustomerRemainderResp> copyBeanList2RespList(List<CustomerRemainder> list) throws Exception{
		List<CustomerRemainderResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<CustomerRemainderResp>();
			for(CustomerRemainder bean : list){
				listResp.add(copyBeanList2RespList(bean));
			}
		}
		return listResp;
	}
	
	/**
	 * 实体bean类型转换
	 * xcy 
	 * @return CustomerRemainderResp
	 * @date 2017年11月27日
	 */
	private CustomerRemainderResp copyBeanList2RespList(CustomerRemainder bean) throws Exception {
		CustomerRemainderResp resp = null;
		if(bean != null){
			resp = new CustomerRemainderResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}

}
