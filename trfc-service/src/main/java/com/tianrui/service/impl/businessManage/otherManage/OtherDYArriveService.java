package com.tianrui.service.impl.businessManage.otherManage;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.businessManage.otherManage.IOtherDYArriveService;
import com.tianrui.api.intf.system.base.ISystemCodeService;
import com.tianrui.api.req.businessManage.otherManage.OtherArriveReq;
import com.tianrui.api.req.system.base.GetCodeReq;
import com.tianrui.api.resp.businessManage.otherManage.OtherArriveResp;
import com.tianrui.service.bean.basicFile.measure.YardManage;
import com.tianrui.service.bean.basicFile.nc.MaterielManage;
import com.tianrui.service.bean.businessManage.cardManage.Card;
import com.tianrui.service.bean.businessManage.otherManage.OtherArrive;
import com.tianrui.service.bean.businessManage.purchaseManage.PurchaseArrive;
import com.tianrui.service.bean.businessManage.salesManage.SalesArrive;
import com.tianrui.service.mapper.basicFile.measure.YardManageMapper;
import com.tianrui.service.mapper.basicFile.nc.MaterielManageMapper;
import com.tianrui.service.mapper.businessManage.cardManage.CardMapper;
import com.tianrui.service.mapper.businessManage.otherManage.OtherArriveMapper;
import com.tianrui.service.mapper.businessManage.purchaseManage.PurchaseArriveMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesArriveMapper;
import com.tianrui.smartfactory.common.constants.Constant;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.Result;
@Service
public class OtherDYArriveService implements IOtherDYArriveService{

	@Autowired
	private OtherArriveMapper otherArriveMapper;
	@Autowired
	private ISystemCodeService systemCodeService;
	@Autowired
	private SalesArriveMapper salesArriveMapper;
	@Autowired
	private PurchaseArriveMapper purchaseArriveMapper;
	@Autowired
	private CardMapper cardMapper;
	@Autowired
	private YardManageMapper yardManageMapper;
	@Autowired
	private MaterielManageMapper materielManageMapper;

	
	@Override
	public Result add(OtherArriveReq req) throws Exception {
		Result rs = Result.getParamErrorResult();
		OtherArrive oa = new OtherArrive();
		if(req!=null && validDriverAndVehicle(req,rs,oa)){
			PropertyUtils.copyProperties(oa, req);
			GetCodeReq codeReq = new GetCodeReq();
			codeReq.setCode(req.getCodekey());
			codeReq.setCodeType(true);
			codeReq.setUserid(req.getUserid());
			oa.setCode(String.valueOf(systemCodeService.getCode(codeReq).getData()));
			//获取id
			oa.setId(UUIDUtil.getId());
			oa.setAuditstatus(Constant.ONE_STRING);
			oa.setStatus(Constant.SIX_STRING);
			oa.setReceivedepartmentid(Constant.ORG_ID);
			oa.setCreator(req.getUserid());
			oa.setCreatetime(System.currentTimeMillis());
			oa.setModifier(req.getUserid());
			oa.setModifytime(System.currentTimeMillis());
			oa.setUtc(System.currentTimeMillis());
			//更新数据到数据库
			int index = otherArriveMapper.insertSelective(oa);
			//判断操作是否成功
			if(index>0){
				systemCodeService.updateCodeItem(codeReq);
				otherArriveMapper.emptyForceOutFactoryByVehicle(req.getVehicleid());
				rs = Result.getSuccessResult();
			}else{
				rs.setErrorCode(ErrorCode.OPERATE_ERROR);
			}
		}
		return rs;
	}
	private boolean validDriverAndVehicle(OtherArriveReq req, Result result,OtherArrive oa) {
		boolean flag = true;
		if(StringUtils.equals(req.getBusinesstype(), "4")){
			if(StringUtils.isNotBlank(req.getVehicleid())){
				oa.setVehicleid(req.getVehicleid());
				List<OtherArrive> listVehicle2 = otherArriveMapper.checkDriverAndVehicleAndIcardIsUse(oa);
				if(listVehicle2!=null && listVehicle2.size()>0 && StringUtils.equals(listVehicle2.get(0).getBusinesstype(), "4")){
					result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
					result.setError("此车辆己有厂内倒运通知单、待出厂后进行派车，现有车辆业务单据号为:"+listVehicle2.get(0).getCode()+"，如有疑问请与销售处联系！");
					flag = false;
				}
			}
//			if(StringUtils.isNotBlank(req.getDriverid())){
//				oa.setVehicleid(null);
//				oa.setDriverid(req.getDriverid());
//				List<OtherArrive> listDriver2 = otherArriveMapper.checkDriverAndVehicleAndIcardIsUse(oa);
//				if(listDriver2!=null && listDriver2.size()>0 && StringUtils.equals(listDriver2.get(0).getBusinesstype(), "4")){
//					result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
//					result.setError("此司机己有厂内倒运通知单、待出厂后进行派车，现有车辆业务单据号为:"+listDriver2.get(0).getCode()+"，如有疑问请与销售处联系！");
//					flag = false;
//				}
//			}
		}else{
			PurchaseArrive pa = new PurchaseArrive();
			SalesArrive sa = new SalesArrive();
			if(StringUtils.isNotBlank(req.getVehicleid())){
				pa.setVehicleid(req.getVehicleid());
				List<PurchaseArrive> listVehicle = purchaseArriveMapper.checkDriverAndVehicleIsUse(pa);
				if(listVehicle != null && listVehicle.size() > 0){
					result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
					result.setError("此车辆己有到货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listVehicle.get(0).getCode()+"，如有疑问请与销售处联系！");
					flag = false;
				}
				sa.setVehicleid(req.getVehicleid());
				List<SalesArrive> listVehicle1 = salesArriveMapper.checkDriverAndVehicleIsUse(sa);
				if(listVehicle1 != null && listVehicle1.size() > 0){
					result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
					result.setError("此车辆己有提货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listVehicle1.get(0).getCode()+"，如有疑问请与销售处联系！");
					flag = false;
				}
				oa.setVehicleid(req.getVehicleid());
				List<OtherArrive> listVehicle2 = otherArriveMapper.checkDriverAndVehicleAndIcardIsUse(oa);
				if(listVehicle2!=null && listVehicle2.size()>0){
					if(StringUtils.equals(listVehicle2.get(0).getBusinesstype(), "5")){
						result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
						result.setError("此车辆己有其他入库通知单、待出厂后进行派车，现有车辆业务单据号为:"+listVehicle2.get(0).getCode()+"，如有疑问请与销售处联系！");
						flag = false;
					}else if(StringUtils.equals(listVehicle2.get(0).getBusinesstype(), "7")){
						result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
						result.setError("此车辆己有其他出库通知单、待出厂后进行派车，现有车辆业务单据号为:"+listVehicle2.get(0).getCode()+"，如有疑问请与销售处联系！");
						flag = false;
					}
				}
			}
//			if(StringUtils.isNotBlank(req.getDriverid())){
//				pa.setVehicleid(null);
//				pa.setDriverid(req.getDriverid());
//				List<PurchaseArrive> listDriver = purchaseArriveMapper.checkDriverAndVehicleIsUse(pa);
//				if(listDriver != null && listDriver.size() > 0){
//					result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
//					result.setError("此司机己有到货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listDriver.get(0).getCode()+"，如有疑问请与销售处联系！");
//					flag = false;
//				}
//				sa.setVehicleid(null);
//				sa.setDriverid(req.getDriverid());
//				List<SalesArrive> listDriver1 = salesArriveMapper.checkDriverAndVehicleIsUse(sa);
//				if(listDriver1 != null && listDriver1.size() > 0){
//					result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
//					result.setError("此司机己有提货通知单、待出厂后进行派车，现有车辆业务单据号为:"+listDriver1.get(0).getCode()+"，如有疑问请与销售处联系！");
//					flag = false;
//				}
//				oa.setVehicleid(null);
//				oa.setDriverid(req.getDriverid());
//				List<OtherArrive> listDriver2 = otherArriveMapper.checkDriverAndVehicleAndIcardIsUse(oa);
//				if(listDriver2!=null && listDriver2.size()>0){
//					if(StringUtils.equals(listDriver2.get(0).getBusinesstype(), "5")){
//						result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
//						result.setError("此司机己有其他入库通知单、待出厂后进行派车，现有车辆业务单据号为:"+listDriver2.get(0).getCode()+"，如有疑问请与销售处联系！");
//						flag = false;
//					}else if(StringUtils.equals(listDriver2.get(0).getBusinesstype(), "7")){
//						result.setErrorCode(ErrorCode.PARAM_REPEAT_ERROR);
//						result.setError("此司机己有其他出库通知单、待出厂后进行派车，现有车辆业务单据号为:"+listDriver2.get(0).getCode()+"，如有疑问请与销售处联系！");
//						flag = false;
//					}
//				}
//			}
		}
		//ic卡信息
		if(StringUtils.isNotBlank(req.getIcardno())){
			Card card = cardMapper.selectByCardno(req.getIcardno());
			if(card!=null){
				//ic卡是否占用
				SalesArrive sales = salesArriveMapper.checkICUse(card.getId());
				PurchaseArrive purchase = purchaseArriveMapper.checkICUse(card.getId());
				oa.setVehicleid(null);
				oa.setIcardid(card.getId());
				List<OtherArrive> listIcard2 = otherArriveMapper.checkDriverAndVehicleAndIcardIsUse(oa);
				if(sales == null && purchase == null && listIcard2.size()==0) {
					//获取icard id
					req.setIcardid(card.getId());
				}else{
					result.setErrorCode(ErrorCode.CARD_IN_USE);
					flag = false;
				}
			}else{
				result.setErrorCode(ErrorCode.CARD_NOT_EXIST);
				flag = false;
			}
		}
		return flag;
	}
	@Override
	public OtherArriveResp getById(String id) throws Exception {
		OtherArrive oa = otherArriveMapper.selectByPrimaryKey(id);
		if (oa != null) {
			OtherArriveResp oar = new OtherArriveResp();
			PropertyUtils.copyProperties(oar, oa);
			if (StringUtils.isNotBlank(oar.getEnteryard())) {
				YardManage yard = yardManageMapper.selectByPrimaryKey(oar.getEnteryard());
				if (yard != null) {
					oar.setEnteryerdname(yard.getName());
				}
			}
			if (StringUtils.isNotBlank(oar.getLeaveyard())) {
				YardManage yard = yardManageMapper.selectByPrimaryKey(oar.getLeaveyard());
				if (yard != null) {
					oar.setLeaveyerdname(yard.getName());
				}
			}
			if (StringUtils.isNotBlank(oar.getMaterielid())) {
				MaterielManage material = materielManageMapper.selectByPrimaryKey(oar.getMaterielid());
				if (material != null) {
					oar.setMaterielname(material.getName());
				}
			}
			return oar;
		}
		return null;
	}

}
