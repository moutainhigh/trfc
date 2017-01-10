package com.tianrui.service.impl.businessManage.salesManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.businessManage.salesManage.ISalesArriveService;
import com.tianrui.api.req.businessManage.salesManage.SalesArriveQuery;
import com.tianrui.api.resp.businessManage.salesManage.SalesArriveResp;
import com.tianrui.service.bean.businessManage.salesManage.SalesArrive;
import com.tianrui.service.mapper.businessManage.salesManage.SalesArriveMapper;
import com.tianrui.smartfactory.common.vo.PaginationVO;

/**
 * 销售到货通知单Service
 * @author zhanggaohao
 * @createtime 2017年1月9日 下午2:22:43
 * @classname SalesArriveService.java
 */
@Service
public class SalesArriveService implements ISalesArriveService {

	@Autowired
	private SalesArriveMapper salesArriveMapper;
	
	@Override
	public PaginationVO<SalesArriveResp> page(SalesArriveQuery query) throws Exception {
		PaginationVO<SalesArriveResp> page = null;
		if(query != null){
			page = new PaginationVO<SalesArriveResp>();
			query.setState("1");
			long count = salesArriveMapper.findSalesArrivePageCount(query);
			if(count > 0){
				query.setStart((query.getPageNo() - 1)*query.getPageSize());
				query.setLimit(query.getPageSize());
				List<SalesArrive> list = salesArriveMapper.findSalesArrivePage(query);
				page.setList(copyBeanList2RespList(list));
			}
			page.setTotal(count);
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
		}
		return page;
	}
	
	private List<SalesArriveResp> copyBeanList2RespList(List<SalesArrive> list) throws Exception {
		List<SalesArriveResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<SalesArriveResp>();
			for(SalesArrive sa : list){
				listResp.add(copyBean2Resp(sa));
			}
		}
		return listResp;
	}
	
	private SalesArriveResp copyBean2Resp(SalesArrive bean) throws Exception {
		SalesArriveResp resp = null;
		if(bean != null){
			resp = new SalesArriveResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}
}
