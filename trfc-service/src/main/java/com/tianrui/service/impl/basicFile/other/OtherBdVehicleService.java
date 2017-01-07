package com.tianrui.service.impl.basicFile.other;

import java.util.ArrayList;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianrui.api.intf.basicFile.other.IOtherBdVehicleService;
import com.tianrui.api.req.basicFile.other.OtherBdVehicleReq;
import com.tianrui.api.resp.basicFile.other.OtherBdVehicleResp;
import com.tianrui.service.bean.basicFile.other.OtherBdVehicle;
import com.tianrui.service.mapper.basicFile.other.OtherBdVehicleMapper;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
/**
 * 其他车辆Service
 * @author Yangzhenfu
 * @createtime 2017年1月4日 下午2:14:52
 * @classname OtherBdVehicleService.java
 */
@Service
public class OtherBdVehicleService implements IOtherBdVehicleService{
	@Autowired
	private OtherBdVehicleMapper otherBdVehicleMapper;
	
	
	@Override
	public PaginationVO<OtherBdVehicleResp> page(OtherBdVehicleReq req) throws Exception {
		PaginationVO<OtherBdVehicleResp> page = null;
		if(req != null){
			page = new PaginationVO<OtherBdVehicleResp>();
			long count = otherBdVehicleMapper.findVehiclePageCount(req);
			if(count > 0){
				req.setStart((req.getPageNo()-1)*req.getPageSize());
				req.setLimit(req.getPageSize());
				List<OtherBdVehicle> list = this.otherBdVehicleMapper.findVehiclePage(req);
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
	public int addVehicle(OtherBdVehicleReq req) throws Exception {
		int n=0;
		if(req != null){
			OtherBdVehicle vehicle = new OtherBdVehicle();
			PropertyUtils.copyProperties(vehicle, req);
			vehicle.setId(getVehicleId());
			vehicle.setCreator("YZF");
			vehicle.setAddr("墨西哥");
			vehicle.setTelphone(String.valueOf((int)(Math.random()*10000)));
			vehicle.setCreatetime(System.currentTimeMillis());
			vehicle.setModifytime(System.currentTimeMillis());
			n = this.otherBdVehicleMapper.insert(vehicle);
		}
		return n;
	}
	
	@Transactional
	@Override
	public int editVehicle(OtherBdVehicleReq req) throws Exception {
		int n = 0;
		if(req != null){
			OtherBdVehicle vehicle = new OtherBdVehicle();
			PropertyUtils.copyProperties(vehicle, req);
			vehicle.setModifytime(System.currentTimeMillis());
			n = this.otherBdVehicleMapper.updateByPrimaryKeySelective(vehicle);
		}
		return n;
	}
	
	@Transactional
	@Override
	public int deleteVehicle(String id) {
		return this.otherBdVehicleMapper.deleteByPrimaryKey(id);
	}
	
	private List<OtherBdVehicleResp> copyBeanList2RespList(List<OtherBdVehicle> list) throws Exception {
		List<OtherBdVehicleResp> listResp = null;
		if(list != null && list.size() > 0){
			listResp = new ArrayList<OtherBdVehicleResp>();
			for(OtherBdVehicle vehicle : list){
				listResp.add(copyBean2Resp(vehicle));
			}
		}
		return listResp;
	}
	private OtherBdVehicleResp copyBean2Resp(OtherBdVehicle bean) throws Exception {
		OtherBdVehicleResp resp = null;
		if(bean != null){
			resp = new OtherBdVehicleResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}

	public String getVehicleId(){
		return UUIDUtil.getId();
	}
	public String getVehicleCode(){
		return "CD"+(int)(Math.random()*10000);
	}

	public String getVehicleInnercode(){
		return "ICD"+(int)(Math.random()*10000);
	}

}
