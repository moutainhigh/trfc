package com.tianrui.service.impl.businessManage.poundNoteMaintain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tianrui.api.intf.businessManage.poundNoteMaintain.IPoundNoteService;
import com.tianrui.api.intf.businessManage.salesManage.ISalesApplicationDetailService;
import com.tianrui.api.intf.businessManage.salesManage.ISalesApplicationService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.businessManage.poundNoteMaintain.PoundNoteQuery;
import com.tianrui.api.req.businessManage.poundNoteMaintain.PoundNoteSave;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.businessManage.poundNoteMaintain.PoundNoteResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationDetailResp;
import com.tianrui.api.resp.businessManage.salesManage.SalesApplicationResp;
import com.tianrui.service.bean.basicFile.measure.DriverManage;
import com.tianrui.service.bean.basicFile.measure.MinemouthManage;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;
import com.tianrui.service.bean.basicFile.measure.YardManage;
import com.tianrui.service.bean.basicFile.nc.WarehouseManage;
import com.tianrui.service.bean.businessManage.poundNoteMaintain.PoundNote;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseApplication;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplication;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationDetail;
import com.tianrui.service.bean.businessManage.salesManage.SalesApplicationJoinPoundNote;
import com.tianrui.service.mapper.basicFile.measure.DriverManageMapper;
import com.tianrui.service.mapper.basicFile.measure.MinemouthManageMapper;
import com.tianrui.service.mapper.basicFile.measure.VehicleManageMapper;
import com.tianrui.service.mapper.basicFile.measure.YardManageMapper;
import com.tianrui.service.mapper.basicFile.nc.WarehouseManageMapper;
import com.tianrui.service.mapper.businessManage.poundNoteMaintain.PoundNoteMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseApplicationMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationDetailMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationJoinPoundNoteMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesApplicationMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.PaginationVO;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class PoundNoteService implements IPoundNoteService {

	@Autowired
	private PoundNoteMapper poundNoteMapper;
	@Autowired
	private PurchaseApplicationMapper purchaseApplicationMapper;
	@Autowired
	private ISystemCodeService systemCodeService;
	@Autowired
	private VehicleManageMapper vehicleManageMapper;
	@Autowired
	private DriverManageMapper driverManageMapper;
	@Autowired
	private WarehouseManageMapper warehouseManageMapper;
	@Autowired
	private MinemouthManageMapper minemouthManageMapper;
	@Autowired
	private YardManageMapper yardManageMapper;
	@Autowired
	private SalesApplicationMapper salesApplicationMapper;
	@Autowired
	private SalesApplicationDetailMapper salesApplicationDetailMapper;
	@Autowired
	private SalesApplicationJoinPoundNoteMapper salesApplicationJoinPoundNoteMapper;
	@Autowired
	private ISalesApplicationService salesApplicationService;
	@Autowired
	private ISalesApplicationDetailService salesApplicationDetailService;
	
	@Override
	public PaginationVO<PoundNoteResp> purchasePage(PoundNoteQuery query) throws Exception {
		PaginationVO<PoundNoteResp> page = null;
		if (query != null) {
			page = new PaginationVO<PoundNoteResp>();
			query.setState("1");
			long count = poundNoteMapper.purchasePageCount(query);
			if (count > 0) {
				List<PoundNoteResp> list = poundNoteMapper.purchasePage(query);
				page.setList(list);
			}
			page.setTotal(count);
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
		}
		return page;
	}

	@Override
	public Result addPurchasePoundNote(PoundNoteSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if (save != null) {
			PoundNote bean = new PoundNote();
			PropertyUtils.copyProperties(bean, save);
			bean.setId(UUIDUtil.getId());
			GetCodeReq codeReq = new GetCodeReq();
			codeReq.setCode("RK");
			codeReq.setCodeType(true);
			codeReq.setUserid(save.getMakerid());
			bean.setCode(systemCodeService.getCode(codeReq).getData().toString());
			if (StringUtils.isNotBlank(save.getBillid()) && StringUtils.isNotBlank(save.getBilldetailid())) {
				PurchaseApplication purchaseApplication = purchaseApplicationMapper.selectByPrimaryKey(save.getBillid());
				if (purchaseApplication != null) {
					bean.setBillcode(purchaseApplication.getCode());
					bean.setReceivedepartmentid(purchaseApplication.getOrgid());
					bean.setReceivedepartmentname(purchaseApplication.getOrgname());
				}
			}
			setDetails(save, bean);
			bean.setReturnstatus("0");
			bean.setRedcollide("0");
			bean.setStatus("1");
			bean.setBilltype("0");
			bean.setNetweight(bean.getGrossweight() - bean.getTareweight());
			bean.setDeductionweight(0D);
			bean.setDeductionother(0D);
			bean.setState("1");
			bean.setCreator(save.getMakerid());
			bean.setCreatetime(System.currentTimeMillis());
			bean.setModifier(save.getMakerid());
			bean.setModifytime(System.currentTimeMillis());
			if (poundNoteMapper.insertSelective(bean) > 0 
					&& StringUtils.equals(systemCodeService.updateCodeItem(codeReq).getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())) {
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			} else {
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	private void setDetails(PoundNoteSave save, PoundNote bean) {
		if (StringUtils.isNotBlank(save.getVehicleid())) {
			VehicleManage vehicle = vehicleManageMapper.selectByPrimaryKey(save.getVehicleid());
			if (vehicle != null) {
				bean.setVehicleno(vehicle.getVehicleno());
			}
		}
		if (StringUtils.isNotBlank(save.getDriverid())) {
			DriverManage driver = driverManageMapper.selectByPrimaryKey(save.getDriverid());
			if (driver != null) {
				bean.setDrivername(driver.getName());
				bean.setDriveridentityno(driver.getIdentityno());
			}
		}
		if (StringUtils.isNotBlank(save.getWarehouseid())) {
			WarehouseManage warehouse = warehouseManageMapper.selectByPrimaryKey(save.getWarehouseid());
			if (warehouse != null) {
				bean.setWarehousename(warehouse.getName());
			}
		}
		if (StringUtils.isNotBlank(save.getMinemouthid())) {
			MinemouthManage minemouth = minemouthManageMapper.selectByPrimaryKey(save.getMinemouthid());
			if (minemouth != null) {
				bean.setMinemouthname(minemouth.getName());
			}
		}
		if (StringUtils.isNotBlank(save.getYardid())) {
			YardManage yard = yardManageMapper.selectByPrimaryKey(save.getYardid());
			if (yard != null) {
				bean.setYardname(yard.getName());
			}
		}
		//收料员
		if (StringUtils.isNotBlank(save.getReceiverpersonid())) {
			
		}
	}

	@Override
	public Result returnAddPoundNote(PoundNoteSave save) throws Exception {
		Result result = Result.getParamErrorResult();
		if (save != null) {
			if (StringUtils.isNotBlank(save.getId())){
				PoundNote bean = poundNoteMapper.selectByPrimaryKey(save.getId());
				bean.setId(UUIDUtil.getId());
				bean.setArrivepoundnotecode(bean.getCode());
				GetCodeReq codeReq = new GetCodeReq();
				codeReq.setCode("RK");
				codeReq.setCodeType(true);
				codeReq.setUserid(save.getMakerid());
				bean.setCode(systemCodeService.getCode(codeReq).getData().toString());
				bean.setGrossweight(save.getGrossweight());
				bean.setTareweight(save.getTareweight());
				bean.setNetweight(save.getNetweight());
				setDetails(save, bean);
				bean.setLighttime(save.getLighttime());
				bean.setWeighttime(save.getWeighttime());
				bean.setReturnstatus("0");
				bean.setRedcollide("0");
				bean.setStatus("2");
				bean.setBilltype("0");
				bean.setDeductionweight(0D);
				bean.setDeductionother(0D);
				bean.setState("1");
				bean.setCreator(save.getMakerid());
				bean.setCreatetime(System.currentTimeMillis());
				bean.setModifier(save.getMakerid());
				bean.setModifytime(System.currentTimeMillis());
				if (poundNoteMapper.insertSelective(bean) > 0 
						&& StringUtils.equals(systemCodeService.updateCodeItem(codeReq).getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())) {
					result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
				} else {
					result.setErrorCode(ErrorCode.OPERATE_ERROR);
				}
			}
		}
		return result;
	}

	@Override
	public Result purchaseRedcollide(PoundNoteQuery query) {
		Result result = Result.getParamErrorResult();
		if (query != null && StringUtils.isNotBlank(query.getId())) {
			PoundNote bean = new PoundNote();
			bean.setId(query.getId());
			bean.setRedcollide("1");
			bean.setModifier(query.getCurrId());
			bean.setModifytime(System.currentTimeMillis());
			if (poundNoteMapper.updateByPrimaryKeySelective(bean) > 0) {
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			} else {
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Override
	public Result purchaseInvalid(PoundNoteQuery query) {
		Result result = Result.getParamErrorResult();
		if (query != null && StringUtils.isNotBlank(query.getId())) {
			PoundNote bean = new PoundNote();
			bean.setId(query.getId());
			bean.setStatus("3");
			bean.setModifier(query.getCurrId());
			bean.setModifytime(System.currentTimeMillis());
			if (poundNoteMapper.updateByPrimaryKeySelective(bean) > 0) {
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			} else {
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Override
	public PoundNoteResp findOne(String id) throws Exception {
		PoundNoteResp resp = null;
		if (StringUtils.isNotBlank(id)) {
			resp = poundNoteMapper.findOne(id);
			if(resp != null && StringUtils.equals(resp.getBilltype(), "2")){
				resp.setSalesApplicationList(getSalesApplicationListByPoundNoteId(resp.getId()));
			}
		}
		return resp;
	}
	
public Result findByBillid(String billid) throws Exception{
		Result rs = Result.getParamErrorResult();
		if(StringUtils.isNotBlank(billid)){
			PoundNote note = poundNoteMapper.findByBillid(billid);
			PoundNoteResp resp = new PoundNoteResp();
			PropertyUtils.copyProperties(resp, note);
			rs = Result.getSuccessResult();
			rs.setData(resp);
		}
		return rs;
	}	private List<SalesApplicationResp> getSalesApplicationListByPoundNoteId(String poundNoteId) throws Exception {
		List<SalesApplicationResp> listApplication = null;
		if(StringUtils.isNotBlank(poundNoteId)){
			List<SalesApplicationJoinPoundNote> listPoundNote = salesApplicationJoinPoundNoteMapper.selectByPoundNoteId(poundNoteId);
			if(CollectionUtils.isNotEmpty(listPoundNote)){
				List<String> ids = new ArrayList<String>();
				List<String> detailIds = new ArrayList<String>();
				for(SalesApplicationJoinPoundNote join : listPoundNote){
					ids.add(join.getBillid());
					detailIds.add(join.getBilldetailid());
				}
				listApplication = salesApplicationService.selectByIds(ids, false);
				List<SalesApplicationDetailResp> listApplicationDetail = salesApplicationDetailService.selectByIds(detailIds);
				for(SalesApplicationJoinPoundNote join : listPoundNote){
					SalesApplicationResp application = null;
					for(SalesApplicationResp applicationResp : listApplication){
						if(StringUtils.equals(applicationResp.getId(), join.getBillid())){
							application = applicationResp;
						}
					}
					if(application != null){
						List<SalesApplicationDetailResp> listDetail = application.getList();
						if(CollectionUtils.isEmpty(listDetail)){
							listDetail = new ArrayList<SalesApplicationDetailResp>();
						}
						for(SalesApplicationDetailResp detailResp : listApplicationDetail){
							if(StringUtils.equals(detailResp.getId(), join.getBilldetailid())){
								listDetail.add(detailResp);
								application.setList(listDetail);
							}
						}
					}
				}
			}
		}
		return listApplication;
	}
	@SuppressWarnings("unused")
	private List<PoundNoteResp> copyBeanList2RespList(List<PoundNote> list, boolean setApplication) throws Exception {
		List<PoundNoteResp> listResp = null;
		if (list != null && list.size() > 0) {
			listResp = new ArrayList<PoundNoteResp>();
			for (PoundNote sa : list) {
				listResp.add(copyBean2Resp(sa, setApplication));
			}
		}
		return listResp;
	}
	
	private PoundNoteResp copyBean2Resp(PoundNote bean, boolean setApplication) throws Exception {
		PoundNoteResp resp = null;
		if (bean != null) {
			resp = new PoundNoteResp();
			PropertyUtils.copyProperties(resp, bean);
			if (setApplication) {
				
			}
		}
		return resp;
	}

	@Override
	public PaginationVO<PoundNoteResp> salesPage(PoundNoteQuery query) {
		PaginationVO<PoundNoteResp> page = null;
		if (query != null) {
			page = new PaginationVO<PoundNoteResp>();
			query.setState("1");
			long count = poundNoteMapper.salesPageCount(query);
			if (count > 0) {
				List<PoundNoteResp> list = poundNoteMapper.salesPage(query);
				page.setList(list);
			}
			page.setTotal(count);
			page.setPageNo(query.getPageNo());
			page.setPageSize(query.getPageSize());
		}
		return page;
	}

	@Override
	public Result addSalesPoundNote(PoundNoteSave save, String bills) throws Exception {
		Result result = Result.getParamErrorResult();
		if (save != null) {
			PoundNote bean = new PoundNote();
			PropertyUtils.copyProperties(bean, save);
			bean.setId(UUIDUtil.getId());
			GetCodeReq codeReq = new GetCodeReq();
			codeReq.setCode("CK");
			codeReq.setCodeType(true);
			codeReq.setUserid(save.getMakerid());
			bean.setCode(systemCodeService.getCode(codeReq).getData().toString());
			if (StringUtils.isNotBlank(save.getBillid()) && StringUtils.isNotBlank(save.getBilldetailid())) {
				SalesApplication salesApplication = salesApplicationMapper.selectByPrimaryKey(save.getBillid());
				if (salesApplication != null) {
					bean.setBillcode(salesApplication.getCode());
					bean.setReceivedepartmentid(salesApplication.getOrgid());
					bean.setReceivedepartmentname(salesApplication.getOrgname());
				}
			}
			setDetails(save, bean);
			bean.setReturnstatus("0");
			bean.setRedcollide("0");
			bean.setStatus("1");
			bean.setBilltype("2");
			bean.setNetweight(bean.getGrossweight() - bean.getTareweight());
			bean.setDeductionweight(0D);
			bean.setDeductionother(0D);
			bean.setState("1");
			bean.setCreator(save.getMakerid());
			bean.setCreatetime(System.currentTimeMillis());
			bean.setModifier(save.getMakerid());
			bean.setModifytime(System.currentTimeMillis());
			JSONArray array = JSONArray.parseArray(bills);
			List<SalesApplicationJoinPoundNote> list = new ArrayList<SalesApplicationJoinPoundNote>();
			if(array != null && array.size() > 0){
				for(Object object : array){
					SalesApplicationJoinPoundNote join = new SalesApplicationJoinPoundNote();
					JSONObject jsonObject = (JSONObject) object;
					String billid = jsonObject.getString("billid");
					String billdetailid = jsonObject.getString("billdetailid");
					join.setId(UUIDUtil.getId());
					join.setBillid(billid);
					join.setBilldetailid(billdetailid);
					join.setPoundnoteid(bean.getId());
					SalesApplicationDetail applicationDetail = salesApplicationDetailMapper.selectByPrimaryKey(billdetailid);
					if(applicationDetail != null){
						join.setBillsum(applicationDetail.getSalessum());
					}
					join.setTakeamount(bean.getPickupquantity());
					join.setState("1");
					join.setCreator(bean.getCreator());
					join.setCreatetime(System.currentTimeMillis());
					join.setModifier(bean.getModifier());
					join.setModifytime(System.currentTimeMillis());
					list.add(join);
				}
			}
			if (poundNoteMapper.insertSelective(bean) > 0 
					&& StringUtils.equals(systemCodeService.updateCodeItem(codeReq).getCode(), ErrorCode.SYSTEM_SUCCESS.getCode())
					&& salesApplicationJoinPoundNoteMapper.insertBatch(list) > 0) {
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			} else {
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Override
	public Result updateSerialNumber(PoundNoteQuery query) {
		Result result = Result.getParamErrorResult();
		if (query != null && StringUtils.isNotBlank(query.getId())
				&& StringUtils.isNotBlank(query.getBatchnumberid())
				&& StringUtils.isNotBlank(query.getSerialnumber())) {
			PoundNote bean = new PoundNote();
			bean.setId(query.getId());
			bean.setBatchnumberid(query.getBatchnumberid());
			bean.setSerialnumber(query.getSerialnumber());
			bean.setModifier(query.getCurrId());
			bean.setModifytime(System.currentTimeMillis());
			if (poundNoteMapper.updateByPrimaryKeySelective(bean) > 0) {
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			} else {
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Override
	public Result salesRedcollide(PoundNoteQuery query) {
		Result result = Result.getParamErrorResult();
		if (query != null && StringUtils.isNotBlank(query.getId())) {
			PoundNote bean = new PoundNote();
			bean.setId(query.getId());
			bean.setRedcollide("1");
			bean.setModifier(query.getCurrId());
			bean.setModifytime(System.currentTimeMillis());
			if (poundNoteMapper.updateByPrimaryKeySelective(bean) > 0) {
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			} else {
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

	@Override
	public Result salesInvalid(PoundNoteQuery query) {
		Result result = Result.getParamErrorResult();
		if (query != null && StringUtils.isNotBlank(query.getId())) {
			PoundNote bean = new PoundNote();
			bean.setId(query.getId());
			bean.setStatus("3");
			bean.setModifier(query.getCurrId());
			bean.setModifytime(System.currentTimeMillis());
			if (poundNoteMapper.updateByPrimaryKeySelective(bean) > 0) {
				result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
			} else {
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return result;
	}

}
