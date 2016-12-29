package com.tianrui.service.impl.basicFile.measure;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.basicFile.measure.IBlacklistManageService;
import com.tianrui.api.intf.basicFile.measure.IVehicleManageService;
import com.tianrui.api.req.basicFile.measure.BlacklistManageReq;
import com.tianrui.api.req.basicFile.measure.VehicleManageReq;
import com.tianrui.api.resp.basicFile.measure.VehicleManageResp;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;
import com.tianrui.service.mapper.basicFile.measure.VehicleManageMapper;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;

/**
 * 车辆管理Service
 * @author zhanggaohao
 * @createtime 2016年12月20日 下午4:23:52
 * @classname VehicleManageService.java
 */
@Service
public class VehicleManageService implements IVehicleManageService {
	
	@Autowired
	private VehicleManageMapper vehicleManageMapper;
	
	@Autowired
	private IBlacklistManageService blacklistManageService;
	
	@Override
	public PaginationVO<VehicleManageResp> page(VehicleManageReq req) throws Exception{
		PaginationVO<VehicleManageResp> page = null;
		if(req != null){
			page = new PaginationVO<VehicleManageResp>();
			long count = vehicleManageMapper.findVehiclePageCount(req);
			if(count > 0){
				req.setStart((req.getPageNo()-1)*req.getPageSize());
				req.setLimit(req.getPageSize());
				List<VehicleManage> list = this.vehicleManageMapper.findVehiclePage(req);
				page.setList(copyBeanList2RespList(list));
			}
			page.setTotal(count);
			page.setPageNo(req.getPageNo());
			page.setPageSize(req.getPageSize());
		}
		return page;
	}
	
	@Transactional
	@Override
	public int addVehicle(VehicleManageReq req) throws Exception {
		int n = 0;
		if(req != null){
			VehicleManage vehicle = new VehicleManage();
			vehicle.setVehicleno(req.getVehicleno());
			List<VehicleManage> list = this.vehicleManageMapper.selectSelective(vehicle);
			if(list != null && list.size() > 0){
				return -1;
			}
			PropertyUtils.copyProperties(vehicle, req);
			vehicle.setId(UUIDUtil.getId());
			vehicle.setIsblacklist("0");
//			vehicle.setCreator("");
			vehicle.setCreatetime(System.currentTimeMillis());
//			vehicle.setModifier("");
			vehicle.setModifytime(System.currentTimeMillis());
			n = this.vehicleManageMapper.insert(vehicle);
		}
		return n;
	}
	
	@Transactional
	@Override
	public int editVehicle(VehicleManageReq req) throws Exception {
		int n = 0;
		if(req != null){
			VehicleManage vehicle = new VehicleManage();
			PropertyUtils.copyProperties(vehicle, req);
//			vehicle.setModifier("");
			vehicle.setModifytime(System.currentTimeMillis());
			n = this.vehicleManageMapper.updateByPrimaryKeySelective(vehicle);
		}
		return n;
	}
	
	@Transactional
	@Override
	public int deleteVehicle(String id){
		return this.vehicleManageMapper.deleteByPrimaryKey(id);
	}

	@Transactional
	@Override
	public boolean delblacklist(VehicleManageReq req) throws Exception {
		if(req != null && StringUtils.isNotBlank(req.getId())){
			req.setIsblacklist("0");
			if(this.editVehicle(req) > 0){
				if(this.blacklistManageService.deleteBlacklist(req.getId()) > 0){
					return true;
				}else{
					return false;
				}
			}
		}
		return false;
	}

	@Transactional
	@Override
	public boolean addblacklist(VehicleManageReq req) throws Exception {
		if(req != null && StringUtils.isNotBlank(req.getId())){
			req.setIsblacklist("1");
			if(this.editVehicle(req) > 0){
				BlacklistManageReq breq = new BlacklistManageReq();
				breq.setId(UUIDUtil.getId());
				breq.setVehicleid(req.getId());
				breq.setVehicleno(req.getBlackVno());
				breq.setRemarks(req.getBlackRemarks());
				breq.setCreator(req.getBlackCreator());
				breq.setCreatetime(System.currentTimeMillis());
				breq.setModifier(req.getBlackCreator());
				breq.setModifytime(System.currentTimeMillis());
				if(this.blacklistManageService.addBlacklist(breq) > 0){
					return true;
				}else{
					return false;
				}
			}
		}
		return false;
	}
	
	private List<VehicleManageResp> copyBeanList2RespList(List<VehicleManage> list) throws Exception {
		List<VehicleManageResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<VehicleManageResp>();
			for(VehicleManage vehicle : list){
				listResp.add(copyBean2Resp(vehicle));
			}
		}
		return listResp;
	}
	
	private VehicleManageResp copyBean2Resp(VehicleManage bean) throws Exception {
		VehicleManageResp resp = null;
		if(bean != null){
			resp = new VehicleManageResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}

}