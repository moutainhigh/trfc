package com.tianrui.service.impl.businessManage.otherManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.businessManage.otherManage.IOtherArriveService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.businessManage.otherManage.OtherArriveReq;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.businessManage.otherManage.OtherArriveResp;
import com.tianrui.api.resp.businessManage.poundNoteMaintain.PoundNoteResp;
import com.tianrui.service.bean.basicFile.measure.DriverManage;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;
import com.tianrui.service.bean.basicFile.nc.CustomerManage;
import com.tianrui.service.bean.basicFile.nc.MaterielManage;
import com.tianrui.service.bean.basicFile.nc.SupplierManage;
import com.tianrui.service.bean.basicFile.nc.WarehouseManage;
import com.tianrui.service.bean.businessManage.cardManage.Card;
import com.tianrui.service.bean.businessManage.otherManage.OtherArrive;
import com.tianrui.service.bean.businessManage.poundNoteMaintain.PoundNote;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseArrive;
import com.tianrui.service.bean.businessManage.salesManage.SalesArrive;
import com.tianrui.service.bean.system.auth.Organization;
import com.tianrui.service.bean.system.auth.SystemUser;
import com.tianrui.service.mapper.basicFile.measure.DriverManageMapper;
import com.tianrui.service.mapper.basicFile.measure.VehicleManageMapper;
import com.tianrui.service.mapper.basicFile.nc.CustomerManageMapper;
import com.tianrui.service.mapper.basicFile.nc.MaterielManageMapper;
import com.tianrui.service.mapper.basicFile.nc.SupplierManageMapper;
import com.tianrui.service.mapper.basicFile.nc.WarehouseManageMapper;
import com.tianrui.service.mapper.businessManage.cardManage.CardMapper;
import com.tianrui.service.mapper.businessManage.otherManage.OtherArriveMapper;
import com.tianrui.service.mapper.businessManage.poundNoteMaintain.PoundNoteMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseArriveMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesArriveMapper;
import com.tianrui.service.mapper.system.auth.OrganizationMapper;
import com.tianrui.service.mapper.system.auth.SystemUserMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class OtherArriveService implements IOtherArriveService {

	@Autowired
	private OtherArriveMapper otherArriveMapper;
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
	@Autowired
	private CustomerManageMapper customerManageMapper;
	@Autowired
	private OrganizationMapper organizationMapper;
	@Autowired
	private PoundNoteMapper poundNoteMapper;
	@Autowired
	private ISystemCodeService systemCodeService;
	@Autowired
	private SalesArriveMapper salesArriveMapper;
	@Autowired
	private PurchaseArriveMapper purchaseArriveMapper;
	@Autowired
	private CardMapper cardMapper;



	@Override
	public Result update(OtherArriveReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		OtherArrive oa = new OtherArrive();
		oa.setId(req.getId());
		if(req!=null && StringUtils.isNotBlank(req.getId()) && validDriverAndVehicle(req,rs,oa)){
			//转换类型
			PropertyUtils.copyProperties(oa, req);
			oa.setModifier(req.getUserid());
			oa.setModifytime(System.currentTimeMillis());
			oa.setUtc(System.currentTimeMillis());
			//更新数据到数据库
			int index = otherArriveMapper.updateByPrimaryKeySelective(oa);
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
	public Result updateOperation(OtherArriveReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		OtherArrive oa = new OtherArrive();
		oa.setId(req.getId());
		if(req!=null && StringUtils.isNotBlank(req.getId())){
			//转换类型
			PropertyUtils.copyProperties(oa, req);
			oa.setModifier(req.getUserid());
			oa.setModifytime(System.currentTimeMillis());
			oa.setUtc(System.currentTimeMillis());
			//更新数据到数据库
			int index = otherArriveMapper.updateByPrimaryKeySelective(oa);
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
	public Result add(OtherArriveReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		OtherArrive oa = new OtherArrive();
		if(req!=null && validDriverAndVehicle(req,rs,oa)){
			PropertyUtils.copyProperties(oa, req);
			GetCodeReq codeReq = new GetCodeReq();
			codeReq.setCode("QRN");
			codeReq.setCodeType(true);
			codeReq.setUserid(req.getUserid());
			oa.setCode(String.valueOf(systemCodeService.getCode(codeReq).getData()));
			//获取id
			oa.setId(UUIDUtil.getId());
			oa.setAuditstatus("0");
			oa.setStatus("0");
			oa.setCreator(req.getUserid());
			oa.setModifier(req.getUserid());
			oa.setModifytime(System.currentTimeMillis());
			oa.setUtc(System.currentTimeMillis());
			//更新数据到数据库
			int index = otherArriveMapper.insertSelective(oa);
			//判断操作是否成功
			if(index>0){
				systemCodeService.updateCodeItem(codeReq);
				rs = Result.getSuccessResult();
			}else{
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}

	@Override
	public Result page(OtherArriveReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		if(req!=null){
			PaginationVO<OtherArriveResp> pagevo = new PaginationVO<OtherArriveResp>();
			int pageNo = req.getPageNo();
			int pageSize = req.getPageSize();
			req.setStart((pageNo-1)*pageSize);
			req.setLimit(pageSize*pageNo);
			//获取数据总数
			long total = otherArriveMapper.count(req);
			pagevo.setTotal(total);
			List<OtherArriveResp> resps = new ArrayList<OtherArriveResp>();
			//判断是否有数据可以查询
			if(total>0){
				List<OtherArrive> list = otherArriveMapper.page(req);
				for(OtherArrive oa : list){
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
	 * @param OtherArrive
	 * @return OtherArriveResp
	 * @throws Exception
	 */
	private OtherArriveResp bean2Resp(OtherArrive oa) throws Exception{
		OtherArriveResp resp = new OtherArriveResp();
		PropertyUtils.copyProperties(resp, oa);
		//获取磅单
		PoundNote pound = poundNoteMapper.selectByNoticeId(oa.getId());
		if(pound!=null){
			PoundNoteResp poundResp = new PoundNoteResp();
			PropertyUtils.copyProperties(resp, pound);
			resp.setPoundDetail(poundResp);
		}
		//获取物料名称
		if(StringUtils.isNotBlank(oa.getMaterielid())){
			MaterielManage materiel = materielManageMapper.selectByPrimaryKey(oa.getMaterielid());
			if(materiel!=null){
				resp.setMaterielname(materiel.getName());
			}
		}
		//获取客户名称
		if(StringUtils.isNotBlank(oa.getCustomerid())){
			CustomerManage customer = customerManageMapper.selectByPrimaryKey(oa.getCustomerid());
			if(customer!=null){
				resp.setCustormername(customer.getName());
			}
		}

		//获取供应商名称
		if(StringUtils.isNotBlank(oa.getSupplierid())){
			SupplierManage supplier = supplierManageMapper.selectByPrimaryKey(oa.getSupplierid());
			if(supplier!=null){
				resp.setSuppliername(supplier.getName());
			}
		}
		//获取组织名称
		if(StringUtils.isNotBlank(oa.getReceivedepartmentid())){
			Organization org = organizationMapper.selectByPrimaryKey(oa.getReceivedepartmentid());
			if(org!=null){
				resp.setReceivedepartmentname(org.getName());
			}
		}
		//获取组织名称
		if(StringUtils.isNotBlank(oa.getSenddepartmentid())){
			Organization org = organizationMapper.selectByPrimaryKey(oa.getSenddepartmentid());
			if(org!=null){
				resp.setSenddepartmentname(org.getName());
			}
		}
		//获取司机信息
		if(StringUtils.isNotBlank(oa.getDriverid())){
			DriverManage driver = driverManageMapper.selectByPrimaryKey(oa.getDriverid());
			if(driver!=null){
				resp.setDrivername(driver.getName());
				resp.setDriveridentityno(driver.getIdentityno());
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

	@Override
	public Result findOne(String id) throws Exception{
		Result rs = Result.getParamErrorResult();
		if(StringUtils.isNotBlank(id)){
			OtherArrive oa = otherArriveMapper.selectByPrimaryKey(id);
			OtherArriveResp resp = new OtherArriveResp();
			if(oa!=null){
				resp = bean2Resp(oa);
			}
			rs = Result.getSuccessResult();
			rs.setData(resp);
		}
		return rs;
	}

	private boolean validDriverAndVehicle(OtherArriveReq req, Result result,OtherArrive oa) {
		boolean flag = true;
		PurchaseArrive pa = new PurchaseArrive();
		pa.setVehicleid(req.getVehicleid());
		List<PurchaseArrive> listVehicle = purchaseArriveMapper.checkDriverAndVehicleIsUse(pa);
		if(listVehicle != null && listVehicle.size() > 0){
			result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			result.setError("此车辆己有到货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listVehicle.get(0).getCode()+"，如有疑问请与销售处联系！");
			flag = false;
		}
		SalesArrive sa = new SalesArrive();
		sa.setVehicleid(req.getVehicleid());
		List<SalesArrive> listVehicle1 = salesArriveMapper.checkDriverAndVehicleIsUse(sa);
		if(listVehicle1 != null && listVehicle1.size() > 0){
			result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			result.setError("此车辆己有提货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listVehicle1.get(0).getCode()+"，如有疑问请与销售处联系！");
			flag = false;
		}
		pa.setVehicleid(null);
		pa.setDriverid(req.getDriverid());
		List<PurchaseArrive> listDriver = purchaseArriveMapper.checkDriverAndVehicleIsUse(pa);
		if(listDriver != null && listDriver.size() > 0){
			result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			result.setError("此司机己有到货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listDriver.get(0).getCode()+"，如有疑问请与销售处联系！");
			flag = false;
		}
		sa.setVehicleid(null);
		sa.setDriverid(req.getDriverid());
		List<SalesArrive> listDriver1 = salesArriveMapper.checkDriverAndVehicleIsUse(sa);
		if(listDriver1 != null && listDriver1.size() > 0){
			result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			result.setError("此司机己有提货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listDriver1.get(0).getCode()+"，如有疑问请与销售处联系！");
			flag = false;
		}

		//ic卡信息
		if(StringUtils.isNotBlank(req.getIcardid())){
			Card card = cardMapper.selectByPrimaryKey(req.getIcardid());
			if(card!=null){
				//ic卡是否占用
				SalesArrive sales = salesArriveMapper.checkICUse(card.getId());
				PurchaseArrive purchase = purchaseArriveMapper.checkICUse(card.getId());
				if(sales == null && purchase == null) {
					pa.setIcardid(card.getId());
				}else{
					result.setErrorCode(ErrorCode.CARD_IN_USE);
					flag = false;
				}
			}else{
				result.setErrorCode(ErrorCode.CARD_NOT_EXIST);
				flag = false;
			}
		}

		oa.setDriverid(req.getDriverid());
		List<OtherArrive> listDriver2 = otherArriveMapper.checkDriverAndVehicleAndIcardIsUse(oa);
		if(listDriver2!=null && listDriver2.size()>0){
			result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			result.setError("此司机己有提货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listDriver2.get(0).getCode()+"，如有疑问请与销售处联系！");
			flag = false;
		}
		oa.setDriverid(null);
		oa.setVehicleid(req.getVehicleid());
		List<OtherArrive> listVehicle2 = otherArriveMapper.checkDriverAndVehicleAndIcardIsUse(oa);
		if(listVehicle2!=null && listVehicle2.size()>0){
			result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
			result.setError("此车辆己有提货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listVehicle2.get(0).getCode()+"，如有疑问请与销售处联系！");
			flag = false;
		}
		oa.setVehicleid(null);
		oa.setIcardid(req.getIcardid());
		if(StringUtils.isNotBlank(req.getIcardid())){
			List<OtherArrive> listIcard2 = otherArriveMapper.checkDriverAndVehicleAndIcardIsUse(oa);
			if(listIcard2!=null && listIcard2.size()>0){
				result.setErrorCode(ErrorCode.CARD_IN_USE);
				flag = false;
			}
		}
		return flag;
	}



}
