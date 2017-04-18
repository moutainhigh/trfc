package com.tianrui.service.impl.businessManage.otherManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.businessManage.otherManage.IOtherRKArriveService;
import com.tianrui.api.req.businessManage.otherManage.OtherRKArriveReq;
import com.tianrui.api.resp.businessManage.otherManage.OtherRKArriveResp;
import com.tianrui.service.bean.basicFile.measure.DriverManage;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;
import com.tianrui.service.bean.basicFile.nc.MaterielManage;
import com.tianrui.service.bean.basicFile.nc.SupplierManage;
import com.tianrui.service.bean.basicFile.nc.WarehouseManage;
import com.tianrui.service.bean.businessManage.otherManage.OtherRKArrive;
import com.tianrui.service.bean.system.auth.SystemUser;
import com.tianrui.service.mapper.basicFile.measure.DriverManageMapper;
import com.tianrui.service.mapper.basicFile.measure.VehicleManageMapper;
import com.tianrui.service.mapper.basicFile.nc.MaterielManageMapper;
import com.tianrui.service.mapper.basicFile.nc.SupplierManageMapper;
import com.tianrui.service.mapper.basicFile.nc.WarehouseManageMapper;
import com.tianrui.service.mapper.businessManage.otherManage.OtherRKArriveMapper;
import com.tianrui.service.mapper.system.auth.SystemUserMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class OtherRKArriveService implements IOtherRKArriveService {

	@Autowired
	private OtherRKArriveMapper otherRKArriveMapper;
	@Autowired
	private MaterielManageMapper materielManageMapper;
	@Autowired
	private SupplierManageMapper supplierManageMapper;
	@Autowired
	private WarehouseManageMapper warehouseManageMapper;
	@Autowired
	private SystemUserMapper systemUserMapper;
	@Autowired
	private DriverManageMapper driverManageMapper;
	@Autowired
	private VehicleManageMapper vehicleManageMapper;



	@Override
	public Result update(OtherRKArriveReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null && StringUtils.isNotBlank(req.getId())){
			//转换类型
			OtherRKArrive oa = new OtherRKArrive();
			PropertyUtils.copyProperties(oa, req);
			oa.setModifier(req.getUserid());
			oa.setModifytime(System.currentTimeMillis());
			oa.setUtc(System.currentTimeMillis());
			//更新数据到数据库
			int index = otherRKArriveMapper.updateByPrimaryKeySelective(oa);
			//判断操作是否成功
			if(index>0){
				rs = Result.getSuccessResult();
			}else{
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}

	@Override
	public Result add(OtherRKArriveReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null){
			OtherRKArrive oa = new OtherRKArrive();
			PropertyUtils.copyProperties(oa, req);
			//获取id
			oa.setId(UUIDUtil.getId());
			oa.setCreator(req.getUserid());
			oa.setModifier(req.getUserid());
			oa.setModifytime(System.currentTimeMillis());
			oa.setUtc(System.currentTimeMillis());
			//更新数据到数据库
			int index = otherRKArriveMapper.insertSelective(oa);
			//判断操作是否成功
			if(index>0){
				rs = Result.getSuccessResult();
			}else{
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}

	@Override
	public Result page(OtherRKArriveReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null){
			PaginationVO<OtherRKArriveResp> pagevo = new PaginationVO<OtherRKArriveResp>();
			int pageNo = req.getPageNo();
			int pageSize = req.getPageSize();
			req.setStart((pageNo-1)*pageSize);
			req.setLimit(pageSize*pageNo);
			//获取数据总数
			long total = otherRKArriveMapper.count(req);
			pagevo.setTotal(total);
			List<OtherRKArriveResp> resps = new ArrayList<OtherRKArriveResp>();
			//判断是否有数据可以查询
			if(total>0){
				List<OtherRKArrive> list = otherRKArriveMapper.page(req);
				for(OtherRKArrive oa : list){
					resps.add(bean2Resp(oa));
				}
			}
			pagevo.setList(resps);
			rs = Result.getSuccessResult();
			rs.setData(pagevo);
		}
		return rs;
	}
	
	/**
	 * 转换类型
	 * @param OtherRKArrive
	 * @return OtherRKArriveResp
	 * @throws Exception
	 */
	private OtherRKArriveResp bean2Resp(OtherRKArrive oa) throws Exception{
		OtherRKArriveResp resp = new OtherRKArriveResp();
		PropertyUtils.copyProperties(resp, oa);
		//获取物料名称
		if(StringUtils.isNotBlank(oa.getMaterielid())){
			MaterielManage materiel = materielManageMapper.selectByPrimaryKey(oa.getMaterielid());
			if(materiel!=null){
				resp.setMaterielname(materiel.getName());
			}
		}
		//获取供应商名称
		if(StringUtils.isNotBlank(oa.getSupplierid())){
			SupplierManage supplier = supplierManageMapper.selectByPrimaryKey(oa.getSupplierid());
			if(supplier!=null){
				resp.setSuppliername(supplier.getName());
			}
		}
		//获取司机信息
		if(StringUtils.isNotBlank(oa.getDriverid())){
			DriverManage driver = driverManageMapper.selectByPrimaryKey(oa.getDriverid());
			if(driver!=null){
				resp.setDrivername(driver.getName());
			}
		}
		//获取车辆信息
		if(StringUtils.isNotBlank(oa.getVehicleid())){
			VehicleManage vehicle = vehicleManageMapper.selectByPrimaryKey(oa.getVehicleid());
			if(vehicle!=null){
				resp.setVehicleno(vehicle.getVehicleno());
				resp.setRfid(vehicle.getRfid());
			}
		}
		//获取仓库名称
		if(StringUtils.isNotBlank(oa.getWarehouseid())){
			WarehouseManage warehouse = warehouseManageMapper.selectByPrimaryKey(oa.getWarehouseid());
			if(warehouse!=null){
				resp.setWarehousename(warehouse.getName());
			}
		}
		//获取用户名称
		if(StringUtils.isNotBlank(oa.getCreator())){
			SystemUser user = systemUserMapper.selectByPrimaryKey(oa.getCreator());
			if(user!=null){
				resp.setCreatorname(user.getName()); 
			}
		}
		return resp;
	}

}
