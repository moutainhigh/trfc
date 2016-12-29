package com.tianrui.service.impl.basicFile.nc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.basicFile.nc.ISupplierManageService;
import com.tianrui.api.req.basicFile.nc.SupplierManageReq;
import com.tianrui.api.resp.basicFile.nc.SupplierManageResp;
import com.tianrui.service.bean.basicFile.nc.SupplierManage;
import com.tianrui.service.mapper.basicFile.nc.SupplierManageMapper;
import com.tianrui.smartfactory.common.vo.PaginationVO;
/**
 * 供应商管理Service
 * @author zhanggaohao
 * @createtime 2016年12月19日 上午9:54:13
 * @classname SupplierManageService.java
 */
@Service
public class SupplierManageService implements ISupplierManageService {
	
	@Autowired
	private SupplierManageMapper supplierManageMapper;

	@Override
	public PaginationVO<SupplierManageResp> page(SupplierManageReq req) throws Exception {
		PaginationVO<SupplierManageResp> page = null;
		if(req != null){
			page = new PaginationVO<SupplierManageResp>();
			long count = supplierManageMapper.findSupplierPageCount(req);
			if(count > 0){
				req.setStart((req.getPageNo()-1)*req.getPageSize());
				req.setLimit(req.getPageSize());
				List<SupplierManage> list = supplierManageMapper.findSupplierPage(req);
				page.setList(copyBeanList2RespList(list));
			}
			page.setTotal(count);
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		return page;
	}
	
	@Override
	public int updateSupplier(SupplierManageReq req) throws Exception {
		int n = 0;
		if(req != null){
			SupplierManage sm = new SupplierManage();
			PropertyUtils.copyProperties(sm, req);
//			sm.setModifier("");
			sm.setModifytime(System.currentTimeMillis());
			n =  supplierManageMapper.updateByPrimaryKeySelective(sm);
		}
		return n;
	}
	
	private List<SupplierManageResp> copyBeanList2RespList(List<SupplierManage> list) throws Exception {
		List<SupplierManageResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<SupplierManageResp>();
			for(SupplierManage wm : list){
				listResp.add(copyBean2Resp(wm));
			}
		}
		return listResp;
	}
	
	private SupplierManageResp copyBean2Resp(SupplierManage bean) throws Exception {
		SupplierManageResp resp = null;
		if(bean != null){
			resp = new SupplierManageResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}

}
