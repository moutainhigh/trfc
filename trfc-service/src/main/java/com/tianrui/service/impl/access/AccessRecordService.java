package com.tianrui.service.impl.access;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.access.IAccessRecordService;
import com.tianrui.api.req.basicFile.measure.VehicleCheckApi;
import com.tianrui.api.req.businessManage.salesManage.ApiDoorSystemSave;
import com.tianrui.api.req.businessManage.salesManage.SalesArriveQuery;
import com.tianrui.service.bean.access.AccessRecord;
import com.tianrui.service.bean.basicFile.measure.VehicleManage;
import com.tianrui.service.bean.businessManage.cardManage.Card;
import com.tianrui.service.bean.businessManage.salesManage.SalesArrive;
import com.tianrui.service.mapper.access.AccessRecordMapper;
import com.tianrui.service.mapper.basicFile.measure.VehicleManageMapper;
import com.tianrui.service.mapper.businessManage.cardManage.CardMapper;
import com.tianrui.service.mapper.businessManage.salesManage.SalesArriveMapper;
import com.tianrui.smartfactory.common.constants.ErrorCode;
import com.tianrui.smartfactory.common.utils.UUIDUtil;
import com.tianrui.smartfactory.common.vo.Result;

@Service
public class AccessRecordService implements IAccessRecordService {

	@Autowired
	private AccessRecordMapper accessRecordMapper;
	@Autowired
	private CardMapper cardMapper;
	@Autowired
	private SalesArriveMapper salesArriveMapper;
	@Autowired
	private VehicleManageMapper vehicleManageMapper;
	
	/**
	 * 新增数据
	 */
	@Override
	public Result add(ApiDoorSystemSave api) {
		Result result = Result.getParamErrorResult();
		if(api!=null && StringUtils.isNotBlank(api.getNotionformcode()) && StringUtils.isNotBlank(api.getIccode())){
			Card card = new Card();
			card.setState("1");
			card.setCardstatus("1");
			card.setCardcode(api.getIccode());
			List<Card> listCard = cardMapper.selectSelective(card);
			if(listCard == null || listCard.size() == 0){
				result.setErrorCode(ErrorCode.CARD_NOT_EXIST);
				return result;
			}
			SalesArriveQuery query = new SalesArriveQuery();
			query.setIcardid(listCard.get(0).getId());
			query.setState("1");
			int count = salesArriveMapper.checkICUse(query);
			if(count > 0){
				result.setErrorCode(ErrorCode.CARD_IN_USE);
				return result;
			}
			AccessRecord record = new AccessRecord();
			record.setId(UUIDUtil.getId());
			record.setSalesarrivecode(api.getNotionformcode());
			record.setIcardcode(api.getIccode());
			record.setAccesstype(api.getType());
			record.setIntotime(api.getTime());
			//此处添加 创建者 创建时间 修改者 修改时间
			record.setCreator(api.getCurrUid());
			record.setCreatetime(System.currentTimeMillis());
			record.setModifier(api.getCurrUid());
			record.setModifytime(System.currentTimeMillis());
			int index = accessRecordMapper.insertSelective(record);
			//判断操作失败
			if(index<=0){
				result.setErrorCode(ErrorCode.OPERATE_ERROR);
				return result;
			}
			//操作成功
			result = Result.getSuccessResult();
			result.setData(index>0);
		}
		return result;
	}

	@Override
	public Result leaveFactoryCheckApi(VehicleCheckApi api) {
		Result result = Result.getParamErrorResult();
		if(api != null && StringUtils.isNotBlank(api.getVehicleNo()) && StringUtils.isNotBlank(api.getRfid())){
			VehicleManage vehicle = new VehicleManage();
			vehicle.setVehicleno(api.getVehicleNo());
			vehicle.setRfid(api.getRfid());
			vehicle.setState("1");
			List<VehicleManage> listVehicle = vehicleManageMapper.selectSelective(vehicle);
			if(listVehicle == null || listVehicle.size() == 0){
				result.setErrorCode(ErrorCode.RFID_VEHICLE_NOT_EXIST);
				return result;
			}
			SalesArrive sa = new SalesArrive();
			sa.setVehicleid(listVehicle.get(0).getId());
			sa.setStatus("2");
			sa.setState("1");
			List<SalesArrive> listSalesArrive = salesArriveMapper.selectSelective(sa);
			if(listSalesArrive == null || listSalesArrive.size() == 0){
				result.setErrorCode(ErrorCode.VEHICLE_NOT_ARRIVE);
				return result;
			}
			AccessRecord access = new AccessRecord();
			access.setSalesarrivecode(listSalesArrive.get(0).getCode());
			access.setIcardcode(cardMapper.selectByPrimaryKey(listSalesArrive.get(0).getIcardid()).getCardcode());
			access.setAccesstype("1");
			access.setState("1");
			List<AccessRecord> listAccess = accessRecordMapper.selectSelective(access);
			if(listAccess == null || listAccess.size() == 0){
				result.setErrorCode(ErrorCode.VEHICLE_ARRIVE_NOT_ENTER);
				return result;
			}
			result.setErrorCode(ErrorCode.SYSTEM_SUCCESS);
		}
		return result;
	}
	
}
