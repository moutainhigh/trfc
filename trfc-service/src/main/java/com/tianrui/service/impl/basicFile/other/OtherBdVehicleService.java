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
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;
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
	
	/**
	 * 分页查询数据
	 * @throws Exception 
	 */
	@Override
	public Result page(OtherBdVehicleReq req) throws Exception  {
		Result result=Result.getSuccessResult();
		if(req != null){
			PaginationVO<OtherBdVehicleResp> page = new PaginationVO<OtherBdVehicleResp>();
			int pageNo = req.getPageNo();
			int pageSize = req.getPageSize();
			req.setStart((pageNo-1)*pageSize);
			req.setLimit(pageSize);
			long count = otherBdVehicleMapper.findVehiclePageCount(req);
			page.setTotal(count);
			if(count > 0){
				req.setStart((req.getPageNo()-1)*req.getPageSize());
				req.setLimit(req.getPageSize());
				List<OtherBdVehicle> list = this.otherBdVehicleMapper.findVehiclePage(req);
				page.setList(copyBeanList2RespList(list));
				page.setTotal(count);
				page.setPageNo(req.getPageNo());
				page.setPageSize(req.getPageSize());
				result.setData(page);
			}else{
				page.setTotal(count);
				page.setPageNo(req.getPageNo());
				page.setPageSize(req.getPageSize());
				result.setData(page);
			}
			
		}
		return result;
	}
	

	/**
	 * 增加其他车辆信息
	 * @throws Exception 
	 */
	@Transactional
	@Override
	public Result addVehicle(OtherBdVehicleReq req) throws Exception  {
		Result result = Result.getSuccessResult();
		if(req != null){
			OtherBdVehicle vehicle = new OtherBdVehicle();
			PropertyUtils.copyProperties(vehicle, req);
			vehicle.setId(getVehicleId());
			vehicle.setCreator("YZF");
			vehicle.setAddr("墨西哥");
			vehicle.setTelphone(String.valueOf((int)(Math.random()*10000)));
			vehicle.setCreatetime(System.currentTimeMillis());
			vehicle.setModifytime(System.currentTimeMillis());
			int n=this.otherBdVehicleMapper.insert(vehicle);
			if(n > 0){
				result.setData(n);
			}else if(n == -1){
				result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	/**
	 * 修改其他车辆信息
	 * @throws Exception 
	 */
	@Transactional
	@Override
	public Result editVehicle(OtherBdVehicleReq req) throws Exception  {
		Result result=Result.getSuccessResult();
		if(req != null){
			OtherBdVehicle vehicle = new OtherBdVehicle();
			PropertyUtils.copyProperties(vehicle, req);
			vehicle.setModifytime(System.currentTimeMillis());
			int n=this.otherBdVehicleMapper.updateByPrimaryKeySelective(vehicle);
			if(n > 0){
				result.setData(n);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	/**
	 * 删除其他车辆信息
	 */
	@Transactional
	@Override
	public Result deleteVehicle(String id) {
		Result result=Result.getSuccessResult();
		if(id!=null && !id.trim().isEmpty()){
			int n=this.otherBdVehicleMapper.deleteByPrimaryKey(id);
			if(n > 0){
				result.setData(n);
			}else{
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}
	
	/**
	 * 检查名称不能重复
	 */
	@Override
	public Result checkName(String name){
		Result result=Result.getSuccessResult();
		if(name!=null && !name.trim().isEmpty()){
			if(this.otherBdVehicleMapper.findVehicleByName(name)==0){
				result.setData(true);
			}
		}else{
			result.setErrorCode(ErrorCode.PARAM_NULL_ERROR);
		}
		return result;		
	}

	
	
	/**
	 * 集合转换
	 * @param List<OtherBdVehicle> list
	 * @return List<OtherBdVehicleResp> 
	 * @throws Exception
	 */
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
	
	/**
	 * 实体bean类型转换
	 * @param OtherBdVehicle bean
	 * @return OtherBdVehicleResp
	 * @throws Exception
	 */
	private OtherBdVehicleResp copyBean2Resp(OtherBdVehicle bean) throws Exception {
		OtherBdVehicleResp resp = null;
		if(bean != null){
			resp = new OtherBdVehicleResp();
			PropertyUtils.copyProperties(resp, bean);
		}
		return resp;
	}
	
	/*
	 * 获取id
	 */
	public String getVehicleId(){
		return UUIDUtil.getId();
	}
	/*
	 * 获取编号
	 */
	public String getVehicleCode(){
		return "CD"+(int)(Math.random()*10000);
	}
	/*
	 * 获取内码
	 */
	public String getVehicleInnercode(){
		return "ICD"+(int)(Math.random()*10000);
	}


	
}
