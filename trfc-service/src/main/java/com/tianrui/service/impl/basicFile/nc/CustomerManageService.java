package com.tianrui.service.impl.basicFile.nc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.basicFile.nc.ICustomerManageService;
import com.tianrui.api.req.basicFile.nc.CustomerManageQuery;
import com.tianrui.api.req.basicFile.nc.CustomerManageSave;
import com.tianrui.api.req.basicFile.nc.WarehouseManageQuery;
import com.tianrui.api.resp.basicFile.nc.CustomerManageResp;
import com.tianrui.service.bean.basicFile.nc.CustomerManage;
import com.tianrui.service.bean.basicFile.nc.WarehouseManage;
/**
 * 客户管理Service
 * @author zhanggaohao
 * @createtime 2016年12月27日 下午3:52:54
 * @classname CustomerManageService.java
 */
import com.tianrui.service.mapper.basicFile.nc.CustomerManageMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
@Service
public class CustomerManageService implements ICustomerManageService {

	@Autowired
	private CustomerManageMapper customerManageMapper;
	
	@Override
	public PaginationVO<CustomerManageResp> page(CustomerManageQuery query) throws Exception {
		PaginationVO<CustomerManageResp> page = null;
		if(query != null){
			page = new PaginationVO<CustomerManageResp>();
			long count = customerManageMapper.findCustomerPageCount(query);
			if(count > 0){
				query.setStart((query.getPageNo()-1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				List<CustomerManage> list = customerManageMapper.findCustomerPage(query);
				page.setList(copyBeanList2RespList(list));
			}
			page.setTotal(count);
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
		}
		return page;
	}
	
	@Override
	public Result updateCustomer(CustomerManageSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if(save != null){
			CustomerManage c = new CustomerManage();
			PropertyUtils.copyProperties(c, save);
			//c.setModifier("");
			c.setModifytime(System.currentTimeMillis());
			if(customerManageMapper.updateByPrimaryKeySelective(c) > 0){
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	@Override
	public Result findListByParmas(CustomerManageQuery query) throws Exception {
		Result result = Result.getSuccessResult();
		CustomerManage cm = new CustomerManage();
		if(query != null){
			PropertyUtils.copyProperties(cm, query);
		}
		List<CustomerManage> list = customerManageMapper.selectSelective(cm);
		result.setData(copyBeanList2RespList(list));
		return result;
	}

	@Override
	public CustomerManageResp findOne(CustomerManageQuery query) throws Exception {
		if(StringUtils.isNotBlank(query.getId())){
			return copyBean2Resp(customerManageMapper.selectByPrimaryKey(query.getId()));
		}
		return null;
	}
	
	private List<CustomerManageResp> copyBeanList2RespList(List<CustomerManage> list) throws Exception {
		List<CustomerManageResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<CustomerManageResp>();
			for(CustomerManage c : list){
				listResp.add(copyBean2Resp(c));
			}
		}
		return listResp;
	}
	
	private CustomerManageResp copyBean2Resp(CustomerManage bean) throws Exception {
		CustomerManageResp resp = null;
		if(bean != null){
			resp = new CustomerManageResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}

}
