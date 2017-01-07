package com.tianrui.service.impl.basicFile.nc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.basicFile.nc.ICustomerManageService;
import com.tianrui.api.req.basicFile.nc.CustomerManageReq;
import com.tianrui.api.resp.basicFile.nc.CustomerManageResp;
import com.tianrui.service.bean.basicFile.nc.CustomerManage;
/**
 * 客户管理Service
 * @author zhanggaohao
 * @createtime 2016年12月27日 下午3:52:54
 * @classname CustomerManageService.java
 */
import com.tianrui.service.mapper.basicFile.nc.CustomerManageMapper;
import com.tianrui.smartfactory.common.vo.PaginationVO;
@Service
public class CustomerManageService implements ICustomerManageService {

	@Autowired
	private CustomerManageMapper customerManageMapper;
	
	@Override
	public PaginationVO<CustomerManageResp> page(CustomerManageReq req) throws Exception {
		PaginationVO<CustomerManageResp> page = null;
		if(req != null){
			page = new PaginationVO<CustomerManageResp>();
			long count = customerManageMapper.findCustomerPageCount(req);
			if(count > 0){
				req.setStart((req.getPageNo()-1)*req.getPageSize());
				req.setLimit(req.getPageSize());
				List<CustomerManage> list = customerManageMapper.findCustomerPage(req);
				page.setList(copyBeanList2RespList(list));
			}
			page.setTotal(count);
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		return page;
	}
	
	@Override
	public int updateCustomer(CustomerManageReq req) throws Exception {
		int n = 0;
		if(req != null){
			CustomerManage c = new CustomerManage();
			PropertyUtils.copyProperties(c, req);
			//c.setModifier("");
			c.setModifytime(System.currentTimeMillis());
			n = customerManageMapper.updateByPrimaryKeySelective(c);
		}
		return n;
	}
	
	@Override
	public List<CustomerManageResp> selectSelective(CustomerManageReq req) throws Exception {
		CustomerManage c = new CustomerManage();
		PropertyUtils.copyProperties(c, req);
		List<CustomerManage> list = customerManageMapper.selectSelective(c);
		return copyBeanList2RespList(list);
	}
	
	@Override
	public List<CustomerManageResp> findAll() throws Exception {
		List<CustomerManage> list = customerManageMapper.selectSelective(null);
		return copyBeanList2RespList(list);
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
