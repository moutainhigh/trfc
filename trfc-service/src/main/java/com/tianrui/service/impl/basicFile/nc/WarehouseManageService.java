package com.tianrui.service.impl.basicFile.nc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.basicFile.nc.IWarehouseManageService;
import com.tianrui.api.req.basicFile.nc.WarehouseManageReq;
import com.tianrui.api.resp.basicFile.nc.WarehouseManageResp;
import com.tianrui.service.bean.basicFile.nc.WarehouseManage;
import com.tianrui.service.mapper.basicFile.nc.WarehouseManageMapper;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
/**
 * 仓库管理Service
 * @author zhanggaohao
 * @createtime 2016年12月10日 上午10:21:40
 * @classname WarehouseManageService.java
 */
@Service
public class WarehouseManageService implements IWarehouseManageService {
	
	@Autowired
	private WarehouseManageMapper warehouseManageMapper;

	@Override
	public PaginationVO<WarehouseManageResp> page(WarehouseManageReq req) throws Exception {
		PaginationVO<WarehouseManageResp> page = null;
		if(req != null){
			page = new PaginationVO<WarehouseManageResp>();
			long count = warehouseManageMapper.findWarehouseManagePageCount(req);
			if(count > 0){
				req.setStart((req.getPageNo()-1)*req.getPageSize());
				req.setLimit(req.getPageSize());
				List<WarehouseManage> list = warehouseManageMapper.findWarehouseManagePage(req);
				page.setList(copyBeanList2RespList(list));
			}
			page.setTotal(count);
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		return page;
	}

	@Override
	public WarehouseManageResp findOne(String id) throws Exception {
		WarehouseManageResp resp = null;
		if(StringUtils.isNotBlank(id)){
			resp = new WarehouseManageResp();
			WarehouseManage wm = warehouseManageMapper.selectByPrimaryKey(id);
			resp = copyBean2Resp(wm);
		}
		return resp;
	}

	@Override
	@Transactional
	public int deleteWarehouse(String id) {
		int n = 0;
		if(StringUtils.isNotBlank(id)){
			n = warehouseManageMapper.deleteByPrimaryKey(id);
		}
		return n;
	}

	@Override
	@Transactional
	public int updateWarehouse(WarehouseManageReq req) throws Exception {
		int n = 0;
		if(req != null){
			WarehouseManage wm = new WarehouseManage();
			PropertyUtils.copyProperties(wm, req);
//			wm.setModifier("");
			wm.setModifytime(System.currentTimeMillis());
			n = warehouseManageMapper.updateByPrimaryKeySelective(wm);
		}
		return n;
	}
	
	@Override
	@Transactional
	public int addWarehouse(WarehouseManageReq req) throws Exception {
		int n = 0;
		if(req != null){
			WarehouseManage wm = new WarehouseManage();
			PropertyUtils.copyProperties(wm, req);
			wm.setId(UUIDUtil.getId());
			wm.setCreatetime(System.currentTimeMillis());
			n = warehouseManageMapper.insert(wm);
		}
		return n;
	}

	@Override
	public List<WarehouseManageResp> selectSelective(WarehouseManageReq req) throws Exception {
		WarehouseManage w = new WarehouseManage();
		PropertyUtils.copyProperties(w, req);
		List<WarehouseManage> list = warehouseManageMapper.selectSelective(w);
		return copyBeanList2RespList(list);
	}
	
	@Override
	public List<WarehouseManageResp> findAll() throws Exception {
		List<WarehouseManage> list = warehouseManageMapper.selectSelective(null);
		return copyBeanList2RespList(list);
	}
	
	private List<WarehouseManageResp> copyBeanList2RespList(List<WarehouseManage> list) throws Exception {
		List<WarehouseManageResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<WarehouseManageResp>();
			for(WarehouseManage wm : list){
				listResp.add(copyBean2Resp(wm));
			}
		}
		return listResp;
	}
	
	private WarehouseManageResp copyBean2Resp(WarehouseManage bean) throws Exception {
		WarehouseManageResp resp = null;
		if(bean != null){
			resp = new WarehouseManageResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}
	
}
